package spring;

//이메일과 비밀번호가 일치하면 AutoInfo객체를 생성하는 클래스
public class AuthService {
	private MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;		
	}
	
	public AuthInfo authenticate(String email,String password) {
		Member member = memberDao.selectByEmail(email); //받아온 email로 검색
		if(member == null) { //결과가 없다면 Exception발생 
			throw new IdPasswordNotMatchingException();
		}
		if(!member.matchPassword(password)) { //결과가 있다면 password일치확인
			throw new IdPasswordNotMatchingException();
		}
		return new AuthInfo(member.getId(), member.getEmail(), member.getName());
		//member의 결과를 return
	}
}
