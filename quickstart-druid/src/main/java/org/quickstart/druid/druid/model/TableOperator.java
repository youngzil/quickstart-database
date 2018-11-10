package org.quickstart.druid.druid.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class TableOperator {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final int COUNT = 800;

	public TableOperator() {

	}

	public void tearDown() throws Exception {
		try {
			dropTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert() throws Exception {

		StringBuffer ddl = new StringBuffer();
		ddl.append("INSERT INTO t_big (");
		for (int i = 0; i < COUNT; ++i) {
			if (i != 0) {
				ddl.append(", ");
			}
			ddl.append("F" + i);
		}
		ddl.append(") VALUES (");
		for (int i = 0; i < COUNT; ++i) {
			if (i != 0) {
				ddl.append(", ");
			}
			ddl.append("?");
		}
		ddl.append(")");
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dataSource.getConnection();

			// System.out.println(ddl.toString());

			stmt = conn.prepareStatement(ddl.toString());

			for (int i = 0; i < COUNT; ++i) {
				stmt.setInt(i + 1, i);
			}
			stmt.execute();

		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

	}

	private void dropTable() throws SQLException {

		Connection conn = null;
		Statement stmt = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();

			stmt.execute("DROP TABLE t_big");
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void createTable() throws SQLException {
		StringBuffer ddl = new StringBuffer();
		ddl.append("CREATE TABLE t_big (FID INT AUTO_INCREMENT PRIMARY KEY ");
		for (int i = 0; i < COUNT; ++i) {
			ddl.append(", ");
			ddl.append("F" + i);
			ddl.append(" BIGINT NULL");
		}
		ddl.append(")");

		Connection conn = null;
		Statement stmt = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			stmt.execute(ddl.toString());
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

	}
}
