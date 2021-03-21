
mysqldump -u root -h localhost -p limsdb > /root/mysql/backdb2021020722444.sql

mysqldump -u root -h localhost --databases limsdb > /root/mysql/limsdbbak.sql



## mysqldump免密备份方法

1. 我用的root用户，先进入家目录
   cd ~

2. vim .my.cnf #在家目录添加该文件
```
[mysqldump]
user=root
password=XXXXXX
```

chmod 600 .my.cnf #为了提高安全性，修改文件权限

3. mysqldump备份

mysqldump -u root  --databases db > db.sql #备份语句中一定不能使用-p选项，否则还是需要输入密码的；当服务器有多个实例时需要指定套接字-S /path/to/mysql.sock





## mysqldump备份命令

备份整个数据库
```
$> mysqldump -u root -h host -p dbname > backdb.sql
```

备份数据库中的某个表
```
$> mysqldump -u root -h host -p dbname tbname1, tbname2 > backdb.sql
```

备份多个数据库
```
$> mysqldump -u root -h host -p --databases dbname1, dbname2 > backdb.sql
```

备份系统中所有数据库
```
$> mysqldump -u root -h host -p --all-databases > backdb.sql
```




参考  
[MySql数据库备份的几种方式](https://www.cnblogs.com/yourblog/p/10381962.html)  
[mysqldump免密备份方法](https://www.cnblogs.com/godfather007/p/10373940.html)  
[mysqldump免密码导出方法](https://blog.csdn.net/codecocktail/article/details/81909128)  

