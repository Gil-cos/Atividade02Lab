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
import com.fatec.lab.atividade02.form.AccountUpdateForm;
import com.fatec.lab.atividade02.form.TransactionalForm;
import com.fatec.lab.atividade02.service.AccountService;
import com.fatec.lab.atividade02.view.AccountView;

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

	@GetMapping(value = "bank/{bankName}/type/{type}")
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
	@JsonView({ AccountView.AccountDetail.class })
	public ResponseEntity<Account> update(@RequestBody @Valid final AccountUpdateForm accountForm, @PathVariable final Long id)
			throws ObjectNotFoundException {
		return ResponseEntity.ok(accountService.update(id, accountForm));
	}
	
	@GetMapping(value = "number/{number}")
	@JsonView({ AccountView.AccountDetail.class })
	public ResponseEntity<Account> getByOwner(@PathVariable Long number) throws ObjectNotFoundException {
		Account conta = accountService.getAccountByOwner(number);
		return new ResponseEntity<Account>(conta, HttpStatus.OK);
	}

	@PostMapping()
	@JsonView({ AccountView.AccountDetail.class })
	public ResponseEntity<Account> save(@RequestBody AccountForm form) throws ObjectNotFoundException {
		Account newAccount = accountService.createAccount(form);
		return ResponseEntity.ok(newAccount);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) throws ObjectNotFoundException {
		accountService.deleteAccount(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}/withdraw")
	@JsonView({ AccountView.AccountDetail.class })
	public ResponseEntity<Account> withdraw(@RequestBody @Valid final TransactionalForm form , @PathVariable final Long id)
			throws ObjectNotFoundException {
		return ResponseEntity.ok(accountService.withdraw(id, form));
	}
	
	@PutMapping(value = "/{id}/deposit")
	@JsonView({ AccountView.AccountDetail.class })
	public ResponseEntity<Account> deposit(@RequestBody @Valid final TransactionalForm form , @PathVariable final Long id)
			throws ObjectNotFoundException {
		return ResponseEntity.ok(accountService.deposit(id, form));
	}

}
