INSERT INTO ""("ABILITY_ID", "ABILITY_NAME", "ABILITY_CODE", "ABILITY_SKETCH", "ABILITY_SORT", "ABILITY_URL", "ABILITY_LOGO", "QUOTA_NUMS", "FLOW_NUMS", "ESB_DOMAIN_ID", "ABILITY_FORMAT", "ABILITY_STATUS", "REMARKS", "CREATOR", "CREATE_TIME", "UPDATOR", "UPDATE_TIME", "CYCLE_TYPE", "QUOTA_TYPE", "ABILITY_TYPE", "ABILITY_SHORT_ADDR") VALUES ('100000000408', 'hallOpenGsm_ICertCheckCSV_queryCertTypeList', 'hallOpenGsm_ICertCheckCSV_queryCertTypeList', NULL, '1', 'http://20.26.37.180:20150/rest/hallOpenGsm_ICertCheckCSV_queryCertTypeList', '1', NULL, NULL, '2,3', 'json', '3', NULL, '2000001', TO_DATE('2019-05-06 11:17:21', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL, NULL, '1', NULL);

select count(*) from AOP_ABILITY_BASEINFO where ABILITY_CODE='hallOpenGsm_IBusiSceneCSV_qryBusiScene'

select * from AOP_ABILITY_BASEINFO where ABILITY_ID in (100000000808,100000000408)

delete from AOP_ABILITY_BASEINFO where ABILITY_ID in (100000000408)


select * from AOP_ABILITY_BASEINFO where ABILITY_ID>=100000000800 and ABILITY_ID<=100000000809
select * from AOP_ABILITY_PROTOCOL where ABILITY_ID>=100000000800 and ABILITY_ID<=100000000809

select * from AOP_ABILITY_BASEINFO

dbpath=/app/memdb/mongodb/data/node3/conf
logpath=/app/memdb/mongodb/log/node3/mongo_config.log
mongod --repair --dbpath /app/memdb/mongodb/data/node3/conf / --repairpath /app/memdb/mongodb/log/node3/mongo_config.log


select  * from AIOSP_CFG.AOP_APPINFO

select * from aop_api_app where app_code='GW_CUST_PC01'

select * from AOP_ABILITY_BASEINFO where ABILITY_ID in (100000000800,100000000408)


select * from AOP_ABILITY_BASEINFO where ABILITY_url like 'http://aifgatewaytest.zj.chinamobile.com%' or ABILITY_ID in (100000000808,100000000408)


select * from AOP_ABILITY_PROTOCOL where CALL_PROTOCOL='7' and ABILITY_ID in (100000000800,100000000801,100000000802,100000000803,100000000804,100000000805,100000000806,100000000807,100000000808,100000000809)



select * from AOP_ABILITY_EXTINFO where ability_id=100000000801

select * from AOP_ABILITY_EXTINFO where ABILITY_ID in (100000000808,100000000408)


select * from AOP_ABILITY_BASEINFO where ABILITY_ID>=100000000800 and ABILITY_ID<=100000000809

------------------------------------------------------------------------------------------------------------------------------------------------

-- 报文大小限制修改
select * from ospgateway.AOP_PARA_DETAIL where PARA_TYPE='AOP_REQUEST';
update  ospgateway.AOP_PARA_DETAIL set PARA1='115857600' where PARA_TYPE='AOP_REQUEST' and PARA_CODE='MAX_LENGTH';

------------------------------------------------------------------------------------------------------------------------------------------------
-- 跳过下沙箱测试
update ospgateway.AOP_APP_IPMLINFO set EXT_A = 'T' where APP_ID = 501080;
select * from AOP_APP_IPMLINFO where APP_ID = 501080;


------------------------------------------------------------------------------------------------------------------------------------------------
-- 业务大厅网关前台app发布秘钥变动，可以直接插表解决
-- 1、使用序列表cfg_id_generator查询出使用的序列
select * from cfg_id_generator where table_name like 'AOP_APP_IPMLINFO';
select ospgateway.AOP_APP_IPMLINFO_SEQ.NEXTVAL from dual;

-- 2、应用能力关联表，aop_app_ipmlinfo，EXT_A=T，DISABLED_DATE写个大的失效时间，IMPL_ID是主键
select  t.*,t.rowid from AIOSP_CFG.AOP_APP_IPMLINFO t;

-- 查询能力id
SELECT * from aiosp_cfg.AOP_ABILITY_BASEINFO t where t.ability_name='hallCommon_IFileDealCSV_base64PhotoUploadToken';


select * from ospgateway.AOP_ABILITY_BASEINFO


------------------------------------------------------------------------------------------------------------------------------------------------
