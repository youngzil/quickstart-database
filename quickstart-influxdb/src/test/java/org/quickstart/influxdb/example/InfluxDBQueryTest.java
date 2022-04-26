package org.quickstart.influxdb.example;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.JSON;
import com.influxdb.client.WriteApi;
import com.influxdb.client.flux.FluxClient;
import com.influxdb.client.flux.FluxClientFactory;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.junit.jupiter.api.Test;

import java.util.List;

public class InfluxDBQueryTest {

  // 使用Flux方式查询数据
  @Test
  public void queryExampleUsingFluxClient() {
    FluxClient fluxClient = FluxClientFactory.create("http://127.0.0.1:8086/");
    Boolean ping = fluxClient.ping();
    System.out.println("是否可以连接InfluxDb:" + ping);
    //
    // Flux
    //
    String flux =
        "from(bucket: \"mydb\") " // bucket就代表当前的influxdb1.8中的database
            + " |> range(start: -3h, stop: 3h) " // 必须要有起始时间和结束时间
            + " |> filter(fn: (r) => r[\"_measurement\"] == \"treasures\")"; // _measurement
    // 表示当前的series
    /*
     * String flux = String.format("from(bucket: \"%s\") " +
     * " |> range(start: -3h, stop: 3h) " +
     * " |> filter(fn: (r) => r[\"_measurement\"] == \"%s\") ", "mydb", "mem");
     */
    // String flux ="chunked=true&db=mydb&epoch=ns&q=select * from treasures";
    //
    // Synchronous query
    //
    List<FluxTable> tables = fluxClient.query(flux);
    System.out.println("开始显示数据===============>");
    System.out.println("time : captain_id : value");
    for (FluxTable fluxTable : tables) {
      List<FluxRecord> records = fluxTable.getRecords(); // 获取实际的记录操作
      // List<FluxColumn> columns = fluxTable.getColumns(); // 获取返回的列
      // System.out.println(columns); // 下面的数据显示是按照当前的column中的label名称获取的数据
      // System.out.println(records);

      for (FluxRecord fluxRecord : records) {
        System.out.println(
            fluxRecord.getTime()
                + ": "
                + fluxRecord.getValueByKey("captain_id")
                + ":"
                + fluxRecord.getValueByKey("_value"));
      }
    }
    System.out.println("开始显示数据<===============");
    //
    // Asynchronous query 。一个异步操作请求
    //
    /*
     * fluxClient.query(flux, (cancellable, record) -> {
     *
     * // process the flux query result record System.out.println(record.getTime() +
     * ": " + record.getValue());
     *
     * }, error -> {
     *
     * // error handling while processing result
     * System.out.println("Error occurred: " + error.getMessage());
     *
     * }, () -> {
     *
     * // on complete System.out.println("Query completed"); });
     */

    fluxClient.close();
  }


  /**
   *
   * @author hy
   * @createTime 2021-03-14 12:00:16
   * @description 使用官方推荐的方式查询
   *
   */
  @Test
  public  void queryExampleUseingInfluxDBClient() {
    String database = "mydb";
    String retentionPolicy = "autogen";

    // 1.打开客户端
    InfluxDBClient client = InfluxDBClientFactory.createV1("http://127.0.0.1:8086", "root",
        "root".toCharArray(), database, retentionPolicy);


    System.out.println("*** Write Points ***");

    // 2.创建写入api
    try (WriteApi writeApi = client.makeWriteApi()) {

      Point point = Point.measurement("treasures").addTag("captain_id", "crunch").addField("value", 19.5f);

      System.out.println(point.toLineProtocol());
      // 执行写入数据操作
      writeApi.writePoint(point);
    }

    // 开始执行查询操作
    System.out.println("*** Query Points ***");
    String query = String.format("from(bucket: \"%s\") " // bucket就代表当前的influxdb1.8中的database
        + " |> range(start: -3h, stop: 3h) " // 必须要有起始时间和结束时间
        + " |> filter(fn: (r) => r[\"_measurement\"] == \"%s\")",database,"treasures"); // _measurement 表示当前的series

    query = "select * from treasures";
    List<FluxTable> tables = client.getQueryApi().query(query);
    tables.get(0).getRecords().forEach(record -> System.out.println(String.format("%s %s: %s %s", record.getTime(),
        record.getMeasurement(), record.getField(), record.getValue())));

    client.close();
  }


  @Test
  public void testQueryRange() throws UnirestException {

    String query_range = "http://127.0.0.1:8086/query";

    // Unirest.get("http://httpbin.org/{method}").routeParam("method", "get").queryString("name", "Mark").asJson();

    HttpResponse<String> response = Unirest.get(query_range)//
        .queryString("db", "mydb")//
        .queryString("q", "select * from treasures")//
        .asString();
    String body = response.getBody();
    System.out.println("body=" + body);

    // 我获取数据是Response.json中的，所以我定义了一个Response.class
    // QueryRangeResponse test = JSON.parseObject(body, QueryRangeResponse.class);
    // System.out.println(test);
  }


}
