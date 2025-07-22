package com.bos.server.oauth.service;

import com.bos.server.oauth.model.dto.ClientRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final JpaRegisteredClientRepository registeredClientRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerClient(ClientRegistrationDto clientRegistrationDto) {
        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId(clientRegistrationDto.getClientId())
                .clientSecret(passwordEncoder.encode(clientRegistrationDto.getClientSecret()))
                .clientName(clientRegistrationDto.getClientName())
                .clientIdIssuedAt(Instant.now())
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUris(uris -> uris.addAll(Arrays.stream(clientRegistrationDto.getRedirectUris().split(","))
                        .map(String::trim).collect(Collectors.toSet())))
                .scopes(scopes -> scopes.addAll(Arrays.stream(clientRegistrationDto.getScopes().split(","))
                        .map(String::trim).collect(Collectors.toSet())))
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();

        registeredClientRepository.save(registeredClient);
    }
}
