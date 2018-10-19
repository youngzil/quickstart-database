DML、DDL、DCL的区别：
DDL（data definition language）数据库定义语言：其实就是我们在创建表的时候用到的一些sql，比如说：CREATE、ALTER、DROP等。DDL主要是用在定义或改变表的结构，数据类型，表之间的链接和约束等初始化工作上
DCL（Data Control Language）数据库控制语言：是用来设置或更改数据库用户或角色权限的语句，包括（grant,deny,revoke等）语句。这个比较少用到。
DML（data manipulation language）数据操纵语言:就是我们最经常用到的 SELECT、UPDATE、INSERT、DELETE。 主要用来对数据库的数据进行一些操作。



1. 创建数据库CREATE DATABASE语句用于创建新的数据库。 
语法是 -SQL> CREATE DATABASE DATABASE_NAME;

2. 删除数据库DROP DATABASE语句用于删除存在的数据库。 语法是 -
注意：要创建或删除数据库，需要有数据库服务器的管理员权限。 需要特别小心的是：删除数据库将丢失数据库中存储的所有数据(无法恢复)。

3. 创建表CREATE TABLE语句用于创建新表。 
SQL示例以下SQL语句创建一个名为Employees的表，其中包含四列：SQL> CREATE TABLE Employees
(
   id INT NOT NULL,
   age INT NOT NULL,
   first VARCHAR(255),
   last VARCHAR(255),
   PRIMARY KEY ( id )
);

4. 删除表DROP TABLE语句用于删除存在的表。 
语法是 -SQL> DROP TABLE table_name;


5. INSERT数据INSERT的语法如下所示，
SQL示例以下SQL INSERT语句在先前创建的Employees表中插入一个新行 
-SQL> INSERT INTO Employees VALUES (100, 18, 'Max', 'Su');

6. 查询数据SELECT语句用于从数据库检索数据。 
SQLWHERE子句可以使用比较运算符，例如：=，!=，<，>，<=和>=，以及BETWEEN和LIKE运算符。示例以下SQL语句从Employees表中选择：age，first和last列，其中id列为100 
-SQL> SELECT first, last, age 
     FROM Employees 
     WHERE id = 100;
SQL以下SQL语句从Employees表中选择：age, first 和 last 列，其中first列包含Max -SQL> SELECT first, last, age 
     FROM Employees 
     WHERE first LIKE '%Max%';

7. 更新数据UPDATE语句用于更新数据。 UPDATE的语法是 -SQL> UPDATE table_name
     SET column_name = value, column_name = value, ...
     WHERE conditions;
SQLWHERE子句可以使用比较运算符，例如：=，!=，<，>，<=和>=，以及BETWEEN和LIKE运算符。示例以下SQL UPDATE语句更新id为100的雇员的age列的值为：20，SQL> UPDATE Employees SET age=20 WHERE id=100;

8. 删除数据DELETE语句用于从表中删除数据。 DELETE的语法是 -SQL> DELETE FROM table_name WHERE conditions;
SQLWHERE子句可以使用比较运算符，例如：=，!=，<，>，<=和>=，以及BETWEEN和LIKE运算符。示例以下SQL DELETE语句将删除ID为100的员工的记录 -SQL> DELETE FROM Employees WHERE id=100;






