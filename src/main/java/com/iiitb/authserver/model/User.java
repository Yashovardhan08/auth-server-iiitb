package com.iiitb.authserver.model;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class User implements UserDetails {
    @Id
    private String id;

    @NotBlank
    private String password;

    private Date dob;

    @NotBlank
    private String userName;

    private String contact;

    @NotBlank
    private String collegeEmail;


    private ArrayList<ROLE> roles = new ArrayList<ROLE>();

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        System.out.println(this.getRoles().size());
        for (ROLE role : this.roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase()));
//            System.out.println(role.toString().toUpperCase());
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.collegeEmail;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}

