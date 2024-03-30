package com.salesmgt.salesmgtsystem.services.client;

import com.salesmgt.salesmgtsystem.dtos.requests.RegisterClientRequest;
import com.salesmgt.salesmgtsystem.dtos.requests.UpdateClientRequest;
import com.salesmgt.salesmgtsystem.dtos.responses.ClientResponse;
import com.salesmgt.salesmgtsystem.exceptions.SalesMgtException;

import java.util.List;

public interface ClientService{
    public List<ClientResponse> findAllClient(int page, int items);

    ClientResponse registerClient(RegisterClientRequest registerClientRequest) throws SalesMgtException;

    ClientResponse getClient(String id) throws SalesMgtException;

    ClientResponse updateClient(String id, UpdateClientRequest updateRequest) throws SalesMgtException;

    void deleteClient(String id) throws SalesMgtException;
}
