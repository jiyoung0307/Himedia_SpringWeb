package com.greedy.exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandlerController {

	@GetMapping("simple-null")
	public String simpleNullPointerExceptionTest() {
		
		String str = null;
		System.out.println(str.charAt(0));
		
		return "main";
	}
	
	@GetMapping("simple-user")
	public String simpleUserExceptionTest() throws MemberRegistException {
		
		/* 이후 코드가 닿지 않는 Unrechable Exception으로 인해 에러 발생 시 자바를 속일 수 있는 코드 작성 */
		boolean check = true;
		if(check) {
			
			/* 사용자 정의형 예외(MemberRegistException) 발생 시키기 */
			throw new MemberRegistException("당신 같은 사람은 회원으로 받을 수 없습니다!");
		}
		
		return "main";
	}
	
	/* 이 클래스에서 발생한 NullPointerException 처리용 메소드(클래스 별로 따로 존재) */
	/* 기본적으로 제공되는 예외 타입은 requestScope에 exception이라는 키 값으로 예외 객체가 넘어가게 된다. */
	@ExceptionHandler(NullPointerException.class)
	public String nullPointerExceptionHandler(NullPointerException exception) {
		System.out.println("동작하는지 확인");
		return "error/nullPointer";
	}
	
	@GetMapping("annotation-user")
	public String annotationUserExceptionTest() throws MemberRegistException {
		
		boolean check = true;
		if(check) {
			throw new MemberRegistException("회원가입 당신은 안된다니깐!");
		}
		
		return "main";
	}
	
	/* 이 클래스에서 발생한 MemberRegistException 처리용 메소드(클래스 별로 따로 존재) */
	/* 우리가 정의한 예외 타입은 반드시 Model 객체를 사용하여 requestScope에 우리가 정의한 키 값으로 예외 객체를 넘겨야 한다. */
	@ExceptionHandler(MemberRegistException.class)
	public String userExceptionHandler(Model model, MemberRegistException exception) {
		
		model.addAttribute("exceptionMessage", exception);
		
		return "error/memberRegist";
	}
}







