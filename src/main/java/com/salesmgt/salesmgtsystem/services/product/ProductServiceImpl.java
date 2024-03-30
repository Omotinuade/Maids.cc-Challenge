package com.salesmgt.salesmgtsystem.services.product;

import com.salesmgt.salesmgtsystem.dtos.requests.RegisterProductRequest;
import com.salesmgt.salesmgtsystem.dtos.requests.UpdateProductRequest;
import com.salesmgt.salesmgtsystem.dtos.responses.ProductResponse;
import com.salesmgt.salesmgtsystem.enums.Category;
import com.salesmgt.salesmgtsystem.exceptions.SalesMgtException;
import com.salesmgt.salesmgtsystem.models.Product;
import com.salesmgt.salesmgtsystem.repositories.ProductRepository;
import com.salesmgt.salesmgtsystem.utilities.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.salesmgt.salesmgtsystem.utilities.AppUtils.CLIENT;
import static com.salesmgt.salesmgtsystem.utilities.AppUtils.PRODUCT;
import static com.salesmgt.salesmgtsystem.utilities.ExceptionUtils.NOT_FOUND;
import static com.salesmgt.salesmgtsystem.utilities.ExceptionUtils.REGISTRATION_FAILED;


@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductResponse> findAllProducts(int page, int items) {
        Pageable pageable = AppUtils.buildPageRequest(page, items);
        Page<Product> productPage = productRepository.findAll(pageable);
        List<Product> products = productPage.getContent();
        return products.stream().map(ProductServiceImpl::buildProductResponse).toList();
    }
    public void reduceQuantity(Product product, int quantity) throws SalesMgtException {
        int currentQuantity = product.getAvailableQuantity();
        if (currentQuantity >= quantity) {
            product.setAvailableQuantity(currentQuantity - quantity);
            productRepository.save(product);
        } else {
            throw new SalesMgtException(HttpStatus.BAD_REQUEST, "Insufficient quantity for product with name: " + product.getName());
        }
    }
    @Override
    public ProductResponse createProduct(RegisterProductRequest registerRequest) throws SalesMgtException {
        Product product = new Product();
        product.setName(registerRequest.getName());
        product.setDescription(registerRequest.getDescription());
        product.setInitialQuantity(registerRequest.getInitialQuantity());
        product.setAvailableQuantity(registerRequest.getInitialQuantity());
        product.setCategory(Category.fromString(registerRequest.getCategory()));
        product.setPrice(registerRequest.getPrice());
        Product savedProduct= productRepository.save(product);
        if(savedProduct.getId()==null) throw new SalesMgtException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(REGISTRATION_FAILED,PRODUCT));
        return buildProductResponse(savedProduct);
    }

    @Override
    public ProductResponse getProduct(String id) throws SalesMgtException {
        Optional<Product> foundProduct= productRepository.findById(id);
        foundProduct.orElseThrow(()->new SalesMgtException(HttpStatus.NOT_FOUND, String.format(NOT_FOUND, PRODUCT, id)));
        return buildProductResponse(foundProduct.get());
    }

    @Override
    public ProductResponse updateProduct(String id, UpdateProductRequest updateRequest) throws SalesMgtException {
        Optional<Product> existingProductOptional = productRepository.findById(id);
        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setName(updateRequest.getName());
            existingProduct.setDescription(updateRequest.getDescription());
            existingProduct.setCategory(Category.fromString(updateRequest.getCategory()));
            existingProduct.setAvailableQuantity(updateRequest.getQuantity());
            existingProduct.setPrice(updateRequest.getPrice());
            existingProduct.setLastModifiedDate(LocalDateTime.now());
            Product updatedProduct= productRepository.save(existingProduct);
            return buildProductResponse(updatedProduct);
        } else {
            throw new SalesMgtException(HttpStatus.NOT_FOUND,String.format(NOT_FOUND, PRODUCT, id));
        }
    }

    @Override
    public void deleteProduct(String id) throws SalesMgtException {
        Optional<Product> foundProduct= productRepository.findById(id);
        foundProduct.orElseThrow(()->new SalesMgtException(HttpStatus.NOT_FOUND, String.format(NOT_FOUND, PRODUCT, id)));
        productRepository.deleteById(id);
    }
    private static ProductResponse buildProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .category(product.getCategory())
                .quantity(product.getAvailableQuantity())
                .price(product.getPrice()).build();
    }
}

