- [Influxdb时序数据库安装](#Influxdb时序数据库安装)
- [Influxdb Docker安装](#Influxdb使用Docker安装)
- [Influxdb监控管理界面](#Influxdb监控管理界面)


---------------------------------------------------------------------------------------------------------------------
## Influxdb时序数据库安装

安装
```
brew update
brew install influxdb
brew upgrade  influxdb
```


- 服务端是influxd
- 客户端是influx


启动
```
influxd
或者
influxd -config /etc/influxdb/influxdb.conf

influxd -config /usr/local/etc/influxdb.conf

或者
echo $INFLUXDB_CONFIG_PATH
/etc/influxdb/influxdb.conf
influxd
```

```
show databases //展示所有的数据库
create database  [databaseName] //创建数据库
use [databaseName] //使用其中的一个数据库，表名有点号时，用双引号包起来
show measurements  //展示选中数据库中所有的数据表，没有表则无返回
insert <tbname>,<tags> <values> [timestamp]    //新增一条数据，表不存在时，创建表；
drop measurement  <tbname>    //删除表
delete from <tbname>  //删除表中所有的数据
precision rfc3339 //可以设置一下时间显示格式
show users //展示所有的用户
create user "user" with password 'user'   //新增用户
create user "admin" with password 'admin' with all privileges //创建管理员用户并授权管理员
drop user "user"  //删除用户
```

influx其他参数请使用：influx --help


- HTTP API 监听端口是 8086，
- 如果需要更改这些默认设定，修改 InfluxDB 的配置文件（/etc/influxdb/influxdb.conf）并重启就可以了。
- 启动后打开 web 管理界面 http://127.0.0.1:8083/ 默认用户名和密码是 root 和 root. InfluxDB 的 Web 管理界面端口是 8083，

8086，HTTP API
8088，RPC端口，用于备份和恢复
8083 官方自从 1.3 版本开始把 web 界面取消了,或者说换成了Chronograf


连接
```
influx
influx -host 127.0.0.1 -port 8086
influx -database test
influx -format=json -pretty 可以格 式化返回的json


show databases; #展示当前存在的数据库

使用exit命令退出InfluxDB的shell

```


- influxdb默认端口号是8086,如果在配置文件修改端口号后启动命令变为influx -port 8087
- influxdb默认连接本地数据库,如果想要连接远程数据库 influx -host 128.0.0.1:8086
- 启动命令并加载数据库 influx -database test
- 使用 influx -format 可以修改返回的数据格式,可选选项有 json|csv|column,例 子:influx -format=json 返回json格式,使用 influx -format=json -pretty 可以格 式化返回的json


端口说明
```
8083：访问web页面的地址，8083为默认端口；
8086：数据写入influxdb的地址，8086为默认端口；
8088：数据备份恢复地址，8088为默认端口；
```

PS：如果你的服务器是阿里云的话，记得在阿里云控制台-安全组，开启准入访问的端口，以免无法访问！




https://blog.csdn.net/u014087707/article/details/52700946

参考文章
https://www.cnblogs.com/imyalost/p/9689209.html
https://www.cnblogs.com/mafeng/p/6848166.html
https://www.linuxdaxue.com/series/influxdb-series/





命令
https://blog.csdn.net/yue530tomtom/article/details/82625561
https://jasper-zhang1.gitbooks.io/influxdb/content/Introduction/getting_start.html



---------------------------------------------------------------------------------------------------------------------
## Influxdb使用Docker安装


安装Influxdb

docker search inflxudb

docker pull influxdb

docker pull influxdb:1.8.4


mkdir -p $HOME/influxdb/data

docker run -itd --name influxdb-dev -p 8086:8086 -v $HOME/influxdb/data:/var/lib/influxdb influxdb:1.8.4

docker run -itd --name influxdb-dev -p 8086:8086 -v $HOME/influxdb/data:/var/lib/influxdb influxdb



使用

docker exec -it influxdb-dev bash

influx -help
influx -version

```aidl
# influxDB中创建数据库
create database imooc

# influxDB中创建用户bubba,设置密码bumblebeetuna,并授予所有权限
create user bubba with password 'bumblebeetuna'
grant all privileges to bubba

# 查询imooc数据库的nginx_log表的所有数据
use imooc								# 使用imooc数据库
select * from nginx_log
```


用户管理
show users ; 显示用户
create user “username” with password ‘password’ 创建用户
create user “username” with password ‘password’ with all privileges  创建管理员权限的用户
drop user ‘username’ 删除用户
SET PASSWORD FOR admin =’influx@gpscloud’


create user "admin" with password 'admin123' with all privileges




[https://hub.docker.com/_/influxdb](https://hub.docker.com/_/influxdb)  
[Docker安装influxDB](https://blog.csdn.net/CSDN_FlyYoung/article/details/89923878)  


---------------------------------------------------------------------------------------------------------------------

## Influxdb监控管理界面

官方自从 1.3 版本开始把 web 界面取消了或者说换成了Chronograf



- 官方Chronograf,支持 influxdb 的基础监控、管理以及数据展示、警报管理及数据库管理
- 第三方开源监控软件InfluxDBStudio




安装和配置TICK堆栈的所有四个软件包（Telegraf，InfluxDB，Chronograf和Kapacitor）：必须4个一起使用，Chronograf是监控TICK的，不是InfluxDB

TICK方案架构
- Telegraf：采用插件机制实现的数据采集服务，可以采集包含Docker容器在内的多种性能数据
- InfluxDB：专门负责存储时序数据
- Chronograf：基于React.js编写的性能数据可视化服务
- Kapacitor：提供告警触发和处理功能

Telegraf负责采集节点上的性能数据，然后放入InfluxDB数据库进行存储，
Kapacitor通过监听InfluxDB的性能数据来对异常指标发出告警，Kapacitor负责在Chronograf中创建和发送警报。
而Chronograf用来展示集群实时的各项性能指标和状态，提供一个可视化的界面。




Open source monitoring and visualization UI for the TICK stack

Chronograf是一个用Go和React.js编写的开源Web应用程序，它提供了可视化监视数据并轻松创建警报和自动化规则的工具。

[Chronograf](https://github.com/influxdata/chronograf)  
[Chronograf官网](https://www.influxdata.com/time-series-platform/chronograf/)
[Chronograf文档](https://docs.influxdata.com/chronograf/v1.8/)

[Chronograf dockerhub](https://hub.docker.com/_/chronograf/)  



安装Chronograf

docker pull chronograf:latest

docker pull chronograf:1.8.8

mkdir -p $HOME/chronograf


docker run -itd \
--name influxdb-chronograf \
-p 8888:8888 \
-v $HOME/chronograf:/var/lib/chronograf \
chronograf \
--influxdb-url=http://influxdb-dev:8086


http://localhost:8888/






InfluxDB Studio is a UI management tool for the InfluxDB time series database.
[InfluxDB Studio](https://github.com/CymaticLabs/InfluxDBStudio)





一个监控的示例
https://github.com/youngbloood/tick-monitor

https://www.jianshu.com/p/c7d18e7afccd

https://developer.aliyun.com/article/606242

https://www.cnblogs.com/zouhao/p/9865254.html
https://www.hangge.com/blog/cache/detail_3002.html
