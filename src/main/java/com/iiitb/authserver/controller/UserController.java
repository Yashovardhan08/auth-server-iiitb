package com.iiitb.authserver.controller;

import com.iiitb.authserver.dto.AuthDTO;
import com.iiitb.authserver.dto.RegisterRequest;
import com.iiitb.authserver.dto.UserDTO;
import com.iiitb.authserver.model.User;
import com.iiitb.authserver.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/addAdmin")
    @PreAuthorize("hasRole('super-admin')")
    public ResponseEntity<String> addAdmin(@RequestBody RegisterRequest request) {
        userService.createUserFromRequest(request);
        return ResponseEntity.ok("User created successfully");
    }

    @PostMapping("/addProf")
    @PreAuthorize("hasRole('super-admin','admin')")
    public ResponseEntity<String> addProf(@RequestBody RegisterRequest request) {
        userService.createUserFromRequest(request);
        return ResponseEntity.ok("User created successfully");
    }
}
