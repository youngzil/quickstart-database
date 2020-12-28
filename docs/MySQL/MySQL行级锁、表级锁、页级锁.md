MySQL行级锁、表级锁、页级锁  
http://www.jb51.net/article/50047.htm  
http://www.hollischuang.com/archives/914  
http://www.cnblogs.com/chenqionghe/p/4845693.html  

页级:引擎 BDB。  
表级:引擎 MyISAM ， 理解为锁住整个表，可以同时读，写不行  
行级:引擎 INNODB ， 单独的一行记录加锁  
   
表级，直接锁定整张表，在你锁定期间，其它进程无法对该表进行写操作。如果你是写锁，则其它进程则读也不允许  
行级,，仅对指定的记录进行加锁，这样其它进程还是可以对同一个表中的其它记录进行操作。  
页级，表级锁速度快，但冲突多，行级冲突少，但速度慢。所以取了折衷的页级，一次锁定相邻的一组记录。  
   
   
oracle的行级锁与表级锁  
https://www.cnblogs.com/li1111xin/p/4775240.html  
   
Pessimistic locking（悲观锁）（加的是表级锁）  
一方：查询语句加 for update;另一方：查询语句加 for update;当进行更新语句的时候，另一方不能进行更新操作  
   
Optimistic locking（乐观锁）  
更新语句设置版本号或者时间，在指定版本中更新数据  
   
如果更新多，查询少，用悲观锁；反之，乐观锁  
表级锁，where用的是非主键  
行级锁，where用主键一般是id  
如果用表级锁，其他客户将不能进行查询操作，因此开发中记得用行级锁  
对于MySQL，如果表锁是读锁，可以并发读，如果是写锁，就会阻止读和写  
  

