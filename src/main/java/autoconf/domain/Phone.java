package autoconf.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.OnDelete;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.PostLoad;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="phone")
public class Phone {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String mac;
	
	@Column(name="inventory_num")
	private String inventory;
	private String mol;
	private String room;
	
	@Column(name="ntp_server")
	private String ntpServer;
	
	@Column(name="dhcp")
	private boolean isDhcp;
		
	@Column(name="ip_address")
	private String ipAddress;
	
	@Column(name="subnet_mask")
	private String subnetMask;
	
	@Column(name="default_gw")
	private String defaultGw;
	private String dns1;
	private String dns2;
	
	@Column(name="prov_url")
	private String provUrl;
	
	@Column(name="admin_login")
	private String adminLogin;
	
	@Column(name="admin_password")
	private String adminPassword;
	
	public Phone() {
		
	}
	
	public Phone(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	public String getMol() {
		return mol;
	}

	public void setMol(String mol) {
		this.mol = mol;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getNtpServer() {
		return ntpServer;
	}

	public void setNtpServer(String ntpServer) {
		this.ntpServer = ntpServer;
	}

	public boolean getIsDhcp() {
		return isDhcp;
	}

	public void setIsDhcp(boolean isDhcp) {
		this.isDhcp = isDhcp;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getSubnetMask() {
		return subnetMask;
	}

	public void setSubnetMask(String subnetMask) {
		this.subnetMask = subnetMask;
	}

	public String getDefaultGw() {
		return defaultGw;
	}

	public void setDefaultGw(String defaultGw) {
		this.defaultGw = defaultGw;
	}

	public String getDns1() {
		return dns1;
	}

	public void setDns1(String dns1) {
		this.dns1 = dns1;
	}

	public String getDns2() {
		return dns2;
	}

	public void setDns2(String dns2) {
		this.dns2 = dns2;
	}

	public String getProvUrl() {
		return provUrl;
	}

	public void setProvUrl(String provUrl) {
		this.provUrl = provUrl;
	}

	public String getAdminLogin() {
		return adminLogin;
	}

	public void setAdminLogin(String adminLogin) {
		this.adminLogin = adminLogin;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	@OneToMany(mappedBy = "phone", cascade = CascadeType.ALL)
	private List<PhoneAccount> phoneAccounts;
	
	public List<PhoneAccount> getPhoneAccounts() {
		return phoneAccounts;
	}

	public void setPhoneAccounts(List<PhoneAccount> phoneAccounts) {
		this.phoneAccounts = phoneAccounts;
	}

	public void setDhcp(boolean isDhcp) {
		this.isDhcp = isDhcp;
	}

	@ManyToOne(fetch = FetchType.LAZY,  optional = false)
	@JoinColumn(name = "id_vendor", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Vendor vendor;
		
	@PrePersist
	@PreUpdate
	public void removeColons() {
		mac = mac.replaceAll("\\:|\\-", "");
	}
	
	@PostLoad
	public void addColons() {
		mac = mac.replaceAll("(.{2})", "$1\\:");
		mac = mac.substring(0, mac.length() - 1);
		mac = mac.toUpperCase();
	}
	
	
}
