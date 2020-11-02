<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EmpList</title>
</head>
<body>
<h1>사원 정보 목록</h1>
<!-- 절대경로를 써주려면  -->
<a href="insert">사원 정보 입력</a>&nbsp;&nbsp;<a href="/myapp/emp">메인 페이지</a><br>
<table border=1>
	<tr>
		<td>사원번호</td>
		<td>이름</td><td>성</td>
		<td>이메일</td>
		<td>연락처</td>
		<td>입사일</td>
		<td>직무</td>
		<td>급여</td>
		<td>보너스율</td>
		<td>매니저번호</td>
		<td>부서번호</td>
	</tr>
	<c:forEach var="emp" items="${empList}">
	<tr>
		<td>${emp.employeeId}</td>
		<td><a href="${emp.employeeId}">${emp.firstName}</a></td>
		<td>${emp.lastName}</td>
		<td>${emp.email}</td>
		<td>${emp.phoneNumber}</td>
		<td>${emp.hireDate}</td>
		<td>${emp.jobId}</td>
		<td>${emp.salary}</td>
		<td>${emp.commissionPct}</td>
		<td>${emp.managerId}</td>
		<td>${emp.departmentId}</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>