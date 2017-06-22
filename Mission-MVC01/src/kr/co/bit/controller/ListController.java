package kr.co.bit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.vo.BoardVO;

public class ListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BoardDAO boardDAO = new BoardDAO();
		int pageNum = 1;
		String pageNo = request.getParameter("pageNo");
		if (pageNo != null) {
			pageNum = Integer.parseInt(pageNo);
		}

		List<BoardVO> boardList = boardDAO.selectByPageNum(pageNum);
		request.setAttribute("boardList", boardList);

		// 전체 게시물 개수, 최대 페이지 갯수
		int maxCount = boardDAO.selectMaxBoardCount();

		// 시작페이지, 끝페이지 정의(그릴 때)
		int startPage = 5 * ((pageNum - 1) / 5) + 1;
		int lastPage = startPage + 4;

		if (lastPage >= (maxCount / 10) + 1) {
			lastPage = (maxCount / 10) + 1;
		}

		// 페이지번호
		request.setAttribute("currentPage", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("maxPage", (maxCount / 10) + 1);

		HttpSession session = request.getSession();

		if (session.getAttribute("recentNo") != null) {
			session.removeAttribute("recentNo");
		}

		return "/jsp/board/list.jsp";
	}

}
