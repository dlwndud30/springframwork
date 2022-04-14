package com.mycompany.webapp.controller;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch08")
@Log4j2

public class Ch08Controller {
	@RequestMapping("/content")
	public String content() {
		return"ch08/content";
	}
	
	@GetMapping(value="/saveData", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String saveData(String name, HttpSession session) {
		session.setAttribute("name", name);
		
		//{"result": "success"} -> 배열 : JSONObject
		//["result": "success"] -> array : JSONArray
		JSONObject j = new JSONObject();
		j.put("result", "success");
		String json = j.toString();
		
		return json;
	}
	
	@GetMapping(value="/readData", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String readData(HttpSession session) {
		String name = (String) session.getAttribute("name");
		
		//{"name": "홍길동"}
		JSONObject j = new JSONObject();
		j.put("name", name);
		String json = j.toString();
		
		return json;
	}
	
	@RequestMapping("/login")
	public String loginForm() {
		
		return"ch08/loginForm";
	}
	
	@PostMapping("/login")
	public String login(String mid, String mpassword, HttpSession session) {
		
		//로그인 성공 시 세션에 회원 아이디를 저장
		if(mid.equals("spring") && mpassword.equals("12345")) {
			session.setAttribute("sessionMid", mid);
		}
		return"redirect:/ch08/content";
	}
	
	@RequestMapping("/logout")
	public String logoutForm(HttpSession session) {
		//세션에서 주어진 키를 완전히 삭제
		//session.removeAttribute("sessionMid");
		//세션 객체 자체를 제거 : 안에 뭐가 들어가 있던지 다 날아감
		session.invalidate();  //session을 무효화시키겠다
		return"redirect:/ch08/content";
	}
	
	@RequestMapping("/userinfo")
	public String userInfo(@SessionAttribute String sessionMid, @SessionAttribute("sessionMid") String mid, HttpSession session) {
		//방법1
		log.info(sessionMid);
		
		//방법2
		log.info(mid);
	
		//방법3
		String id = (String) session.getAttribute("sessionMid");
		log.info(id);
		return"redirect:/ch08/content";
	}
	
	
}
