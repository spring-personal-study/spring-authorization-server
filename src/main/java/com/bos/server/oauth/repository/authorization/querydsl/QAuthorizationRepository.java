package com.bos.server.oauth.repository.authorization.querydsl;

import com.bos.server.oauth.model.entity.Authorization;

import java.util.Optional;

public interface QAuthorizationRepository {
    Optional<Authorization> findByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValueOrOidcIdTokenValueOrUserCodeValueOrDeviceCodeValue(String token);

    Optional<Authorization> findByAuthorizationCodeValue(String token);

    Optional<Authorization> findByAccessTokenValue(String token);

    Optional<Authorization> findByRefreshTokenValue(String token);

    Optional<Authorization> findByOidcIdTokenValue(String token);

    Optional<Authorization> findByUserCodeValue(String token);

    Optional<Authorization> findByDeviceCodeValue(String token);

    Boolean existsByAuthorizationId(String authorizationId);
}
