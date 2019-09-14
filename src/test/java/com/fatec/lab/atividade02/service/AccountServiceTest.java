package com.fatec.lab.atividade02.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.fatec.lab.atividade02.entity.Account;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class AccountServiceTest {
	
	@Autowired
	private AccountService service;
	
	/* TODO
	 * @Test public void createAccountTest() { Account account =
	 * service.createAccount(10l, "Gilberto"); assertNotNull(account); }
	 */	
	@Test
	public void creteAccountNullTest() {
		Account account = service.createAccount(null, null);
		assertNull(account.getNumber());
		assertNull(account.getOwner());
	}
}
