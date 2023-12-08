package com.bos.server.config.exception.common;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String getMsg();

    Integer getBizCode();

    HttpStatus getHttpStatus();

    Integer findMatchBizCode(String failMessage);

}