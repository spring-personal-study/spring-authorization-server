package com.bos.server.oauth.exception;


import com.bos.server.config.exception.common.ErrorResponseDTO;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.bos.server.config.exception.controllerAdvice.GeneralControllerAdvice.handleValidParameterException;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class UserControllerAdvice {

    /**
     * Operates when validation fails through @Valid or @Validated annotations.
     * If you set the errors' argument value
     * when handling exception responses for controller methods that use @Valid or @Validated annotations,
     * more detailed error messages can be delivered to the client.
     *
     * @param e InvalidParameterException
     * @return 400 (Bad Request: invalid parameter error)
     * @see ErrorResponseDTO
     */
    @ExceptionHandler(InvalidResourceOwnerParameterException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidPostParameterException(InvalidResourceOwnerParameterException e) {
        return handleValidParameterException(e.getHttpStatus(), e.getErrorCode(), e);
    }
}