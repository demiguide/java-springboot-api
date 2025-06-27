package com.springtool.demiguide.controller;

import com.springtool.demiguide.entity.AccountEntity;
import com.springtool.demiguide.service.AccountService;
import com.springtool.demiguide.model.ApiResponse;
import com.springtool.demiguide.model.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<AccountEntity> findAllAccount(){
        return accountService.findAllAccount();
    }

    @GetMapping("/{id}")
    public Optional<AccountEntity> findAccount(@PathVariable Integer id){
        return accountService.fineAccount(id);
    }

    @PostMapping
    public ResponseEntity<?> addAccount(@RequestBody RegisterRequest account){
        return accountService.addAccount(account);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAccount(@PathVariable Integer id){
        return accountService.deleteAccount(id) ? new ResponseEntity<>(new ApiResponse("Success","20000","Account with ID " + id + " deleted successfully."), HttpStatus.OK)
                : new ResponseEntity<>(new ApiResponse("ERR","40000","Product with ID " + id + " not found or could not be deleted."), HttpStatus.NOT_FOUND) ;
    }
}
