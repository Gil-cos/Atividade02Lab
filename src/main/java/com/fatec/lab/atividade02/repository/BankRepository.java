package com.fatec.lab.atividade02.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fatec.lab.atividade02.entity.Bank;

public interface BankRepository extends CrudRepository<Bank, Long> {
	
	Bank findByName(final String name);
	
	@Query("FROM Bank b WHERE b.cnpj = :cnpj")
	Bank findByCnpj(final String cnpj);
}
