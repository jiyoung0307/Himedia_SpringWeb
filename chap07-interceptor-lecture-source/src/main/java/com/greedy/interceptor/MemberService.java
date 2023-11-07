package com.greedy.interceptor;

import org.springframework.stereotype.Component;

@Component
public class MemberService {
	
	public void method() {
		System.out.println("메소드 호출 확인");
	}
}
