package com.salesmgt.salesmgtsystem.services.sale;

import com.salesmgt.salesmgtsystem.exceptions.SalesMgtException;
import com.salesmgt.salesmgtsystem.models.Sale;

import java.util.List;

public interface SaleService {
    List<Sale> getAllSales(int page, int items);
    Sale getSaleById(String id) throws SalesMgtException;
    Sale createSale(Sale sale) throws SalesMgtException;
    Sale updateSale(String id, Sale sale);
    void deleteSale(String id) throws SalesMgtException;
}
