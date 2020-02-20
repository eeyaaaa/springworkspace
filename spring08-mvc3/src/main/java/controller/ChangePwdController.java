package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.AuthInfo;
import spring.ChangePasswordService;
import spring.IdPasswordNotMatchingException;

//비밀번호 변경을 처리할 컨트롤러 클래스 
@Controller
@RequestMapping("/edit/changePassword")
public class ChangePwdController {
	private ChangePasswordService changePasswordService;

	public void setChangePasswordService(ChangePasswordService changePasswordService) {
		this.changePasswordService = changePasswordService;
	}
	@RequestMapping(method=RequestMethod.GET)
	public String form(@ModelAttribute("command")ChangePwdCommand pwdCmd, Errors errors) {
		System.out.println("GET 컨트롤러 실행 ");
		return "edit/changePwdForm";
	}
	@RequestMapping(method=RequestMethod.POST)
	public String submit(
			@ModelAttribute("command")ChangePwdCommand pwdCmd,
			Errors errors,
			HttpSession session) {
		System.out.println("POST 컨트롤러 실행 ");
		new ChangePwdCommandValidator().validate(pwdCmd, errors);
		if(errors.hasErrors()) {
			return "edit/changePwdForm";
		}
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		//기존의 세션을 가져와서 로그인한 사용자 판별
		try {
			changePasswordService.changePassword(
					authInfo.getEmail(),
					pwdCmd.getCurrentPassword(),
					pwdCmd.getNewPassword());
			return "edit/changePwd";
		}catch(IdPasswordNotMatchingException e) {
			errors.rejectValue("currentPassword", "notMatching");
			return "edit/changePwdForm";
		}
	}
	
}
