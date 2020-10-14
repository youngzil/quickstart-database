1、基本语法
登录、
2、查看数据库所有用户、表、视图、序列、存储函数和过程、索引、同义词、约束条件

3、

4、

5、

6、


---------------------------------------------------------------------------------------------------------------------
查看数据库所有用户、表、视图、序列、存储函数和过程、索引、同义词、约束条件

在Oracle数据库中的大部分数据库对象，如表、视图、物化视图、序列、函数、存储过程、包、同义词等等，数据库管理员都可以根据实际情况为他们定义同义词。


1、用户
2、表、视图、索引
表：物理存在的
视图：虚拟的，映射
物化视图：物理存在的
3、同义词：数据库所有对象都可以设置同义词，如用户、表等
4、序列、存储函数和过程、包
5、索引、约束条件





同义词
https://www.cnblogs.com/kerrycode/archive/2012/12/19/2824963.html

物化视图
https://blog.csdn.net/joshua_peng1985/article/details/6213593

Oracle—包和包体
https://blog.csdn.net/u013249984/article/details/77651549


查看用户所拥有的表： 
SELECT TABLE_NAME FROM USER_TABLES; 
用户可存取的表： 
SELECT TABLE_NAME FROM ALL_TABLES; 
数据库中所有表： 
SELECT TABLE_NAME FROM DBA_TABLES;
或者
select   *   from   tab;


select * from user_tables;
select * from user_views;
select * from user_sequences;
select * from user_triggers;


视图(view)，也称虚表, 不占用物理空间，这个也是相对概念，因为视图本身的定义语句还是要存储在数据字典里的。视图只有逻辑定义。每次使用的时候, 只是重新执行SQL.
视图是从一个或多个实际表中获得的，这些表的数据存放在数据库中。那些用于产生视图的表叫做该视图的基表。一个视图也可以从另一个视图中产生。
视图的定义存在数据库中，与此定义相关的数据并没有再存一份于数据库中。通过视图看到的数据存放在基表中。
视图看上去非常象数据库的物理表，对它的操作同任何其它的表一样。当通过视图修改数据时，实际上是在改变基表中的数据；相反地，基表数据的改变也会自动反映在由基表产生的视图中。由于逻辑上的原因，有些Oracle视图可以修改对应的基表，有些则不能（仅仅能查询）。
select * from user_views;

查询用户下的所有表
select  table_name from all_tables where owner = upper('TANAME');
select * from user_tables;
select * from user_sequences;
select * from user_triggers;

查询用户下的所有表
select  table_name from all_tables where owner = upper('TANAME');
查看当前用户的缺省表空间
　　SQL>select username,default_tablespace from user_users;
　　查看当前用户的角色
　　SQL>select * from user_role_privs;
　　查看当前用户的系统权限和表级权限
　　SQL>select * from user_sys_privs;
　　SQL>select * from user_tab_privs;
　　查看用户下所有的表
　　SQL>select * from user_tables;
1、用户
　　查看当前用户的缺省表空间
　　SQL>select username,default_tablespace from user_users;
　　查看当前用户的角色
　　SQL>select * from user_role_privs;
　　查看当前用户的系统权限和表级权限
　　SQL>select * from user_sys_privs;
　　SQL>select * from user_tab_privs;
　　显示当前会话所具有的权限
　　SQL>select * from session_privs;
　　显示指定用户所具有的系统权限
　　SQL>select * from dba_sys_privs where grantee=’GAME’;
2、表
　　查看用户下所有的表
　　SQL>select * from user_tables;
　　查看名称包含log字符的表
　　SQL>select object_name,object_id from user_objects
　　where instr(object_name,’LOG’)>0;
　　查看某表的创建时间
　　SQL>select object_name,created from user_objects where object_name=upper(‘&table_name’);
　　查看某表的大小
　　SQL>select sum(bytes)/(1024*1024) as “size(M)” from user_segments
　　where segment_name=upper(‘&table_name’);
　　查看放在ORACLE的内存区里的表
　　SQL>select table_name,cache from user_tables where instr(cache,’Y')>0;
3、索引
　　查看索引个数和类别
　　SQL>select index_name,index_type,table_name from user_indexes order by table_name;
　　查看索引被索引的字段
　　SQL>select * from user_ind_columns where index_name=upper(‘&index_name’);
　　查看索引的大小
　　SQL>select sum(bytes)/(1024*1024) as “size(M)” from user_segments
　　where segment_name=upper(‘&index_name’);
4、序列号
　　查看序列号，last_number是当前值
　　SQL>select * from user_sequences;
5、视图
　　查看视图的名称
　　SQL>select view_name from user_views;
　　查看创建视图的select语句
　　SQL>set view_name,text_length from user_views;
　　SQL>set long 2000; 说明：可以根据视图的text_length值设定set long 的大小
　　SQL>select text from user_views where view_name=upper(‘&view_name’);
6、同义词
　　查看同义词的名称
　　SQL>select * from user_synonyms;
7、约束条件
　　查看某表的约束条件
　　SQL>select constraint_name, constraint_type,search_condition, r_constraint_name
　　from user_constraints where table_name = upper(‘&table_name’);
　　SQL>select c.constraint_name,c.constraint_type,cc.column_name
　　from user_constraints c,user_cons_columns cc
　　where c.owner = upper(‘&table_owner’) and c.table_name = upper(‘&table_name’)
　　and c.owner = cc.owner and c.constraint_name = cc.constraint_name
　　order by cc.position;
8、存储函数和过程
　　查看函数和过程的状态
　　SQL>select object_name,status from user_objects where object_type=’FUNCTION’;
　　SQL>select object_name,status from user_objects where object_type=’PROCEDURE’;
　　查看函数和过程的源代码
　　SQL>select text from all_source where owner=user and name=upper(‘&plsql_name’);

9、通过命令查询：
 Select object_name From user_objects Where object_type='VIEW';  --查看所有视图  
Select object_name From user_objects Where object_type='TABLE'; --查看所有表
select object_type  from user_objects where object_name='xxxxx'--跟住名字查看数据类型



---------------------------------------------------------------------------------------------------------------------
基本语法

登录
登录主机，普通登录，su到oracle用户，用sqlplus
oracle/admin123
sqlplus test/test123@188.102.0.117:1521/msp_srv

select * from sec_approve t where application_date > to_date('2019-5-28','yyyy-mm-dd') ;



desc table_name;





---------------------------------------------------------------------------------------------------------------------




---------------------------------------------------------------------------------------------------------------------






---------------------------------------------------------------------------------------------------------------------







---------------------------------------------------------------------------------------------------------------------








---------------------------------------------------------------------------------------------------------------------








---------------------------------------------------------------------------------------------------------------------







---------------------------------------------------------------------------------------------------------------------



