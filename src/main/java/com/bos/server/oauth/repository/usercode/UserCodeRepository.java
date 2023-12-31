package com.bos.server.oauth.repository.usercode;

import com.bos.server.oauth.model.entity.UserCode;
import com.bos.server.oauth.repository.usercode.querydsl.QUserCodeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCodeRepository extends JpaRepository<UserCode, Long>, QUserCodeRepository {
    Optional<UserCode> findByUserCodeValue(String userCode);
}
