package org.quickstart.database.datasource.datatranslator.config;

import lombok.Data;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/9/24 22:07
 */
@Data
public class DataSourceConfig {

  private String url;
  private String username;
  private String password;
  private String type;
  private String encode;
  private String poolSize;

}
