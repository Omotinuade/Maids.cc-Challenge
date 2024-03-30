package com.salesmgt.salesmgtsystem.controllers;

import com.salesmgt.salesmgtsystem.dtos.requests.RegisterClientRequest;
import com.salesmgt.salesmgtsystem.dtos.requests.RegisterUserRequest;
import com.salesmgt.salesmgtsystem.dtos.requests.UpdateClientRequest;
import com.salesmgt.salesmgtsystem.dtos.requests.UpdateUserRequest;
import com.salesmgt.salesmgtsystem.dtos.responses.ApiResponse;
import com.salesmgt.salesmgtsystem.services.user.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class UserController {
    public final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody RegisterUserRequest registerUserRequest)  {
        userService.createUser(registerUserRequest);
        return null;
    }
    @GetMapping("/users")
    public ResponseEntity<ApiResponse<?>> findAllUser(
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int items
    )  {
        return null;
    }
    @PatchMapping("/users/:id")
    public ResponseEntity<?> updateUser(@PathVariable("id") String id, @RequestBody UpdateUserRequest updateRequest) {

        return null;
    }
    @DeleteMapping("/users/:id")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {

        return null;
    }

}
