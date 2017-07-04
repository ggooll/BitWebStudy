package kr.co.bit.login.service;

import kr.co.bit.login.dao.LoginDAO;
import kr.co.bit.login.vo.LoginVO;

public class LoginService {

	private LoginDAO loginDAO;

	public LoginService(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	public LoginVO login(LoginVO user) {
		LoginVO loginUser = loginDAO.login(user);
		return loginUser;
	}

}
