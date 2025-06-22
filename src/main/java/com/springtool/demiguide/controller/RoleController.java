package com.springtool.demiguide.controller;

import com.springtool.demiguide.entity.RoleEntity;
import com.springtool.demiguide.service.RoleService;
import com.springtool.demiguide.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<RoleEntity> findAllRole(){
        return roleService.findAllRole();
    }

    @GetMapping("/{id}")
    public Optional<RoleEntity> findRole(@PathVariable Integer id){
        return roleService.fineRole(id);
    }

    @PostMapping
    public RoleEntity addRole(@RequestBody RoleEntity role){
        return roleService.addRole(role);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteRole(@PathVariable Integer id){
        return roleService.deleteRole(id) ? new ResponseEntity<>(new ApiResponse("Success","20000","Role with ID " + id + " deleted successfully."), HttpStatus.OK)
                : new ResponseEntity<>(new ApiResponse("ERR","40000","Product with ID " + id + " not found or could not be deleted."), HttpStatus.NOT_FOUND) ;
    }
}
