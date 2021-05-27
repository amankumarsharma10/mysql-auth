package com.aman.mysqlauth.service;

import java.util.Optional;

import com.aman.mysqlauth.model.User;
import com.aman.mysqlauth.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> userOp = userRepository.findByUserName(userName);
        userOp.orElseThrow(()-> new UsernameNotFoundException("Username is Not Found"));
        //return new MyUserDetails(userOp.get());
        return userOp.map(MyUserDetails::new).get(); // JAVA8 WAY :D
    }
}
