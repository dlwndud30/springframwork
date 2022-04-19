package com.mycompany.webapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j2;

@Component
@ControllerAdvice
@Log4j2
public class Ch10ExceptionHandler {
	@ExceptionHandler(NullPointerException.class)
	public String handleNullPointerException(NullPointerException e) {
		log.info(e.getMessage());
		return "ch10/500_null";
	}
	
	@ExceptionHandler(ClassCastException.class)
	public String handleClassCastException(ClassCastException e) {
		log.info(e.getMessage());
		return "ch10/500_cast";
	}
	
	@ExceptionHandler(Ch10SoldOutException.class)
	public String handleCh10SoldOutException(Ch10SoldOutException e) {
		log.info(e.getMessage());
		return "ch10/soldout";
	}
}
