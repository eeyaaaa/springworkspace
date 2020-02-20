package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.Member;
import spring.MemberDao;

@Controller
public class MemberListController {
	private MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@RequestMapping("/member/list")
	public String list(@ModelAttribute("cmd")ListCommand listCommand,
			Errors errors,Model model) {
		//400에러발생시 폼에 알맞은 에러 메시지 보여주기 (Command객체 바로위에 적용하기!!)
		if(errors.hasErrors()) {
			return "member/memberList";
		}
		if(listCommand.getFrom() != null && listCommand.getTo() != null) {
			//ListCommand에서 받아온 form & to값이 있다면 , 아래에서 반환된 member를 받아 model에 저장
			List<Member> members = memberDao.selectByRegdate(
					listCommand.getFrom(), listCommand.getTo());
			model.addAttribute("members",members);
		}
		return "member/memberList";
	}

}
