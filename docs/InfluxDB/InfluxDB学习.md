- [InfluxDB介绍](#InfluxDB介绍)
    - [InfluxDB的关键概念](#InfluxDB的关键概念)
    - [InfluxDB原理介绍](#InfluxDB原理介绍)
    - [无模式设计](#无模式设计)
    - [InfluxDB数据保留策略](#InfluxDB数据保留策略)
    - [InfluxDB连续查询（Continuous Queries）](#InfluxDB连续查询（Continuous Queries）)
- [InfluxDB提供三种操作方式](#InfluxDB提供三种操作方式)
  - [1、客户端InfluxQL命令行方式](#1、客户端InfluxQL命令行方式)
  - [2、HTTP API接口](#2、HTTP-API接口)
  - [3、各语言API库](#3、各语言API库)
- [InfluxDB的命令函数](#InfluxDB的命令函数)
- [Influxdb时序数据库安装](InfluxDB数据库安装.md)
- [InfluxQL基础语法教程GROUP BY子句](#InfluxQL基础语法教程GROUP-BY子句)




---------------------------------------------------------------------------------------------------------------------

## InfluxDB介绍

一、InfluxDB介绍

InfluxDB 是用Go语言编写的一个开源分布式时序、事件和指标数据库，无需外部依赖。

InfluxDB是一个开源的时序数据库，使用GO语言开发，特别适合用于处理和分析资源监控数据这种时序相关数据。而InfluxDB自带的各种特殊函数如求标准差，随机取样数据，统计数据变化比等，使数据统计和实时分析变得十分方便。

1、特色功能
- ①、基于时间序列，支持与时间有关的相关函数（如最大，最小，求和等）；
- ②、可度量性：你可以实时对大量数据进行计算；
- ③、基于事件：它支持任意的事件数据；

2、主要特点
- 1）无结构（无模式）：可以是任意数量的列；
- 2）可拓展；
- 3）支持min, max, sum, count, mean, median 等一系列函数，方便统计；
- 4）原生的HTTP支持，内置HTTP API；
- 5）强大的类SQL语法；
- 6）自带管理界面，方便使用；



时序数据库：Time Series Database (TSDB)  
[InfluxDB官网](https://www.influxdata.com/)  
[InfluxDB文档](https://docs.influxdata.com/influxdb)  
[InfluxDB Github](https://github.com/influxdata/influxdb)  
[https://docs.influxdata.com/influxdb/v1.8/introduction/getting-started](https://docs.influxdata.com/influxdb/v1.8/introduction/getting-started/)  
[https://docs.influxdata.com/influxdb/v1.8/administration/config](https://docs.influxdata.com/influxdb/v1.8/administration/config)  
[measurement查询数据语法](https://docs.influxdata.com/influxdb/v1.8/query_language/explore-data/)  
[InfluxDB中文文档](https://jasper-zhang1.gitbooks.io/influxdb/content/Introduction/)


Scalable datastore for metrics, events, and real-time analytics

可扩展的数据存储，用于指标，事件和实时分析



InfluxDB 2.x and InfluxDB 1.8+.
[influxdb-client-java](https://github.com/influxdata/influxdb-client-java)  
[InfluxDB 2.0 python client](https://github.com/influxdata/influxdb-client-python)  
[InfluxDB 2 Go Client](https://github.com/influxdata/influxdb-client-go)  


InfluxDB 1.7 or earlier instances
[Java客户端](https://github.com/influxdata/influxdb-java)  
[Python client for InfluxDB](https://github.com/influxdata/influxdb-python)  
[The old clientv2 for InfluxDB 1.x](https://github.com/influxdata/influxdb1-client)  


Open source monitoring and visualization UI for the TICK stack

Chronograf是一个用Go和React.js编写的开源Web应用程序，它提供了可视化监视数据并轻松创建警报和自动化规则的工具。

[Chronograf](https://github.com/influxdata/chronograf)  
[Chronograf官网](https://www.influxdata.com/time-series-platform/chronograf/)  



教程：
[InfluxDB中文文档](https://jasper-zhang1.gitbooks.io/influxdb/content/Introduction/)  
[InfluxDB系列学习教程目录](https://www.linuxdaxue.com/influxdb-study-series-manual.html)  
[Influxdb语法介绍与使用](https://segmentfault.com/a/1190000015721780)  



https://www.jianshu.com/p/e3eba4dc7439
[InfluxDB - 安装与使用](http://flacro.me/influxdb/)  
[InfluxDB的安装与配置](https://blog.csdn.net/qq_25464557/article/details/107356896)  
[初次使用时序数据库InfluxDB，安装、简单的命令及配置](https://www.jianshu.com/p/e7dd9463c247)  
[Docker安装InfluxDB](http://giaogiaocat.github.io/tool/docker-influxdb/)  



### InfluxDB的关键概念

![influx概念说明](./images/概念.png "ReferencePicture")

- database(库)、measurement（表）、point（行记录）
- point（行记录）：tags、fields、timestamp
- tags:tag key、tag value、tag set
- fields:field key、field value、field set
- series序列
- Retention policy数据保留策略
- Continuous Queries连续查询

database(库)、measurement（表）、point（行记录）
series--序列（多个point）、point（行记录，一个点）
measurement tags fields timestamp


Measurement（table）
Timestamp (PK)
Tags (index)
Fields (value)

数据格式：measurement,tags fields timestamp

命令行中写入和读取：
INSERT cpu,host=serverA,region=us_west value=0.64  
SELECT "host", "region", "value" FROM "cpu"


1）tag--标签，在InfluxDB中，tag是一个非常重要的部分，表名+tag一起作为数据库的索引，是“key-value”的形式。

2）field--数据，field主要是用来存放数据的部分，也是“key-value”的形式。

3）timestamp--时间戳，作为时序型数据库，时间戳是InfluxDB中最重要的部分，在插入数据时可以自己指定也可留空让系统指定。

说明：在插入新数据时，tag、field和timestamp之间用空格分隔。

4）series--序列，所有在数据库中的数据，都需要通过图表来展示，而这个series表示这个表里面的数据，可以在图表上画成几条线。

5）Retention policy--数据保留策略，可以定义数据保留的时长，每个数据库可以有多个数据保留策略，但只能有一个默认策略。。

6）Point--点，表示每个表里某个时刻的某个条件下的一个field的数据，因为体现在图表上就是一个点，于是将其称为point。



- measurement：一类数据
- series：数据序列
- 一个series就是一个测点，或者说一条曲线，那么retention policy, measurement, tagset就共同组成了一个定位测点序列的唯一标识。
- point，就是某个series的同一个时刻的多个field的value，就组成了一个point；其实就是一般曲线上的一个点。
- InfluxDb不需要做schema定义，这意味着你可以随意的添加measurements, tags, and fields at any time，



数据库的名字、measurement、tag keys、field keys和tag values只被存储一次且只能是字符串。只有field values和timestamp在每个数据点上都有存储。

非字符串类型的值大约需要3字节，字符串类型的值需要的空间由字符串的压缩来决定。





## InfluxDB原理介绍

InfluxDB是如何保存数据并且高性能对外提供存取服务的呢

centos下influxdb默认配置路径为/etc/influxdb/influxdb.conf

influxdb数据存储主要有3个目录，分别是meta、wal和data。
- meta主要存放元数据，该目录下有一个meta.db文件；存放数据库元数据
- wal目录存放预写日志文件，以.wal结尾；
- data目录存放TSM文件，以.tsm文件结尾。存放最终存储的数据，文件以.tsm结尾


在同一个database中，retention policy、measurement、tag sets 完全相同的数据同属于一个 series，从Index数据排列来看，同一个 series 的数据在物理上会按照时间顺序排列存储在一起，series 的 key 为 measurement + tags set 序列化字符串



InfluxDB 支持的数据类型非常简单：
- measurement : string
- tag key : string
- tag value : string
- field key : string
- field value : string , float , interger , boolean

你可以看到除了 field value 支持的数据类型多一点之外，其余全是字符串类型。

当然还有最重要的 timestamp ，InfluxDB 中的时间都是 UTC 时间，而且时间精度非常高，默认为纳秒。



在实际使用中，数据都是存储在 tag 或者 field 中，这两者最重要的区别就是，tag 会构建索引（也就是说查询时，where 条件里的是 tag ，则查询性能更高），field 则不会被索引。

存储数据到底是使用 tag 还是 field ，参考以下原则：
- 常用于查询条件的数据存储为 tag 。
- 计划使用 GROUP BY() 的数据存储为 tag 。
- 计划使用 InfluxQL function 的数据存储为 field 。
- 数据不只是 string 类型的存储为 field 。



索引

InfluxDB 通过构建索引可以提高查询性能。InfluxDB 中的索引有两种：In-memory 和 TSI 。这两种索引只能选择一种，且无法动态更改，一旦更改必须重启 InfluxDB 。

In-memory ：索引被存储在内存中，这也是默认使用的方式，性能更高。

TSI（ Time Series Index ）：In-memory 索引可以支持千万级别的 series ，然而内存资源终归是有限的，为了支持亿级和十亿级别的 series 数据，TSI 应运而生，其会将索引映射到磁盘文件上。




存储引擎

所有数据先写入到 WAL（ Write Ahead Log ）预写日志文件，并同步到 Cache 缓存中，当 Cache 缓存的数据达到了一定的大小，或者达到一定的时间间隔之后，数据会被写入到 TSM 文件中。

为了处理文件，存储引擎通过 Writers/Readers 处理数据的写和读。另外存储引擎还会使用 In-Memory Index 内存索引快速访问 measurements、tags、series 等数据。

存储引擎的组成部分：
- In-Memory Index ：跨分片的共享内存索引，并不是存储引擎本身特有的，存储引擎只是用到了它。
- WAL ：预写日志。
- Cache ：同步缓存 WAL 的内容，并最终刷写到 TSM 文件中去。
- TSM Files ：特定格式存储最终数据的磁盘文件。
- FileStore ：调节对磁盘上所有TSM文件的访问。
- Compactor ：压缩器。
- Compaction Planner ：压缩计划。
- Compression ：编码解码压缩。
- Writers/Readers ：读写文件。

IOPS（ Input/Output Operations Per Second ）



[关于时序数据库，一起看看influxdb原理那些事](https://zhuanlan.zhihu.com/p/104349911)  
[时序数据库 InfluxDB](https://segmentfault.com/a/1190000020921682)  





## 无模式设计

InfluxDB是一个无模式(schemaless)的数据库，你可以在任意时间添加measurement，tags和fields。注意：如果你试图写入一个和之前的类型不一样的数据(例如，filed字段之前接收的是数字类型，现在写了个字符串进去)，那么InfluxDB会拒绝这个数据。


对于REST的一个说明
InfluxDB使用HTTP作为方便和广泛支持的数据传输协议。

现代web的APIs都基于REST的设计，因为这样解决了一个共同的需求。因为随着终端数量的增长，组织系统的需求变得越来越迫切。REST是为了组织大量终端的一个业内认可的标准。这种一致性对于开发者和API的消费者都是一件好事：所有的参与者都知道期望的是什么。

REST的确是很方便的，而InfluxDB也只提供了三个API，这使得InfluxQL在翻译为HTTP请求的时候很简单便捷。 所以InfluxDB API并不是RESTful的。


HTTP返回值概要
2xx：如果你写了数据后收到HTTP 204 No Content，说明写入成功了！
4xx：表示InfluxDB不知道你发的是什么鬼。
5xx：系统过载或是应用受损。




---------------------------------------------------------------------------------------------------------------------
## InfluxDB提供三种操作方式
- 1）客户端InfluxQL命令行方式
- 2）HTTP API接口
- 3）各语言API库


[InfluxDB中文文档](https://jasper-zhang1.gitbooks.io/influxdb/content/Introduction/getting_start.html)  



### 1、客户端InfluxQL命令行方式

show databases
create database testMetric
use testMetric
drop database test

SHOW MEASUREMENTS
drop measurement disk_free

SELECT * FROM "test-org.quickstart.dropwizard.metrics.MeterTest.request.tps"


show users //展示所有的用户
create user "user" with password 'user'   //新增用户
create user "admin" with password 'admin' with all privileges //创建管理员用户并授权管理员
drop user "user"  //删除用户




使用CLI插入单条的时间序列数据到InfluxDB中，用INSERT后跟数据点：
> INSERT cpu,host=serverA,region=us_west value=0.64

这样一个measurement为cpu，tag是host和region，value值为0.64的数据点被写入了InfluxDB中。

说明：我们在写入的时候没有包含时间戳，当没有带时间戳的时候，InfluxDB会自动添加本地的当前时间作为它的时间戳。




> SELECT "host", "region", "value" FROM "cpu"


让我们来写入另一笔数据，它包含有两个字段：

> INSERT temperature,machine=unit42,type=assembly external=25,internal=37

查询的时候想要返回所有的字段和tag，可以用*：

> SELECT * FROM "temperature"

InfluxQL还有很多特性和用法没有被提及，包括支持golang样式的正则，例如：

> SELECT * FROM /.*/ LIMIT 1
--
> SELECT * FROM "cpu_load_short"
--
> SELECT * FROM "cpu_load_short" WHERE "value" > 0.9
>


用户管理
show users ; 显示用户
create user “username” with password ‘password’ 创建用户
create user “username” with password ‘password’ with all privileges 创建管理员权限的用户
drop user ‘username’ 删除用户
SET PASSWORD FOR admin =’influx@gpscloud’
————————————————
版权声明：本文为CSDN博主「Muroidea」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/u014087707/article/details/52700946




查询数据参考 [measurement查询数据语法](https://docs.influxdata.com/influxdb/v1.8/query_language/explore-data/)



修改和删除数据
InfluxDB属于时序数据库，没有提供修改和删除数据的方法。
但是删除可以通过InfluxDB的数据保存策略（Retention Policies）来实现



### 2、HTTP API接口

1）建立数据库
curl -POST http://localhost:8086/query --data-urlencode "q=CREATE DATABASE mydb"
curl -i -X POST http://localhost:8086/query --data-urlencode "q=CREATE DATABASE mydb"

执行这个语句后，会在本地建立一个名为mydb的数据库。

2）删除数据库
curl -POST http://localhost:8086/query --data-urlencode "q=DROP DATABASE mydb"
其实使用HTTP API就是向 InfluxDB 接口发送相应的POST请求。将语句通过POST方式发送到服务器。



InfluxDB通过HTTP API添加数据主要使用如下格式：
curl -i -XPOST 'http://localhost:8086/write?db=mydb' --data-binary 'cpu_load_short,host=server01,region=us-west value=0.64 1434055562000000000'

说明：
- db=mydb是指使用mydb这个数据库。
- --data-binary后面是需插入数据。
- cpu_load_short是表名（measurement），tag字段是host和region，值分别为：server01和us-west。
- field key字段是value，值为0.64。
- 时间戳（timestamp）指定为1434055562000000000。

这样，就向mydb数据库的cpu_load_short表中插入了一条数据。

其中，db参数必须指定一个数据库中已经存在的数据库名，数据体的格式遵从InfluxDB规定格式，首先是表名，后面是tags，然后是field，最后是时间戳。tags、field和时间戳三者之间以空格相分隔。


通过HTTP接口POST数据到/write路径是我们往InfluxDB写数据的主要方式。下面的例子写了一条数据到mydb数据库。这条数据的组成部分是measurement为cpu_load_short，tag的key为host和region，对应tag的value是server01和us-west，field的key是value，对应的数值为0.64，而时间戳是1434055562000000000。







InfluxDB通过HTTP API添加多条数据与添加单条数据相似，示例如下：

curl -i -XPOST 'http://localhost:8086/write?db=mydb' --data-binary 'cpu_load_short,host=server02 value=0.67
cpu_load_short,host=server02,region=us-west value=0.55 1422568543702900257
cpu_load_short,direction=in,host=server01,region=us-west value=2.0 1422568543702900257'


在使用HTTP API时，InfluxDB的响应主要有以下几个：
- 1）2xx：204代表no content，200代表InfluxDB可以接收请求但是没有完成请求。一般会在body体中带有出错信息。
- 2）4xx：InfluxDB不能解析请求。
- 3）5xx：系统出现错误。


HTTP返回值概要
2xx：如果你写了数据后收到HTTP 204 No Content，说明写入成功了！
4xx：表示InfluxDB不知道你发的是什么鬼。
5xx：系统过载或是应用受损。



无模式设计

InfluxDB是一个无模式(schemaless)的数据库，你可以在任意时间添加measurement，tags和fields。注意：如果你试图写入一个和之前的类型不一样的数据(例如，filed字段之前接收的是数字类型，现在写了个字符串进去)，那么InfluxDB会拒绝这个数据。



使用HTTP接口查询数据

HTTP接口是InfluxDB查询数据的主要方式。通过发送一个GET请求到/query路径，并设置URL的db参数为目标数据库，设置URL参数q为查询语句。下面的例子是查询在写数据里写入的数据点。

curl -G 'http://localhost:8086/query?pretty=true' --data-urlencode "db=mydb" --data-urlencode "q=SELECT \"value\" FROM \"cpu_load_short\" WHERE \"region\"='us-west'"

说明：添加pretty=ture参数在URL里面，是为了让返回的json格式化。这在调试或者是直接用curl的时候很有用，但在生产上不建议使用，因为这样会消耗不必要的网络带宽。



多个查询

在一次API调用中发送多个InfluxDB的查询语句，可以简单地使用分号分隔每个查询，例如：

curl -G 'http://localhost:8086/query?pretty=true' --data-urlencode "db=mydb" --data-urlencode "q=SELECT \"value\" FROM \"cpu_load_short\" WHERE \"region\"='us-west';SELECT count(\"value\") FROM \"cpu_load_short\" WHERE \"region\"='us-west'"

curl -G 'http://172.16.113.4:8086/query?pretty=true' --data-urlencode "db=mydb" --data-urlencode "q=SELECT \"value\" FROM \"cpu_load_short\" WHERE \"region\"='us-west';SELECT count(\"value\") FROM \"cpu_load_short\" WHERE \"region\"='us-west'"



时间戳格式

在InfluxDB中的所有数据都是存的UTC时间，时间戳默认返回RFC3339格式的纳米级的UTC时间，例如2015-08-04T19:05:14.318570484Z，如果你想要返回Unix格式的时间，可以在请求参数里设置epoch参数，其中epoch可以是[h,m,s,ms,u,ns]之一。例如返回一个秒级的epoch：

curl -G 'http://localhost:8086/query' --data-urlencode "db=mydb" --data-urlencode "epoch=s" --data-urlencode "q=SELECT \"value\" FROM \"cpu_load_short\" WHERE \"region\"='us-west'"




### 3、各语言API库

---------------------------------------------------------------------------------------------------------------------


InfluxDB提供了两个特性——连续查询(Continuous Queries简称CQ)和保留策略(Retention Policies简称RP)，分别用来处理数据采样和管理老数据的。


### InfluxDB数据保留策略

InfluxDB推出了数据保留策略（Retention Policies），用来让我们自定义数据的保留时间

InfluxDB的数据保留策略（RP） 用来定义数据在InfluxDB中存放的时间，或者定义保存某个期间的数据。

一个数据库可以有多个保留策略，但每个策略必须是独一无二的。

InfluxDB本身不提供数据的删除操作，因此用来控制数据量的方式就是定义数据保留策略。

因此定义数据保留策略的目的是让InfluxDB能够知道可以丢弃哪些数据，从而更高效的处理数据。

InfluxDB 数据保留策略 操作：增删改查

查看现有策略
show retention policies on db_name

新建策略
create retention policy "policy_name" on "db_name" duration <time> replication n [default]

//例子
create retention policy "3_hour" on "test" duration 3h default

修改策略
alter retention policy "3_hour" on "test" duration 4h default

删除策略
drop retention policy "3_hour" on "test"




### InfluxDB连续查询（Continuous Queries）


- InfluxDB的连续查询是在数据库中自动定时启动的一组语句，语句中必须包含 SELECT 关键词和 GROUP BY time() 关键词。
- InfluxDB会将查询结果放在指定的数据表中。
- 一般用于定时聚合数据，如聚合访问记录为每天访问量。连续查询主要用在将数据归档，以降低系统空间的占用率，主要是以降低精度为代价。连续查询和存储策略搭配使用将会大大降低InfluxDB的系统占用量。
- 只有管理员才可以操作连续查询


连续查询 Continuous Queries（ CQ ）是 InfluxDB 很重要的一项功能，它的作用是在 InfluxDB 数据库内部自动定期的执行查询，然后将查询结果存储到指定的 measurement 里。

使用连续查询是最优的降低采样率的方式，连续查询和存储策略搭配使用将会大大降低InfluxDB的系统占用量。

而且使用连续查询后，数据会存放到指定的数据表中，这样就为以后统计不同精度的数据提供了方便。

在InfluxDB中，将连续查询与数据存储策略一起使用会达到最好的效果。

比如，将精度高的表的存储策略定为一个周，然后将精度底的表存储策略定的时间久一点，这要就可以实现高低搭配，以满足不同的工作需要。

指定连续查询的时间范围  
指定连续查询的执行频次  
同时指定连续查询的范围和频次

语法
create continuous query cq_name on db_name [resample [every <interval>][for <interval>]]
begin
select <function><field>,... into measurement_1
from measurement_2 where... group by time(time<interval>)
end

//例子
create continuous query billed_30 on payment begin select sum(billed) into billed_30min from payment group by time(30m) end
//这个连续查询会每30分钟执行一次

显示所有连续查询
show continuous queries

删除
drop continuous query cq_name on db_name



https://segmentfault.com/a/1190000015721780

[采样和数据保留](https://jasper-zhang1.gitbooks.io/influxdb/content/Guide/downsampling_and_retention.html)  




---------------------------------------------------------------------------------------------------------------------
## InfluxDB的命令函数

InfluxDB的函数可以分成Aggregate，select和predict类型。


Aggregate聚合类函数

常用的聚合函数有count、disinct、mean(平均值)、median(中位数)、spread(最小值和最大值之间的差值)、sum等。

1）count()函数
返回一个（field）字段中的非空值的数量（行数）。
2）DISTINCT()函数
返回一个字段（field）的唯一值。
3）MEAN() 函数
返回一个字段（field）中的值的算术平均值（平均值）。字段类型必须是长整型或float64。
4）MEDIAN()函数
从单个字段（field）中的排序值返回中间值（中位数）。字段值的类型必须是长整型或float64格式。
5）SPREAD()函数
返回字段的最小值和最大值之间的差值。数据的类型必须是长整型或float64。
6）SUM()函数
返回一个字段中的所有值的和。字段的类型必须是长整型或float64。

MODE()
返回字段中出现频率最高的值。

http://www.linuxdaxue.com/influxdb-study-influxdb-aggregations-funcitons.html



Selectors选择类函数

1）TOP()函数
作用：返回一个字段中最大的N个值，字段类型必须是长整型或float64类型。
2）BOTTOM()函数
作用：返回一个字段中最小的N个值。字段类型必须是长整型或float64类型。
3）FIRST()函数
作用：返回一个字段中最老的取值。
4）LAST()函数
作用：返回一个字段中最新的取值。
5）MAX()函数
作用：返回一个字段中的最大值。该字段类型必须是长整型，float64，或布尔类型。
6）MIN()函数
作用：返回一个字段中的最小值。该字段类型必须是长整型，float64，或布尔类型。
7）PERCENTILE()函数
作用：返回排序值排位为N的百分值。字段的类型必须是长整型或float64。
百分值是介于100到0之间的整数或浮点数，包括100。

> SELECT PERCENTILE("water_level",5) FROM "h2o_feet"
查询返回water_level中值在总的field value中比较大的百分之五。

SAMPLE()
返回N个随机抽样的字段值。SAMPLE()使用reservoir sampling来生成随机点。

例一：返回field key的两个随机抽样的字段值
> SELECT SAMPLE("water_level",2) FROM "h2o_feet"
查询返回water_level的两个随机抽样的字段值。

http://www.linuxdaxue.com/influxdb-study-influxdb-selectors-funcitons.html



Transformations变换类函数

1）DERIVATIVE()函数
作用：返回一个字段在一个series中的变化率。
InfluxDB会计算按照时间进行排序的字段值之间的差异，并将这些结果转化为单位变化率。其中，单位可以指定，默认为1s。
2）DIFFERENCE()函数
作用：返回一个字段中连续的时间值之间的差异。字段类型必须是长整型或float64。
3）ELAPSED()函数
作用：返回一个字段在连续的时间间隔间的差异，间隔单位可选，默认为1纳秒。
4）MOVING_AVERAGE()函数
作用：返回一个连续字段值的移动平均值，字段类型必须是长整形或者float64类型。
5）NON_NEGATIVE_DERIVATIVE()函数
作用：返回在一个series中的一个字段中值的变化的非负速率。
6）STDDEV()函数
作用：返回一个字段中的值的标准偏差。值的类型必须是长整型或float64类型。

http://www.linuxdaxue.com/influxdb-study-influxdb-transformations-funcitons.html

[InfluxDB的函数可以分成Aggregate，select和predict类型](https://jasper-zhang1.gitbooks.io/influxdb/content/Query_language/functions.html)


---------------------------------------------------------------------------------------------------------------------
## InfluxQL基础语法教程GROUP BY子句

GROUP BY子句通过用户自己制定的tags set或time区间，来将查询结果进行分组。




一、GROUP BY tags

GROUP BY 通过用户指定的tag set，来对查询结果进行分组。

语法：
SELECT_clause FROM_clause [WHERE_clause]
GROUP BY [* | <tag_key>[,<tag_key]]

注 ：如果在sql中同时存在WHERE子句和GROUP BY子句，则GROUP BY子句一定要在WHERE子句之后！


GROUP BY子句	意义
GROUP BY *	使用所有tag对查询结果进行分组
GROUP BY <tag_key>	使用指定tag对查询结果进行分组
GROUP BY <tag_key>,<tag_key>	使用指定的多个tag对查询结果进行分组，其中tag之间的顺序是无关的。




二、基础GROUP BY time intervals

GROUP BY time() 查询会将查询结果按照用户指定的时间区间来进行分组。

语法：
SELECT <function>(<field_key>) 
FROM_clause
WHERE <time_range>
GROUP BY time(<time_interval>),[tag_key] [fill(<fill_option>)]

time(time_interval)
在GROUP BY time()子句中的time_interval是个连续的时间区间，该时间区间决定了InfluxDB如何通过时间来对查询结果进行分组。比如，如果time_interval为5m，那么它会将查询结果分为5分钟一组（如果在WHERE子句中指定了time区间，那么就是将WHERE中指定的time区间划分为没5分钟一组）。

fill(<fill_option>)
fill(<fill_option>) 是可选的。它可以填充那些没有数据的时间区间的值。 从 [GROUP BY time intervals and fill() ] (https://docs.influxdata.com/influxdb/v1.7/query_language/data_exploration/#group-by-time-intervals-and-fill) 部分可查看到关于这部分的更多信息。

注：基本的GROUP BY time()查询通过当前InfluxDB数据库的预设时间边界来确定每个时间间隔中包含的原始数据和查询返回的时间戳。




三、高级GROUP BY time() 语法

语法如下：
SELECT <function>(<field_key>)
FROM_clause
WHERE <time_range>
GROUP BY time(<time_interval>,<offset_interval>),[tag_key] [fill(<fill_option>)]

time(time_interval,offset_interval)
在GROUP BY time()子句中的通过time_interval和offset_interval来表示一个连续的时间区间，该时间区间决定了InfluxDB如何通过时间来对查询结果进行分组。比如，如果时间区间为5m，那么它会将查询结果分为5分钟一组（如果在WHERE子句中指定了time区间，那么就是将WHERE中指定的time区间划分为没5分钟一组）。
offset_interval是持续时间文本。它向前或向后移动InfluxDB数据库的预设时间边界。offset_interval可以为正或负。

fill(<fill_option>)
fill(<fill_option>)是可选的。 它可以填充那些没有数据的时间区间的值。 从 GROUP BY time intervals and fill() 部分可查看到关于这部分的更多信息。

注：高级 GROUP BY time() 语法依赖于time_interval、offset_interval、以及 InfluxDB 数据库的预设时间边界来确定每组内的数据条数、以及查询结果的时间戳。




GROUP BY time intervals and fill()
Fill() 可以填充那些没有数据的时间区间的值。

语法：
SELECT <function>(<field_key>) FROM_clause
WHERE <time_range>
GROUP BY time(time_interval,[<offset_interval])[,tag_key] [fill(<fill_option>)]

默认情况下，在GROUP BY time()查询结果中，若某个时间区间没有数据，则该时间区间对应的值为null。通过fill()，就可以填充那些没有数据的时间区间的值。
需要注意的是，fill()必须出现在GROUP BY子句的最后。

Fill选项

1. 任何数学数值
使用给定的数学数值进行填充
2. linear
为没有数据值的时间区间线性插入数值，使得插入之后的数值，跟其他本来就有数据的区间的值成线性。（这里翻译的不是很好，看示例就能明白了）
3. none
若某个时间区间内没有数据，则在查询结果中该区间对应的时间戳将不显示出来
4. null
没有值的区间，显示为null。这也是默认的选项。
5. previous
用前一个区间的数值来填充当前没有数据的区间的值。





[InfluxQL基础语法教程--GROUP BY子句](https://www.cnblogs.com/suhaha/p/11692281.html)  
[InfluxDB group by time()说明](https://bloodhunter.github.io/2019/03/08/influxdb-group-by-time-shuo-ming/)  




