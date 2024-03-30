package com.salesmgt.salesmgtsystem.controllers;
import com.salesmgt.salesmgtsystem.dtos.requests.RegisterClientRequest;
import com.salesmgt.salesmgtsystem.dtos.requests.UpdateClientRequest;
import com.salesmgt.salesmgtsystem.dtos.responses.ApiResponse;
import com.salesmgt.salesmgtsystem.exceptions.SalesMgtException;
import com.salesmgt.salesmgtsystem.models.Sale;
import com.salesmgt.salesmgtsystem.services.sale.SaleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.salesmgt.salesmgtsystem.utilities.AppUtils.*;

@RestController
@RequestMapping("/api/v1/sale")
@AllArgsConstructor
public class SaleController {
    public final SaleService saleService;

    @GetMapping
    public ResponseEntity<?> getAllSales(
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int items
    ) {
        List<Sale> sales = saleService.getAllSales( page, items);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().
                message(String.format(RETRIEVED_SUCCESS,SALE+"s")).data(sales).success(true).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSaleById(@PathVariable String id) throws SalesMgtException {
        Sale sale = saleService.getSaleById(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().
                message(String.format(RETRIEVED_SUCCESS,SALE)).data(sale).success(true).build());

        }

    @PostMapping
    public ResponseEntity<?> createSale(@Valid @RequestBody Sale sale, BindingResult bindingResult) throws SalesMgtException {
        if (bindingResult.hasErrors()) {
            return handleValidationErrors(bindingResult);
        }
        Sale createdSale = saleService.createSale(sale);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder().
                message(String.format(CREATED_SUCCESS,SALE)).data(createdSale).success(true).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSale(@PathVariable String id, @Valid @RequestBody Sale sale, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return handleValidationErrors(bindingResult);
        }
        Sale updatedSale = saleService.updateSale(id, sale);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().
                message(String.format(UPDATED_SUCCESS,SALE)).data(updatedSale).success(true).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSale(@PathVariable String id) throws SalesMgtException {
        saleService.deleteSale(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().
                message(String.format(DELETED_SUCCESS,SALE)).success(true).build());
    }

    private ResponseEntity<?> handleValidationErrors(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors);
    }
}


