package kr.co.bit.bonddebt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.bit.bonddebt.dao.DealHistoryDAO;
import kr.co.bit.bonddebt.dao.MemberDAO;
import kr.co.bit.bonddebt.vo.Deal;
import kr.co.bit.bonddebt.vo.Member;

public class SendMoneyController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "/jsp/process/sessionOutProcess.jsp";
		}

		int senderNo = loginUser.getNo();

		int receiverNo = Integer.parseInt(request.getParameter("receiverNo"));
		int money = Integer.parseInt(request.getParameter("money"));
		String comments = request.getParameter("comments");

		MemberDAO memberDAO = new MemberDAO();
		int result = memberDAO.updateMoney(false, senderNo, money);
		result = memberDAO.updateMoney(true, receiverNo, money);

		DealHistoryDAO dealhistoryDAO = new DealHistoryDAO();
		Deal deal = new Deal();
		deal.setReceiverNo(receiverNo);
		deal.setSenderNo(senderNo);
		deal.setMoney(money);
		deal.setComments(comments);
		result = dealhistoryDAO.insert(deal, true);

		request.setAttribute("successFlag", result);

		return "/jsp/process/successProcess.jsp";
	}

}
