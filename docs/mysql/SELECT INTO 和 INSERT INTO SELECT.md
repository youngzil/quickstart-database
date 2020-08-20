SELECT INTO 和 INSERT INTO SELECT



select into from 和 insert into select 都是用来复制表

两者的主要区别为： 
select into from 要求目标表不存在，因为在插入时会自动创建；将查询出来的数据整理到一张新表中保存，表结构与查询结构一致。
insert into select from 要求目标表存在。为已经存在的表批量添加新数据。




1、 INSERT INTO SELECT 语句
INSERT INTO SELECT 语句从一个表复制数据，然后把数据插入到一个已存在的表中。目标表中任何已存在的行都不会受影响。

SQL INSERT INTO SELECT 语法

我们可以从一个表中复制所有的列插入到另一个已存在的表中：
INSERT INTO table2
SELECT * FROM table1;

或者我们可以只复制希望的列插入到另一个已存在的表中：
INSERT INTO table2
(column_name(s))
SELECT column_name(s)
FROM table1;<br><br>Insert into Table2(field1,field2,...) select value1,value2,... from Table1




2、SELECT INTO 语句

要求目标表Table2不存在，因为在插入时会自动创建表Table2，并将Table1中指定字段数据复制到Table2中。示例如下：

SELECT INTO 语句从一个表中选取数据，然后把数据插入另一个表中。
SELECT INTO 语句常用于创建表的备份复件或者用于对记录进行存档。

SQL SELECT INTO 语法

您可以把所有的列插入新表：
SELECT *
INTO new_table_name [IN externaldatabase]
FROM old_tablename

或者只把希望的列插入新表：
SELECT column_name(s)
INTO new_table_name [IN externaldatabase]
FROM old_tablename



1. 复制表结构及其数据：
create table table_name_new as select * from table_name_old

2. 只复制表结构：
create table table_name_new as select * from table_name_old where 1=2;

或者：
create table table_name_new like table_name_old

3. 只复制表数据：
如果两个表结构一样：
insert into table_name_new select * from table_name_old

如果两个表结构不一样：
insert into table_name_new(column1,column2...) select column1,column2... from table_name_old




稍微整理一下 select into from 和 insert into select 的理解层面的区别

select into from ：将查询出来的数据整理到一张新表中保存，表结构与查询结构一致。
select *（查询出来的结果） into newtable（新的表名）form where （后续条件）
即，查询出来结果--->复制一张同结构的空表--->将数据拷贝进去。
insert into select ：为已经存在的表批量添加新数据。

insert into  (准备好的表) select *（或者取用自己想要的结构）frome 表名 where 各种条件
即，指定一张想要插入数据的表格--->对数据进行加工筛选--->填入一张准备好的表格。





参考
https://www.cnblogs.com/liliyi/p/6068668.html
https://www.runoob.com/sql/sql-insert-into-select.html
