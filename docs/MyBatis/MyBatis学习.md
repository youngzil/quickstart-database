- [MyBatis Dynamic SQL框架](#MyBatis Dynamic SQL框架)
- [Mysql中${param}与#{param}区别](#Mysql中${param}与#{param}区别)
- [mybatis一级缓存二级缓存](mybatis一级缓存二级缓存.md)
- [mybatis与hibernate的区别](mybatis与hibernate的区别.md)



---------------------------------------------------------------------------------------------------------------------
## MyBatis Dynamic SQL框架

http://www.mybatis.org/mybatis-dynamic-sql/docs/introduction.html
https://github.com/mybatis/mybatis-dynamic-sql
https://www.oschina.net/p/mybatis-dynamic-sql

MyBatis Dynamic SQL 详细介绍
这个库是生成动态 SQL 语句的框架。可把它看作是一个类型安全的 SQL 模板库，另外还支持 MyBatis3 和 Spring JDBC 模板。
该库将生成格式化为由 MyBatis 或 Spring 使用的完整 DELETE，INSERT，SELECT 和 UPDATE 语句。
最常见的用例是生成语句和一组匹配的参数，这些参数可以被 MyBatis 直接使用。该库还将生成与 Spring JDBC 模板兼容的语句和参数对象。
该库通过实现一个类似 SQL 的 DSL 来创建一个对象，该对象包含完整的 SQL 语句以及该语句所需的任何参数。SQL 语句对象可以被 MyBatis 直接用作映射器方法的参数。


---------------------------------------------------------------------------------------------------------------------
## Mysql中${param}与#{param}区别

${param}传递的参数会被当成sql语句中的一部分，比如传递表名，字段名
例子：(传入值为id)
order by ${param}
则解析成的sql为：
order by id


#{parm}传入的数据都当成一个字符串，会对自动传入的数据加一个双引号
例子：(传入值为id)
select * from table where name = #{param}
则解析成的sql为：
select * from table where name = "id"
为了安全，能用#的地方就用#方式传参，这样可以有效的防止sql注入攻击


sql注入简介
直接上了百度的例子，感觉一看就清晰明了
某个网站的登录验证的SQL查询代码为：
strSQL="SELECT*FROMusersWHERE(name='"+userName+"')and(pw='"+passWord+"');"
恶意填入
userName="1'OR'1'='1";
与passWord="1'OR'1'='1";
时，将导致原本的SQL字符串被填为
strSQL="SELECT*FROMusersWHERE(name='1'OR'1'='1')and(pw='1'OR'1'='1');"
也就是实际上运行的SQL命令会变成下面这样的
strSQL="SELECT*FROMusers;"
这样在后台帐号验证的时候巧妙地绕过了检验，达到无账号密码，亦可登录网站。所以SQL注入攻击被俗称为黑客的填空游戏。


---------------------------------------------------------------------------------------------------------------------





---------------------------------------------------------------------------------------------------------------------




