package com.salesmgt.salesmgtsystem.services.client;

import com.salesmgt.salesmgtsystem.dtos.requests.RegisterClientRequest;
import com.salesmgt.salesmgtsystem.dtos.requests.UpdateClientRequest;
import com.salesmgt.salesmgtsystem.dtos.responses.ClientResponse;
import com.salesmgt.salesmgtsystem.exceptions.SalesMgtException;
import com.salesmgt.salesmgtsystem.models.Client;
import com.salesmgt.salesmgtsystem.repositories.ClientRepository;
import com.salesmgt.salesmgtsystem.services.product.ProductServiceImpl;
import com.salesmgt.salesmgtsystem.utilities.AppUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.salesmgt.salesmgtsystem.utilities.AppUtils.CLIENT;
import static com.salesmgt.salesmgtsystem.utilities.ExceptionUtils.*;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public List<ClientResponse> findAllClient(int page, int items) {
        Pageable pageable = AppUtils.buildPageRequest(page, items);
        Page<Client> clientsPage = clientRepository.findAll(pageable);
        List<Client> clients = clientsPage.getContent();
        return clients.stream().map(ClientServiceImpl::buildClientResponse).toList();
    }

    @Override
    public ClientResponse registerClient(RegisterClientRequest registerClientRequest) throws SalesMgtException {
        checkIfClientExists(registerClientRequest.getEmail());
        Client client = new Client();
        client.setName(registerClientRequest.getName());
        client.setLastName(registerClientRequest.getLastName());
        client.setMobile(registerClientRequest.getMobile());
        client.setAddress(registerClientRequest.getAddress());
        client.setEmail(registerClientRequest.getEmail());
       var newClient = clientRepository.save(client);
       if (newClient.getId() == null) throw new SalesMgtException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(REGISTRATION_FAILED,CLIENT));
        return buildClientResponse(newClient);
    }

    @Override
    public ClientResponse getClient(String id) throws SalesMgtException {
        Optional<Client> foundClient= clientRepository.findById(id);
        foundClient.orElseThrow(()->new SalesMgtException(HttpStatus.NOT_FOUND, String.format(NOT_FOUND, CLIENT, id)));
        return buildClientResponse(foundClient.get());
    }

    @Override
    public ClientResponse updateClient(String id, UpdateClientRequest updateRequest) throws SalesMgtException {
        Optional<Client> existingClientOptional = clientRepository.findById(id);
        if (existingClientOptional.isPresent()) {
            Client existingClient = existingClientOptional.get();
            existingClient.setName(updateRequest.getName());
            existingClient.setLastName(updateRequest.getLastName());
            existingClient.setMobile(updateRequest.getMobile());
            existingClient.setEmail(updateRequest.getEmail());
            existingClient.setAddress(updateRequest.getAddress());
            Client updatedClient= clientRepository.save(existingClient);
            logger.trace("Updated client {}", updatedClient);
            return buildClientResponse(updatedClient);
        } else {
            throw new SalesMgtException(HttpStatus.NOT_FOUND, String.format(NOT_FOUND, CLIENT, id));
        }
    }

    @Override
    public void deleteClient(String id) throws SalesMgtException {
        Optional<Client> foundClient= clientRepository.findById(id);
        foundClient.orElseThrow(()->new SalesMgtException(HttpStatus.NOT_FOUND, String.format(NOT_FOUND, CLIENT, id)));
        clientRepository.delete(foundClient.get());
    }

    private void checkIfClientExists(String emailAddress) throws SalesMgtException {
        Optional<Client> existingClient = clientRepository.findByEmail(emailAddress);
        if (existingClient.isPresent()) {
            throw new SalesMgtException(HttpStatus.BAD_REQUEST,String.format(ALREADY_EXISTING,CLIENT,emailAddress));
        }
    }

    private static ClientResponse buildClientResponse(Client client) {
        return ClientResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .mobile(client.getMobile())
                .address(client.getAddress()).build();
    }
}
