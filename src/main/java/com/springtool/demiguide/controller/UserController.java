package com.springtool.demiguide.controller;

import com.springtool.demiguide.service.UserService;
import com.springtool.demiguide.entity.UserEntity;
import com.springtool.demiguide.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserEntity> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> getUserById(@PathVariable Long id){
        return  userService.findById(id);
    }

    @GetMapping("/find")
    public Optional<UserEntity> getUserByParam(@RequestParam(required = false) Long id,
                                        @RequestParam(required = false) String name) {
        if (id != null) return userService.findById(id);
        if (name != null) return userService.findByName(name);
        return Optional.empty();
    }

    @PostMapping
    public UserEntity addUser(@RequestBody UserEntity user) {
        return userService.addUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserEntity user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id){
        boolean flag = userService.deleteUser(id);
        if(flag) return new ResponseEntity<>(new ApiResponse("Success","20000","Product with ID " + id + " deleted successfully."), HttpStatus.OK);
        else return new ResponseEntity<>(new ApiResponse("ERR","40000","Product with ID " + id + " not found or could not be deleted."), HttpStatus.NOT_FOUND);
    }
}
