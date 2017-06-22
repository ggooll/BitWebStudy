<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- service내 로컬변수로 사용 -->
	<% int sum = 0; %>
	<%=sum%>
	<br>

	<!-- 서블릿 클래스 멤버변수로 선언 %!  -->
	<%!private int sum2 = 0;%>
	<%=sum2%>
	
	<br>
	<%! 
	public int add(int val1, int val2){
		int sum=0;
		for(int i= val1; i<=val2; i++){
			sum += i;
		}
		return sum;
	}
	%>
	
	1부터 10까지의 합은 <%= this.add(1, 10) %><br>

</body>
</html>