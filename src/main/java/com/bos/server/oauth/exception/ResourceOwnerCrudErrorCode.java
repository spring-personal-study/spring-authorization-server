package com.bos.server.oauth.exception;

import com.bos.server.config.exception.common.ApiErrorMessage;
import com.bos.server.config.exception.common.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ResourceOwnerCrudErrorCode implements ErrorCode {

    RO_CRUD_FAIL(BAD_REQUEST, -1401, ApiErrorMessage.RO_CRUD_FAIL),
    RO_NOT_FOUND(NOT_FOUND, -1402, ApiErrorMessage.RO_NOT_FOUND),
    DUPLICATED_RO_ID(CONFLICT, -1404, ApiErrorMessage.DUPLICATED_RO_ID),
    INCORRECT_ID_OR_PASSWORD(BAD_REQUEST, -1406, ApiErrorMessage.INCORRECT_ID_OR_PASSWORD),
    INSUFFICIENT_AUTHORIZATION(UNAUTHORIZED, -1407, ApiErrorMessage.INSUFFICIENT_AUTHORIZATION),
    RO_ID_IS_NULL(BAD_REQUEST, -1408, ApiErrorMessage.RO_ID_IS_NULL),
    RO_ID_IS_EMPTY(BAD_REQUEST, -1409, ApiErrorMessage.RO_ID_IS_EMPTY),
    PASSWORD_IS_NULL(BAD_REQUEST, -1412, ApiErrorMessage.PASSWORD_IS_NULL),
    PASSWORD_IS_EMPTY(BAD_REQUEST, -1413, ApiErrorMessage.PASSWORD_IS_EMPTY),
    AUTH_CODE_IS_NULL(BAD_REQUEST, -1419, ApiErrorMessage.AUTH_CODE_IS_NULL),
    AUTH_CODE_IS_EMPTY(BAD_REQUEST, -1420, ApiErrorMessage.AUTH_CODE_IS_EMPTY),
    AUTH_CODE_HAS_EXPIRED(UNAUTHORIZED, -1422, ApiErrorMessage.AUTH_CODE_HAS_EXPIRED),
    EXPIRED_ACCESS_TOKEN(UNAUTHORIZED, -1431, ApiErrorMessage.ACCESS_TOKEN_EXPIRED),
    INVALID_ACCESS_TOKEN(UNAUTHORIZED, -1432, ApiErrorMessage.INVALID_ACCESS_TOKEN),
    INVALID_REFRESH_TOKEN(UNAUTHORIZED, -1433, ApiErrorMessage.INVALID_REFRESH_TOKEN),
    EXPIRED_REFRESH_TOKEN(UNAUTHORIZED, -1434, ApiErrorMessage.REFRESH_TOKEN_EXPIRED),
    ;

    private static final Map<String, ResourceOwnerCrudErrorCode> bizCodes =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(ResourceOwnerCrudErrorCode::getMsg, Function.identity())));
    private final HttpStatus httpStatus;
    private final Integer bizCode;
    private final String msg;

    public Integer findMatchBizCode(final String failMessage) {
        return Optional.ofNullable(bizCodes.get(failMessage))
                .orElseGet(() -> RO_CRUD_FAIL)
                .getBizCode();
    }

}
