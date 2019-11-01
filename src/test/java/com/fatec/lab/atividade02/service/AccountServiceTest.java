package com.fatec.lab.atividade02.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.fatec.lab.atividade02.entity.Account;
import com.fatec.lab.atividade02.entity.Bank;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class AccountServiceTest {

	@Autowired
	private AccountService service;

	@Test
	public void createAccountTest() {
		Account account = new Account();

		Bank bank = new Bank();
		bank.setId(1L);
		bank.setName("Inter");

		account.setId(1L);
		account.setBalance(BigDecimal.TEN);
		account.setNumber(123L);
		account.setPassword("Gsw190");
		account.setBank(bank);
		service.createAccount(account, 1L);
		assertNotNull(account);

	}

	@Test
	public void creteAccountNullTest() {
		Account account = service.createAccount(null, null);
		assertNull(account.getNumber());
		assertNull(account.getOwner());
	}
}
