package spring;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberRegisterService {
	@Resource
	private MemberDao memberDao;
	
	public MemberRegisterService() {
		System.out.println("디폴트 생성자");
	}

	//@Resource:Resource는 생성자에는 적용할수 없다
	
	//@Autowired
	public MemberRegisterService(MemberDao memberDao){
		System.out.println("인자 생성자");
		this.memberDao = memberDao;
	}
	
	public void regist(RegisterRequest req){
		Member member = memberDao.selectByEmail(req.getEmail());
		if(member != null){
			throw new AlreadyExistingMemberException(
						"dup email " + req.getEmail());
		}
		Member newMember = new Member(
				req.getEmail(),
				req.getPassword(),
				req.getName(),
				new Date()
				);
		memberDao.insert(newMember);		
	}
}
