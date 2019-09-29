package com.fatec.lab.atividade02.exception;

import com.fasterxml.jackson.annotation.JsonView;
import com.fatec.lab.atividade02.view.ApiErroView;

public class ApiErro {
	
	public ApiErro(Integer status, String descricao) {
		this.status = status;
		this.descricao = descricao;
	}
	
	@JsonView({ApiErroView.ApiErrorDetail.class})
	private Integer status;
	
	@JsonView({ApiErroView.ApiErrorDetail.class})
	private String descricao;
}
