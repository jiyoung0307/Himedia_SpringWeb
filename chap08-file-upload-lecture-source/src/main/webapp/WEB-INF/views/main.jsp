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
		1. commons 파일업로드 관련 라이브러리 pom.xml에 추가하기(dependency 2개 추가)
		2. servlet-context.xml 혹은 root-context.xml에 파일 업로드 관련 빈 등록(최대 파일 업로드 용량)
	 -->
	<h1 align="center">파일 업로드 하기</h1>
	
	<h3>single file 업로드하기</h3>
	<form action="single-file" method="post" encType="multipart/form-data">
		파일 : <input type="file" name="singleFile"><br>
		파일 설명 : <input type="text" name="singleFileDescription"><br>
		<input type="submit">
	</form>
	
	<h3>multi file 업로드하기</h3>
	<form action="multi-file" method="post" encType="multipart/form-data">
		파일 : <input type="file" name="multiFile" multiple><br>
		파일 설명 : <input type="text" name="multiFileDescription"><br>
		<input type="submit">
	</form>
</body>
</html>




