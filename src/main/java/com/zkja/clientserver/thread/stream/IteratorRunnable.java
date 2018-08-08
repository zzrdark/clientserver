package com.zkja.clientserver.thread.stream;

import com.zkja.clientserver.domain.TcpReq;
import com.zkja.clientserver.thread.QueueManager;
import com.zkja.clientserver.thread.SocketQueueManager;
import com.zkja.clientserver.thread.socket.SocketManager;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;

/**
 * @author zzr
 */
public class IteratorRunnable implements Runnable {

    private Logger logger = LoggerFactory.getLogger(IteratorRunnable.class);
    private Iterator iterator;
    private SocketManager socketManager;
    private QueueManager queueManager;
    private ByteBuffer recBuffer = ByteBuffer.allocate(2048);

    public IteratorRunnable(Iterator iterator, SocketManager socketManager, QueueManager queueManager) {
        this.iterator = iterator;
        this.socketManager = socketManager;
        this.queueManager = queueManager;
    }

    @Override
    public void run() {

    }
}
