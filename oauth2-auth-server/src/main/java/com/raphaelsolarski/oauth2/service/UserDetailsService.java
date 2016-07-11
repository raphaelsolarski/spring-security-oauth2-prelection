package com.raphaelsolarski.oauth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.raphaelsolarski.oauth2.model.User;
import com.raphaelsolarski.oauth2.model.CustomUserDetails;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = Optional.ofNullable(userService.findByLogin(username));
        return CustomUserDetails.ofUser(userOptional.orElseThrow(() -> new UsernameNotFoundException("no such a user with login " + username)));
    }

}
