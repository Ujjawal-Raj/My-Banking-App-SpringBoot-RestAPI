package com.virtusa.bankingdemo.mapper;

import com.virtusa.bankingdemo.dto.AccountDTO;
import com.virtusa.bankingdemo.entity.Account;

public class AccountMapper {

    public static Account toAccount(AccountDTO accountDTO){
        return new Account(
                accountDTO.getId(),
                accountDTO.getAccountHolderName(),
                accountDTO.getBalance()
        );
    }

    public static AccountDTO toAccountDto(Account account){
        return new AccountDTO(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
    }

}
