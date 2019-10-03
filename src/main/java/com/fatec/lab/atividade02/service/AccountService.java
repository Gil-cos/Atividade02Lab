package com.fatec.lab.atividade02.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fatec.lab.atividade02.entity.Account;
import com.fatec.lab.atividade02.entity.Bank;
import com.fatec.lab.atividade02.enums.AccountType;
import com.fatec.lab.atividade02.repository.AccountRepository;
import com.fatec.lab.atividade02.repository.BankRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private BankRepository bankRepo;

	public void setAccountRepo(AccountRepository accountRepo) {
		this.accountRepo = accountRepo;
	}

	@Transactional
	public Account createAccount(final Account account, Long bankId) {

		try {
			Bank bank = bankRepo.findById(bankId).get();
			account.setBank(bank);
			Account conta = accountRepo.save(account);
			return conta;
		}
		catch(DataIntegrityViolationException ex) {
			throw new DataIntegrityViolationException(ex.getMessage());
		}
		catch(NoSuchElementException ex) {
			throw new DataIntegrityViolationException("Banco nao encontrado");
		}
		
		
	}

	public List<Account> getAll() {
		try {
			return accountRepo.findAll();
		}
		catch (MethodArgumentTypeMismatchException ex) {
			throw new MethodArgumentTypeMismatchException(ex.getMessage(), null, null, null, ex);
		}
	}

	public Account getAccount(Long id) throws ObjectNotFoundException {
		return accountRepo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada"));
	}

	public List<Account> getByBankNameAndType(String bankName, AccountType type) {
		return accountRepo.getByBankNameAndType(bankName, type);
	}

	@Transactional
	public void deleteAccount(Long id) throws ObjectNotFoundException {
		Account deletedAccount = accountRepo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada"));
		accountRepo.delete(deletedAccount);
	}

}
