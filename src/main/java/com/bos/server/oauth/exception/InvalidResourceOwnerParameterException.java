package com.bos.server.oauth.exception;



import com.bos.server.config.exception.common.ErrorCode;
import com.bos.server.config.exception.common.InvalidParameterException;
import org.springframework.validation.Errors;

public class InvalidResourceOwnerParameterException extends InvalidParameterException {
    public InvalidResourceOwnerParameterException(Errors errors, ErrorCode errorCode) {
        super(errors, errorCode);
    }
}
