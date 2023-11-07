package com.greedy.resolver;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/*")
public class ResolverController {

	@GetMapping("string")
	public String stringReturning(Model model) {
		model.addAttribute("message", "문자열로 뷰 이름 반환함...");
		
		return "result";
	}
	
	/* 
	 * Model과 HttpServletRequest의 차이점
	 * 
	 * HttpServletRequest에 담긴 값은 Redirect 시 전환된 화면에서 활용할 수 없다.
	 * (redirect는 request객체를 유지하지 않고 새로 만든다.) 
	 * 
	 * 반면, Model은 Redirect 시에는 전환된 화면에 parameter로 담긴 값을 전송하고
	 * forward 시에는 requestScope로 담긴 값을 전송한다.
	 */
	@GetMapping("string-redirect")
	public String stringRedirect(Model model) throws UnsupportedEncodingException {
			
		/*
		 * Model을 써서 redirect를 할 때 어떤 값을 유지시키면서 redirect를 하고 싶을 때 사용하는 것으로
		 * Model에 담은 값을 파라미터(param)로 전달된다. 파라미터라는 것은 URL 경로 상에서 값이 전달된다는 것이고
		 * 이럴 경우 한글을 사용하고자 하면 인코딩, 디코딩 처리를 필터와 별개로 따로 해 주어야 한다. 
		 */
		model.addAttribute("message", URLEncoder.encode("세종대왕님이 만들어 주신 한글 입니다.", "UTF-8"));
		
		return "redirect:main";
	}
	
	@GetMapping("string-redirect-attr")
	public String stringRedirectFlashAttribute(RedirectAttributes rttr) {
		
		/*
		 * 리다이렉트 시 URL에 노출되지 않는 정보를 flash영역에 한번만 request에 담아서 redirect 할 수 있다.
		 * (이 때는 Model이 아닌 RedirectAttributes를 써야 한다.)
		 * 전달된 값을 꺼낼 때는 리퀘스트(requestScope)에서 꺼낼 수 있다.
		 * (내부적으로 세션에 임시로 값을 담고 소멸시키는 과정이 있으므로 session에 동일한 키 값이 존재하지 않아야 한다.)
		 */
		rttr.addFlashAttribute("flashMessage", "리다이렉트 attr 사용하여 redirect...");
		
		return "redirect:main"; 
	}
	
	@GetMapping("modelandview")
	public ModelAndView modelAndViewReturning(ModelAndView mv) {
		
		/*
		 * 모델(Model 객체)과 뷰(ViewTemplate 이름)를 합친 개념이다.
		 * 핸들러 어댑터가 핸들러 메소드를 호출하고 반환받은 ModelAndView를
		 * DispatcherServlet에 반환한다.
		 * DispatcherServlet은 ModelAndView에 있는 viewName 값을
		 * viewResolver에게 주게 되고 prefix와 suffix를 결합하여 뷰를 생성하게
		 * 된다.
		 */
		mv.addObject("message", "ModelAndView를 이용한 모델과 뷰 반환");
		mv.setViewName("result");
		
		return mv;
	}
	
	@GetMapping("modelandview-redirect")
	public ModelAndView modelAndViewRedirect(ModelAndView mv) throws UnsupportedEncodingException {
		
		mv.addObject("message2", URLEncoder.encode("ModelAndView를 이용한 redirect", "UTF-8"));
		mv.setViewName("redirect:main");
		
		return mv;
	}
	
	@GetMapping("modelandview-redirect-attr")
	public ModelAndView modelAndViewRedirect(ModelAndView mv, RedirectAttributes rttr) {
		
		rttr.addFlashAttribute("flashMessage2", "ModelAndView를 이용한 redirect");
		mv.setViewName("redirect:main");
		
		return mv;
	}
}









