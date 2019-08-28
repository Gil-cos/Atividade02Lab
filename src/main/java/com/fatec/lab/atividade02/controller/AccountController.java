package com.fatec.lab.atividade02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.lab.atividade02.entity.Account;
import com.fatec.lab.atividade02.service.AccountService;

@RestController
@RequestMapping(value="api/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	@GetMapping(value="register")
	public Account register() {
		return accountService.createAccount(54l, "MUMU");
	}
}
