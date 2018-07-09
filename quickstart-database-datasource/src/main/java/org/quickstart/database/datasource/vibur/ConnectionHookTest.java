/**
 * Copyright 2017 Simeon Malchev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.quickstart.database.datasource.vibur;

import org.junit.Test;
import org.vibur.dbcp.ViburDBCPDataSource;
import org.vibur.dbcp.ViburDBCPException;
import org.vibur.dbcp.pool.Hook;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Simeon Malchev
 */
public class ConnectionHookTest {

    public static void main(String[] args) throws SQLException {
        final List<String> executionOrder = new ArrayList<>();

        ViburDBCPDataSource ds = createDataSourceNoStatementsCache();
        ds.setPoolInitialSize(0);
        ds.setPoolMaxSize(1);
        ds.getConnHooks().addOnInit(new Hook.InitConnection() {
            @Override
            public void on(Connection rawConnection, long takenNanos) throws SQLException {
                executionOrder.add("init");
            }
        });
        ds.getConnHooks().addOnGet(new Hook.GetConnection() {
            @Override
            public void on(Connection rawConnection, long takenNanos) throws SQLException {
                executionOrder.add("get");
            }
        });
        ds.start();

        Connection connection = ds.getConnection();
        connection.close();

        assertEquals(2, executionOrder.size());
        assertEquals("init", executionOrder.get(0));
        assertEquals("get", executionOrder.get(1));

        connection = ds.getConnection();
        connection.close();

        assertEquals(3, executionOrder.size());
        assertEquals("get", executionOrder.get(2));
    }

    protected static final String PROPERTIES_FILE = "src/test/resources/vibur-dbcp-test.properties";
    protected static final int POOL_INITIAL_SIZE = 2;
    protected static final int POOL_MAX_SIZE = 10;
    protected static final int CONNECTION_TIMEOUT_MS = 5000;

    private static String driverClassName = "com.mysql.cj.jdbc.Driver";
    private static String jdbcUrl = "jdbc:mysql://10.11.20.83:3306/msgframe2.0";
    private static String username = "root";
    private static String password = "root";

    protected static ViburDBCPDataSource createDataSourceNoStatementsCache() throws ViburDBCPException {
        ViburDBCPDataSource dataSource = new ViburDBCPDataSource();

        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        dataSource.setPoolInitialSize(POOL_INITIAL_SIZE);
        dataSource.setPoolMaxSize(POOL_MAX_SIZE);
        dataSource.setConnectionTimeoutInMs(CONNECTION_TIMEOUT_MS);

        dataSource.setConnectionIdleLimitInSeconds(120);

        dataSource.setLogQueryExecutionLongerThanMs(0);
        dataSource.setLogConnectionLongerThanMs(0);
        dataSource.setLogLargeResultSet(2);

        dataSource.setValidateTimeoutInSeconds(1);
        dataSource.setClearSQLWarnings(true);

        dataSource.start();

        return dataSource;
    }
}
