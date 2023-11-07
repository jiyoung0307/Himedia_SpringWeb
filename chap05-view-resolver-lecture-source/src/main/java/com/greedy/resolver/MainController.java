package com.greedy.resolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	/* 이번에는 index.jsp(main으로 포워딩 하는 것) 없이 기본 경로 요청과 main요청에 대해 main.jsp로 포워딩 해 보기 */
	@GetMapping("/")
	public String defaultLocation() {
		return "main";
	}
	
	@GetMapping("main")
	public void main() {}
}





