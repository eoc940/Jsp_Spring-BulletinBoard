<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    	session.invalidate();
    	
    %>
    <script>
    	alert("로그아웃 처리되었습니다.");
    	location.href="/MyWeb";//index.jsp로 돌아감
    </script>