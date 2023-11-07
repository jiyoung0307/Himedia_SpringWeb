package com.greedy.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InterceptorController {
	
	@GetMapping("stopwatch")
	public String hadlerMethod() throws InterruptedException {
		System.out.println("stopwatch 핸들러 메소드 호출됨...");
		
		Thread.sleep(1000);
		
		return "result";
	}
}
