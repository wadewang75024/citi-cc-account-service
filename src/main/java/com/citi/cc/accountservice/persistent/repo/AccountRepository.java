package com.citi.cc.accountservice.persistent.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citi.cc.accountservice.persistent.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
