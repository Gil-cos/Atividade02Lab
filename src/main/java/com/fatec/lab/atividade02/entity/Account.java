package com.fatec.lab.atividade02.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;
import com.fatec.lab.atividade02.enums.AccountType;
import com.fatec.lab.atividade02.view.AccountView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({AccountView.AccountList.class})
	private Long id;

	@NotNull(message = "Provide a number")
	@JsonView({AccountView.AccountDetail.class, AccountView.AccountList.class})
	private Long number;
	
	@NotNull(message = "Provide an owner")
	@JsonView({AccountView.AccountDetail.class, AccountView.AccountList.class})
	@ManyToOne
	private User owner;
	
	@NotNull(message = "Provide a balance")
	@JsonView({AccountView.AccountDetail.class, AccountView.AccountList.class})
	private BigDecimal balance;

	private String password;
	
	@ManyToOne
	@JsonView({AccountView.AccountDetail.class, AccountView.AccountList.class})
	private Bank bank;
	
	@Enumerated(value = EnumType.STRING)
	@JsonView({AccountView.AccountDetail.class, AccountView.AccountList.class})
	private AccountType type;


}
