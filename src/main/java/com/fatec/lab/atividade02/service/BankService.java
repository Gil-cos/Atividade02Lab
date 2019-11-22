package com.fatec.lab.atividade02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatec.lab.atividade02.entity.Bank;
import com.fatec.lab.atividade02.repository.BankRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class BankService {

	@Autowired
	private BankRepository bankRepository;

	@Transactional
	public Bank createBank(String nome, String cnpj, String endereco) {
		Bank novoBanco = new Bank();
		novoBanco.setName(nome);
		novoBanco.setCnpj(cnpj);
		novoBanco.setEndereço(endereco);
		return bankRepository.save(novoBanco);
	}

	@Transactional
	public void deleteBank(Long id) throws ObjectNotFoundException {
		bankRepository.delete(findById(id));
	}

	public Bank getBankByCnpj(String cnpj) throws ObjectNotFoundException {
		return bankRepository.findByCnpj(cnpj)
				.orElseThrow(() -> new ObjectNotFoundException("Data not found"));
	}

	public Bank getBankByName(String name) {
		return bankRepository.findByName(name);
	}

	public List<Bank> getAll() {
		return bankRepository.findAll();
	}

	public Bank findById(Long bankId) throws ObjectNotFoundException {
		return bankRepository.findById(bankId).orElseThrow(() -> new ObjectNotFoundException("Banco nao encontrado."));
	}

}
