package com.bos.server.oauth.service;

import com.bos.server.oauth.model.entity.AuthorizationConsent;
import com.bos.server.oauth.repository.authorizationconsent.AuthorizationConsentRepository;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

@Component
public class JpaOAuth2AuthorizationConsentService implements OAuth2AuthorizationConsentService {
    private final AuthorizationConsentRepository authorizationConsentRepository;
    private final RegisteredClientRepository jpaRegisteredClientRepository;

    public JpaOAuth2AuthorizationConsentService(AuthorizationConsentRepository authorizationConsentRepository, RegisteredClientRepository jpaRegisteredClientRepository) {
        this.authorizationConsentRepository = authorizationConsentRepository;
        this.jpaRegisteredClientRepository = jpaRegisteredClientRepository;
    }

    @Override
    public void save(OAuth2AuthorizationConsent authorizationConsent) {
        this.authorizationConsentRepository.save(toEntity(authorizationConsent));
    }

    @Override
    public void remove(OAuth2AuthorizationConsent authorizationConsent) {
        this.authorizationConsentRepository.deleteByRegisteredClientIdAndPrincipalName(
                authorizationConsent.getRegisteredClientId(), authorizationConsent.getPrincipalName());
    }

    @Override
    public OAuth2AuthorizationConsent findById(String registeredClientId, String principalName) {
        return this.authorizationConsentRepository.findByRegisteredClientIdAndPrincipalName(
                registeredClientId, principalName).map(this::toObject).orElse(null);
    }

    private OAuth2AuthorizationConsent toObject(AuthorizationConsent authorizationConsent) {
        String registeredClientId = authorizationConsent.getRegisteredClientId();
        RegisteredClient registeredClient = this.jpaRegisteredClientRepository.findById(registeredClientId);
        if (registeredClient == null) {
            throw new DataRetrievalFailureException(
                    "The RegisteredClient with id '" + registeredClientId + "' was not found in the RegisteredClientRepository.");
        }

        OAuth2AuthorizationConsent.Builder builder = OAuth2AuthorizationConsent.withId(
                registeredClientId, authorizationConsent.getPrincipalName()
        );
        if (authorizationConsent.getAuthorities() != null) {
            for (String authority : StringUtils.commaDelimitedListToSet(authorizationConsent.getAuthorities())) {
                builder.authority(new SimpleGrantedAuthority(authority));
            }
        }

        return builder.build();
    }

    private AuthorizationConsent toEntity(OAuth2AuthorizationConsent authorizationConsent) {
       Set<String> authorities = new HashSet<>();
        for (GrantedAuthority authority : authorizationConsent.getAuthorities()) {
            authorities.add(authority.getAuthority());
        }
        return new AuthorizationConsent(
                authorizationConsent.getRegisteredClientId(),
                authorizationConsent.getPrincipalName(),
                StringUtils.collectionToCommaDelimitedString(authorities)
        );
    }
}