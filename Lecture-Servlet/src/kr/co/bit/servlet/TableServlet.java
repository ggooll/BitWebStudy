package kr.co.bit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 기본 5x5
 * http://localhost:8000/Lecture-Servlet/servlet/table/rows=4&cols=3
 * 
 * @author 임규철
 */
// mapping을 어노테이션으로 대체할 수 있음(서블릿 3.0)
@WebServlet("/servlet/tableexam")
public class TableServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int rowCnt = 5, colCnt = 5;
		String rows = request.getParameter("rows");
		String cols = request.getParameter("cols");
		if (rows != null) {
			rowCnt = Integer.parseInt(rows);
		}
		if (cols != null) {
			colCnt = Integer.parseInt(cols);
		}

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		StringBuilder sb = new StringBuilder();
		sb.append("<HTML><HEAD><TITLE> 테이블만들기 </TITLE></HEAD><BODY>");
		sb.append("<TABLE border=\"1\">");
		for (int i = 0; i < rowCnt; i++) {
			sb.append("<TR>");
			for (int j = 0; j < colCnt; j++) {
				sb.append("<TD>" + "cell(" + i + "," + j + ")</TD>");
			}
			sb.append("</TR>");
		}
		sb.append("</TABLE></BODY></HTML>");

		out.println(sb.toString());
		out.flush();
		out.close();
	}
}
