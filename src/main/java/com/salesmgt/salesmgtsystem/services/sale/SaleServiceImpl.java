package com.salesmgt.salesmgtsystem.services.sale;

import com.salesmgt.salesmgtsystem.dtos.responses.ProductResponse;
import com.salesmgt.salesmgtsystem.dtos.responses.SalesReport;
import com.salesmgt.salesmgtsystem.exceptions.SalesMgtException;
import com.salesmgt.salesmgtsystem.models.Product;
import com.salesmgt.salesmgtsystem.models.Sale;
import com.salesmgt.salesmgtsystem.models.SaleItem;
import com.salesmgt.salesmgtsystem.repositories.SaleRepository;
import com.salesmgt.salesmgtsystem.services.product.ProductService;
import com.salesmgt.salesmgtsystem.services.product.ProductServiceImpl;
import com.salesmgt.salesmgtsystem.utilities.AppUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.salesmgt.salesmgtsystem.utilities.AppUtils.PRODUCT;
import static com.salesmgt.salesmgtsystem.utilities.AppUtils.SALE;
import static com.salesmgt.salesmgtsystem.utilities.ExceptionUtils.NOT_FOUND;

@Service
@AllArgsConstructor
public class SaleServiceImpl implements SaleService{

    public final SaleRepository saleRepository;

    private final ProductService productService;

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);


    @Override
    public List<Sale> getAllSales(int page, int items) {
        Pageable pageable = AppUtils.buildPageRequest(page, items);
        Page<Sale> salePage= saleRepository.findAll(pageable);
        return salePage.getContent();
    }

    @Override
    public Sale getSaleById(String id) throws SalesMgtException {
        Optional<Sale> sale= saleRepository.findById(id);
        sale.orElseThrow(()->new SalesMgtException(HttpStatus.NOT_FOUND, String.format(NOT_FOUND, SALE, id)));
        return sale.get();
    }

    @Override
    @Transactional
    public Sale createSale(Sale sale) throws SalesMgtException {
        double total = 0;

        for (SaleItem item : sale.getItems()) {
            validateSaleItem(item);
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            productService.reduceQuantity(product, quantity);
            total += quantity * item.getPrice();
        }
        sale.setTotal(total);
        sale.setCreationDate(LocalDateTime.now());
        logger.trace("new sale: {}", sale);
        return saleRepository.save(sale);
    }

    private void validateSaleItem(SaleItem item) throws SalesMgtException {
        if (item.getProduct() == null)throw new SalesMgtException(HttpStatus.BAD_REQUEST, "Product is required for sale item");
        ProductResponse product = productService.getProduct(item.getProduct().getId());
        if (product.getId()==null) throw new SalesMgtException(HttpStatus.NOT_FOUND,String.format(NOT_FOUND, PRODUCT, item.getProduct().getId()));
        if (item.getQuantity() <= 0) {
            throw new SalesMgtException(HttpStatus.BAD_REQUEST, "Quantity must be positive");
        }

    }

    @Override
    public Sale updateSale(String id, Sale sale) {
        return null;
    }

    @Override
    public void deleteSale(String id) throws SalesMgtException {
        Optional<Sale> sale= saleRepository.findById(id);
        sale.orElseThrow(()->new SalesMgtException(HttpStatus.NOT_FOUND, String.format(NOT_FOUND, SALE, id)));
        saleRepository.delete(sale.get());
    }
//
//    @Override
//    public SalesReport generateSalesReport(LocalDateTime startDate, LocalDateTime endDate) {
//        List<Sale> sales = saleRepository.findByCreationDateBetween(startDate, endDate);
//        SalesReport report = new SalesReport();
//        report.setTotalRevenue();
//        report.setTopPerformingSellerId(getTopPerformingSeller(sales));
//        report.setTotalRevenue();
//        report.setNumberOfSales(sales.size());
//        return report;
//    }
//
//    private Long getTopPerformingSeller(List<Sale> sales) {
//        Map<String, Sale> result = sales.stream()
//                .collect(Collectors.groupingBy(sasale -> sale.getSeller().getId()));
//    }
}
