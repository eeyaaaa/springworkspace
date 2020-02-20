package spring08;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller /*Spring mvc에서 컨트롤러로 사용하겠다고 선언*/
public class HelloController {
	
	@RequestMapping("/hello") 
	//요청을 Mapping한다.(최상위의 hello라고 요청이 왔을때) 라고 어노테이션으로 선언 
	public String hello(Model model/*컨트롤러의 처리결과를 뷰에 전달. 서버쪽에서 처리한 결과를 담는 역할*/,
			@RequestParam(value="name", required=false)String name) {
			/*RequestParam 어노테이션을 사용하면 Spring 에서 자동으로 HttpServletRequest를 
			사용한것처럼 인식해 String name이라는 변수에  value를 넣어준다. (required는 없으면 말고! 라는뜻)*/
		System.out.println("hello요청");
		model.addAttribute("greeting","안녕하세요" + name);		
		//model에 name을 담아서 사용할수 있게 
		//(greetring : medel에 담길 id, 뒤에는 value)
		
		return "hello"; //컨트롤로의 처리결과를 보여줄 view이름지정 (hello.jsp로 경로지정과 같음)
		
	}

}
