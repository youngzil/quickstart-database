

[Canal Github](https://github.com/alibaba/canal)  
[]()  
[]()  
[]()  




[超详细Canal入门](https://juejin.cn/post/6859278867635388430)  
[开源数据同步神器——canal](https://www.cnblogs.com/joylee/p/10248106.html)





阿里巴巴 MySQL binlog 增量订阅&消费组件



### MySQL主备复制原理
- MySQL master 将数据变更写入二进制日志( binary log, 其中记录叫做二进制日志事件binary log events，可以通过 show binlog events 进行查看)
- MySQL slave 将 master 的 binary log events 拷贝到它的中继日志(relay log)
- MySQL slave 重放 relay log 中事件，将数据变更反映它自己的数据


### Canal工作原理
- canal 模拟 MySQL slave 的交互协议，伪装自己为 MySQL slave ，向 MySQL master 发送dump 协议
- MySQL master 收到 dump 请求，开始推送 binary log 给 slave (即 canal )
- canal 解析 binary log 对象(原始为 byte 流)








## 多语言

canal 特别设计了 client-server 模式，交互协议使用 protobuf 3.0 , client 端可采用不同语言实现不同的消费逻辑，欢迎大家提交 pull request

- canal java 客户端: [https://github.com/alibaba/canal/wiki/ClientExample](https://github.com/alibaba/canal/wiki/ClientExample)






canal 作为 MySQL binlog 增量获取和解析工具，可将变更记录投递到 MQ 系统中，比如 Kafka/RocketMQ，可以借助于 MQ 的多语言能力

- 参考文档: [Canal Kafka/RocketMQ QuickStart](https://github.com/alibaba/canal/wiki/Canal-Kafka-RocketMQ-QuickStart)






## 相关开源&产品

- [canal 消费端开源项目: Otter](http://github.com/alibaba/otter)
- [阿里巴巴去 Oracle 数据迁移同步工具: yugong](http://github.com/alibaba/yugong)
- [阿里巴巴离线同步开源项目 DataX](https://github.com/alibaba/datax)
- [阿里巴巴数据库连接池开源项目 Druid](https://github.com/alibaba/druid)
- [阿里巴巴实时数据同步工具 DTS](https://www.aliyun.com/product/dts)

