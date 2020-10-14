




MySQL事务
https://mp.weixin.qq.com/s/mZxAn7qRQ8EycVOcdql3hQ




MySQL 如何优化cpu消耗
https://www.cnblogs.com/YangJiaXin/p/10933458.html


---------------------------------------------------------------------------------------------------------------------
## MySQL事务实现原理


1、redo log（重做日志）(原子性)
2、undo log（回滚日志）(持久性)
3、共享锁(shared lock),又叫做"读锁"；
4、排他锁(exclusive lock),又叫做"写锁"；
5、MVCC (MultiVersion Concurrency Control) 叫做多版本并发控制。




redo log叫做重做日志，是用来实现事务的持久性。该日志文件由两部分组成：重做日志缓冲（redo log buffer）以及重做日志文件（redo log）,前者是在内存中，后者在磁盘中。当事务提交之后会把所有修改信息都会存到该日志中。


undo log 叫做回滚日志，用于记录数据被修改前的信息。他正好跟前面所说的重做日志所记录的相反，重做日志记录数据被修改后的信息。undo log主要记录的是数据的逻辑变化，为了在发生错误时回滚之前的操作，需要将之前的操作都记录下来，然后在发生错误时才可以回滚。

undo log是用来回滚数据的用于保障未提交事务的原子性。

共享锁(shared lock),又叫做"读锁"；

排他锁(exclusive lock),又叫做"写锁"；

MVCC (MultiVersion Concurrency Control) 叫做多版本并发控制。

MVCC在mysql中的实现依赖的是undo log与read view：  
- undo log :undo log 中记录某行数据的多个版本的数据。  
- read view :用来判断当前版本数据的可见性。  

MVCC主要作用于事务性的，有行锁控制的数据库模型。




前面讲的重做日志，回滚日志以及锁技术就是实现事务的基础。
- 事务的原子性是通过 undo log 来实现的
- 事务的持久性性是通过 redo log 来实现的
- 事务的隔离性是通过 (读写锁+MVCC)来实现的

而事务的终极大 boss 一致性是通过原子性，持久性，隔离性来实现的。

原子性，持久性，隔离性折腾半天的目的也是为了保障数据的一致性！

总之，ACID只是个概念，事务最终目的是要保障数据的可靠性，一致性。




一致性的实现  

数据库总是从一个一致性的状态转移到另一个一致性的状态.




实现事务采取了哪些技术以及思想？
- 原子性：使用 undo log ，从而达到回滚；
- 持久性：使用 redo log，从而达到故障后恢复；
- 隔离性：使用锁以及MVCC,运用的优化思想有读写分离，读读并行，读写并行；
- 一致性：通过回滚，以及恢复，和在并发环境下的隔离做到一致性。








[Mysql的事务实现原理](https://zhuanlan.zhihu.com/p/117452178)  
[深入学习MySQL事务：ACID特性的实现原理](https://www.cnblogs.com/kismetv/p/10331633.html)  
[MySQL 中事务的实现](https://draveness.me/mysql-transaction/)  
[MySql数据库InnoDB引擎ACID的实现原理](https://my.oschina.net/leitingweb/blog/3191463)  


