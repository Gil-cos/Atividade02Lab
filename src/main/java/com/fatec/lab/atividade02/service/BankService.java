package com.fatec.lab.atividade02.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatec.lab.atividade02.entity.Account;
import com.fatec.lab.atividade02.entity.Bank;
import com.fatec.lab.atividade02.repository.AccountRepository;
import com.fatec.lab.atividade02.repository.BankRepository;

@Service
public class BankService {

	public void setBankRepository(BankRepository bankRepository) {
		this.bankRepository = bankRepository;
	}

	public void setAccountRepo(AccountRepository accountRepo) {
		this.accountRepo = accountRepo;
	}

	@Autowired
	private BankRepository bankRepository;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Transactional
	public Bank createBank(String nome, String cnpj, String endereco) {
		Bank novoBanco = new Bank();
		novoBanco.setName(nome);
		novoBanco.setCnpj(cnpj);
		novoBanco.setEndereço(endereco);
		return bankRepository.save(novoBanco);
	}
	
	@Transactional 
	public void deleteBank(String cnpj) {
		Bank deletedBank = bankRepository.findByCnpj(cnpj);
		if (deletedBank != null) {
			bankRepository.delete(deletedBank);
		}
	}
	
	public Bank getBankByCnpj(String cnpj) {
		Bank bank = bankRepository.findByCnpj(cnpj);
		return bank;
	}
	
	public Bank getBankByName(String name) {
		Bank bank = bankRepository.findByName(name);
		return bank;
	}
	
	public void setBankAccount(String bankName, String accountOwnerName) {
		Bank bank = bankRepository.findByName(bankName);
		Account conta = accountRepo.findByOwner(accountOwnerName);
		
		Set<Account> contas = bank.getAccounts();
		contas.add(conta);
		
		bank.setAccounts(contas);
		
		bankRepository.save(bank);
	}
	
}
