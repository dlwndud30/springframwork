package com.mycompany.webapp.dao;

import org.springframework.stereotype.Repository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository("ch13Dao1CreateByAnno")  //유일한 이름으로 줘야함
public class Ch13Dao1CreateByAnno {
	public Ch13Dao1CreateByAnno() {
		log.info("실행");  //객체가 생성되었는지 확인
	}
}
