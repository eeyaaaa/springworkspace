package controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("spring08-mvc3") //공통적인 예외 처리하기
public class CommonExceptionHandler {

	@ExceptionHandler(Exception.class) //해당컨트롤러 클래스에서 발생된 예외를 처리
	public String handleException() {
		return "error/commonException";
	}
}
