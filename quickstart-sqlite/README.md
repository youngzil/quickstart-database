https://www.sqlite.org/index.html
https://github.com/xerial/sqlite-jdbc

与SpringBoot整合
quickstart-spring-boot-sqlite


一些有用的 SQLite 命令

显示表结构：
sqlite> .schema [table]

获取所有表和视图：
sqlite > .tables 

获取指定表的索引列表：
sqlite > .indices [table ]

导出数据库到 SQL 文件：
sqlite > .output [filename ] 
sqlite > .dump 
sqlite > .output stdout

从 SQL 文件导入数据库：
sqlite > .read [filename ]

格式化输出数据到 CSV 格式：
sqlite >.output [filename.csv ] 
sqlite >.separator , 
sqlite > select * from test; 
sqlite >.output stdout

从 CSV 文件导入数据到表中：
sqlite >create table newtable ( id integer primary key, value text ); 
sqlite >.import [filename.csv ] newtable 

备份数据库：
/* usage: sqlite3 [database] .dump > [filename] */
sqlite3 mytable.db .dump > backup.sql

恢复数据库：
/* usage: sqlite3 [database ] < [filename ] */ 
sqlite3 mytable.db < backup.sql



参考
http://www.runoob.com/sqlite/sqlite-java.html
https://www.jianshu.com/p/424a8b143bbb

