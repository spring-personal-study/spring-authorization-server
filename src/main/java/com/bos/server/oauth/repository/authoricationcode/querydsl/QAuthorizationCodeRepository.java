package com.bos.server.oauth.repository.authoricationcode.querydsl;

import com.bos.server.oauth.model.entity.Authorization;

public interface QAuthorizationCodeRepository {
    Boolean existsByAuthorization(Authorization authorization);
}
