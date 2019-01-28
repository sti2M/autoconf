package autoconf.config;

import java.io.FileWriter;
import java.io.IOException;

import autoconf.domain.Phone;
import autoconf.domain.Vendor;

public class Configurator {
	private ConfigurationStrategy strategy;
	private Phone phone;
	
	public Configurator(Phone phone) {
		this.phone = phone;
	}
	
	public ConfigurationStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(ConfigurationStrategy strategy) {
		this.strategy = strategy;
	}

	
	public void saveConfig() {
		this.strategy = selectStrategy(phone.getVendor());
		String config = strategy.prepareConfig(phone);
		writeConfig(config, phone);
		System.err.println(config);
	}
	
	
	private ConfigurationStrategy selectStrategy(Vendor vendor) {
		ConfigurationStrategy strategy = null;
		String vendorName = vendor.getName();
		int separatorIndex = vendorName.indexOf("/");
		vendorName = vendorName.substring(0, separatorIndex);
		
		switch(vendorName) {
		case "Yealink":
			strategy = new Yealink();
			break;
		case "Escene":
			strategy = new Escene();
			break;
		case "D-Link":
			strategy = new DLink();
			break;
		}
		return strategy;
	}
	
	private void writeConfig(String config, Phone phone) {
		try(FileWriter fw = new FileWriter("/tftpboot/" + phone.getMac() + strategy.getFileExtension())) {
			fw.write(config);
			fw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
