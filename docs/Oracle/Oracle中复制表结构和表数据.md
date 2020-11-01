1. 复制表结构及其数据：

create table table_name_new as select * from table_name_old



2. 只复制表结构：

create table table_name_new as select * from table_name_old where 1=2;

或者：

create table table_name_new like table_name_old



3. 只复制表数据：

如果两个表结构一样：

insert into table_name_newselect * fromtable_name_old

如果两个表结构不一样：

insert into table_name_new(column1,column2...)selectcolumn1,column2... fromtable_name_old


--------------------- 
作者：yzj_000 
来源：CSDN 
原文：https://blog.csdn.net/yzj_000/article/details/2378432 
版权声明：本文为博主原创文章，转载请附上博文链接！