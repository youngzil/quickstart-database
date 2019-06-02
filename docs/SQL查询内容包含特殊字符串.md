在like操作还有以下特殊字符：下划线_，百分号%，方括号[]，尖号^。
%：匹配零个及多个任意字符； _：与任意单字符匹配； []：匹配一个范围； [^]：排除一个范围


SQL：
select d.*,d.ROWID from DEMO d where d.user_name like '%ad_%' ESCAPE   'd' 


mybatis：
and  aliasName like concat ('%' , #{1} , '%') ESCAPE '*'



https://new-restart.iteye.com/blog/1416765
https://blog.csdn.net/guyue35/article/details/71295289



