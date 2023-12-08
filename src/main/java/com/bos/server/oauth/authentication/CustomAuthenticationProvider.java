package com.bos.server.oauth.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationProvider;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final RegisteredClientRepository registeredClientRepository;
    private final OAuth2AuthorizationService oAuth2AuthorizationService;
    private final OAuth2AuthorizationConsentService oAuth2AuthorizationConsentService;

    @Override
    public Authentication authenticate(Authentication authentication) {

        OAuth2AuthorizationCodeRequestAuthenticationToken authorizationCodeRequestAuthentication =
                (OAuth2AuthorizationCodeRequestAuthenticationToken) authentication;

        AuthenticationProvider authenticationProvider
                = new OAuth2AuthorizationCodeRequestAuthenticationProvider(registeredClientRepository, oAuth2AuthorizationService, oAuth2AuthorizationConsentService);
        AbstractAuthenticationToken authenticate
                = (AbstractAuthenticationToken) authenticationProvider.authenticate(authorizationCodeRequestAuthentication);

        Authentication principal = (Authentication) authorizationCodeRequestAuthentication.getPrincipal();
        System.out.println("principal = " + principal);

        return authenticate;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OAuth2AuthorizationCodeRequestAuthenticationToken.class.isAssignableFrom(authentication);
    }
}