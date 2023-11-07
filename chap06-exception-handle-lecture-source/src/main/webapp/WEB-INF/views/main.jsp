<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
		서블릿을 이용해 Exception Handler를 사용할 때는 web.xml에 추가 했었지만
		스프링 MVC에서는 클래스 예외 처리용 뷰를 관리하는 기능도 추가로 제공하고 있다.
		
		크게 두 가지 방식으로 살펴보자.
		1. SimpleMappingExceptionResolver를 이용하여 컨텍스트에서 발생항 예외를
		   카테고리별로 매핑할 수 있다.(전역)
		2. @ExceptionHandler 어노테이션을 이용하여 예외를 매핑할 수 있다.(클래스별)
	 -->
	 <h3>SimpleMappingExceptionResolver를 이용한 방식</h3>
	 <button onclick="location.href='simple-null'">NullPointerException 테스트</button>
	 <button onclick="location.href='simple-user'">사용자 정의 Exception 테스트</button>
	 
	 <h3>@ExceptionHandler 어노테이션을 이용한 방식</h3>
	 <button onclick="location.href='annotation-user'">사용자 정의 Exception 테스트</button>
	 
</body>
</html>




