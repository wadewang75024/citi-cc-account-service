package com.citi.cc.accountservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citi.cc.accountservice.persistent.entities.Account;
import com.citi.cc.accountservice.persistent.repo.AccountRepository;

@Service
public class AccountDBServiceImpl implements AccountDBService {
	
	@Autowired
	AccountRepository dbRepo;
	
	@Override
	public Optional<Account> retrieveCustomerById(Long id) throws Exception {
		return dbRepo.findById(id);
	}
	
	@Override
	public Optional<List<Account>> retrieveAll() throws Exception {
		Optional<List<Account>> aList = Optional.ofNullable(dbRepo.findAll());
		return aList;
	}
	
	@Override
	public Account createAccount(Account account) throws Exception { 
		return dbRepo.save(account);
	}
	
	@Override
	public List<Account> retrieveFromAndToAccounts(Long fromAccount, Long toAccount) throws Exception {
		List<Account> accounts = dbRepo.findFromAndToAccounts(fromAccount, toAccount);
		return accounts;
	}
	
	@Override
	@Transactional
	public void balanceTransfer(Long fromAccount, Long toAccount, Double amount) throws Exception {
		List<Account> accounts = retrieveFromAndToAccounts(fromAccount, toAccount);
    	
		Account fAccount= null; Account tAccount= null;
		for (Account a: accounts) {
			if ( a.getId().equals(fromAccount))
				fAccount = a;
			else 
				tAccount = a;   			
		}
		fAccount.setAmount(fAccount.getAmount() - amount);
		tAccount.setAmount(tAccount.getAmount() + amount);
		dbRepo.save(fAccount);
		dbRepo.save(tAccount);
	}

}
