package com.fatec.lab.atividade02.form;

import javax.validation.constraints.NotNull;

import com.fatec.lab.atividade02.enums.AccountType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountUpdateForm {
	
	@NotNull
	private String password;
	
	@NotNull
	private AccountType type;
}
