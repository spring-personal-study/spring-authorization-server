package com.bos.server.oauth.repository.resourceowner;

import com.bos.server.oauth.model.entity.ResourceOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResourceOwnerRepository extends JpaRepository<ResourceOwner, String> {
    Optional<ResourceOwner> findById(String id);
}
