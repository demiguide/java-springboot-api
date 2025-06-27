package com.springtool.demiguide.controller;

import com.springtool.demiguide.service.NativeUserService;
import com.springtool.demiguide.entity.UserEntity;
import com.springtool.demiguide.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/native-user")
public class NativeUserController {
    @Autowired
    private NativeUserService nativeService;

    @GetMapping
    public List<UserEntity> getAllUser(){
        return nativeService.getAllUser();
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> getUserById(@PathVariable Long id){
        return nativeService.getUserById(id);
    }

    @PostMapping
    public Optional<UserEntity> addUser(@RequestBody UserEntity user){
        return nativeService.addUser(user);
    }

    @PutMapping("/{id}")
    public Optional<UserEntity> updateUser(@PathVariable Long id, @RequestBody Map<String, Object> fields){
        return nativeService.updateUser(id, fields);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id){
        return nativeService.deleteUser(id) ? new ResponseEntity<>(new ApiResponse("Success","20000","Product with ID " + id + " deleted successfully."), HttpStatus.OK) :
                new ResponseEntity<>(new ApiResponse("ERR","40000","Product with ID " + id + " not found or could not be deleted."), HttpStatus.NOT_FOUND);
    }
}
