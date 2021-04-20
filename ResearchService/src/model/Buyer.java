package model;

public class Buyer {
	
	private int id;
	private String userName;
	private String password;
	private String email;
	private String regiteredAt;
	private String updatedAt;
	
	public int getId() {
		return id;
	}
	
	public Buyer(String userName, String password, String email) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRegiteredAt() {
		return regiteredAt;
	}
	
	public void setRegiteredAt(String regiteredAt) {
		this.regiteredAt = regiteredAt;
	}
	
	public String getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@Override
	public String toString() {
		return "Buyer [id=" + id + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", regiteredAt=" + regiteredAt + ", updatedAt=" + updatedAt + "]";
	}
}
