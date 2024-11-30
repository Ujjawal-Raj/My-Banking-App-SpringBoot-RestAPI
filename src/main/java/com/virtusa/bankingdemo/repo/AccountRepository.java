package com.virtusa.bankingdemo.repo;

import com.virtusa.bankingdemo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
