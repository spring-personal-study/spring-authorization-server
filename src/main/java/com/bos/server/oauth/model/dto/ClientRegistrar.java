package com.bos.server.oauth.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

public class ClientRegistrar {
    public record ClientRegistrationRequest(
            @JsonProperty("client_name") String clientName,
            @JsonProperty("grant_types") List<String> grantTypes,
            @JsonProperty("redirect_uris") List<String> redirectUris,
            @JsonProperty("logo_uri") String logoUri,
            List<String> contacts,
            String scope) {
    }

    public record ClientRegistrationResponse(
            @JsonProperty("registration_access_token") String registrationAccessToken,
            @JsonProperty("registration_client_uri") String registrationClientUri,
            @JsonProperty("client_name") String clientName,
            @JsonProperty("client_id") String clientId,
            @JsonProperty("client_secret") String clientSecret,
            @JsonProperty("grant_types") List<String> grantTypes,
            @JsonProperty("redirect_uris") List<String> redirectUris,
            @JsonProperty("logo_uri") String logoUri,
            List<String> contacts,
            String scope) {
    }
}
