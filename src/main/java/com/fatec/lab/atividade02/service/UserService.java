package com.fatec.lab.atividade02.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fatec.lab.atividade02.entity.User;
import com.fatec.lab.atividade02.form.UserForm;
import com.fatec.lab.atividade02.repository.UserRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public User newUser(UserForm userForm) {
		User newUser = userForm.convert(passwordEncoder);
		return save(newUser);
	}

	public User findById(Long userId) throws ObjectNotFoundException {
		return userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("Usuario nao encontrado."));
	}

	public Optional<User> findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	public List<User> findByProfilesName(String profile) {
		return userRepository.findByProfilesName(profile);
	}

	private User save(User user) {
		return userRepository.save(user);
	}
}
