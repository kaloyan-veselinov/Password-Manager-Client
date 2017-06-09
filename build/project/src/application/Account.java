package application;

public class Account {

	private String login;
	private String domain;
	private int loginHash;
	private int domainHash;

	public Account(String login, String domain) {
		this.setLogin(login);
		this.setDomain(domain);
		this.setLoginHash(login.hashCode());
		this.setDomainHash(domain.hashCode());
	}


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}


	public int getLoginHash() {
		return loginHash;
	}

	public void setLoginHash(int loginHash) {
		this.loginHash = loginHash;
	}

	public int getDomainHash() {
		return domainHash;
	}

	public void setDomainHash(int domainHash) {
		this.domainHash = domainHash;
	}


}