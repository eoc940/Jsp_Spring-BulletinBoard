<%@page import="kr.co.jsp.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    	request.setCharacterEncoding("utf-8");
    	
    	UserVO user = new UserVO(
    			request.getParameter("id"),
    			request.getParameter("pw"),
    			request.getParameter("name"),
    			request.getParameter("email"),
    			request.getParameter("address")
    			);
    	
    	request.setAttribute("user", user);
    	
    %>
    <jsp:forward page="el_obj03.jsp" />
    