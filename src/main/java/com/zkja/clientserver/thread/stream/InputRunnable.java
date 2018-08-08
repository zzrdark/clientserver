package com.zkja.clientserver.thread.stream;

import com.zkja.clientserver.thread.QueueManager;
import com.zkja.clientserver.thread.socket.SocketManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * 这里是bio调用的  已废弃
 *
 * @author zzr
 */
public class InputRunnable implements Runnable {

    private volatile QueueManager queueManager;
    private SocketChannel socketChannel;
    private volatile SocketManager socketManager;
    private ByteBuffer recBuffer = ByteBuffer.allocate(2048);
    private SelectionKey selectionKey;


    public InputRunnable(QueueManager queueManager, SocketChannel socketChannel, SocketManager socketManager, SelectionKey selectionKey) {
        this.queueManager = queueManager;
        this.socketChannel = socketChannel;
        this.socketManager = socketManager;
        this.selectionKey = selectionKey;
    }

    @Override
    public void run() {
        Logger logger = LoggerFactory.getLogger(InputRunnable.class);
        try {
            Boolean statusRead = socketManager.sendSmc(recBuffer, socketChannel, queueManager, selectionKey);
        } catch (Exception e) {
            logger.info("selectionKey已被channel");
            logger.error("异常断开", e);

            if (selectionKey.attachment() != null) {
                socketManager.getMap().remove(selectionKey.attachment());
                logger.error("成功删除 Imei " + selectionKey.attachment());
            }

            selectionKey.cancel();
            try {
                socketChannel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }


    }


}
