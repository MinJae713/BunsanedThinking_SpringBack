package com.example.bunsanedthinking_springback.global.common;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
	// @ExceptionHandler({IllegalArgumentException.class, NotExistException.class, NotExistContractException.class,
	// 	NotExistException.class, AlreadyProcessedException.class, AlreadyProcessedException.class,
	// })
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<String>> handlerException(MethodArgumentNotValidException e) {
		e.printStackTrace();
		List<String> messages = e.getBindingResult().getAllErrors().stream()
				.map(ObjectError::getDefaultMessage)
				.toList();
		return ResponseEntity.badRequest().body(messages);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handlerException(Exception e) {
		e.printStackTrace();
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
