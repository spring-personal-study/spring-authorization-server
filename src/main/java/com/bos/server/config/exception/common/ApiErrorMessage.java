package com.bos.server.config.exception.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiErrorMessage {
    // common error
    public static final String INSUFFICIENT_AUTHORIZATION = "You do not have sufficient privileges to request this process.";
    // end of common error

    // jwt auth error
    public static final String INVALID_JWT = "invalid jwt. please check it again.";
    public static final String EXPIRED_JWT = "jwt refresh token has been expired. please id/password login again.";
    public static final String MISSING_JWT = "jwt was not passed. please check it again.";
    // end of jwt auth error

    // user error
    public static final String RO_CRUD_FAIL = "user requests has failed.";
    public static final String RO_NOT_FOUND = "The user could not be found.";
    public static final String INCORRECT_ID_OR_PASSWORD = "userId or password is incorrect.";
    public static final String DUPLICATED_RO_ID = "the user's id already exists. Please try other one.";
    public static final String RO_ID_IS_NULL = "userId is null. it must be provided.";
    public static final String RO_ID_IS_EMPTY = "userId must not be empty.";
    public static final String PASSWORD_IS_NULL = "password is null. it must be provided.";
    public static final String PASSWORD_IS_EMPTY = "password must not be empty.";
    public static final String AUTH_CODE_IS_NULL = "auth code is null. it must be provided.";
    public static final String AUTH_CODE_IS_EMPTY = "auth code must not be empty.";
    public static final String AUTH_CODE_HAS_EXPIRED = "Invalid authentication code or the code has expired.";
    public static final String ACCESS_TOKEN_EXPIRED = "access token has been expired. renew access token with a refresh token. or login again please.";
    public static final String REFRESH_TOKEN_EXPIRED = "cannot find user's refresh token. refresh token has expired. or check user has been removed.";
    public static final String INVALID_ACCESS_TOKEN = "invalid access token. please check it again.";
    public static final String INVALID_REFRESH_TOKEN = "invalid refresh token. please check it again.";
    // end of user error
}
