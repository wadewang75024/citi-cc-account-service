package com.citi.cc.accountservice.persistent.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.GenerationType;

@Entity
@Table(name="citi_accounts")
@NamedQuery(name="Account.findFromAndToAccounts",
			query="select a from Account a where a.id=:fromAccount or a.id=:toAccount")
public class Account {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Column(name="account_id")
	Long id;
	
	@Column(name="customer_id")
	Long customerId;
	
	@Column(name="type")
	String type;
	
	@Column(name="amount")
	Double amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
