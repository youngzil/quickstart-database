ROWNUM伪列：每次查询生成的，ROWNUM伪列是Oracle首先进行查询获取到结果集之后在加上去的一个伪列
ROWID伪列：它是物理存在的，ROWID是一种数据类型，它使用基于64为编码的18个字符来唯一标识一条记录物理位置的一个ID，其值并未存储在表中，所以不支持增删改操作



做过Oracle分页的人都知道由于Oracle中没有像MySql中limit函数以及SQLServer中的top关键字等，所以只能通过伪列的方式去满足分页功能


一、ROWNUM伪列

ROWNUM伪列是Oracle首先进行查询获取到结果集之后在加上去的一个伪列，这个伪列对符合条件的结果添加一个从1开始的序列号，先看一个例子：
SELECT ROWNUM,empno,ename,job FROM emp WHERE deptno = 30;

可以看到确实添加了一列从1开始的序列号，那么有了这个伪列，就可以完成好多提取数据的工作，比如提取emp表中前5条数据，SQL如下：
SELECT ROWNUM,empno,ename,job FROM emp WHERE ROWNUM < 6;


二、ROWID伪列

同ROWNUM伪列不同的是，它是物理存在的，ROWID是一种数据类型，它使用基于64为编码的18个字符来唯一标识一条记录物理位置的一个ID，类似于Java中一个对象的哈希码，都是为了唯一标识对应对象的物理位置，需要注意的是ROWID虽然可以在表中进行查询，但是其值并未存储在表中，所以不支持增删改操作








参考
https://blog.csdn.net/yu102655/article/details/52370542
https://blog.csdn.net/haiross/article/details/15338061


