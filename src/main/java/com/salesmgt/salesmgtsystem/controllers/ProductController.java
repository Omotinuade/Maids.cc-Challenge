package com.salesmgt.salesmgtsystem.controllers;

import com.salesmgt.salesmgtsystem.dtos.requests.RegisterProductRequest;
import com.salesmgt.salesmgtsystem.dtos.requests.UpdateProductRequest;
import com.salesmgt.salesmgtsystem.dtos.responses.ApiResponse;
import com.salesmgt.salesmgtsystem.exceptions.SalesMgtException;
import com.salesmgt.salesmgtsystem.services.product.ProductService;
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
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody RegisterProductRequest registerRequest, BindingResult bindingResult) throws SalesMgtException {
        if (bindingResult.hasErrors()) {
            return handleValidationErrors(bindingResult);
        }
        var response = productService.createProduct(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder().
                message(String.format(CREATED_SUCCESS,PRODUCT)).data(response).success(true).build());
    }
    @GetMapping
    public ResponseEntity<?> findAllProduct(
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int items
    )  {
        var response =  productService.findAllProducts(page, items);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().
                message(String.format(RETRIEVED_SUCCESS,PRODUCT+"s")).data(response).success(true).build());
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") String id, @Valid @RequestBody UpdateProductRequest updateRequest, BindingResult bindingResult) throws SalesMgtException {
        if (bindingResult.hasErrors()) {
            return handleValidationErrors(bindingResult);
        }
        var response =productService.updateProduct(id, updateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().
                message(String.format(UPDATED_SUCCESS,PRODUCT)).data(response).success(true).build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") String id) throws SalesMgtException {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().
                message(String.format(DELETED_SUCCESS,PRODUCT)).success(true).build());
    }
    private ResponseEntity<?> handleValidationErrors(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors);
    }

}
