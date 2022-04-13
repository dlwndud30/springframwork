package com.mycompany.webapp.controller;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/ch05")
public class Ch05Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch05/content";
	}
	
	//요청 HTTP 헤더값 얻기
	@GetMapping("/getHeaderValue")
	public String getHeaderValue(HttpServletRequest request, @RequestHeader("User-Agent") String userAgent) {
		log.info("Client IP: " + request.getRemoteAddr());
		log.info("Request Method: " + request.getMethod());  //요청 방식 "GET"
		log.info("Context Path(Root): " + request.getContextPath());  //"/webapp"
		log.info("Request URI: " + request.getRequestURI());   //"/webapp/ch05/getHeaderValue"
		log.info("Request URI: " + request.getRequestURL());   //"http://localhost:8080/webapp/ch05/getHeaderValue"
		log.info("Header User-Agent: " + request.getHeader("User-Agent"));   //"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.75 Safari/537.36"
		log.info(userAgent);
		
		return "redirect:/ch05/content";
	}
	
	//쿠키 생성하기
	@GetMapping("/createCookie")
	public String createCookie(HttpServletResponse response) {
		log.info("OK");
		
		Cookie cookie = new Cookie("useremail", "blueskii@naver.com");
	     cookie.setDomain("localhost");    //localhost로 요청을 했을 때만 쿠키를 들고 와
	     cookie.setPath("/webapp/ch05");   //localhost:8080/webapp/ch05... 이면 쿠키를 들고 와
	     cookie.setMaxAge(30*60);      	   //이 시간까지만 브라우저에 쿠키가 저장된다 -> 30*60 : 30분
	     cookie.setHttpOnly(false);         //true = 서버와 클라이언트가 통신할 때만 쿠키를 들고 가게함 -> JavaScript에서는 쿠키를 못읽음
	     cookie.setSecure(false);          //true = https://만 전송 -> 보안상으로 더 유리
	     response.addCookie(cookie);       //응답에 쿠키 추가
	     
		 cookie = new Cookie("userId", "spring");
	     cookie.setDomain("localhost");    //localhost로 요청을 했을 때만 쿠키를 들고 와
	     cookie.setPath("/webapp/ch05");   //localhost:8080/webapp/ch05... 이면 쿠키를 들고 와
		 cookie.setMaxAge(30*60);      	   //이 시간까지만 브라우저에 쿠키가 저장된다 -> 30*60 : 30분
         cookie.setHttpOnly(false);         //서버와 클라이언트가 통신할 때만 쿠키를 들고 가게함 -> JavaScript에서는 쿠키를 못읽음
		 cookie.setSecure(false);          //false = https 또는 http 전송
		 response.addCookie(cookie);       //응답에 쿠키 추가
		
		return "redirect:/ch05/content";
	}

	//쿠키 읽기 : 쿠키의 값을 받아서 매개변수에 저장
	@GetMapping("/getCookie1")
	public String getCookie1(HttpServletRequest request) {
		log.info("OK");
		Cookie[] cookies = request.getCookies();
		for(Cookie c : cookies) {
			String cookieName = c.getName();
			String cookieValue = c.getValue();
			log.info(cookieName + ": " + cookieValue);
		}
		return "redirect:/ch05/content";
	}
	
	//쿠키 읽기 : 쿠키의 값을 받아서 매개변수에 저장
	@GetMapping("/getCookie2")
	public String createJsonCookie(@CookieValue String userId, @CookieValue String useremail) {
		log.info("userId: "+userId);
		log.info("useremail: "+useremail);
		
		return "redirect:/ch05/content";
	}
	
	
	//JSON 쿠키 생성
	@GetMapping("createJsonCookie")
	public String getCookie2(HttpServletResponse response) throws Exception{
		String json = "{\"userId\":\"spring\"}";
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("useremail", "spring@naver.com");
		jsonObject.put("username", "spring");
		json=jsonObject.toString();  //{"useremail":"spring@naver.com","username":"spring"}
		log.info(json);
		json = URLEncoder.encode(json, "UTF-8");  //%7B%22useremail%22%3A%22spring%40naver.com%22%2C%22username%22%3A%22spring%22%7D
		log.info(json);

		Cookie cookie = new Cookie("user", json);
		response.addCookie(cookie);
	
		return "redirect:/ch05/content";
	}
	
	//JSON 쿠키 읽기
	@GetMapping("getJsonCookie")
	public String getJsonCookie(@CookieValue String user){
		log.info(user);
		JSONObject jsonObject = new JSONObject(user);  //자바 객체로 JSONObject이 얻어진다
		String username = jsonObject.getString("username");
		String useremail = jsonObject.getString("useremail");
		
		log.info("username: "+username);
		log.info("useremail: "+useremail);

		return "redirect:/ch05/content";
	}
	
}
