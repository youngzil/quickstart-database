
- [SQL | DDL, DQL, DML, DCL and TCL Commands](#SQL-|-DDL,DQL,DML,DCL-and-TCL-Commands)
- [DDL命令示例](#DDL命令示例)
- [DML命令示例](#DML命令示例)
- [DCL命令示例](#DCL命令示例)
- [使用SQL查询所有数据库名和表名](#使用SQL查询所有数据库名和表名)



---------------------------------------------------------------------------------------------------------------------
## SQL | DDL,DQL,DML,DCL and TCL Commands


SQL命令主要分为四类：
1. DDL –数据定义语言
1. DQl –数据查询语言
1. DML –数据处理语言
1. DCL –数据控制语言



SQL主要分成四部分：
（1）数据定义。（SQL DDL）用于定义SQL模式、基本表、视图和索引的创建和撤消操作。
（2）数据操纵。（SQL DML）数据操纵分成数据查询和数据更新两类。数据更新又分成插入、删除、和修改三种操作。
（3）数据控制。包括对基本表和视图的授权，完整性规则的描述，事务控制等内容。
（4）嵌入式SQL的使用规定。涉及到SQL语句嵌入在宿主语言程序中使用的规则。



1. DDL（Data Definition Language）数据库定义语言statements are used to define the database structure or schema.

DDL是SQL语言的四大功能之一。
用于定义数据库的三级结构，包括外模式、概念模式、内模式及其相互之间的映像，定义数据的完整性、安全控制等约束
DDL不需要commit.

DDL（数据定义语言）： DDL或数据定义语言实际上由可用于定义数据库模式的SQL命令组成。它仅处理数据库模式的描述，并用于创建和修改数据库中数据库对象的结构。
DDL命令的示例：
CREATE –用于创建数据库或其对象（如表，索引，函数，视图，存储过程和触发器）。
ALTER -is用于改变数据库的结构。
DROP –用于从数据库中删除对象。
TRUNCATE –用于删除表中的所有记录，包括分配给记录的所有空间都将被删除。
COMMENT –用于向数据字典添加注释。
RENAME -is用于重命名存在于数据库中的对象。




2. DML（Data Manipulation Language）数据操纵语言statements are used for managing data within schema objects.
   DML（数据操作语言）：处理数据库中存在的数据的SQL命令属于DML或数据操作语言，并且包括大多数SQL语句。
   
由DBMS提供，用于让用户或程序员使用，实现对数据库中数据的操作。
DML分成交互型DML和嵌入型DML两类。
依据语言的级别，DML又可分成过程性DML和非过程性DML两种。
需要commit.

DML的示例：
INSERT –用于将数据插入表中。
UPDATE –用于更新表中的现有数据。
DELETE –用于从数据库表中删除记录。
SELECT
MERGE
CALL
EXPLAIN PLAN
LOCK TABLE


DQL（数据查询语言）：
DQL语句用于对模式对象内的数据执行查询。DQL命令的目的是基于传递给它的查询来获取某种模式关系。

DQL的示例：
SELECT –用于从数据库检索数据。



3. DCL（Data Control Language）数据库控制语言  授权，角色控制等
DCL（数据控制语言）： DCL包含诸如GRANT和REVOKE之类的命令，这些命令主要处理数据库系统的权限，权限和其他控制。

DCL命令的示例：
GRANT -授权，gives用户访问权限的数据库。
REVOKE-取消授权，撤消使用GRANT命令给定的用户访问权限。



4. TCL（Transaction Control Language）事务控制语言
TCL（事务控制语言）： TCL命令处理数据库内的事务。

TCL命令的示例：
COMMIT –提交事务。
ROLLBACK –在发生任何错误的情况下回滚事务。
SAVEPOINT –在事务中设置一个保存点。
SET TRANSACTION –指定交易的特征。


[SQL | DDL，DQL，DML，DCL和TCL命令](https://www.geeksforgeeks.org/sql-ddl-dql-dml-dcl-tcl-commands/)  


---------------------------------------------------------------------------------------------------------------------
## DDL命令示例

MySQL为例子

连接数据库
mysql -u root -p

退出 mysql> 命令提示窗口可以使用 exit 命令

使用数据库
use RUNOOB;


CREATE DATABASE 数据库名;

drop database <数据库名>;


[MySQL 教程](https://www.runoob.com/mysql/mysql-connection.html)



---------------------------------------------------------------------------------------------------------------------
## DML命令示例

MySQL为例子


CREATE TABLE IF NOT EXISTS `runoob_tbl`(
`runoob_id` INT UNSIGNED AUTO_INCREMENT,
`runoob_title` VARCHAR(100) NOT NULL,
`runoob_author` VARCHAR(40) NOT NULL,
`submission_date` DATE,
PRIMARY KEY ( `runoob_id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


删除表
DROP TABLE table_name ;


INSERT INTO runoob_tbl(runoob_title, runoob_author, submission_date) VALUES("学习 PHP", "菜鸟教程", NOW());


select * from runoob_tbl;

UPDATE runoob_tbl SET runoob_title='学习 C++' WHERE runoob_id=3;

DELETE FROM runoob_tbl WHERE runoob_id=3;


[MySQL 教程](https://www.runoob.com/mysql/mysql-create-tables.html)  


---------------------------------------------------------------------------------------------------------------------
## DCL命令示例

MySQL为例子





---------------------------------------------------------------------------------------------------------------------
## 使用SQL查询所有数据库名和表名

### MySQL中查询所有数据库名和表名

查询所有数据库
show databases;

查询指定数据库中所有表名
select table_name from information_schema.tables where table_schema='database_name' and table_type='base table';

查询指定表中的所有字段名
select column_name from information_schema.columns where table_schema='database_name' and table_name='table_name';

查询指定表中的所有字段名和字段类型
select column_name,data_type from information_schema.columns where table_schema='database_name' and table_name='table_name';




### Oracle中查询所有数据库名和表名
查询所有数据库
由于Oralce没有库名,只有表空间,所以Oracle没有提供数据库名称查询支持，只提供了表空间名称查询。
select * from v$tablespace;--查询表空间(需要一定权限)

查询当前数据库中所有表名
select * from user_tables;

查询指定表中的所有字段名
select column_name from user_tab_columns where table_name = 'table_name';--表名要全大写

查询指定表中的所有字段名和字段类型
select column_name, data_type from user_tab_columns where table_name = 'table_name';--表名要全大写



[使用SQL查询所有数据库名和表名](https://blog.csdn.net/u012643122/article/details/44039155)


---------------------------------------------------------------------------------------------------------------------



