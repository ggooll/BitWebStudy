package kr.co.bit.login.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.bit.framework.ModelAndView;
import kr.co.bit.framework.annotation.Controller;
import kr.co.bit.framework.annotation.RequestMapping;
import kr.co.bit.login.service.LoginService;
import kr.co.bit.login.vo.LoginVO;

@Controller
public class LoginController {

	/**
	 * 로그인 등록 폼
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/login/loginForm.do")
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("로그인 폼 호출");
		ModelAndView mav = new ModelAndView("/jsp/login/login.jsp");

		return mav;
	}

	/**
	 * 로그인 수행
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/login/login.do")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		LoginVO loginVO = new LoginVO();
		loginVO.setId(id);
		loginVO.setPassword(password);

		// 서블릿 공유영역에 등록해둔 서비스 객체 호출
		ServletContext sc = request.getServletContext();
		LoginService loginService = (LoginService) sc.getAttribute("loginService");
		LoginVO loginUser = loginService.login(loginVO);

		// ModelAndView는 model이 request에 등록됨
		ModelAndView mav = new ModelAndView();
		// 결과페이지로 이동(성공 or 실패)
		if (loginUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			mav.setView("redirect:" + request.getContextPath());
		} else {
			// 1.그냥 login.jsp로 forward시키면 안되는 이유 (login.do가 forward시키므로)
			// redirect의 root 개념
			mav.setView("redirect:" + request.getContextPath() + "/login/loginForm.do");
		}
		return mav;
	}

	/**
	 * 로그아웃
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login/logout.do")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		session.invalidate();
		// session.removeAttribute("loginUser");
		
		ModelAndView mav = new ModelAndView();
		mav.setView("redirect:" + request.getContextPath());
		return mav;
	}
}
