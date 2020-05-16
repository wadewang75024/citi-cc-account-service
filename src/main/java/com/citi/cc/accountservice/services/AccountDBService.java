package com.citi.cc.accountservice.services;

import java.util.List;
import java.util.Optional;

import com.citi.cc.accountservice.persistent.entities.Account;

public interface AccountDBService {
	Optional<Account> retrieveCustomerById(Long id) throws Exception;
	Optional<List<Account>> retrieveAll() throws Exception;	
	Account createAccount(Account account) throws Exception;
	
	List<Account> retrieveFromAndToAccounts(Long fromAccount, Long toAccount) throws Exception;
	void balanceTransfer(Long fromAccount, Long toAccount, Double amount) throws Exception;
}
