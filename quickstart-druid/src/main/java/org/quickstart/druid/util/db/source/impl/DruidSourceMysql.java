package org.quickstart.druid.util.db.source.impl;

import org.quickstart.druid.util.config.MySqlConfigProperty;
import org.quickstart.druid.util.db.source.AbstractDataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DruidSourceMysql extends AbstractDataSource {

	private volatile static DruidSourceMysql instance;	
	
	
	private DruidSourceMysql() throws Exception {
		dataSource = DruidDataSourceFactory.createDataSource(
				MySqlConfigProperty.getInstance().getProperties());		
	}

	public static DruidSourceMysql getInstance() throws Exception {
		if (instance == null) {
			synchronized (DruidSourceMysql.class) {
				if (instance == null) {
					instance = new DruidSourceMysql();
				}
			}
		}
		return instance;
	}	
	
}
