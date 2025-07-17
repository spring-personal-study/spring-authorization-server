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

INSERT INTO tb_user (id, password) VALUES ('admin', '$2a$10$N0myA.3g2rxvQ2VqVBSi..xG2iYyC3b.2wJd8l8j5N.4uG5v/T5y.');
