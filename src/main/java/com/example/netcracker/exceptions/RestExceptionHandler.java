package com.example.netcracker.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);
    public static final String EXCEPTION_ATTRIBUTE = "exception";
    public static final String SERVLET_EXCEPTION_ATTRIBUTE = "org.springframework.web.servlet.DispatcherServlet.EXCEPTION";
    public static final String HTTP_STATUS_ATTRIBUTE = "httpStatus";


    @ExceptionHandler({EntityNotFoundException.class})
    protected ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException exception, WebRequest request) {
        LOGGER.error("Can't execute Rest Method {}.", request.getContextPath(), exception);
        return handleException(exception, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({PreconditionException.class})
    protected ResponseEntity<?> handlePreconditionException(PreconditionException exception, WebRequest request) {
        LOGGER.error("Can't execute Rest Method {}.", request.getContextPath(), exception);
        return handleException(exception, HttpStatus.PRECONDITION_FAILED, request);
    }

    @ExceptionHandler({NotAcceptableException.class})
    protected ResponseEntity<?> handleNotAcceptableException(NotAcceptableException exception, WebRequest request) {
        LOGGER.error("Can't execute Rest Method {}.", request.getContextPath(), exception);
        return handleException(exception, HttpStatus.NOT_ACCEPTABLE, request);
    }

    @ExceptionHandler({RuntimeException.class})
    protected ResponseEntity<?> handleRuntimeException(RuntimeException exception, WebRequest request) {
        LOGGER.error("Can't execute Rest Method {}.", request.getContextPath(), exception);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return handleException(exception, status, request);
    }
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable rootCause = ex.getRootCause();
        if (rootCause instanceof InvalidFormatException) {
            InvalidFormatException rootEx = (InvalidFormatException) rootCause;
            return handleException(rootEx, HttpStatus.NOT_ACCEPTABLE, request, rootEx.getOriginalMessage());
        }
        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }

    private ResponseEntity<Object> handleException(Exception exception, HttpStatus status, WebRequest request) {
        return handleException(exception, status, request, exception.getMessage());
    }

    private ResponseEntity<Object> handleException(Exception exception, HttpStatus status, WebRequest request, String message) {
        ErrorResponse errorResponse = new ErrorResponse(exception);
        errorResponse.setCode(status.toString());
        errorResponse.setMessage(message);

        request.setAttribute(EXCEPTION_ATTRIBUTE, errorResponse.getException(), 0);
        request.setAttribute(HTTP_STATUS_ATTRIBUTE, errorResponse.getCode(), 0);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<Object>(errorResponse, headers, status);
    }


}
