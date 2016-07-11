package com.raphaelsolarski.oauth2.model;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class CustomUserDetailsTest {

    @Test
    public void ofUser() throws Exception {
        User user = new User();
        user.setRoles("ROLE_ONE,ROLE_TWO");

        CustomUserDetails result = CustomUserDetails.ofUser(user);
        GrantedAuthority grantedAuthority1 = new SimpleGrantedAuthority("ROLE_ONE");
        GrantedAuthority grantedAuthority2 = new SimpleGrantedAuthority("ROLE_TWO");

        MatcherAssert.assertThat(result.getAuthorities(), Matchers.containsInAnyOrder(grantedAuthority1, grantedAuthority2));
    }

}