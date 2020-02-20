package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.AuthInfo;
import spring.AuthService;
import spring.IdPasswordNotMatchingException;


//로그인 요청을 처리하는 클래스
@Controller
@RequestMapping("/login")
public class LoginController {
	private AuthService authService;

	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String form(LoginCommand loginCommand,
			//쿠키 존재할경우(value는 쿠키의 이름, required=false는 필수 아님을 표시)
			@CookieValue(value="REMEMBER",required=false)Cookie cookie) {
		if(cookie != null) {
			loginCommand.setEmail(cookie.getValue());
			loginCommand.setRememberEmail(true); 
		}
		return "login/loginForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String submit(LoginCommand loginCommand, Errors errors,HttpSession session,
			HttpServletResponse response) {
						/*LoginCommnad객체에서 정보를 받아 err를 코드확인 
						세션에 정보를 저장하기위에 session추가(항상 HttpSession생성)
						쿠키생성을 위해  HttpServletResponse 객체 생성필요*/
		new LoginCommandValidator().validate(loginCommand, errors);
		if(errors.hasErrors()) { 
			return "login/loginForm";
		}
		try {
			AuthInfo authInfo = authService.authenticate(  //authInfo객체는 세션을 구현할때 사용
					loginCommand.getEmail(),
					loginCommand.getPassword());
			//LoginCommand에서 email,password를 받아 member객체를 반환받는다.
			
			session.setAttribute("authInfo", authInfo);
			
			//쿠키생성 
			Cookie rememberCookie =
					new Cookie("REMEMBER", loginCommand.getEmail());
			rememberCookie.setPath("/"); //쿠키의 유효한 디렉토리설정
			if(loginCommand.isRememberEmail()) {
				rememberCookie.setMaxAge(60*60*24*30);  //쿠키의 유효기간설정
			}else {
				rememberCookie.setMaxAge(0);
			}
			response.addCookie(rememberCookie);
			
			return "login/loginSuccess"; 
		}catch(IdPasswordNotMatchingException e) {
			errors.reject("IdPasswordNotMatching");
			return "login/loginForm";
		}
	}
	
	
}
