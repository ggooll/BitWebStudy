package kr.co.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.member.dao.MemberDAO;
import kr.co.bit.member.vo.MemberVO;

public class LoginProcessController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		MemberDAO memberDAO = new MemberDAO();
		MemberVO member = new MemberVO(id, password);
		MemberVO loginUser = memberDAO.login(member);

		String msg = null;
		String url = null;

		if (loginUser == null) {
			msg = "아이디 또는 비밀번호가 잘못되었습니다.";
			url = request.getContextPath() + "/login.do";
		} else {
			// 세션 등록
			request.getSession().setAttribute("loginUser", loginUser);

			if (loginUser.getType().equals("S")) {
				msg = "관리자님 환영합니다.";
			} else {
				msg = loginUser.getId() + "님 환영합니다.";
			}

			url = request.getContextPath();
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);

		// response.sendRedirect(url);
		return "/jsp/login/loginProcess.jsp";
	}

}
