package com.bos.server.config;

import com.bos.server.oauth.authentication.CustomAuthenticationProvider;
import com.bos.server.oauth.authentication.PrincipalDetails;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.security.core.GrantedAuthority;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;
import java.util.stream.Collectors;


import static com.bos.server.config.CustomClientMetadataConfig.configureCustomClientMetadataConverters;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.util.StringUtils.hasText;

@Configuration
@RequiredArgsConstructor
public class AuthorizationServerConfig {

    private final CustomAuthenticationProvider customAuthenticationProvider;

    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(oidc -> oidc.clientRegistrationEndpoint(
                        clientRegistrationEndpoint ->
                                clientRegistrationEndpoint.authenticationProviders(configureCustomClientMetadataConverters())))// Enable OpenID Connect 1.0
                .authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint
                        .authenticationProvider(customAuthenticationProvider)
                        .authorizationResponseHandler((request, response, authentication) -> {
                                    OAuth2AuthorizationCodeRequestAuthenticationToken auth = (OAuth2AuthorizationCodeRequestAuthenticationToken) authentication;
                                    //String redirectUri = auth.getRedirectUri();
                                    //String authorizationCode = auth.getAuthorizationCode().getTokenValue();
                                    if (hasText(auth.getState())) {
                                        response.sendRedirect(auth.getRedirectUri() + "?code=" + auth.getAuthorizationCode().getTokenValue() + "&state=" + auth.getState());
                                        return;
                                    }
                                    response.sendRedirect(auth.getRedirectUri() + "?code=" + auth.getAuthorizationCode().getTokenValue());
                                }
                        )
                        .errorResponseHandler((request, response, exception) -> response.sendError(HttpServletResponse.SC_BAD_REQUEST)));
        // Redirect to the login page when not authenticated from the
        // authorization endpoint
        http.exceptionHandling((exceptions) -> exceptions.defaultAuthenticationEntryPointFor(
                new LoginUrlAuthenticationEntryPoint("/login"),
                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)));

        // Accept access tokens for User Info and/or Client Registration
        http.oauth2ResourceServer((oauth2) -> oauth2.jwt(withDefaults()));
        return http.build();
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }

   /* @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient registrarClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("bluebird-default-client")
                .clientSecret("{bcrypt}$2a$12$tDI3S/mBdAYYEy3O.tHvFuHrsDz..QrDNtcMaQPDlLHI23tkkp4dO")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://localhost:9000")
                .redirectUri("http://localhost:9000/login/oauth2/code/bluebird-client-oidc")
                .scope("client.create")
                .scope("client.read")
                .build();

        return new InMemoryRegisteredClientRepository(registrarClient);
    }*/

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

/*    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer() {
        return context -> {
            Authentication principal = context.getPrincipal();

            if (context.getTokenType().equals(OAuth2TokenType.ACCESS_TOKEN)) {
                PrincipalDetails details = (PrincipalDetails) principal.getPrincipal();

                context.getClaims()
                        .claim("id", details.getResourceOwner().getId())
                        .claim("authorities", details.getAuthorities()
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toSet()));
            }
        };
    }

    @Bean
    OAuth2TokenGenerator<?> tokenGenerator(JWKSource<SecurityContext> jwkSource) {
        JwtGenerator jwtGenerator = new JwtGenerator(new NimbusJwtEncoder(jwkSource));
        jwtGenerator.setJwtCustomizer(tokenCustomizer());
        OAuth2AccessTokenGenerator accessTokenGenerator = new OAuth2AccessTokenGenerator();
        OAuth2RefreshTokenGenerator refreshTokenGenerator = new OAuth2RefreshTokenGenerator();
        return new DelegatingOAuth2TokenGenerator(
                jwtGenerator, accessTokenGenerator, refreshTokenGenerator);
    }*/
}