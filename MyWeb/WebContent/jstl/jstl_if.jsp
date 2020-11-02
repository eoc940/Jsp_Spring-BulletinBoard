<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%-- jsp 페이지 내부에서 JSTL 라이브러리를 사용하려면 taglib 선언을 하셔야 합니다. --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:set var="age" value="${param.age}" />

	<p>
		# 나이 : ${age} 세<br>
		
		<c:if test="${age > 19}">
			<b>당신은 성인입니다.</b>
		</c:if>
		
		<c:if test="${age < 20}">
			<b>당신은 미성년자입니다.</b>
		</c:if>
	</p>

</body>
</html>