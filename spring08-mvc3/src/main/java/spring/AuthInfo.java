package spring;


//로그인 성공후 인증 상태를 세션에 보관하기 위한 클래스
public class AuthInfo {
	private Long id;
	private String email;
	private String name;
	public AuthInfo(Long id, String email, String name) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public String getName() {
		return name;
	}
	
	

}
