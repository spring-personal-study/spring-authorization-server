package com.bos.server.oauth.repository.querydsl;

import com.bos.server.oauth.entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class QAuthorizationRepositoryImpl implements QAuthorizationRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Authorization> findByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValueOrOidcIdTokenValueOrUserCodeValueOrDeviceCodeValue(String token) {
        QAuthorization qAuthorization = QAuthorization.authorization;
        QAuthorizationCode qAuthorizationCode = QAuthorizationCode.authorizationCode;
        QAccessToken qAccessToken = QAccessToken.accessToken;
        QRefreshToken qRefreshToken = QRefreshToken.refreshToken;
        QOidcToken qOidcToken = QOidcToken.oidcToken;
        QUserCode qUserCode = QUserCode.userCode;
        QDeviceCode qDeviceCode = QDeviceCode.deviceCode;

        Authorization authorization = queryFactory.select(qAuthorization)
                .from(qAuthorization)
                .leftJoin(qAuthorization.authorizationCode, qAuthorizationCode).fetchJoin()
                .leftJoin(qAuthorization.accessToken, qAccessToken).fetchJoin()
                .leftJoin(qAuthorization.refreshToken, qRefreshToken).fetchJoin()
                .leftJoin(qAuthorization.oidcToken, qOidcToken).fetchJoin()
                .leftJoin(qAuthorization.userCode, qUserCode).fetchJoin()
                .leftJoin(qAuthorization.deviceCode, qDeviceCode).fetchJoin()
                .where(qAuthorization.state.eq(token)
                        .or(qAuthorizationCode.authorizationCodeValue.eq(token))
                        .or(qAccessToken.tokenValue.eq(token))
                        .or(qRefreshToken.refreshTokenValue.eq(token))
                        .or(qOidcToken.oidcIdTokenValue.eq(token))
                        .or(qUserCode.userCodeValue.eq(token))
                        .or(qDeviceCode.deviceCodeValue.eq(token))
                )
                .fetchOne();

        return Optional.ofNullable(authorization);
    }

    @Override
    public Optional<Authorization> findByAuthorizationCodeValue(String token) {
        Authorization authorization = queryFactory.select(QAuthorization.authorization)
                .from(QAuthorization.authorization)
                .leftJoin(QAuthorization.authorization.authorizationCode, QAuthorizationCode.authorizationCode).fetchJoin()
                .where(QAuthorizationCode.authorizationCode.authorizationCodeValue.eq(token))
                .fetchOne();

        return Optional.ofNullable(authorization);
    }

    @Override
    public Optional<Authorization> findByAccessTokenValue(String token) {
        Authorization authorization = queryFactory.select(QAuthorization.authorization)
                .from(QAuthorization.authorization)
                .leftJoin(QAuthorization.authorization.accessToken, QAccessToken.accessToken).fetchJoin()
                .where(QAccessToken.accessToken.tokenValue.eq(token))
                .fetchOne();

        return Optional.ofNullable(authorization);
    }

    @Override
    public Optional<Authorization> findByRefreshTokenValue(String token) {
        Authorization authorization = queryFactory.select(QAuthorization.authorization)
                .from(QAuthorization.authorization)
                .leftJoin(QAuthorization.authorization.refreshToken, QRefreshToken.refreshToken).fetchJoin()
                .where(QRefreshToken.refreshToken.refreshTokenValue.eq(token))
                .fetchOne();

        return Optional.ofNullable(authorization);
    }

    @Override
    public Optional<Authorization> findByOidcIdTokenValue(String token) {
        Authorization authorization = queryFactory.select(QAuthorization.authorization)
                .from(QAuthorization.authorization)
                .leftJoin(QAuthorization.authorization.oidcToken, QOidcToken.oidcToken).fetchJoin()
                .where(QOidcToken.oidcToken.oidcIdTokenValue.eq(token))
                .fetchOne();

        return Optional.ofNullable(authorization);
    }

    @Override
    public Optional<Authorization> findByUserCodeValue(String token) {
        Authorization authorization = queryFactory.select(QAuthorization.authorization)
                .from(QAuthorization.authorization)
                .leftJoin(QAuthorization.authorization.userCode, QUserCode.userCode).fetchJoin()
                .where(QUserCode.userCode.userCodeValue.eq(token))
                .fetchOne();

        return Optional.ofNullable(authorization);
    }

    @Override
    public Optional<Authorization> findByDeviceCodeValue(String token) {
        Authorization authorization = queryFactory.select(QAuthorization.authorization)
                .from(QAuthorization.authorization)
                .leftJoin(QAuthorization.authorization.deviceCode, QDeviceCode.deviceCode).fetchJoin()
                .where(QDeviceCode.deviceCode.deviceCodeValue.eq(token))
                .fetchOne();

        return Optional.ofNullable(authorization);
    }
}
