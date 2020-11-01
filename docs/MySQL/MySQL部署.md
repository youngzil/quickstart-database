https://www.mysql.com/
https://github.com/mysql/mysql-server



1、下载
https://dev.mysql.com/downloads/mysql/


2、解压文件
# 解压文件
tar -zxvf mysql-5.7.27-linux-glibc2.12-x86_64.tar.gz
重命名文件夹为mysql


下面的是不需要的
# 移动 MySQL 目录到 /usr/local 目录下
sudo mv mysql-5.7.27-linux-glibc2.12-x86_64 /usr/local
# 创建软链接 mysql 指向 mysql-5.7.27-linux-glibc2.12-x86_64
sudo ln -s /usr/local/mysql-5.7.27-linux-glibc2.12-x86_64/ /usr/local/mysql


3、设置配置文件和创建data目录

设置配置文件：查看my.cnf
vim /etc/my.cnf

创建 data 文件夹
cd /home/mysql/mysql
mkdir ./data

修改当前目录拥有者为 mysql 用户：如果使用的是mysql用户创建的，不需要
chown -R ospmysql:mysql ./

初始化 mysqld：
 ./bin/mysqld --initialize --user=ospmysql --basedir=/home/mysql/mysql/ --datadir=/home/mysql/mysql/data/
 
 最后一行是root初始化密码，初次登录需要设置密码才能进行后续的数据库操作
 2019-09-19T03:30:24.359104Z 1 [Note] A temporary password is generated for root@localhost: g,PJ6yxwk3ho
 
 
4、启动MySQL服务

启动mysql服务： ./support-files/mysql.server start

还可以根据需要 配置 mysql 设置开机启动 


5、登录并修改密码

mysql -uroot -pg,PJ6yxwk3ho

修改密码为root
SET PASSWORD = PASSWORD('root');


6、设置允许客户端登录 mysql

设置允许远程连接数据库，命令如下：

先选择数据库：
usemysql
updateusersetuser.Host='%'whereuser.User='root';
update user set authentication_string=PASSWORD('password') where User='root';

强制刷新数据库
flushprivileges;

可能还需要防火墙端口设置，便于远程访问（防火墙关闭就不用设置了）

开启防火墙：systemctl start firewalld
[root@rabbitmq2~]$firewall-cmd --zone=public --add-port=3306/tcp --permanent
[root@rabbitmq2~]$firewall-cmd --reload

开启防火墙mysql3306端口的外部访问
CentOS升级到7之后，使用firewalld代替了原来的iptables。下面记录如何使用firewalld开放Linux端口
--zone : 作用域，网络区域定义了网络连接的可信等级。
　　　　这是一个一对多的关系，这意味着一次连接可以仅仅是一个区域的一部分，而一个区域可以用于很多连接
--add-port : 添加端口与通信协议，格式为：端口/通讯协议，协议是tcp 或 udp
--permanent : 永久生效，没有此参数系统重启后端口访问失效


7、创建数据库

SHOW DATABASES [LIKE '数据库名'];
CREATE DATABASE aifgwtest;


参考
https://blog.csdn.net/qq_35275233/article/details/89852817
https://blog.csdn.net/binghuozhi/article/details/95390588
https://www.jianshu.com/p/1af54641d0be


