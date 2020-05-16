package com.citi.cc.accountservice.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.citi.cc.accountservice.data.AccountDTO;
import com.citi.cc.accountservice.data.BalanceTransferDTO;
import com.citi.cc.accountservice.data.DataMapper;
import com.citi.cc.accountservice.exceptions.BusinessException;
import com.citi.cc.accountservice.persistent.entities.Account;
import com.citi.cc.accountservice.services.AccountDBService;

@RestController
public class AccountController {
public static Logger logger = LogManager.getLogger(AccountController.class);
	
    @Autowired 
    AccountDBService accountDBService;

    /**
     * Access via http://localhost:3001/
     * @return
     */
    @GetMapping(path="/accounts" )
    public List<AccountDTO> getAllCustomers() {
    	logger.info("getAllAccounts starts...");
    		
    	Optional<List<Account>> accounts = null;
    	try {
    		accounts = accountDBService.retrieveAll();
    		if ( accounts.isPresent()) {
    			List<Account> accountList = accounts.get();
    			List<AccountDTO> dtoList = new ArrayList<>();
    			accountList.forEach( account-> {
    				AccountDTO dto = DataMapper.mapToDTO(account);
    				dtoList.add(dto);
    			}
    			);
    			return dtoList;
    		}
    		else
    			throw new BusinessException();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		throw new BusinessException();
    	}
    }

    /**
     * Access via http://localhost:3001/1
     * @param id
     * @return
     */
    @GetMapping("/account/{id}")
    public AccountDTO getAccountById(@PathVariable long id) {
    	logger.info("getAccountById starts...");
    	try {
    	  Optional<Account> account = accountDBService.retrieveCustomerById(id);
    	  if ( account.isPresent() ) {
    		  AccountDTO dto = DataMapper.mapToDTO(account.get());
    		  return dto;
    	  }
    	  else {
    		  throw new BusinessException();
    	  }
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		throw new BusinessException();
    	}
    }
    
    @PostMapping("/account/new")
    public AccountDTO createAccount(@RequestBody AccountDTO dto) {
    	try {
    	  Account account = DataMapper.mapToEntity(dto);
    	  Account newAccount = accountDBService.createAccount(account);
    	  AccountDTO result = DataMapper.mapToDTO(newAccount);
    	  return result;
    	}
    	catch (Exception e) {
    		throw new BusinessException();
    	}
    }
    
    @PutMapping(path="/account/balanceTransfer",  consumes={MediaType.APPLICATION_JSON_VALUE})
    public String balanceTransfer(@RequestBody BalanceTransferDTO dto) {
    	logger.info(dto.getFromAccount() + "-" + dto.getToAccount() + "-" + dto.getAmount());
    	
    	try {		
        	accountDBService.balanceTransfer(dto.getFromAccount(), dto.getToAccount(), dto.getAmount());
    	}
    	catch (Exception e) {
    		throw new BusinessException();
    	}
    	return "ok";
    }

}
