package kr.co.bit.login.service;

import kr.co.bit.login.dao.LoginDAO;

public class LoginService {

	private LoginDAO loginDAO;
	
	public LoginService(LoginDAO dao) {
		this.loginDAO = dao;
	}
}
