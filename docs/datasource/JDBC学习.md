JDBC规范提供的接口和类 
Statement、PreparedStatement、CallableStatement区别

---------------------------------------------------------------------------------------------------------------------

JDBC代表Java数据库连接(Java Database Connectivity)，它是用于Java编程语言和数据库之间的数据库无关连接的标准Java API，换句话说：JDBC是用于在Java语言编程中与数据库连接的API。


JDBC API提供以下接口和类 
-DriverManager：此类管理数据库驱动程序列表。 使用通信子协议将来自java应用程序的连接请求与适当的数据库驱动程序进行匹配。在JDBC下识别某个子协议的第一个驱动程序将用于建立数据库连接。
Driver：此接口处理与数据库服务器的通信。我们很少会直接与Driver对象进行交互。 但会使用DriverManager对象来管理这种类型的对象。 它还提取与使用Driver对象相关的信息。
Connection：此接口具有用于联系数据库的所有方法。 连接(Connection)对象表示通信上下文，即，与数据库的所有通信仅通过连接对象。Statement：使用从此接口创建的对象将SQL语句提交到数据库。 除了执行存储过程之外，一些派生接口还接受参数。
ResultSet：在使用Statement对象执行SQL查询后，这些对象保存从数据库检索的数据。 它作为一个迭代器并可移动ResultSet对象查询的数据。
SQLException：此类处理数据库应用程序中发生的任何错误。


构建JDBC应用程序涉及以下六个步骤 
-导入包：需要包含包含数据库编程所需的JDBC类的包。 大多数情况下，使用import java.sql.*就足够了。
注册JDBC驱动程序：需要初始化驱动程序，以便可以打开与数据库的通信通道。
打开一个连接：需要使用DriverManager.getConnection()方法创建一个Connection对象，它表示与数据库的物理连接。
执行查询：需要使用类型为Statement的对象来构建和提交SQL语句到数据库。
从结果集中提取数据：需要使用相应的ResultSet.getXXX()方法从结果集中检索数据。
清理环境：需要明确地关闭所有数据库资源，而不依赖于JVM的垃圾收集。


下表列出了常用的JDBC驱动程序名称和数据库URL。
RDBMS	JDBC驱动程序名称	URL格式
MySQL	com.mysql.jdbc.Driver	jdbc:mysql://hostname/databaseName
ORACLE	oracle.jdbc.driver.OracleDriver	jdbc:oracle:thin:@hostname:portNumber:databaseName
PostgreSQL	org.postgresql.Driver	jdbc:postgresql://hostname:port/dbname
DB2	com.ibm.db2.jdbc.net.DB2Driver	jdbc:db2:hostname:port Number/databaseName
Sybase	com.sybase.jdbc.SybDriver	jdbc:sybase:Tds:hostname: portNumber/databaseName


在JDBC编程中，常用Statement、PreparedStatement 和 CallableStatement三种方式来执行查询语句，其中 Statement 用于通用查询， PreparedStatement 用于执行参数化查询，而 CallableStatement则是用于存储过程。

使用预编译的好处：
1：PreparedStatement比 Statement 更快
使用 PreparedStatement 最重要的一点好处是它拥有更佳的性能优势，SQL语句会预编译在数据库系统中。执行计划同样会被缓存起来，它允许数据库做参数化查询。使用预处理语句比普通的查询更快，因为它做的工作更少（数据库对SQL语句的分析，编译，优化已经在第一次查询前完成了）。

2：PreparedStatement可以防止SQL注入式攻击
SQL 注入攻击:SQL 注入是利用某些系统没有对用户输入的数据进行充分的检查，而在用户输入数据中注入非法的 SQL 语句段或命令，从而利用系统的 SQL 引擎完成恶意行为的做法。

接口	推荐使用
Statement	用于对数据库进行通用访问，在运行时使用静态SQL语句时很有用。 Statement接口不能接受参数。
PreparedStatement	当计划要多次使用SQL语句时使用。PreparedStatement接口在运行时接受输入参数。
CallableStatement	当想要访问数据库存储过程时使用。CallableStatement接口也可以接受运行时输入参数。

PreparedStatement：
JDBC中的所有参数都由 ? 符号作为占位符，这被称为参数标记。 在执行SQL语句之前，必须为每个参数(占位符)提供值。
setXXX()方法将值绑定到参数，其中XXX表示要绑定到输入参数的值的Java数据类型。 如果忘记提供绑定值，则将会抛出一个SQLException。
每个参数标记是它其顺序位置引用。第一个标记表示位置1，下一个位置2等等。 该方法与Java数组索引不同(它不从0开始)。



JDBC数据类型下表列出了默认的JDBC数据类型与Java数据类型转换，当使用PreparedStatement或CallableStatement对象时可调用setXXX()方法或ResultSet.updateXXX()方法。
SQL	JDBC/Java	setXXX	updateXXX
VARCHAR	java.lang.String	setString	updateString
CHAR	java.lang.String	setString	updateString
LONGVARCHAR	java.lang.String	setString	updateString
BIT	boolean	setBoolean	updateBoolean
NUMERIC	java.math.BigDecimal	setBigDecimal	updateBigDecimal
TINYINT	byte	setByte	updateByte
SMALLINT	short	setShort	updateShort
INTEGER	int	setInt	updateInt
BIGINT	long	setLong	updateLong
REAL	float	setFloat	updateFloat
FLOAT	float	setFloat	updateFloat
DOUBLE	double	setDouble	updateDouble
VARBINARY	byte[ ]	setBytes	updateBytes
BINARY	byte[ ]	setBytes	updateBytes
DATE	java.sql.Date	setDate	updateDate
TIME	java.sql.Time	setTime	updateTime
TIMESTAMP	java.sql.Timestamp	setTimestamp	updateTimestamp
CLOB	java.sql.Clob	setClob	updateClob
BLOB	java.sql.Blob	setBlob	updateBlob
ARRAY	java.sql.Array	setARRAY	updateARRAY
REF	java.sql.Ref	SetRef	updateRef
STRUCT	java.sql.Struct	SetStruct	updateStruct


参考教程
https://www.yiibai.com/jdbc/jdbc_quick_guide.html
https://www.cnblogs.com/Qian123/p/5339164.html#_label1

