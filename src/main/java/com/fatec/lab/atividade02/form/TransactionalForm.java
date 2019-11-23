package com.fatec.lab.atividade02.form;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionalForm {

	@NonNull
	private BigDecimal value;
}
