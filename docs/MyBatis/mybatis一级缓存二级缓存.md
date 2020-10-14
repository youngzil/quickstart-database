mybatis一级缓存二级缓存


一级缓存：相对于同一个SqlSession而言
一级缓存基于sqlSession默认开启,在操作数据库时需要构造SqlSession对象，在对象中有一个HashMap用于存储缓存数据。不同的SqlSession之间的缓存数据区域是互相不影响的。

　　Mybatis对缓存提供支持，但是在没有配置的默认情况下，它只开启一级缓存，一级缓存只是相对于同一个SqlSession而言。所以在参数和SQL完全一样的情况下，我们使用同一个SqlSession对象调用一个Mapper方法，往往只执行一次SQL，因为使用SelSession第一次查询后，MyBatis会将其放在缓存中，以后再查询的时候，如果没有声明需要刷新，并且缓存没有超时的情况下，SqlSession都会取出当前缓存的数据，而不会再次发送SQL到数据库。

二级缓存：
　　MyBatis的二级缓存是Application级别的缓存，它可以提高对数据库查询的效率，以提高应用的性能。
二级缓存是mapper级别的缓存。使用二级缓存时，多个SqlSession使用同一个Mapper的sql语句去操作数据库，得到的数据会存在二级缓存区域，它同样是使用HashMap进行数据存储。
相比一级缓存SqlSession，二级缓存的范围更大，多个Sqlsession可以共用二级缓存，二级缓存是跨SqlSession的。


二级缓存可以重写并且在mapper文件中配置即可
实现org.apache.ibatis.cache.Cache.java接口即可




参考
https://blog.csdn.net/llziseweiqiu/article/details/79413130
https://blog.csdn.net/u013036274/article/details/55815104
https://www.cnblogs.com/happyflyingpig/p/7739749.html


