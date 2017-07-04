package kr.co.bit.bonddebt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.bonddebt.dao.MemberDAO;
import kr.co.bit.bonddebt.vo.Member;

public class SignUpController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");

		Member member = new Member();
		member.setId(id);
		member.setPassword(password);
		member.setName(name);
		member.setEmail(email);
		member.setTel(tel);

		String message = "";
		MemberDAO memberDAO = new MemberDAO();
		int result = memberDAO.insert(member);
		if (result < 1) {
			message = "ȸ�����Կ� �����߽��ϴ�. �����ڿ��� �����ϼ���.";
		} else {
			message = member.getId() + "�� ȸ������ �Ǽ̽��ϴ�. �α��� �� �̿����ּ���.";
		}

		request.setAttribute("message", message);
		return "/jsp/process/signUpProcess.jsp";
	}
}
