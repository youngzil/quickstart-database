和Spring整合：quickstart-spring-framework-jooq


http://www.jooq.org
https://github.com/jOOQ/jOOQ
http://www.jooq.org/products/
http://www.jooq.org/learn
http://www.jooq.org/javadoc/latest/
https://blog.jooq.org/
http://www.jooq.org/doc/3.11/manual/getting-started/
http://ikaisays.com/2011/11/01/getting-started-with-jooq-a-tutorial/


jOOQ: The easiest way to write SQL in Java
jOOQ是一个基于Java编写SQL的工具包，具有：简单、轻量、函数式编程写SQL等独特优势，非常适合敏捷快速迭代开发。


使用jOOQ，SQL看起来好像是由Java原生支持的，保留SQL原有的简单。

SQL语句：
SELECT AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME, COUNT(*)
    FROM AUTHOR
    JOIN BOOK ON AUTHOR.ID = BOOK.AUTHOR_ID
   WHERE BOOK.LANGUAGE = 'DE'
     AND BOOK.PUBLISHED > DATE '2008-01-01'
GROUP BY AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME
  HAVING COUNT(*) > 5
ORDER BY AUTHOR.LAST_NAME ASC NULLS FIRST
   LIMIT 2
  OFFSET 1
  
Java代码：
create.select(AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME, count())
      .from(AUTHOR)
      .join(BOOK).on(AUTHOR.ID.equal(BOOK.AUTHOR_ID))
      .where(BOOK.LANGUAGE.eq("DE"))
      .and(BOOK.PUBLISHED.gt(date("2008-01-01")))
      .groupBy(AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME)
      .having(count().gt(5))
      .orderBy(AUTHOR.LAST_NAME.asc().nullsFirst())
      .limit(2)
      .offset(1)




参考
https://segmentfault.com/a/1190000010415384


