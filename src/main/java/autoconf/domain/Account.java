package autoconf.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
		
	private String username;
	
	private String password;
	
	@Column(name="sip_server")
	private String sipServer;
	
	@Column(name="sip_port")
	private String sipPort;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<PhoneAccount> phoneAccounts;

	public Account() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSipServer() {
		return sipServer;
	}

	public void setSipServer(String sipServer) {
		this.sipServer = sipServer;
	}

	public String getSipPort() {
		return sipPort;
	}

	public void setSipPort(String sipPort) {
		this.sipPort = sipPort;
	}

	public List<PhoneAccount> getPhoneAccounts() {
		return phoneAccounts;
	}

	public void setPhoneAccounts(List<PhoneAccount> phoneAccounts) {
		this.phoneAccounts = phoneAccounts;
	}
	
	@Override
	public String toString() {
		return this.getUsername();
	}
	
}
