package com.salesmgt.salesmgtsystem.controllers;
import com.salesmgt.salesmgtsystem.dtos.requests.RegisterClientRequest;
import com.salesmgt.salesmgtsystem.dtos.requests.UpdateClientRequest;
import com.salesmgt.salesmgtsystem.dtos.responses.ApiResponse;
import com.salesmgt.salesmgtsystem.services.sale.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sale")
@AllArgsConstructor
public class SaleController {
    public final SaleService saleService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createSale(@RequestBody RegisterClientRequest registerClientRequest)  {

        return null;
    }
    @GetMapping
    public ResponseEntity<ApiResponse<?>> findAllSales(
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int items
    )  {
        return null;
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable("id") String id, @RequestBody UpdateClientRequest updateRequest) {

        return null;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") String id) {

        return null;
    }

}
