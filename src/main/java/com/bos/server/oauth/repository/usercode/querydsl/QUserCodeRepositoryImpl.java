package com.bos.server.oauth.repository.usercode.querydsl;

import com.bos.server.oauth.model.entity.Authorization;
import com.bos.server.oauth.model.entity.QAccessToken;
import com.bos.server.oauth.model.entity.QUserCode;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QUserCodeRepositoryImpl implements QUserCodeRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Boolean existsByAuthorization(Authorization authorization) {
        return queryFactory.selectOne()
                .from(QUserCode.userCode)
                .where(QUserCode.userCode.authorization.eq(authorization))
                .fetchOne() != null;
    }
}
