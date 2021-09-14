
SQL唯一约束、主键、外键
三范式：
第一范式(1NF)：定义所需要的数据项、确保有数据没有重复的组、确保有一个主键、
第二范式(2NF)：满足所有1NF的规则，必须有任意列不依赖主键关系
第三范式(3NF)：满足第二范式 、所有非主字段都是依赖于主键


第一范式是最基本的范式。如果数据库表中的所有字段值都是不可分解的原子值，就说明该数据库表满足了第一范式。
第二范式：在一个数据库表中，一个表中只能保存一种数据，不可以把多种数据保存在同一张数据库表中。也就是一类，比如订单信息和客户信息分成两个表
第三范式需要确保数据表中的每一列数据都和主键直接相关，而不能间接相关。


UNION/UNION ALL区别


Select、where、order by、COUNT、HAVING 、


SELECT INTO 和 INSERT INTO SELECT.md


---------------------------------------------------------------------------------------------------------------------
https://www.yiibai.com/sql/sql-rdbms-concepts.html

SQL数据库是一种语言，它包括数据库的创建，删除，获取数据行和修改行等 
SQL是一种ANSI（美国国家标准协会）的标准，但也有许多不同版本的SQL语言。

SQL是结构化查询语言，这是一种计算机语言，用于存储，操纵和检索存储在关系数据库中的数据。 
SQL是关系数据库系统的标准语言。所有关系型数据库管理系统，如MySQL, MS Access, Oracle, Sybase, Informix, postgres 和SQL Server使用SQL作为标准数据库语言。 
此外，它们也使用不同的方言，如： 
MS SQL Server 使用 
T-SQL, Oracle 使用 
PL/SQL, MS Access 的SQL版本叫 JET SQL (本地格式) 等


SQL DEFAULT约束
如果CUSTOMERS表已经创建，当添加一个DEFAULT约束SALARY列，类似如下的声明：
ALTER TABLE CUSTOMERS
   MODIFY SALARY  DECIMAL (18, 2) DEFAULT 5000.00;

   要删除DEFAULT约束，使用下面的SQL语句：ALTER TABLE CUSTOMERS
   ALTER COLUMN SALARY DROP DEFAULT;


SQL唯一约束
如果CUSTOMERS表已经创建，然后要将唯一约束添加到AGE列，类似如下的声明：ALTER TABLE CUSTOMERS
   MODIFY AGE INT NOT NULL UNIQUE; 
还可以使用下面的语法，它支持命名的多个列的约束：ALTER TABLE CUSTOMERS
   ADD CONSTRAINT myUniqueConstraint UNIQUE(AGE, SALARY);

要删除UNIQUE约束，请使用以下SQL：
ALTER TABLE CUSTOMERS
   DROP CONSTRAINT myUniqueConstraint; 如果正在使用MySQL，那么可以使用下面的语法：ALTER TABLE CUSTOMERS
   DROP INDEX myUniqueConstraint;


SQL主键
要在“ID”列创建PRIMARY KEY约束，假设CUSTOMERS表已经存在，使用下面的SQL语法：
ALTER TABLE CUSTOMER ADD PRIMARY KEY (ID); 
注：如果您使用ALTER TABLE语句添加主键，主键列必须已经被声明为不包含NULL值(要创建表时)。

当CUSTOMERS表已经存在，要在“ID”和“NAMES”列创建PRIMARY KEY约束，可以使用下面的SQL语法：
ALTER TABLE CUSTOMERS 
   ADD CONSTRAINT PK_CUSTID PRIMARY KEY (ID, NAME);

您可以清除表主键约束，使用语法：ALTER TABLE CUSTOMERS DROP PRIMARY KEY ;


SQL外键
如果ORDERS表已经创建，以及外键尚未设置，通过改变一个表指定外键，使用下面的语法。
ALTER TABLE ORDERS 
   ADD FOREIGN KEY (Customer_ID) REFERENCES CUSTOMERS (ID);

要删除一个外键约束，使用下面的SQL语句：ALTER TABLE ORDERS
   DROP FOREIGN KEY;


SQL CHECK约束

例如，下面的SQL创建一个新的表名为CUSTOMERS，并添加了五列。在这里，我们添加使用AGE列的检查，这样就不能有18岁以下的任何客户：CREATE TABLE CUSTOMERS(
       ID   INT              NOT NULL,
       NAME VARCHAR (20)     NOT NULL,
       AGE  INT              NOT NULL CHECK (AGE >= 18),
       ADDRESS  CHAR (25) ,
       SALARY   DECIMAL (18, 2),       
       PRIMARY KEY (ID)
); 
如果CUSTOMERS表已经创建， 然后将CHECK约束添加到AGE列，使用类似如下的声明：ALTER TABLE CUSTOMERS
   MODIFY AGE INT NOT NULL CHECK (AGE >= 18 ); 还
可以使用下面的语法，它支持命名的多个列的约束：
ALTER TABLE CUSTOMERS
   ADD CONSTRAINT myCheckConstraint CHECK(AGE >= 18);
   
要删除CHECK约束，使用下面的SQL语法。此语法不在MySQL中使用：ALTER TABLE CUSTOMERS
   DROP CONSTRAINT myCheckConstraint;


SQL索引约束
您可以创建单个或多个列索引使用以下语法：CREATE INDEX index_name
    ON table_name ( column1, column2.....); 
    要在AGE列上创建一个索引， 来优化客户搜索一个特定的年龄，以下是SQL语法：
CREATE INDEX idx_age
    ON CUSTOMERS ( AGE ); 
    
  删除索引约束： 要删除索引的约束，使用下面的SQL：ALTER TABLE CUSTOMERS
   DROP INDEX idx_age;


SQL NULL值
SQL NULL是用来代表缺失值的术语。在表中的NULL值是显示为空白字段的值。 一个NULL值的字段是一个没有值的字段。这是非常重要的是要明白，一个NULL值与小于零值或包含空格的字段不同。


第一范式(1NF)：定义所需要的数据项、确保有数据没有重复的组、确保有一个主键、
第二范式(2NF)：满足所有1NF的规则，必须有任意列不依赖主键关系
第三范式(3NF)：满足第二范式 、所有非主字段都是依赖于主键


第一范式是最基本的范式。如果数据库表中的所有字段值都是不可分解的原子值，就说明该数据库表满足了第一范式。
第二范式：在一个数据库表中，一个表中只能保存一种数据，不可以把多种数据保存在同一张数据库表中。也就是一类，比如订单信息和客户信息分成两个表
第三范式需要确保数据表中的每一列数据都和主键直接相关，而不能间接相关。



第一范式(1NF)设置了一个有组织的数据库非常基本的规则： 
定义所需要的数据项，因为它们成为在表中的列。放在一个表中的相关的数据项。 
确保有数据没有重复的组。 
确保有一个主键。

第二范式指出，它应满足所有1NF的规则，必须有任意列不依赖主键关系：

表满足以下条件时就是第三范式： 
满足第二范式 
所有非主字段都是依赖于主键



Select、where、order by、COUNT、HAVING 、


SELECT 语句
SELECT DISTINCT 

WHERE 子句
AND/OR、IN、BETWEEN、LIKE

排序语句
ORDER BY column_name {ASC|DESC}

统计语句
COUNT 子句
SELECT COUNT(column_name) FROM   table_name WHERE  CONDITION;

分组语句
GROUP BY 子句：
SELECT SUM(column_name) 
FROM   table_name 
WHERE  condition 
GROUP BY column_name;

HAVING 子句：
SELECT SUM(column_name) 
FROM   table_name 
WHERE  condition 
GROUP BY column_name 
HAVING (arithematic function condition);

SELECT column1, column2
FROM table1, table2
WHERE [ conditions ]
GROUP BY column1, column2
HAVING [ conditions ]
ORDER BY column1, column2

DML语句
create、drop、alter
SQL的DROP TABLE语句用来删除表定义及其所有的数据，索引，触发器，约束和权限规范。 
注意: 使用此命令，因为一旦一个表被删除，那么所有信息表中的可用也将永远失去了，所以要小心。



SQL CREATE INDEX 语句 :CREATE UNIQUE INDEX index_name ON table_name ( column1, column2,...columnN); 
SQL DROP INDEX 语句 :ALTER TABLE table_name DROP INDEX index_name;


显示表结构信息：DESC table_name;
清空表数据和空间：TRUNCATE TABLE table_name;
ALTER TABLE 语句:ALTER TABLE table_name {ADD|DROP|MODIFY} column_name {data_ype};
TABLE重命名 :ALTER TABLE table_name RENAME TO new_table_name;


1、INSERT INTO 语句:INSERT INTO table_name( column1, column2....columnN)
VALUES ( value1, value2....valueN); 
2、UPDATE 语句:UPDATE table_name 
SET column1 = value1, column2 = value2....columnN=valueN [ WHERE  CONDITION ]; 
3、DELETE 语句:DELETE FROM table_name WHERE  {CONDITION};

CREATE DATABASE database_name; 
DROP DATABASE database_name; 
USE database_name; 
COMMIT; 
ROLLBACK;


使用已经存在的表创建新表
可以使用CREATE TABLE语句和SELECT语句的组合现有表的副本来创建表。 
新表具有相同的列定义。可以选择所有列的特定列。 
当你使用现有的表中创建一个新表，新表将在旧表使用现有值来填充。 
语法 从另一个表创建表的基本语法如下：
CREATE TABLE NEW_TABLE_NAME AS
   SELECT [ column1, column2...columnN ]
   FROM EXISTING_TABLE_NAME
   [ WHERE ] 这里, column1, column2...是现有的表中和相同的字段将被用于创建新表的字段。


用一个表填充另一个表： 
可以通过使用select语句填充一个表的数据到另一个表中的字段，这是必需的，以填充第一个表。下面是语法：
INSERT INTO first_table_name [(column1, column2, ... columnN)] 
   SELECT column1, column2, ...columnN 
   FROM second_table_name
   [WHERE condition];


LIKE子句使用通配符运算符比较相似的值。符合LIKE操作符配合使用2个通配符： 
百分号 (%) 
下划线 (_) 
百分号代表零个，一个或多个字符。下划线表示单个数字或字符。所述符号可以在组合使用。

语句	描述
WHERE SALARY LIKE '200%'	查找以200开始的任何值
WHERE SALARY LIKE '%200%'	查找含有200的任何值
WHERE SALARY LIKE '_00%'	查找在第二和第三位置是00的任何值
WHERE SALARY LIKE '2_%_%'	查找开始2并且长度至少为3个字符的任何值
WHERE SALARY LIKE '%2'	查找以2结尾的任何值
WHERE SALARY LIKE '_2%3'	查找第二位置为2，并以3结束的任何值
WHERE SALARY LIKE '2___3'	查找以5位数字-开头为2，并3结束的任何值


SQL TOP,LIMIT,ROWNUM子句
SQL TOP子句用于从表中获取一个TOP N数字或X%的纪录。 
注意: 不是所有的数据库都支持TOP子句。例如，MySQL使用LIMIT子句来获取记录；Oracle使用ROWNUM获取有限的记录数。

取前客户表的前3个记录的例子：
SQL SERVER服务器：SELECT TOP 3 * FROM CUSTOMERS;
MySQL服务器：SELECT * FROM CUSTOMERS LIMIT 3;
Oracle服务器：SELECT * FROM CUSTOMERS WHERE ROWNUM <= 3;


GROUP BY子句的基本语法如下。GROUP BY子句中必须遵循WHERE子句中的条件，如果使用必须先于ORDER BY子句。SELECT column1, column2
FROM table_name
WHERE [ conditions ]
GROUP BY column1, column2
ORDER BY column1, column2


获取使用自己的优先顺序行，SELECT查询将如下：
SQL> SELECT * FROM CUSTOMERS
    ORDER BY (CASE ADDRESS
    WHEN 'DELHI' 	 THEN 1
    WHEN 'BHOPAL' 	 THEN 2
    WHEN 'KOTA' 	 THEN 3
    WHEN 'AHMADABAD' THEN 4
    WHEN 'MP' 	THEN 5
    ELSE 100 END) ASC, ADDRESS DESC;
匹配ADDRESS列，等于DELHI设置为1，等于BHOPAL设置为2等等，转换后先升序排列，再按照ADDRESS倒序


SQL中的约束：
NOT NULL 约束: 确保列不能有NULL值。
DEFAULT约束: 提供未指定时为列的默认值。
UNIQUE约束: 确保了在一列中的所有的值是不同(唯一)的。
PRIMARY Key(主键) : 唯一标识数据库表中的每一行/记录。
FOREIGN Key(外键): 唯一标识任何其他数据库表中的行/记录。
CHECK约束: CHECK约束可以确保列中的所有值满足一定的条件。
INDEX索引: 使用非常快速地创建和检索数据库中的数据。

当一个表是用CREATE TABLE语句创建，也可以使用ALTER TABLE语句创建表，即使创建约束后，约束也可以再次指定。

删除约束： 
您所定义的任何约束可以使用带有DROP CONSTRAINT选项的ALTER TABLE命令删除。 
例如，删除EMPLOYEES表的主键约束，可以使用下面的命令：
ALTER TABLE EMPLOYEES DROP CONSTRAINT EMPLOYEES_PK; 
一些实现中可提供用于删除某些约束的快捷方式。例如，要在Oracle表中删除主键约束，可以使用下面的命令：
ALTER TABLE EMPLOYEES DROP PRIMARY KEY; 
一些实现中允许禁用约束。而不是从数据库中永久删除约束，您可能需要暂时禁用约束，以后再启用它。


SQL Join 类型: 在SQL连接有不同类型可用：
INNER JOIN: 返回记录当两个表有匹配。
LEFT JOIN: 返回左表中所有的行，即使右表中没有匹配。
RIGHT JOIN: 返回右表中所有的行，即使有在左表中没有匹配。
FULL JOIN: 返回表中匹配的所有行。
SELF JOIN: 是用来连接表本身，如果表有两张表，暂时改名至少在一个表中的SQL语句。
CARTESIAN JOIN: 返回来自两个或更多个联接的表的记录的集合的笛卡尔乘积。


INNER JOIN基本语法如下：
SELECT table1.column1, table2.column2...
FROM table1
INNER JOIN table2 ON table1.common_field = table2.common_field;


如果你的数据库不支持FULL JOIN，如MySQL不支持FULL JOIN，那么可以使用UNION ALL子句，将两个JOIN为如下


自连接：使用表别名，自己和自己关联，和两个不同的表关联一样

笛卡尔连接或交叉连接从两个或多个连接表返回笛卡尔乘积的记录。因此，它相当于一个内部联接，其中联接条件始终计算为真或者联接条件是从语句中空缺。
笛卡尔积记录数是m*n，如两个表关联没有where条件，查询出来的记录是m*n
SELECT  ID, NAME, AMOUNT, DATE FROM CUSTOMERS, ORDERS;


UNION/UNION ALL区别

UNION子句/操作符用于合并两个或多个SELECT语句的结果，不返回任何重复的行。union all直接连接，会返回重复的行
UNION ALL运算符用于合并两个SELECT语句，包括重复行的结果。 适用于UNION同样的规则也适用于UNION ALL操作。
要使用UNION，每个SELECT必须选择相同的列数，相同数目的列表达式，相同的数据类型，并让它们以相同的顺序，但它们不必具有相同的长度。



SQL INTERSECT子句: 是用来将两个SELECT语句，但只从第一个SELECT语句返回完全相同于第二个SELECT语句结果的所有行。 
SQL EXCEPT子句 : SQL EXCEPT子句/操作符用于合并两个SELECT语句，并从那些没有被第二个SELECT语句返回的第一个SELECT语句返回行。

这意味着INTERSECT是由两个SELECT语句返回相同的行(唯一)。正如使用UNION操作，同样的规则可使用在INTERSECT运算符。 但MySQL不支持INTERSECT操作
这意味着EXCEPT仅返回行，在第二个SELECT语句不可用。正如使用UNION操作，同样的规则时，使用EXCEPT操作符适用。MySQL不支持EXCEPT运算符。


SQL别名语法：使用AS，AS可以省略（表别名或列别名的时候）

索引是数据库的搜索引擎使用，以加快数据检索特定的查找表。简单地说，索引是一个指向表中的数据。数据库中的索引非常类似于在一本书的索引。
索引有助于加快SELECT和WHERE子句查询，但它会减慢数据输入，使用UPDATE和INSERT语句。索引可创建或删除，但对数据不会有影响。
创建索引包括CREATE INDEX语句，它允许重命名索引，指定表和其中一个或多个列索引，并指示索引是否为升序或降序排序。 
索引是唯一的，类似于UNIQUE约束，索引防止在列的列或组合在其上有一个索引重复条目。

CREATE INDEX的基本语法如下：CREATE INDEX index_name ON table_name (column_name);
1、单列索引是一个基于只有创建表列。
2、唯一索引不仅用于性能，而且要求数据的完整性。唯一索引不允许有任何重复值插入到表中。其基本语法如下：CREATE UNIQUE INDEX index_name on table_name (column_name);
3、组合索引在表上的两个或多个列的索引。
是否要创建一个单列索引或复合索引，考虑到列，您可以使用非常频繁的查询的WHERE子句作为过滤条件。
4、隐式索引是自动由数据库服务器创建对象时创建的索引。索引是主键约束和唯一性约束自动创建。

索引可以使用SQL DROP命令删除。 应当谨慎删除索引时导致的性能可能会减慢或改善。 其基本语法如下：DROP INDEX index_name;


尽管索引的目的在于提高数据库的性能，以下几种情况应该避免使用。以下准则显示，当使用索引应该重新考虑： 
1、索引不应该用在小型表上。 
2、有频繁的，大批量更新或插入操作的表。 
3、索引不应该用于对包含大量NULL值的列。 
4、列经常操纵不应该被索引。



SQL ALTER TABLE命令用于添加，删除或修改列在一个现有的表。 
也可以使用ALTER TABLE命令可在现有的表中添加和删除各种约束。 
ALTER TABLE基本语法来添加新的列到现有表如下：ALTER TABLE table_name ADD column_name datatype; 
ALTER TABLE用于DROP COLUMN在现有表的基本语法如下：ALTER TABLE table_name DROP COLUMN column_name; 
ALTER TABLE的基本语法更改列的数据类型(DATA TYPE)在表中如下：ALTER TABLE table_name MODIFY COLUMN column_name datatype; 
ALTER TABLE添加NOT NULL约束到一个表的列的基本语法如下：ALTER TABLE table_name MODIFY column_name datatype NOT NULL; 
ALTER TABLE添加唯一约束到表的基本语法如下：ALTER TABLE table_name  ADD CONSTRAINT MyUniqueConstraint UNIQUE(column1, column2...); 
ALTER TABLE添加CHECK约束到表的基本语法如下：ALTER TABLE table_name  ADD CONSTRAINT MyUniqueConstraint CHECK (CONDITION); 
ALTER TABLE添加PRIMARY KEY约束到表的基本语法如下：ALTER TABLE table_name ADD CONSTRAINT MyPrimaryKey PRIMARY KEY (column1, column2...); 
ALTER TABLE从表中删除约束的基本语法如下：ALTER TABLE table_name  DROP CONSTRAINT MyUniqueConstraint; 
如果使用MySQL，参考代码如下：ALTER TABLE table_name  DROP INDEX MyUniqueConstraint; 
ALTER TABLE从表中删除主键约束的基本语法如下：ALTER TABLE table_name  DROP CONSTRAINT MyPrimaryKey; 
如果使用MySQL，代码如下：ALTER TABLE table_name  DROP PRIMARY KEY;


TRUNCATE TABLE  table_name;
DELETE FROM table_name;
DROP TABLE  table_name;

视图无非是存储在数据库中的相关名称的SQL语句。视图实际上是一个表中的预定义的SQL查询形式的组合物。 
视图可以包含一个表中的所有行或从表中选择部分行。视图可以从一个或多个表取决于书面SQL查询来创建。 视图是一种虚拟表，让用户做到以下几点： 
1、用户或用户类别的找到天然或直观的结构数据的方式。 
2、限制访问的数据，使得用户可以看到，（有时）修改确实需要或不需要更多。 
3、汇总可从表中的数据生成各种报告。


使用CREATE VIEW语句创建数据库视图。 视图可以从一个单一的表，多个表或另一视图中创建。 要创建视图，用户必须根据具体的实施有相应的系统权限。 
CREATE VIEW基本的语法如下：
CREATE VIEW view_name AS
SELECT column1, column2.....
FROM table_name
WHERE [condition]; 可以包括在正常使用的SQL SELECT查询类似的方式，在SELECT语句中的多个表。


WITH CHECK OPTION是CREATE VIEW语句选项。WITH CHECK OPTION的目的是为了确保所有更新和插入满足视图定义的条件。 
如果它没有满足条件，在UPDATE或INSERT返回一个错误。 
以下是创建CUSTOMERS_VIEW 视图例子使用WITH CHECK OPTION：
CREATE VIEW CUSTOMERS_VIEW AS
SELECT name, age
FROM  CUSTOMERS
WHERE age IS NOT NULL
WITH CHECK OPTION; 
在这种情况下，WITH CHECK OPTION拒绝任何NULL值条目在视图的AGE列，因为该视图由数据定义的不能在AGE栏中有NULL值。


视图在一定条件下可以更新： （包括INSERT、UPDATE、DELETE操作）
1、SELECT子句不包含关键字DISTINCT。 
2、SELECT子句不包含汇总函数。 
3、SELECT子句不包含集合函数。 
4、SELECT子句不能包含集合运算符。 
5、SELECT子句不能包含一个ORDER BY子句。 
6、FROM子句中不能包含多个表。 
7、WHERE子句不能包含子查询。 
8、查询不包含GROUP BY或HAVING。 
9、计算列无法更新。 
10、从基表中的所有NOT NULL列必须包含在视图是为了使用INSERT查询功能。
所以，如果一个视图满足所有上述规则，那么就可以更新视图。

UPDATE CUSTOMERS_VIEW
      SET AGE = 35
      WHERE name='Ramesh'; 
更改视图这最终将更新基础表CUSTOMERS，并同样在视图中反映。

删除视图： DROP VIEW view_name;


事务特性： 事务具有以下四个标准属性，通常由首字母缩写ACID简称： 
原子: 确保工作单元中的所有操作都成功完成; 否则，该事务被中止的故障点，操作回滚到操作之前的状态。 
一致性: 确保数据库正确后成功提交事务更改状态。 
隔离: 事务操作彼此独立和透明。 
持久性: 可确保提交的事务的结果或仍然存在系统故障的情况下的作用。

事务控制： 用来控制事务有如下命令： 
COMMIT: 保存更改。 
ROLLBACK: 回滚更改。 
SAVEPOINT: 回滚事务组创建点 
SET TRANSACTION: 事务放置的名称。 
事务控制指令只能用DML命令使用INSERT，UPDATE和DELETE只。它们不能在创建表或删除它们，因为这些操作都是自动提交到数据库中。

COMMIT和ROLLBACK命令范围是事务在数据库自上次COMMIT或ROLLBACK命令。


保存点SAVEPOINT是，可以回滚事务到某一事务节点，而不回滚整个事务。 SAVEPOINT命令的语法如下：SAVEPOINT savepoint_name;
回滚到SAVEPOINT的语法如下：ROLLBACK TO savepoint_name;
RELEASE SAVEPOINT命令用于删除已经创建的一个SAVEPOINT。 RELEASE SAVEPOINT的语法如下：RELEASE SAVEPOINT savepoint_name;
一旦保存点已被释放，使用ROLLBACK命令不能再撤消自SAVEPOINT进行事务。

SET TRANSACTION命令可以被用来启动一个数据库事务。该命令用于指定随后的事务特性。 例如，可以指定一个事务是只读的，或读写。 SET TRANSACTION的语法如下：SET TRANSACTION [ READ WRITE | READ ONLY ];


以下是SQL中可使用的所有重要的日期和时间相关的函数列表。RDBMS支持其他各种功能。以下给定的列表是基于MySQL的RDBMS日期函数。
名称	描述
ADDDATE()	相加日期
ADDTIME()	相加时间
CONVERT_TZ()	从一个时区转换到另一个
CURDATE()	返回当前日期
CURRENT_DATE(), CURRENT_DATE	CURDATE()同义词
CURRENT_TIME(), CURRENT_TIME	CURTIME()同义词
CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP	NOW()同义词
CURTIME()	返回当前时间
DATE_ADD()	相加两个时间
DATE_FORMAT()	格式化日期如指定格式
DATE_SUB()	相减两个日期
DATE()	提取日期或日期时间表达式的日期部分
DATEDIFF()	相减两个日期
DAY()	DAYOFMONTH()同义词
DAYNAME()	返回星期的名字
DAYOFMONTH()	返回月份的第几天 (1-31)
DAYOFWEEK()	返回参数的星期索引
DAYOFYEAR()	返回一年中的第几天 (1-366)
EXTRACT	提取的日期部分
FROM_DAYS()	天数转换为日期
FROM_UNIXTIME()	格式日期作为UNIX时间戳
HOUR()	提取小时
LAST_DAY	返回参数的对应月份中的最后一天
LOCALTIME(), LOCALTIME	NOW()同义词
LOCALTIMESTAMP, LOCALTIMESTAMP()	NOW()同义词
MAKEDATE()	创建从年度中年份和第几天的日期
MAKETIME	MAKETIME()
MICROSECOND()	从参数中返回微秒
MINUTE()	从参数返回分钟
MONTH()	返回日期的月份
MONTHNAME()	返回当前月份的名称
NOW()	返回当前的日期和时间
PERIOD_ADD()	添加一个时期到年月
PERIOD_DIFF()	返回月期间之间数
QUARTER()	从date参数返回季度
SEC_TO_TIME()	转换秒为 'HH:MM:SS' 格式
SECOND()	返回秒 (0-59)
STR_TO_DATE()	将字符串转换为日期
SUBDATE()	当三个参数时类似调用DATE_SUB()
SUBTIME()	相减时间
SYSDATE()	返回函数执行时的时间
TIME_FORMAT()	格式化为时间
TIME_TO_SEC()	返回参数转换成秒
TIME()	提取表达过去的时间部分
TIMEDIFF()	相减时间
TIMESTAMP()	带一个参数，函数返回日期或日期时间表达式。有两个参数，参数的总和
TIMESTAMPADD()	增加datetime表达式的一个间隔
TIMESTAMPDIFF()	从日期表达式减去时间间隔
TO_DAYS()	返回日期参数转换为天
UNIX_TIMESTAMP()	返回一个UNIX时间戳
UTC_DATE()	返回当前UTC日期
UTC_TIME()	返回当前UTC时间
UTC_TIMESTAMP()	返回当前UTC日期和时间
WEEK()	返回周数
WEEKDAY()	返回星期的索引
WEEKOFYEAR()	返回日期的日历周 (1-53)
YEAR()	返回年份
YEARWEEK()	返回年份和星期


SQL临时表
CREATE TEMPORARY TABLE table_name ();

RDBMS有支持临时表。 临时表是一个伟大的功能，让您存储和使用如：选择，更新过程中间结果，并加入功能，可以与典型的SQL Server表使用。 
临时表可能是非常有用的在某些情况下，以保持临时数据。这应该被称为临时表的最重要的事情是，当当前客户端会话终止，它们将被删除。 
临时表可在MySQL版本3.23以上使用。如果你使用MySQL的旧版本比3.23，则不能使用临时表，但可以使用堆表。 
如前所述，临时表将只要会话持续就活着。如果在一个PHP脚本运行的代码，当脚本执行完毕后，临时表将被自动销毁。如果您是通过MySQL客户端程序连接到MySQL数据库服务器， 那么临时表会一直存在，直到您关闭客户端或者手动销毁表。

当你发出一个SHOW TABLES命令，临时表不会被列在列表中。
如果注销MySQL的会话，然后发出SELECT命令，这时你会发现在数据库中没有可用的数据。 临时表也就不存在了。

删除临时表： DROP TABLE table_name
默认情况下，当你的数据库连接被终止，所有的临时表被MySQL删除。您如果还是想要删除的他们，那么可发出DROP TABLE命令。


SQL克隆表

1、新表不存在
create table new_table select * from old_talbe;
这种方法会将old_table中所有的内容都拷贝过来,用这种方法需要注意,new_table中没有了old_table中的primary key,Extra,auto_increment等属性,需要自己手动加

只复制表结构到新表
# 第一种方法,和上面类似,只是数据记录为空,即给一个false条件
create table new_table select * from old_table where 1=2;
# 第二种方法
create table new_table like old_table;

2、新表存在：
复制旧表数据到新表(假设两个表结构一样)
insert into new_table select * from old_table;
复制旧表数据到新表(假设两个表结构不一样)
insert into new_table(field1,field2,.....) select field1,field2,field3 from old_table;
复制全部数据
select * into new_table from old_table;
只复制表结构到新表
select * into new_talble from old_table where 1=2;

由上面的使用 CREATE TABLE 表名 AS SELECT 语句可以看出：
    1：只会复制表数据和表结构，不会有任何约束。
    2：当 where 条件不成立时，只复制表结构，没有任务数据。

如果正在使用MySQL RDBMS，你可以处理这种情况通过以下步骤：
1、使用SHOW CREATE TABLE命令来获得一个CREATE TABLE语句指定源表的结构，索引和所有其它。
SHOW CREATE TABLE table_name;
2、修改语句表名更改为克隆表并执行该语句。这样，您将有准确克隆表。
3、如果你需要复制表的内容，发出一个INSERT INTO ... SELECT语句。
INSERT INTO new_table (column)
    SELECT column
    FROM old_table;


子查询或内部查询或嵌套查询在另一个SQL查询的查询和嵌入式WHERE子句中。 
子查询用于返回将被用于在主查询作为条件的数据，以进一步限制要检索的数据。 
子查询可以在SELECT，INSERT，UPDATE使用，而且随着运算符如DELETE语句 =, <, >, >=, <=, IN, BETWEEN 等.


SQL使用序列（自动递增）
在MySQL中最简单的方法来使用顺序是定义一个列AUTOINCREMENT并保留其余的事情由MySQL处理。
CREATE TABLE INSECT
    (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id),
    name VARCHAR(30) NOT NULL, # type of insect
    date DATE NOT NULL, # date collected
    origin VARCHAR(30) NOT NULL # where collected
);


重编一个现有的序列：
有可能出现一种情况，当从表中已删除多条记录，并且希望所有的记录重新排序。这可以通过使用一个简单的技巧来完成，但这样做要非常小心，如果你的表连接使用其他表。
这样做的方式是从表中删除列，然后重新添加这个删除的列。下面的例子演示了如何使用这种技术在 insect 表重新编号ID值：
mysql> ALTER TABLE INSECT DROP id;
mysql> ALTER TABLE insect
    -> ADD id INT UNSIGNED NOT NULL AUTO_INCREMENT FIRST,
    -> ADD PRIMARY KEY (id);

启动顺序在一个特定的值： 默认情况下，MySQL将从1开始顺序，但可以在创建表时指定的任何其他数字也是可以的。以下为例子，MySQL顺序将从100开始。
mysql> CREATE TABLE INSECT
    -> (
    -> id INT UNSIGNED NOT NULL AUTO_INCREMENT = 100,
    -> PRIMARY KEY (id),
    -> name VARCHAR(30) NOT NULL, # type of insect
    -> date DATE NOT NULL, # date collected
    -> origin VARCHAR(30) NOT NULL # where collected
); 
或者，您可以创建表，然后设置初始序列值ALTER TABLE。mysql> ALTER TABLE t AUTO_INCREMENT = 100;


SQL注入
如果需要通过网页需要用户输入的数据并将其插入到一个SQL数据库，可能会留下敞开称为SQL注入安全问题。
注入通常当要求一个用户输入，就如他们的名字，但他们给你不是一个名字，会在不知不觉中对数据库运行SQL语句时发生问题。 
千万不要信任用户提供的数据，处理这些数据只是验证后;作为一项规则，通过模式匹配进行。


防止SQL注入：
如在sql拼接中注入DELETE查询删除所有记录的客户。
LIKE问题：一个自定义的转义机制必须将用户提供的'％'和'_'字符文字


SQL具有对字符串或数字数据进行处理许多内置函数。以下是所有有用的SQL内置函数列表：
SQL COUNT()函数 - SQL COUNT聚合函数是用来统计在数据库表中的行数。
SQL MAX()函数 - SQL MAX聚合函数允许我们选择某个/些列最高(最大)值。
SQL MIN()函数 - SQL MIN聚合函数允许我们选择某些列最低(最小)值。
SQL AVG()函数 - SQL AVG聚合函数选择某些表列的平均值。
SQL SUM()函数 - SQL SUM聚合函数允许选择列的总和。
SQL SQRT()函数 - 这是用来生成给定数的平方根。
SQL RAND()函数 - 这用于生成使用SQL命令的随机数。
SQL CONCAT()函数 - 这是用来连接内部SQL命令的任何字符串。
SQL Numeric()函数 - SQL操作数字需要SQL函数的完整列表。
SQL String()函数 - SQL操作字符串需要SQL函数的完整列表。


MAX()、MIN()、AVG()、SUM()一般配合可以使用GROUP BY子句中各种记录的总和

SQL SQRT函数用来找出任何数的平方根。您可以使用SELECT语句来找出任何数的平方根如下：SQL>  select SQRT(16);
SQL RAND函数被调用产生0和1之间的一个随机数：
当一个整数参数调用，RAND()使用该值作为随机数发生器的种子。每次种子生成器使用给定值，RAND()会产生重复的一系列数字：SQL>  SELECT RAND(1), RAND( ), RAND( );
可以使用ORDER BY RAND()来随机化一组行或值如下：SQL> SELECT * FROM employee_tbl ORDER BY RAND();
SQL CONCAT函数用于连接两个字符串，形成一个字符串。试试下面的例子：SQL> SELECT CONCAT('FIRST ', 'SECOND');
现在假设的基础上要连接所有的名字：员工ID和work_date在上述表中，那么你可以使用以下命令做到这一点：
SQL> SELECT CONCAT(id, name, work_date) FROM employee_tbl;



SQL数值函数主要用于数字处理和/或数学计算。下表详细列出了数值函数：
名称	描述
ABS()	返回数字表达式的绝对值
ACOS()	返回数字表达式的反余弦值，返回NULL如果值不是在-1到1范围
ASIN()	返回数字表达式的反正弦，返回NULL，如果值是不在-1到1范围
ATAN()	返回数字表达式的反正切
ATAN2()	返回传递给它的两个变量的反正切
COS()	返回传递数值表达式的余弦。数字表达式用弧度表示
COT()	返回传递数字表达式的余切值

BIT_AND()	返回位与表达中的所有位
BIT_COUNT()	返回传递给它的参数的二进制值的字符串表示
BIT_OR()	返回传递表达的所有位的位或

CONV()	将数字表达式从一个基数到另一个。
DEGREES()	返回数值表达式转换弧度度
EXP()	返回自然对数（E）升高到通过数值表达式的幂

CEIL()	返回最小的整数值但不比通过数值表达式小
CEILING()	返回最小的整数值并不比通过数值表达式小
FLOOR()	返回最大整型值但不大于数值表达式

FORMAT()	返回舍入到小数位数数字表达式
GREATEST()	返回输入表达式的最大值
INTERVAL()	需要多个表达式exp1, exp2 和 exp3等..并返回0如果exp1小于exp2 ，返回1如果exp1小于ex3等
LEAST()	给定两个或更多个时返回最小值输入值
LOG()	返回传递数值表达式的自然对数
LOG10()	返回传递数值的表达的底数10对数
MOD()	返回一个表达式的由另一种表达除以剩余部分
OCT()	返回传递数字表达式的八进制值的字符串表示，返回NULL如果传递值为NULL
PI()	返回圆周率的值pi
POW()	返回一个表达式的值提升到另一种表达的幂
POWER()	返回一个表达式的值提升到另一种表达的幂
RADIANS()	返回传递表达从度转换为弧度值
ROUND()	返回数值表达式四舍五入到整数。可用于舍表达式为数字小数点
SIN()	返回弧度给定的数字表达式的正弦值
SQRT()	返回数字表达式的非负平方根
STD()	返回数字表达式的标准偏差
STDDEV()	返回数字表达式的标准偏差
TAN()	返回以弧度表示数值表达式的正切
TRUNCATE()	返回数值exp1截断为exp2小数，如果exp2为0，则结果将没有小数点



SQL字符串函数主要用于字符串操作。下表详细列出了重要的字符串函数：
名称	描述
ASCII()	返回最左边的字符数值(ASCII码值)
BIN()	返回参数的字符串表示
BIT_LENGTH()	返回参数的长度位
CHAR_LENGTH()	返回参数中的字符数字
CHAR()	返回字符传递的每个整数
CHARACTER_LENGTH()	 CHAR_LENGTH() 的代名词
CONCAT_WS()	返回串联使用的分离器
CONCAT()	返回连接字符串
CONV()	不同数值的基数之间转换数字
ELT()	在索引号返回字符串
EXPORT_SET()	返回一个字符串，例如，对于每一个位值的位置，会得到一个对串并为每个未设置位，会得到一个字符串断开
FIELD()	返回第一个参数在随后的参数索引（位置）
FIND_IN_SET()	返回第二个参数中的第一个参数的索引位置
FORMAT()	返回一个数字格式的小数位数指定数量
HEX()	返回一个十六进制值的字符串表示
INSERT()	插入一个子在指定的位置到指定的字符数值
INSTR()	返回字符串中第一次出现的索引
LCASE()	 LOWER()代名词
LEFT()	指定返回最左边的字符数
LENGTH()	返回字符串中的字节长度
LOAD_FILE()	加载指定的文件
LOCATE()	返回字符串的第一个出现的位置
LOWER()	返回参数的小写
LPAD()	返回字符串参数，左填充为指定字符串
LTRIM()	删除前导空格
MAKE_SET()	返回一组具有以位相应的位置逗号分隔的字符串
MID()	返回从指定位置开始的子串
OCT()	返回八进制参数的字符串表示
OCTET_LENGTH()	LENGTH() 的一个代名词
ORD()	如果参数的最左边的字符是一个多字节字符，返回代码为字符
POSITION()	LOCATE()的一个代名词
QUOTE()	转义的使用参数在SQL语句
REGEXP	使用正则表达式模式匹配
REPEAT()	重复一个字符串指定的次数
REPLACE()	替换出现一个指定的字符串
REVERSE()	反转字符串中的字符
RIGHT()	返回指定的字符最右边的数值
RPAD()	附加字符串指定的次数
RTRIM()	删除尾随空格
SOUNDEX()	返回一个soundex字符串
SOUNDS LIKE	音色比较
SPACE()	返回空格指定数目的字符串
STRCMP()	比较两个字符串
SUBSTRING_INDEX()	出现的分隔符的指定数量的前返回一个字符串的子串
SUBSTRING(), SUBSTR()	指定返回字符串
TRIM()	除去开头和结尾的空格
UCASE()	UPPER() 的一个代名词
UNHEX()	每一对十六进制数字转换为字符
UPPER()	转换为大写



