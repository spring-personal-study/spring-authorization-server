package com.bos.server.oauth.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "oauth_authorization_consent")
@IdClass(AuthorizationConsent.AuthorizationConsentId.class)
public class AuthorizationConsent {

    @Id
    @Column(name = "registered_client_id", nullable = false)
    private String registeredClientId;

    @Id
    @Column(name = "principal_name", nullable = false)
    private String principalName;

    @Column(name = "authorities", length = 1000, nullable = false)
    private String authorities;

    public AuthorizationConsent(String registeredClientId, String principalName, String authorities) {
        this.registeredClientId = registeredClientId;
        this.principalName = principalName;
        this.authorities = authorities;
    }

    @Getter @Setter
    public static class AuthorizationConsentId implements Serializable {
        private String registeredClientId;
        private String principalName;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AuthorizationConsentId that = (AuthorizationConsentId) o;
            return registeredClientId.equals(that.registeredClientId) && principalName.equals(that.principalName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(registeredClientId, principalName);
        }
    }
}
