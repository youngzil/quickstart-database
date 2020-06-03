连接、退出、帮助
数据库DB操作：查看、使用use、创建、删除
Collection级操作：新建、删除、重命名、查看所有、创建索引
Record级的操作：插入数据、查找记录（各种过滤条件）、删除、更新
对查询结果集的操作：Pretty打印、排序、统计


---------------------------------------------------------------------------------------------------------------------


连接

客户端shell连接：
cd /Users/yangzl/mysoft/mongodb-osx-x86_64-4.0.10/bin
./mongo
默认连接到connecting to: mongodb://127.0.0.1:27017/?gssapiServiceName=mongodb
或者
mongo 20.26.37.176:38200/esb
mongo 20.26.37.177:38201/esb
mongo 20.26.37.178:38202/esb

#使用默认端口27017登陆MongoDB
mongo 127.0.0.1

mongo --help

#使用exit，回车则退出交互环境


命令：
show dbs 显示所有数据的列表
执行 "db" 命令可以显示当前数据库对象或集合。

查看数据库
> show dbs

运行"use"命令，可以连接到一个指定的数据库。
> use local

>使用指定库
> use wiki

查看所有数据集
> show collections

创建数据库
使用use可以直接创建数据库，不过直到插入数据时，使用 show dbs才能看到库
> use test
> show dbs
> db.hello.insert({"name":"mongodb"})
> show dbs


查看当前使用的库
> db

删除数据库
> db.dropDatabase()
> db
> show dbs

---------------------------------------------------------------------------------------------------------------------
Collection级操作

新建collection，效果与使用 db.user.insert({"user":"xxx"})类似。
> db.createCollection("user")
{ "ok" : 1 }
> show collections
>

删除collection
> db.user.drop()
true
> show collections
hello
> db.user.drop() ### 再删除已经不存在
false

重命名collection
> show collections
hello
> db.hello.renameCollection("HELLO")
{ "ok" : 1 }
> show collections
HELLO

查看所有collection
> show collections
HELLO

创建索引在HELLO集合上，建立对Name字段的索引,1代表正序
> db.HELLO.ensureIndex({NAME:1})


查询集合
db.getCollection("AOP_ABILITY_BASEINFO")

db.AOP_ABILITY_BASEINFO.find().count();
db.AOP_ABILITY_BASEINFO.find({key1:value1, key2:value2}).pretty()

---------------------------------------------------------------------------------------------------------------------

Record级的操作

使用实现存在的test数据库做测试，其中有两个Collection:user

插入数据
向user插入数据
> db.user.insert({'name':'Gal Gadot','gender':'female','age':28,'salary':11000})  
> db.user.insert({'name':'Mikie Hara','gender':'female','age':26,'salary':7000})

也可以使用save插入
> db.user.save({'name':'Wentworth Earl Miller','gender':'male','age':41,'salary':33000})


查找记录
查看集合所有记录
> db.user.find()

查找符合记录的记录

Exact Equal
> db.user.find({"age":26})

Great Than
> db.user.find({salary:{$gt:7000}})


Fuzzy Match
查看名称中包含‘a’的数据
> db.user.find({name:/a/})

查询name以W打头的数据
> db.user.find({name:/^W/})


多条件“与”
查询age小于30，salary大于6000的数据
> db.user.find({age:{$lt:30},salary:{$gt:6000}})


多条件“或”
查询age小于25，或者salary大于10000的记录
> db.user.find({$or:[{salary:{$gt:10000}},{age:{$lt:25}}]})


查询一条记录
db.user.findOne({$or:[{salary:{$gt:10000}},{age:{$lt:25}}]})


查询记录中的指定列，这里的1表示显示此列的意思，也可以用true表示。
> db.user.find({},{name:1,age:1,salary:1,sex_orientation:true})


查询指定字段的数据，并去重
> db.user.distinct('gender')




对查询结果集的操作
mongo也提供了pretty print工具，db.collection.pretty()或者是db.collection.forEach(printjson)


Pretty Print
> db.user.find().pretty()


指定结果集返回条目
> db.user.find().limit(2)


查询第一条以外的数据
> db.user.find().skip(1)

对结果集排序
升序
> db.user.find().sort({salary:1})

降序
> db.user.find().sort({salary:-1})


统计结果集中的记录数量
> db.user.find().count()



删除记录
删除整个集合中的所有数据
> db.test.remove()

删除集合中符合过滤条件的数据
> db.test.remove({name:/aws/})

删除符合条件的一条记录
> db.test.remove({name:/aws/},1)


更新操作
db.collection.update(criteria, objNew, upsert, multi )

criteria:update的查询条件，类似sql update查询内where后面的
objNew:update的对象和一些更新的操作符（如$,$inc…）等，也可以理解为sql update查询内set后面的。
upsert : 如果不存在update的记录，是否插入objNew,true为插入，默认是false，不插入。
multi : mongodb默认是false,只更新找到的第一条记录，如果这个参数为true,就把按条件查出来多条记录全部更新。

> db.user.find() 


## 更新符合过滤条件的信息
> db.user.update({name:'Gal Gadot'},{$set:{age:23}},false,true) 
> db.user.find() 


## 添加新的字段
> db.user.update({name:'Mikie Hara'},{$set:{interest:"CBA"}},false,true) 
> db.user.find() 


## 使用函数 $inc
> db.user.update({gender:'female'},{$inc:{salary:50}},false,true) 
> db.user.find() 



关于更新操作db.collection.update(criteria, objNew, upsert, multi )，
要说明的是，如果upsert为true，那么在没有找到符合更新条件的情况下，mongo会在集合中插入一条记录其值满足更新条件的记录(其中的 字段只有更新条件中涉及的字段，字段的值满足更新条件)，然后将其更新

（注意，如果更新条件是$lt这种不等式条件，那么upsert插入的记录只会包含 更新操作涉及的字段，而不会有更新条件中的字段，因为没法为这种字段定值，mongo索性就不取这些字段）。
如果符合条件的记录中没有要更 新的字段，那么mongo会为其创建该字段，并更新。


