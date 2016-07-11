package com.raphaelsolarski.oauth2.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.raphaelsolarski.oauth2.model.User;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceTest {

    private static final String USER_PASSWORD = "user_password";
    private static final String USER_NAME = "user_name";
    private static final String ROLE_ONE_AND_ROLE_TWO_IN_STRING = "ROLE_ONE,ROLE_TWO";
    private static final int EXPECTED_ROLES_NUMBER = 2;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserDetailsService sut = new UserDetailsService();

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameShouldThrowWhenNoSuchUser() throws Exception {
        Mockito.when(userService.findByLogin(USER_NAME)).thenReturn(null);
        sut.loadUserByUsername(USER_NAME);
    }

    @Test
    public void loadUserByUserNameShouldReturnExistentUser() throws Exception {
        User user = new User();
        user.setLogin(USER_NAME);
        user.setPassword(USER_PASSWORD);
        user.setRoles(ROLE_ONE_AND_ROLE_TWO_IN_STRING);
        Mockito.when(userService.findByLogin(USER_NAME)).thenReturn(user);

        UserDetails userDetails = sut.loadUserByUsername(USER_NAME);

        Assert.assertEquals(USER_NAME, userDetails.getUsername());
        Assert.assertEquals(USER_PASSWORD, userDetails.getPassword());
        Assert.assertEquals(EXPECTED_ROLES_NUMBER, userDetails.getAuthorities().size());
    }

}