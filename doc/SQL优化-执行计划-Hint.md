SQL优化：执行计划和Hint


查看执行计划：
mysql：EXPLAIN 
oracle：EXPLAIN PLAN FOR 



oracle访问数据的存取方法:
1） 全表扫描（Full Table Scans， FTS）：
2） 通过ROWID的表存取（Table Access by ROWID或rowid lookup）
3）索引扫描（Index Scan或index lookup）有4种类型的索引扫描：
　（1） 索引唯一扫描（index unique scan）
　（2） 索引范围扫描（index range scan）
       在非唯一索引上都使用索引范围扫描。使用index rang scan的3种情况：
　　  （a） 在唯一索引列上使用了range操作符（> < <> >= <= between）
　　  （b） 在组合索引上，只使用部分列进行查询，导致查询出多行
　　  （c） 对非唯一索引列上进行的任何查询。　　
　（3） 索引全扫描（index full scan）
　（4） 索引快速扫描（index fast full scan）


表之间的连接：
1，排序 - - 合并连接（Sort Merge Join， SMJ）
2，嵌套循环（Nested Loops， NL）
3，哈希连接（Hash Join， HJ）
另外，笛卡儿乘积（Cartesian Product）：关联查询动态SQL丢失Where条件 或者 忘记写Where条件


1、全表扫描：使用FTS的前提条件：在较大的表上不建议使用全表扫描，除非取出数据的比较多，超过总量的5% —— 10%，或你想使用并行查询功能时。
2、通过ROWID的表存取：行的ROWID指出了该行所在的数据文件、数据块以及行在该块中的位置，所以通过ROWID来存取数据可以快速定位到目标数据上，是Oracle存取单行数据的最快方法。
3、索引扫描：对于索引，由于经常使用，绝大多数都已经CACHE到内存中，所以第1步的 I/O经常是逻辑I/O，即数据可以从内存中得到，第二步数据不可能全在内存中，所以其I/O很有可能是物理I/O，这 是一个机械操作，相对逻辑I/O来说，是极其费时间的，所以如果多大表进行索引扫描，取出的数据如果大于总量的5% —— 10%，使用索引扫描会效率下降很多。
	索引扫描可以由2步组成：
　　（1） 扫描索引得到对应的rowid值。 
　　（2） 通过找到的rowid从表中读出具体的数据。
  根据索引的类型与where限制条件的不同，有4种类型的索引扫描：
　　3-1、索引唯一扫描（index unique scan）：通过唯一索引查找一个数值经常返回单个ROWID.如果存在UNIQUE 或PRIMARY KEY 约束（它保证了语句只存取单行）的话，Oracle经常实现唯一性扫描。
　　3-2、索引范围扫描（index range scan）：使用一个索引存取多行数据，在唯一索引上使用索引范围扫描的典型情况下是在谓词（where限制条件）中使用了范围操作符（如>、<、<>、>=、<=、between）
　　3-3、索引全扫描（index full scan）：与全表扫描对应，也有相应的全索引扫描。而且此时查询出的数据都必须从索引中可以直接得到。
　　3-4、索引快速扫描（index fast full scan）：扫描索引中的所有的数据块，与 index full scan很类似，但是一个显著的区别就是它不对查询出的数据进行排序，即数据不是以排序顺序被返回。在这种存取方法中，可以使用多块读功能，也可以使用并行读入，以便获得最大吞吐量与缩短执行时间。



可选择性（selectivity）：比较一下列中唯一键的数量和表中的行数，就可以判断该列的可选择性。 如果该列的“唯一键的数量/表中的行数”的比值越接近1，则该列的可选择性越高，该列就越适合创建索引，同样索引的可选择性也越高。在可选择性高的列上进 行查询时，返回的数据就较少，比较适合使用索引查询。



Hint的弊端
Hint是比较"暴力"的一种解决方式，不是很优雅。需要开发人员手工修改代码。
Hint不会去适应新的变化。比如数据结构、数据规模发生了重大变化，但使用Hint的语句是感知变化并产生更优的执行计划。
Hint随着数据库版本的变化，可能会有一些差异、甚至废弃的情况。此时，语句本身是无感知的，必须人工测试并修正。



SQL执行计划：
https://blog.csdn.net/heng_yan/article/details/78324176
https://www.cnblogs.com/jianggc/articles/2029854.html
https://blog.csdn.net/asfanj/article/category/7283070


Hint
https://blog.csdn.net/u012232730/article/details/73604176
https://blog.csdn.net/tmchongye/article/details/64389420



