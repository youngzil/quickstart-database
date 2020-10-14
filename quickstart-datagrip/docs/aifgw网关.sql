
select * from aop_page_open where PAGE_CODE='pcHome'


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


ALTER TABLE AOP_API_VERSION ADD COLUMN INTERFACE_TYPE VARCHAR(2) DEFAULT 'B' COMMENT '接口类型';

ALTER TABLE AOP_API_VERSION DROP INTERFACE_TYPE;

ALTER TABLE AOP_API_VERSION ADD COLUMN INTERFACE_TYPE VARCHAR(2) COMMENT '接口类型';
ALTER TABLE AOP_API_VERSION ALTER COLUMN INTERFACE_TYPE SET DEFAULT 'B';

# 修改字段默认值语法：
alter table AOP_API_VERSION alter column INTERFACE_TYPE drop default; #(若本身存在默认值，则先删除)
alter table AOP_API_VERSION alter column INTERFACE_TYPE set default 'B'; #(若本身不存在则可以直接设定)

INSERT INTO aop_api_version (ID, API_CODE, version_NAME, version_PATH, version_METHOD, STATUS, TIME_OUT, RETRY_COUNT, version, version_DESC, CREATE_DATE, ROUTE_STRATEGY, INVOLVED_TYPE, operation, BODY_SIZE) VALUES ('1111112', 'marketAccept_IMarketAcceptCSV_qryOtherMktInfo', '默认版本', '/marketAccept_IMarketAcceptCSV_qryOtherMktInfo', 'POST', '2', 60000, 3, '1.0.0', '营销案', '2019-10-30 15:53:14', 'polling', null, null, null);

#某个字段设置了默认值，若是插入的时候，没有这个字段，就填充默认值，若是有这个字段，但是空，插进去的就是空，不是默认值


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

