<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">interceptor를 이용하여 controller 요청 가로채기</h1>
	
	<!-- 
	    인터셉터는 웹 요청을 가로채 전처리/후처리를 할 수 있다.
	    핸들러 인터셉터는 스프링 웹 애플리케이션 컨텍스트에 구성하기 때문에(스프링 컨테이너 소속) 컨테이너의 기능을 자유롭게
	    활용할 수 있으며 그 내부에 선언된 빈을 참조할 수 있다.(필터와 다른점!)
	    반면 핸들러 인터셉터는 특정 요청 URL에만 적용되도록 매핑할 수 있다는 점에서는 필터와 유사하다.
	 -->
	<h3>인터셉터를 이용한 요청 처리 시간 확인하기</h3>
	<button onclick="location.href='stopwatch'">수행 시간 확인하기</button>
</body>
</html>




