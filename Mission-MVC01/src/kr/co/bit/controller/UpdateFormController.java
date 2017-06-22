package kr.co.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.vo.BoardVO;

public class UpdateFormController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		BoardDAO boardDAO = new BoardDAO();
		BoardVO board = boardDAO.selectByNo(no);
		request.setAttribute("board", board);
		System.out.println("update board : " + board);
		return "/jsp/board/updateForm.jsp";
	}

	
}
