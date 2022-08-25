package org.quickstart.mysql.example;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-01 15:38
 */
import com.mysql.management.util.QueryUtil;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/** Created by kail on 2017/2/5. */
public class MySQLTest extends BaseMySQLTest {

  private QueryUtil queryUtil;

  @Before
  public void before() throws SQLException, ClassNotFoundException {
    super.before();

    queryUtil = new QueryUtil(connection);
  }

  /** 删除表 */
  @Test
  public void testDropTable() {
    String sql = "DROP TABLE `T_TEST` ";
    queryUtil.execute(sql);
  }

  /** 创建表 */
  @Test
  public void testCreateTable() {
    String sql =
        ""
            + "CREATE TABLE `T_TEST` (\n"
            + "  `ID` int(11) NOT NULL AUTO_INCREMENT,\n"
            + "  `UNAME` varchar(50) DEFAULT NULL,\n"
            + "  `AGE` int(11) DEFAULT NULL,\n"
            + "  `CREATE_TIME` datetime ,\n"
            + "  PRIMARY KEY (`ID`)\n"
            + ") ENGINE=InnoDB DEFAULT CHARSET=utf8";

    queryUtil.execute(sql);
  }

  /** 插入 */
  @Test
  public void testInsert() {
    List returningKeys =
        queryUtil.executeUpdateReturningKeys(
            "INSERT INTO T_TEST (UNAME,AGE,CREATE_TIME) VALUES (?,?,NOW())",
            new Object[] {"Kail", 24});
    println(returningKeys);
  }

  /** 查询 */
  @Test
  public void testSelect() {
    List query = queryUtil.executeQuery("SELECT * FROM T_TEST");
    println(query);
  }

  /** 测试对函数的支持 */
  @Test
  public void testMySQLFunction() {
    String[] functions = {
      "SELECT NOW()",
      "SELECT CURDATE()",
      "SELECT CURTIME()",
      "SELECT SUBDATE(CURDATE(),1)",
      "sELECT SUBDATE(NOW(),INTERVAL 1 HOUR)",
      "SELECT DATE_FORMAT(NOW(),'%W,%D %M %Y %r')",
      "SELECT DATE_FORMAT(19990330,'%Y-%m-%d')",
      "SELECT DATE_FORMAT(NOW(),'%h:%i %p')",
      "SELECT MD5('Kail')",
      "SELECT IFNULL(1,2)",
      "SELECT IFNULL(NULL,10)",
      "SELECT IF(1=2,1,2)",
      "SELECT CASE 9 WHEN 1 THEN 'a' WHEN 2 THEN 'b' ELSE 'N/A' END",
      "SELECT CASE WHEN (2+2)=4 THEN 'OK' WHEN(2+2)<>4 THEN 'not OK' END",
      "SELECT DATABASE()",
      "SELECT USER()",
      "SELECT VERSION()"
    };

    for (String func : functions) {
      println(String.format("函数 ：%-70s  结果：%s", func, queryUtil.queryForString(func)));
    }
  }

  /**
   * 如果不想每次都关闭MySQL服务器(不关闭会更快，避免每次都启动) 可以注释掉 BaseMySQLTest.afterClass 关闭的话，打开
   * BaseMySQLTest.afterClass 注释，执行一下
   */
  @Test
  public void noting() {
    println("only shutdown");
  }
}
