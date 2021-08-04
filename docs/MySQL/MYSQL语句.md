

替换字段中某个字符串：吧155IP替换成154
UPDATE op_api_node_group set nodes=REPLACE(nodes,'20.26.39.155','20.26.39.154') where nodes LIKE '%20.26.39.155%';



SELECT
 t.*
FROM
 (
  SELECT
   @id idlist,
   (
    SELECT
     @id := group_concat(param_id SEPARATOR ',')
    FROM
     aop_api_parameter
    WHERE
     find_in_set(parent_id ,@id)
    AND api_code ='hallCommon_IPayTypeCSV_qryPayTypeTest'
    AND version ='1.0.0'
    AND param_scope ='IN'
    AND STATUS = 'U'
   ) > 0 sub
  FROM
   aop_api_parameter,
   (SELECT  @id := '-1') vars
  WHERE
   @id IS NOT NULL
 ) tl,
 aop_api_parameter t
WHERE
 find_in_set(t.param_id, tl.idlist) > 0
AND api_code ='hallCommon_IPayTypeCSV_qryPayTypeTest'
AND version ='1.0.0'
AND param_scope ='IN'
AND STATUS = 'U';



bit数据库查询的显示的是true和false，tinyint显示的是1和0

ALTER TABLE tb_consumer_conf ADD COLUMN regex bit(1) DEFAULT false COMMENT 'topic是不是正则订阅';

ALTER TABLE tb_consumer_conf ADD COLUMN regex tinyint(1) DEFAULT false COMMENT 'topic是不是正则订阅';




create table tb_sasl_user_topic
(
id int UNSIGNED primary key not null auto_increment comment '主键ID',
appName        varchar(40)                               not null comment '应用名称',
clusterId      varchar(20)                               not null comment '集群ID',
username        varchar(40)                              not null comment '用户名',
topic        varchar(40)                          not null comment '主题topic',
consumerGroup        varchar(40)                          not null comment '消费组consumerGroup',
operationType     TINYINT(1)                               null comment '操作类型，3-读权限，4-写权限，5-创建权限。。。',
status         TINYINT(1)                               not null comment '状态',
created_time   timestamp(3) default CURRENT_TIMESTAMP(3) not null comment '创建时间',
updated_time   timestamp(3) default CURRENT_TIMESTAMP(3) not null on update CURRENT_TIMESTAMP(3) comment '更新时间',
constraint uk_app_cluster_user_resource unique (appName, clusterId, username,topic)
) comment '用户和主题的权限关系表' collate = utf8_unicode_ci;



# MySQL一次性添加/删除多个字段
ALTER TABLE tb_sasl_user_topic
ADD topic2 varchar(40) not null comment '主题topic',
ADD consumerGroup2 varchar(40)  not null comment '消费组consumerGroup',
ADD operationType2  TINYINT(1) null comment '操作类型，3-读权限，4-写权限，5-创建权限。。。',
ADD status2 TINYINT(1)  not null comment '状态';

ALTER TABLE tb_sasl_user_topic
drop topic2,
drop consumerGroup2,
drop operationType2,
drop status2;


# Mysql修改unique key：先drop掉，然后在创建。

# 需要注意的是drop时关键字是“index”，而创建时关键词是“unique key”

alter table tb_sasl_user_topic drop index uk_app_cluster_user_resource;

alter table tb_sasl_user_topic add unique key uk_app_cluster_user_resource(appName, clusterId, username,topic);


# 修改原字段名称及类型
ALTER TABLE tb_sasl_user_topic CHANGE operationType operationType     char(1)                               null comment '操作类型';




alter add命令用来增加表的字段。

alter add命令格式：alter table 表名 add字段 类型 其他;

例如，在表MyClass中添加了一个字段passtest，类型为int(4)，默认值为0：
mysql> alter table MyClass add passtest int(4) default '0';

1) 加索引
   mysql> alter table 表名 add index 索引名 (字段名1[，字段名2 …]);

例子： mysql> alter table employee add index emp_name (name);

2) 加主关键字的索引
   mysql> alter table 表名 add primary key (字段名);

例子： mysql> alter table employee add primary key(id);

3) 加唯一限制条件的索引
   mysql> alter table 表名 add unique 索引名 (字段名);

例子： mysql> alter table employee add unique emp_name2(cardnumber);

4) 删除某个索引
   mysql> alter table 表名 drop index 索引名;

例子： mysql>alter table employee drop index emp_name;

5) 增加字段
   mysql> ALTER TABLE table_name ADD field_name field_type;

6) 修改原字段名称及类型
   mysql> ALTER TABLE table_name CHANGE old_field_name new_field_name field_type;

7) 删除字段
   MySQL ALTER TABLE table_name DROP field_name;
   ————————————————
   版权声明：本文为CSDN博主「u013063153」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
   原文链接：https://blog.csdn.net/u013063153/article/details/53304325
   



