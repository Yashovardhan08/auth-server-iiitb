package com.iiitb.authserver;


import com.iiitb.authserver.dto.RegisterRequest;
import com.iiitb.authserver.model.ROLE;
import com.iiitb.authserver.model.User;
import com.iiitb.authserver.repository.UserRepository;
import com.iiitb.authserver.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;
    private final AuthenticationService userService;
    private static String passwd;
    private static String collegeEmail;
    private static String username;

    @Value("${super_admin_username}")
    public void setUsername(String usrName) {
        username = usrName;
    }

    @Value("${super_admin_email}")
    public  void setAdminEmail(String email){
        collegeEmail=email;
    }

    @Value("${super_admin_password}")
    public void setAdminPassword(String pass){
        passwd = pass;
    }
    @Autowired
    public DataLoader(AuthenticationService userService,UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        // Check if users already exist
        if (!userRepository.findByUserName("superAdmin").isPresent()) {
            // Add default users
            RegisterRequest superadmin = new RegisterRequest();
            superadmin.setAccPassword(passwd);
            superadmin.setName(username);
            superadmin.setCollegeEmail(collegeEmail);
            ArrayList<String> roles = new ArrayList<>();
            roles.add("superadmin");
            superadmin.setRoles(roles);

            userService.register(superadmin,true);

        }
    }
}