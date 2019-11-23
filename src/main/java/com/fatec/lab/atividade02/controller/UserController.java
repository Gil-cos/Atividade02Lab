package com.fatec.lab.atividade02.controller;

import java.net.URI;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;
import com.fatec.lab.atividade02.entity.User;
import com.fatec.lab.atividade02.form.UserForm;
import com.fatec.lab.atividade02.service.UserService;
import com.fatec.lab.atividade02.view.UserView;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "list/{profile}")
	@JsonView({ UserView.UserList.class })
	public ResponseEntity<List<User>> listAll(@PathVariable final String profile) {
		return new ResponseEntity<List<User>>(userService.findByProfilesName(profile), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	@JsonView({ UserView.UserDetail.class })
	public ResponseEntity<User> getById(@PathVariable final Long id) throws ObjectNotFoundException {
		return new ResponseEntity<User>(userService.findById(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/cpf/{cpf}")
	@JsonView({ UserView.UserDetail.class })
	public ResponseEntity<User> getById(@PathVariable final String cpf) throws ObjectNotFoundException {
		return new ResponseEntity<User>(userService.findByCpf(cpf), HttpStatus.OK);
	}

	@PostMapping()
	@JsonView({ UserView.UserDetail.class })
	public ResponseEntity<User> create(@RequestBody @Valid final UserForm userForm, UriComponentsBuilder uriBuilder) {
		User newUser = userService.newUser(userForm);
		URI uri = uriBuilder.path("/api/user/{id}").buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(uri).body(newUser);
	}

	@PutMapping(value = "/{id}")
	@JsonView({ UserView.UserDetail.class })
	public ResponseEntity<User> update(@RequestBody @Valid final UserForm userForm, @PathVariable final Long id)
			throws ObjectNotFoundException {
		return ResponseEntity.ok(userService.update(id, userForm));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable final Long id) throws ObjectNotFoundException {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

}