package com.bos.server.oauth.repository.oidctoken.querydsl;

import com.bos.server.oauth.model.entity.Authorization;
import com.bos.server.oauth.model.entity.QAccessToken;
import com.bos.server.oauth.model.entity.QOidcToken;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QOidcTokenRepositoryImpl implements QOidcTokenRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Boolean existsByAuthorization(Authorization authorization) {
        return queryFactory.selectOne()
                .from(QOidcToken.oidcToken)
                .where(QOidcToken.oidcToken.authorization.eq(authorization))
                .fetchOne() != null;
    }
}
