package com.bos.server.oauth.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRegistrationDto {
    private String clientId;
    private String clientSecret;
    private String clientName;
    private String redirectUris;
    private String scopes;
}