package survey;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String form(Model model) {
		List<Question> questions = createQuestions();
		//질문들을 List에 담는다.
		ModelAndView mav = new ModelAndView();
		//model을 이용해 view에 전달할 데이터와, 결과를 보여줄 view이름을 한번에 묶어서 반환하기위한 클래스
		mav.addObject("questions",questions);
		//모델 객체 지정
		mav.setViewName("survey/surveyForm2");
		//뷰 이름 지정 
		
		/* ModelAndView클래스에 담기전 
		model.addAttribute("questions",questions);
		//담은 질문 list를 model에 담음 
		 */
		return "survey/surveyForm2";
	}
	
	private List<Question> createQuestions(){
		Question q1 = new Question("당신의 역할은?",
				Arrays.asList("서버","프론트","풀스택"));
			/*asList() : 일반배열을 ArrayList로 변환
			 *질문과, 3가지 답변옵션들을 Question객체로 생성
			 * */
		Question q2 = new Question("주로 사용하는 개발 도구는?",
				Arrays.asList("이클립스","인텔리J","서브라임"));
		Question q3 = new Question("하고 싶은 말");
				return Arrays.asList(q1,q2,q3);
				//form()메서드에 Question 3가지객체를 return. 
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public String submit(@ModelAttribute("ansData")AnsweredData data){
		return "survey/submitted";
	}
}
