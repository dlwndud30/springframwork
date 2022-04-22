package com.mycompany.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@RequestMapping("/ch17")
@Log4j2
@Controller
public class Ch17Controller {
	   @RequestMapping("/content")
	   public String content(Model model) {
	      log.info("실행");
	      return "ch17/content";
	   }
	   
	   @RequestMapping("/loginForm")
	   public String loginForm() {
	      log.info("실행");
	      return "ch17/loginForm";
	   }
	   
	   @RequestMapping("/adminAction")
	   public String adminAction() {
	      log.info("실행");
	      return "redirect:/ch17/content";
	   }
	   
	   @RequestMapping("/managerAction")
	   public String managerAction() {
	      log.info("실행");
	      return "redirect:/ch17/content";
	   }
	   
	   @RequestMapping("/userAction")
	   public String userAction() {
	      log.info("실행");
	      return "redirect:/ch17/content";
	   }
	   
	   @RequestMapping("/error403")
	   public String error403() {
	      log.info("실행");
	      return "ch17/error403";
	   }
}
