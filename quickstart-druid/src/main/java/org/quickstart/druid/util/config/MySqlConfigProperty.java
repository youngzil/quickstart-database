package org.quickstart.druid.util.config;

public class MySqlConfigProperty extends PropertyConfig {
	
	private static final  String configName = "dbconfig.properties";
	private volatile static MySqlConfigProperty instance = null;

	protected MySqlConfigProperty() {
		super(configName);
	}
	
	public static MySqlConfigProperty getInstance() {
		if (instance == null) {
			synchronized (MySqlConfigProperty.class) {
				if (instance == null) {
					instance = new MySqlConfigProperty();
				}
			}
		}
		
		return instance;
	}

}
