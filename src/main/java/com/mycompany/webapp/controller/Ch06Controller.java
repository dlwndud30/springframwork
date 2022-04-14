package com.mycompany.webapp.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch06")
@Log4j2
public class Ch06Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch06/content";
	}
	
	@GetMapping("/forward")
	public String forward() {
		return "ch06/forward";
	}
	
	@GetMapping("/redirect")
	public String redirect() {	
		return "redirect:/";
	}
	
	@GetMapping("/getFragmentHtml")
	public String getFragmentHtml() {
		//반드시 html 조각을 보내줘야 함 -> redirct 하면 안돼!!
		return "ch06/fragmentHtml";
	}
	
	@GetMapping("/getJson1")
	public void getJson1(HttpServletResponse response) throws Exception{
		//{"fileName":"phpto6.jpg"}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fileName", "photo6.jpg");
		String json = jsonObject.toString();
		
		//응답의 유형(보내고자하는 데이터의 타입)이 뭔지 설정 : 응답의 타입이 뭔지
		//한글이 포함되어 있다면 어떤 걸로 인코딩 했는지 써줘야함 -> charset=UTF-8
		response.setContentType("application/json; charset=UTF-8");  
		
		//서버와 데이터를 주고 받을 때는 입출력 스트림을 사용
		//클라이언트로 가는 출력 스트림을 얻어냄 : response.getWriter()
		PrintWriter pw = response.getWriter();
		pw.write(json);  //pw.println(json); 써도 된다
		pw.flush();
		pw.close();
		
		//jsp로 가지 않고 응답을 바로 만들었으므로(jsp forward X) return이 필요 없음
	}
	
	@GetMapping(value="/getJson2", produces = "application/json; charset=UTF-8")
	@ResponseBody  //리턴되는 내용이 응답 본문에 들어간다. -> 얘가 있으면 return되는 내용이 뷰 이름으로 해석되지 X
	public String getJson2(HttpServletResponse response) throws Exception{
		//{"fileName":"phpto6.jpg"}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fileName", "photo6.jpg");
		String json = jsonObject.toString();
		
		//view이름이 아니라 응답 http 본문에 들어가는 실제 내용임
		return json;
	}
	
	//AJAX로 redirect하면 어똫게 되는가 -> 404에러
	//AJAX는 반드시 응답을 제공해 줘야함 -> redirect 못해
	@GetMapping("/getJson3")
	public String getJson3() {
		
		return "redirect:/";
	}
	
	
}
