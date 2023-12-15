package com.bos.server.oauth.repository.authoricationcode.querydsl;

import com.bos.server.oauth.model.entity.Authorization;
import com.bos.server.oauth.model.entity.QAuthorizationCode;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QAuthorizationCodeRepositoryImpl implements QAuthorizationCodeRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Boolean existsByAuthorization(Authorization authorization) {
        return queryFactory.selectOne()
                .from(QAuthorizationCode.authorizationCode)
                .where(QAuthorizationCode.authorizationCode.authorization.eq(authorization))
                .fetchOne() != null;
    }
}
