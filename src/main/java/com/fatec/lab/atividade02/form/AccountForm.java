package com.fatec.lab.atividade02.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fatec.lab.atividade02.enums.AccountType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountForm {
	
	@NotNull @NotEmpty
	private Long number;

	@NotNull @NotEmpty
	private Long ownerId;
	
	@NotNull @NotEmpty
	private Long bankId;
	
	@NotNull @NotEmpty
	private String password;
	
	@NotNull @NotEmpty
	private AccountType type;

}
