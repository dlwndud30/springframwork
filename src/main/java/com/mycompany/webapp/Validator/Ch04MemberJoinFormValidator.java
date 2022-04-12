package com.mycompany.webapp.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.webapp.dto.Ch04Member;

import lombok.extern.log4j.Log4j2;

//Member Class만 검사 가능한 Validator
@Log4j2
public class Ch04MemberJoinFormValidator implements Validator{

	//유효성 검사가 가능한 객체인지를 조사하는 : 검사 가능하면 true
	@Override
	public boolean supports(Class<?> clazz) {
		log.info("실행");
		//clazz가 Ch04Member로 대입될 수 있는지 : clazz가 Ch04Member또는 Ch04Member의 자식객체이면 true
		boolean result = Ch04Member.class.isAssignableFrom(clazz);  //Ch04Member m = ?(Ch04Member이거나 Ch04Member의 자식객체) 
		log.info(result);
		return result;
	}

	//유효성 검사 : supports()가 true일때만 validate()를 실행 -> 문제가 발생하면 Errors에 저장
	@Override
	public void validate(Object target, Errors errors) {  //Object target: 유효성 검사를 할 객체=clazz, Errors errors: 어떤 내용이 잘못되었는지를 작성
		log.info("실행");
		Ch04Member member = (Ch04Member) target;
		
		//mid 검사
		if(member.getMid()==null || member.getMid().trim().equals("")) {  //mid가 존재하지 않을 경우
			//errors.mid.required(키)가 있으면 그 값을 사용하고 없으면 "mid는 필수 입력 사항입니다."(default)를 사용하겠다.
			//스프링이 브라우저가 지원하는 언어를 읽어서  en.properties를 이용할지 ko.properties를 이용할지 알아서 결정해줌
			errors.rejectValue("mid", "errors.mid.required", "mid는 필수 입력 사항입니다."); //처리를 거절 :"mid"(필드이름), "errors.mid.minlength"(에러 코드-국제화와 관련있음), "mid는 필수 입력 사항입니다."(에러메시지)
		}else {
			if(member.getMid().length() < 4) {
				errors.rejectValue("mid", "errors.mid.minlength",new Object[] {4}, "mid는 4자 이상입니다."); 
			}
		}
		
		//mpassword 검사
		if(member.getMpassword()==null || member.getMpassword().trim().equals("")) {
			errors.rejectValue("mpassword", "errors.mpassword.required", "mpassword는 필수 입력 사항입니다."); //어떤 필드에서처리를 거절
		}else {
			if(member.getMpassword().length() < 8) {
				errors.rejectValue("mpassword", "errors.mpassword.minlength", new Object[] {8}, "massword는 8자 이상입니다."); 
			}
		}
		
		//memail 검사
		if(member.getMemail()==null || member.getMemail().trim().equals("")) {
			errors.rejectValue("memail", "errors.memail.required", "memail는 필수 입력 사항입니다."); //처리를 거절
		}else {
			String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(member.getMemail());
			if(!matcher.matches()) {
				errors.rejectValue("memail", "errors.memail.invalid","memail은 이메일 형식이 아닙니다."); 
			}
		}
		
		//mtel 검사
	      if(member.getMtel() == null || member.getMtel().trim().equals("")) {
	         errors.rejectValue("mtel", "errors.mtel.required", "mtel은 필수 입력 사항입니다.");
	      } else {
	         String regex = "^\\d{3}-\\d{3,4}-\\d{4}$";
	         Pattern pattern = Pattern.compile(regex);
	         Matcher matcher = pattern.matcher(member.getMtel());
	         if(!matcher.matches()) {
	            errors.rejectValue("mtel", "errors.mtel.invalid", "mtel은 전화번호 형식이 아닙니다.");
	         }
	      }
	}

}
