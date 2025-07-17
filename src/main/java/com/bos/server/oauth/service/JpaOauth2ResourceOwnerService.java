package com.bos.server.oauth.service;

import com.bos.server.config.exception.common.BizException;
import com.bos.server.oauth.exception.ResourceOwnerCrudErrorCode;
import com.bos.server.oauth.model.dto.ResourceOwnerDto;
import com.bos.server.oauth.model.entity.ResourceOwner;
import com.bos.server.oauth.repository.resourceowner.ResourceOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JpaOauth2ResourceOwnerService {

    private final ResourceOwnerRepository resourceOwnerRepository;

    @Transactional(readOnly = true)
    public ResourceOwnerDto findById(String id) {
        ResourceOwner resourceOwner = resourceOwnerRepository.findById(id)
                .orElseThrow(() -> new BizException(ResourceOwnerCrudErrorCode.RO_NOT_FOUND));
        return ResourceOwnerDto.toObject(resourceOwner);
    }

}
