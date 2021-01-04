1、查看可用的 MySQL 版本
访问 MySQL 镜像库地址：https://hub.docker.com/_/mysql?tab=tags 。

可以通过 Sort by 查看其他版本的 MySQL，默认是最新版本 mysql:latest 。

此外，我们还可以用 docker search mysql 命令来查看可用版本：

2、拉取 MySQL 镜像
这里我们拉取官方的最新版本的镜像：
$ docker pull mysql:latest

3、查看本地镜像
使用以下命令来查看是否已安装了 mysql：
$ docker images


4、 创建配置
cd ~
mkdir -p mysql/conf mysql/data mysql/logs
touch mysql/conf/my.cnf
vim mysql/conf/my.cnf


5、运行容器
安装完成后，我们可以使用以下命令来运行 mysql 容器：

docker run -itd --restart=always --name mysql8 -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -v /root/mysql/conf/my.cnf:/etc/mysql/my.cnf -v /root/mysql/logs:/logs -v /root/mysql/data/mysql:/var/lib/mysql -d mysql


docker run -p 3307:3306 --restart 策略名称 -e MYSQL_ROOT_PASSWORD=mysql密码 -d 镜像ID
docker run -p 3307:3306 --restart always -e MYSQL_ROOT_PASSWORD=rootroot -d 5709795eeffa
docker run -itd --name mysql5.7 -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -d f07dfa83b528
docker run -itd --name mysql8 -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -d a347a5928046

参数说明：
-p 3306:3306 ：映射容器服务的 3306 端口到宿主机的 3306 端口，外部主机可以直接通过 宿主机ip:3306 访问到 MySQL 的服务。
MYSQL_ROOT_PASSWORD=123456：设置 MySQL 服务 root 用户的密码。


6、安装成功
通过 docker ps 命令查看是否安装成功：

本机可以通过 root 和密码 123456 访问 MySQL 服务。
mysql -h114.55.168.254 -p3306 -uroot -p123456

进入容器
docker exec -it d3ed0f3d31af bash

连接
mysql -u root -p

创建用户
CREATE USER 'lims' IDENTIFIED BY '123456';

创建数据库
create database limsdb;

授权
grant all privileges on limsdb.* to lims@'%' identified by '123456';




grant all privileges on limsdb.* to 'lims'@'127.0.0.1';
grant all privileges on limsdb.* to lims@'%' with grant option;

mysql8.0数据库添加用户和授权
mysql8有新的安全要求，不能像之前的版本那样一次性创建用户并授权需要先创建用户，再进行授权操作

1. 创建新用户：create user ‘username’@‘host’ identified by ‘password’; 其中username为自定义的用户名；host为登录域名，host为’%'时表示为 任意IP，为localhost时表示本机，或者填写指定的IP地址；paasword为密码
2. 为用户授权：grant all privileges on . to ‘username’@’%’ with grant option; 其中*.第一个表示所有数据库，第二个表示所有数据表，如果不想授权全部那就把对应的写成相应数据库或者数据表；username为指定的用户；%为该用户登录的域名
3. 授权之后刷新权限：flush privileges;

[查看MySql版本号命令](https://blog.csdn.net/qq_38486203/article/details/80324014)
[mysql 8.0.12 创建新的数据库、用户并授权](https://blog.csdn.net/a599174211/article/details/82670896)



[root@iZbp11w09b3rwkvpgmw9ysZ ~]# docker ps
CONTAINER ID   IMAGE     COMMAND                  CREATED          STATUS          PORTS                               NAMES
feae9b171420   mysql     "docker-entrypoint.s…"   32 minutes ago   Up 32 minutes   0.0.0.0:3306->3306/tcp, 33060/tcp   mysql-test
[root@iZbp11w09b3rwkvpgmw9ysZ ~]# docker stop feae9b171420
feae9b171420
[root@iZbp11w09b3rwkvpgmw9ysZ ~]# docker ps
CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES
[root@iZbp11w09b3rwkvpgmw9ysZ ~]# 
[root@iZbp11w09b3rwkvpgmw9ysZ ~]# docker ps -qa
feae9b171420
[root@iZbp11w09b3rwkvpgmw9ysZ ~]# docker ps -a
CONTAINER ID   IMAGE     COMMAND                  CREATED          STATUS                      PORTS     NAMES
feae9b171420   mysql     "docker-entrypoint.s…"   33 minutes ago   Exited (0) 20 seconds ago             mysql-test
[root@iZbp11w09b3rwkvpgmw9ysZ ~]# docker rm feae9b171420
feae9b171420
[root@iZbp11w09b3rwkvpgmw9ysZ ~]# docker ps -a
CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES
[root@iZbp11w09b3rwkvpgmw9ysZ ~]# docker images
REPOSITORY   TAG       IMAGE ID       CREATED      SIZE
mysql        5.7.32    f07dfa83b528   6 days ago   448MB
mysql        latest    a347a5928046   6 days ago   545MB
[root@iZbp11w09b3rwkvpgmw9ysZ ~]# docker run -itd --name mysql5.7 -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -d f07dfa83b528
d3ed0f3d31af9961e76926f8869fdf037727f490177e651ea8d14c6e982ea184
[root@iZbp11w09b3rwkvpgmw9ysZ ~]# docker ps
CONTAINER ID   IMAGE          COMMAND                  CREATED         STATUS         PORTS                               NAMES
d3ed0f3d31af   f07dfa83b528   "docker-entrypoint.s…"   5 seconds ago   Up 3 seconds   0.0.0.0:3306->3306/tcp, 33060/tcp   mysql5.7
[root@iZbp11w09b3rwkvpgmw9ysZ ~]# docker exec -it d3ed0f3d31af bash
root@d3ed0f3d31af:/# mysql -u root -p
Enter password: 
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 2
Server version: 5.7.32 MySQL Community Server (GPL)

Copyright (c) 2000, 2020, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> CREATE USER 'lims' IDENTIFIED BY '123456';
Query OK, 0 rows affected (0.00 sec)

mysql> create database limsdb;
Query OK, 1 row affected (0.00 sec)

mysql> grant all privileges on limsdb.* to lims@'%' identified by '123456';
Query OK, 0 rows affected, 1 warning (0.00 sec)



参考  
[Installation of Docker fails on CentOS 8 with Error — package containerd.io](https://medium.com/@anuketjain007/installation-of-docker-fails-on-centos-8-with-error-package-containerd-io-f7a338b34a71)  
[使用Docker安装、运行mysql](https://www.jianshu.com/p/d9b6bbc7fd77)  
[docker安装Mysql8.0并挂载外部配置和数据](https://www.cnblogs.com/roinbi/p/12032952.html)  
[Docker部署安装MySQL5.7](https://www.jianshu.com/p/5c18a4b01dcc)  



