package com.study.mvcxml2.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class CommonExceptionAdvice {
	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
	//500번 예외 전체에 대한 처리
	@ExceptionHandler(Exception.class) // 어떤 exception을 처리할것인지 명시하는 어노테이션
	public String exception(Exception e, Model model) {
		
		logger.error("Exception ...");
		model.addAttribute("exception", e);
		logger.error(model.toString());
		
		return "error/error_page";
	}
	
	//404번 오류 처리 , web.xml에서 throwExceptionIfNoHandlerFound 설정을 먼저해줘야 함 
	//스프링시큐리티 필터가 존재할시에는 작동하지 않는듯하고, 이럴땐 web.xml 에서 error-page 태그등으로 설정해줘야한다.
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND) // HTTP State Code, 상태코드 설정을 위한 어노테이션
	public String handle404(NoHandlerFoundException e) {
		
		logger.error("404 Error");
		
		return "error/custom404";
	}
	
	
	
	//커스텀 예외에 대한 처리
	@ExceptionHandler(CustomException.class) // 어떤 exception을 처리할것인지 명시하는 어노테이션
	public ModelAndView customException(CustomException e, Model model) {
		
		ModelAndView mv = new ModelAndView();
		
		logger.error("CustomException ...");
		mv.addObject("exception", e);
		mv.setStatus(HttpStatus.BAD_REQUEST); // 응답코드가 400으로 설정되어서 return 된다. 
		mv.addObject("statusCode", HttpStatus.BAD_REQUEST);
		mv.setViewName("error/custom_error");
		logger.error(mv.toString());
		
		return mv;
	}

}
