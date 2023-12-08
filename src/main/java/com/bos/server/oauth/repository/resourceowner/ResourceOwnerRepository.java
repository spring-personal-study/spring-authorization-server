package com.bos.server.oauth.repository.resourceowner;

import com.bos.server.oauth.model.entity.ResourceOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceOwnerRepository extends JpaRepository<ResourceOwner, Long> {
    ResourceOwner findByResourceOwnerId(String roId);
}
