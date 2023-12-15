package com.bos.server.oauth.repository.devicecode.querydsl;

import com.bos.server.oauth.model.entity.Authorization;

public interface QDeviceCodeRepository {

    Boolean existsByAuthorization(Authorization authorization);
}
