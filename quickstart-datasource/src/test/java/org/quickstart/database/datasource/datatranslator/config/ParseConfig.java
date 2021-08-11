package org.quickstart.database.datasource.datatranslator.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/9/24 22:10
 */
public class ParseConfig {

  private final static String SOURCE_PREFIX = "database.source";
  private final static String TARGET_PREFIX = "database.target";

  private final static DataSourceConfig sourceDataSource = new DataSourceConfig();
  private final static DataSourceConfig targetDataSource = new DataSourceConfig();

  private static List<String> files = new ArrayList<>();

  static {

    InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("datatranslator.properties");

    Properties prop = new Properties();
    try {
      prop.load(inStream);

      sourceDataSource.setUrl(prop.getProperty(SOURCE_PREFIX + ".url"));
      sourceDataSource.setUsername(prop.getProperty(SOURCE_PREFIX + ".username"));
      sourceDataSource.setPassword(prop.getProperty(SOURCE_PREFIX + ".password"));
      sourceDataSource.setType(prop.getProperty(SOURCE_PREFIX + ".type"));
      sourceDataSource.setEncode(prop.getProperty(SOURCE_PREFIX + ".encode"));
      sourceDataSource.setPoolSize(prop.getProperty(SOURCE_PREFIX + ".poolSize"));

      targetDataSource.setUrl(prop.getProperty(TARGET_PREFIX + ".url"));
      targetDataSource.setUsername(prop.getProperty(TARGET_PREFIX + ".username"));
      targetDataSource.setPassword(prop.getProperty(TARGET_PREFIX + ".password"));
      targetDataSource.setType(prop.getProperty(TARGET_PREFIX + ".type"));
      targetDataSource.setEncode(prop.getProperty(TARGET_PREFIX + ".encode"));
      targetDataSource.setPoolSize(prop.getProperty(TARGET_PREFIX + ".poolSize"));

      String[] fileArray = prop.getProperty("files").split(",");
      files = Arrays.asList(fileArray).stream().filter(file -> !file.isEmpty()).collect(Collectors.toList());

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public static DataSourceConfig getSourceDataSource() {
    return sourceDataSource;
  }

  public static DataSourceConfig getTargetDataSource() {
    return targetDataSource;
  }

}
