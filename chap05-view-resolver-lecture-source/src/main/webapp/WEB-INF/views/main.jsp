<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">뷰 리졸버를 이용한 뷰 연결하기</h1>
	<!-- 
	    핸들러 메소드가 요청을 처리하고 논리 뷰 이름을 반환하면 DispatcherServlet은 화면에서 데이터를 표시하도록
	    뷰 템플릿에 제어권을 넘긴다.
	    스프링 MVC에서는 다양한 전략에 맞게 뷰를 해석할 수 있는 ViewResolver 구현체 몇 가지가 있다.
	    그 중 MVC 기본 설정에는 템플릿 명과 위치에 따른 뷰를 해석하는 InternalResourceViewResolver를 기본으로
	    사용하고 있다.
	    prefix/suffix를 이용해 뷰 이름을 특정 애플리케이션 디렉토리에 대응 시킨다.
	    InternalResourceViewResolver는 사용이 간단해서 좋기는 하지만 RequestDispatcher가 forward할 수 있는
	    내부 리소스(jsp 또는 서블릿)만 해석이 가능하기 때문에, 다른 뷰 템플릿을 사용하는 경우에는 다른 viewResolver를
	    사용해야 한다.
	 -->
	
	<h3>문자열로 뷰 이름 반환하기</h3>
	<button onclick="location.href='string'">문자열로 뷰 이름 반환</button>
	
	<h3>문자열로 redirect하기</h3>
	<button onclick="location.href='string-redirect'">문자열로 뷰 이름 반환하며 리다이렉트</button>
	<script>
		const message = decodeURIComponent('${param.message}').replaceAll("+", " ");
		console.log(message);
		if(message){
			alert(message);
		}
	</script>
	
	<h3>문자열로 뷰 이름 반환하면서 flashAttribute 추가하기</h3>
	<button onclick="location.href='string-redirect-attr'">
		문자열로 뷰 이름 반환하여 리다이렉트 & flashAttr 사용하기
	</button>
	<script>
	    const flashMessage = '${requestScope.flashMessage}';
	    console.log(flashMessage)
	    if(flashMessage){
	    	alert(flashMessage);
	    }
	</script>
	
	<h3>ModelAndView로 뷰 이름 지정해서 반환하기</h3>
	<button onclick="location.href='modelandview'">ModelAndView로 뷰 이름 지정해서 반환받기</button>
	
	<h3>ModelAndView로 redirect하기</h3>
	<button onclick="location.href='modelandview-redirect'">ModelAndView로 뷰 이름 반환하여 리다이렉트</button>
	<script>
	    const message2 = decodeURIComponent("${param.message2}").replaceAll("+", " ");
	    console.log(message2);
	    if(message2){
	    	alert(message2);
	    }
	</script>
	
	<h3>ModelAndView로 뷰 이름 반환하면서 flashAttribute 추가하기</h3>
	<button onclick="location.href='modelandview-redirect-attr'">
	ModelAndView로 뷰 이름 반환하여 리다이렉트 & flashAttr 사용하기
	</button>
	<script>
	    const flashMessage2 = '${requestScope.flashMessage2}';
	    console.log(flashMessage2)
	    if(flashMessage2){
	    	alert(flashMessage2);
	    }
	</script>
</body>
</html>






