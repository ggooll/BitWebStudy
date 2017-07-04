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
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("contextDestroyed...");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {

		System.out.println("contextInitialized....");
		
		ServletContext sc = event.getServletContext();
		
		BoardDAO dao = new BoardDAO();
//		sc.setAttribute("boardDao", dao);
		
		BoardService service = new BoardService(dao);
		sc.setAttribute("boardService", service);
		
		LoginDAO loginDAO = new LoginDAO();
		LoginService loginService = new LoginService(loginDAO);
		sc.setAttribute("loginService", loginService);
	}

	
}









