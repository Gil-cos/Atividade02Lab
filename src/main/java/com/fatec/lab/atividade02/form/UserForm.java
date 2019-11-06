package com.fatec.lab.atividade02.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.fatec.lab.atividade02.entity.Profile;
import com.fatec.lab.atividade02.entity.User;
import com.fatec.lab.atividade02.service.UserService;

import javassist.tools.rmi.ObjectNotFoundException;

public class UserForm {
	
	@NotNull @NotEmpty
	private String userName;

	@NotNull @NotEmpty
	private String password;

	public User convert(PasswordEncoder encoder) {
		List<Profile> profiles = new ArrayList<Profile>();
		profiles.add(new Profile(2L, "ROLE_COSTUMER"));
		User user = new User(this.userName, encoder.encode(this.password), profiles);
		return user;
	}

	public User update(Long id, UserService userService, PasswordEncoder passwordEncoder) throws ObjectNotFoundException {
		User user = userService.findById(id);
		user.setUserName(this.userName);
		user.setPassword(passwordEncoder.encode(this.password));
		
		return user;
	}

}