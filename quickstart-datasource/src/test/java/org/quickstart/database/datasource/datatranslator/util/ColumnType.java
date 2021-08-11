package org.quickstart.database.datasource.datatranslator.util;

import lombok.Data;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/9/24 23:49
 */
@Data
public class ColumnType {

  private String columnName;
  private String columnType;
  private int columnSize;
  private int digits;
  private int nullable;

}
