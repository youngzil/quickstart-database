package org.quickstart.canal;

import com.alibaba.otter.canal.instance.manager.CanalInstanceWithManager;
import com.alibaba.otter.canal.instance.manager.model.Canal;
import com.alibaba.otter.canal.instance.manager.model.CanalParameter;
import com.alibaba.otter.canal.instance.manager.model.CanalParameter.HAMode;
import com.alibaba.otter.canal.instance.manager.model.CanalParameter.IndexMode;
import com.alibaba.otter.canal.instance.manager.model.CanalParameter.MetaMode;
import com.alibaba.otter.canal.instance.manager.model.CanalParameter.SourcingType;
import com.alibaba.otter.canal.instance.manager.model.CanalParameter.StorageMode;
import com.alibaba.otter.canal.protocol.CanalPacket.Ack;
import com.alibaba.otter.canal.protocol.CanalPacket.ClientAck;
import com.alibaba.otter.canal.protocol.CanalPacket.ClientAuth;
import com.alibaba.otter.canal.protocol.CanalPacket.ClientRollback;
import com.alibaba.otter.canal.protocol.CanalPacket.Get;
import com.alibaba.otter.canal.protocol.CanalPacket.Handshake;
import com.alibaba.otter.canal.protocol.CanalPacket.Messages;
import com.alibaba.otter.canal.protocol.CanalPacket.Packet;
import com.alibaba.otter.canal.protocol.CanalPacket.PacketType;
import com.alibaba.otter.canal.protocol.CanalPacket.Sub;
import com.alibaba.otter.canal.protocol.CanalPacket.Unsub;
import com.alibaba.otter.canal.server.embedded.CanalServerWithEmbedded;
import com.alibaba.otter.canal.server.netty.CanalServerWithNetty;
import com.alibaba.otter.canal.server.netty.NettyUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Ignore
public class CanalServerTest2 {

    protected static final String cluster1 = "127.0.0.1:2188";
    protected static final String DESTINATION = "ljhtest1";
    protected static final String DETECTING_SQL = "insert into retl.xdual values(1,now()) on duplicate key update x=now()";
    protected static final String MYSQL_ADDRESS = "192.168.3.118";
    protected static final int MYSQL_PORT = 3311;
    protected static final String USERNAME = "retl";
    protected static final String PASSWORD = "retl";
    protected static final String FILTER = "retl\\..*,erosa.canaltable1s,erosa.canaltable1t";

    private final ByteBuffer header = ByteBuffer.allocate(4);
    private CanalServerWithNetty nettyServer;

    public static void main(String[] args) {
        CanalServerTest2 test2 = new CanalServerTest2();
        test2.start();
    }

    public void start(){
        CanalServerWithEmbedded embeddedServer = new CanalServerWithEmbedded();
        embeddedServer.setCanalInstanceGenerator(destination -> {
            Canal canal = buildCanal();
            return new CanalInstanceWithManager(canal, FILTER);
        });

        nettyServer = CanalServerWithNetty.instance();
        nettyServer.setEmbeddedServer(embeddedServer);
        nettyServer.setPort(1088);
        nettyServer.start();

    }

    private byte[] readNextPacket(SocketChannel channel) throws IOException {
        header.clear();
        read(channel, header);
        int bodyLen = header.getInt(0);
        ByteBuffer bodyBuf = ByteBuffer.allocate(bodyLen);
        read(channel, bodyBuf);
        return bodyBuf.array();
    }

    private void writeWithHeader(SocketChannel channel, byte[] body) throws IOException {
        ByteBuffer header = ByteBuffer.allocate(4);
        header.putInt(body.length);
        header.flip();
        int len = channel.write(header);
        assert (len == header.capacity());

        channel.write(ByteBuffer.wrap(body));
    }

    private void read(SocketChannel channel, ByteBuffer buffer) throws IOException {
        while (buffer.hasRemaining()) {
            int r = channel.read(buffer);
            if (r == -1) {
                throw new IOException("end of stream when reading header");
            }
        }
    }

    private Canal buildCanal() {
        Canal canal = new Canal();
        canal.setId(1L);
        canal.setName(DESTINATION);
        canal.setDesc("test");

        CanalParameter parameter = new CanalParameter();

        parameter.setZkClusters(Arrays.asList("127.0.0.1:2188"));
        parameter.setMetaMode(MetaMode.MEMORY);
        parameter.setHaMode(HAMode.HEARTBEAT);
        parameter.setIndexMode(IndexMode.MEMORY);

        parameter.setStorageMode(StorageMode.MEMORY);
        parameter.setMemoryStorageBufferSize(32 * 1024);

        parameter.setSourcingType(SourcingType.MYSQL);
        parameter.setDbAddresses(Arrays.asList(new InetSocketAddress(MYSQL_ADDRESS, MYSQL_PORT), new InetSocketAddress(MYSQL_ADDRESS, MYSQL_PORT)));
        parameter.setDbUsername(USERNAME);
        parameter.setDbPassword(PASSWORD);
        parameter.setPositions(Arrays.asList("{\"journalName\":\"mysql-bin.000001\",\"position\":6163L,\"timestamp\":1322803601000L}",
            "{\"journalName\":\"mysql-bin.000001\",\"position\":6163L,\"timestamp\":1322803601000L}"));

        parameter.setSlaveId(1234L);

        parameter.setDefaultConnectionTimeoutInSeconds(30);
        parameter.setConnectionCharset("UTF-8");
        parameter.setConnectionCharsetNumber((byte)33);
        parameter.setReceiveBufferSize(8 * 1024);
        parameter.setSendBufferSize(8 * 1024);

        parameter.setDetectingEnable(false);
        parameter.setDetectingIntervalInSeconds(10);
        parameter.setDetectingRetryTimes(3);
        parameter.setDetectingSQL(DETECTING_SQL);

        canal.setCanalParameter(parameter);
        return canal;
    }
}
