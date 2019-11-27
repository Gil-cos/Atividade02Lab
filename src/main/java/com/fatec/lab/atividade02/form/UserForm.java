package com.fatec.lab.atividade02.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
	
	@NotNull @NotEmpty
	private String userName;
	
	@NotNull @NotEmpty
	private String cpf;

	@NotNull @NotEmpty
	private String password;
	
	@NotNull @NotEmpty
	private String profile;

}