[RocksDB官网](https://rocksdb.org/)  
[RocksDB Github地址](https://github.com/facebook/rocksdb)  
[RocksDB中文网](https://rocksdb.org.cn/doc.html)  
[RocksDB示例](https://github.com/facebook/rocksdb/tree/master/examples)  

[RocksDB Java示例](https://github.com/facebook/rocksdb/tree/master/java/samples/src/main/java)  



A persistent key-value store for fast storage environments  
用于快速存储环境的持久键值存储


RocksDB is an embeddable persistent key-value store for fast storage.  
RocksDB是用于快速存储的可嵌入持久键值存储。


RocksDB: A Persistent Key-Value Store for Flash and RAM Storage  
RocksDB：闪存和RAM存储的持久键值存储


A library that provides an embeddable, persistent key-value store for fast storage.  
一个提供可嵌入的持久键值存储以进行快速存储的库。


RocksDB是使用C++编写的嵌入式kv存储引擎，其键值均允许使用二进制流。由Facebook基于levelDB开发， 提供向后兼容的levelDB API。

RocksDB依靠大量灵活的配置，使之能针对不同的生产环境进行调优，包括直接使用内存，使用Flash，使用硬盘或者HDFS。支持使用不同的压缩算法，并且有一套完整的工具供生产和调试使用。

RocksDB针对Flash存储进行优化，延迟极小。RocksDB使用LSM存储引擎，纯C++编写。Java版本RocksJava正在开发中。参见RocksJavaBasic。




RocksJava是为了给RocksDB构建一个高性能，但是易用的java驱动的工程。

RocksJava由3层构成：
- org.rocksdb包里面的Java类，构成RocksJava API。Java用户只会直接接触到这一层。
- C++的JNI代码，提供Java API和Rock是DB之间的链接。
- C++层的RocksDB本身，并且编译成了一个native库，被JNI层使用。



