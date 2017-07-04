package kr.co.bit.bonddebt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.bonddebt.dao.MemberDAO;
import kr.co.bit.bonddebt.vo.Member;

public class LoginController implements Controller {

	private static final int SUCCESS_FLAG = 1;

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		// post��
		String id = request.getParameter("username");
		String password = request.getParameter("password");
		int flag = 0;

		MemberDAO memberDAO = new MemberDAO();
		Member loginUser = memberDAO.login(id, password);

		if (loginUser != null) {
			request.getSession().setAttribute("loginUser", loginUser);
			flag = SUCCESS_FLAG;
		}
		request.setAttribute("successFlag", flag);

		return "jsp/process/successProcess.jsp";
	}
}

// return "redirect:" + request.getContextPath() + "/jsp/loginProcess.jsp";
