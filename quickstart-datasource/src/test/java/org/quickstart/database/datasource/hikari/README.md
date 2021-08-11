1、
HikariConfig config = new HikariConfig();
         config.setDriverClassName(className);
         config.setJdbcUrl(url);
         config.setUsername(username);
         config.setPassword(password);
         config.addDataSourceProperty("cachePrepStmts", "true");
         config.addDataSourceProperty("prepStmtCacheSize", "250");
         config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
 //链接池初始化
HikariDataSource ds  = new HikariDataSource(config)


2、
HikariDataSource ds = new HikariDataSource();
 ds.setJdbcUrl("jdbc:mysql://localhost:3306/simpsons");
 ds.setUsername("bart");
 ds.setPassword("51mp50n");

3、
HikariConfig config = new HikariConfig("config.properties");
 HikariDataSource ds = new HikariDataSource(config);

Properties props = new Properties();
 props.setProperty("dataSourceClassName", "com.mysql.jdbc.Driver");
 props.setProperty("dataSource.user", "root");
 props.setProperty("dataSource.password", "root");
 props.setProperty("dataSource.databaseName", "mango_example");
 props.put("dataSource.logWriter", new PrintWriter(System.out));

 HikariConfig config = new HikariConfig(props);
 HikariDataSource ds = new HikariDataSource(config);



