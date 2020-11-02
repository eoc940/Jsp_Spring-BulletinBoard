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

	<c:set var="total" value="0"/>
	<c:forEach var="i" begin="1" end="100" step="1">
		<c:set var="total" value="${total + i}" />
	</c:forEach>
	<h4>1-100누적합 : ${total }</h4>
	
	<hr>
	
	<h4>구구단 7단</h4>
	<c:forEach var="hang" begin="1" end="9"> <!-- step 생략시 자동으로 1로 처리. -->
		7 X ${hang} = ${7*hang}<br>
	</c:forEach>
	
	<hr>
	
	<c:forEach var="dan" begin="2" end="9">
		구구단 ${dan} 단 <br>
		<c:forEach var="hang" begin="1" end="9">
			${dan} X ${hang} =${dan*hang}<br>
		</c:forEach>
		<hr>
	</c:forEach>
</body>
</html>