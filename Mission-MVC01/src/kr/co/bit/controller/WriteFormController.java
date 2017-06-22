package kr.co.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteFormController implements Controller {
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 요청은 servlet이 받고, 응답은 jsp가 하기때문에
		// 공유영역은 최소 request를 사용해야 함
		return "/jsp/board/writeForm.jsp";
	}

}
