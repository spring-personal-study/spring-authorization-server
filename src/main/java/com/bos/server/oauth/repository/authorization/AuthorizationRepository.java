package com.bos.server.oauth.repository.authorization;

import com.bos.server.oauth.entity.Authorization;
import com.bos.server.oauth.repository.querydsl.QAuthorizationRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorizationRepository extends JpaRepository<Authorization, String>, QAuthorizationRepository {
    Optional<Authorization> findByState(String state);
}