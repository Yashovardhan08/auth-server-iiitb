package com.iiitb.authserver.dto;

import com.iiitb.authserver.model.ROLE;

import com.iiitb.authserver.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

@Data
public class UserDTO {
    private String name;
    private String accPassword;
    private String rollNo;
    private Date dob;
    private String personalEmail;
    private String address;
    private String gender;
    private String contact;
    private String collegeEmail;
    private ArrayList<String> roles; // Set of roles associated with the user
    public User getUserFromDto(){
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
//                case "professor":{
//                    userRoles.add(ROLE.PROFESSOR);
//                    break;
//                }
//                case "admin":{
//                    userRoles.add(ROLE.ADMIN);
//                    break;
//                }
//                case "superadmin":{
//                    userRoles.add(ROLE.SUPERADMIN);
//                    break;
//                }
                default:

            }
        }
        user.setRoles(userRoles );

        return user;
    }
}
