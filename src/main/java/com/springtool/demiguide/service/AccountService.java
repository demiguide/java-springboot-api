package com.springtool.demiguide.service;

import com.springtool.demiguide.entity.AccountEntity;
import com.springtool.demiguide.entity.RoleEntity;
import com.springtool.demiguide.repository.AccountRepository;
import com.springtool.demiguide.repository.RoleRepository;
import com.springtool.demiguide.util.ApiResponse;
import com.springtool.demiguide.util.RegisterRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    private AccountRepository account_repo;
    @Autowired
    private RoleRepository role_repo;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public ResponseEntity<?> addAccount(@NotNull RegisterRequest account){
        if(account_repo.existsByUsername(account.getUsername()) || account_repo.existsByEmail(account.getEmail())) {
            return new ResponseEntity<ApiResponse>(new ApiResponse("ERR","50000","username or email is already used"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String encodedPassword = passwordEncoder.encode(account.getPassword());

        Set<RoleEntity> roles = account.getRolename().stream()
                .map(roleName -> role_repo.findByName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                .collect(Collectors.toSet());

        // สร้าง user
        AccountEntity new_account = new AccountEntity();
        new_account.setUsername(account.getUsername());
        new_account.setEmail(account.getEmail());
        new_account.setPassword(encodedPassword);
        new_account.setRoles(roles);

        return  new ResponseEntity<AccountEntity>(account_repo.save(new_account), HttpStatus.OK);
    }

    public List<AccountEntity> findAllAccount(){
        return account_repo.findAll();
    }

    public Optional<AccountEntity> fineAccount(Integer id){
        return account_repo.findById(id);
    }

    public boolean deleteAccount(Integer id){
        if(account_repo.existsById(id)){
            account_repo.deleteById(id);
            return !account_repo.existsById(id);
        }
        return false;
    }

}
