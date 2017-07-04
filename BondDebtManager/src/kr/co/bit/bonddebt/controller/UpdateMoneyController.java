package kr.co.bit.bonddebt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.bit.bonddebt.dao.MemberDAO;
import kr.co.bit.bonddebt.vo.Member;

public class UpdateMoneyController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		// 세션의 멤버에 밸런스를 넣음
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "/jsp/process/sessionOutProcess.jsp";
		}

		MemberDAO memberDAO = new MemberDAO();
		int money = Integer.parseInt(request.getParameter("money"));

		int result = memberDAO.updateMoney(true, loginUser.getNo(), money);
		request.setAttribute("successFlag", result);

		return "/jsp/process/successProcess.jsp";
	}

}
