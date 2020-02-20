package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.AlreadyExistingMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

@Controller //컨트롤러를 사용하겠다는 선언
public class RegisterController {
	
	/* **회원가입시 약관을 보여주는 경로를 처리하기위한 컨트롤러
	 * 클라이언트로 부터 요청이 들어오는 url경로가 /register/step1 이라면
	 * handleStep() 메서드가 처리하고 view register/step1 를  반환
	 * */
	@RequestMapping("/register/step1")
	public String handleStep1() {
		return "register/step1";
	}
	
	//POST만 처리 (아래 두가지 오버로딩 방법중 하나만 선언해야함. 스프링이 뭘로 쓸지 헷갈려함)
	
	//<첫번째 방법 : HttpServletRequest를 직접 이용>
//	@RequestMapping(value="/register/step2", method=RequestMethod.POST)
//	public String handleStep2(HttpServletRequest request) {
//		String agreeParam = request.getParameter("agree");
//		if(agreeParam == null || !agreeParam.equals("true")) {
//			return "register/step1";
//		}
//		return "register/step2";
//	}
	//<두번째 방법 : @RequestParam어노테이션을 사용. 요청 파라미터 개수가 몇개 안되는 경우 유용>
	@RequestMapping(value="/register/step2", method=RequestMethod.POST)
	public String handleStep2(
			@RequestParam(value="agree",defaultValue="false")Boolean agree,
			Model model) {
			//<form:form>태그를 사용하기위해 Command객체가 모델에 담겨야함
		if(!agree) {
			return "register/step1";
		}
		model.addAttribute("formData", new RegisterRequest());
		return "register/step2";
	}
	
	//약관 동의 없이 setp2로 이동한경우 다시 step1으로 redirect함
	@RequestMapping(value="/register/step2", method=RequestMethod.GET)
	public String handleStep2() {
		return "redirect:/register/step1";
		//redirect: 설정시 '/'로 시작하면 절대경로로 지정된다. 그렇지 않으면 상대경로 지정
	}
	
	/* **회원가입을 위한메서드
	 * RegisterRequest 클래스를 Command객체로 지정해 폼에서 입력받은 파라미터를 저장해 처리
	 * */
	
	private MemberRegisterService memberRegisterService;
	
	//Controller에 Command객체에 대한 setter추가(의존 주입시 필요/controll.xml에 작성)
	public void setMemberRegisterService(
			MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}
	
	//Controller에 Command객체를 받아 회원가입 기능 메서드 추가
	@RequestMapping(value="/register/step3", method=RequestMethod.POST)
	public String handleStep3(@ModelAttribute("formData")RegisterRequest regReq,
			Errors errors) { /*Errors를 전달받아 Validator의 두번째 파라미터로 전달.
							 (자동으로 Command객체와 연결된 Errors객체를 생성해 전달)
							!!!!Errors매개변수를 추가할 경우 반드시 Command객체 뒤에 위치해야함!!!*/
		
		/*Request객체를 Spring이 자동으로 regReq에 set해주고(Command객체 생성) 
		 *Command객체를 view로 넘겨준다.
		 *Command객체 앞에 @ModelAttribute어노테이션을 붙혀주면
		 *Command객체의 이름을 변경할수 있다. */	
		
		new RegisterRequestValidator().validate(regReq, errors);
			//validator를 구현한 객체에서 전달받은 파라미터를 비교해 err코드로 저장하는 메서드 
					
		if(errors.hasErrors()) { //vaildate()실행중 한번이라도 rejectValue()가 실행되면 true반환
			return "register/step2";
		}
		try {
			memberRegisterService.regist(regReq); //동일한 이메일 주소일경우 catch발생
			return "register/step3";
		}catch(AlreadyExistingMemberException e) {
			errors.rejectValue("email", "duplicate");
			//email에 대한 duplicate err코드 추가 
			return "register/step2";
		}		
	}

}
