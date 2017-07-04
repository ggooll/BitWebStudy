package kr.co.bit.bonddebt.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.bit.bonddebt.dao.GroupDAO;
import kr.co.bit.bonddebt.vo.Group;
import kr.co.bit.bonddebt.vo.Member;

public class InsertGroupController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "/jsp/process/sessionOutProcess.jsp";
		}

		String groupName = request.getParameter("groupName");
		String[] memberList = request.getParameterValues("memberList");
		ArrayList<String> members = new ArrayList<>(Arrays.asList(memberList));
		members.add(String.valueOf(loginUser.getNo()));

		GroupDAO groupDAO = new GroupDAO();
		int groupNo = groupDAO.selectNo();
		int successFlag = groupDAO.insert(groupNo, groupName);
		successFlag = groupDAO.insertMembers(groupNo, members);

		Group group = groupDAO.selectGroupByNo(groupNo);

		request.setAttribute("successFlag", successFlag);
		request.setAttribute("group", group);

		return "/jsp/process/successProcess.jsp";
	}

}
