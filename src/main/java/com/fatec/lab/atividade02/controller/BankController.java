package com.fatec.lab.atividade02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fatec.lab.atividade02.entity.Bank;
import com.fatec.lab.atividade02.service.BankService;
import com.fatec.lab.atividade02.view.BankView;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "api/bank")
public class BankController {

	@Autowired
	private BankService bankService;

	@GetMapping
	@JsonView({ BankView.BankResumo.class })
	public ResponseEntity<List<Bank>> getAll() {
		return new ResponseEntity<List<Bank>>(bankService.getAll(), HttpStatus.OK);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) throws ObjectNotFoundException {
		bankService.deleteBank(id);
		return ResponseEntity.noContent().build();
	}

}
