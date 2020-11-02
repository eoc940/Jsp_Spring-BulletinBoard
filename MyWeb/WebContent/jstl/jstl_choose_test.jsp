<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--
		jstl choose태그와 el을 활용하여
		100점이 넘어가면 "점수를 잘못 입력하셨습니다."
		90점이 넘어가면 '당신의 학점은 a입니다.'를 출력.
		80점대는 b, 70점대는 c, 60점대는 d, 나머지는 f를 출력하세요.
	 --%>
	 
	 <p>
	 	<c:set var="point" value="${param.point}" />
	 	
	 	<c:choose>
	 		<c:when test="${point >100}">
	 			<b>점수를 잘못 입력하셨습니다</b>
	 		</c:when>
	 		<c:when test="${point >=90}">
	 			<b>당신의 학점은 a입니다</b>
	 		</c:when>
	 		<c:when test="${point >=80}">
	 			<b>당신의 학점은 b입니다</b>
	 		</c:when>
	 		<c:when test="${point >=70}">
	 			<b>당신의 학점은 c입니다</b>
	 		</c:when>
	 		<c:when test="${point >=60}">
	 			<b>당신의 학점은 d입니다</b>
	 		</c:when>
	 		<c:otherwise>
	 			<b>당신의 학점은 f입니다</b>
	 		</c:otherwise>
	 	</c:choose>
	 </p>

</body>
</html>