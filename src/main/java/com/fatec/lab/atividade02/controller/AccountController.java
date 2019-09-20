package com.fatec.lab.atividade02.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fatec.lab.atividade02.entity.Account;
import com.fatec.lab.atividade02.enums.AccountType;
import com.fatec.lab.atividade02.service.AccountService;
import com.fatec.lab.atividade02.view.AccountView;

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
	
	@GetMapping
	@JsonView({ AccountView.AccountList.class })
	public ResponseEntity<List<Account>> getByBankName(@RequestParam(required = false) String bankName, @RequestParam(required = false) AccountType type) {
		return new ResponseEntity<List<Account>>(accountService.getByBankNameAndType(bankName, type), HttpStatus.OK);
	}

	@GetMapping(value = "{id}")
	@JsonView({ AccountView.AccountDetail.class })
	public ResponseEntity<Account> get(@PathVariable Long id) {
		Optional<Account> optional = accountService.getAccount(id);
		if (optional.isPresent()) {
			return new ResponseEntity<Account>(optional.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "{bankId}")
	@JsonView({ AccountView.AccountDetail.class })
	public ResponseEntity<Account> save(@RequestBody Account account, @PathVariable Long bankId) {
		Account newAccount = accountService.createAccount(account, bankId);
		return ResponseEntity.ok(newAccount);
	}
}
