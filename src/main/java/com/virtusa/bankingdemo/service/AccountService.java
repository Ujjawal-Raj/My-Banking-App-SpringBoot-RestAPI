package com.virtusa.bankingdemo.service;

import com.virtusa.bankingdemo.dto.AccountDTO;
import com.virtusa.bankingdemo.dto.AccountDTO;

import java.util.List;

public interface AccountService {

    AccountDTO createAccount(AccountDTO accountDTO);

    AccountDTO getAccountById(Long id);

    AccountDTO deposit(Long id, double amount);

    AccountDTO withDraw(Long id, double amount);

    List<AccountDTO> getAllAccounts();

    String deleteAccount(Long id);

}
