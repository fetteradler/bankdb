package tools;

import java.util.Date;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class AuthenticationCookie {
	
	private static int expiringTimeInMilliSeconds = 1000 * 60 * 30; // 30 Minuten
	
	private Role role;
	private int userId;
	private Date expiringDate;
	
	public AuthenticationCookie(Role role, int userId) {
		this.role = role;
		this.userId = userId;
		expiringDate = new Date();
		expiringDate.setTime(expiringDate.getTime() + expiringTimeInMilliSeconds);
	}
	
	public void extendExpiringTime () {
		expiringDate = new Date();
		expiringDate.setTime(expiringDate.getTime() + expiringTimeInMilliSeconds);
	}
	
	public boolean isValidCookie() {
		Date currentTime = new Date();
		return (expiringDate.getTime() - currentTime.getTime()) > 0;
	}
}