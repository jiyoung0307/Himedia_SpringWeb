package com.greedy.parameter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/first/*")
@SessionAttributes({"id", "pwd"})
public class FirstController {

	@GetMapping("/regist")
	public void regist() {}
	
	/* 1. HttpServletRequest 방식으로 값 꺼내기 */
	@PostMapping("regist")
	public String registMenu(HttpServletRequest request, Model model) {
//		System.out.println("/first/regist 요청일 때 실행 될 핸들러 메소드...");
		
		String name = request.getParameter("name");
		int price = Integer.valueOf(request.getParameter("price"));
		int categoryCode = Integer.valueOf(request.getParameter("categoryCode"));
		
		String message = name + "을(를) 신규 메뉴 목록의 " + categoryCode + "번 카테고리에 " + 
		                 price + "원으로 등록하셨습니다!";
		System.out.println(message);
		
		model.addAttribute("message", message);
		model.addAttribute("message2", "또 다른 메세지");
		
		return "first/messagePrinter";
	}
	
	@GetMapping("modify")
	public void modify() {}
	
	/*
	 * 2. @RequestParam 방식으로 값 꺼내기
	 * 
	 * 요청 파라미터를 매핑하여 호출 시 값을 넣어주는 어노테이션이다.
	 * 
	 * 값이 넘어오지 않게 되면 ""와 같은 빈 문자열이 넘어오게 되는데, String이 아닌 자료형이 매개변수 일 때는
	 * parsing 관련 에러가 발생할 수 있다. 값이 넘어오지 않는 경우 defaultValue를 이용하게 되면
	 * 기본값으로 사용할 수 있다.
	 * 
	 * @RequrestParm 어노테이션을 통째로 안 적고 매개변수 명만 input태그의 name과 일치시키면 생략해도 되지만
	 * 가독성 측면이나 defaultValue같은 션을 달 때는 어노테이션을 달아주는 것이 좋다.
	 */
	@PostMapping("modify")
	public String modifyMenu(@RequestParam String modifyName, 
			@RequestParam(defaultValue="0") int modifyPrice,
			Model model) {
		String message = modifyName + "메뉴의 가격을 " + modifyPrice + "로 변경하였습니다.";
		
		model.addAttribute("message", message);
		
		return "first/messagePrinter";
	}
	
	@PostMapping("modifyAll")
	public String modifyMenu(@RequestParam Map<String, String> parameters,
			Model model) {
		String modifyName = parameters.get("modifyName2");
		int modifyPrice = Integer.valueOf(parameters.get("modifyPrice2"));
		
		String message = modifyName + "메뉴의 가격을 " + modifyPrice + "로 변경하였습니다.";
		
		model.addAttribute("message", message);
		
		return "first/messagePrinter";
	}
	
	/*
	 * 3. @ModelAttribute를 이용하는 방법
	 * 
	 * DTO 같은 모델을 커맨드 객체를 사용해서 전달 받아보자.
	 * 
	 * @ModelAttribute의 경우 커맨드 객체를 생성하여 매개변수로 전달해 준 뒤 해당 인스턴스를 Model에 담는다.
	 * 
	 * @ModelAttribute("모델에 담을 key값")을 지정할 수 있으며, 지정하지 않으면 타입의 앞 글자를 소문자로한
	 * 네이밍 규칙을 따른다. 즉, menuDTO라는 키값으로 모델에 담긴다.
	 */
	@GetMapping("search")
	public void search() {}
	
	@PostMapping("search")
	public String searchMenu(@ModelAttribute("menu") MenuDTO menu) {
		System.out.println(menu);	// 매개변수로 MenuDTO로 사용자의 입력값이 묶여 넘어온 것을 활용할 수도 있다.
		return "first/searchResult";
	}
	
	/* HttpSession 이용하기 */
	@GetMapping("login")
	public void login() {}
	
	@PostMapping("login1")
	public String sessionTest1(@RequestParam String id, @RequestParam String pwd
			, HttpSession session) {
		
		session.setAttribute("id", id);
		session.setAttribute("pwd", pwd);
		
		return "first/loginResult";
	}
	
	@GetMapping("logout1")
	public String logoutTest1(HttpSession session){
		session.invalidate();
		
		return "first/loginResult";
	}
	
	/*
	 * 기존의 HttpSession에서 제공하는 invalidate() 메소드로는 로그아웃 처리가 되지 않는다.
	 * @SessionAttributes로 등록된 값은 session의 상태를 관리하는 SessionStatus의
	 * setComplete() 메소드를 호출해야 사용이 만료된다.
	 */
	@PostMapping("login2")
	public String sessionTest2(@RequestParam String id, @RequestParam String pwd,
			Model model) {
		
		model.addAttribute("id", id);
		model.addAttribute("pwd", pwd);
		
		return "first/loginResult";
	}
	
	@GetMapping("logout2")
	public String logoutTest2(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		
		return "first/loginResult";
	}
	
	/* @RequestBody 활용해 보기 */
	@GetMapping("body")
	public void body() {}
	
	@PostMapping("body")
	public String bodyTest(
			@RequestBody String list,
			@RequestHeader("content-type") String contentType,
			@CookieValue("JSESSIONID") String sessionId	
			) {
		
		System.out.println("RequestBody에 담겨온 내용 확인 : " + list);
		System.out.println("RequestHeader에서 content-type속성만 확인 : " + contentType);
		System.out.println("Cookie에 담긴 JSESSIONID 속성만 확인 : " + sessionId);
		
		return "main";
	}
}




