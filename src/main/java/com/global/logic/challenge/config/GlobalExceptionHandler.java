package com.global.logic.challenge.config;

import com.global.logic.challenge.domain.error.ErrorDTO;
import com.global.logic.challenge.domain.error.ErrorResponseDTO;
import com.global.logic.challenge.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Freddy Paredes
 * This class handle all Exceptions
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private final static int GENERIC_ERROR_CODE= 400; //code in responseBody


    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleException(Exception e) {
        List<ErrorDTO> errors = new ArrayList<>();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        errors.add(ErrorDTO.builder()
                .codigo(GENERIC_ERROR_CODE).detail( e.getMessage()).timestamp(timestamp).build());
        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }//method closure

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<ErrorDTO> errors = new ArrayList<>();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        result.getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.add(ErrorDTO.builder()
                    .codigo(GENERIC_ERROR_CODE).detail("["+field+"] - " + message).timestamp(timestamp).build());
        });

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ ServiceException.class })
    public ResponseEntity<ErrorResponseDTO> handleSchemaException(ServiceException ex, WebRequest request) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder().codigo(ex.getCode().value()).detail(ex.getMessage()).build());
        return buildResponseEntity(errors, ex.getCode());
    }

    public ResponseEntity<ErrorResponseDTO> buildResponseEntity(List<ErrorDTO> errors, HttpStatus status) {
        ErrorResponseDTO responseError = new ErrorResponseDTO();
        responseError.setError(errors);
        errors.forEach( error-> {
            log.error(error.getDetail());
                }
        );
        return new ResponseEntity<>(responseError, status);
    }//method closure
}//class closure
