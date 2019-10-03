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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;
import com.fatec.lab.atividade02.enums.AccountType;
import com.fatec.lab.atividade02.view.AccountView;

@Entity
@Table
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
	private String owner;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}


}
