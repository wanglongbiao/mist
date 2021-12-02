package com.wanglongbiao.mist;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ServiceException.class, IllegalArgumentException.class, MethodArgumentNotValidException.class})
    public Map<String, Object> handleException(Exception e) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        map.put("data", Collections.emptyMap());
        if (e instanceof MethodArgumentNotValidException) {
            String message = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage();
            map.put("message", message);
        } else {
            map.put("message", e.getMessage());
        }
//        log.error(e.getMessage().toString());
        return map;
    }
}
