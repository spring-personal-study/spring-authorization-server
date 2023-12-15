package com.bos.server.oauth.repository.accesstoken.querydsl;

import com.bos.server.oauth.model.entity.Authorization;
import com.bos.server.oauth.model.entity.QAccessToken;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QAccessTokenRepositoryImpl implements QAccessTokenRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Boolean existsByAuthorization(Authorization authorization) {
        return queryFactory.selectOne()
                .from(QAccessToken.accessToken)
                .where(QAccessToken.accessToken.authorization.eq(authorization))
                .fetchOne() != null;
    }
}
