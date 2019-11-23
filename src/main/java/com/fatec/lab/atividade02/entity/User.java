package com.fatec.lab.atividade02.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonView;
import com.fatec.lab.atividade02.form.UserForm;
import com.fatec.lab.atividade02.view.AccountView;
import com.fatec.lab.atividade02.view.UserView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ UserView.UserList.class, UserView.UserDetail.class })
	private Long id;

	@Column(name = "USER_NAME", nullable = false)
	@NotNull(message = "Provide a username")
	@JsonView({ UserView.UserDetail.class, UserView.UserList.class, AccountView.AccountDetail.class, AccountView.AccountList.class })
	private String userName;
	
	@Column(name = "PASSWORD", nullable = false)
	@NotNull(message = "Provide a password")
	private String password;
	
	@Column(name = "CPF", nullable = false, unique = true)
	@NotNull(message = "Provide a CPF")
	@JsonView({ UserView.UserList.class, UserView.UserDetail.class })
	private String cpf;

	@ManyToMany(fetch = FetchType.EAGER)
	@JsonView({ UserView.UserDetail.class, UserView.UserList.class })
	private List<Profile> profiles = new ArrayList<>();
	

	public User(String name, String password, List<Profile> profiles) {
		this.userName = name;
		this.password = password;
		this.profiles.addAll(profiles);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.profiles;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public User update(@Valid UserForm userForm, PasswordEncoder encoder, Profile profile) {
		this.userName = userForm.getUserName();
		this.password = encoder.encode(userForm.getPassword());
		this.cpf = userForm.getCpf();
		updateProfile(profile);
		return this;
	}

	private void updateProfile(Profile profile) {
		this.profiles.clear();
		this.profiles.add(profile);
	}


}
