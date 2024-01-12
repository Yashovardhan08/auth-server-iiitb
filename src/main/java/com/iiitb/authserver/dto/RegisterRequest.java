package com.iiitb.authserver.dto;

import com.iiitb.authserver.model.ROLE;
import com.iiitb.authserver.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String collegeEmail;
    private String accPassword;
    private String contact;
    private Date dob;
    private ArrayList<String> roles = new ArrayList<String>();

    public User getUserFromDto() {
        User user = new User();

        user.setUserName(name);
        user.setPassword(accPassword);
        user.setDob(dob);
        user.setContact(contact);
        user.setCollegeEmail(collegeEmail);
        ArrayList<ROLE> userRoles = new ArrayList<ROLE>();
        for(String role: roles){
            switch (role){
                case "student":{
                    userRoles.add(ROLE.STUDENT);
                    break;
                }
                case "professor":{
                    userRoles.add(ROLE.PROFESSOR);
                    break;
                }
                case "admin": {
                    userRoles.add(ROLE.ADMIN);
                    break;
                }
                default:
            }
        }
        user.setRoles(userRoles );

        return user;
    }
}
