package kr.co.bit.bonddebt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.bit.bonddebt.dao.DealHistoryDAO;
import kr.co.bit.bonddebt.vo.DealHistory;
import kr.co.bit.bonddebt.vo.Member;

public class DealHistoriesController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "/jsp/process/sessionOutProcess.jsp";
		}

		int memberNo = loginUser.getNo();

		int pageNo = 1;
		String page = request.getParameter("page");
		if (page != null) {
			pageNo = Integer.parseInt(page);
		}

		DealHistoryDAO dealHistoryDAO = new DealHistoryDAO();
		List<DealHistory> dealHistories = dealHistoryDAO.selectAll(memberNo, pageNo);
		request.setAttribute("dealHistories", dealHistories);

		// 회원의 지난거래내역에 대한 전체 게시물 개수, 최대 페이지 갯수
		int maxCount = dealHistoryDAO.selectMaxCount(memberNo);

		// 시작페이지, 끝페이지 정의(그릴 때)
		int startPage = 5 * ((pageNo - 1) / 5) + 1;
		int lastPage = startPage + 4;

		if (lastPage >= (maxCount / 10) + 1) {
			lastPage = (maxCount / 10) + 1;
		}

		// 페이지번호
		request.setAttribute("currentPage", pageNo);
		request.setAttribute("startPage", startPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("maxPage", (maxCount / 10) + 1);

		return "/jsp/historyDeal.jsp";
	}

}
