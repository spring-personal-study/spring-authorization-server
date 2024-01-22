package com.bos.server.unit.entity;

import com.bos.server.oauth.model.entity.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Entity Test")
public class EntityTest {

    @Nested
    @DisplayName("Client Entity Test")
    class ClientEntityTest {

        @Test
        @DisplayName("Given ClientEntity, when to create, then Ok")
        void givenClientEntity_whenToCreate_thenOk() {
            Client client = new Client(
                    "1",
                    "test-client",
                    "testClientSecret",
                    Instant.MIN,
                    Instant.MAX,
                    "clientName",
                    "client_secret_basic,client_secret_post",
                    "authorization_code,client_credentials,refresh_token",
                    "http://127.0.0.1:8081",
                    "postLogoutRedirectUris",
                    "scopes",
                    "{ \"@class\": \"java.util.Collections$UnmodifiableMap\", \"settings.client.require-proof-key\": false, \"settings.client.require-authorization-consent\": true}",
                    "{\"@class\": \"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\": true," +
                            "\"settings.token.id-token-signature-algorithm\": [\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"]," +
                            "\"settings.token.access-token-time-to-live\": [\"java.time.Duration\",\"PT6H\"]," +
                            "\"settings.token.refresh-token-time-to-live\": [\"java.time.Duration\",\"PT72H\"]," +
                            "\"settings.token.authorization-code-time-to-live\": [\"java.time.Duration\",\"PT1H\"]," +
                            "\"settings.token.device-code-time-to-live\": [\"java.time.Duration\",\"PT5M\"], " +
                            "\"settings.token.access-token-format\": {\"@class\": \"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\", " +
                            "\"value\": \"self-contained\"}}"
            );

            assertThat(client).isNotNull();
            assertThat(client.getId()).isEqualTo("1");
            assertThat(client.getClientId()).isEqualTo("test-client");
            assertThat(client.getClientSecret()).isEqualTo("testClientSecret");
            assertThat(client.getClientIdIssuedAt()).isEqualTo(Instant.MIN);
            assertThat(client.getClientSecretExpiresAt()).isEqualTo(Instant.MAX);
            assertThat(client.getClientName()).isEqualTo("clientName");
            assertThat(client.getClientAuthenticationMethods()).isEqualTo("client_secret_basic,client_secret_post");
            assertThat(client.getAuthorizationGrantTypes()).isEqualTo("authorization_code,client_credentials,refresh_token");
            assertThat(client.getRedirectUris()).isEqualTo("http://127.0.0.1:8081");
            assertThat(client.getPostLogoutRedirectUris()).isEqualTo("postLogoutRedirectUris");
            assertThat(client.getScopes()).isEqualTo("scopes");
            assertThat(client.getClientSettings()).isEqualTo("{ \"@class\": \"java.util.Collections$UnmodifiableMap\", \"settings.client.require-proof-key\": false, \"settings.client.require-authorization-consent\": true}");
            assertThat(client.getTokenSettings()).isEqualTo("{\"@class\": \"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\": true," +
                    "\"settings.token.id-token-signature-algorithm\": [\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"]," +
                    "\"settings.token.access-token-time-to-live\": [\"java.time.Duration\",\"PT6H\"]," +
                    "\"settings.token.refresh-token-time-to-live\": [\"java.time.Duration\",\"PT72H\"]," +
                    "\"settings.token.authorization-code-time-to-live\": [\"java.time.Duration\",\"PT1H\"]," +
                    "\"settings.token.device-code-time-to-live\": [\"java.time.Duration\",\"PT5M\"], " +
                    "\"settings.token.access-token-format\": {\"@class\": \"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\", " +
                    "\"value\": \"self-contained\"}}");
        }
    }

    @Nested
    @DisplayName("Authorization Entity Test")
    class AuthorizationEntityTest {

        @Test
        @DisplayName("Given AuthorizationEntity, when to create, then Ok")
        void givenAuthorizationEntity_whenToCreate_thenOk() {
            Authorization authorization = new Authorization(
                    "06b67900-1714-4043-8cad-74b55cd361a9",
                    "1",
                    "username",
                    "authorization_code",
                    "openid,client.read",
                    "{\"@class\":\"java.util.Collections$UnmodifiableMap\"," +
                            "\"java.security.Principal\":{\"@class\":\"org.springframework.security.authentication.UsernamePasswordAuthenticationToken\"," +
                            "\"authorities\":[\"java.util.Collections$UnmodifiableRandomAccessList\",[]]," +
                            "\"details\":{\"@class\":\"org.springframework.security.web.authentication.WebAuthenticationDetails\"," +
                            "\"remoteAddress\":\"127.0.0.1\"," +
                            "\"sessionId\":\"E35F7F68CF98D2AE900F6E0EBF573180\"}," +
                            "\"authenticated\":true," +
                            "\"principal\":{\"@class\":\"com.bos.server.oauth.authentication.PrincipalDetails\"," +
                            "\"resourceOwner\":{\"roId\":\"mclee\",\"password\":\"{bcrypt}$2a$12$JTX1hGYLGuhX3rOiHL5Gne0iOwh0AfA3PAmUmZuEtZk8arbB5BrGa\"}," +
                            "\"attributes\":null," +
                            "\"authorities\":[\"java.util.Collections$EmptyList\",[]],\"name\":\"mclee\",\"enabled\":true,\"password\":\"{bcrypt}$2a$12$JTX1hGYLGuhX3rOiHL5Gne0iOwh0AfA3PAmUmZuEtZk8arbB5BrGa\"," +
                            "\"username\":\"mclee\"," +
                            "\"accountNonLocked\":true,\"accountNonExpired\":true,\"credentialsNonExpired\":true}," +
                            "\"credentials\":null}," +
                            "\"org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest\":{" +
                            "\"@class\":\"org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest\"," +
                            "\"authorizationUri\":\"http://127.0.0.1:9000/oauth2/authorize\"," +
                            "\"authorizationGrantType\":{" +
                            "\"value\":\"authorization_code\"}," +
                            "\"responseType\":{\"value\":\"code\"}," +
                            "\"clientId\":\"bluebird-default-client\",\"redirectUri\":\"http://127.0.0.1:8081\"," +
                            "\"scopes\":[\"java.util.Collections$UnmodifiableSet\",[\"openid\",\"client.read\"]]," +
                            "\"state\":null," +
                            "\"additionalParameters\":{\"@class\":\"java.util.Collections$UnmodifiableMap\"}," +
                            "\"authorizationRequestUri\":\"http://127.0.0.1:9000/oauth2/authorize?response_type=code&client_id=bluebird-default-client&scope=openid%20client.read&redirect_uri=http://127.0.0.1:8081\"," +
                            "\"attributes\":{\"@class\":\"java.util.Collections$UnmodifiableMap\"}}," +
                            "\"state\":\"aIf71szUghkYJGL__k17aHLcXtRvMsQodfXxFF1iqb8=\"}",
                    "uNIPbEQb_cinY4Oa1hOKXhwq1X3eB5QabpIYRXOHww0=");

            assertThat(authorization).isNotNull();
            assertThat(authorization.getId()).isEqualTo("06b67900-1714-4043-8cad-74b55cd361a9");
            assertThat(authorization.getRegisteredClientId()).isEqualTo("1");
            assertThat(authorization.getPrincipalName()).isEqualTo("username");
            assertThat(authorization.getAuthorizationGrantType()).isEqualTo("authorization_code");
            assertThat(authorization.getAuthorizedScopes()).isEqualTo("openid,client.read");

        }
    }

    @Nested
    @DisplayName("AuthorizationConsent Entity Test")
    class AuthorizationConsentEntityTest {

        @Test
        @DisplayName("Given AuthorizationConsentEntity, when to create, then Ok")
        void givenAuthorizationConsentEntity_whenToCreate_thenOk() {
            AuthorizationConsent authorizationConsent = new AuthorizationConsent(
                    "1",
                    "username",
                    "SCOPE_openid,SCOPE_client.read"
            );

            assertThat(authorizationConsent).isNotNull();
            assertThat(authorizationConsent.getRegisteredClientId()).isEqualTo("1");
            assertThat(authorizationConsent.getPrincipalName()).isEqualTo("username");
            assertThat(authorizationConsent.getAuthorities()).isEqualTo("SCOPE_openid,SCOPE_client.read");
        }
    }


    @Nested
    @DisplayName("AuthorizationConsent Entity Test")
    class AuthorizationCodeEntityTest {

        @Test
        @DisplayName("Given AuthorizationCodeEntity, when to create, then Ok")
        void givenAuthorizationCodeEntity_whenToCreate_thenOk() {
            Authorization authorization = new Authorization(
                    "06b67900-1714-4043-8cad-74b55cd361a9",
                    "1",
                    "username",
                    "authorization_code",
                    "openid,client.read",
                    "{\"@class\":\"java.util.Collections$UnmodifiableMap\"," +
                            "\"java.security.Principal\":{\"@class\":\"org.springframework.security.authentication.UsernamePasswordAuthenticationToken\"," +
                            "\"authorities\":[\"java.util.Collections$UnmodifiableRandomAccessList\",[]]," +
                            "\"details\":{\"@class\":\"org.springframework.security.web.authentication.WebAuthenticationDetails\"," +
                            "\"remoteAddress\":\"127.0.0.1\"," +
                            "\"sessionId\":\"E35F7F68CF98D2AE900F6E0EBF573180\"}," +
                            "\"authenticated\":true," +
                            "\"principal\":{\"@class\":\"com.bos.server.oauth.authentication.PrincipalDetails\"," +
                            "\"resourceOwner\":{\"roId\":\"mclee\",\"password\":\"{bcrypt}$2a$12$JTX1hGYLGuhX3rOiHL5Gne0iOwh0AfA3PAmUmZuEtZk8arbB5BrGa\"}," +
                            "\"attributes\":null," +
                            "\"authorities\":[\"java.util.Collections$EmptyList\",[]],\"name\":\"mclee\",\"enabled\":true,\"password\":\"{bcrypt}$2a$12$JTX1hGYLGuhX3rOiHL5Gne0iOwh0AfA3PAmUmZuEtZk8arbB5BrGa\"," +
                            "\"username\":\"mclee\"," +
                            "\"accountNonLocked\":true,\"accountNonExpired\":true,\"credentialsNonExpired\":true}," +
                            "\"credentials\":null}," +
                            "\"org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest\":{" +
                            "\"@class\":\"org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest\"," +
                            "\"authorizationUri\":\"http://127.0.0.1:9000/oauth2/authorize\"," +
                            "\"authorizationGrantType\":{" +
                            "\"value\":\"authorization_code\"}," +
                            "\"responseType\":{\"value\":\"code\"}," +
                            "\"clientId\":\"bluebird-default-client\",\"redirectUri\":\"http://127.0.0.1:8081\"," +
                            "\"scopes\":[\"java.util.Collections$UnmodifiableSet\",[\"openid\",\"client.read\"]]," +
                            "\"state\":null," +
                            "\"additionalParameters\":{\"@class\":\"java.util.Collections$UnmodifiableMap\"}," +
                            "\"authorizationRequestUri\":\"http://127.0.0.1:9000/oauth2/authorize?response_type=code&client_id=bluebird-default-client&scope=openid%20client.read&redirect_uri=http://127.0.0.1:8081\"," +
                            "\"attributes\":{\"@class\":\"java.util.Collections$UnmodifiableMap\"}}," +
                            "\"state\":\"aIf71szUghkYJGL__k17aHLcXtRvMsQodfXxFF1iqb8=\"}",
                    "uNIPbEQb_cinY4Oa1hOKXhwq1X3eB5QabpIYRXOHww0=");

            AuthorizationCode authorizationCode = new AuthorizationCode(
                    "jwodIUL--7q96FNZXe_Ynwab4i6sHzVjfdTutXt4_hkOsnzXstu2A1_isq1sOiKv1bP5A6w8W-l7BXonE9grAt5Er8kmKjux7uxIaPOLfH29wU39--tTdc9k_63iPGW_",
                    Instant.MIN,
                    Instant.MAX,
                    "{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"metadata.token.invalidated\":false}",
                    authorization
            );

            assertThat(authorizationCode).isNotNull();
            assertThat(authorizationCode.getAuthorizationCodeValue()).isEqualTo("jwodIUL--7q96FNZXe_Ynwab4i6sHzVjfdTutXt4_hkOsnzXstu2A1_isq1sOiKv1bP5A6w8W-l7BXonE9grAt5Er8kmKjux7uxIaPOLfH29wU39--tTdc9k_63iPGW_");
            assertThat(authorizationCode.getAuthorizationCodeIssuedAt()).isEqualTo(Instant.MIN);
            assertThat(authorizationCode.getAuthorizationCodeExpiresAt()).isEqualTo(Instant.MAX);
            assertThat(authorizationCode.getAuthorizationCodeMetadata()).isEqualTo("{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"metadata.token.invalidated\":false}");
            assertThat(authorizationCode.getAuthorization()).isEqualTo(authorization);
        }
    }

    @Nested
    @DisplayName("ResourceOwner Entity Test")
    class ResourceOwnerEntityTest {

        @Test
        @DisplayName("Given ResourceOwnerEntity, when to create, then Ok")
        void givenResourceOwnerEntity_whenToCreate_thenOk() {
            ResourceOwner resourceOwner = new ResourceOwner(
                    1L,
                    "username",
                    "$2a$12$JTX1hGYLGuhX3rOiHL5Gne0iOwh0AfA3PAmUmZuEtZk8arbB5BrGa"
            );

            assertThat(resourceOwner).isNotNull();
            assertThat(resourceOwner.getId()).isEqualTo(1L);
            assertThat(resourceOwner.getResourceOwnerId()).isEqualTo("username");
            assertThat(resourceOwner.getPassword()).isEqualTo("$2a$12$JTX1hGYLGuhX3rOiHL5Gne0iOwh0AfA3PAmUmZuEtZk8arbB5BrGa");

        }
    }

    @Nested
    @DisplayName("AccessToken Entity Test")
    class AccessTokenEntityTest {

        @Test
        @DisplayName("Given AccessTokenEntity, when to create, then Ok")
        void givenAccessTokenEntity_whenToCreate_thenOk() {
            Authorization authorization = new Authorization(
                    "06b67900-1714-4043-8cad-74b55cd361a9",
                    "1",
                    "username",
                    "authorization_code",
                    "openid,client.read",
                    "{\"@class\":\"java.util.Collections$UnmodifiableMap\"," +
                            "\"java.security.Principal\":{\"@class\":\"org.springframework.security.authentication.UsernamePasswordAuthenticationToken\"," +
                            "\"authorities\":[\"java.util.Collections$UnmodifiableRandomAccessList\",[]]," +
                            "\"details\":{\"@class\":\"org.springframework.security.web.authentication.WebAuthenticationDetails\"," +
                            "\"remoteAddress\":\"127.0.0.1\"," +
                            "\"sessionId\":\"E35F7F68CF98D2AE900F6E0EBF573180\"}," +
                            "\"authenticated\":true," +
                            "\"principal\":{\"@class\":\"com.bos.server.oauth.authentication.PrincipalDetails\"," +
                            "\"resourceOwner\":{\"roId\":\"mclee\",\"password\":\"{bcrypt}$2a$12$JTX1hGYLGuhX3rOiHL5Gne0iOwh0AfA3PAmUmZuEtZk8arbB5BrGa\"}," +
                            "\"attributes\":null," +
                            "\"authorities\":[\"java.util.Collections$EmptyList\",[]],\"name\":\"mclee\",\"enabled\":true,\"password\":\"{bcrypt}$2a$12$JTX1hGYLGuhX3rOiHL5Gne0iOwh0AfA3PAmUmZuEtZk8arbB5BrGa\"," +
                            "\"username\":\"mclee\"," +
                            "\"accountNonLocked\":true,\"accountNonExpired\":true,\"credentialsNonExpired\":true}," +
                            "\"credentials\":null}," +
                            "\"org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest\":{" +
                            "\"@class\":\"org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest\"," +
                            "\"authorizationUri\":\"http://127.0.0.1:9000/oauth2/authorize\"," +
                            "\"authorizationGrantType\":{" +
                            "\"value\":\"authorization_code\"}," +
                            "\"responseType\":{\"value\":\"code\"}," +
                            "\"clientId\":\"bluebird-default-client\",\"redirectUri\":\"http://127.0.0.1:8081\"," +
                            "\"scopes\":[\"java.util.Collections$UnmodifiableSet\",[\"openid\",\"client.read\"]]," +
                            "\"state\":null," +
                            "\"additionalParameters\":{\"@class\":\"java.util.Collections$UnmodifiableMap\"}," +
                            "\"authorizationRequestUri\":\"http://127.0.0.1:9000/oauth2/authorize?response_type=code&client_id=bluebird-default-client&scope=openid%20client.read&redirect_uri=http://127.0.0.1:8081\"," +
                            "\"attributes\":{\"@class\":\"java.util.Collections$UnmodifiableMap\"}}," +
                            "\"state\":\"aIf71szUghkYJGL__k17aHLcXtRvMsQodfXxFF1iqb8=\"}",
                    "uNIPbEQb_cinY4Oa1hOKXhwq1X3eB5QabpIYRXOHww0=");


            AccessToken accessToken = new AccessToken(
                    "eyJraWQiOiJmYWUyZDU3Ni04OWRmLTRlZTAtYjUwYS1kYTk3MzZiMGYzYmUiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImF1ZCI6ImJsdWViaXJkLWRlZmF1bHQtY2xpZW50IiwibmJmIjoxNzAyNjEwNDM1LCJzY29wZSI6WyJvcGVuaWQiLCJjbGllbnQucmVhZCJdLCJpc3MiOiJodHRwOi8vMTI3LjAuMC4xOjkwMDAiLCJleHAiOjE3MDI2MzIwMzUsImlhdCI6MTcwMjYxMDQzNSwianRpIjoiN2VhODkzYTgtOGI1MS00ZTRiLTgwNzEtYWU3MzIzNWJmMzM5In0.xSwB18s-Ay_LnWxqn15fUgz5rNiVrpH4wuBoHFlhx4cTz5FUm0g0jiAvroyCyquwaBXwwNjw-BigzrMfJrRcyJyDWUhILh3T1ISFeWxGK_uDiMmFzkBW6QqJN6M2eQbqRI9f0YtQDwEM14u2CnBpXLV4pltUYrU1b7m_aWJLuBLpfm2aLunRe2WpU5nu3DdHMcbc8yUl_spQUdDicsAS7z8Sog0k51fcc_dGtHurEMeBg7lZf_l-AF8HlnefZayBMUopB8wE4yy2Rec1cyIZDLkzr6uNMQFEUbX-wcGOiSxdqSeActKv41y1qRvu7VdtuRfNY_N2aLUHfKvMEiXG4Q",
                    Instant.MIN,
                    Instant.MAX,
                    "{\"@class\":\"java.util.Collections$UnmodifiableMap\"," +
                            "\"metadata.token.claims\":{\"@class\":\"java.util.Collections$UnmodifiableMap\"," +
                            "\"sub\":\"admin\"," +
                            "\"aud\":[\"java.util.Collections$SingletonList\",[\"bluebird-default-client\"]]," +
                            "\"nbf\":[\"java.time.Instant\",1702610435.751804300]," +
                            "\"scope\":[\"java.util.Collections$UnmodifiableSet\",[\"openid\",\"client.read\"]]," +
                            "\"iss\":[\"java.net.URL\",\"http://127.0.0.1:9000\"]," +
                            "\"exp\":[\"java.time.Instant\",1702632035.751804300]," +
                            "\"iat\":[\"java.time.Instant\",1702610435.751804300]," +
                            "\"jti\":\"7ea893a8-8b51-4e4b-8071-ae73235bf339\"}," +
                            "\"metadata.token.invalidated\":false}",
                    authorization);

            assertThat(accessToken).isNotNull();
            assertThat(accessToken.getTokenValue()).isEqualTo("eyJraWQiOiJmYWUyZDU3Ni04OWRmLTRlZTAtYjUwYS1kYTk3MzZiMGYzYmUiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImF1ZCI6ImJsdWViaXJkLWRlZmF1bHQtY2xpZW50IiwibmJmIjoxNzAyNjEwNDM1LCJzY29wZSI6WyJvcGVuaWQiLCJjbGllbnQucmVhZCJdLCJpc3MiOiJodHRwOi8vMTI3LjAuMC4xOjkwMDAiLCJleHAiOjE3MDI2MzIwMzUsImlhdCI6MTcwMjYxMDQzNSwianRpIjoiN2VhODkzYTgtOGI1MS00ZTRiLTgwNzEtYWU3MzIzNWJmMzM5In0.xSwB18s-Ay_LnWxqn15fUgz5rNiVrpH4wuBoHFlhx4cTz5FUm0g0jiAvroyCyquwaBXwwNjw-BigzrMfJrRcyJyDWUhILh3T1ISFeWxGK_uDiMmFzkBW6QqJN6M2eQbqRI9f0YtQDwEM14u2CnBpXLV4pltUYrU1b7m_aWJLuBLpfm2aLunRe2WpU5nu3DdHMcbc8yUl_spQUdDicsAS7z8Sog0k51fcc_dGtHurEMeBg7lZf_l-AF8HlnefZayBMUopB8wE4yy2Rec1cyIZDLkzr6uNMQFEUbX-wcGOiSxdqSeActKv41y1qRvu7VdtuRfNY_N2aLUHfKvMEiXG4Q");
        }
    }

    @Nested
    @DisplayName("OidcToken Entity Test")
    class OidcTokenEntityTest {

        @Test
        @DisplayName("Given OidcTokenEntity, when to create, then Ok")
        void givenOidcTokenEntity_whenToCreate_thenOk() {
            Authorization authorization = new Authorization(
                    "06b67900-1714-4043-8cad-74b55cd361a9",
                    "1",
                    "username",
                    "authorization_code",
                    "openid,client.read",
                    "{\"@class\":\"java.util.Collections$UnmodifiableMap\"," +
                            "\"java.security.Principal\":{\"@class\":\"org.springframework.security.authentication.UsernamePasswordAuthenticationToken\"," +
                            "\"authorities\":[\"java.util.Collections$UnmodifiableRandomAccessList\",[]]," +
                            "\"details\":{\"@class\":\"org.springframework.security.web.authentication.WebAuthenticationDetails\"," +
                            "\"remoteAddress\":\"127.0.0.1\"," +
                            "\"sessionId\":\"E35F7F68CF98D2AE900F6E0EBF573180\"}," +
                            "\"authenticated\":true," +
                            "\"principal\":{\"@class\":\"com.bos.server.oauth.authentication.PrincipalDetails\"," +
                            "\"resourceOwner\":{\"roId\":\"mclee\",\"password\":\"{bcrypt}$2a$12$JTX1hGYLGuhX3rOiHL5Gne0iOwh0AfA3PAmUmZuEtZk8arbB5BrGa\"}," +
                            "\"attributes\":null," +
                            "\"authorities\":[\"java.util.Collections$EmptyList\",[]],\"name\":\"mclee\",\"enabled\":true,\"password\":\"{bcrypt}$2a$12$JTX1hGYLGuhX3rOiHL5Gne0iOwh0AfA3PAmUmZuEtZk8arbB5BrGa\"," +
                            "\"username\":\"mclee\"," +
                            "\"accountNonLocked\":true,\"accountNonExpired\":true,\"credentialsNonExpired\":true}," +
                            "\"credentials\":null}," +
                            "\"org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest\":{" +
                            "\"@class\":\"org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest\"," +
                            "\"authorizationUri\":\"http://127.0.0.1:9000/oauth2/authorize\"," +
                            "\"authorizationGrantType\":{" +
                            "\"value\":\"authorization_code\"}," +
                            "\"responseType\":{\"value\":\"code\"}," +
                            "\"clientId\":\"bluebird-default-client\",\"redirectUri\":\"http://127.0.0.1:8081\"," +
                            "\"scopes\":[\"java.util.Collections$UnmodifiableSet\",[\"openid\",\"client.read\"]]," +
                            "\"state\":null," +
                            "\"additionalParameters\":{\"@class\":\"java.util.Collections$UnmodifiableMap\"}," +
                            "\"authorizationRequestUri\":\"http://127.0.0.1:9000/oauth2/authorize?response_type=code&client_id=bluebird-default-client&scope=openid%20client.read&redirect_uri=http://127.0.0.1:8081\"," +
                            "\"attributes\":{\"@class\":\"java.util.Collections$UnmodifiableMap\"}}," +
                            "\"state\":\"aIf71szUghkYJGL__k17aHLcXtRvMsQodfXxFF1iqb8=\"}",
                    "uNIPbEQb_cinY4Oa1hOKXhwq1X3eB5QabpIYRXOHww0=");

            OidcToken oidcToken = new OidcToken(
                    "eyJraWQiOiJiNDMzZTYwMS0wMThkLTRjNDItYjdmZC1mNDM1YzExZTlmYzYiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImF1ZCI6ImJsdWViaXJkLWRlZmF1bHQtY2xpZW50IiwiYXpwIjoiYmx1ZWJpcmQtZGVmYXVsdC1jbGllbnQiLCJpc3MiOiJodHRwOi8vMTI3LjAuMC4xOjkwMDAiLCJleHAiOjE3MDI2MDkzMDQsImlhdCI6MTcwMjYwNzUwNCwianRpIjoiM2NiZTFkNmYtYTVjOS00NWJiLWI5MjUtMTEyMWU5Y2RmZGVlIn0.yPEu0nNjuEzGzOpYAlaFwm6TYOrBnPmM1rAW6URi4t7gftt0rQ5YvWgHNgAWs2PudiyZwiHVrVPwR3AoKmCaiB34U66pJWVOeiU85lZrmLsPl3PCCpKu7tSoQ6LnTpRR79791FooBiULBlKAapCcFo4x6MT2DZ6URCvE09hjng5otCj1ZsWGQq5-wceAlZ7jMNIguV-zIg0clkVFMxR8AQaS_NN1ZWqPoxYj8qwHZPxomOvis3yvpDEOLE1YEFFpfGpeDd7ZDMAaOdVKAX6fQfoxr40qVPHuG-0nS6-9v8CjmbYt0duZjsK-j0mNzDgDLjbX-ArRjsDIm1W1BSsp8Q",
                    Instant.MIN,
                    Instant.MAX,
                    "{\"@class\":\"java.util.Collections$UnmodifiableMap\"," +
                            "\"metadata.token.claims\":{\"@class\":\"java.util.Collections$UnmodifiableMap\"," +
                            "\"sub\":\"admin\"," +
                            "\"aud\":[\"java.util.Collections$SingletonList\",[\"bluebird-default-client\"]]," +
                            "\"azp\":\"bluebird-default-client\"," +
                            "\"iss\":[\"java.net.URL\",\"http://127.0.0.1:9000\"]," +
                            "\"exp\":[\"java.time.Instant\",1702609304.021905700]," +
                            "\"iat\":[\"java.time.Instant\",1702607504.021905700]," +
                            "\"jti\":\"3cbe1d6f-a5c9-45bb-b925-1121e9cdfdee\"}," +
                            "\"metadata.token.invalidated\":false}",
                    "{\"@class\":\"java.util.Collections$UnmodifiableMap\"," +
                            "\"sub\":\"admin\"," +
                            "\"aud\":[\"java.util.Collections$SingletonList\",[\"bluebird-default-client\"]]," +
                            "\"azp\":\"bluebird-default-client\"," +
                            "\"iss\":[\"java.net.URL\",\"http://127.0.0.1:9000\"]," +
                            "\"exp\":[\"java.time.Instant\",1702609304.021905700]," +
                            "\"iat\":[\"java.time.Instant\",1702607504.021905700]," +
                            "\"jti\":\"3cbe1d6f-a5c9-45bb-b925-1121e9cdfdee\"}",
                    authorization);

            assertThat(oidcToken).isNotNull();
            assertThat(oidcToken.getOidcIdTokenValue()).isEqualTo("eyJraWQiOiJiNDMzZTYwMS0wMThkLTRjNDItYjdmZC1mNDM1YzExZTlmYzYiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImF1ZCI6ImJsdWViaXJkLWRlZmF1bHQtY2xpZW50IiwiYXpwIjoiYmx1ZWJpcmQtZGVmYXVsdC1jbGllbnQiLCJpc3MiOiJodHRwOi8vMTI3LjAuMC4xOjkwMDAiLCJleHAiOjE3MDI2MDkzMDQsImlhdCI6MTcwMjYwNzUwNCwianRpIjoiM2NiZTFkNmYtYTVjOS00NWJiLWI5MjUtMTEyMWU5Y2RmZGVlIn0.yPEu0nNjuEzGzOpYAlaFwm6TYOrBnPmM1rAW6URi4t7gftt0rQ5YvWgHNgAWs2PudiyZwiHVrVPwR3AoKmCaiB34U66pJWVOeiU85lZrmLsPl3PCCpKu7tSoQ6LnTpRR79791FooBiULBlKAapCcFo4x6MT2DZ6URCvE09hjng5otCj1ZsWGQq5-wceAlZ7jMNIguV-zIg0clkVFMxR8AQaS_NN1ZWqPoxYj8qwHZPxomOvis3yvpDEOLE1YEFFpfGpeDd7ZDMAaOdVKAX6fQfoxr40qVPHuG-0nS6-9v8CjmbYt0duZjsK-j0mNzDgDLjbX-ArRjsDIm1W1BSsp8Q");


        }
    }

    @Nested
    @DisplayName("RefreshToken Entity Test")
    class RefreshTokenEntityTest {

        Authorization authorization = new Authorization(
                "06b67900-1714-4043-8cad-74b55cd361a9",
                "1",
                "username",
                "authorization_code",
                "openid,client.read",
                "{\"@class\":\"java.util.Collections$UnmodifiableMap\"," +
                        "\"java.security.Principal\":{\"@class\":\"org.springframework.security.authentication.UsernamePasswordAuthenticationToken\"," +
                        "\"authorities\":[\"java.util.Collections$UnmodifiableRandomAccessList\",[]]," +
                        "\"details\":{\"@class\":\"org.springframework.security.web.authentication.WebAuthenticationDetails\"," +
                        "\"remoteAddress\":\"127.0.0.1\"," +
                        "\"sessionId\":\"E35F7F68CF98D2AE900F6E0EBF573180\"}," +
                        "\"authenticated\":true," +
                        "\"principal\":{\"@class\":\"com.bos.server.oauth.authentication.PrincipalDetails\"," +
                        "\"resourceOwner\":{\"roId\":\"mclee\",\"password\":\"{bcrypt}$2a$12$JTX1hGYLGuhX3rOiHL5Gne0iOwh0AfA3PAmUmZuEtZk8arbB5BrGa\"}," +
                        "\"attributes\":null," +
                        "\"authorities\":[\"java.util.Collections$EmptyList\",[]],\"name\":\"mclee\",\"enabled\":true,\"password\":\"{bcrypt}$2a$12$JTX1hGYLGuhX3rOiHL5Gne0iOwh0AfA3PAmUmZuEtZk8arbB5BrGa\"," +
                        "\"username\":\"mclee\"," +
                        "\"accountNonLocked\":true,\"accountNonExpired\":true,\"credentialsNonExpired\":true}," +
                        "\"credentials\":null}," +
                        "\"org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest\":{" +
                        "\"@class\":\"org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest\"," +
                        "\"authorizationUri\":\"http://127.0.0.1:9000/oauth2/authorize\"," +
                        "\"authorizationGrantType\":{" +
                        "\"value\":\"authorization_code\"}," +
                        "\"responseType\":{\"value\":\"code\"}," +
                        "\"clientId\":\"bluebird-default-client\",\"redirectUri\":\"http://127.0.0.1:8081\"," +
                        "\"scopes\":[\"java.util.Collections$UnmodifiableSet\",[\"openid\",\"client.read\"]]," +
                        "\"state\":null," +
                        "\"additionalParameters\":{\"@class\":\"java.util.Collections$UnmodifiableMap\"}," +
                        "\"authorizationRequestUri\":\"http://127.0.0.1:9000/oauth2/authorize?response_type=code&client_id=bluebird-default-client&scope=openid%20client.read&redirect_uri=http://127.0.0.1:8081\"," +
                        "\"attributes\":{\"@class\":\"java.util.Collections$UnmodifiableMap\"}}," +
                        "\"state\":\"aIf71szUghkYJGL__k17aHLcXtRvMsQodfXxFF1iqb8=\"}",
                "uNIPbEQb_cinY4Oa1hOKXhwq1X3eB5QabpIYRXOHww0=");

        @Test
        @DisplayName("Given RefreshTokenEntity, when to create, then Ok")
        void givenRefreshTokenEntity_whenToCreate_thenOk() {
        }

    }

    @Nested
    @DisplayName("UserCode Entity Test")
    class UserCodeEntityTest {

        @Test
        @DisplayName("Given UserCodeEntity, when to create, then Ok")
        void givenUserCodeEntity_whenToCreate_thenOk() {

            Authorization authorization = new Authorization(
                    "06b67900-1714-4043-8cad-74b55cd361a9",
                    "1",
                    "username",
                    "authorization_code",
                    "openid,client.read",
                    "{\"@class\":\"java.util.Collections$UnmodifiableMap\"," +
                            "\"java.security.Principal\":{\"@class\":\"org.springframework.security.authentication.UsernamePasswordAuthenticationToken\"," +
                            "\"authorities\":[\"java.util.Collections$UnmodifiableRandomAccessList\",[]]," +
                            "\"details\":{\"@class\":\"org.springframework.security.web.authentication.WebAuthenticationDetails\"," +
                            "\"remoteAddress\":\"127.0.0.1\"," +
                            "\"sessionId\":\"E35F7F68CF98D2AE900F6E0EBF573180\"}," +
                            "\"authenticated\":true," +
                            "\"principal\":{\"@class\":\"com.bos.server.oauth.authentication.PrincipalDetails\"," +
                            "\"resourceOwner\":{\"roId\":\"mclee\",\"password\":\"{bcrypt}$2a$12$JTX1hGYLGuhX3rOiHL5Gne0iOwh0AfA3PAmUmZuEtZk8arbB5BrGa\"}," +
                            "\"attributes\":null," +
                            "\"authorities\":[\"java.util.Collections$EmptyList\",[]],\"name\":\"mclee\",\"enabled\":true,\"password\":\"{bcrypt}$2a$12$JTX1hGYLGuhX3rOiHL5Gne0iOwh0AfA3PAmUmZuEtZk8arbB5BrGa\"," +
                            "\"username\":\"mclee\"," +
                            "\"accountNonLocked\":true,\"accountNonExpired\":true,\"credentialsNonExpired\":true}," +
                            "\"credentials\":null}," +
                            "\"org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest\":{" +
                            "\"@class\":\"org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest\"," +
                            "\"authorizationUri\":\"http://127.0.0.1:9000/oauth2/authorize\"," +
                            "\"authorizationGrantType\":{" +
                            "\"value\":\"authorization_code\"}," +
                            "\"responseType\":{\"value\":\"code\"}," +
                            "\"clientId\":\"bluebird-default-client\",\"redirectUri\":\"http://127.0.0.1:8081\"," +
                            "\"scopes\":[\"java.util.Collections$UnmodifiableSet\",[\"openid\",\"client.read\"]]," +
                            "\"state\":null," +
                            "\"additionalParameters\":{\"@class\":\"java.util.Collections$UnmodifiableMap\"}," +
                            "\"authorizationRequestUri\":\"http://127.0.0.1:9000/oauth2/authorize?response_type=code&client_id=bluebird-default-client&scope=openid%20client.read&redirect_uri=http://127.0.0.1:8081\"," +
                            "\"attributes\":{\"@class\":\"java.util.Collections$UnmodifiableMap\"}}," +
                            "\"state\":\"aIf71szUghkYJGL__k17aHLcXtRvMsQodfXxFF1iqb8=\"}",
                    "uNIPbEQb_cinY4Oa1hOKXhwq1X3eB5QabpIYRXOHww0=");

            UserCode userCode = new UserCode(
                    null,
                    Instant.MIN,
                    Instant.MAX,
                    null,
                    authorization);

            assertThat(userCode).isNotNull();
            assertThat(userCode.getAuthorization()).isNotNull();
        }

    }

    @Nested
    @DisplayName("DeviceCode Entity Test")
    class DeviceCodeEntityTest {

        @Test
        @DisplayName("Given DeviceCodeEntity, when to create, then Ok")
        void givenDeviceCodeEntity_whenToCreate_thenOk() {
            Authorization authorization = new Authorization(
                    "06b67900-1714-4043-8cad-74b55cd361a9",
                    "1",
                    "username",
                    "authorization_code",
                    "openid,client.read",
                    "{\"@class\":\"java.util.Collections$UnmodifiableMap\"," +
                            "\"java.security.Principal\":{\"@class\":\"org.springframework.security.authentication.UsernamePasswordAuthenticationToken\"," +
                            "\"authorities\":[\"java.util.Collections$UnmodifiableRandomAccessList\",[]]," +
                            "\"details\":{\"@class\":\"org.springframework.security.web.authentication.WebAuthenticationDetails\"," +
                            "\"remoteAddress\":\"127.0.0.1\"," +
                            "\"sessionId\":\"E35F7F68CF98D2AE900F6E0EBF573180\"}," +
                            "\"authenticated\":true," +
                            "\"principal\":{\"@class\":\"com.bos.server.oauth.authentication.PrincipalDetails\"," +
                            "\"resourceOwner\":{\"roId\":\"mclee\",\"password\":\"{bcrypt}$2a$12$JTX1hGYLGuhX3rOiHL5Gne0iOwh0AfA3PAmUmZuEtZk8arbB5BrGa\"}," +
                            "\"attributes\":null," +
                            "\"authorities\":[\"java.util.Collections$EmptyList\",[]],\"name\":\"mclee\",\"enabled\":true,\"password\":\"{bcrypt}$2a$12$JTX1hGYLGuhX3rOiHL5Gne0iOwh0AfA3PAmUmZuEtZk8arbB5BrGa\"," +
                            "\"username\":\"mclee\"," +
                            "\"accountNonLocked\":true,\"accountNonExpired\":true,\"credentialsNonExpired\":true}," +
                            "\"credentials\":null}," +
                            "\"org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest\":{" +
                            "\"@class\":\"org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest\"," +
                            "\"authorizationUri\":\"http://127.0.0.1:9000/oauth2/authorize\"," +
                            "\"authorizationGrantType\":{" +
                            "\"value\":\"authorization_code\"}," +
                            "\"responseType\":{\"value\":\"code\"}," +
                            "\"clientId\":\"bluebird-default-client\",\"redirectUri\":\"http://127.0.0.1:8081\"," +
                            "\"scopes\":[\"java.util.Collections$UnmodifiableSet\",[\"openid\",\"client.read\"]]," +
                            "\"state\":null," +
                            "\"additionalParameters\":{\"@class\":\"java.util.Collections$UnmodifiableMap\"}," +
                            "\"authorizationRequestUri\":\"http://127.0.0.1:9000/oauth2/authorize?response_type=code&client_id=bluebird-default-client&scope=openid%20client.read&redirect_uri=http://127.0.0.1:8081\"," +
                            "\"attributes\":{\"@class\":\"java.util.Collections$UnmodifiableMap\"}}," +
                            "\"state\":\"aIf71szUghkYJGL__k17aHLcXtRvMsQodfXxFF1iqb8=\"}",
                    "uNIPbEQb_cinY4Oa1hOKXhwq1X3eB5QabpIYRXOHww0=");

            DeviceCode deviceCode = new DeviceCode(
                    null,
                    Instant.MIN,
                    Instant.MAX,
                    null,
                    authorization);

            assertThat(deviceCode).isNotNull();
            assertThat(deviceCode.getAuthorization()).isNotNull();
        }

    }
}
