oracle开发中序列的使用
　　序列是一数据库对象，利用它可生成唯一的整数。一般使用序列自动地生成主码值。一个序列的值是由特别的Oracle程序自动生成，因而序列避免了在运用层实现序列而引起的性能瓶颈。Oracle序列允许同时生成多个序列号，而每一个序列号是唯一的。 当一个序列号生成时，序列是递增，独立于事务的提交或回滚。容许设计缺省序列，不需指定任何子句。该序列为上升序列，由1开始，增量为1，没有上限。
    1） 建立序列命令
    CREATE SEQUENCE [user.]sequence_name
    [increment by n]
    [start with n]
    [maxvalue n | nomaxvalue]
    [minvalue n | nominvalue];
    INCREMENT BY： 指定序列号之间的间隔，该值可为正的或负的整数，但不可为0。序列为升序。忽略该子句时，缺省值为1。
    START WITH：指定生成的第一个序列号。在升序时，序列可从比最小值大的值开始，缺省值为序列的最小值。对于降序，序列可由比最大值小的值开始，缺省值为序列的最大值。
    MAXVALUE：指定序列可生成的最大值。
    NOMAXVALUE：为升序指定最大值为1027，为降序指定最大值为-1。
    MINVALUE：指定序列的最小值。
    NOMINVALUE：为升序指定最小值为1。为降序指定最小值为-1026。

    2） 更改序列命令
    ALTERSEQUENCE [user.]sequence_name
    [INCREMENT BY n]
    [MAXVALUE n| NOMAXVALUE ]
    [MINVALUE n | NOMINVALUE]；
    修改序列可以：
    ? 修改未来序列值的增量。
    ? 设置或撤消最小值或最大值。
    ? 转变缓冲序列的数目。
    ? 指定序列号是否是有序。

    3） 删除序列命令
    DROP SEQUENCE [user.]sequence_name；
    从数据库中删除一序列。

   树立一个序列号的语句：
   CREATE SEQUENCE EXAM_NO_SEQ
      START WITH 1484
      MAXVALUE 9999999999
      MINVALUE 1
      CYCLE
      CACHE 20
      NOORDER;


序列的创建
create sequence seq_newsId
start with 1
increment by 1
minvalue 1
maxvalue 9999999999999999
cache 20
cycle;

CREATE SEQUENCE seq_itv_collection
            INCREMENT BY 1  -- 每次加几个  
            START WITH 1399       -- 从1开始计数  
            NOMAXVALUE        -- 不设置最大值  
            NOCYCLE               -- 一直累加，不循环  
            CACHE 10;

得到序列的SQL语句
select seq_newsid.nextval from sys.dual;

删除序列的SQL
   DROP SEQUENCE seq_newsId；

   
   