package com.salesmgt.salesmgtsystem.services.product;

import com.salesmgt.salesmgtsystem.dtos.requests.RegisterClientRequest;
import com.salesmgt.salesmgtsystem.dtos.requests.RegisterProductRequest;
import com.salesmgt.salesmgtsystem.dtos.requests.UpdateClientRequest;
import com.salesmgt.salesmgtsystem.dtos.requests.UpdateProductRequest;
import com.salesmgt.salesmgtsystem.dtos.responses.ClientResponse;
import com.salesmgt.salesmgtsystem.dtos.responses.ProductResponse;
import com.salesmgt.salesmgtsystem.exceptions.SalesMgtException;
import com.salesmgt.salesmgtsystem.models.Product;

import java.util.List;

public interface ProductService {
    public List<ProductResponse> findAllProducts(int page, int items);

    ProductResponse createProduct(RegisterProductRequest registerRequest) throws SalesMgtException;

    ProductResponse getProduct(String id) throws SalesMgtException;
    void reduceQuantity(Product product, int quantity) throws SalesMgtException;

    ProductResponse updateProduct(String id, UpdateProductRequest updateRequest) throws SalesMgtException;

    void deleteProduct(String id) throws SalesMgtException;
}
