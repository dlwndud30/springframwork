package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.dto.Ch09Dto;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch09")
@Log4j2
public class Ch09Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch09/content";
	}
	
	@PostMapping("fileupload")
	//파일이 여러개가 넘어오는 경우 :  MultipartFile attach1, MultipartFile attach2 또는 똑같은 이름으로 넘어오는 경우는 배열로 받으면 된다
	public String fileupload(String title, String desc, MultipartFile attach) throws Exception{
		log.info("title: "+title);
		log.info("desc: "+desc);
		log.info("file originalname: "+attach.getOriginalFilename());
		log.info("file contextType: "+attach.getContentType());
		log.info("file size: "+attach.getSize());
		
		//파일의 순수 데이터 (나중에 DB에 저징할 때 사용)
		//byte[] bytes = attach.getBytes();
		//InputStream is = attach.getInputStream();
		
		//받은 파일을 DB에 저장할 때 사용
		String saveFilename = new Date().getTime()+"-"+attach.getOriginalFilename();
		File file = new File("C:/Temp/uploadfiles/" + saveFilename);
		attach.transferTo(file);  //클라이언트가 보낸 파일을 서버에 저장
		
		return "redirect:/ch09/content";
	}
/*	
	@PostMapping(value="/fileuploadAjax", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String fileuploadAjax(String title, String desc, MultipartFile attach) throws Exception{
		
		log.info("title: "+title);
		log.info("desc: "+desc);
		log.info("file originalname: "+attach.getOriginalFilename());
		log.info("file contextType: "+attach.getContentType());
		log.info("file size: "+attach.getSize());
		
		//파일의 순수 데이터 (나중에 DB에 저징할 때 사용)
		//byte[] bytes = attach.getBytes();
		//InputStream is = attach.getInputStream();
		
		//받은 파일을 DB에 저장할 때 사용
		String saveFilename = new Date().getTime()+"-"+attach.getOriginalFilename();
		File file = new File("C:/Temp/uploadfiles/" + saveFilename);
		attach.transferTo(file);  //클라이언트가 보낸 파일을 서버에 저장
		
		JSONObject j = new JSONObject();
		j.put("result", "success");
		j.put("saveFilename", saveFilename);
		String json = j.toString();
		
		return json;
	}
	*/
	//위에랑 똑같음
	@PostMapping(value="/fileuploadAjax", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String fileuploadAjax(Ch09Dto dto) throws Exception{
		
		log.info("title: "+dto.getTitle());
		log.info("desc: "+dto.getDesc());
		log.info("file originalname: "+dto.getAttach().getOriginalFilename());
		log.info("file contextType: "+dto.getAttach().getContentType());
		log.info("file size: "+dto.getAttach().getSize());
		
		//파일의 순수 데이터 (나중에 DB에 저징할 때 사용)
		//byte[] bytes = attach.getBytes();
		//InputStream is = attach.getInputStream();
		
		//받은 파일을 DB에 저장할 때 사용
		String saveFilename = new Date().getTime()+"-"+dto.getAttach().getOriginalFilename();
		File file = new File("C:/Temp/uploadfiles/" + saveFilename);
		dto.getAttach().transferTo(file);  //클라이언트가 보낸 파일을 서버에 저장
		
		JSONObject j = new JSONObject();
		j.put("result", "success");
		j.put("saveFilename", saveFilename);
		String json = j.toString();
		/*
		byte[] bytes = ...   //이렇게 해도 되지만 파일 사이즈가 큰 경우 
		return bytes;*/
		
		return json;
	}
	
	//파일 다운로드
	@GetMapping("/filedownload")
	public void filedownload(int fileNo, HttpServletResponse response, @RequestHeader("User-Agent")String userAgent) throws Exception {
		//나중에는 DB에서 가져와야할 정보들
		String contentType = "image/jpeg";
		String originalFilename = "photo2.jpg";
		String saveFilename = "1650135847686-photo2.jpg";
		
		//응답 내용의 데이터 타입을 응답 헤더에 추가
		response.setContentType(contentType);   //= response.setHeader("Content-Type", contentType);
		
		
		//다운로드할 파일명을 헤더에 추가 : 브라우저별로 한글을 처리하는 방식이 다름
		if(userAgent.contains("Trident") || userAgent.contains("MSIE")) {
			//IE 브라우저일 경우
			originalFilename = URLEncoder.encode(originalFilename, "UTF-8");
		}else {
			//크롬, 엣지, 사파리일 경우
			originalFilename = new String(originalFilename.getBytes("UTF-8"), "ISO-8859-1");
		}
		
		//Content-Disposition : HTTP Response Body에 오는 컨텐츠의 기질/성향을 알려주는 속성
		//attachment + filename을 써주면 Body에 오는 값을 다운로드 받으라는 뜻이다
		response.setHeader("Content-Disposition", "attachment; filename=\""+ originalFilename + "\"");
		
		//파일 데이터를 응답 본문에 실기
		File file = new File("C://Temp/uploadfiles/" + saveFilename);  //C://Temp/uploadfiles/" + saveFilename에 있는 파일들의 file 객체를 생성
		if(file.exists()) {  //파일의 존재 여부를 리턴
			FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
		}
	}
}
