

https://blog.csdn.net/kai_wei_zhang/article/details/7749568
https://blog.csdn.net/ai_xao/article/details/78475833

一、 Derby 数据库介绍
Apache Derby 是100% Java 编写的内存数据库，属于 Apache 的一个开源项目。并且是一个容易管理的关系数据库管理系统，可以和一些商业产品的特性进行交付。
Apache Derby 是一个与平台无关的数据库引擎，它以 Java 类库的形式对外提供服务。与其他难以部署的数据库不同， Derby 数据库体积小、安装非常简单，只需要将其 *.jar 文件复制到系统中并为用户的项目添加该 *.jar 文件即可。


二、 Derby 数据库的两种运行模式
1） 内嵌模式。Derby数据库与应用程序共享同一个JVM，通常由应用程序负责启动和停止，对除启动它的应用程
序外的其它应用程序不可见，即其它应用程序不可访问它；
2） 网络模式。Derby数据库独占一个JVM，做为服务器上的一个独立进程运行。在这种模式下，允许有多个应用
程序来访问同一个Derby数据库。



--内嵌模式
create=true表示如果数据库不存在，则创建该数据库
connect 'jdbc:derby:~/derby/mydb;user=db_user1;password=111111;create=true'; 

--服务器模式
connect 'jdbc:derby://127.0.0.1:1527/~/derby/mydb;user=db_user1;password=111111;create=true';






