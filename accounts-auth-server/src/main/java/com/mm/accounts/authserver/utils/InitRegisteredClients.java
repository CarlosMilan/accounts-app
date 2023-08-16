package com.mm.accounts.authserver.utils;

import com.mm.accounts.authserver.entity.Client;
import com.mm.accounts.authserver.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@Component
@AllArgsConstructor
public class InitRegisteredClients implements ApplicationRunner {

    private final ClientService registeredClientRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        Client client = Client.builder()
                .id(UUID.randomUUID())
                .clientId("admin")
                .clientSecret(passwordEncoder.encode("12345"))
                .authenticationMethods(Set.of(ClientAuthenticationMethod.CLIENT_SECRET_BASIC))
                .authorizationGrantTypes(Set.of(
                        AuthorizationGrantType.AUTHORIZATION_CODE,
                        AuthorizationGrantType.CLIENT_CREDENTIALS,
                        AuthorizationGrantType.REFRESH_TOKEN))
                .redirectUris(Set.of("http://localhost:9001"))
                .scopes( Set.of("openid", "email", "profile"))
                .requireProofKey(true)
                .build();
        registeredClientRepository.createClient(client);
    }
}
