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


