package com.greedy.spring.common.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.greedy.spring.member.model.dto.MemberDTO;

@WebFilter(urlPatterns = {"/notice/*", "/member/*", "/board/*", "/thumbnail/*"})
public class AuthenticationFilter implements Filter {
	
	Map<String, List<String>> permitURIList;
	
	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		/* 세션에 권한이 있는지 확인하여 허용된 url에만 접근 가능하도록 설정한다. */
		HttpServletRequest hrequest = (HttpServletRequest) request;
		String uri = hrequest.getRequestURI();
		
		/* 접근 목적에 해당하는 url만 추출 */
		String intent = uri.replace(hrequest.getContextPath(), "");
		System.out.println("intent : " + intent);
		
		HttpSession requestSession = hrequest.getSession();
		MemberDTO loginMember = (MemberDTO) requestSession.getAttribute("loginMember");
		
		boolean isAuthorized = false;
		
		/* 로그인 상태인 회원과 로그인 하지 않은 비회원에 대한 처리 */
		if(loginMember != null) {									// 회원일 시
			boolean isPermitAdmin = permitURIList.get("adminPermitList").contains(intent);
			boolean isPermitMember = permitURIList.get("memberPermitList").contains(intent);
			boolean isPermitAll = permitURIList.get("allPermitList").contains(intent);
			
			/* url 접근자의 권한 인증 후 인가 */
			if("ROLE_ADMIN".equals(loginMember.getRole())) {
				
				/* 관리자 권한 url 인가 처리*/
				if(isPermitAdmin || isPermitMember || isPermitAll) {
					isAuthorized = true;
				}
			} else if("ROLE_MEMBER".equals(loginMember.getRole())) {
				
				/* 일반 회원 권한 url 인가 */
				if((isPermitMember || isPermitAll) && !isPermitAdmin) {
					isAuthorized = true;
				}
			}
			
			/* 회원 중 url 권한이 있는지 없는지에 대한 처리 */
			if(isAuthorized) {
				chain.doFilter(request, response);
			} else {
				((HttpServletResponse) response).sendError(403);
			}
		} else {													// 비회원일 시

			/* 비회원이 접근 권한이 있는지 없는지에 대한 저리 */
			if(permitURIList.get("allPermitList").contains(intent)) {
				chain.doFilter(request, response);
			} else {
				request.setAttribute("message", "로그인이 필요한 서비스 입니다.");
				request.getRequestDispatcher("/WEB-INF/views/error/default.jsp").forward(hrequest, response);
			}
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
		permitURIList = new HashMap<>();
		List<String> adminPermitList = new ArrayList<>();
		List<String> memberPermitList = new ArrayList<>();
		List<String> allPermitList = new ArrayList<>();
		
		/* 관리자의 추가 접근 가능 url */
		adminPermitList.add("/notice/regist");
		adminPermitList.add("/notice/update");
		adminPermitList.add("/notice/delete");
		
		/* 일반 회원의 추가 접근 가능 url */
		memberPermitList.add("/member/update");
		memberPermitList.add("/member/delete");
		memberPermitList.add("/member/logout");
		memberPermitList.add("/notice/list");
		memberPermitList.add("/notice/detail");
		memberPermitList.add("/board/list");
		memberPermitList.add("/board/regist");
		memberPermitList.add("/board/detail");
		memberPermitList.add("/board/registReply");
		memberPermitList.add("/board/removeReply");
		memberPermitList.add("/thumbnail/list");
		memberPermitList.add("/thumbnail/regist");
		memberPermitList.add("/thumbnail/detail");

		/* 비회원의 접근 가능 url */
		allPermitList.add("/member/regist");
		allPermitList.add("/member/login");
		allPermitList.add("/member/idDupCheck");
		
		permitURIList.put("adminPermitList", adminPermitList);
		permitURIList.put("memberPermitList", memberPermitList);
		permitURIList.put("allPermitList", allPermitList);
		
	}

}
