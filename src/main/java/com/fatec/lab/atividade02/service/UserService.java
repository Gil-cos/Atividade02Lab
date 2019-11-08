package com.fatec.lab.atividade02.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fatec.lab.atividade02.entity.Profile;
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
	
	@Autowired
	private ProfileService profileService;

	public User newUser(final UserForm userForm) {
		User newUser = new User(userForm.getUserName(), passwordEncoder.encode(userForm.getPassword()),
				Arrays.asList(profileService.getByName(userForm.getProfile())));
		return save(newUser);
	}

	public User findById(final Long userId) throws ObjectNotFoundException {
		return userRepository.findById(userId)
				.orElseThrow(() -> new ObjectNotFoundException("Usuario nao encontrado."));
	}

	public User findUser(final Long userId) {
		return userRepository.findById(userId).get();
	}

	public Optional<User> findByUserName(final String userName) {
		return userRepository.findByUserName(userName);
	}

	public List<User> findByProfilesName(final String profile) {
		return userRepository.findByProfilesName(profile);
	}

	public void delete(final Long id) throws ObjectNotFoundException {
		final User user = findById(id);
		deleteUser(user);
	}

	public User update(final Long id, final UserForm userForm) throws ObjectNotFoundException {
		Profile profile = profileService.getByName(userForm.getProfile());
		return findById(id).update(userForm, passwordEncoder, profile);
	}

	private User save(final User user) {
		return userRepository.save(user);
	}

	private void deleteUser(User user) {
		userRepository.delete(user);
	}

}
