package com.bos.server.oauth.repository.refreshtoken;

import com.bos.server.oauth.model.entity.RefreshToken;
import com.bos.server.oauth.repository.refreshtoken.querydsl.QRefreshTokenRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>, QRefreshTokenRepository {
    Optional<RefreshToken> findByRefreshTokenValue(String refreshToken);
}
