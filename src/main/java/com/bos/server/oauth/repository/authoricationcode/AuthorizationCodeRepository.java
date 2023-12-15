package com.bos.server.oauth.repository.authoricationcode;

import com.bos.server.oauth.model.entity.AuthorizationCode;
import com.bos.server.oauth.repository.authoricationcode.querydsl.QAuthorizationCodeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorizationCodeRepository extends JpaRepository<AuthorizationCode, Long>, QAuthorizationCodeRepository {
    Optional<AuthorizationCode> findByAuthorizationCodeValue(String authorizationCode);
}
