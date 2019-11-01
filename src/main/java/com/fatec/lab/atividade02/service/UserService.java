package com.fatec.lab.atividade02.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.lab.atividade02.entity.User;
import com.fatec.lab.atividade02.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User save(User user) {
		return userRepository.save(user);
	}

	public Optional<User> findById(Long userId) {
		return userRepository.findById(userId);
	}

	public Optional<User> findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
}
