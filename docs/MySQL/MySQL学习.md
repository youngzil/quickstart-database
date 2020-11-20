- [MySQL中文编码问题](#MySQL中文编码问题.md)
- [MySQL主从复制原理](MySQL主从复制原理.md)
- [MySQL事务](MySQL事务.md)
- [mysql介绍](mysql介绍.md)
- [Mysql导出表结构及表数据mysqldump用法](Mysql导出表结构及表数据mysqldump用法.md)
- [MySQL数据库存储引擎](MySQL数据库存储引擎.md)
- [MYSQL语句](MYSQL语句.md)
- [MySQL部署](MySQL部署.md)
- [一次SQL查询使用Limit优化原理分析](一次SQL查询使用Limit优化原理分析.md)
- [为什么GROUP BY 之后不能直接引用原表中的列](为什么GROUPBY之后不能直接引用原表中的列.md)
- [在Linux环境下mysql的root密码忘记解决方法](在Linux环境下mysql的root密码忘记解决方法.md)
- [备份表语句区别](备份表语句区别.md)
- [数据库深度分页](数据库深度分页.md)
- [永远不要在MySQL中使用utf8，改用utf8mb4](永远不要在MySQL中使用utf8，改用utf8mb4.md)
- [修改字段默认值语法](#修改字段默认值语法)


教程
https://www.yiibai.com/mysql/mysql_quick_start.html


Java数据类型和MySql数据类型对应一览
https://blog.csdn.net/zoucui/article/details/6125522



mysql> SHOW DATABASES;
D:\software\mysql-5.6.25-winx64\bin> mysqladmin -u root password "123456";


mysql启动
mysql -h localhost -u root -p




mysql命令教程
http://c.biancheng.net/view/2419.html

---------------------------------------------------------------------------------------------------------------------




## 修改字段默认值语法

alter table 表名 alter column 字段名 drop default; (若本身存在默认值，则先删除)

alter table 表名 alter column 字段名 set default 默认值;(若本身不存在则可以直接设定)



参考  
[Mysql 修改字段默认值](https://www.cnblogs.com/hellojesson/p/6025548.html)   

