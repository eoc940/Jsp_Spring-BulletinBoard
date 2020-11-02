<%@page import="kr.co.jsp.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<% UserVO user = (UserVO)request.getAttribute("user"); %>
	<p>
		#이름: <%=user.getName() %><br>
		#아이디: <%=user.getId() %><br>
		#이메일: <%=user.getEmail() %><br>
		#주소: <%=user.getAddress() %><br>
		
	</p>
	
	<hr>
	
	<p>
		#이름: ${requestScope.user.name }<br>
		#아이디: ${user.id }<br>
		#이메일: ${user.email }<br>
		#주소: ${user.address }<br>
	</p>

</body>
</html>