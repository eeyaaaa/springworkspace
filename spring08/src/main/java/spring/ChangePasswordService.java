package spring;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import encrypt.Sha256Util;
import oracle.sql.NUMBER;

public class ChangePasswordService {
	private MemberDao memberDao;
	private PasswordEncoder encoder;
	
	public void setPasswordEncoder(PasswordEncoder encoder) {
		
	}
	
	public ChangePasswordService(MemberDao memberDao){
		this.memberDao = memberDao;
	}
	
	@Transactional
	public void changePassword(String email, String oldPwd, String newPwd){
		Member member = memberDao.selectByEmail(email);
		if(member == null){
			throw new MemberNotFoundException();
		}
		String dbPass = member.getPassword(); //해시값 $솔트  (솔트값을 잘라야 비밀번호를 비교할수 있음)
		String oldSalt = dbPass.split("\\$")[1];  //\을 두개써야 하나로 인식함. 정규식에서 $뒤에 값을 잘라내기 위해서 \$를 사용 
		
		//사용자가 입력한 평문을 위에서 가져온 salt값으로 해싱 
		StringBuffer eop = new StringBuffer();
		eop.append(Sha256Util.getEncrypt(oldPwd, oldSalt));  //기본 비밀번호과 비교할 값
		eop.append("\\$").append(oldSalt); //기본 비밀번호과 비교할 값. salt까지 같이 비교
		oldPwd = eop.toString();
		
		//사용자가 입력한 새로운 비밀번호를 해싱
//		StringBuffer encryptPassword = new StringBuffer();
//		
//		String newSalt = Sha256Util.genSalt();
//		
//		encryptPassword.append(Sha256Util.getEncrypt(newPwd, newSalt));
//		encryptPassword.append("\\$").append(newSalt);
//		newPwd = encryptPassword.toString();
//		System.out.println(encryptPassword.toString());
//		
//		System.out.println("기존비밀번호(db) :" + member.getPassword());
//		System.out.println("기존비밀번호 (u): " + oldPwd);
//		System.out.println("");
		
		newPwd = encoder.encode(newPwd);
		
		
		member.changePassword(oldPwd,  newPwd);
		memberDao.update(member);
	}
}
