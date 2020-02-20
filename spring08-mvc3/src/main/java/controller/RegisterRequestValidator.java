package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.RegisterRequest;

//validator를 구현한 클래스(객체를 검증할때 사용)
public class RegisterRequestValidator implements Validator{
	private static final String emailRegExp =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+
			"[A-Za-z0-9-]+(\\).[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;
	
	public RegisterRequestValidator() {
		pattern = Pattern.compile(emailRegExp);	//주어진 정규 표현을 패턴으로 컴파일한다.	
	}
	@Override
	public boolean supports(Class<?> arg0) {
		return RegisterRequest.class.isAssignableFrom(arg0);
		/*
		 * 스프링 MVC에서 전달받는 객체를 자동으로 검증할 경우 올바르게 구현해야함
		 * 현재는 사용x
		 * */		
	}
	
	//첫번째 파라미터로 받은 객체를 검증하고 결과를 Errors에 담는다 
	//(검사대상객체의 특정값이나 상태가 올바르지 않다면 rejectValue()를 통해 에러 코드를 저장)
	@Override
	public void validate(Object target, Errors errors) { 
		//target: 검사 대상 객체
		//errors : 검사 결과 에러코드를 저장하는 객체
		RegisterRequest regReq = (RegisterRequest) target; 
		if(regReq.getEmail() == null || regReq.getEmail().trim().isEmpty()) {
			//받아온 이메일 파라미터가 null이거나, 공백을제거한 이메일이 비어있는지확인
			errors.rejectValue("email", "required");
			//email에 대한 required err코드 추가 
		}else {
			Matcher matcher = pattern.matcher(regReq.getEmail());
			//Matcher클래스 : char들 match하는것들과 관련된 메서드 제공
			//matcher() : email에서 패턴을 찾는 matcher객체를 만든다
			if(!matcher.matches()) { //패턴이 전체 문자열과 일치하는경우 true반환
				errors.rejectValue("email", "bad");
			}
		}
		//ValidationUtils 클래스는 객체의 값 검증코드를 간결하게 작성하도록 도와주는 역할
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
														/*자동으로 taget의 name을 검사*/
						//프로퍼티의 값이 null이거나 공백문자이거나 길이가 0인경우
		ValidationUtils.rejectIfEmpty(errors, "password", "required");
						//프로퍼티의 값이 null이거나 길이가 0인경우
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "required");
		if(!regReq.getPassword().isEmpty()) {
			if(!regReq.isPasswordEqualToConfirmPassword()) {
				errors.rejectValue("confirmPassword", "nomatch");
				//confirmPassword에 대한 nomatch err코드 추가 
			}
		}		
	}

}
