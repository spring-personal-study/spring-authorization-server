package com.bos.server.oauth.repository.refreshtoken.querydsl;

import com.bos.server.oauth.model.entity.Authorization;
import com.bos.server.oauth.model.entity.QAccessToken;
import com.bos.server.oauth.model.entity.QRefreshToken;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QRefreshTokenRepositoryImpl implements QRefreshTokenRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Boolean existsByAuthorization(Authorization authorization) {
        return queryFactory.selectOne()
                .from(QRefreshToken.refreshToken)
                .where(QRefreshToken.refreshToken.authorization.eq(authorization))
                .fetchOne() != null;
    }
}
