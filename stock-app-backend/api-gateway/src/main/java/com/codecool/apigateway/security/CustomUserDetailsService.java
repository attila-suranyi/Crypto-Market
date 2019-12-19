package com.codecool.apigateway.security;


import com.codecool.apigateway.service.UserCaller;
import com.codecool.cryptomarketjsonclasses.model.StockAppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCaller userCaller;

    /**
     * Loads the user from the DB and converts it to Spring Security's internal User object
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        StockAppUser user = userCaller.getUser(username);

        return new User(user.getUserName(), user.getPassword(),
                user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
}
