package kr.co.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.member.dao.MemberDAO;

public class CheckDuplicateIdController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestId = request.getParameter("id");
		MemberDAO memberDAO = new MemberDAO();
		int exist = memberDAO.isExistId(requestId);
		request.setAttribute("exist", exist);
		return "/jsp/member/checkDupId.jsp";
	}

}
