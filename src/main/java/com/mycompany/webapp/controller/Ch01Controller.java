package com.mycompany.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ch01")
//@Log4j2
public class Ch01Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch01Controller.class);

	@RequestMapping("/content")   //url만 맞으면 모든 방식이 다 허용됨
	public String content(){
		logger.info("실행");
		//log.info("싫ㅇ");
		return "ch01/content";
	}
}
