package kr.co.bit.bonddebt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.bit.bonddebt.dao.MemberDAO;
import kr.co.bit.bonddebt.vo.Member;

public class SelectMoneyController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "/jsp/process/sessionOutProcess.jsp";
		}

		MemberDAO memberDAO = new MemberDAO();
		Member selectMember = memberDAO.selectByNo(loginUser.getNo());

		request.setAttribute("availableMoney", selectMember.getBalance());

		return "/jsp/process/selectMoneyProcess.jsp";
	}

}
