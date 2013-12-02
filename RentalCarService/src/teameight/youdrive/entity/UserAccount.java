package teameight.youdrive.entity;

public class UserAccount {
	
	private String username;
	private String password;
	
	public UserAccount(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public boolean verifyCredentials(String username, String password) {
		return username.equalsIgnoreCase(this.username) && password.equals(this.password);
	}
}
