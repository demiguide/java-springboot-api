package com.springtool.demiguide.service;

import com.springtool.demiguide.entity.AccountEntity;
import com.springtool.demiguide.entity.RoleEntity;
import com.springtool.demiguide.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity account = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // ตัด prefix "ROLE_" ออกก่อน
        String[] roleNames = account.getRoles().stream()
                .map(RoleEntity::getName)
                .map(name -> name.startsWith("ROLE_") ? name.substring(5) : name)
                .toArray(String[]::new);

        return User.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .roles(roleNames)
                .build();
    }
}