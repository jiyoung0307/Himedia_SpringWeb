package com.greedy.mapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MethodMappingTestController {

	/* method 속성을 적지 않으면 GET이나 POST 요청에 대해 모두 매핑 될 수 있다. */
//	@RequestMapping("/menu/regist")
//	public String registMenu(Model model) {			// 매개변수에 적히는 객체는 스프링의 핸들러에 의해 자동으로 객체가 생성되어 들어온다.(커맨드 객체라고 한다.)
//		
//		model.addAttribute("message", "신규 메뉴 등록용 핸들러 메소드 호출함...");
//		
//		return "mappingResult";
//	}
	
	@RequestMapping(value="/menu/regist", method=RequestMethod.GET)
	public String registMenu(Model model) {
		
		model.addAttribute("message", "(GET만 구분)신규 메뉴 등록용 핸들러 메소드 호출함...");
		
		return "mappingResult";
	}
	
	@RequestMapping(value="/menu/modify", method=RequestMethod.GET)
	public String getModifyMenu(Model model) {
		
		model.addAttribute("message", "GET 방식의 메뉴 수정용 핸들러 메소드 호출함...");
		
		return "mappingResult";
	}
	
	@RequestMapping(value="/menu/modify", method=RequestMethod.POST)
	public String postModifyMenu(Model model) {
		
		model.addAttribute("message", "POST 방식의 메뉴 수정용 핸들러 메소드 호출함...");
		
		return "mappingResult";
	}
	
	@GetMapping("/menu/delete")
	public String getDeleteMenu(Model model) {
		
		model.addAttribute("message", "GET 방식의 메뉴 삭제용 핸들러 메소드 호출함...");
		
		return "mappingResult";
	}
	
	@PostMapping("/menu/delete")
	public String postDeleteMenu(Model model) {
		
		model.addAttribute("message", "POST 방식의 메뉴 삭제용 핸들러 메소드 호출함...");
		
		return "mappingResult";
	}
	
}













