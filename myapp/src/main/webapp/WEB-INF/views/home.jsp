<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>	
	<meta charset="UTF-8">
	<title>Home</title>
</head>
<body>
<h3>${message}</h3>
<br><br>
<a href="emp/count"><button>1.사원의 수</button></a><br><br>
<form action="emp/count" >
부서로 검색 : <input type=text name=deptId>
<input type=submit value=검색>
</form><br>
<form action="emp/pay">
최저급여  : <input type=text name=minPay><br>
최고급여  : <input type=text name=maxPay>
<input type=submit value=검색>
</form>
<br>
<form action="emp/name">
2. 이름 검색 : <input type=text name=name>&nbsp;
<input type=submit value=검색>
</form>
<a href="emp/insert"><button>3. 사원 입력</button></a><br><br>
<a href="emp/list"><button>4. 사원 목록</button></a><br>
</body>
</html>
