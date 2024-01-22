CREATE TABLE oauth_client
(
    id                            bigint                      NOT NULL auto_increment,
    client_id                     varchar(255)                NOT NULL,
    client_id_issued_at           datetime      DEFAULT NOW() NOT NULL,
    client_secret                 varchar(255)  DEFAULT NULL,
    client_secret_expires_at      datetime      DEFAULT NULL,
    client_name                   varchar(255)                NOT NULL,
    client_authentication_methods varchar(1000)               NOT NULL,
    authorization_grant_types     varchar(1000)               NOT NULL,
    redirect_uris                 varchar(1000) DEFAULT NULL,
    post_logout_redirect_uris     varchar(1000) DEFAULT NULL,
    scopes                        varchar(1000)               NOT NULL,
    client_settings               varchar(2000)               NOT NULL,
    token_settings                varchar(2000)               NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE oauth_authorization_consent
(
    registered_client_id bigint        NOT NULL,
    principal_name       varchar(255)  NOT NULL,
    authorities          varchar(1000) NOT NULL,
    PRIMARY KEY (registered_client_id, principal_name)
);

CREATE TABLE oauth_authorization
(
    id                       varchar(255) NOT NULL,
    registered_client_id     varchar(255) NOT NULL,
    principal_name           varchar(255) NOT NULL,
    authorization_grant_type varchar(255) NOT NULL,
    authorized_scopes        varchar(1000) DEFAULT NULL,
    `attributes`             varchar(4000) DEFAULT NULL,
    state                    varchar(500)  DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE oauth_authorization_code
(
    id                            bigint NOT NULL auto_increment,
    authorization_code_value      varchar(4000) DEFAULT NULL,
    authorization_code_issued_at  datetime      DEFAULT NULL,
    authorization_code_expires_at datetime      DEFAULT NULL,
    authorization_code_metadata   varchar(2000) DEFAULT NULL,
    authorization_id              bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (authorization_id) REFERENCES oauth_authorization (id)
);

CREATE TABLE oauth_access_token
(
    id                      bigint NOT NULL auto_increment,
    access_token_value      varchar(4000) DEFAULT NULL,
    access_token_issued_at  datetime      DEFAULT NULL,
    access_token_expires_at datetime      DEFAULT NULL,
    access_token_metadata   varchar(2000) DEFAULT NULL,
    access_token_type       varchar(255)  DEFAULT NULL,
    access_token_scopes     varchar(1000) DEFAULT NULL,
    authorization_id        bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (authorization_id) REFERENCES oauth_authorization (id)
);

CREATE TABLE oauth_refresh_token
(
    id                       bigint NOT NULL auto_increment,
    refresh_token_value      varchar(4000) DEFAULT NULL,
    refresh_token_issued_at  datetime      DEFAULT NULL,
    refresh_token_expires_at datetime      DEFAULT NULL,
    refresh_token_metadata   varchar(2000) DEFAULT NULL,
    authorization_id         bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (authorization_id) REFERENCES oauth_authorization (id)
);

CREATE TABLE oauth_oidc_token
(
    id                       bigint NOT NULL auto_increment,
    oidc_id_token_value      varchar(4000) DEFAULT NULL,
    oidc_id_token_issued_at  datetime      DEFAULT NULL,
    oidc_id_token_expires_at datetime      DEFAULT NULL,
    oidc_id_token_metadata   varchar(2000) DEFAULT NULL,
    oidc_id_token_claims     varchar(2000) DEFAULT NULL,
    authorization_id         bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (authorization_id) REFERENCES oauth_authorization (id)
);

CREATE TABLE oauth_device_code
(
    id                     bigint NOT NULL auto_increment,
    device_code_value      varchar(4000) DEFAULT NULL,
    device_code_issued_at  datetime      DEFAULT NULL,
    device_code_expires_at datetime      DEFAULT NULL,
    device_code_metadata   varchar(2000) DEFAULT NULL,
    authorization_id       bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (authorization_id) REFERENCES oauth_authorization (id)
);

CREATE TABLE oauth_user_code
(
    id                   bigint NOT NULL auto_increment,
    user_code_value      varchar(4000) DEFAULT NULL,
    user_code_issued_at  datetime      DEFAULT NULL,
    user_code_expires_at datetime      DEFAULT NULL,
    user_code_metadata   varchar(2000) DEFAULT NULL,
    authorization_id     bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (authorization_id) REFERENCES oauth_authorization (id)
);