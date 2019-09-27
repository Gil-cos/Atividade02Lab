package com.fatec.lab.atividade02.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		Bank bank = bankRepo.findById(bankId).get();
		account.setBank(bank);
		return accountRepo.save(account);
	}

	public List<Account> getAll() {
		return accountRepo.findAll();
	}

	public Optional<Account> getAccount(Long id) {
		return accountRepo.findById(id);
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
