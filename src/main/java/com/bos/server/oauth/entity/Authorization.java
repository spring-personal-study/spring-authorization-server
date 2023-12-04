package com.bos.server.oauth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "oauth_authorization")
public class Authorization {

    @Id
    private String id;

    @Column(name = "registered_client_id", nullable = false)
    private String registeredClientId;

    @Column(name = "principal_name", nullable = false)
    private String principalName;

    @Column(name = "authorization_grant_type", nullable = false)
    private String authorizationGrantType;

    @Column(name = "authorized_scopes", length = 1000)
    private String authorizedScopes;

    @Column(name = "attributes", length = 4000)
    private String attributes;

    @Column(name = "state", length = 500)
    private String state;

    @OneToOne(mappedBy = "authorization", fetch = FetchType.LAZY)
    private AuthorizationCode authorizationCode;

    @OneToOne(mappedBy = "authorization", fetch = FetchType.LAZY)
    private AccessToken accessToken;

    @OneToOne(mappedBy = "authorization", fetch = FetchType.LAZY)
    private RefreshToken refreshToken;

    @OneToOne(mappedBy = "authorization", fetch = FetchType.LAZY)
    private OidcToken oidcToken;

    @OneToOne(mappedBy = "authorization", fetch = FetchType.LAZY)
    private DeviceCode deviceCode;

    @OneToOne(mappedBy = "authorization", fetch = FetchType.LAZY)
    private UserCode userCode;

    public Authorization(String id, String registeredClientId, String principalName, String authorizationGrantType, String authorizedScopes, String attributes, String state) {
        this.id = id;
        this.registeredClientId = registeredClientId;
        this.principalName = principalName;
        this.authorizationGrantType = authorizationGrantType;
        this.authorizedScopes = authorizedScopes;
        this.attributes = attributes;
        this.state = state;
    }

    public void setAuthorizationCode(AuthorizationCode authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(RefreshToken refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setOidcToken(OidcToken oidcToken) {
        this.oidcToken = oidcToken;
    }

    public void setDeviceCode(DeviceCode deviceCode) {
        this.deviceCode = deviceCode;
    }

    public void setUserCode(UserCode userCode) {
        this.userCode = userCode;
    }
}
