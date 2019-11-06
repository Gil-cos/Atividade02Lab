package com.fatec.lab.atividade02.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonView;
import com.fatec.lab.atividade02.view.ProfileView;

@Entity
public class Profile implements GrantedAuthority{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonView({ProfileView.ProfileDetail.class, ProfileView.ProfileList.class})
	private String name;
	
	public Profile(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
