package com.salesmgt.salesmgtsystem.services.user;

import com.salesmgt.salesmgtsystem.dtos.requests.RegisterUserRequest;
import com.salesmgt.salesmgtsystem.dtos.responses.UserResponse;

public interface UserService {
    UserResponse createUser(RegisterUserRequest registerUserRequest);
}
