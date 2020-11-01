- [InfluxDB的关键概念](#InfluxDB的关键概念)
- [InfluxDB提供三种操作方式](#InfluxDB提供三种操作方式)
- [Influxdb时序数据库安装](#Influxdb时序数据库安装)


---------------------------------------------------------------------------------------------------------------------
## InfluxDB的关键概念

![influx概念说明](./images/概念.png "ReferencePicture")

database、measurement、point
tag key、tag set、tag value
field key、field set、field value
timestamp
series序列
Retention policy数据保留策略
Continuous Queries连续查询

1）tag--标签，在InfluxDB中，tag是一个非常重要的部分，表名+tag一起作为数据库的索引，是“key-value”的形式。

2）field--数据，field主要是用来存放数据的部分，也是“key-value”的形式。

3）timestamp--时间戳，作为时序型数据库，时间戳是InfluxDB中最重要的部分，在插入数据时可以自己指定也可留空让系统指定。

说明：在插入新数据时，tag、field和timestamp之间用空格分隔。

4）series--序列，所有在数据库中的数据，都需要通过图表来展示，而这个series表示这个表里面的数据，可以在图表上画成几条线。

5）Retention policy--数据保留策略，可以定义数据保留的时长，每个数据库可以有多个数据保留策略，但只能有一个默认策略。。

6）Point--点，表示每个表里某个时刻的某个条件下的一个field的数据，因为体现在图表上就是一个点，于是将其称为point。

measurement：一类数据
series：数据序列

一个series就是一个测点，或者说一条曲线，那么retention policy, measurement, tagset就共同组成了一个定位测点序列的唯一标识。
point，就是某个series的同一个时刻的多个field的value，就组成了一个point；其实就是一般曲线上的一个点。
InfluxDb不需要做schema定义，这意味着你可以随意的添加measurements, tags, and fields at any time，


---------------------------------------------------------------------------------------------------------------------
## InfluxDB提供三种操作方式
1）客户端命令行方式
2）HTTP API接口
3）各语言API库


1、客户端命令行方式：

show databases
create database test
use test
drop database test

SHOW MEASUREMENTS
drop measurement disk_free


修改和删除数据
InfluxDB属于时序数据库，没有提供修改和删除数据的方法。
但是删除可以通过InfluxDB的数据保存策略（Retention Policies）来实现


2、HTTP API接口

1）建立数据库
curl -POST http://localhost:8086/query --data-urlencode "q=CREATE DATABASE mydb"
执行这个语句后，会在本地建立一个名为mydb的数据库。

2）删除数据库
curl -POST http://localhost:8086/query --data-urlencode "q=DROP DATABASE mydb"
其实使用HTTP API就是向 InfluxDB 接口发送相应的POST请求。将语句通过POST方式发送到服务器。

InfluxDB通过HTTP API添加数据主要使用如下格式：
curl -i -XPOST 'http://localhost:8086/write?db=mydb' --data-binary 'cpu_load_short,host=server01,region=us-west value=0.64 1434055562000000000'
说明：db=mydb是指使用mydb这个数据库。
--data-binary后面是需插入数据。
cpu_load_short是表名（measurement），tag字段是host和region，值分别为：server01和us-west。
field key字段是value，值为0.64。
时间戳（timestamp）指定为1434055562000000000。
这样，就向mydb数据库的cpu_load_short表中插入了一条数据。

其中，db参数必须指定一个数据库中已经存在的数据库名，数据体的格式遵从InfluxDB规定格式，首先是表名，后面是tags，然后是field，最后是时间戳。tags、field和时间戳三者之间以空格相分隔。


InfluxDB通过HTTP API添加多条数据与添加单条数据相似，示例如下：

curl -i -XPOST 'http://localhost:8086/write?db=mydb' --data-binary 'cpu_load_short,host=server02 value=0.67
cpu_load_short,host=server02,region=us-west value=0.55 1422568543702900257
cpu_load_short,direction=in,host=server01,region=us-west value=2.0 1422568543702900257'

在使用HTTP API时，InfluxDB的响应主要有以下几个：
1）2xx：204代表no content，200代表InfluxDB可以接收请求但是没有完成请求。一般会在body体中带有出错信息。
2）4xx：InfluxDB不能解析请求。
3）5xx：系统出现错误。



InfluxDB推出了数据保留策略（Retention Policies），用来让我们自定义数据的保留时间
InfluxDB的数据保留策略（RP） 用来定义数据在InfluxDB中存放的时间，或者定义保存某个期间的数据。
一个数据库可以有多个保留策略，但每个策略必须是独一无二的。
InfluxDB本身不提供数据的删除操作，因此用来控制数据量的方式就是定义数据保留策略。
因此定义数据保留策略的目的是让InfluxDB能够知道可以丢弃哪些数据，从而更高效的处理数据。
InfluxDB 数据保留策略 操作：增删改查



InfluxDB连续查询（Continuous Queries）
InfluxDB的连续查询是在数据库中自动定时启动的一组语句，语句中必须包含 SELECT 关键词和 GROUP BY time() 关键词。
InfluxDB会将查询结果放在指定的数据表中。
使用连续查询是最优的降低采样率的方式，连续查询和存储策略搭配使用将会大大降低InfluxDB的系统占用量。
而且使用连续查询后，数据会存放到指定的数据表中，这样就为以后统计不同精度的数据提供了方便。
在InfluxDB中，将连续查询与数据存储策略一起使用会达到最好的效果。
比如，将精度高的表的存储策略定为一个周，然后将精度底的表存储策略定的时间久一点，这要就可以实现高低搭配，以满足不同的工作需要。

指定连续查询的时间范围
指定连续查询的执行频次
同时指定连续查询的范围和频次




1）count()函数
返回一个（field）字段中的非空值的数量。
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







---------------------------------------------------------------------------------------------------------------------

## Influxdb时序数据库安装


时序数据库：Time Series Database (TSDB)
https://github.com/influxdata/influxdb
https://docs.influxdata.com/influxdb/v1.7/introduction/getting-started/
https://docs.influxdata.com/influxdb/v1.7/administration/config

Java客户端
https://github.com/influxdata/influxdb-java



安装
brew update
brew install influxdb
brew upgrade  influxdb


服务端是influxd
客户端是influx


启动
influxd

influxd -config /etc/influxdb/influxdb.conf
或者
echo $INFLUXDB_CONFIG_PATH
/etc/influxdb/influxdb.conf
influxd


influx其他参数请使用：influx --help


连接
influx
influx -host 127.0.0.1 -port 8086
influx -database test
influx -format=json -pretty 可以格 式化返回的json



influxdb默认端口号是8086,如果在配置文件修改端口号后启动命令变为influx -port 8087
influxdb默认连接本地数据库,如果想要连接远程数据库 influx -host 128.0.0.1:8086
启动命令并加载数据库 influx -database test
使用 influx -format 可以修改返回的数据格式,可选选项有 json|csv|column,例 子:influx -format=json 返回json格式,使用 influx -format=json -pretty 可以格 式化返回的json




端口说明
8083：访问web页面的地址，8083为默认端口；
8086：数据写入influxdb的地址，8086为默认端口；
8088：数据备份恢复地址，8088为默认端口；

PS：如果你的服务器是阿里云的话，记得在阿里云控制台-安全组，开启准入访问的端口，以免无法访问！



一、InfluxDB介绍

InfluxDB 是用Go语言编写的一个开源分布式时序、事件和指标数据库，无需外部依赖。

1、特色功能
①、基于时间序列，支持与时间有关的相关函数（如最大，最小，求和等）；
②、可度量性：你可以实时对大量数据进行计算；
③、基于事件：它支持任意的事件数据；

2、主要特点
1）无结构（无模式）：可以是任意数量的列；
2）可拓展；
3）支持min, max, sum, count, mean, median 等一系列函数，方便统计；
4）原生的HTTP支持，内置HTTP API；
5）强大的类SQL语法；
6）自带管理界面，方便使用；








参考文章
https://www.cnblogs.com/imyalost/p/9689209.html
https://www.cnblogs.com/mafeng/p/6848166.html
https://www.linuxdaxue.com/series/influxdb-series/

influx语法
https://segmentfault.com/a/1190000015721780


教程
https://www.linuxdaxue.com/influxdb-study-series-manual.html


命令
https://blog.csdn.net/yue530tomtom/article/details/82625561
https://jasper-zhang1.gitbooks.io/influxdb/content/Introduction/getting_start.html



---------------------------------------------------------------------------------------------------------------------





---------------------------------------------------------------------------------------------------------------------






---------------------------------------------------------------------------------------------------------------------




























