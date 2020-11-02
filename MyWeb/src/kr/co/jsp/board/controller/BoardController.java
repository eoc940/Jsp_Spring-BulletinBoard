package kr.co.jsp.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.board.model.BoardDAO;
import kr.co.jsp.board.model.BoardVO;
import kr.co.jsp.board.service.ContentServiceImpl;
import kr.co.jsp.board.service.DeleteServiceImpl;
import kr.co.jsp.board.service.GetListServiceImpl;
import kr.co.jsp.board.service.IBoardService;
import kr.co.jsp.board.service.ModifyServiceImpl;
import kr.co.jsp.board.service.RegisterServiceImpl;
import kr.co.jsp.board.service.SearchServiceImpl;
import kr.co.jsp.board.service.UpdateServiceImpl;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IBoardService sv;
	RequestDispatcher dp;
    
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doRequest(request, response);
	}
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		uri = uri.substring(conPath.length()+1, uri.lastIndexOf("."));
		System.out.println(uri);
		
		switch(uri) {
			case "write":
				System.out.println("글 쓰기 페이지 이동 요청!");
				response.sendRedirect("board/board_write.jsp");
				break;
				
			case "regist":
				System.out.println("글 등록 요청이 들어옴!");
				sv = new RegisterServiceImpl();
				sv.execute(request, response);
				
				//응답을 내보내고 재요청이 필요할때 쓴다
				response.sendRedirect("/MyWeb/list.board");
				break;
				
			case "list":
				System.out.println("글 목록 요청이 들어옴!");
				sv = new GetListServiceImpl();
				sv.execute(request, response);
				//request객체를 다음 화면까지 운반하기 위한 forward기능을 제공하는 객체.
				// -> requestDispatcher
				//응답이 나가기 전에 다른 페이지에서 응답을 해야할 때 쓴다
				dp =request.getRequestDispatcher("board/board_list.jsp");
				dp.forward(request, response);
				break;
			case "content":
				System.out.println("글 상세보기 요청이 들어옴!");
				sv = new ContentServiceImpl();
				sv.execute(request, response);
				dp = request.getRequestDispatcher("board/board_content.jsp");
				dp.forward(request, response);
				break;
				
			case "modify":
				System.out.println("글 수정 페이지로 이동 요청이 들어옴!");
				sv = new ModifyServiceImpl();
				sv.execute(request, response);
				dp = request.getRequestDispatcher("board/board_modify.jsp");
				dp.forward(request, response);
				break;
			case "update":
				System.out.println("글 수정 요청이 들어옴!");
				sv = new UpdateServiceImpl();
				sv.execute(request, response);
				response.sendRedirect("/MyWeb/content.board?bId=" + request.getParameter("bId"));
				break;
			case "delete":
				System.out.println("글 삭제 요청이 들어옴!");
				sv = new DeleteServiceImpl();
				sv.execute(request, response);
				response.sendRedirect("/MyWeb/list.board");
				break;
				
			case "search":
				System.out.println("글 검색 요청이 들어옴!");
				sv = new SearchServiceImpl();
				sv.execute(request, response);
				
				if(request.getAttribute("boardList") != null) {
					dp = request.getRequestDispatcher("board/board_list.jsp");
					dp.forward(request, response);
				}
				break;
		}
	}
}
