package com.fatec.lab.atividade02.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatec.lab.atividade02.entity.Account;
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
	private AccountService accountService;
	
	@Autowired
	private ProfileService profileService;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public User newUser(final UserForm userForm) {
		User newUser = new User(userForm.getUserName(), passwordEncoder.encode(userForm.getPassword()),
				Arrays.asList(profileService.getByName(userForm.getProfile())));
		return save(newUser);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public User findById(final Long userId) throws ObjectNotFoundException {
		return userRepository.findById(userId)
				.orElseThrow(() -> new ObjectNotFoundException("Usuario nao encontrado."));
	}

	public User findUser(final Long userId) {
		return userRepository.findById(userId).get();
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public User findByCpf(final String cpf) throws ObjectNotFoundException {
		return userRepository.findByCpf(cpf)
				.orElseThrow(() -> new ObjectNotFoundException("Usuario nao encontrado."));
	}

	public Optional<User> findByUserName(final String userName) {
		return userRepository.findByUserName(userName);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public List<User> findByProfilesName(final String profile) {
		return userRepository.findByProfilesName(profile);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public void delete(final Long id) throws ObjectNotFoundException {
		User user = findById(id);
		Optional<Account> account = accountService.findByOwner(user);
		if (account.isPresent()) {
			throw new DataIntegrityViolationException("Usuario possui uma conta ativa.");
		} else {
			delete(user);	
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public User update(final Long id, final UserForm userForm) throws ObjectNotFoundException {
		Profile profile = profileService.getByName(userForm.getProfile());
		return save(findById(id).update(userForm, passwordEncoder, profile));
	}

	@Transactional
	private User save(final User user) {
		return userRepository.save(user);
	}

	@Transactional
	private void delete(final User user) {
		userRepository.delete(user);
	}


}
