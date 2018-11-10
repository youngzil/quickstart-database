package org.quickstart.druid.util.db.source.impl;

import org.quickstart.druid.util.config.MySqlConfigProperty2;
import org.quickstart.druid.util.db.source.AbstractDataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DruidSourceMysql2 extends AbstractDataSource {

	private volatile static DruidSourceMysql2 instance;	
	
	
	private DruidSourceMysql2() throws Exception {
		dataSource = DruidDataSourceFactory.createDataSource(
				MySqlConfigProperty2.getInstance().getProperties());		
	}

	public static DruidSourceMysql2 getInstance() throws Exception {
		if (instance == null) {
			synchronized (DruidSourceMysql2.class) {
				if (instance == null) {
					instance = new DruidSourceMysql2();
				}
			}
		}
		return instance;
	}	
	
}
