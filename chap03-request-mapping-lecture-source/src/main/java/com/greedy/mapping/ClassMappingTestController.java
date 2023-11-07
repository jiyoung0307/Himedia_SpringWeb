package com.greedy.mapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/order/*")
public class ClassMappingTestController {

	@GetMapping("/regist")
	public String registOrder(Model model) {
		model.addAttribute("message", "GET 방식의 주문 등록용 핸들러 메소드 호출함...");
		return "mappingResult";
	}
	
	@RequestMapping(value= {"modify", "delete"}, method=RequestMethod.POST)
	public String modifyAndDeleteOrder(Model model) {
		model.addAttribute("message", "POST 방식의 주문 정보 수정 및 삭제 시 공통적으로 동작하는 핸들러 메소드 호출함...");
		return "mappingResult";
	}
	
	/*
	 * PathVariable로 전달되는 값은 매개변수 이름이 동일하게 쓰이는 것이 편하다.
	 * 만약 동일하지 않으면 @PathVariable("이름")을 설정해 주어야 한다.
	 * 
	 * 핸들러 메소드에서 요청 객체를 들춰서 전달된 값을 꺼내볼 필요 없이 url 경로에 위치한 값을 
	 * value로 인식하는 방법이다.
	 * 특히 REST형 웹 서비스를 설계할 때 유용하다.
	 * 
	 * 스프링 4.3버전부터 지원되는 어노테이션
	 * 핸들러 메소드를 좀 더 간결하게 코딩할 수 있게 해준다.
	 * 
	 * 요청 메소드          어노테이션
	 * POST              @PostMapping
	 * GET               @GetMapping
	 * DELETE			 @DeleteMapping
	 * PUT				 @PutMapping
	 */
	@GetMapping("/delete/{orderNo}")
	public String deleteOrder(Model model, @PathVariable("orderNo") int orderNo) {
		model.addAttribute("message", orderNo + "번 주문 삭제용 핸들러 메소드 호출함...");
		return "mappingResult";
	}
}













