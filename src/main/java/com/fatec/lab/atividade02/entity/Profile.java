package com.fatec.lab.atividade02.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonView;
import com.fatec.lab.atividade02.view.ProfileView;
import com.fatec.lab.atividade02.view.UserView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Profile implements GrantedAuthority{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NAME", nullable = false)
	@JsonView({ProfileView.ProfileDetail.class, ProfileView.ProfileList.class, UserView.UserList.class, UserView.UserDetail.class})
	private String name;
	
	@Override
	public String getAuthority() {
		return name;
	}	
	
}
