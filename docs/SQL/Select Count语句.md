COUNT(列名)、COUNT(常量)和COUNT(*)之间的区别

COUNT(常量) 和 COUNT(*)表示的是直接查询符合条件的数据库表的行数：常量 是一个固定值，肯定不为NULL。*可以理解为查询整行
COUNT(列名)表示的是查询符合条件的列的值不为NULL的行数。

建议使用COUNT(*)！因为这个是SQL92定义的标准统计行数的语法


在InnoDB中COUNT(*)和COUNT(1)实现上没有区别，而且效率一样，
但是COUNT(字段)需要进行字段的非NULL判断，所以效率会低一些。
因为COUNT(*)是SQL92定义的标准统计行数的语法，并且效率高，所以请直接使用COUNT(*)查询表的行数！



关于COUNT函数
1、COUNT(expr) ，返回SELECT语句检索的行中expr的值不为NULL的数量。结果是一个BIGINT值。
2、如果查询结果没有命中任何记录，则返回0
3、但是，值得注意的是，COUNT(*) 的统计结果中，会包含值为NULL的行数。


即以下表记录
create table #bla(id int,id2 int)
insert #bla values(null,null)
insert #bla values(1,null)
insert #bla values(null,1)
insert #bla values(1,null)
insert #bla values(null,1)
insert #bla values(1,null)
insert #bla values(null,null)
使用语句count(*),count(id),count(id2)查询结果如下：
select count(*),count(id),count(id2)
from #bla
results 7 3 2


除了COUNT(id)和COUNT(*)以外，还可以使用COUNT(常量)（如COUNT(1)）来统计行数，那么这三条SQL语句有什么区别呢？到底哪种效率更高呢？为什么《阿里巴巴Java开发手册》中强制要求不让使用 COUNT(列名)或 COUNT(常量)来替代 COUNT(*)呢？


COUNT(列名)、COUNT(常量)和COUNT(*)之间的区别
COUNT(常量) 和 COUNT(*)表示的是直接查询符合条件的数据库表的行数：常量 是一个固定值，肯定不为NULL。*可以理解为查询整行
COUNT(列名)表示的是查询符合条件的列的值不为NULL的行数。



前面我们提到过COUNT(expr)用于做行数统计，统计的是expr不为NULL的行数，那么COUNT(列名)、 COUNT(常量) 和 COUNT(*)这三种语法中，expr分别是列名、 常量 和 *。
那么列名、 常量 和 *这三个条件中，常量 是一个固定值，肯定不为NULL。*可以理解为查询整行，所以肯定也不为NULL，那么就只有列名的查询结果有可能是NULL了。

所以， COUNT(常量) 和 COUNT(*)表示的是直接查询符合条件的数据库表的行数。而COUNT(列名)表示的是查询符合条件的列的值不为NULL的行数。


除了查询得到结果集有区别之外，COUNT(*)相比COUNT(常量) 和 COUNT(列名)来讲，COUNT(*)是SQL92定义的标准统计行数的语法，因为他是标准语法，所以MySQL数据库对他进行过很多优化。
SQL92，是数据库的一个ANSI/ISO标准。它定义了一种语言（SQL）以及数据库的行为（事务、隔离级别等）。



MySQL中比较常用的执行引擎就是InnoDB和MyISAM。
MyISAM和InnoDB有很多区别，其中有一个关键的区别和我们接下来要介绍的COUNT(*)有关，那就是MyISAM不支持事务，MyISAM中的锁是表级锁；而InnoDB支持事务，并且支持行级锁。


那既然COUNT(*)和COUNT(1)一样，建议用哪个呢？
建议使用COUNT(*)！因为这个是SQL92定义的标准统计行数的语法，而且本文只是基于MySQL做了分析，关于Oracle中的这个问题，也是众说纷纭的呢。

COUNT(字段)
最后，就是我们一直还没提到的COUNT(字段)，他的查询就比较简单粗暴了，就是进行全表扫描，然后判断指定字段的值是不是为NULL，不为NULL则累加。
相比COUNT(*)，COUNT(字段)多了一个步骤就是判断所查询的字段是否为NULL，所以他的性能要比COUNT(*)慢。




参考
https://developer.aliyun.com/article/756450
https://mp.weixin.qq.com/s/4atIDmiucqvkw0b2_u8itA



