我们要创建一个名为library的数据库，和一个author表，在表中插入zhang3,li4数据。

CREATE DATABASE `library`;

USE `library`;

CREATE TABLE `author` (
  `id` int NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `author` (`id`, `first_name`, `last_name`) VALUES ('1', '3', 'zhang'), ('2', '4', 'li');


代码生成
在这一步中，我们将使用jOOQ的命令行工具生成映射到author表的Java类。 
有关jOOQ代码生成器的更详细信息，请参见：
jOOQ manual pages about setting up the code generator
代码生成的最简单的方法是将jOOQ的3个jar文件和MySQL Connector jar文件复制到一个临时目录（本示例中目录是test-generated）， 然后创建一个如下所示的library.xml（名字随意修改）：

在Windows中，cd到test-generated目录，执行以下命令：
java -classpath jooq-3.11.7.jar;jooq-meta-3.11.7.jar;jooq-codegen-3.11.7.jar;mysql-connector-java-5.1.18-bin.jar;. org.jooq.codegen.GenerationTool library.xml
  
  
UNIX / Linux / Mac中：
java -classpath jooq-3.11.7.jar:jooq-meta-3.11.7.jar:jooq-codegen-3.11.7.jar:mysql-connector-java-5.1.47.jar:. org.jooq.codegen.GenerationTool library.xml


