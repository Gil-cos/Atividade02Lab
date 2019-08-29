package com.fatec.lab.atividade02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fatec.lab.atividade02.entity.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
	
	Bank findByName(final String name);
	
	@Query("FROM Bank b WHERE b.cnpj = :cnpj")
	Bank findByCnpj(final String cnpj);
	
}
