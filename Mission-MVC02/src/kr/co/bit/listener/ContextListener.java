package kr.co.bit.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.service.BoardService;
import kr.co.bit.login.dao.LoginDAO;
import kr.co.bit.login.service.LoginService;

@WebListener
public class ContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {

		System.out.println("contextInitialized....");

		// 서블릿 공유영역에 필요한 객체를 등록시켜두고
		// 필요할때마다 불러 씀 (코드상으로 객체를 계속 생성하지 않음)
		ServletContext sc = event.getServletContext();
		BoardDAO boardDAO = new BoardDAO();
		BoardService service = new BoardService(boardDAO);
		sc.setAttribute("boardService", service);

		LoginDAO loginDAO = new LoginDAO();
		LoginService loginService = new LoginService(loginDAO);
		sc.setAttribute("loginService", loginService);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("contextDestroyed...");
	}

}
