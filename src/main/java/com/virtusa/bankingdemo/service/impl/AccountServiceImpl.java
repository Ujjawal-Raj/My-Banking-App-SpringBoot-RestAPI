package com.virtusa.bankingdemo.service.impl;

import com.virtusa.bankingdemo.dto.AccountDTO;
import com.virtusa.bankingdemo.entity.Account;
import com.virtusa.bankingdemo.mapper.AccountMapper;
import com.virtusa.bankingdemo.repo.AccountRepository;
import com.virtusa.bankingdemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository theAccountRepository) {
        accountRepository = theAccountRepository;
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account savedAccount = accountRepository.save(AccountMapper.toAccount(accountDTO));
        return AccountMapper.toAccountDto(savedAccount);
    }

    @Override
    public AccountDTO getAccountById(Long id) {
        Optional<Account> tempAccount = accountRepository.findById(id);
        Account account = tempAccount.orElseThrow(() -> new RuntimeException("Account with Id: "+id+" doesn't exist"));
        return AccountMapper.toAccountDto(account);
    }

    @Override
    public AccountDTO deposit(Long id, double amount) {

        Optional<Account> tempAccount = accountRepository.findById(id);
        Account account = tempAccount.orElseThrow(() -> new RuntimeException("Account with Id: "+id+" doesn't exist"));

        account.setBalance(account.getBalance() + amount);

        accountRepository.save(account);

        return AccountMapper.toAccountDto(account);
    }

    @Override
    public AccountDTO withDraw(Long id, double amount) {

        Optional<Account> tempAccount = accountRepository.findById(id);
        Account account = tempAccount.orElseThrow(() -> new RuntimeException("Account with Id: "+id+" doesn't exist"));

        if(account.getBalance() < amount)
            throw new RuntimeException("Insufficient Balance!!!");

        account.setBalance(account.getBalance() - amount);

        accountRepository.save(account);

        return AccountMapper.toAccountDto(account);

    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        //return accounts.stream()
                //.map((AccountMapper::toAccountDto)).collect(Collectors.toList());
        return accounts.stream()
                .map((account -> AccountMapper.toAccountDto(account))).collect(Collectors.toList());
    }

    @Override
    public String deleteAccount(Long id) {

        Optional<Account> tempAccount = accountRepository.findById(id);
        Account account = tempAccount.orElseThrow(() -> new RuntimeException("Account with Id: "+id+" doesn't exist"));

        accountRepository.delete(account);

        return "Account Id: "+id+" has been deleted now!";
    }

}
