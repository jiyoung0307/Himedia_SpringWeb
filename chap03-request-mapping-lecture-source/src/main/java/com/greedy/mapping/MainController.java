package com.greedy.mapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	/* 
	 * "/" 요청에 대해서 main()메소드가 키와 밸류 형태로 handler mapping에 세팅 된다.
	 *  이때 요청에 매핑된 main()메소드는 핸들러 메소드라고 불린다. 
	 */
	@RequestMapping("/")
	public String main() {
		
		
		return "main";
	}
}
