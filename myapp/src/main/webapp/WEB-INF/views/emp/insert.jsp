<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Emp${message}</title>
</head>
<body>
<h1>사원 정보 ${message eq 'insert' ? "입력" : "수정" }</h1>
<c:choose>
<c:when test="${message eq insert}">

</c:when>
<c:otherwise>
<!-- <form action="${message eq 'update' ? './' : message }" method=post> -->
<form:form action="/myapp/emp/${message}" method="post" modelAttribute="emp">	
<table border=1>
	<tr>
		<td>사원번호</td><td><form:input path="employeeId" />
		<form:errors path="employeeId" />
		</td>
	</tr>
	<tr>
		<td>이름</td><td><form:input path="firstName" />
		<form:errors path="firstName" />
		</td>
	</tr>
	<tr>
		<td>성</td><td><form:input path="lastName" />
		<form:errors path="lastName" />
		</td>
	</tr>
	<tr>
		<td>이메일</td><td><form:input path="email" />
		<form:errors path="email" />
		</td>
	</tr>
	<tr>
		<td>연락처</td><td><form:input path="phoneNumber" />
		<form:errors path="phoneNumber" />
		</td>
	</tr>
	<tr>
		<td>입사일</td><td><form:input path="hireDate" type="date" required="required" />
		<form:errors path="hireDate" />
		</td>
	</tr>
	<tr>
		<td>직무</td>
		<td>
			<select name=jobId>
				<c:forEach var="job" items="${jobList}">
					<option value="${job.jobId}" ${emp.jobId eq job.jobId ? "selected" : ""}>${job.jobTitle}
					</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>급여</td><td><form:input path="salary" />
		<form:errors path="salary" />
		</td>
	</tr>
	<tr>
		<td>보너스율</td>
		<td>
			<form:input type="number" path="commissionPct" step="0.05" />
			<form:errors path="commissionPct" />
		</td>
	</tr>
	<tr>
		<td>매니저</td>
		<td>
			<form:select path="managerId">
				<c:forEach var="man" items="${manList}">
					<option value="${man.managerId}">${man.managerName}</option>
				</c:forEach>
			</form:select>
		</td>
	</tr>
	<tr>
		<td>부서</td>
		<td>
			<form:select path="departmentId">
				<c:forEach var="dept" items="${deptList}">
					<option value="${dept.departmentId}" >${dept.departmentName}</option>
				</c:forEach>
			</form:select>
		</td>
	</tr>
</table>
<tr>
	<td><input type=submit value="저장"><input type=reset value="취소"></td>
</tr>
</form:form>
</c:otherwise>
</c:choose>
<a href="/myapp/emp">메인 페이지</a>
<a href="/myapp/emp/list">사원 목록</a><br>
</body>
</html>
<!-- 
<form action="/myapp/emp/${message}" method=post modelAttribute="emp">
<table border=1>
	<tr>
		<td>사원번호</td><td><input type=text name=employeeId value="${emp.employeeId}" ${empty emp ? "" : "readonly" }>
		</td>
	</tr>
	<tr>
		<td>이름, 성</td><td><input type=text name=firstName value="${emp.firstName}"><input type=text name=lastName value="${emp.lastName}">
		</td>
	</tr>
	<tr>
		<td>이메일</td><td><input type=text name=email value="${emp.email}" ${empty emp ? "" : "readonly" }>
		</td>
	</tr>
	<tr>
		<td>연락처</td><td><input type=text name=phoneNumber value="${emp.phoneNumber}">
		</td>
	</tr>
	<tr>
		<td>입사일</td><td><input type=date name=hireDate value="${emp.hireDate}">
		</td>
	</tr>
	<tr>
		<td>직무</td>
		<td>
			<select name=jobId>
				<c:forEach var="job" items="${jobList}">
					<option value="${job.jobId}" ${emp.jobId eq job.jobId ? "selected" : ""}>${job.jobTitle}
					</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>급여</td><td><input type=text name=salary value="${emp.salary}">
		</td>
	</tr>
	<tr>
		<td>보너스율</td>
		<td>
			<input type=number name=commissionPct min=0 max=0.95 step=0.05 value="${emp.commissionPct}">
		</td>
	</tr>
	<tr>
		<td>매니저</td>
		<td>
			<select name=managerId>
				<c:forEach var="man" items="${manList}">
					<option value="${man.managerId}" ${emp.managerId eq man.managerId ? "selected" : "" }>${man.managerName}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>부서</td>
		<td>
			<select name=departmentId>
				<c:forEach var="dept" items="${deptList}">
					<option value="${dept.departmentId}" ${emp.departmentId eq dept.departmentId ? "selected" : "" }>${dept.departmentName}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
</table>
 -->