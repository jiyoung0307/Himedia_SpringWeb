package com.greedy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
 * 인터셉터를 사용하는 경우(목적)
 * 로그인 체크, 권한 체크, 프로그램 실행시간 계산 작업 로그 처리, 업로드 파일 처리, 로케일(지역) 설정
 */
public class StopWatchInterceptor implements HandlerInterceptor{
	
	/* 필터와 달리 인터셉터는 스프링 컨테이너에 있는 빈을 활용(의존성 주입(DI))할 수 있다. */
	private final MemberService memberService;
	
	@Autowired
	public StopWatchInterceptor(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) {
		System.out.println("preHandler 호출함...");
		
		long startTime = System.currentTimeMillis();
		
		request.setAttribute("startTime", startTime);
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, @Nullable ModelAndView modelAndView) {
		System.out.println("postHandle 호출함...");
		
		long startTime = (Long)request.getAttribute("startTime");
		request.removeAttribute("startTime");
		
		long endTime = System.currentTimeMillis();
		
		modelAndView.addObject("interval", endTime - startTime);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, @Nullable Exception ex) {
		System.out.println("afterCompletion 호출함...");
		
		memberService.method();
	}
}




