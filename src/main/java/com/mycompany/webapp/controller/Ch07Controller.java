package com.mycompany.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.webapp.dto.Ch07Board;
import com.mycompany.webapp.dto.Ch07City;
import com.mycompany.webapp.dto.Ch07Cloth;
import com.mycompany.webapp.dto.Ch07Member;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch07")
@Log4j2
public class Ch07Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch07Controller.class);
	private int count;  //컨트롤러에서 공유해서 사용하는 것 -> 컨트롤러에는 필드를 선언하지 않는 것이 좋다.
	
	@RequestMapping("/content")
	public String content(HttpServletRequest request) {
		logger.info("실행");
		log.info("실행");
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sdf.format(date);
		
		request.setAttribute("strDate", strDate); //"strDate"라는 키 이름으로 strDate을 저장하겠다
		
		return "ch07/content";
	}
	
	@RequestMapping("/requestScopeSave")
	public String requestScopeSave(HttpServletRequest request) {	
		log.info("실행");

		//request 범위에 객체 저장
		request.setAttribute("requestScopeValue", "홍길동");
		
		//멤버 객체 생성 후 request 범위에 저장
		Ch07Member member = new Ch07Member();
		member.setName("홍길동");
		member.setAge(25);
		member.setJob("프로그래머");
		Ch07City city = new Ch07City();
		city.setName("서울");
		member.setCity(city);
		request.setAttribute("member", member);
		
		return "ch07/content";
	}
	
	@RequestMapping("/sessionScopeSave")
	public String sessionScopeSave(HttpSession session) {	
		log.info("실행");

		//session 범위에 객체 저장
		session.setAttribute("sessionScopeValue", "고길동");
		
		//멤버 객체 생성 후 session 범위에 저장
		Ch07Member member = new Ch07Member();
		member.setName("고길동");
		member.setAge(27);
		member.setJob("개발자");
		Ch07City city = new Ch07City();
		city.setName("부산");
		member.setCity(city);
		session.setAttribute("member2", member);
		
		return "ch07/content";
	}
	
	@RequestMapping("/applicationScopeSave")
	public String applicationScopeSave(HttpServletRequest request) {		
		log.info("실행");

		ServletContext application = request.getServletContext();
		//application 범위에 객체 저장
		application.setAttribute("applicationScopeValue", "황길동");
		
		//객체 생성 후 session 범위에 객체 저장
		Integer counter = ++count;
		application.setAttribute("counter", counter);
		return "ch07/content";
	}
	
	@GetMapping("/useJstl1")
	public String useJstl1(HttpServletRequest request) {
		String[] languages = {"Java", "JavaScript", "SpringFramework", "Vue"};
		request.setAttribute("langs", languages);
		return "ch07/useJstl1";
	}
	
	@GetMapping("/useJstl2")
	public String useJstl2(HttpServletRequest request) {
		List<Ch07Board> list = new ArrayList<>();
		for(int i=0; i<=5; i++) {
			Ch07Board board = new Ch07Board(i, "제목"+i, "내용"+i, "글쓴이"+i, new Date());
			list.add(board);
		}
		request.setAttribute("boardList", list);
		return "ch07/useJstl2";
	}
	
	@GetMapping("/modelAndViewReturn")
	public ModelAndView modelAndViewReturn() {
		Ch07Board board = new Ch07Board(1, "제목1", "내용1", "글쓴이1", new Date());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("board", board);
		modelAndView.setViewName("ch07/boardDetail");
		return modelAndView;
	}
	
	@GetMapping("/modelArgument")
	public String modelArgument(Model model) {
		Ch07Board board = new Ch07Board(1, "제목1", "내용1", "글쓴이1", new Date());
		model.addAttribute("board", board);
		return "ch07/boardDetail";
	}
	
	@GetMapping("/modelAttribute")
	public String ModelAttribute(@ModelAttribute("kind") String kind, @ModelAttribute("sex") String sex) {
		return "ch07/clothInfo";
	}
	/*
	 * 이렇게 해도 된다
	@GetMapping("/ModelAttribute")
	public String ModelAttribute(String kind, String sex, Model model) {
		model.addAttribute("kind", kind);
		model.addAttribute("sex", sex);

		return "ch07/clothInfo";
	}
	*/
	
	@GetMapping("/commandObject")
	public String commandObject(Ch07Cloth cloth) {
		return "ch07/clothInfo";
	}	
	/*
	 * ch07Cloth 말고 다른 이름으로 JSP쪽으로 전달하고 싶을 때
	@GetMapping("/commandObject")
	public String commandObject(@ModelAttribute("cloth") Ch07Cloth cloth) {
		return "ch07/clothInfo";
	}
	*/
	
	//요청 매핑 메소드가 실행될 때마다 먼저 실행된다
	@ModelAttribute("commonData")
	public Ch07Board getCommonData() {
		log.info("실행");
		Ch07Board board = new Ch07Board(2, "제목2", "내용2", "글쓴이2", new Date());
		return board;
	}
}
