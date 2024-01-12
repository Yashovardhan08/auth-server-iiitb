package com.iiitb.authserver.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/auth/demo-controller")
public class DemoController {
    @GetMapping("/admin-ping")
    @PreAuthorize("hasAnyAuthority('admin', 'superadmin')")
    public ResponseEntity<String> adminHello() {
        return ResponseEntity.ok("Hello for admin from endpoint");
    }

    @GetMapping("/student-ping")
    @PreAuthorize("hasAnyAuthority('student', 'superadmin','admin')")
    public ResponseEntity<String> userHello() {
        return ResponseEntity.ok("Hello for student from endpoint");
    }
    @GetMapping("/prof-ping")
    @PreAuthorize("hasAnyAuthority('professor', 'superadmin','admin')")
    public ResponseEntity<String> profHello() {
        return ResponseEntity.ok("Hello for Prof from endpoint");
    }

    @GetMapping("/superadmin-ping")
    @PreAuthorize("hasAnyAuthority('superadmin')")
    public ResponseEntity<String> superAdminHello() {
        return ResponseEntity.ok("Hello for super-admin from endpoint");
    }
}
