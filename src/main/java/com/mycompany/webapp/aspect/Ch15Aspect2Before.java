package com.mycompany.webapp.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Aspect
@Order(1)   //빠른게 먼저 실행되고 느린게 ㄴ
@Log4j2
public class Ch15Aspect2Before {  //기능에 따라 공통 코드도 분리해서 사용 가능
	@Before("execution(public * com.mycompany.webapp.controller.Ch15Controller.before*(..))")
	public void method() {
		//공통코드
		log.info("실행");
	}
}