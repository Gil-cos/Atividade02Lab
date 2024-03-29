package com.fatec.lab.atividade02.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.lab.atividade02.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserName(final String userName);

	List<User> findByProfilesName(final String name);

	Optional<User> findByCpf(final String cpf);

}
