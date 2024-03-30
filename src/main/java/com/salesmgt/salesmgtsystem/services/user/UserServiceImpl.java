package com.salesmgt.salesmgtsystem.services.user;

import com.salesmgt.salesmgtsystem.repositories.UserRepository;
import com.salesmgt.salesmgtsystem.security.Principal;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username)  {
        return new Principal(userRepository.findByUsername(username).orElseThrow(()->
                new UsernameNotFoundException(format("user with username %s not found", username))));
    }
}
