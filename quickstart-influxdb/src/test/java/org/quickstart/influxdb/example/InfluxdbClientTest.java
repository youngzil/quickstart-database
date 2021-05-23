package org.quickstart.influxdb.example;

import okhttp3.OkHttpClient;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDB.ResponseFormat;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Pong;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class InfluxdbClientTest {

    // 参考https://www.baeldung.com/java-influxdb

    private final static Logger logger = LoggerFactory.getLogger(InfluxdbClientTest.class);

    @Test
    public void testConnect() throws IOException, InterruptedException {
        connectToInfluxDB(null, null, ResponseFormat.JSON);

        main(null);

    }

    public static void main(String[] args) {
        String databaseURL = "http://" + "127.0.0.1" + ":" + "8086";
        String userName = null;
        String password = null;

        // 内部还是调用的OkHttpClient
        InfluxDB influxDB = InfluxDBFactory.connect(databaseURL);
        // InfluxDB influxDB = InfluxDBFactory.connect(databaseURL, userName, password);

        // Verifying the Connection
        Pong response = influxDB.ping();
        if (response.isGood()) {
            logger.debug("success");
        }

        if (response.getVersion().equalsIgnoreCase("unknown")) {
            logger.error("Error pinging server.");
            return;
        } else {
            logger.debug("success");
        }

        // Creating a Database
        // To create a retention policy
        String dbName = "baeldung";
        influxDB.createDatabase(dbName);
        influxDB.createRetentionPolicy("defaultPolicy", "baeldung", "30d", 1, true);

        // Setting a Logging Level
        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);

        Point point =
            Point.measurement("memory").time(System.currentTimeMillis(), TimeUnit.MILLISECONDS).addField("name", "server1").addField("free", 4743656L)
                .addField("used", 1015096L).addField("buffer", 1010467L).build();

        BatchPoints batchPoints = BatchPoints.database(dbName).retentionPolicy("defaultPolicy").build();

        Point point1 =
            Point.measurement("memory").time(System.currentTimeMillis(), TimeUnit.MILLISECONDS).addField("name", "server1").addField("free", 4743656L)
                .addField("used", 1015096L).addField("buffer", 1010467L).build();

        Point point2 = Point.measurement("memory").time(System.currentTimeMillis() - 100, TimeUnit.MILLISECONDS).addField("name", "server1")
            .addField("free", 4743696L).addField("used", 1016096L).addField("buffer", 1008467L).build();

        batchPoints.point(point1);
        batchPoints.point(point2);
        influxDB.write(batchPoints);

        influxDB.enableBatch(100, 200, TimeUnit.MILLISECONDS);

        influxDB.setRetentionPolicy("defaultPolicy");
        influxDB.setDatabase(dbName);

        influxDB.write(point);

        influxDB.disableBatch();

        influxDB.close();

    }

    public static InfluxDB connectToInfluxDB(final OkHttpClient.Builder client, String apiUrl, ResponseFormat responseFormat)
        throws InterruptedException, IOException {
        OkHttpClient.Builder clientToUse;
        if (client == null) {
            clientToUse = new OkHttpClient.Builder();
        } else {
            clientToUse = client;
        }
        String apiUrlToUse;
        if (apiUrl == null) {
            apiUrlToUse = "http://" + "127.0.0.1" + ":" + "8086";
        } else {
            apiUrlToUse = apiUrl;
        }
        InfluxDB influxDB = InfluxDBFactory.connect(apiUrlToUse, "admin", "admin", clientToUse, responseFormat);
        boolean influxDBstarted = false;
        do {
            Pong response;
            try {
                response = influxDB.ping();
                if (response.isGood()) {
                    influxDBstarted = true;
                }
            } catch (Exception e) {
                // NOOP intentional
                e.printStackTrace();
            }
            Thread.sleep(100L);
        } while (!influxDBstarted);
        influxDB.setLogLevel(InfluxDB.LogLevel.NONE);
        System.out.println("##################################################################################");
        System.out.println("#  Connected to InfluxDB Version: " + influxDB.version() + " #");
        System.out.println("##################################################################################");
        return influxDB;
    }

}
