package com.app.conation.advice;

import com.app.conation.exception.JWTDecodeException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.CommunicationException;
import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@AllArgsConstructor
public class SignInAdvisor {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    protected ResponseEntity<ErrorResponse> tokenValidationException(MethodArgumentNotValidException ex) {
//        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.INVALID_REQUEST_DATA), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(JWTDecodeException.class)
//    public ResponseEntity<ErrorResponse> jwtDecodeException(JWTDecodeException ex) {
//        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.NOT_INVALID_JWT_TOKEN), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(CommunicationException.class)
//    public ResponseEntity<ErrorResponse> communicationException(CommunicationException ex) {
//        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.COMMUNICATION_ERROR), HttpStatus.BAD_REQUEST);
//    }
}
