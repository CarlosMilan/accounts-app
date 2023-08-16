package com.mm.accounts.authserver.service;

import com.mm.accounts.authserver.entity.Client;
import com.mm.accounts.authserver.exception.ClientNotFoundException;
import com.mm.accounts.authserver.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientService implements RegisteredClientRepository {

    private final ClientRepository clientRepository;

    public void createClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        //Metodo vacío dado que no se registran clientes por el momento
    }

    @Override
    public RegisteredClient findById(String id) {
        Client client = clientRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        return Client.toRegisteredClient(client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Client client = clientRepository.findByClientId(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        return Client.toRegisteredClient(client);
    }
}
