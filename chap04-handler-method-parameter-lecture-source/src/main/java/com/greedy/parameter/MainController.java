package com.greedy.parameter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	/* 아래와 동일하다 */
//	@RequestMapping("/main")
//	public String showMain() {
//		return "main";
//	}
	
	/* return  타입을 void로 하는 경우 viewResolver는 요청 주소 자체를 view의 이름으로 해석하게 된다. */
	@RequestMapping("/main")
	public void showMain() {}
}
