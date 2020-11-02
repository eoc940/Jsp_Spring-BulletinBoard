package com.jsp.servlet.basic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public FrontController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doRequest(request, response);
	}
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("요청 URI : " + request.getRequestURI());
		String uri = request.getRequestURI();
		
		String conPath = request.getContextPath();
		System.out.println(conPath); // -> /MyWeb
		
		uri = uri.substring(conPath.length()+1, uri.lastIndexOf("."));
		System.out.println("정제된 uri : " + uri);
		
		if(uri.equals("write")) {
			System.out.println("글쓰기 요청이 들어왔구나");
		}else if(uri.equals("list")) {
			System.out.println("글 목록 요청이 들어왔구나");
		}
	}

}
