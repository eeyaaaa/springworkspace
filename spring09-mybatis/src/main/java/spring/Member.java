package spring;

import java.util.Date;

public class Member{
	private Long id;
	private String email;
	private String password;
	private String name;
	private Date regdate;
	
	public Member() {
		
	}
	
	public Member(
		String email, String password, String name, Date regdate)
	{
		this.email = email;
		this.password = password;
		this.name = name;
		this.regdate = regdate;
	}
	
	public void setId(Long id) {
		this.id = id; 
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setregdate(Date regdate) {
		this.regdate = regdate;
	}
	public Long getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public Date getregdate() {
		return regdate;
	}
	
	//암호변경 기능을 구현하는 메서드
	public void changePassword(String oldPassword, String newPassword){
		if(!password.equals(oldPassword))
			throw new IdPasswordNotMatchingException();
		this.password = newPassword;
	}
	
	//패스워드를가 일치하는지 확인하는 메서드
	public boolean matchPassword(String pwd) {
		return this.password.equals(pwd);
	}
}
