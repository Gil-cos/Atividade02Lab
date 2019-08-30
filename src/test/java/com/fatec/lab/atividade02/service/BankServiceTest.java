package com.fatec.lab.atividade02.service;

import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.fatec.lab.atividade02.entity.Bank;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class BankServiceTest {

	@Autowired
	private BankService service;
	
	@Test
	public void createBankTest() {
		Bank bank = service.createBank("Itau", "123456", "Avenida Um");
		assertNotNull(bank);
	}
}
