package com.fatec.lab.atividade02.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;
import com.fatec.lab.atividade02.view.AccountView;
import com.fatec.lab.atividade02.view.BankView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({BankView.BankList.class})
	private Long id;
	
	@NotNull(message = "Provide a name")
	@JsonView({AccountView.AccountDetail.class, AccountView.AccountList.class, BankView.BankList.class})
	private String name;
	
	@NotNull(message = "Provide an cnpj")
	@JsonView({BankView.BankList.class})
	private String cnpj;
	
	@NotNull(message = "Provide an address")
	@JsonView({BankView.BankList.class})
	private String endereço;
	
	@OneToMany(mappedBy = "bank")
	private Set<Account> accounts;

}
