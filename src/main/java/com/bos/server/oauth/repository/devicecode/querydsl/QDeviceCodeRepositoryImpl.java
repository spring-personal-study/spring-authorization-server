package com.bos.server.oauth.repository.devicecode.querydsl;

import com.bos.server.oauth.model.entity.Authorization;
import com.bos.server.oauth.model.entity.QAccessToken;
import com.bos.server.oauth.model.entity.QDeviceCode;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QDeviceCodeRepositoryImpl implements QDeviceCodeRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Boolean existsByAuthorization(Authorization authorization) {
        return queryFactory.selectOne()
                .from(QDeviceCode.deviceCode)
                .where(QDeviceCode.deviceCode.authorization.eq(authorization))
                .fetchOne() != null;
    }
}
