

我自己测试的示例


```
单引号方式
select concat('insert into test_app(app_code, app_name, app_key, app_secret, app_desc)
values(','''',a.app_name,''',','''',a.app_name,''',','''',ak.app_key,''',','''',ak.app_secret,''',','''', a.description,'''',');')
from app_info a ,app_key_secret_info ak where a.app_key = ak.app_key and ak.is_deleted=0 and a.is_deleted=0


双引号方式
select concat("insert into test_app(app_code, app_name, app_key, app_secret, app_desc)
values(","'",a.app_name,"','",a.app_name,"','",ak.app_key,"','",ak.app_secret,"','",a.description,"');") as 'insert SQL'
from app_info a ,app_key_secret_info ak where a.app_key = ak.app_key and ak.is_deleted=0 and a.is_deleted=0

```




方式一示例

```
---sql
select concat('insert into user(user_id,user_name,pswd) values(','''',user_id,''',','''',user_name,''',','''',pswd,'''',');') from user;
 
---result
 
| insert into user(user_id,user_name,pswd) values('haiyoung','Haiyoung','123');                                          
| insert into user(user_id,user_name,pswd) values('haiyoung001','Haiyoung001','123');                                    
| insert into user(user_id,user_name,pswd) values('haiyoung002','Haiyoung002','123');                                    
| insert into user(user_id,user_name,pswd) values('haiyoung003','Haiyoung003','123'); 
```


方式二示例
```
# 通过select 将结果拼接成新的insert into语句，用于迁移使用。
# 参考链接：https://www.jianshu.com/p/0937af88060c

> select CONCAT("INSERT INTO logrotate_config ( `serial_number`, `task_id`, `host`, `path`, `filename`,
`regex`, `compress`, `tran_cycle`, `copytruncate`, `olddir_status`, `olddir`, `rotate`, `other_paramter`,
`create_date`, `username`, `ad_account`, `remark`, `update_date`, `plan_status`, `plan_date`, `result`,
`result_log`, `is_delete`) VALUES(","'",`serial_number`,"','",`serial_number`,"','",
`host`,"','",`path`,"','",`filename`,"','",`regex`,"','", `compress`,"','", `tran_cycle`,"','",
`copytruncate`,"','", `olddir_status`,"','", `olddir`,"','", `rotate`,"','", `other_paramter`,"','",
`create_date`,"','", `username`,"','", `ad_account`,"','", `remark`,"','",
`update_date`,"','",0,"','",`update_date`,"','",  `result`,"','", `result_log`,"','", `is_delete`,"');") as
'insert' from logrotate_config ORDER BY id asc;
# CONCAT() 使用该函数进行字符串拼接操作。每个字符串直接采用""分割，同时将查询的结果放置到对应位置，当作结果values值。

```


[select查询组合生成insert into](https://blog.csdn.net/zksfyz/article/details/116666611)  

[使用select语句，concat函数导出表的insert脚本](https://blog.csdn.net/haiyoung/article/details/78988711)





