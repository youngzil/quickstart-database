Mybatis和hibernate不同，它不完全是一个ORM框架，因为MyBatis需要程序员自己编写Sql语句，不过mybatis可以通过XML或注解方式灵活配置要运行的sql语句，并将java对象和sql语句映射生成最终执行的sql，最后将sql执行的结果再映射生成java对象。

区别：
1、sql语句：Mybatis直接编写原生态sql，可严格控制sql执行性能，灵活度高，跟数据库相关性高，移植性低
          Hibernate是HQL，灵活性低，数据库无关高，移植性高
          
  


