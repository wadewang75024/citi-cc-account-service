package com.citi.cc.accountservice.data;

import com.citi.cc.accountservice.persistent.entities.Account;

public class DataMapper {
	public static AccountDTO mapToDTO(Account account) {
		AccountDTO dto = new AccountDTO();
		dto.setId(account.getId());
		dto.setType(account.getType());
		dto.setAmount(account.getAmount());
		return dto;
	}
	
	public static Account mapToEntity(AccountDTO dto) {
		Account entity = new Account();
		entity.setId(dto.getId());
		entity.setCustomerId(dto.getCustomerId());
		entity.setType(dto.getType());
		entity.setAmount(dto.getAmount());
		
		return entity;
	}
}
