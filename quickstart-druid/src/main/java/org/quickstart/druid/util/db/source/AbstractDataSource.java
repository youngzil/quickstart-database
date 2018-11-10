package org.quickstart.druid.util.db.source;

import javax.sql.DataSource;

public abstract class AbstractDataSource {
	protected DataSource dataSource;
	public DataSource getDataSource() {
		return dataSource;
	}
	public  static AbstractDataSource getInstance() throws Exception {
		return null;
	}
}
