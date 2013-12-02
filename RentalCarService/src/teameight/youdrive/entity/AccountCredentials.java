package teameight.youdrive.entity;

public class AccountCredentials {
	
	private String username;
	private String password;
	private String role;
	
	public AccountCredentials(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role     = role;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public String getRole() {
	    return role;
	}
	
	public boolean verifyCredentials(String username, String password) {
		return username.equalsIgnoreCase(this.username) && password.equals(this.password);
	}
}
