package com.mycompany.webapp.service;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.Ch13Dao2CreateByXML;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class Ch13Service6InjectByXml {
	private Ch13Dao2CreateByXML dao;
	private String str;
	private List collection1;  //<>안넣으면 기본적으로 모든 타입이 다 들어갈 수 있다
	private Set collection2;	//<>안넣으면 기본적으로 모든 타입이 다 들어갈 수 있다
	private Map collection3;	//<>안넣으면 기본적으로 모든 타입이 다 들어갈 수 있다
	private Properties collection4;  //Map 타입인데 키와 값이 모두 스트링 타입으로 들어가야 한다

	
	public Ch13Service6InjectByXml() {
		log.info("실행: Ch13Service6InjectByXml()");
	}
	
	public Ch13Service6InjectByXml(Ch13Dao2CreateByXML dao, String str) {
		log.info("실행: Ch13Service6InjectByXml(Ch13Dao2CreateByXML dao, String str)");
		this.dao=dao;
		this.str = str;
	}

	public void setDao(Ch13Dao2CreateByXML dao) {
		log.info("실행");
		this.dao = dao;
	}

	public void setStr(String str) {
		log.info("실행");
		this.str = str;
	}

	public void setCollection1(List collection1) {
		log.info("실행");
		this.collection1 = collection1;
	}

	public void setCollection2(Set collection2) {
		log.info("실행");
		this.collection2 = collection2;
	}

	public void setCollection3(Map collection3) {
		log.info("실행");
		this.collection3 = collection3;
	}

	public void setCollection4(Properties collection4) {
		log.info("실행");
		this.collection4 = collection4;
	}
	
}
