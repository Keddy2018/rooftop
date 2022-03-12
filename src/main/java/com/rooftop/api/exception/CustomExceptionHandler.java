package com.rooftop.api.exception;

import com.rooftop.api.dto.FormatErrorDto;
import static com.rooftop.api.util.Constants.TEXT_NOT_CONTROLLED;
import java.security.NoSuchAlgorithmException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return createFormatExcpetion(ex, status);
    }

    @ExceptionHandler({NotExistRecordExeption.class})
    private ResponseEntity<Object> notExisxtRecord(Exception ex) {
        return createFormatExcpetion(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({Exception.class,
        MethodArgumentTypeMismatchException.class,
        NoSuchAlgorithmException.class})
    private ResponseEntity<Object> notControlledException(Exception ex) {
        return createFormatExcpetion(new NotControlledExeption(TEXT_NOT_CONTROLLED), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private ResponseEntity<Object> createFormatExcpetion(Exception ex, HttpStatus status) {
        FormatErrorDto format = new FormatErrorDto();
        format.setError(Boolean.TRUE);
        format.setCode(status.value());
        format.setMessage(ex.getMessage());
        return ResponseEntity.status(status.value()).body(format);
    }
}
