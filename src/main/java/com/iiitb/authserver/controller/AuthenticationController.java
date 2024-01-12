package com.iiitb.authserver.controller;

import com.iiitb.authserver.dto.AuthenticationRequest;
import com.iiitb.authserver.dto.AuthenticationResponse;
import com.iiitb.authserver.dto.RegisterRequest;
import com.iiitb.authserver.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request,false));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateRequest(@RequestBody AuthenticationRequest request) throws Exception {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PreAuthorize("hasAnyAuthority('admin', 'superadmin')")
    @PostMapping("/addUser")
    public ResponseEntity<AuthenticationResponse> addUser(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.registerOtherRoles(request));
    }

}
