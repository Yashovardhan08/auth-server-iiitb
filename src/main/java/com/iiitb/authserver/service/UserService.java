package com.iiitb.authserver.service;

import com.iiitb.authserver.dto.UserDTO;
import com.iiitb.authserver.model.ROLE;
import com.iiitb.authserver.model.User;
import com.iiitb.authserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String collegeEmail) throws UsernameNotFoundException {
        User user = findUserByCollegeEmail(collegeEmail);
        return new org.springframework.security.core.userdetails.User(user.getCollegeEmail(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for(ROLE r:user.getRoles()){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+r.toString().toUpperCase()));
        }
        return authorities;
    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void createUser(UserDTO userDTO) {
        User user = userDTO.getUserFromDto();
        userRepository.save(user);
    }

    public User findUserByCollegeEmail(String collegeEmail) throws UsernameNotFoundException{
        // FIND USER
        Optional<User> user = userRepository.findByCollegeEmail(collegeEmail);
        // SEND USER ID TO ROLE
        if(user.isPresent()){
            User u = user.get();
            return u;
        }
        else throw new UsernameNotFoundException("User is not found");
    }

    public List<User> findAllUsers()
    {
        return userRepository.findAll();
    }
}
