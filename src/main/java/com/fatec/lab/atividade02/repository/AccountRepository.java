package com.fatec.lab.atividade02.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fatec.lab.atividade02.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
	
	Account findByOwner(final String owner);
	
	@Query("FROM Account a WHERE a.number = :number")
	Account findByNumber(final String number);

}
