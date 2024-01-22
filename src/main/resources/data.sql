INSERT INTO oauth_authorization_consent (registered_client_id, principal_name, authorities)
VALUES (1, 'admin', 'SCOPE_client.create,SCOPE_openid,SCOPE_client.read'),
       (2, 'mock_user', 'SCOPE_client.create,SCOPE_openid,SCOPE_client.read');

INSERT INTO oauth_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name,
 client_authentication_methods, authorization_grant_types, redirect_uris, post_logout_redirect_uris, scopes,
 client_settings, token_settings)
VALUES ('1', 'bluebird-test-client', '2023-12-04 00:00:00', 'bluebird-test-client-secret', '9999-12-31 00:00:00',
        'bluebird-test-client-name', 'client_secret_basic,client_secret_post',
        'authorization_code,client_credentials,refresh_token', 'http://127.0.0.1:8081', '',
        'openid,client.read,client.create',
        '{ "@class": "java.util.Collections$UnmodifiableMap", "settings.client.require-proof-key": false, "settings.client.require-authorization-consent": true}',
        '{"@class": "java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens": true,"settings.token.id-token-signature-algorithm": ["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live": ["java.time.Duration","PT6H"],"settings.token.refresh-token-time-to-live": ["java.time.Duration","PT72H"],"settings.token.authorization-code-time-to-live": ["java.time.Duration","PT1H"],"settings.token.device-code-time-to-live": ["java.time.Duration","PT5M"], "settings.token.access-token-format": {"@class": "org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat", "value": "self-contained"}}');

INSERT INTO oauth_authorization
(id, registered_client_id, principal_name, authorization_grant_type, authorized_scopes, `attributes`, state, created_at)
VALUES ('215f6cb1-04a7-4c6e-bb1b-3dc9cd04f41e', '1', 'mock_user', 'authorization_code', '',
        '{"@class":"java.util.Collections$UnmodifiableMap","java.security.Principal":{"@class":"org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken","authorities":["java.util.Collections$UnmodifiableRandomAccessList",[{"@class":"org.springframework.security.core.authority.SimpleGrantedAuthority","authority":"SCOPE_client.read"}]],"details":null,"authenticated":true,"principal":{"@class":"org.springframework.security.oauth2.jwt.Jwt","tokenValue":"token","issuedAt":-31557014167219200.000000000,"expiresAt":31556889864403199.999999999,"headers":{"@class":"java.util.Collections$UnmodifiableMap","alg":"none"},"claims":{"@class":"java.util.Collections$UnmodifiableMap","aud":["java.util.ImmutableCollections$List12",["http://127.0.0.1:9000"]],"exp":["java.time.Instant",31556889864403199.999999999],"iat":["java.time.Instant",-31557014167219200.000000000],"iss":"http://127.0.0.1:9000","jti":"jti","nbf":["java.time.Instant",-31557014167219200.000000000],"sub":"mock_user"},"id":"jti","subject":"mock_user","notBefore":-31557014167219200.000000000,"issuer":"http://127.0.0.1:9000","audience":["java.util.ArrayList",["http://127.0.0.1:9000"]]},"credentials":{"@class":"org.springframework.security.oauth2.jwt.Jwt","tokenValue":"token","issuedAt":-31557014167219200.000000000,"expiresAt":31556889864403199.999999999,"headers":{"@class":"java.util.Collections$UnmodifiableMap","alg":"none"},"claims":{"@class":"java.util.Collections$UnmodifiableMap","aud":["java.util.ImmutableCollections$List12",["http://127.0.0.1:9000"]],"exp":["java.time.Instant",31556889864403199.999999999],"iat":["java.time.Instant",-31557014167219200.000000000],"iss":"http://127.0.0.1:9000","jti":"jti","nbf":["java.time.Instant",-31557014167219200.000000000],"sub":"mock_user"},"id":"jti","subject":"mock_user","notBefore":-31557014167219200.000000000,"issuer":"http://127.0.0.1:9000","audience":["java.util.ArrayList",["http://127.0.0.1:9000"]]},"token":{"@class":"org.springframework.security.oauth2.jwt.Jwt","tokenValue":"token","issuedAt":-31557014167219200.000000000,"expiresAt":31556889864403199.999999999,"headers":{"@class":"java.util.Collections$UnmodifiableMap","alg":"none"},"claims":{"@class":"java.util.Collections$UnmodifiableMap","aud":["java.util.ImmutableCollections$List12",["http://127.0.0.1:9000"]],"exp":["java.time.Instant",31556889864403199.999999999],"iat":["java.time.Instant",-31557014167219200.000000000],"iss":"http://127.0.0.1:9000","jti":"jti","nbf":["java.time.Instant",-31557014167219200.000000000],"sub":"mock_user"},"id":"jti","subject":"mock_user","notBefore":-31557014167219200.000000000,"issuer":"http://127.0.0.1:9000","audience":["java.util.ArrayList",["http://127.0.0.1:9000"]]},"name":"mock_user","tokenAttributes":{"@class":"java.util.Collections$UnmodifiableMap","aud":["java.util.ImmutableCollections$List12",["http://127.0.0.1:9000"]],"exp":["java.time.Instant",31556889864403199.999999999],"iat":["java.time.Instant",-31557014167219200.000000000],"iss":"http://127.0.0.1:9000","jti":"jti","nbf":["java.time.Instant",-31557014167219200.000000000],"sub":"mock_user"}},"org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest":{"@class":"org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest","authorizationUri":"http://localhost/oauth2/authorize","authorizationGrantType":{"value":"authorization_code"},"responseType":{"value":"code"},"clientId":"bluebird-test-client","redirectUri":"http://127.0.0.1:51367","scopes":["java.util.Collections$UnmodifiableSet",["openid","client.read"]],"state":null,"additionalParameters":{"@class":"java.util.Collections$UnmodifiableMap"},"authorizationRequestUri":"http://localhost/oauth2/authorize?response_type=code&client_id=bluebird-test-client&scope=openid%20client.read&redirect_uri=http://127.0.0.1:51367","attributes":{"@class":"java.util.Collections$UnmodifiableMap"}},"state":"uNIPbEQb_cinY4Oa1hOKXhwq1X3eB5QabpIYRXOHww0="}',
        'uNIPbEQb_cinY4Oa1hOKXhwq1X3eB5QabpIYRXOHww0=', '2024-01-18 15:49:07');
