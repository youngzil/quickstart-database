MySQL编码问题


查看 MySQL 程序编码设置
修改编码设置的方式有三种
已创建的数据库的编码
防御性编码
MySQL 中 utf8 和 utf8mb4 的区别





---------------------------------------------------------------------------------------------------------------------

MySQL 中 utf8 和 utf8mb4 的区别

MySQL在5.5.3之后增加了这个utf8mb4的编码，mb4就是most bytes 4的意思，专门用来兼容四字节的unicode。
好在utf8mb4是utf8的超集，除了将编码改为utf8mb4外不需要做其他转换。当然，为了节省空间，一般情况下使用utf8也就够了。

低版本的MySQL支持的utf8编码，最大字符长度为 3 字节，如果遇到 4 字节的字符就会出现错误了。
三个字节的 UTF-8 最大能编码的 Unicode 字符是 0xFFFF，也就是 Unicode 中的基本多文平面（BMP）。
也就是说，任何不在基本多文平面的 Unicode字符，都无法使用MySQL原有的 utf8 字符集存储。
这些不在BMP中的字符包括哪些呢？最常见的就是Emoji 表情（Emoji 是一种特殊的 Unicode 编码，常见于 ios 和 android 手机上），和一些不常用的汉字，以及任何新增的 Unicode 字符等等。


查看
永远不要在MySQL中使用utf8，改用utf8mb4.md


参考
https://my.oschina.net/xsh1208/blog/1052781
https://blog.csdn.net/w05980598/article/details/79080381



---------------------------------------------------------------------------------------------------------------------

查看 MySQL 程序编码设置
mysql> show variables like 'char%';

修改编码设置
修改编码设置的方式有三种。

方式1: session 范围修改
mysql> set character_set_server=utf8mb4

方式2: global 范围修改
mysql> set global character_set_server=utf8mb4

方式3: 修改配置文件
➜  ~ mysql --verbose --help | grep my.cnf
/etc/my.cnf /etc/mysql/my.cnf /usr/local/mysql/etc/my.cnf ~/.my.cnf

在 my.cnf文件中添加以下内容：
[mysqld]
character_set_server=utf8mb4
collation_server=utf8mb4_unicode_ci 
[client]
default_character-set=utf8mb4

修改配置文件后重启 MySQL，再查看下编码设置，
mysql> show variables like 'char%';

已创建的数据库的编码

查看完整的数据库创建语句：
mysql> show create database school;

可以看到数据库 school 的默认编码仍然是 latin1，改起：
mysql> alter database school character set 'utf8mb4';

 表的编码
在前一节，数据库 school 的默认编码已经修改为 utf8mb4，接下来看数据表的默认编码：
mysql> show create table student;

看到数据表 student 的默认编码仍然是 latin1，再改起：
mysql> alter table student character set 'utf8mb4';

列的编码
在上一节可以看到，数据表 student 的默认编码已经修改为 utf8mb4，但是列 name 的编码还是 latin1，改起！
mysql> alter table `student` change `name` `name` text character set 'utf8mb4';

防御性编码
具体就是在创建数据表的时候指定默认编码：
mysql> show variables like 'char%';
create table course(name varchar(32)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
或者直接在创建数据库的时候就指定编码：
mysql> create database school DEFAULT CHARACTER SET utf8mb4;




mysql> create database school;
mysql> use school;
mysql> create table student(name varchar(10));
mysql> insert into student values("Clarke");
mysql> select * from student;
+--------+
| name   |
+--------+
| Clarke |
+--------+
mysql> insert into student values("唐三");

# 插入中文失败
ERROR 1366 (HY000): Incorrect string value: '\xE5\x94\x90\xE4\xB8\x89' for column 'name' at row 1



查看 MySQL 程序编码设置
mysql> show variables like 'char%';

关于这些设置的含义：

option	desc
character_set_client	客户端使用的字符编码，如果客户端连接时没有设置，或者服务端已配置为忽略客户端的设置
character_set_connection	客户端设置连接数据库时的字符编码，如果客户端没有指明，则连接数据库使用该设置的编码
character_set_database	当前选中数据库的默认字符编码，如果没有选中数据库(use )，则和 character_set_server 的值一致
character_set_filesystem	文件系统的编码格式，把操作系统上的文件名转化成此字符集，即把 character_set_client转换character_set_filesystem， 默认binary是不做任何转换的
character_set_results	数据库给客户端返回时使用的编码格式，如果客户端连接时没有指明，则使用该编码
character_set_server	数据库服务器默认编码格式，创建数据库时默认使用
character_set_system	数据库系统使用的编码格式，这个值一直是utf8，不需要设置，它是为存储系统元数据的编码格式
character_sets_dir	这个变量是字符集安装的目录



修改编码设置
编码设置中我们需要关注的是下面 5 个字符编码设置：

# 服务端相关
character_set_server
character_set_database #当前选中数据库的编码，这个设置不需要手动修改​

# 客户端相关
character_set_client
character_set_connection
character_set_results



修改编码设置的方式有三种。

方式1: session 范围修改
mysql> set character_set_server=utf8mb4

方式2: global 范围修改
mysql> set global character_set_server=utf8mb4

方式3: 修改配置文件
想要编码设置在 MySQL 服务端重启后依然生效，可以修改配置文件。
不同平台的配置文件位置不一样，可以通过下面命令查看：
➜  ~ mysql --verbose --help | grep my.cnf
/etc/my.cnf /etc/mysql/my.cnf /usr/local/mysql/etc/my.cnf ~/.my.cnf
除了~/.my.cnf文件是用户级别的外，其他几个位置都是系统级别的，如果该位置没有my.cnf文件，就新建一个文本文件，命名为 my.cnf。
在 my.cnf文件中添加以下内容：
[mysqld]
character_set_server=utf8mb4
collation_server=utf8mb4_unicode_ci 
[client]
default_character-set=utf8mb4



已创建的数据库的编码
如果上面的配置已经修改完成，可能仍然有中文编码问题，因为对于已经创建完成的数据库和表，它的编码在创建时已经确定了，前面的配置项（character_set_server）已经不能影响了，需要逐个修改相应的数据库，表，列。

mysql> alter database school character set 'utf8mb4';
mysql> alter table student character set 'utf8mb4';
mysql> alter table `student` change `name` `name` text character set 'utf8mb4';



防御性编码
# 指定存储引擎，编码，排序规则
mysql> create table course(name varchar(32)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
或者直接在创建数据库的时候就指定编码：
mysql> create database school DEFAULT CHARACTER SET utf8mb4;



解决 MySQL 中文编码问题的步骤：
    查看数据库编码设置
    修改编码设置，在终端中修改设置项，或者修改配置文件 my.cnf 以永久生效
    对于之前创建的数据库，修改数据库，数据表，数据列的默认编码
    最佳实践：防御性编码，在数据库创建语句中指定默认编码。



参考
https://blog.csdn.net/clarketang/article/details/105839911


---------------------------------------------------------------------------------------------------------------------









---------------------------------------------------------------------------------------------------------------------





