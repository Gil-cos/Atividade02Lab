package com.fatec.lab.atividade02.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

	private String userName;
	private String password;

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(userName, password);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}