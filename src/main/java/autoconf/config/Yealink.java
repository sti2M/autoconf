package autoconf.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.HashMap;

import autoconf.domain.Phone;

public class Yealink implements ConfigurationStrategy {
	
	private static final String TEMPLATE_FILE = "/tftpboot/templates/yealink_template.cfg";
	public static final String FILE_EXTENSION = ".cfg";
	
	public String prepareConfig(Phone phone) {
		String config;
		
		try {
			config = new String(Files.readAllBytes(Paths.get(TEMPLATE_FILE)));
		} catch (IOException e) {
			config = "Something was wrong";
			e.printStackTrace();
		}
		
		config = config.replace("{ntpServer}", phone.getNtpServer());
		config = config.replace("{isDhcp}", new Integer(phone.getIsDhcp() ? 0 : 2).toString());
		config = config.replace("{ipAddress}", phone.getIpAddress());
		config = config.replace("{subnetMask}", phone.getSubnetMask());
		config = config.replace("{defaultGw}", phone.getDefaultGw());
		config = config.replace("{dns1}", phone.getDns1());
		config = config.replace("{dns2}", phone.getDns2());
		config = config.replace("{adminLogin}", phone.getAdminLogin());
		config = config.replace("{adminPassword}", phone.getAdminPassword());
		config = config.replace("{provUrl}", phone.getProvUrl());

		String lineConfigTemplate = new String(config);
		lineConfigTemplate = lineConfigTemplate.substring(lineConfigTemplate.indexOf("<") + 1, lineConfigTemplate.indexOf(">"));
		config = config.substring(0, config.indexOf("<"));
		
		String linesConfig = new String("");
		for (int index = 1; index <= phone.getVendor().getLinesCount(); index++) {
			
			String lineConfig = new String(lineConfigTemplate);
			lineConfig = lineConfig.replaceAll("index", Integer.toString(index));
			
			String lineUsername;
			if (phone.getPhoneAccounts().get(index - 1).getAccount().getUsername().equalsIgnoreCase("NotUsed")) {
				lineConfig = lineConfig.replace("lineEnable", "0");
				lineConfig = lineConfig.replaceAll("lineUsername", "");
				lineConfig = lineConfig.replace("linePassword", "");
				lineConfig = lineConfig.replace("sipServer", "");
				lineConfig = lineConfig.replace("sipPort", "");
			} else {
				lineConfig = lineConfig.replace("lineEnable", "1");
				lineConfig = lineConfig.replaceAll("lineUsername", phone.getPhoneAccounts().get(index - 1).getAccount().getUsername());
				lineConfig = lineConfig.replace("linePassword", phone.getPhoneAccounts().get(index - 1).getAccount().getPassword());
				lineConfig = lineConfig.replace("sipServer", phone.getPhoneAccounts().get(index - 1).getAccount().getSipServer());
				lineConfig = lineConfig.replace("sipPort", phone.getPhoneAccounts().get(index - 1).getAccount().getSipPort());
			}
			
			linesConfig = linesConfig.concat(lineConfig).concat("\r\n");
		}
		
		config = config.concat(linesConfig);
		
		return config;
	}
	
	public String getFileExtension() {
		return FILE_EXTENSION;
	}
}
