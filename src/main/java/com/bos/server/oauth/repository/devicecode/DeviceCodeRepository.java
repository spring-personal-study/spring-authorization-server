package com.bos.server.oauth.repository.devicecode;

import com.bos.server.oauth.entity.DeviceCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceCodeRepository extends JpaRepository<DeviceCode, Long> {
    Optional<DeviceCode> findByDeviceCodeValue(String deviceCode);
}
