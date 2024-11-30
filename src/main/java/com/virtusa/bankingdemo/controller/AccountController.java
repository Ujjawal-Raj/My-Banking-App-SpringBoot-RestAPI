package com.virtusa.bankingdemo.controller;

import com.virtusa.bankingdemo.dto.AccountDTO;
import com.virtusa.bankingdemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService theAccountService) {
        accountService = theAccountService;
    }

    @PostMapping
    public ResponseEntity<AccountDTO> saveAccount(@RequestBody AccountDTO accountDTO) {
        AccountDTO tempAccountDTO = accountService.createAccount(accountDTO);
        return new ResponseEntity<AccountDTO>(tempAccountDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccounts(@PathVariable Long id){
        AccountDTO tempAccountDTO = accountService.getAccountById(id);
        return new ResponseEntity<AccountDTO>(tempAccountDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> deposit(@PathVariable Long id, @RequestBody Map<String, Double> amount){

        Double tempAmount = amount.get("amount");

        AccountDTO tempAccountDTO = accountService.deposit(id, tempAmount);

        return ResponseEntity.ok(tempAccountDTO);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDTO> withDraw(@PathVariable Long id, @RequestBody Map<String, Double> amount){

        Double tempAmount = amount.get("amount");

        AccountDTO tempAccountDTO = accountService.withDraw(id, tempAmount);

        return ResponseEntity.ok(tempAccountDTO);
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> accountDTOS = accountService.getAllAccounts();
        return ResponseEntity.ok(accountDTOS);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String msg = accountService.deleteAccount(id);
        return ResponseEntity.ok(msg);
    }

}
