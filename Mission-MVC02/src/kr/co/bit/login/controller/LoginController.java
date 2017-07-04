package kr.co.bit.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.framework.ModelAndView;
import kr.co.bit.framework.annotation.Controller;
import kr.co.bit.framework.annotation.RequestMapping;

@Controller
public class LoginController {

	/**
	 * �α��� ����� ����
	 */
	@RequestMapping("/login/loginForm.do")
	public void loginForm(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		System.out.println("로그인 서비스");
	}
	
	/**
	 * �α��� ���� ����
	 */
	@RequestMapping("/login/login.do")
	public void login(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
	}
	
	/**
	 * �α׾ƿ� ����
	 */
	@RequestMapping("/login/logout.do")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		// �α׾ƿ� �� ������������ �̵�(sendRedirect)
		mav.setView("redirect:" + request.getContextPath());
		
		return mav;
	}
}










