package com.mycompany.webapp.controller;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mycompany.webapp.dto.Ch08InputForm;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch08")
@Log4j2
@SessionAttributes({"inputForm"})
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
		log.info("dd");
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
	
	@RequestMapping(value="/loginAjax", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String loginAjax(String mid, String mpassword, HttpSession session) {
		//data = {result:"success"}
		//data = {result:"wrongMid"}
		//data = {result:"wrongMpassword"}
		String result = null;
		if(mid.equals("spring")) {
			if(mpassword.equals("12345")) {
				result="success";
				session.setAttribute("sessionMid", mid);
			}else {
				result="wrongMpassword";
			}
		}else {
			result="wrongMid";
		}
		
		JSONObject j = new JSONObject();
		j.put("result", result);
		String json = j.toString();
		
		return json;
	}
	
	@RequestMapping(value="/logoutAjax", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String logoutAjax(HttpSession session) {
		session.removeAttribute("sessionMid");
		//session.invalidate();
		JSONObject j = new JSONObject();
		j.put("result", "success");
		String json = j.toString();
		
		return json;
	}
	
	//새로운 세션 저장소에 객체를 저장하는 역할, 단 1번만 실행된다. -> @SessionAttributes({"inputForm"})있으면
	//세션에 없을 경우 새로 객체 생성, 세션에 있을 경우 메소드를 실행하지 X(기존의 객체 사용)
	@ModelAttribute("inputForm")
	public Ch08InputForm getCh08InputForm() {
		Ch08InputForm inputForm = new Ch08InputForm();
		return inputForm;
	}
	
	@RequestMapping("/inputStep1")
	public String inputStep1() {
		return "ch08/inputStep1";
	}
	
	@PostMapping("/inputStep2")
	public String inputStep2(@ModelAttribute("inputForm") Ch08InputForm inputForm) {
		log.info("data1: "+inputForm.getData1());
		log.info("data2: "+inputForm.getData2());
		
		return "ch08/inputStep2";
	}
	
	@PostMapping("/inputDone")
	public String inputStep3(@ModelAttribute("inputForm") Ch08InputForm inputForm, SessionStatus status) {
		log.info("data1: "+inputForm.getData1());
		log.info("data2: "+inputForm.getData2());
		log.info("data3: "+inputForm.getData3());
		log.info("data4: "+inputForm.getData4());
		
		status.setComplete(); //세션 객체 제거
		
		return "redirect:/ch08/content";
	}
	
	
}
