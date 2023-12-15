package com.bos.server.oauth.repository.devicecode;

import com.bos.server.oauth.model.entity.DeviceCode;
import com.bos.server.oauth.repository.devicecode.querydsl.QDeviceCodeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceCodeRepository extends JpaRepository<DeviceCode, Long>, QDeviceCodeRepository {
    Optional<DeviceCode> findByDeviceCodeValue(String deviceCode);
}
