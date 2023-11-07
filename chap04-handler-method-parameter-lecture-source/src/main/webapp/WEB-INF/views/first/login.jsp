<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">Session 이용하기</h1>
	
	<h3>HttpSession을 매개변수로 선언하기</h3>
	<form action="login1" method="post">
		ID : <input type="text" name="id"><br>
		PWD : <input type="password" name="pwd"><br>
		<input type="submit" value="로그인">
	</form>
	
	<h3>@SessionAttributes 활용하기</h3>
	<form action="login2" method="post">
		ID : <input type="text" name="id"><br>
		PWD : <input type="password" name="pwd"><br>
		<input type="submit" value="로그인">
	</form>
</body>
</html>