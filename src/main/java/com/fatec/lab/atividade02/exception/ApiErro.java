package com.fatec.lab.atividade02.exception;

import com.fasterxml.jackson.annotation.JsonView;
import com.fatec.lab.atividade02.view.ApiErroView;

public class ApiErro {
	
	public ApiErro(Integer erro, String descricao) {
		this.erro = erro;
		this.descricao = descricao;
	}
	
	@JsonView({ApiErroView.ApiErrorDetail.class})
	private Integer erro;
	
	@JsonView({ApiErroView.ApiErrorDetail.class})
	private String descricao;
}
