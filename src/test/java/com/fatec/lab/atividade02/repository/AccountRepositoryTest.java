package com.fatec.lab.atividade02.repository;

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
public class AccountRepositoryTest {

	@Autowired
	private AccountRepository repository;
	
	@Test
	public void findByOwnerTest() {
		Account account = repository.findByOwner("Gilberto");
		assertNull(account);
	}
	
	@Test
	public void findByNumberTest() {
		Account account = repository.findByNumber(123l);
		assertNull(account);
	}
}
