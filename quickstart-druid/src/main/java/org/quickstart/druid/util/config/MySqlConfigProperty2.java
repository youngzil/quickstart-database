package org.quickstart.druid.util.config;

public class MySqlConfigProperty2 extends PropertyConfig {
	
	private static final  String configName = "dbconfig2.properties";
	private volatile static MySqlConfigProperty2 instance = null;

	protected MySqlConfigProperty2() {
		super(configName);
	}
	
	public static MySqlConfigProperty2 getInstance() {
		if (instance == null) {
			synchronized (MySqlConfigProperty2.class) {
				if (instance == null) {
					instance = new MySqlConfigProperty2();
				}
			}
		}
		
		return instance;
	}

}
