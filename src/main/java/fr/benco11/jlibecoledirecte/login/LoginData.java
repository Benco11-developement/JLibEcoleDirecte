package fr.benco11.jlibecoledirecte.login;

import java.util.HashSet;
import java.util.Set;

public class LoginData {
	private Set<Account> accounts = new HashSet<Account>(0);
	
	public LoginData(Set<Account> accounts) {
		this.accounts = accounts;
	}
	
	public Account getAccount() {
		return (Account) accounts.toArray()[0];
	}
}
