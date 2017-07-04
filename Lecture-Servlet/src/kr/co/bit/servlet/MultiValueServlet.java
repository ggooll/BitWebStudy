package kr.co.bit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MultiValueServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");

		// checkbox처럼 한 네임에 다중 밸류 선택이 가능한 경우
		// getParameterValues를 사용하여 String array로 받아올 수 있다.
		String[] hobbys = request.getParameterValues("hobby");

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		StringBuilder sb = new StringBuilder();
		sb.append("<HTML><HEAD><TITLE> 출력결과 </TITLE></HEAD><BODY>");
		sb.append("이름은 : " + name + "<br>");
		sb.append("취미는 " + Arrays.toString(hobbys) + "<br>");
		sb.append("</BODY></HTML>");
		out.println(sb.toString());
		out.flush();
		out.close();
	}
}
