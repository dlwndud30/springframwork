package com.mycompany.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller("ch13Controller")  //이 이름으로 Ch13Controller를 관리하겠다
@RequestMapping("/ch13")
@Log4j2
public class Ch13Controller {
	public Ch13Controller() {
		//ch13Controller 객체가 생성되었다.
		log.info("실행");
	}
	
	@RequestMapping("content")
	public String content() {
		log.info("실행");
		return "ch13/content";
	}
	
	
}
