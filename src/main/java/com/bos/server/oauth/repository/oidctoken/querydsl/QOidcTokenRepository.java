package com.bos.server.oauth.repository.oidctoken.querydsl;

import com.bos.server.oauth.model.entity.Authorization;

public interface QOidcTokenRepository {

    Boolean existsByAuthorization(Authorization authorization);
}
