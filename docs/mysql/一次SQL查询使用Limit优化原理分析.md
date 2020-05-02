操作：查询条件放到子查询中，子查询只查主键ID，然后使用子查询中确定的主键关联查询其他的属性字段；

原理：减少回表操作；


我们看一下select * from test where val=4 limit 300000,5;的查询过程：
1、查询到索引叶子节点数据。
2、根据叶子节点上的主键值去聚簇索引上查询需要的全部字段值。

需要查询300005次索引节点，查询300005次聚簇索引的数据，最后再将结果过滤掉前300000条，取出最后5条。
MySQL耗费了大量随机I/O在查询聚簇索引的数据上，而有300000次随机I/O查询到的数据是不会出现在结果集当中的。



为了证实select * from test where val=4 limit 300000,5是扫描300005个索引节点和300005个聚簇索引上的数据节点

我只能通过间接的方式来证实：
InnoDB中有buffer pool。里面存有最近访问过的数据页，包括数据页和索引页。所以我们需要运行两个sql，来比较buffer pool中的数据页的数量。

mysql> select * from test where val=4 limit 300000,5;

mysql> select index_name,count(*) from information_schema.INNODB_BUFFER_PAGE where INDEX_NAME in('val','primary') and TABLE_NAME like '%test%' group by index_name;



为了防止上次试验的影响，我们需要清空buffer pool，重启mysql。
select * from test a inner join (select id from test where val=4 limit 300000,5) ;

mysql> select index_name,count(*) from information_schema.INNODB_BUFFER_PAGE where INDEX_NAME in('val','primary') and TABLE_NAME like '%test%' group by index_name;



我们可以看明显的看出两者的差别：第一个sql加载了4098个数据页到buffer pool，而第二个sql只加载了5个数据页到buffer pool。
符合我们的预测。也证实了为什么第一个sql会慢：读取大量的无用数据行（300000），最后却抛弃掉。
而且这会造成一个问题：加载了很多热点不是很高的数据页到buffer pool，会造成buffer pool的污染，占用buffer pool的空间。 

作者：Muscleape
链接：https://www.jianshu.com/p/0768ebc4e28d
来源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。



