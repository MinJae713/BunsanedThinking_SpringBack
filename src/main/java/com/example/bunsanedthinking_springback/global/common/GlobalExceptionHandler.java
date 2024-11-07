package com.example.bunsanedthinking_springback.global.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	// @ExceptionHandler({IllegalArgumentException.class, NotExistException.class, NotExistContractException.class,
	// 	NotExistException.class, AlreadyProcessedException.class, AlreadyProcessedException.class,
	// })
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handlerException(Exception e) {
		e.printStackTrace();
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
