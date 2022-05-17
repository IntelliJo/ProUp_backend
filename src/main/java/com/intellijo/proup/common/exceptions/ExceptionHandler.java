package com.intellijo.proup.common.exceptions;

import com.intellijo.proup.common.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.Properties;

/**
 * Exception을 aop로 핸들링 할수 있게 처리한다.
 * 처리하고 싶은 Exception을 ExceptionHandler(처리하고싶은Exception.class)로 작성하여 반환한다.
 * 해당 내용을 개발자가 확인 할 수 있게 mail로 보내거나, db에 저장
 */
public class ExceptionHandler {

    String resource = "properties/code.properties";
    Properties properties = new Properties();

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .status(201)
                .timestamp(new Date())
                .message(ex.getMessage())
                .details(request.getDescription(false)).build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
