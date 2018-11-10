package org.quickstart.druid.util.config;

public class SqliteConfigProperty extends PropertyConfig {
	
	private static final  String configName = "sqlite.properties";
	private volatile static SqliteConfigProperty instance = null;

	protected SqliteConfigProperty() {
		super(configName);
	}
	
	public static SqliteConfigProperty getInstance() {
		if (instance == null) {
			synchronized (SqliteConfigProperty.class) {
				if (instance == null) {
					instance = new SqliteConfigProperty();
				}
			}
		}
		
		return instance;
	}

}
