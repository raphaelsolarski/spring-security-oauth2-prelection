package com.raphaelsolarski;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final String CAN_NOT_FIND_USER_WITH_USERNAME = "Can not find user with username:";
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(username));

        return userOptional.orElseThrow(() -> new UsernameNotFoundException(CAN_NOT_FIND_USER_WITH_USERNAME + username));
    }
}
