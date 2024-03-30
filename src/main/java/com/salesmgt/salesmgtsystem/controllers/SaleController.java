package com.salesmgt.salesmgtsystem.controllers;
import com.salesmgt.salesmgtsystem.dtos.requests.RegisterClientRequest;
import com.salesmgt.salesmgtsystem.dtos.requests.UpdateClientRequest;
import com.salesmgt.salesmgtsystem.dtos.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class SaleController {

    @PostMapping("/sale")
    public ResponseEntity<ApiResponse<?>> createSale(@RequestBody RegisterClientRequest registerClientRequest)  {

        return null;
    }
    @GetMapping("/sales")
    public ResponseEntity<ApiResponse<?>> findAllSales(
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int items
    )  {
        return null;
    }
    @PatchMapping("/sales/:id")
    public ResponseEntity<?> updateClient(@PathVariable("id") String id, @RequestBody UpdateClientRequest updateRequest) {

        return null;
    }
    @GetMapping("/sales/report")
    public ResponseEntity<?> deleteClient(@PathVariable("id") String id) {

        return null;
    }

//    GET /sales/report: Generate sales report for a specific date range.
}
