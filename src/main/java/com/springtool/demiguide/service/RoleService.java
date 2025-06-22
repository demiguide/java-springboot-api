package com.springtool.demiguide.service;

import com.springtool.demiguide.entity.RoleEntity;
import com.springtool.demiguide.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repo;

    public RoleEntity addRole(RoleEntity role){
        return  repo.save(role);
    }

    public List<RoleEntity> findAllRole(){
        return repo.findAll();
    }

    public Optional<RoleEntity> fineRole(Integer id){
        return repo.findById(id);
    }

    public boolean deleteRole(Integer id){
        if(repo.existsById(id)){
            repo.deleteById(id);
            return !repo.existsById(id);
        }
        return false;
    }
}
