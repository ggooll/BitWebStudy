package kr.co.bit.bonddebt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.bit.bonddebt.dao.DealDAO;
import kr.co.bit.bonddebt.dao.GroupDAO;
import kr.co.bit.bonddebt.vo.Deal;
import kr.co.bit.bonddebt.vo.Member;

/**
 * 
 * @author imgyucheol
 *
 */
public class SendMoneyGroupController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int result = 0;
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		int money = Integer.parseInt(request.getParameter("money"));
		String comments = request.getParameter("comments");

		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("loginUser");
		int memberNo = member.getNo();

		GroupDAO groupDAO = new GroupDAO();
		List<Integer> list = groupDAO.selectMemberByNo(groupNo, memberNo);

		DealDAO dealDAO = new DealDAO();

		Deal deal = new Deal();
		deal.setReceiverNo(memberNo);
		deal.setComments(comments);
		deal.setMoney(money);

		for (int senderNo : list) {
			deal.setSenderNo(senderNo);
			result = dealDAO.insert(deal);
		}

		request.setAttribute("successFlag", result);
		return "jsp/process/successProcess.jsp";
	}

}
