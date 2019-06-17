1、官网、驱动、可视化工具和安装



---------------------------------------------------------------------------------------------------------------------
https://www.runoob.com/mongodb/mongodb-java.html
https://mongodb.github.io/mongo-java-driver/3.10/driver/getting-started/quick-start/

![mongodb概念说明](./images/mongodb-concept.png "ReferencePicture")
---------------------------------------------------------------------------------------------------------------------
官网、驱动、可视化工具、安装、连接

https://www.mongodb.com/
https://www.mongodb.com/cn
https://docs.mongodb.com/
https://docs.mongodb.com/manual/

驱动
https://mongodb.github.io/mongo-java-driver/
https://github.com/mongodb/mongo-java-driver
https://github.com/mongodb/mongo-python-driver
https://github.com/mongodb/mongo-go-driver

可视化工具
https://nosqlbooster.com/
https://studio3t.com/

MongoDB可视化工具Studio 3T的使用
https://blog.csdn.net/weixin_39999535/article/details/81383196


安装
export PATH=/Users/yangzl/mysoft/mongodb-osx-x86_64-4.0.10/bin:$PATH

如果你的数据库目录不是/data/db，可以通过 --dbpath 来指定。
mongod --dbpath=/Users/yangzl/mysoft/mongodb-osx-x86_64-4.0.10/data/db

默认port=27017 dbpath=/data/db



连接

客户端shell连接：
cd /Users/yangzl/mysoft/mongodb-osx-x86_64-4.0.10/bin
./mongo
默认连接到connecting to: mongodb://127.0.0.1:27017/?gssapiServiceName=mongodb



命令：
show dbs 显示所有数据的列表
执行 "db" 命令可以显示当前数据库对象或集合。

运行"use"命令，可以连接到一个指定的数据库。
> use local



---------------------------------------------------------------------------------------------------------------------









---------------------------------------------------------------------------------------------------------------------




