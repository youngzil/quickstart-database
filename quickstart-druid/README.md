Druid Spring Boot Starter:
查看项目quickstart-spring-boot-druid


druid数据源
https://github.com/alibaba/druid
https://gitee.com/wenshao/druid

https://www.oschina.net/p/druid

Documentation
中文 https://github.com/alibaba/druid/wiki/%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98
English https://github.com/alibaba/druid/wiki/FAQ
Druid Spring Boot Starter https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter


为监控而生的数据库连接池！阿里云DRDS(https://www.aliyun.com/product/drds )、
阿里巴巴TDDL 连接池powered by Druid https://github.com/alibaba/druid/wiki

Druid 详细介绍
Druid是一个JDBC组件，它包括三部分： 
DruidDriver 代理Driver，能够提供基于Filter－Chain模式的插件体系。 
DruidDataSource 高效可管理的数据库连接池。 
SQLParser 


Druid是Java语言中最好的数据库连接池。Druid能够提供强大的监控和扩展功能。DruidDataSource支持的数据库：
理论上说，支持所有有jdbc驱动的数据库。实际测试过的有

数据库	支持状态
mysql	支持，大规模使用
oracle	支持，大规模使用
sqlserver	支持
postgres	支持
db2	支持
h2	支持
derby	支持
sqlite	支持
sybase	支持


Druid可以做什么？ 
1) 可以监控数据库访问性能，Druid内置提供了一个功能强大的StatFilter插件，能够详细统计SQL的执行性能，这对于线上分析数据库访问性能有帮助。 
2) 替换DBCP和C3P0。Druid提供了一个高效、功能强大、可扩展性好的数据库连接池。 
3) 数据库密码加密。直接把数据库密码写在配置文件中，这是不好的行为，容易导致安全问题。DruidDruiver和DruidDataSource都支持PasswordCallback。 
4) SQL执行日志，Druid提供了不同的LogFilter，能够支持Common-Logging、Log4j和JdkLog，你可以按需要选择相应的LogFilter，监控你应用的数据库访问情况。 
扩展JDBC，如果你要对JDBC层有编程的需求，可以通过Druid提供的Filter-Chain机制，很方便编写JDBC层的扩展插件。 



参考
https://blog.csdn.net/yunnysunny/article/details/8657095
https://blog.csdn.net/qq_41376740/article/details/81869261

