package com.fatec.lab.atividade02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatec.lab.atividade02.entity.Account;
import com.fatec.lab.atividade02.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepo;
	
	@Transactional
	public void createAccount(Long number, String owner) {
		Account novaConta = new Account();
		novaConta.setNumber(number);
		novaConta.setOwner(owner);
		accountRepo.save(novaConta);
	}
}
