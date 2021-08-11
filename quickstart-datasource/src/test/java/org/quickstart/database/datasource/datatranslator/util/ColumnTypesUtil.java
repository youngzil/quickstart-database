package org.quickstart.database.datasource.datatranslator.util;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Ref;
import java.sql.Struct;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/9/24 23:49
 */
public class ColumnTypesUtil {

  private static Class getJavaObjectType(int jdbcTypeCode) {
    switch (jdbcTypeCode) {
      case Types.VARCHAR:
      case Types.LONGVARCHAR:
      case Types.CHAR:
        return String.class;
      case Types.DATE:
        return Date.class;
      case Types.TIME:
        return Time.class;
      case Types.TIMESTAMP:
        return Timestamp.class;
      case Types.BIT:
      case Types.BOOLEAN:
        return Boolean.class;
      case Types.NUMERIC:
      case Types.DECIMAL:
        return BigDecimal.class;
      case Types.TINYINT:
        return Byte.class;
      case Types.SMALLINT:
        return Short.class;
      case Types.INTEGER:
        return Integer.class;
      case Types.BIGINT:
        return Long.class;
      case Types.REAL:
        return Float.class;
      case Types.FLOAT:
      case Types.DOUBLE:
        return Double.class;
      case Types.VARBINARY:
      case Types.LONGVARBINARY:
      case Types.BINARY:
        return Byte[].class;
      case Types.CLOB:
        return Clob.class;
      case Types.BLOB:
        return Blob.class;
      case Types.ARRAY:
        return Array.class;
      case Types.REF:
        return Ref.class;
      case Types.STRUCT:
        return Struct.class;
    }
    return null;
  }

  private static String getOracleType(int jdbcTypeCode) {
    switch (jdbcTypeCode) {
      case Types.ARRAY:
      case Types.BIGINT:
      case Types.BINARY:
      case Types.BIT:
      case Types.BOOLEAN:
      case Types.REF_CURSOR:
      case Types.LONGVARBINARY:
      case Types.NULL:
      case Types.NVARCHAR:
      case Types.OTHER:
      case Types.STRUCT:
      case Types.TIME:
      case Types.TINYINT:
      case Types.VARBINARY:
        return null;
      case Types.BLOB:
        return "CLOB";
      case Types.CHAR:
        return "CHAR";
      case Types.CLOB:
        return "CLOB";
      case Types.DATE:
        return "DATE";
      case Types.DECIMAL:
        return "DECIMAL";
      case Types.DOUBLE:
        return "NUMBER";
      case Types.FLOAT:
        return "FLOAT";
      case Types.INTEGER:
        return "INTEGER";
      case Types.LONGVARCHAR:
        return "LONG VARCHAR";
      case Types.NCHAR:
        return "NCHAR";
      case Types.NCLOB:
        return "NCLOB";
      case Types.NUMERIC:
        return "NUMERIC，NUMBER";
      case Types.REAL:
        return "REAL";
      case Types.SMALLINT:
        return "SMALLINT";
      case Types.TIMESTAMP:
        return "TIMESTAMP";
      case Types.VARCHAR:
        return "VARCHAR";
    }
    return null;
  }

  private static String getMysqlType(int jdbcTypeCode) {
    switch (jdbcTypeCode) {
      case Types.ARRAY:
      case Types.BINARY:
      case Types.BIT:
      case Types.CLOB:
      case Types.REF_CURSOR:
      case Types.LONGVARBINARY:
      case Types.NCHAR:
      case Types.NCLOB:
      case Types.NULL:
      case Types.NVARCHAR:
      case Types.OTHER:
      case Types.STRUCT:
      case Types.VARBINARY:
        return null;
      case Types.BIGINT:
        return "BIGINT";
      case Types.BLOB:
        return "TINYBLOB，BLOB，MEDIUMBLOB，LONGBLOB";
      case Types.BOOLEAN:
      case Types.TINYINT:
        return "TINYINT";
      case Types.CHAR:
        return "CHAR";
      case Types.DATE:
        return "DATE";
      case Types.DECIMAL:
        return "DECIMAL";
      case Types.DOUBLE:
        return "DOUBLE";
      case Types.FLOAT:
        return "FLOAT";
      case Types.INTEGER:
        return "INTEGER，INT";
      case Types.LONGVARCHAR:
        return "TEXT，MEDIUMTEXT，LONGTEXT";
      case Types.NUMERIC:
        return "NUMERIC";
      case Types.REAL:
        return "REAL";
      case Types.SMALLINT:
        return "SMALLINT";
      case Types.TIME:
        return "TIME";
      case Types.TIMESTAMP:
        return "TIMESTAMP，DATETIME";
      case Types.VARCHAR:
        return "VARCHAR，TINYTEXT";
    }
    return null;
  }
}
