package com.fatec.lab.atividade02.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
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

	@GetMapping(value = "list")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@JsonView({ UserView.UserList.class })
	public ResponseEntity<List<User>> listUsers() {
		return new ResponseEntity<List<User>>(userService.findByProfilesName("ROLE_COSTUMER"), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@JsonView({ UserView.UserDetail.class })
	public ResponseEntity<User> getUser(@PathVariable Long id) throws ObjectNotFoundException {
		userService.findById(id);
		return new ResponseEntity<User>(userService.findById(id), HttpStatus.OK);
	}

	@PostMapping()
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@JsonView({ UserView.UserDetail.class })
	public ResponseEntity<User> signup(@RequestBody @Valid UserForm userForm, UriComponentsBuilder uriBuilder) {
		User newUser = userService.newUser(userForm);
		URI uri = uriBuilder.path("/api/user/{id}").buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(uri).body(newUser);
	}
	
	@Transactional
	@PutMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@JsonView({ UserView.UserDetail.class })
	public ResponseEntity<User> updateUser(@RequestBody @Valid UserForm userForm, @PathVariable Long id) {
		return ResponseEntity.ok(userService.update(id, userForm));
	}
//	
//	@DeleteMapping(value = "/{id}")
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
//	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
//		
//		Optional<User> user = userService.findById(id);
//		if (user.isPresent()) {
//			userService.deleteById(id);
//			return ResponseEntity.ok().build();
//		}
//		
//		return ResponseEntity.notFound().build();
//	}

}