package controller;

//비밀번호 변경을 위해 사용되는 클래스
public class ChangePwdCommand {
	private String currentPassword; //현재 비밀번호
	private String newPassword; //변경할 비밀번호
	
	public String getCurrentPassword() {
		return currentPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
