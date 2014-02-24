package net.sourceforge.quoa.example.bean;

import javax.persistence.Entity;

@Entity
public class LoginPasswordUser extends Person {
	private static final long serialVersionUID = 8379200241283863672L;

	private String login;
	private char[] password;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public char[] getPassword() {
		return password;
	}
	public void setPassword(char[] password) {
		this.password = password;
	}
	
}
