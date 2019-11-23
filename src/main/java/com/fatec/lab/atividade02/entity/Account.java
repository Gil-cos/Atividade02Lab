package com.fatec.lab.atividade02.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;
import com.fatec.lab.atividade02.enums.AccountType;
import com.fatec.lab.atividade02.form.AccountForm;
import com.fatec.lab.atividade02.form.AccountUpdateForm;
import com.fatec.lab.atividade02.view.AccountView;
import com.fatec.lab.atividade02.view.UserView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ AccountView.AccountList.class })
	private Long id;
	
	@Column(name = "NUMBER", nullable = false, unique = true)
	@NotNull(message = "Provide a number")
	@JsonView({ AccountView.AccountDetail.class, AccountView.AccountList.class, UserView.UserDetail.class, UserView.UserList.class })
	private Long number;

	@ManyToOne()
	@JoinColumn(name = "OWNER", nullable = false)
	@NotNull(message = "Provide an owner")
	@JsonView({ AccountView.AccountDetail.class, AccountView.AccountList.class })
	private User owner;

	@Column(name = "BALANCE", nullable = false)
	@NotNull(message = "Provide a balance")
	@JsonView({ AccountView.AccountDetail.class, AccountView.AccountList.class })
	private BigDecimal balance;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@ManyToOne
	@JoinColumn(name = "BANK")
	@JsonView({ AccountView.AccountDetail.class, AccountView.AccountList.class })
	private Bank bank;

	@Column(name = "ACCOUNT_TYPE", nullable = false)
	@Enumerated(value = EnumType.STRING)
	@JsonView({ AccountView.AccountDetail.class, AccountView.AccountList.class })
	private AccountType type;

	
	public Account(final AccountForm form) {
		this.number = form.getNumber();
		this.password = form.getPassword();
		this.type = form.getType();
		this.balance = BigDecimal.ZERO;
	}
	
	public Account update(final AccountUpdateForm form) {
		this.password = form.getPassword();
		this.type = form.getType();
		return this;
	}

	public void setReference(final Bank bank, final User user) {
		this.owner = user;
		this.bank = bank;
	}

	public Account withdraw(final BigDecimal value) {
		this.balance = this.balance.subtract(value);
		return this;
	}

	public Account deposit(final BigDecimal value) {
		this.balance = this.balance.add(value);
		return this;
	}
}
