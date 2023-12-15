package com.bos.server.oauth.service;

import com.bos.server.oauth.model.entity.*;
import com.bos.server.oauth.repository.accesstoken.AccessTokenRepository;
import com.bos.server.oauth.repository.authoricationcode.AuthorizationCodeRepository;
import com.bos.server.oauth.repository.authorization.AuthorizationRepository;
import com.bos.server.oauth.repository.devicecode.DeviceCodeRepository;
import com.bos.server.oauth.repository.oidctoken.OidcTokenRepository;
import com.bos.server.oauth.repository.refreshtoken.RefreshTokenRepository;
import com.bos.server.oauth.repository.usercode.UserCodeRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationCode;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.jackson2.OAuth2AuthorizationServerJackson2Module;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class JpaOAuth2AuthorizationService implements OAuth2AuthorizationService {
    private final AuthorizationRepository authorizationRepository;
    private final RegisteredClientRepository jpaRegisteredClientRepository;
    private final AuthorizationCodeRepository authorizationCodeRepository;
    private final AccessTokenRepository accessTokenRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final OidcTokenRepository oidcTokenRepository;
    private final DeviceCodeRepository deviceCodeRepository;
    private final UserCodeRepository userCodeRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public JpaOAuth2AuthorizationService(
            AuthorizationRepository authorizationRepository,
            RegisteredClientRepository registeredClientRepository,
            AuthorizationCodeRepository authorizationCodeRepository,
            AccessTokenRepository accessTokenRepository,
            RefreshTokenRepository refreshTokenRepository,
            OidcTokenRepository oidcTokenRepository,
            DeviceCodeRepository deviceCodeRepository,
            UserCodeRepository userCodeRepository
    ) {
        this.authorizationRepository = authorizationRepository;
        this.jpaRegisteredClientRepository = registeredClientRepository;

        ClassLoader classLoader = JpaOAuth2AuthorizationService.class.getClassLoader();
        List<Module> securityModules = SecurityJackson2Modules.getModules(classLoader);
        this.objectMapper.registerModules(securityModules);
        this.objectMapper.registerModule(new OAuth2AuthorizationServerJackson2Module());

        this.authorizationCodeRepository = authorizationCodeRepository;
        this.accessTokenRepository = accessTokenRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.oidcTokenRepository = oidcTokenRepository;
        this.deviceCodeRepository = deviceCodeRepository;
        this.userCodeRepository = userCodeRepository;
    }


    @Override
    public void save(OAuth2Authorization authorization) {
        if (authorization == null) throw new IllegalArgumentException("authorization cannot be null");
        boolean isAuthorizationExist = authorizationRepository.existsByAuthorizationId(authorization.getId());
        Authorization authorizationEntity = toAuthEntity(authorization);
        if (!isAuthorizationExist) {
            authorizationRepository.save(authorizationEntity);
            authorizationRepository.flush();
        }
        AuthorizationCode authorizationCode = toAuthCodeEntity(authorization, authorizationEntity);
        AccessToken accessToken = toAccessTokenEntity(authorization, authorizationEntity);
        RefreshToken refreshToken = toRefreshTokenEntity(authorization, authorizationEntity);
        OidcToken oidcToken = toOidcTokenEntity(authorization, authorizationEntity);
        UserCode userCode = toUserCodeEntity(authorization, authorizationEntity);
        DeviceCode deviceCode = toDeviceCodeEntity(authorization, authorizationEntity);

        Boolean isAuthorizationCodeExist = authorizationCodeRepository.existsByAuthorization(authorizationEntity);
        Boolean isAccessTokenExist = accessTokenRepository.existsByAuthorization(authorizationEntity);
        Boolean isRefreshTokenExist = refreshTokenRepository.existsByAuthorization(authorizationEntity);
        Boolean isOidcTokenExist = oidcTokenRepository.existsByAuthorization(authorizationEntity);
        Boolean isUserCodeExist = userCodeRepository.existsByAuthorization(authorizationEntity);
        Boolean isDeviceCodeExist = deviceCodeRepository.existsByAuthorization(authorizationEntity);

        if (!isAuthorizationCodeExist && authorizationCode != null) authorizationCodeRepository.save(authorizationCode);
        if (!isAccessTokenExist && accessToken != null) accessTokenRepository.save(accessToken);
        if (!isRefreshTokenExist && refreshToken != null) refreshTokenRepository.save(refreshToken);
        if (!isOidcTokenExist && oidcToken != null) oidcTokenRepository.save(oidcToken);
        if (!isUserCodeExist && userCode != null) userCodeRepository.save(userCode);
        if (!isDeviceCodeExist && deviceCode != null) deviceCodeRepository.save(deviceCode);
    }

    @Override
    public void remove(OAuth2Authorization authorization) {
        this.authorizationRepository.deleteById(authorization.getId());
    }

    @Override
    public OAuth2Authorization findById(String id) {
        return this.authorizationRepository.findById(id)
                .map(this::toObject)
                .orElse(null);
    }

    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
        Optional<Authorization> result;
        if (tokenType == null) {
            result = this.authorizationRepository.findByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValueOrOidcIdTokenValueOrUserCodeValueOrDeviceCodeValue(token);
        } else {
            switch (tokenType.getValue()) {
                case OAuth2ParameterNames.STATE -> result = this.authorizationRepository.findByState(token);
                case OAuth2ParameterNames.CODE ->
                        result = this.authorizationRepository.findByAuthorizationCodeValue(token);
                case OAuth2ParameterNames.ACCESS_TOKEN ->
                        result = this.authorizationRepository.findByAccessTokenValue(token);
                case OAuth2ParameterNames.REFRESH_TOKEN ->
                        result = this.authorizationRepository.findByRefreshTokenValue(token);
                case OidcParameterNames.ID_TOKEN -> result = this.authorizationRepository.findByOidcIdTokenValue(token);
                case OAuth2ParameterNames.USER_CODE -> result = this.authorizationRepository.findByUserCodeValue(token);
                case OAuth2ParameterNames.DEVICE_CODE ->
                        result = this.authorizationRepository.findByDeviceCodeValue(token);
                default -> result = Optional.empty();
            }
        }

        return result.map(this::toObject).orElse(null);
    }

    private OAuth2Authorization toObject(Authorization entity) {
        RegisteredClient registeredClient = this.jpaRegisteredClientRepository.findById(entity.getRegisteredClientId());
        if (registeredClient == null) {
            throw new DataRetrievalFailureException("The RegisteredClient with id '" + entity.getRegisteredClientId() + "' was not found in the RegisteredClientRepository.");
        }

        OAuth2Authorization.Builder builder = OAuth2Authorization.withRegisteredClient(registeredClient)
                .id(entity.getId())
                .principalName(entity.getPrincipalName())
                .authorizationGrantType(resolveAuthorizationGrantType(entity.getAuthorizationGrantType()))
                .authorizedScopes(StringUtils.commaDelimitedListToSet(entity.getAuthorizedScopes()))
                .attributes(attributes -> attributes.putAll(parseMap(entity.getAttributes())));

        if (entity.getState() != null) {
            builder.attribute(OAuth2ParameterNames.STATE, entity.getState());
        }

        if (entity.getAuthorizationCode() != null && entity.getAuthorizationCode().getAuthorizationCodeValue() != null) {
            OAuth2AuthorizationCode authorizationCode = new OAuth2AuthorizationCode(
                    entity.getAuthorizationCode().getAuthorizationCodeValue(),
                    entity.getAuthorizationCode().getAuthorizationCodeIssuedAt(),
                    entity.getAuthorizationCode().getAuthorizationCodeExpiresAt());
            builder.token(authorizationCode, metadata -> metadata.putAll(parseMap(entity.getAuthorizationCode().getAuthorizationCodeMetadata())));
        }

        if (entity.getAccessToken() != null && entity.getAccessToken().getTokenValue() != null) {
            OAuth2AccessToken accessToken = new OAuth2AccessToken(
                    OAuth2AccessToken.TokenType.BEARER,
                    entity.getAccessToken().getTokenValue(),
                    entity.getAccessToken().getTokenIssuedAt(),
                    entity.getAccessToken().getTokenExpiresAt(),
                    StringUtils.commaDelimitedListToSet(entity.getAccessToken().getTokenScopes()));
            builder.token(accessToken, metadata -> metadata.putAll(parseMap(entity.getAccessToken().getTokenMetadata())));
        }

        if (entity.getRefreshToken() != null && entity.getRefreshToken().getRefreshTokenValue() != null) {
            OAuth2RefreshToken refreshToken = new OAuth2RefreshToken(
                    entity.getRefreshToken().getRefreshTokenValue(),
                    entity.getRefreshToken().getRefreshTokenIssuedAt(),
                    entity.getRefreshToken().getRefreshTokenExpiresAt());
            builder.token(refreshToken, metadata -> metadata.putAll(parseMap(entity.getRefreshToken().getRefreshTokenMetadata())));
        }

        if (entity.getOidcToken() != null && entity.getOidcToken().getOidcIdTokenValue() != null) {
            OidcIdToken idToken = new OidcIdToken(
                    entity.getOidcToken().getOidcIdTokenValue(),
                    entity.getOidcToken().getOidcIdTokenIssuedAt(),
                    entity.getOidcToken().getOidcIdTokenExpiresAt(),
                    parseMap(entity.getOidcToken().getOidcIdTokenClaims()));
            builder.token(idToken, metadata -> metadata.putAll(parseMap(entity.getOidcToken().getOidcIdTokenMetadata())));
        }

        if (entity.getUserCode() != null && entity.getUserCode().getUserCodeValue() != null) {
            OAuth2UserCode userCode = new OAuth2UserCode(
                    entity.getUserCode().getUserCodeValue(),
                    entity.getUserCode().getUserCodeIssuedAt(),
                    entity.getUserCode().getUserCodeExpiresAt());
            builder.token(userCode, metadata -> metadata.putAll(parseMap(entity.getUserCode().getUserCodeMetadata())));
        }

        if (entity.getDeviceCode() != null && entity.getDeviceCode().getDeviceCodeValue() != null) {
            OAuth2DeviceCode deviceCode = new OAuth2DeviceCode(
                    entity.getDeviceCode().getDeviceCodeValue(),
                    entity.getDeviceCode().getDeviceCodeIssuedAt(),
                    entity.getDeviceCode().getDeviceCodeExpiresAt());
            builder.token(deviceCode, metadata -> metadata.putAll(parseMap(entity.getDeviceCode().getDeviceCodeMetadata())));
        }

        return builder.build();
    }

    private Authorization toAuthEntity(OAuth2Authorization authorization) {
        return new Authorization(
                authorization.getId(),
                authorization.getRegisteredClientId(),
                authorization.getPrincipalName(),
                authorization.getAuthorizationGrantType().getValue(),
                StringUtils.collectionToDelimitedString(authorization.getAuthorizedScopes(), ","),
                writeMap(authorization.getAttributes()),
                authorization.getAttribute(OAuth2ParameterNames.STATE)
        );
    }

    private AuthorizationCode toAuthCodeEntity(OAuth2Authorization authorization, Authorization authorizationEntity) {
        OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode = authorization.getToken(OAuth2AuthorizationCode.class);
        if (authorizationCode == null) return null;
        OAuth2Token token = authorizationCode.getToken();
        return new AuthorizationCode(
                token.getTokenValue(),
                token.getIssuedAt(),
                token.getExpiresAt(),
                writeMap(authorizationCode.getMetadata()),
                authorizationEntity
        );
    }

    private AccessToken toAccessTokenEntity(OAuth2Authorization authorization, Authorization authorizationEntity) {
        OAuth2Authorization.Token<OAuth2AccessToken> accessToken = authorization.getToken(OAuth2AccessToken.class);
        if (accessToken == null) return null;
        OAuth2Token token = accessToken.getToken();
        AccessToken accessTokenEntity = new AccessToken(
                token.getTokenValue(),
                token.getIssuedAt(),
                token.getExpiresAt(),
                writeMap(accessToken.getMetadata()),
                authorizationEntity
        );

        if (accessToken.getToken().getScopes() != null) {
            accessTokenEntity.setTokenScopes(StringUtils.collectionToDelimitedString(accessToken.getToken().getScopes(), ","));
        }
        return accessTokenEntity;
    }

    private RefreshToken toRefreshTokenEntity(OAuth2Authorization authorization, Authorization authorizationEntity) {
        OAuth2Authorization.Token<OAuth2RefreshToken> refreshToken = authorization.getToken(OAuth2RefreshToken.class);
        if (refreshToken == null) return null;
        OAuth2Token token = refreshToken.getToken();
        return new RefreshToken(
                token.getTokenValue(),
                token.getIssuedAt(),
                token.getExpiresAt(),
                writeMap(refreshToken.getMetadata()),
                authorizationEntity
        );
    }

    private OidcToken toOidcTokenEntity(OAuth2Authorization authorization, Authorization authorizationEntity) {
        OAuth2Authorization.Token<OidcIdToken> oidcIdToken = authorization.getToken(OidcIdToken.class);
        if (oidcIdToken == null) return null;
        OAuth2Token token = oidcIdToken.getToken();
        return new OidcToken(
                token.getTokenValue(),
                token.getIssuedAt(),
                token.getExpiresAt(),
                writeMap(oidcIdToken.getMetadata()),
                writeMap(oidcIdToken.getClaims()),
                authorizationEntity
        );
    }

    private UserCode toUserCodeEntity(OAuth2Authorization authorization, Authorization authorizationEntity) {
        OAuth2Authorization.Token<OAuth2UserCode> userCode = authorization.getToken(OAuth2UserCode.class);
        if (userCode == null) return null;
        OAuth2Token token = userCode.getToken();
        return new UserCode(
                token.getTokenValue(),
                token.getIssuedAt(),
                token.getExpiresAt(),
                writeMap(userCode.getMetadata()),
                authorizationEntity
        );
    }

    private DeviceCode toDeviceCodeEntity(OAuth2Authorization authorization, Authorization authorizationEntity) {
        OAuth2Authorization.Token<OAuth2DeviceCode> deviceCode = authorization.getToken(OAuth2DeviceCode.class);
        if (deviceCode == null) return null;
        OAuth2Token token = deviceCode.getToken();
        return new DeviceCode(
                token.getTokenValue(),
                token.getIssuedAt(),
                token.getExpiresAt(),
                writeMap(deviceCode.getMetadata()),
                authorizationEntity
        );
    }

    private Map<String, Object> parseMap(String data) {
        try {
            this.objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
            return this.objectMapper.readValue(data, new TypeReference<>() {
            });
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage(), ex);
        }
    }

    private String writeMap(Map<String, Object> metadata) {
        try {
            return this.objectMapper.writeValueAsString(metadata);
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage(), ex);
        }
    }

    private static AuthorizationGrantType resolveAuthorizationGrantType(String authorizationGrantType) {
        if (AuthorizationGrantType.AUTHORIZATION_CODE.getValue().equals(authorizationGrantType)) {
            return AuthorizationGrantType.AUTHORIZATION_CODE;
        } else if (AuthorizationGrantType.CLIENT_CREDENTIALS.getValue().equals(authorizationGrantType)) {
            return AuthorizationGrantType.CLIENT_CREDENTIALS;
        } else if (AuthorizationGrantType.REFRESH_TOKEN.getValue().equals(authorizationGrantType)) {
            return AuthorizationGrantType.REFRESH_TOKEN;
        } else if (AuthorizationGrantType.DEVICE_CODE.getValue().equals(authorizationGrantType)) {
            return AuthorizationGrantType.DEVICE_CODE;
        }
        return new AuthorizationGrantType(authorizationGrantType);
    }
}