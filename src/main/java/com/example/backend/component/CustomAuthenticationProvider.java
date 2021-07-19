package com.example.backend.component;

import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication)
        throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        try {
            UserDetails user = userService.loadUserByUsername(name);
            if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
                return new UsernamePasswordAuthenticationToken(
                        name, password, user.getAuthorities());
            } else {
                return null;
            }
        } catch (UsernameNotFoundException e) {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
