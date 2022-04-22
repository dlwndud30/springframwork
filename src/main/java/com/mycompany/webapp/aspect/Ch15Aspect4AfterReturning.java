package com.mycompany.webapp.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Component
@Aspect
@Log4j2
public class Ch15Aspect4AfterReturning {
	@AfterReturning(
		    pointcut="execution(public * com.mycompany.webapp.controller.Ch15Controller.afterReturning*(..))",
		    returning="returnValue" 		
	)
	
	public void method(String returnValue) {  //pointcut 메소드가 리턴한 값이 returnValue에 들어온다 
		//공통 코드
		log.info("실행");
		log.info("리턴값: "+returnValue);

	}
	
}
