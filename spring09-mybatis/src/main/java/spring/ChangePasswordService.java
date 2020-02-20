package spring;

import org.springframework.transaction.annotation.Transactional;

public class ChangePasswordService {
	private MemberDao memberDao;
	
	public ChangePasswordService(MemberDao memberDao){
		this.memberDao = memberDao;
	}

	@Transactional //트랜잭션 범위에서 실행할 메서드에 적용
	public void changePassword(String email, String oldPwd, String newPwd){
		Member member = (Member)memberDao.selectByEmail(email);
		if(member == null){
			throw new MemberNotFoundException();
		}
		member.changePassword(oldPwd,  newPwd);
		memberDao.update(member);
	}
	

}
