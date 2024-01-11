package com.iiitb.authserver.service;

import com.iiitb.authserver.config.JwtService;
import com.iiitb.authserver.dto.AuthenticationRequest;
import com.iiitb.authserver.dto.AuthenticationResponse;
import com.iiitb.authserver.dto.RegisterRequest;
import com.iiitb.authserver.model.ROLE;
import com.iiitb.authserver.model.User;
import com.iiitb.authserver.repository.UserRepository;
import com.iiitb.authserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse register(RegisterRequest request) {
        System.out.println(request.getName());

        byte[] decodedBytes = Base64.getDecoder().decode(request.getAccPassword());
        String decodedPassword = new String(decodedBytes);
        System.out.println(decodedPassword);

        ArrayList<ROLE> userRoles = new ArrayList<ROLE>();
        for(String role: request.getRoles()){
            switch (role){
                case "student":{
                    userRoles.add(ROLE.STUDENT);
                    break;
                }
                case "professor":{
                    userRoles.add(ROLE.PROFESSOR);
                    break;
                }
                case "admin":{
                    userRoles.add(ROLE.ADMIN);
                    break;
                }
                case "superadmin":{
                    userRoles.add(ROLE.SUPERADMIN);
                    break;
                }
                default:
            }
        }

        var user = User.builder()
        .userName(request.getName())
        .dob(request.getDob())
        .roles(userRoles)
        .contact(request.getContact())
        .collegeEmail(request.getCollegeEmail())
        .password(passwordEncoder.encode(request.getAccPassword())).contact(request.getContact()).build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
        byte[] decodedBytes = Base64.getDecoder().decode(request.getAccPassword());
        String decodedPassword = new String(decodedBytes);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getCollegeEmail(), request.getAccPassword()));
        User user = userService.findUserByCollegeEmail(request.getCollegeEmail());
        HashMap<String, Object> claims = new HashMap<String, Object>();
        List<String> roles = new ArrayList<String>();

        for (ROLE role: user.getRoles()) {
            roles.add("ROLE_" + role.toString().toUpperCase());
        }
        claims.put("roles", roles.toArray());
        var jwtToken = jwtService.generateToken(claims, user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
