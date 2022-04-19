package com.mycompany.webapp.service;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
//외부에서 가져온 .jar파일 안에 있는 객체라고 가정
public class Ch13Service2CreateByXML {
	public Ch13Service2CreateByXML() {
		log.info("실행");
	}
	
	//static : 클래스없이 호출 가능
	public static Ch13Service2CreateByXML getInstance1() {
		log.info("실행");
		Ch13Service2CreateByXML obj = new Ch13Service2CreateByXML();
		return obj;
	}
	
	public Ch13Service2CreateByXML getInstance2() {
		log.info("실행");
		Ch13Service2CreateByXML obj = new Ch13Service2CreateByXML();
		return obj;
	}
}
