package com.fatec.lab.atividade02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatec.lab.atividade02.entity.Account;
import com.fatec.lab.atividade02.entity.Bank;
import com.fatec.lab.atividade02.entity.User;
import com.fatec.lab.atividade02.enums.AccountType;
import com.fatec.lab.atividade02.form.AccountForm;
import com.fatec.lab.atividade02.repository.AccountRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private BankService bankService;
	
	@Autowired
	private UserService userService;
	
	public Account findById(final Long id) throws ObjectNotFoundException {
		return accountRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Conta nao encontrado."));
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public Account createAccount(final AccountForm accountForm) throws ObjectNotFoundException {
		Bank bank = bankService.findById(accountForm.getBankId());
		User user = userService.findById(accountForm.getOwnerId());
		Account newAccount = new Account(accountForm);
		newAccount.setReference(bank, user);
		return save(newAccount);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN') or hasAnyRole('ROLE_CUSTOMER')")
	public Account update(final Long id, AccountForm accountForm) throws ObjectNotFoundException {
		return save(findById(id).update(accountForm));
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public List<Account> getAll() {
		return accountRepository.findAll();
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN') or hasAnyRole('ROLE_CUSTOMER')")
	public Account getAccount(final Long id) throws ObjectNotFoundException {
		return accountRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Conta nao encontrada."));
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN') or hasAnyRole('ROLE_CUSTOMER')")
	public Account getAccountByOwner(final String owner) throws ObjectNotFoundException {
		return accountRepository.findByOwnerUserName(owner).orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada."));
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public List<Account> getByBankNameAndType(final String bankName, AccountType type) {
		return accountRepository.getByBankNameAndType(bankName, type);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public void deleteAccount(final Long id) throws ObjectNotFoundException {
		delete(findById(id));
	}
	
	@Transactional
	private Account save(final Account account) {
		return accountRepository.save(account);
	}

	private void delete(final Account account) {
		accountRepository.delete(account);
	}


}
