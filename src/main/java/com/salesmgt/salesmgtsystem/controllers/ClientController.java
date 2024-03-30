package com.salesmgt.salesmgtsystem.controllers;

import com.salesmgt.salesmgtsystem.dtos.requests.RegisterClientRequest;
import com.salesmgt.salesmgtsystem.dtos.requests.UpdateClientRequest;
import com.salesmgt.salesmgtsystem.dtos.responses.ApiResponse;
import com.salesmgt.salesmgtsystem.exceptions.SalesMgtException;
import com.salesmgt.salesmgtsystem.services.client.ClientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.salesmgt.salesmgtsystem.utilities.AppUtils.*;

@RestController
@RequestMapping("/api/v1/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;
    @PostMapping
    public ResponseEntity<?> createClient(@Valid @RequestBody RegisterClientRequest registerClientRequest, BindingResult bindingResult) throws SalesMgtException {
        if (bindingResult.hasErrors()) {
            return handleValidationErrors(bindingResult);
        }
       var response = clientService.registerClient(registerClientRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder().
                message(String.format(CREATED_SUCCESS,CLIENT)).data(response).success(true).build());
    }
    @GetMapping
    public ResponseEntity<?> findAllClient(
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int items
    )  {
        var response =  clientService.findAllClient(page, items);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().
                message(String.format(RETRIEVED_SUCCESS,CLIENT+"s")).data(response).success(true).build());

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getClient(@PathVariable("id") String id) throws SalesMgtException {
var response = clientService.getClient(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().
                message(String.format(RETRIEVED_SUCCESS,CLIENT)).data(response).success(true).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable("id") String id, @Valid @RequestBody UpdateClientRequest updateRequest,  BindingResult bindingResult) throws SalesMgtException {
        if (bindingResult.hasErrors()) {
            return handleValidationErrors(bindingResult);
        }
        var response =clientService.updateClient(id, updateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().
                message(String.format(UPDATED_SUCCESS,CLIENT)).data(response).success(true).build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") String id) throws SalesMgtException {
        clientService.deleteClient(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().
                message(String.format(DELETED_SUCCESS,CLIENT)).success(true).build());
    }
    private ResponseEntity<?> handleValidationErrors(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors);
    }

}
