package kr.co.jsp.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IBoardService {

	//추상메서드 선언. (모든 객체가 동일한 메서드 이름을 가지게 할 목적으로.)
	void execute(HttpServletRequest request, HttpServletResponse response);
}
