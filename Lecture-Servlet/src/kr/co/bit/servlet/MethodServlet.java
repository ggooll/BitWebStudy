package kr.co.bit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// web.xml에 이 서블릿에 대한 가상 주소를 설정해주었음
// http://localhost:8000/Lecture-Servlet/method?id=(value)&name=(value)
public class MethodServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// 기존 service는 servletRequest / servletResponse를 써도 상관없었음
	// doGet, doPost는 http 프로토콜에 맞춰짐
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 사용자에게 요청받은 값 (Request)
		System.out.println("doget 호출? : " + request.getMethod());
		System.out.println("id : " + request.getParameter("id"));
		System.out.println("name : " + request.getParameter("name"));
		System.out.println("URL : " + request.getRequestURL().toString());
		System.out.println("URI : " + request.getRequestURI());
		// 입력받은 값에 대해 다시 view로 보여주고 싶다. (Response)

		// contentType을 지정해줘야 함
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>메소드호출방식</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("-------------------------------------------------------<br>");
		out.println("파라미터(ID) : " + request.getParameter("id") + "<BR>");
		out.println("요청방식(METHOD) : " + request.getMethod() + "<BR>");
		out.println("URL : " + request.getRequestURL().toString() + "<BR>");
		out.println("URI : " + request.getRequestURI() + "<BR>");
		out.println("-------------------------------------------------------<br>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// post 한글깨짐 방지
		request.setCharacterEncoding("utf-8");

		System.out.println("dopost 호출? : " + request.getMethod());
		System.out.println("id : " + request.getParameter("id"));
		System.out.println("name : " + request.getParameter("name"));
		System.out.println("req URL : " + request.getRequestURL().toString());
		System.out.println("URI : " + request.getRequestURI());

		// contentType을 지정해줘야 함
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>메소드호출방식</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("-------------------------------------------------------<br>");
		out.println("파라미터(ID) : " + request.getParameter("id") + "<BR>");
		out.println("요청방식(METHOD) : " + request.getMethod() + "<BR>");
		out.println("URL : " + request.getRequestURL().toString() + "<BR>");
		out.println("URI : " + request.getRequestURI() + "<BR>");
		out.println("-------------------------------------------------------<br>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
}
