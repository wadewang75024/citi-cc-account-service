package com.citi.cc.accountservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.cc.accountservice.persistent.entities.Account;
import com.citi.cc.accountservice.persistent.repo.AccountRepository;

@Service
public class AccountDBServiceImpl implements AccountDBService {
	
	@Autowired
	AccountRepository dbRepo;
	
	public Optional<Account> retrieveCustomerById(Long id) throws Exception {
		return dbRepo.findById(id);
	}
	
	public Optional<List<Account>> retrieveAll() throws Exception {
		Optional<List<Account>> aList = Optional.ofNullable(dbRepo.findAll());
		return aList;
	}
	
	public Account createAccount(Account account) throws Exception { 
		return dbRepo.save(account);
	}

}
