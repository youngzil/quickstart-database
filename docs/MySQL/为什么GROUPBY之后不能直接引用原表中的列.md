为什么GROUP BY 之后不能直接引用原表中的列.md

总结
　　1、SQL 严格区分层级，包括谓词逻辑中的层级（EXISTS），也包括集合论中的层级（GROUP BY）；
　　2、有了层级区分，那么适用于个体上的属性就不适用于团体了，这也就是为什么聚合查询的 SELECT 子句中不能直接引用原表中的列的原因；
　　3、一般来说，单元素集合的属性和其唯一元素的属性是一样的。这种只包含一个元素的集合让人觉得似乎没有必要特意地当成集合来看待，但是为了保持理论的完整性，我们还是要严格区分元素和单元素集合；



GROUP BY 后 SELECT 列的限制

标准 SQL 规定，在对表进行聚合查询的时候，只能在 SELECT 子句中写下面 3 种内容：通过 GROUP BY 子句指定的聚合键、聚合函数（SUM 、AVG 等）、常量。



SQL 模式
MySQL 服务器可以在不同的 SQL 模式下运行，并且可以针对不同的客户端以不同的方式应用这些模式，具体取决于 sql_mode 系统变量的值。 
DBA 可以设置全局SQL模式以匹配站点服务器操作要求，并且每个应用程序可以将其会话 SQL 模式设置为其自己的要求。
模式会影响 MySQL 支持的 SQL 语法以及它执行的 数据验证检查，
SQL 模式主要分两类：语法支持类和数据检查类，常用的如下

语法支持类

数据检查类



当我们没有修改配置文件的情况下，MySQL 是有自己的默认模式的；版本不同，默认模式也不同
-- 查看 MySQL 版本
SELECT VERSION();

-- 查看 sql_mode
SELECT @@sql_mode;

我们可以看到，5.7.21 的默认模式包含：
ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION



为什么聚合后不能再引用原表中的列
　　很多人都知道聚合查询的限制，但是很少有人能正确地理解为什么会有这样的约束。
表 tbl_student_class 中的 cname 存储的是每位学生的班级信息，但需要注意的是，这里的 cname 只是每个学生的属性，并不是小组的属性，
而 GROUP BY 又是聚合操作，操作的对象就是由多个学生组成的小组，因此，小组的属性只能是平均或者总和等统计性质的属性，




参考
https://www.cnblogs.com/youzhibing/p/11516154.html



