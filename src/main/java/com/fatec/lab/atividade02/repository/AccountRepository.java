package com.fatec.lab.atividade02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fatec.lab.atividade02.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Account findByOwner(final String owner);
	
	@Query("FROM Account a WHERE a.number = :number")
	Account findByNumber(final String number);

}
