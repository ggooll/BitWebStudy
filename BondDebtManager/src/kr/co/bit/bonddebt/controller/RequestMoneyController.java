package kr.co.bit.bonddebt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.bit.bonddebt.dao.DealDAO;
import kr.co.bit.bonddebt.vo.Deal;
import kr.co.bit.bonddebt.vo.Member;

public class RequestMoneyController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "/jsp/process/sessionOutProcess.jsp";
		}

		int receiverNo = loginUser.getNo();
		int senderNo = Integer.parseInt(request.getParameter("senderNo"));
		String comments = request.getParameter("comments");
		int money = Integer.parseInt(request.getParameter("money"));

		Deal deal = new Deal();
		deal.setSenderNo(senderNo);
		deal.setReceiverNo(receiverNo);
		deal.setComments(comments);
		deal.setMoney(money);

		DealDAO dealDAO = new DealDAO();
		int result = dealDAO.insert(deal);

		request.setAttribute("successFlag", result);

		return "jsp/process/successProcess.jsp";
	}

}
