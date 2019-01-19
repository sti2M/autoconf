package autoconf.domain;

import java.util.List;
import java.util.Optional;

public class CommonWrapper {
	private Optional<Phone> phone;
	private List<PhoneAccount> phoneAccounts;
	private List<Vendor> vendors;
	private List<Account> accountList;
	
	public Optional<Phone> getPhone() {
		return phone;
	}
	public void setPhone(Optional<Phone> phone) {
		this.phone = phone;
	}
	public List<PhoneAccount> getPhoneAccounts() {
		return phoneAccounts;
	}
	public void setPhoneAccounts(List<PhoneAccount> phoneAccounts) {
		this.phoneAccounts = phoneAccounts;
	}
	public List<Vendor> getVendors() {
		return vendors;
	}
	public void setVendors(List<Vendor> vendors) {
		this.vendors = vendors;
	}
	public List<Account> getAccountList() {
		return accountList;
	}
	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}
	
	
}
