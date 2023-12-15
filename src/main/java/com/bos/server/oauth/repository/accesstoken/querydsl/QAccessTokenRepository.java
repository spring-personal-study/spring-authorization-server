package com.bos.server.oauth.repository.accesstoken.querydsl;

import com.bos.server.oauth.model.entity.Authorization;

public interface QAccessTokenRepository {

    Boolean existsByAuthorization(Authorization authorization);
}
