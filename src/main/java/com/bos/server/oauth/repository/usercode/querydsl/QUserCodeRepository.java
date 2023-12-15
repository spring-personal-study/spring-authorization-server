package com.bos.server.oauth.repository.usercode.querydsl;

import com.bos.server.oauth.model.entity.Authorization;

public interface QUserCodeRepository {

    Boolean existsByAuthorization(Authorization authorization);
}
