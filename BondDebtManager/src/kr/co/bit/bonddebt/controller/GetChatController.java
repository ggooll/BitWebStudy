package kr.co.bit.bonddebt.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.bonddebt.dao.ChatDAO;
import kr.co.bit.bonddebt.vo.Chat;

public class GetChatController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		int groupNo = Integer.parseInt(request.getParameter("groupNo"));

		ChatDAO chatDAO = new ChatDAO();
		ArrayList<Chat> chatVOList = chatDAO.getChatLog(groupNo);
		request.setAttribute("chatVOList", chatVOList);

		return "/jsp/process/selectChatProcess.jsp";
	}

}
