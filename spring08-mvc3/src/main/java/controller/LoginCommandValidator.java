package controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

//전달받은 값을 검증하는 클래스
public class LoginCommandValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return LoginCommand.class.isAssignableFrom(arg0);
		//isAssignableFrom() : 어떤클래스/인터페이스를 상속/구현했는지 체크, 지금은 사용 x
	}

	@Override
	public void validate(Object target, Errors errors) {
		//target: 검사 대상 객체
		//errors : 검사 결과 에러코드를 저장하는 객체
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
		//프로퍼티의 값이 null이거나 공백문자이거나 길이가 0인경우
		ValidationUtils.rejectIfEmpty(errors, "password", "required");
		//프로퍼티의 값이 null이거나 길이가 0인경우
		
	}

}
