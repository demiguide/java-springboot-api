package com.springtool.demiguide.repository;

import com.springtool.demiguide.entity.AccountEntity;
import com.springtool.demiguide.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<AccountEntity> findByUsername(String username);
}
