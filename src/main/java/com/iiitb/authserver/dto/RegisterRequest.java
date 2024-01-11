package com.iiitb.authserver.dto;

import com.iiitb.authserver.model.ROLE;
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
}
