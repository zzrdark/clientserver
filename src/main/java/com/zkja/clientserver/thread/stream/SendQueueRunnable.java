package com.zkja.clientserver.thread.stream;

import com.zkja.clientserver.domain.TcpReq;
import com.zkja.clientserver.domain.TcpRes;
import com.zkja.clientserver.thread.QueueManager;
import com.zkja.clientserver.thread.SocketQueueManager;
import com.zkja.clientserver.thread.socket.SocketManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @authon zzr
 */
public class SendQueueRunnable implements Runnable {
    Logger logger = LoggerFactory.getLogger(SendQueueRunnable.class);
    private volatile QueueManager queueManager;
    private volatile SocketManager socketManager;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    public SendQueueRunnable(QueueManager queueManager, SocketManager socketManager) {
        this.queueManager = queueManager;
        this.socketManager = socketManager;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Map<String,List> map = ((SocketQueueManager)queueManager).getImeiMap();

            if(map.size()>0){
                for (Map.Entry m : map.entrySet()) {

                    SelectionKey selectionKey = socketManager.getMap().get(m.getKey());
                    //离线后不处理
                    if(selectionKey!=null){
                        try {
                            if(selectionKey.attachment()!=null){
                                selectionKey.interestOps(selectionKey.interestOps()|SelectionKey.OP_WRITE);
                                /*socketManager.sendSmu(byteBuffer, (SocketChannel) selectionKey.channel(),(List)m.getValue());
                                selectionKey.interestOps(selectionKey.interestOps() | SelectionKey.OP_READ);*/
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            logger.info("SelectionKey  Imei码  被Cancel ");
                            try {
                                if(selectionKey.channel()!=null){
                                    selectionKey.channel().close();
                                }
                                selectionKey.cancel();
                                map.remove(m.getKey());
                                socketManager.getMap().remove(m.getKey());

                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }else {
                        logger.error("该smu未在线:"+m.getKey());
                        map.remove(m.getKey());
                    }

                }
            }
        }
    }

}
