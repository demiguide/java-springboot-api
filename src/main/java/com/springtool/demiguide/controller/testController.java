package com.springtool.demiguide.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class testController {
    @GetMapping("/hello")
    public String hello(Authentication auth) {
        return "Hello, " + auth.getName();
    }

    @PreAuthorize("hasRole('USER')") // allow role USER only @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/public/ping")
    public String ping() {
        return "Public OK ✅";
    }
}
