package controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("spring08-mvc3") //공통적인 예외 처리하기 (@ExceptionHandler가 지정된 메서드가 없으면)
public class CommonExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String handleException() {
		return "error/commonException";
	}
}
