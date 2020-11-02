<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="delete" method=post>
삭제하려는 ${emp.firstName}&nbsp;${emp.lastName}은 <br>
${manCount}<br>
명의 매니저이고<br>
${deptCount}<br>
개의 부서를 책임지고 있습니다.<br>
정말 삭제하시겠습니까?<br>
<input type=hidden name=empId value="${emp.employeeId}">
<!-- 사용자가 입력 안했는데 데이터를 보내주어야 할 때 hidden으로 쓴다 -->
<input type=submit value="삭제">
<input type=reset value="취소" onclick="history.back(-1);">
</form>
</body>
</html>