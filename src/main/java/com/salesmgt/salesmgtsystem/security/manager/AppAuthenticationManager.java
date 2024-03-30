package com.salesmgt.salesmgtsystem.security.manager;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AppAuthenticationManager implements AuthenticationManager {
    private final AuthenticationProvider authenticationProvider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Class<?> authClass = authentication.getClass();
        if (authenticationProvider.supports(authClass)) {
            return authenticationProvider.authenticate(authentication);
        }
        throw new ProviderNotFoundException("Failed to authenticate request");
    }
}
