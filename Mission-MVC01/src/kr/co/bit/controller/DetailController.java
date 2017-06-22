package kr.co.bit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.dao.BoardFileDAO;
import kr.co.bit.board.vo.BoardFileVO;
import kr.co.bit.board.vo.BoardVO;

public class DetailController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String no = request.getParameter("no");
		int boardNo = Integer.parseInt(no);
		BoardDAO boardDAO = new BoardDAO();
		BoardVO board = boardDAO.selectByNo(boardNo);

		// 첨부파일 조회
		BoardFileDAO boardFileDAO = new BoardFileDAO();
		List<BoardFileVO> boardFileList = boardFileDAO.selectByBoardNo(boardNo);
		request.setAttribute("boardFileList", boardFileList);

		HttpSession session = request.getSession();

		if (!no.equals(session.getAttribute("recentNo"))) {
			session.setAttribute("recentNo", no);
			board.setViewCnt(board.getViewCnt() + 1);
			boardDAO.increaseViewCnt(board);
		}

		request.setAttribute("board", board);
		return "/jsp/board/detail.jsp";
	}
}
