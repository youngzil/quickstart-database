package org.quickstart.influxdb.example;

import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceSample {

  public static void main(String[] args) {
    // Create an object to handle the communication with InfluxDB.
    // (best practice tip: reuse the 'influxDB' instance when possible)
    final String serverURL = "http://127.0.0.1:8086", username = "root", password = "root";
    // 连接influxDB数据库
    final InfluxDB influxDB = InfluxDBFactory.connect(serverURL, username, password);

    // Create a database...
    // https://docs.influxdata.com/influxdb/v1.7/query_language/database_management/
    String databaseName = "NOAA_water_database";
    // 创建数据库
    influxDB.query(new Query("CREATE DATABASE " + databaseName));
    influxDB.setDatabase(databaseName);

    // ... and a retention policy, if necessary.
    // https://docs.influxdata.com/influxdb/v1.7/query_language/database_management/
    // 创建数据保留策略

    // # 数据保留策略名
    // influxDB.retentionPolicyName=3_days
    // # 数据保留策略，3d:保留3天
    // influxDB.retentionPolicy=3d
    String retentionPolicyName = "one_day_only";
    String retentionPolicy = "1d";
    influxDB.query(
        new Query(
            "CREATE RETENTION POLICY "
                + retentionPolicyName
                + " ON "
                + databaseName
                + " DURATION "
                + retentionPolicy
                + " REPLICATION 1 DEFAULT"));
    influxDB.setRetentionPolicy(retentionPolicyName);

    // Enable batch writes to get better performance.
    influxDB.enableBatch(BatchOptions.DEFAULTS);

    ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

    // 当前活跃线程数
    int currentActive = threadPool.getActiveCount();
    // 当前线程池大小
    int currentPoolSize = threadPool.getPoolSize();
    // 最大线程数
    long maximumPoolSize = threadPool.getMaximumPoolSize();
    // 核心线程数
    long corePoolSize = threadPool.getCorePoolSize();
    // 队列容量
    int queueSize = threadPool.getQueue().size();

    String host = "localhost";
    int port = 9900;
    String poolName = "name";

    /** measurement相当于表名 tag是用于统计或分类的参数，这里用ip+端口进行标记，用于后续图表展示 field相当于列，存储各类指标值 */
    influxDB.write(
        Point.measurement("threadPool")
            .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
            .tag("host", host)
            .tag("port", port + "")
            .tag("addr", host + ":" + port)
            .tag("group", poolName)
            .addField("currentPoolSize", currentPoolSize)
            .addField("currentActive", currentActive)
            .addField("corePoolSize", corePoolSize)
            .addField("maximumPoolSize", maximumPoolSize)
            .addField("queneSize", queueSize)
            .build());

    // while (true) {}

    // Close it if your application is terminating or you are not using it anymore.
    influxDB.close();
  }
}
