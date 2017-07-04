package kr.co.bit.bonddebt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.bit.bonddebt.dao.DealDAO;
import kr.co.bit.bonddebt.vo.Deal;
import kr.co.bit.bonddebt.vo.Member;

public class SelectTopDealController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("loginUser");

		DealDAO dealDAO = new DealDAO();
		List<Deal> dealList = dealDAO.selectTop5(member.getNo());

		System.out.println(dealList);

		request.setAttribute("dealList", dealList);
		return "/jsp/process/selectTopDealProcess.jsp";
	}

}
