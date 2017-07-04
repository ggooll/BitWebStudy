package kr.co.bit.bonddebt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.bit.bonddebt.dao.MemberDAO;
import kr.co.bit.bonddebt.vo.Member;

public class FriendListController implements Controller {

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

		MemberDAO memberDAO = new MemberDAO();
		List<Member> friendList = memberDAO.selectFriends(memberNo, pageNo);
		request.setAttribute("friendList", friendList);

		// Paging
		int maxCount = memberDAO.selectFriendMaxCount(memberNo);
		int startPage = 5 * ((pageNo - 1) / 5) + 1;
		int lastPage = startPage + 4;

		if (lastPage >= (maxCount / 10) + 1) {
			lastPage = (maxCount / 10) + 1;
		}

		request.setAttribute("currentPage", pageNo);
		request.setAttribute("startPage", startPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("maxPage", (maxCount / 10) + 1);

		return "/jsp/friendList.jsp";
	}

}
