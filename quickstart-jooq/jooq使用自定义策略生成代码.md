自定义代码生成策略：MyStratege
jooq代码生成相关配置：src/main/resources/jooqConfig.xml
使用mvn clean install 进行编译，则可生成代码！如果引用不到本项目的jar，可以先把jooq-codegen-maven插件注释，把本项目install之后，再放开再执行package

jooq是通过连接上面配置的数据库找到数据库的表生成相应的java文件。 
我这里做演示只设了一张表，如果有多张表则每张表都会有相应的代码，其中pojos中的类就是一般我们用到的实体类。 
还有一点就是执行mvn clean install的时候如果出不来自动生成的代码就加上-Djooq，完整执行指令为mvn clean install -Djooq。


pom新增
	<build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<configuration>
					<source>1.8</source>
					<target>1.8</target>
				</configuration>
			</plugin>

			<plugin>
				<groupId>org.jooq</groupId>
				<artifactId>jooq-codegen-maven</artifactId>
				<executions>
					<execution>
						<goals>
							<goal>generate</goal>
						</goals>
					</execution>
				</executions>
				<dependencies>
					<dependency>
						<groupId>mysql</groupId>
						<artifactId>mysql-connector-java</artifactId>
						<version>${mysql.version}</version>
					</dependency>
					<dependency>
						<groupId>${project.groupId}</groupId>
						<artifactId>${project.artifactId}</artifactId>
						<version>${project.version}</version>
						<scope>system</scope>
						<systemPath>${project.basedir}/target/${project.artifactId}-${project.version}.jar</systemPath>
					</dependency>
				</dependencies>
				<configuration>
					<configurationFile>src/main/resources/jooqConfig.xml</configurationFile>
				</configuration>
			</plugin>
		</plugins>
	</build>

或者

<plugin>
                    <groupId>org.jooq</groupId>
                    <artifactId>jooq-codegen-maven</artifactId>
                    <executions>
                        <execution>
                            <phase>generate-sources</phase>
                            <goals>
                                <goal>generate</goal>
                            </goals>
                        </execution>
                    </executions>
                    <dependencies>
                        <dependency>
                            <groupId>mysql</groupId>
                            <artifactId>mysql-connector-java</artifactId>
                            <version>5.1.42</version>
                        </dependency>
                    </dependencies>

                    <configuration>
                        <jdbc>
                            <driver>com.mysql.jdbc.Driver</driver>
                            <url>jdbc:mysql://localhost:3306/</url>
                            <username>root</username>
                            <password>root</password>
                        </jdbc>

                        <generator>
                            <database>
                                <name>org.jooq.util.mysql.MySQLDatabase</name>
                                <!--include和exclude用于控制为数据库中哪些表生成代码-->
                                <includes>.*</includes>
                                <!--<excludes></excludes>-->

                                <!--数据库名称-->
                                <inputSchema>jooqdb</inputSchema>
                            </database>

                            <generate>
                                <!--生成dao和pojo-->
                                <daos>true</daos>
                                <pojos>true</pojos>
                                <!--把数据库时间类型映射到java 8时间类型-->
                                <javaTimeTypes>true</javaTimeTypes>
                                <!--<interfaces>true</interfaces>-->
                                <!--不在生成的代码中添加spring注释，比如@Repository-->
                                <springAnnotations>false</springAnnotations>
                            </generate>

                            <target>
                                <!--生成代码文件的包名及放置目录-->
                                <packageName>com.pk.db.gen</packageName>
                                <directory>src/main/java</directory>
                            </target>

                        </generator>
                    </configuration>
                </plugin>

 <plugin>
                <groupId>org.jooq</groupId>
                <artifactId>jooq-codegen-maven</artifactId>
                <version>3.9.2</version>

                <executions>
                    <execution>
                        <phase>generate-sources</phase>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                    </execution>
                </executions>

                <dependencies>
                    <dependency>
                        <groupId>mysql</groupId>
                        <artifactId>mysql-connector-java</artifactId>
                        <version>5.1.38</version>
                    </dependency>
                </dependencies>

                <configuration>
                    <jdbc>
                        <driver>com.mysql.jdbc.Driver</driver>
                        <url>${spring.datasource.url}</url>
                        <username>${spring.datasource.username}</username>
                        <password>${spring.datasource.password}</password>
                    </jdbc>

                    <generator>

                        <database>
                            <!--下面这两行是为view也生成代码的关键-->
                            <!--force generating id'sfor everything in public schema, that has an 'id' field-->
                            <syntheticPrimaryKeys>public\..*\.id</syntheticPrimaryKeys>
                            <!--name for fake primary key-->
                            <overridePrimaryKeys>override_primmary_key</overridePrimaryKeys>

                            <name>org.jooq.util.mysql.MySQLDatabase</name>

                            <!--include和exclude用于控制为数据库中哪些表生成代码-->
                            <includes>.*</includes>
                            <!--<excludes></excludes>-->

                            <!--数据库名称-->
                            <inputSchema>xxxxxx</inputSchema>
                        </database>

                        <generate>
                            <!--生成dao和pojo-->
                            <daos>true</daos>
                            <pojos>true</pojos>
                            <!--把数据库时间类型映射到java 8时间类型-->
                            <javaTimeTypes>true</javaTimeTypes>
                            <!--<interfaces>true</interfaces>-->
                            <!--不在生成的代码中添加spring注释，比如@Repository-->
                            <springAnnotations>false</springAnnotations>
                        </generate>

                        <target>
                            <!--生成代码文件的包名及放置目录-->
                            <packageName>com.iot.xxxxx.jooq</packageName>
                            <directory>src/main/java</directory>
                        </target>

                    </generator>
                </configuration>
            </plugin>

参考
https://blog.csdn.net/u013725455/article/details/77145698

