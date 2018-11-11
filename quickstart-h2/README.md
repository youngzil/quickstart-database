http://h2database.com/html/main.html
https://github.com/h2database/h2database

http://www.h2database.com/html/features.html


与SpringBoot整合查看
quickstart-spring-boot-druid


关于H2 数据库的连接方式
连接H2数据库有以下方式
服务式 （Server）
嵌入式（Embedded）
内存（Memory）

还没太仔细研究，到目前的理解：
嵌入式的话，就是这个H2数据库只能给一个应用使用，连接是有排他机制的。当一个应用用嵌入式连接方式连接了以后，其他的应用就不能再连接了。
服务式的话，就跟MySQL、Oracle这种数据库差不多，服务器单独运行，可以多个客户端同时连接。
内存方式，顾名思义，数据仅保持在内存中

嵌入式和服务式主要体现在JDBC连接的URL方式不同，上面例子中给的是服务式的。
服务式 （Server）
jdbc:h2:tcp://localhost/~/test
嵌入式（Embedded）
jdbc:h2:~/test
内存式（Memory）
jdbc:h2:tcp://localhost/mem:test



H2是一个用Java开发的嵌入式数据库，它本身只是一个类库，可以直接嵌入到应用项目中。
　　Ｈ2最大的用途在于可以同应用程序打包在一起发布，这样可以非常方便地存储少量结构化数据。
　　它的另一个用途是用于单元测试。启动速度快，而且可以关闭持久化功能，每一个用例执行完随即还原到初始状态。
　　Ｈ2的第三个用处是作为缓存，作为NoSQL的一个补充。当某些场景下数据模型必须为关系型，可以拿它当Memcached使，作为后端MySQL/Oracle的一个缓冲层，缓存一些不经常变化但需要频繁访问的数据，比如字典表、权限表。不过这样系统架构就会比较复杂了。

一、产品优势　　
纯Java编写，不受平台的限制；
只有一个jar文件，适合作为嵌入式数据库使用；
h2提供了一个十分方便的web控制台用于操作和管理数据库内容；
功能完整，支持标准SQL和JDBC。麻雀虽小五脏俱全；
支持内嵌模式、服务器模式和集群。

解压缩后的目录结构：
　　h2
　　|---bin
　　|    |---h2-1.1.116.jar 　　//H2数据库的jar包（驱动也在里面）
　　|    |---h2.bat         　　   //Windows控制台启动脚本
　　|    |---h2.sh                  //Linux控制台启动脚本
　　|    |---h2w.bat              //Windows控制台启动脚本（不带黑屏窗口）
　　|---docs                       //H2数据库的帮助文档（内有H2数据库的使用手册）
　　|---service //通过wrapper包装成服务。
　　|---src //H2数据库的源代码
　　|---build.bat //windows构建脚本
　　|---build.sh //linux构建脚本

三、运行模式与运行方式
（一）运行模式
　　Ｈ2有三种运行模式。
　　１、内嵌模式（Embedded Mode）
　　内嵌模式下，应用和数据库同在一个JVM中，通过JDBC进行连接。 可持久化，但同时只能一个客户端连接。内嵌模式性能会比较好。

　　２、服务器模式（Server Mode）
　　使用服务器模式和内嵌模式一样，只不过它可以跑在另一个进程里。

　　３、 混合模式
　　第一个应用以内嵌模式启动它，对于后面的应用来说它是服务器模式跑着的。混合模式是内嵌模式和服务器模式的组合。第一个应用通过内嵌模式与数据库建立连接，同时也作为一个服务器启动，于是另外的应用（运行在不同的进程或是虚拟机上）可以同时访问同样的数据。第一个应用的本地连接与嵌入式模式的连接性能一样的快，而其它连接理论上会略慢。 


运行：
1、复制h2.sh为h2_server.sh文件；
2、编辑h2_server.sh，如下：#!/bin/shdir=$(dirname"$0")java -cp "$dir/h2-1.3.176.jar:$H2DRIVERS:$CLASSPATH" org.h2.tools.Server -tcpAllowOthers -webAllowOthers -webPort 8082"$@"
说明： 
　　org.h2.tools.Server： 以服务器模式启动 
　　-tcpAllowOthers： 允许远程机器通过TCP方式访问 
　　-webAllowOthers： 允许远程机器通过浏览器访问 
　　-webPort 8082： 默认的访问端口（8082为未被占用的端口，如果此端口已经被其他端口占用，则改为其他端口）
3、具体的运行方式
　　①chmod修改文件权限；
　　②输入nohup ./h2_server.sh &回车。这样可以后台运行；


四、远程管理
通过远程浏览器来访问h2 
　　访问地址：http://服务器ip:8082/ ，出现如下页面：
２.路径的配置
　　jdbc:h2:tcp://localhost//usr/h2/data/rlib 是H2的路径。tcp代表使用tcp方式访问。localhost/是IP。因为例子是在Linux下，数据库的文件路径是“/usr/h2/data/rlib”，所以localhost/后面还有个/。详细的URL设置可见本文附录。（聪明如你，可能会问为什么既然H2远程部署在Linux下但使用浏览器访问IP还填写localhost？就不告诉你）
3.点击如图Test connect按钮测试连接。此时如果数据库文件本身不存在则会自动创建。数据库文件自动生成到/usr/h2/data/目录下，名为rlib.mv.db。点击Connect，进入管理终端。

五、数据备份恢复
　　不能备份和导入数据的数据库是跛脚的。可通过自带的CSVWRITE方法导出为csv格式文件，也可通过CSVREAD 导入数据。
（一）导出函数CSVWRITE
例子：CALL CSVWRITE('test2.csv', 'SELECT * FROM TEST', 'charset=UTF-8 fieldSeparator=|');
　　注意：导出时就算加上编码，导出后的文件用VIM、Editplus查看是正常，但用Excel查看也仍然会是乱码的。需要用Editplus另存为Unicode，就好了。
　　（二）导入函数CSVWRITE
　　语法：CSVREAD(fileNameString [, columnsString [, csvOptions ] ] )
　　例子①：导入数据：INSERT INTO TEST ( SELECT * FROM CSVREAD('d:/test.csv ')) ;
　　例子②：导入结构及数据，根据csv文件创建h2数据表。
　　//csv文件数据创建test表
　　CREATE TABLE TEST AS SELECT * FROM CS VREAD('d:/test.csv ');
　　//创建test表，csv文件相应的列插入到test表相应的字段
　　CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(255)) AS SELECT * FROM CSVREAD('d:/test.csv ');
　　（三）csvOptions




参考
https://blog.csdn.net/fanpeizhong/article/details/73543260
https://waylau.gitbooks.io/h2-database-doc/Quickstart/index.html
https://www.jianshu.com/p/b3671e148366



