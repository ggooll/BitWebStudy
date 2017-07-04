package kr.co.bit.bonddebt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.bonddebt.dao.MemberDAO;
import kr.co.bit.bonddebt.vo.Member;

public class SelectFriendController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String friendId = request.getParameter("friendId");

		MemberDAO memberDAO = new MemberDAO();
		Member friend = memberDAO.selectById(friendId);
		request.setAttribute("friend", friend);

		return "/jsp/process/selectFriendProcess.jsp";
	}
}
