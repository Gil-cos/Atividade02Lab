package com.fatec.lab.atividade02.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatec.lab.atividade02.entity.Account;
import com.fatec.lab.atividade02.entity.Bank;
import com.fatec.lab.atividade02.entity.User;
import com.fatec.lab.atividade02.enums.AccountType;
import com.fatec.lab.atividade02.form.AccountForm;
import com.fatec.lab.atividade02.form.AccountUpdateForm;
import com.fatec.lab.atividade02.form.TransactionalForm;
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
	public Account createAccount(final AccountForm form) throws ObjectNotFoundException {
		Bank bank = bankService.findById(form.getBankId());
		User user = userService.findById(form.getOwnerId());
		Account newAccount = new Account(form);
		newAccount.setReference(bank, user);
		return save(newAccount);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN') or hasAnyRole('ROLE_CUSTOMER')")
	public Account update(final Long id, AccountUpdateForm form) throws ObjectNotFoundException {
		return save(findById(id).update(form));
	}

	@PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
	public Account withdraw(final Long id, @Valid final TransactionalForm form) throws ObjectNotFoundException {
		//Account account = findById(id);
		Optional<Account> accountAux = accountRepository.findByNumber(id);
		if (accountAux.isPresent()) {
			Account account = accountAux.get();
			validateWithdraw(account, form.getValue());
			return save(account.withdraw(form.getValue()));
		}
		return null;
		
	}

	@PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
	public Account deposit(final Long id, @Valid final TransactionalForm form) throws ObjectNotFoundException {
		Optional<Account> accountAux = accountRepository.findByNumber(id); //findById(id);
		if (accountAux.isPresent()) {
			Account account = accountAux.get();
			validateDeposit(form.getValue());
			return save(account.deposit(form.getValue()));
		}
		return null;		
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
	public Account getAccountByOwner(final Long number) throws ObjectNotFoundException {
		return accountRepository.findByNumber(number).orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada."));
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public List<Account> getByBankNameAndType(final String bankName, AccountType type) {
		return accountRepository.getByBankNameAndType(bankName, type);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public void deleteAccount(final Long id) throws ObjectNotFoundException {
		delete(findById(id));
	}
	
	public Optional<Account> findByOwner(final User owner) {
		return accountRepository.findByOwner(owner);
	}
	
	@Transactional
	private Account save(final Account account) {
		return accountRepository.save(account);
	}

	@Transactional
	private void delete(final Account account) {
		accountRepository.delete(account);
	}

	private void validateWithdraw(final Account account, final BigDecimal value) {
		if (account.getBalance().compareTo(value) == -1 || value.compareTo(BigDecimal.ZERO) == -1) {
			throw new DataIntegrityViolationException("Valor invalido.");
		}
	}
	
	private void validateDeposit(final BigDecimal value) {
		if (value.compareTo(BigDecimal.ZERO) == -1) {
			throw new DataIntegrityViolationException("Valor invalido.");
		}
	}

	public Optional<Account> getAccountByUserId(Long userId) throws ObjectNotFoundException {
		User user = userService.findById(userId);
		Optional<Account> account = accountRepository.findByOwner(user);
		return account;
	}

}
