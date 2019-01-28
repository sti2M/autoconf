package autoconf.config;

import autoconf.domain.Phone;

public interface ConfigurationStrategy {
	
	public static String FILE_EXTENSION = ".txt";
	public String prepareConfig(Phone phone);
	
	public String getFileExtension();
}
