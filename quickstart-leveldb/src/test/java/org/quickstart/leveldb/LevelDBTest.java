package org.quickstart.leveldb;

import org.iq80.leveldb.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.iq80.leveldb.impl.Iq80DBFactory.*;

public class LevelDBTest {

    @Test
    public void basic() throws IOException {
        Options options = new Options();
        options.createIfMissing(true);

        DB levelDBStore = factory.open(new File("/Users/lengfeng/Documents/levelDBStore/java/data/"), options);
        try {
            // Use the db in here....

            levelDBStore.put(bytes("Tampa"), bytes("rocks"));

            String value = asString(levelDBStore.get(bytes("Tampa")));
            System.out.println("after delete，value=" + value);
            levelDBStore.delete(bytes("Tampa"));
//            levelDBStore.delete(bytes("Tampa"), wo);
            String value2 = asString(levelDBStore.get(bytes("Tampa")));
            System.out.println("after delete，value2=" + value2);

        } finally {
            // Make sure you close the db to shutdown the
            // database and avoid resource leaks.
            levelDBStore.close();
        }

    }


    @Test
    public void batch() throws IOException {

        Options options = new Options();
        options.createIfMissing(true);

        DB levelDBStore = factory.open(new File("/Users/lengfeng/Documents/levelDBStore/java/data/"), options);
        try {

            WriteBatch batch = levelDBStore.createWriteBatch();

            batch.put(bytes("key1"), bytes("value1"));
            batch.put(bytes("key2"), bytes("value2"));
            batch.delete(bytes("key3"));

            // LevelDB保证批处理的事务性
            // 批处理可用于在LevelDB中实现事务，而该功能不能作为内置功能使用。
            //  LevelDB保证将所有插入到批处理中的操作都写入数据库，或者不写入任何操作。不会出现“ key1 ”和“ key2 ”的值被更新并且“ key3 ”不会被删除的情况。（部分执行）
            levelDBStore.write(batch);

            batch.close();

        } finally {
            // Make sure you close the db to shutdown the
            // database and avoid resource leaks.
            levelDBStore.close();
        }
    }

    @Test
    public void snapshot() throws IOException {

        Options options = new Options();
        options.createIfMissing(true);

        DB levelDBStore = factory.open(new File("/Users/lengfeng/Documents/levelDBStore/java/data/"), options);
        try {

//            但是，LevelDB中没有机制可以写入快照或将数据库还原回快照。将Key写入数据库后，就无法还原数据库的状态。

            levelDBStore.put(bytes("a"), bytes("1"));
            Snapshot snapshot = levelDBStore.getSnapshot();
            levelDBStore.put(bytes("a"), bytes("2"));
            byte[] snapshotValue = levelDBStore.get(bytes("a"), new ReadOptions().snapshot(snapshot)); // returns 1
            byte[] dbValue = levelDBStore.get(bytes("a"));// returns 2

            System.out.println("snapshotValue=" + asString(snapshotValue));
            System.out.println("dbValue=" + asString(dbValue));

        } finally {
            // Make sure you close the db to shutdown the
            // database and avoid resource leaks.
            levelDBStore.close();
        }
    }

    @Test
    public void iterator() throws IOException {

        Options options = new Options();
        options.createIfMissing(true);

        DB levelDBStore = factory.open(new File("/Users/lengfeng/Documents/levelDBStore/java/data/"), options);
        try {
            DBIterator iterator = levelDBStore.iterator();

            iterator.seek(bytes("key")); // starts from the specified key
            iterator.seekToFirst(); // starts from the first key
            iterator.seekToLast(); // starts from the last key

            // 迭代器可以使用while循环向前或向后遍历
            while (iterator.hasNext()) {
                byte[] key = iterator.peekNext().getKey();
                byte[] value = iterator.peekNext().getValue();
                // whatever you want to do
                iterator.next();
            }

            iterator.close();

        } finally {
            // Make sure you close the db to shutdown the
            // database and avoid resource leaks.
            levelDBStore.close();
        }
    }
}
