package com.bos.server.oauth.repository.refreshtoken.querydsl;

import com.bos.server.oauth.model.entity.Authorization;

public interface QRefreshTokenRepository {

    Boolean existsByAuthorization(Authorization authorization);
}
