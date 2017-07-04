package kr.co.bit.bonddebt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.bit.bonddebt.dao.GroupDAO;
import kr.co.bit.bonddebt.dao.MemberDAO;
import kr.co.bit.bonddebt.vo.Group;
import kr.co.bit.bonddebt.vo.Member;

public class SelectGroupController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		// 1. get groupList
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "/jsp/process/sessionOutProcess.jsp";
		}

		int memberNo = loginUser.getNo();
		GroupDAO groupDAO = new GroupDAO();
		List<Group> groupList = groupDAO.selectGroupListByNo(memberNo);

		request.setAttribute("groupList", groupList);
		request.setAttribute("groupCount", groupList.size());

		// 2. get friendList for modal
		MemberDAO memberDAO = new MemberDAO();
		List<Member> friendList = memberDAO.selectAllFriend(memberNo);
		request.setAttribute("friendList", friendList);

		return "/jsp/myGroup.jsp";
	}
}
