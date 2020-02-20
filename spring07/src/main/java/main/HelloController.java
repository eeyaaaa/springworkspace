package main;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller 
public class HelloController {
	
	@RequestMapping("/Hello") 
	//요청을 Mapping한다.(최상위의 Hello라고 요청이 왔을때) 라고 어노테이션으로 선언 
	public String hello(Model model/*서버쪽에서 처리한 결과를 담는 역할*/,
			@RequestParam(value="name", required=false)String name) {
			/*RequestParam 어노테이션을 사용하면 Spring 에서 자동으로 HttpServletRequest를 
			사용한것처럼 인식해 String name이라는 변수에  value를 넣어준다. (required는 없으면 말고! 라는뜻)*/
		model.addAttribute("greetring","안녕하세요" + name);
		//model에 name을 담아서 사용할수 있게
		
		return "hello";
		
	}

}
