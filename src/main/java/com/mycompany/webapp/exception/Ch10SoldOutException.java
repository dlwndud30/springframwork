package com.mycompany.webapp.exception;

//사용자 정의 Exception 만들기
public class Ch10SoldOutException extends RuntimeException {
	public Ch10SoldOutException() {
		super("품절");
	}
	
	public Ch10SoldOutException(String message) {
		super(message);
	}
}
