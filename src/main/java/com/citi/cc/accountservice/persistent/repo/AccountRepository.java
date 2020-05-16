package com.citi.cc.accountservice.persistent.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.citi.cc.accountservice.persistent.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	List<Account> findFromAndToAccounts(@Param("fromAccount") Long fromAccount, 
									    @Param("toAccount") Long toAccount);
}
