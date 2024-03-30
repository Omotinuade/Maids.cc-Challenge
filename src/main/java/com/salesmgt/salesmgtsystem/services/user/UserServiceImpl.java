package com.salesmgt.salesmgtsystem.services.user;

import com.salesmgt.salesmgtsystem.dtos.requests.RegisterUserRequest;
import com.salesmgt.salesmgtsystem.dtos.responses.UserResponse;
import com.salesmgt.salesmgtsystem.models.User;
import com.salesmgt.salesmgtsystem.repositories.UserRepository;
import com.salesmgt.salesmgtsystem.security.Principal;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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

    @Override
    public UserResponse createUser(RegisterUserRequest registerUserRequest) {
        ModelMapper mapper = new ModelMapper();
        User user = mapper.map(registerUserRequest, User.class);

        User savedUser = userRepository.save(user);
        var response = new UserResponse();
        response.setUsername(savedUser.getUsername());
        response.setId(savedUser.getId());
        return response;
    }
}
