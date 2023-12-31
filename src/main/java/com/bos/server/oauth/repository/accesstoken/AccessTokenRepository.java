package com.bos.server.oauth.repository.accesstoken;

import com.bos.server.oauth.model.entity.AccessToken;
import com.bos.server.oauth.repository.accesstoken.querydsl.QAccessTokenRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Long>, QAccessTokenRepository {
    Optional<AccessToken> findByTokenValue(String accessToken);
}
