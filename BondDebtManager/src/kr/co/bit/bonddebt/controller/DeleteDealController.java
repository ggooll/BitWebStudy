package kr.co.bit.bonddebt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.bonddebt.dao.DealDAO;
import kr.co.bit.bonddebt.dao.DealHistoryDAO;
import kr.co.bit.bonddebt.dao.MemberDAO;
import kr.co.bit.bonddebt.vo.Deal;

public class DeleteDealController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		int no = Integer.parseInt(request.getParameter("no"));
		int receiverNo = Integer.parseInt(request.getParameter("receiverNo"));
		int senderNo = Integer.parseInt(request.getParameter("senderNo"));
		int money = Integer.parseInt(request.getParameter("money"));
		String comments = request.getParameter("comments");
		String startDate = request.getParameter("startDate");

		Deal deal = new Deal();
		deal.setNo(no);
		deal.setReceiverNo(receiverNo);
		deal.setSenderNo(senderNo);
		deal.setMoney(money);
		deal.setComments(comments);
		deal.setStartDate(startDate);

		MemberDAO memberDAO = new MemberDAO();
		DealDAO dealDAO = new DealDAO();
		DealHistoryDAO dealHistoryDAO = new DealHistoryDAO();

		// 1. receive
		int result = memberDAO.updateMoney(true, receiverNo, money);

		// 2. send
		result = memberDAO.updateMoney(false, senderNo, money);

		// 3. delete from Deal
		result = dealDAO.delete(deal);

		// 4. insert into DealHistory
		result = dealHistoryDAO.insert(deal, false);

		request.setAttribute("successFlag", result);
		return "/jsp/process/successProcess.jsp";
	}
}
