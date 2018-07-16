http://blog.itpub.net/27000195/viewspace-2072904/

要有主键，
表明，字段都要对应，否则需要写转换类进行字段、表明等对应


配置数据转换逻辑
如果要迁移的oracle和mysql的表结构不同，比如表名，字段名有差异，字段类型不兼容，需要使用自定义数据转换。如果完全相同那就可以跳过此章节

整个数据流为：DB -> Extractor -> DataTranslator -> Applier -> DB，本程序预留DataTranslator接口，允许外部用户自定义数据处理逻辑，比如：
1.   表名不同
2.   字段名不同
3.   字段类型不同
4.   字段个数不同
5.   运行过程join其他表的数据做计算等



说明
1.DataTranslator目前仅支持java扩展，允许用户完成类实现后，将类源文件放置到conf/translator/目录下，yugong启动后会进行动态编译.
2.DataTranslator目前查找规则会根据表名自动查找，比如需要处理的表为dbname.test_all_one_pk，查找的时候会将test_all_one_pk转化为TestAllOnePk + 固定DataTranslator后缀. (如果当前classpath中存在，优先使用classpath，如果不存在，则到conf/translator中查找该名字的java文件进行动态编译)
3.目前提供了几个样例，可参见解压后的conf/translator/目录
a. YugongExampleOracleDataTranslator  (当前例子，介绍oracle一张表和mysql一张表之间的转换处理)
b. YugongExampleJoinDataTranslator  (介绍oracle多张表和mysql一张表之间的转换处理，oracle中会通过一张表为主表，运行时join查询出其他表数据，合并同步到mysql)
c. YugongExampleTwoDataTranslator (介绍oracle一张表和mysql多张表之间的转换处理，oracle的一张大表数据，可运行时拆分后输出到多张mysql表上)
综上，如果源端和目标端的表存在表名称、字段名、数据类型、字段个数等不同，则迁移需要配置表名称对应的DataTranslator，也就是有多少张存在不同的表相应就需要配置多少个DataTranslator。如果懂java的同学可以通过类的继承（实现同一类型的不同选项）的配置。


自定义数据转换
上文准备的测试环境的源端oracle的表yugong_example_a和目标端mysql的表yugong_example_mysql_a有如下不同的地方：
1. table名不同. oracle中为yugong_example_a，mysql中为yugong_example_mysql_a
2. 字段名字不同. oracle中的name字段，映射到mysql的display_name
3. 字段逻辑处理. mysql的display_name字段数据来源为oracle库的:name+'('alias_name+')'
4. 字段类型不同. oracle中的amount为number类型，映射到mysql的amount为varchar文本型
5. 源库多一个字段. oracle中多了一个alias_name字段
6. 目标库多了一个字段.mysql中多了一个gmt_move字段，(简单的用迁移时的当前时间进行填充)
故，需要根据不同的地方定义DataTranslator.java,下文的YugongExampleADataTranslator.java中配置这里的6项不同之处。


根据DataTranslator目前查找规则会根据表名自动查找
#源库oracle的表为yugong_example_mysql_a，故对应conf/translator/YugongExampleADataTranslator.java
]# vi conf/translator/YugongExampleADataTranslator.java 
//由文件名可定位源表为：yugong_example_mysql_a


查看log
#log的位置,
]# ls /data/yugong/logs/ -l
注：你会发现logs目录下有同步的源库.表命名的文件夹
其中：yugong目录下的table.log是主日志
      源库.表命名的目录下的table.log是此表同步的日志

