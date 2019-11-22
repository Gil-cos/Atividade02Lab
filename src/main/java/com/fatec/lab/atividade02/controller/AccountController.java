package com.fatec.lab.atividade02.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fatec.lab.atividade02.entity.Account;
import com.fatec.lab.atividade02.enums.AccountType;
import com.fatec.lab.atividade02.form.AccountForm;
import com.fatec.lab.atividade02.service.AccountService;
import com.fatec.lab.atividade02.view.AccountView;
import com.fatec.lab.atividade02.view.UserView;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "api/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping(value = "list")
	@JsonView({ AccountView.AccountList.class })
	public ResponseEntity<List<Account>> getAll() {
		return new ResponseEntity<List<Account>>(accountService.getAll(), HttpStatus.OK);
	}

	@GetMapping(value = "{bankName}/{type}")
	@JsonView({ AccountView.AccountList.class })
	public ResponseEntity<List<Account>> getByBankName(@PathVariable String bankName, @PathVariable AccountType type) {
		return new ResponseEntity<List<Account>>(accountService.getByBankNameAndType(bankName, type), HttpStatus.OK);
	}

	@GetMapping(value = "{id}")
	@JsonView({ AccountView.AccountDetail.class })
	public ResponseEntity<Account> get(@PathVariable Long id) throws ObjectNotFoundException {
		return new ResponseEntity<Account>(accountService.getAccount(id), HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	@JsonView({ UserView.UserDetail.class })
	public ResponseEntity<Account> update(@RequestBody @Valid final AccountForm accountForm, @PathVariable final Long id)
			throws ObjectNotFoundException {
		return ResponseEntity.ok(accountService.update(id, accountForm));
	}
	
	@GetMapping(value = "{owner}")
	@JsonView({ AccountView.AccountDetail.class })
	public ResponseEntity<Account> getByOwner(@PathVariable String owner) throws ObjectNotFoundException {
		Account conta = accountService.getAccountByOwner(owner);
		return new ResponseEntity<Account>(conta, HttpStatus.OK);
	}

	@PostMapping()
	@JsonView({ AccountView.AccountDetail.class })
	public ResponseEntity<Account> save(@RequestBody AccountForm accountForm) throws ObjectNotFoundException {
		Account newAccount = accountService.createAccount(accountForm);
		return ResponseEntity.ok(newAccount);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) throws ObjectNotFoundException {
		accountService.deleteAccount(id);
		return ResponseEntity.noContent().build();
	}

}
