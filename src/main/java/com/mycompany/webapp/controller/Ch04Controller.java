package com.mycompany.webapp.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.Validator.Ch04MemberEmailValidator;
import com.mycompany.webapp.Validator.Ch04MemberIdValidator;
import com.mycompany.webapp.Validator.Ch04MemberJoinFormValidator;
import com.mycompany.webapp.Validator.Ch04MemberPasswordValidator;
import com.mycompany.webapp.Validator.Ch04MemberTelValidator;
import com.mycompany.webapp.dto.Ch04Dto;
import com.mycompany.webapp.dto.Ch04Member;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch04")
@Log4j2
public class Ch04Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch04/content";
	}
	
	@PostMapping("/method1")
	public String method1(Ch04Dto dto) {
		log.info(dto.toString());
		return "ch04/content";
	}
	
	//Dto와 유효성 검사기를 연결해줘야함
	@InitBinder("ch04Member")   //초기에 엮어주는 역할
	public void bindCh04MemberJoinFormValidator(WebDataBinder binder) {
		//form 단위
		//binder.setValidator(new Ch04MemberJoinFormValidator());
		
		//필드 단위
		binder.addValidators(
				new Ch04MemberIdValidator(),
		        new Ch04MemberPasswordValidator(),
		        new Ch04MemberEmailValidator(),
		        new Ch04MemberTelValidator());
	}
	
	@PostMapping("/join")
	//BindingResult bindingresult대신 Errors errors를 써도된다 -> 유효성 검사 결과가 여리에 저장된다
	//@Valid: 유효성 검사를 하라고 지시 -> 유효성 검사를 하려면 이걸 써줘야함
	public String join(@Valid Ch04Member member, Errors errors) {   //Ch04Member는 jsp 파일에서도 사용 가능 -> ch04Member로 Ch04Member 객체를 얻어서 사용 가능(참조 이름을 바꿀 수도 있음 : @ModelAttribute("참조하고 싶은 이름") -> 이 변수로 Ch04Member 객체를 참조하겠다는 뜻)
		log.info("실행");
		
		if(errors.hasErrors()) { 
			return "ch04/content";  //다시 입력 폼으로 돌아간다
		}
		
		//회원 가입 처리
		//...
		
		//홈페이지로 이동
		return "redirect:/";  //클라이언트가 다시 홈을 요청 -> 홈으로 돌아간다
	}
	
	@InitBinder("loginForm")
	public void bindCh04MemberLoginFormValidator(WebDataBinder binder) {
		//form 단위
		//binder.setValidator(new Ch04MemberLoginFormValidator());
		
		//필드 단위
		binder.addValidators(
				new Ch04MemberIdValidator(),
		        new Ch04MemberPasswordValidator());
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm")@Valid Ch04Member member, Errors errors) {
		log.info("실행");
		
		if(errors.hasErrors()) {
			return "ch04/content";
		}
		
		return "redirect:/";
	}
}
