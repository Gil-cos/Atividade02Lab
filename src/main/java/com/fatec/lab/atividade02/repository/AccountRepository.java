package com.fatec.lab.atividade02.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fatec.lab.atividade02.entity.Account;
import com.fatec.lab.atividade02.entity.User;
import com.fatec.lab.atividade02.enums.AccountType;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Optional<Account> findByOwner(final User owner);
	
	@Query("FROM Account a WHERE a.number = :number")
	Optional<Account> findByNumber(final Long number);

	List<Account> getByBankNameAndType(final String bankName, final AccountType type);


}
