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
	//모든 컨트롤러에서 NullPointerException이 발생할 때 실행된다
	@ExceptionHandler(NullPointerException.class)
	public String handleNullPointerException(NullPointerException e) {
		log.info("실행: " + e.getMessage());
		return "ch10/500_null";
	}

	@ExceptionHandler(ClassCastException.class)
	public String handleClassCastException(ClassCastException e) {
		log.info("실행: " + e.getMessage());
		return "ch10/500_cast";
	}
	
	@ExceptionHandler(Ch10SoldOutException.class)
	public String handleCh10SoldOutException(Ch10SoldOutException e) {
		log.info("실행: " + e.getMessage());
		return "ch10/soldout";
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //상태코드가 500으로 가게
	public String handleException(Exception e) {
		log.info("실행: " + e.getMessage());
		return "ch10/500";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)  //상태코드가 404로 가게
	public String handleNoHandlerFoundException(NoHandlerFoundException e) {
		log.info("실행: " + e.getMessage());
		return "ch10/404";
	}
	
}
