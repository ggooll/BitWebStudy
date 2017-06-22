package kr.co.bit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PersonInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private boolean isCheckedMailing(String[] mailing, String key) {
		for (int i = 0; i < mailing.length; i++) {
			if (mailing[i].equals(key)) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String[] mailing = req.getParameterValues("mailing");
		String job = req.getParameter("job");
		
		// response
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		StringBuilder sb = new StringBuilder();
		sb.append("<HTML><HEAD><TITLE>개인정보출력</TITLE></HEAD><BODY>");
		sb.append("<H2>개인 정보 출력 <H2>");
		sb.append("이름 : " + name + "<br>");
		sb.append("아이디 : " + id + "<br>");
		sb.append("암호 : " + password + "<br>");
		sb.append("성별 : " + (gender.equals("male") ? "남자" : "여자") + "<br>");
		sb.append("공지메일 : " + (isCheckedMailing(mailing, "notice") ? "받음" : "받지않음") + "<br>");
		sb.append("광고메일 : " + (isCheckedMailing(mailing, "marketing") ? "받음" : "받지않음") + "<br>");
		sb.append("배송확인메일 : " + (isCheckedMailing(mailing, "shipping") ? "받음" : "받지않음") + "<br>");
		sb.append("직업 : " + job + "<br>");
		out.println(sb.toString());
		out.flush();
		out.close();
	}

}
