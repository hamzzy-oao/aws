package kr.fast.community.dto;

import java.util.regex.Pattern;


public record SignupRequest(String id, String pw, String email) {
	
	public boolean validId() {
		String regex = "^\\w{3,}$";
		return Pattern.matches(regex, this.id);
	}
	
	public boolean validPw() {
		return this.pw != null && this.pw.length() >= 3;
	}
	
	public boolean validEmail() {
		return this.email != null && this.email.length() >= 0;
	}

}
