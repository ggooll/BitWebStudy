package kr.co.bit.bonddebt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.bonddebt.dao.MemberDAO;
import kr.co.bit.bonddebt.vo.Member;

public class ApiLoginController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String emailId = request.getParameter("id");
		String name = request.getParameter("name");

		MemberDAO memberDAO = new MemberDAO();
		Member member = memberDAO.selectById(emailId);

		// 조회된 멤버가 없을경우 생성하여 넣어줌
		if (member == null) {
			member = new Member();
			member.setId(emailId);
			member.setName(name);
			member.setTel("");
			member.setEmail(emailId);
			member.setBalance(0);
			member.setPassword("");
			memberDAO.insert(member);
		}

		request.getSession().setAttribute("loginUser", member);
		request.setAttribute("successFlag", 1);

		return "redirect: " + request.getContextPath() + "/jsp/successProcess.jsp";
	}

}
