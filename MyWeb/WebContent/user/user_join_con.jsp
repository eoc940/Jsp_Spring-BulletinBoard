<%@page import="kr.co.jsp.user.model.UserVO"%>
<%@page import="kr.co.jsp.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    	/*
    	- 사용자의 입력값을 가지고 오세요.
    	- 아이디의 중복 여부부터 확인하셔야 합니다.
    		만약 중복이 발생했다면 경고창으로 '아이디가 중복되었습니다.' 띄워 주시고
    		뒤로가기 진행해 주세요.
    	- 아이디가 중복되지 않았다면 회원가입 처리해 주시면 됩니다.(insertUser)
    	 '회원가입을 환영합니다' 경고창 하나 띄워 주시고 user_login.jsp로 이동시켜주세요.
    	*/
    	
    	request.setCharacterEncoding("utf-8");
    	
    	String id = request.getParameter("id");
    	String pw = request.getParameter("pw");
    	String pwCheck = request.getParameter("pw_check");
    	String name = request.getParameter("name");
    	String email = request.getParameter("email");
    	String address = request.getParameter("address");
    	
    	UserDAO dao = UserDAO.getInstance();
    	
    	boolean flag = dao.confirmId(id);
    	if(flag){%>
    		<script>
    			alert("아이디가 중복되었습니다");
    			history.back();
    		</script>
    	<%}else{ 
    		UserVO user = new UserVO(
					id,pw,name,email,address);
    		dao.insertUser(user);%>
    		<script>
    			alert("회원가입을 환영합니다");
    			location.href="user_login.jsp";
    		</script>
    	<%} %>
    