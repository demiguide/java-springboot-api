package com.springtool.demiguide.service;

import com.springtool.demiguide.entity.UserEntity;
import com.springtool.demiguide.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public List<UserEntity> findAll(){
        return repo.findAll();
    }

    public Optional<UserEntity> findById(Long id){
        return repo.findById(id);
    }

    public Optional<UserEntity> findByName(String name){
        return repo.findByName(name);
    }

    public UserEntity addUser(UserEntity user){
        return repo.save(user);
    }

    public ResponseEntity<?> updateUser(Long id, UserEntity user){
        return repo.findById(id).map(update -> {
            update.setName(user.getName());
            update.setEmail(user.getEmail());
            update.setPassword(user.getPassword());
            repo.save(update);
            return ResponseEntity.ok(update);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public boolean deleteUser(Long id){
        if(repo.existsById(id)){
            repo.deleteById(id);
            return !repo.existsById(id);
        }
        return false;
    }
}
