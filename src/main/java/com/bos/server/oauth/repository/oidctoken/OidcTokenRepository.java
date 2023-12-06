package com.bos.server.oauth.repository.oidctoken;

import com.bos.server.oauth.model.entity.OidcToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OidcTokenRepository extends JpaRepository<OidcToken, Long> {
    Optional<OidcToken> findByOidcIdTokenValue(String idToken);
}
