package com.mycompany.webapp.service;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service("ch13Service1CreateByAnnotation")
@Log4j2
public class Ch13Service1CreateByAnnotation {
	public Ch13Service1CreateByAnnotation() {
		log.info("실행");
	}
}
