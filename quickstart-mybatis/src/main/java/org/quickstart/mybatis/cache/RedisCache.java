package org.quickstart.mybatis.cache;

import java.util.concurrent.locks.ReadWriteLock;
import org.apache.ibatis.cache.Cache;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/9/24 00:09
 */
public class RedisCache implements Cache {//实现类

  @Override
  public String getId() {
    return null;
  }

  @Override
  public void putObject(Object o, Object o1) {

  }

  @Override
  public Object getObject(Object o) {
    return null;
  }

  @Override
  public Object removeObject(Object o) {
    return null;
  }

  @Override
  public void clear() {

  }

  @Override
  public int getSize() {
    return 0;
  }

  @Override
  public ReadWriteLock getReadWriteLock() {
    return null;
  }

}
