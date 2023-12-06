package com.bos.server.oauth.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "oauth_device_code")
public class DeviceCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "device_code_value", length = 4000)
    private String deviceCodeValue;

    @Column(name = "device_code_issued_at")
    private Instant deviceCodeIssuedAt;

    @Column(name = "device_code_expires_at")
    private Instant deviceCodeExpiresAt;

    @Column(name = "device_code_metadata")
    private String deviceCodeMetadata;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorization_id", nullable = false)
    private Authorization authorization;

    public DeviceCode(String deviceCodeValue, Instant deviceCodeIssuedAt, Instant deviceCodeExpiresAt, String deviceCodeMetadata, Authorization authorization) {
        this.deviceCodeValue = deviceCodeValue;
        this.deviceCodeIssuedAt = deviceCodeIssuedAt;
        this.deviceCodeExpiresAt = deviceCodeExpiresAt;
        this.deviceCodeMetadata = deviceCodeMetadata;
        this.authorization = authorization;
    }
}
