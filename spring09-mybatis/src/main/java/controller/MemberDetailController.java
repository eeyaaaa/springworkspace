package controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.Member;
import spring.MemberDao;
import spring.MemberNotFoundException;

@Controller
public class MemberDetailController {
	private MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@RequestMapping("/member/detail/{id}")
	public String detail(@PathVariable("id")Long memId, Model model) {
		Member member = memberDao.selectById(memId);
		if(member == null) {
			throw new MemberNotFoundException();
		}
		model.addAttribute("member",member);
		return "member/memberDetail";
	}
	
	//해당 어노테이션은 try~catch로 지정하지 않고 직접 처리하도록 한다.
	@ExceptionHandler(TypeMismatchException.class)
	public String handleTypeMismatchException() {
		//잘못된 경로를 요청한경우
		return "member/invalidId";
	}
	
	@ExceptionHandler(MemberNotFoundException.class)
	public String handleMemberNotFoundException() {
		//id가 존재하지 않는 회원의 정보를 요청한경우 아래 경로로 이동(위에서 throw로 던져준 예외로 확인)
		return "member/noMember";
	}

}
