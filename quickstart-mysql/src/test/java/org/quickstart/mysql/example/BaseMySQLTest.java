package org.quickstart.mysql.example;

import com.mysql.management.MysqldResource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-01 15:37
 */
public class BaseMySQLTest {

  private static final String MY_PROPERTIES = "my.properties"; // 服务配置写
  private static final File BASE_DIR = new File("/opt/data/mysql-embed/baseDir"); // MySQL 启动启动程序目录(mysql 和 mysqld)
  private static final File DATA_DIR = new File("/opt/data/mysql-embed/dataDir"); // MySQL 数据文件目录(表机构和数据)

  /**
   * 连接信息
   */
  private static final String driverName = "com.mysql.jdbc.Driver";
  private static final String url = "jdbc:mysql://127.0.0.1:3336/test?useUnicode=true&characterEncoding=UTF-8";
  private static final String username = "root";
  private static final String password = "";

  private static MysqldResource mysqlInstance = null;
  protected Connection connection = null;

  /**
   * 启动mysql 服务器
   */
  @BeforeClass
  public static void beforeClass() throws IOException {
    // 读取配置
    Properties props = new Properties();
    props.load(ClassLoader.getSystemResourceAsStream(MY_PROPERTIES));

    // 处理启动参数
    final Set<Object> keys = props.keySet();
    final Map<String, String> options = new HashMap<>(keys.size());
    for (Object key : keys) {
      String val = props.getProperty(key.toString());
      if ("".equals(val)) {
        options.put(key.toString(), null);
      } else {
        options.put(key.toString(), val);
      }
    }

    // 启动 MySQL 资源实例
    mysqlInstance = new MysqldResource(BASE_DIR, DATA_DIR);
    if (!mysqlInstance.isRunning()) {
      mysqlInstance.start("Em_MySQL", options);
    }
  }

  /**
   * 获取 MySQL 链接
   */
  @Before
  public void before() throws ClassNotFoundException, SQLException {
    Class.forName(driverName);
    connection = DriverManager.getConnection(url, username, password);
  }

  /**
   * 关闭 MySQL 链接
   */
  @After
  public void after() throws SQLException {
    if (connection != null) {
      connection.close();
    }
  }

  /**
   * 关闭 MySQL 服务实例
   */
  @AfterClass
  public static void afterClass() {
    if (null != mysqlInstance) {
      mysqlInstance.shutdown();
    }
  }

  public void println(Object obj) {
    System.out.println(obj);
  }
}
