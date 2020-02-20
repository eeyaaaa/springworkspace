package controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

//Command객체에 저장된 값을 검증하기위한 클래스
public class ChangePwdCommandValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return ChangePwdCommand.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
						// target: 검사 대상 객체
						// errors : 검사 결과 에러코드를 저장하는 객체
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword", "required");
		// 프로퍼티의 값이 null이거나 공백문자이거나 길이가 0인경우
		ValidationUtils.rejectIfEmpty(errors, "newPassword", "required");
		// 프로퍼티의 값이 null이거나 길이가 0인경우

	}

}
