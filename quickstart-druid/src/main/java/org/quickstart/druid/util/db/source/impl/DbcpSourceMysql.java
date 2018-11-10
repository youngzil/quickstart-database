package org.quickstart.druid.util.db.source.impl;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.quickstart.druid.util.config.MySqlConfigProperty;
import org.quickstart.druid.util.db.source.AbstractDataSource;

public class DbcpSourceMysql extends AbstractDataSource {

	private volatile static DbcpSourceMysql instance;	
	
	
	private DbcpSourceMysql() throws Exception {
		dataSource = BasicDataSourceFactory.createDataSource(
				MySqlConfigProperty.getInstance().getProperties());		
	}

	public static DbcpSourceMysql getInstance() throws Exception {
		if (instance == null) {
			synchronized (DbcpSourceMysql.class) {
				if (instance == null) {
					instance = new DbcpSourceMysql();
				}
			}
		}
		return instance;
	}	
	
}
