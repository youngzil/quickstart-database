
主节点，备节点，仲裁节点

主备节点存储数据，仲裁节点不存储数据
客户端同时连接主节点与备节点，不连接仲裁节点。

默认设置下，主节点提供所有增删查改服务，备节点不提供任何服务。
但是可以通过设置使备节点提供查询服务，这样就可以减少主节点的压力，当客户端进行数据查询时，请求自动转到备节点上。这个设置叫做Read Preference Modes，同时Java客户端提供了简单的配置方式，可以不必直接对数据库进行操作。

仲裁节点是一种特殊的节点，它本身并不存储数据，主要的作用是决定哪一个备节点在主节点挂掉之后提升为主节点，所以客户端不需要连接此节点。这里虽然只有一个备节点，但是仍然需要一个仲裁节点来提升备节点级别。我开始也不相信必须要有仲裁节点，但是自己也试过没仲裁节点的话，主节点挂了备节点还是备节点，所以咱们还是需要它的。



MongoDB常见集群的搭建：三种，也可以组合如分片+副本集
1、主从复制Master-Slaver：Master + Slaver
2、副本集Replica Set：主节点 副节点 仲裁节点
3、分片Sharding：route路由节点、config配置节点、分片节点
4、分片+副本集：route路由节点、config配置节点、分片节点采用ReplicaSet方式部署（主节点 副节点 仲裁节点）



测试点：
一个是往主节点插入数据，能从备节点查到之前插入的数据（查询备节点可能会遇到某个问题，可以自己去网上查查看）。
二是停掉主节点，备节点能变成主节点提供服务。
三是恢复主节点，备节点也能恢复其备的角色，而不是继续充当主的角色。


---------------------------------------------------------------------------------------------------------------------
分片+副本集部署方式

MongoDB3.6集群搭建（分片+副本集）
https://blog.csdn.net/weixin_42190794/article/details/82586527

MongoDB3.0常见集群的搭建(主从复制，副本集，分片)
https://blog.csdn.net/canot/article/details/50739359


1、 环境准备：
3台主机，节点角色分配，
端口分配：mongos:23000 config:24000 shard1:25001 shard2:25002 shard3:25003
创建文件夹、关闭防火墙

2、 配置服务器搭建副本集：3台配置文件，3台启动节点，初始化副本集，

3、 三台分片服务器搭建副本集：3台配置文件，3台启动节点，初始化副本集，再进行另外2份部署

4、 配置路由服务器：3台配置文件，3台启动节点，
   串联路由服务器与分片副本集
   指定分片生效、指定数据库里需要分片的集合和片键（参考mongodb学习.md中的shell命令）





1、 环境准备
系统系统 centos7.0 
三台服务器：192.168.221.130/131/132 
安装包： mongodb-linux-x86_64-3.6.3.tgz

服务器130	服务器131	服务器132
mongos	mongos	mongos
config server	config server	config server
shard server1 主节点	shard server1副节点	shard server1 仲裁
shard server2 仲裁	shard server2主节点	shard server2 副节点
shard server3 副节点	shard server3 仲裁	shard server3 主节点

端口分配：mongos:23000 config:24000 shard1:25001 shard2:25002 shard3:25003

分别在每台机器建立conf、mongos、config、shard1、shard2、shard3六个目录，因为mongos不存储数据，只需要建立日志文件目录即可。
mkdir -p /usr/local/mongodb/conf
mkdir -p /usr/local/mongodb/mongos/log
mkdir -p /usr/local/mongodb/config/data
mkdir -p /usr/local/mongodb/config/log
mkdir -p /usr/local/mongodb/shard1/data
mkdir -p /usr/local/mongodb/shard1/log
mkdir -p /usr/local/mongodb/shard2/data
mkdir -p /usr/local/mongodb/shard2/log
mkdir -p /usr/local/mongodb/shard3/data
mkdir -p /usr/local/mongodb/shard3/log

关闭三台机器的防火墙
systemctl stop firewalld.service



2、 配置服务器搭建副本集
 1、设置配置文件：文件夹3.6下config.conf
 2、分别启动三台服务器的config server，连接：进入/usr/local/mongodb/bin目录下
   ./mongod -f /usr/local/mongodb/conf/config.conf
 3、登录任意一台配置服务器，初始化配置副本集，登录：进入/usr/local/mongodb/bin目录下
   ./mongo --port 24000
    使用admin数据库
    use admin
    config变量：
    config = {
    ...    _id : "configs",
    ...     members : [
    ...         {_id : 0, host : "192.168.221.130:24000" },
    ...         {_id : 1, host : "192.168.221.131:24000" },
    ...         {_id : 2, host : "192.168.221.132:24000" }
    ...     ]
    ... }
    初始化副本集：
    rs.initiate(config)
    这一步非常重要，必须初始化成功。不成功的话，路由服务器与配置服务器连接不上。 
    其中，”_id” : “configs”应与配置文件中配置的 replicaction.replSetName 一致，”members” 中的 “host” 为三个节点的 ip 和 port。


3、 三台分片服务器搭建副本集
  1、设置配置文件：文件夹3.6下shard1.conf、shard2.conf、shard3.conf
  2、启动三台服务器的shard1 server，进入/usr/local/mongodb/bin目录下：
    ./mongod -f /usr/local/mongodb/conf/shard1.conf
  3、登陆任意一台服务器，初始化副本集，进入/usr/local/mongodb/bin目录下：
    ./mongo --port 25001
    使用admin数据库
    use admin
    定义副本集配置，第三个节点的 “arbiterOnly”:true 代表其为仲裁节点。
    config = {
    ...    _id : "shard1",
    ...     members : [
    ...         {_id : 0, host : "192.168.221.130:25001" },
    ...         {_id : 1, host : "192.168.221.131:25001" },
    ...         {_id : 2, host : "192.168.221.132:25001” , arbiterOnly: true }
    ...     ]
    ... }
    初始化副本集配置
    rs.initiate(config);
  4、2.设置第二个分片 ：配置文件，启动，注册
    登陆任意一台服务器，进入/usr/local/mongodb/bin初始化副本集
    ./mongo --port 25002
    使用admin数据库
    use admin
    定义副本集配置
    config = {
    ...    _id : "shard2",
    ...     members : [
    ...         {_id : 0, host : "192.168.221.130:25002"  , arbiterOnly: true },
    ...         {_id : 1, host : "192.168.221.131:25002" },
    ...         {_id : 2, host : "192.168.221.132:25002" }
    ...     ]
    ... }
    初始化副本集配置
    rs.initiate(config);
  5、3.设置第三个分片 
    登陆任意一台服务器，初始化副本集
    ./mongo --port 25003
    使用admin数据库
    use admin
    定义副本集配置
    config = {
    ...    _id : "shard3",
    ...     members : [
    ...         {_id : 0, host : "192.168.221.130:25003" },
    ...         {_id : 1, host : "192.168.221.131:25003" , arbiterOnly: true},
    ...         {_id : 2, host : "192.168.221.132:25003" }
    ...     ]
    ... }
    初始化副本集配置
    rs.initiate(config);



4、 配置路由服务器
  1、设置配置文件：文件夹3.6下ongos.conf
  2、启动三台服务器的mongos server，进入/usr/local/mongodb/bin目录下：
    ./mongos -f /usr/local/mongodb/conf/mongos.conf



5、分片
 目前搭建了mongodb配置服务器、路由服务器，各个分片服务器，不过应用程序连接到mongos路由服务器并不能使用分片机制，还需要在程序里设置分片配置，让分片生效。 
  登陆任意一台mongos，进入/usr/local/mongodb/bin目录下
  ./mongo --port 23000
  
  使用admin数据库
  use  admin
  
  串联路由服务器与分配副本集
  sh.addShard("shard1/192.168.221.130:25001,192.168.221.131:25001,192.168.221.132:25001")
  sh.addShard("shard2/192.168.221.130:25002,192.168.221.131:25002,192.168.221.132:25002")
  sh.addShard("shard3/192.168.221.130:25003,192.168.221.131:25003,192.168.221.132:25003")

  查看集群状态
  sh.status()



6、 测试
目前配置服务、路由服务、分片服务、副本集服务都已经串联起来了，但我们的目的是希望插入数据，数据能够自动分片。连接在mongos上，准备让指定的数据库、指定的集合分片生效。 

指定testdb分片生效
db.runCommand( { enablesharding :"testdb"});

指定数据库里需要分片的集合和片键
db.runCommand( { shardcollection : "testdb.table1",key : {id: “hashed”} } )

我们设置testdb的 table1 表需要分片，根据 id 自动分片到 shard1 ，shard2，shard3 上面去。要这样设置是因为不是所有mongodb 的数据库和表 都需要分片！插入100000条数据测试： 

查看分配状态：查看分片生效
db.table1.stats();

如下图所示：shard1总数：33755条 
Shard2总数：33143条 
Shard3总数：33102条 



7、注意事项

出现如下问题，说明初始化没有成功，如果初始化没有成功会导致后面路由服务器启动不了 
rs.initiate(config);时候报错了


出现如下问题需要关闭进程。重启服务 
启动服务的时候报错
about to fork child process, waiting until server is ready for connections.

ERROR: child process failed, exited with error number 100
或者
ERROR: child process failed, exited with error number 1


检查一下是不是文件夹权限问题，类似这种:
sudo chown 你的userName /data
sudo chown 你的userName /data/db
sudo chown 你的userName /data/log


about to fork child process, waiting until server is ready for connections.
失败的原因是因为通过service mongodb restart时启动失败，或者如果是直接kill 掉来关闭都会出现这种情况
是因为没有正常关闭导致的。
         
 那么如何正常关闭mongodb？
可以去看官方文档：
http://docs.mongodb.org/manual/tutorial/manage-mongodb-processes/

先通过shell连上服务器：
mongo
use admin
db.shutdownServer()

或者直接kill -15 <pid>,注意kill -9 可能会导致数据文件损坏

那么处理这种要先删掉数据存放点里的mongodb.lock，和日志文件，在logpath的位置，全部删掉，然后再 mongod --repair -f /etc/mongod.conf

  是修复，但有可能还是不行，这时我采用了直接把数据考到另一个地方，然后通过/mongod --port 27017 --datapath /新的位置  --logpath /新的位置 --fork 这样就可以启动了

  我认为都是因为他记录了在log里，如果还是用原来的log很可能启动不了，一个新的log，没有记录出错的地方就可以启动，反正我认为只要删了lock文件，并指向到一个新的log目录，或者是删掉原来log目录里的所有文件就可以。启动不了主要是两个地方控治，一个就是mongod.lock,另一个就是log

   对了注意启动时logpath /home/mongodb/logs/mongod.log 这里是指向一个文件，而不是目录，如果不是指向文件也是启动不了
   而dbpath 指向的是一个目录



MONGODB启动不了：CHILD PROCESS FAILED, EXITED WITH ERROR NUMBER 100
2. 删除了 /var/lib/mongo/mongod.lock后,尝试启动,依旧无法启动
3. 以repair 模式启动
mongod -f /etc/mongod.conf --repair
其实此处可以看到运行状态已经是start状态

4. 然后使用命令
mongod -f /etc/mongod.conf
再次启动一次

5. 然后查看mongo的运行情况
[root@CNSHAS-JD05 mongo]# ps -ef|grep mongo*



之前公司服务器由于停电导致MongoDB数据库非正常关闭，mongod 被锁，重启时报错ERROR: child process failed, exited with error number 1，

解决办法如下：
1、删除db文件夹下mongod.lock，删除logs文件夹下日志文件mongodb.log.2017-03-24T01-04-33 ,如果有必要把 log日志全部删除。
2、进入mongodb根目录下bin目录，输入命令 ./mongod –repair 修复
3、输入命令 ./mongod –dbpath=/usr/mongodb/db –port=27017 –fork –logpath=/usr/mongodb/logs/mongodb.log 重启数据库。dbpath为db文件夹路径，logpath为log文件夹路径，根据自己需求填写路径。


---------------------------------------------------------------------------------------------------------------------
副本集Replica Set部署方式：

2.2.2搭建集群
https://blog.csdn.net/luonanqin/article/details/8497860



1.建立数据文件夹

一般情况下不会把数据目录建立在mongodb的解压目录下，不过这里方便起见，就建在mongodb解压目录下吧。

mkdir -p /mongodb/data/master 
mkdir -p /mongodb/data/slaver 
mkdir -p /mongodb/data/arbiter  
#三个目录分别对应主，备，仲裁节点


2.建立配置文件

由于配置比较多，所以我们将配置写到文件里。

#master.conf
dbpath=/mongodb/data/master
logpath=/mongodb/log/master.log
pidfilepath=/mongodb/master.pid
directoryperdb=true
logappend=true
replSet=testrs
bind_ip=10.10.148.130
port=27017
oplogSize=10000
fork=true
noprealloc=true
#slaver.conf
dbpath=/mongodb/data/slaver
logpath=/mongodb/log/slaver.log
pidfilepath=/mongodb/slaver.pid
directoryperdb=true
logappend=true
replSet=testrs
bind_ip=10.10.148.131
port=27017
oplogSize=10000
fork=true
noprealloc=true
#arbiter.conf
dbpath=/mongodb/data/arbiter
logpath=/mongodb/log/arbiter.log
pidfilepath=/mongodb/arbiter.pid
directoryperdb=true
logappend=true
replSet=testrs
bind_ip=10.10.148.132
port=27017
oplogSize=10000
fork=true
noprealloc=true

参数解释：
dbpath：数据存放目录
logpath：日志存放路径
pidfilepath：进程文件，方便停止mongodb
directoryperdb：为每一个数据库按照数据库名建立文件夹存放
logappend：以追加的方式记录日志
replSet：replica set的名字
bind_ip：mongodb所绑定的ip地址
port：mongodb进程所使用的端口号，默认为27017
oplogSize：mongodb操作日志文件的最大大小。单位为Mb，默认为硬盘剩余空间的5%
fork：以后台方式运行进程
noprealloc：不预先分配存储



3.启动mongodb

进入每个mongodb节点的bin目录下
./monood -f master.conf
./mongod -f slaver.conf
./mongod -f arbiter.conf
注意配置文件的路径一定要保证正确，可以是相对路径也可以是绝对路径。




---------------------------------------------------------------------------------------------------------------------
mongo连接

mongo 远程主机ip或DNS:MongoDB端口号/数据库名 -u user -p password


//使用默认端口连接MongoDB
mongo 192.168.1.100

//连接MongoDB并指定端口
mongo 192.168.1.100:27017

//连接到指定的MongoDB数据库
mongo 192.168.1.100:27017/test

//指定用户名和密码连接到指定的MongoDB数据库
mongo 192.168.1.200:27017/test -u user -p password



查询：
db.getCollection("AOP_ABILITY_BASEINFO")

db.AOP_ABILITY_BASEINFO.find().count();
db.AOP_ABILITY_BASEINFO.find({key1:value1, key2:value2}).pretty()


类似常规 SQL 语句为： 'where likes>50 AND (by = '菜鸟教程' OR title = 'MongoDB 教程')'
>db.col.find({"likes": {$gt:50}, $or: [{"by": "菜鸟教程"},{"title": "MongoDB 教程"}]}).pretty()


---------------------------------------------------------------------------------------------------------------------


