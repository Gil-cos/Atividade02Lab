package com.fatec.lab.atividade02.repository;

import static org.junit.Assert.assertNull;

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
public class BankRepositoryTest {

	@Autowired
	private BankRepository repository;
	
	@Test
	public void findByNameReturnNullTest() {
		Bank bank = repository.findByName("Itau");
		assertNull(bank);
	}
	
	@Test
	public void findByCnpjReturnNullTest() {
		Bank bank = repository.findByCnpj("123456789");
		assertNull(bank);
	}
}
