package entities;

import java.io.Serializable;

public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username, password;
	
	public void setUsername(String user) {
		this.username = user;		
	}
	public void setPassword(String pass) {
		this.password = pass;		
	}
	
	public String getUsername() {
		return this.username;
	}
	public String getPassword(){
		return this.password;
	}
	
	public User() {}
	
	public User(String user, String pass) {
		this.username = user;
		this.password = pass;
	}

}
