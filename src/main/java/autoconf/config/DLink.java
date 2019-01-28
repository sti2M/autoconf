package autoconf.config;

import autoconf.domain.Phone;

public class DLink implements ConfigurationStrategy {
	
	private static final String TEMPLATE_FILE = "/tftpboot/templates/dlink_template.txt";
	public static final String FILE_EXTENSION = ".txt";
	
	public String prepareConfig(Phone phone) {
		String config = "";
		return config;
	}
	
	public String getFileExtension() {
		return FILE_EXTENSION;
	}
}
