package fr.benco11.jlibecoledirecte.login;

public class LoginJson {
	private String token;
	private final LoginData data;
	private String socketToken;
	private int code;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public String getSocketToken() {
		return socketToken;
	}

	public String getToken() {
		return token;
	}

	public LoginData getLoginData() {
		return data;
	}

	public LoginJson(String token, LoginData data) {
		this.token = token;
		this.data = data;
	}

	public int getCode() {
		return code;
	}
}
