select * from aop_api_apprelation where API_CODE like '%esopGroupBoot'

select * from aop_api_open where API_CODE like 'esop%'
select * from aop_api_version where API_CODE like 'esop%'
select * from aop_api_route where API_CODE like 'esop%'


select * from aop_api_apprelation where id='1571312958355';


select * from aop_api_apprelation where id='1571305256478';


select * from aop_api_apprelation where id='1571305653253';

select * from aop_api_apprelation where id=#{id}

select * from aop_api_open where PROTOCOL like '%%'


select * from api_data_sync_notify where PROCESS_STATUS ='X'

select * from aop_api_open where API_CODE  like  'hallNpOpenGsm_%'

select count(*) from aop_api_open where API_CODE  like  'hallNpOpenGsm_%'

select count(*) from aop_api_apprelation  where API_CODE  like  'hallNpOpenGsm_%'

select * from aop_api_open WHERE 1=1 AND status in ('2') and API_CODE  like  'hallNpOpenGsm_%'





------------------------------------------------------------------------------------------------------------------------------------------------
后台表使用

参数裁剪使用：
AOP_API_USER用户表
AOP_AUTH_RELATION权限关系表
AOP_AUTH_ENTITY权限实体表

XSS攻击等参数配置：
AOP_SECURITY_POLICY安全策略表
AOP_SECURITY_RELATION安全策略关系表


AOP_API_APP应用信息表  appcode主键
AOP_API_APPRELATION 应用和服务授权关系表 appcode+apicode主键
AOP_API_IP 应用黑白名单表 appcode+apicode查询多条记录

AOP_API_HYSTRIX 熔断规则表
AOP_API_LIMIT限流表


AOP_API_ROUTE 路由表  根据apicode+apiversion查询多条记录
AOP_API_NODE_GROUP节点分组表 根据groupCode查询出来单条记录

AOP_API_LABEL服务标签表   labelCode主键
AOP_API_LABEL_RELATION 服务标签关系表 根据apiCode查询多个label

服务访问信息表
AOP_API_OPEN 服务开放表，网关给外面用的，apiCode是主键
AOP_API_VERSION 服务版本表，apiCode+apiversion是主键，网关调用的后端服务的参数，节点表+次路径是访问url
AOP_API_PARAMETER 服务参数表，查询多条记录


AOP_API_FILTER 过滤器表

------------------------------------------------------------------------------------------------------------------------------------------------

