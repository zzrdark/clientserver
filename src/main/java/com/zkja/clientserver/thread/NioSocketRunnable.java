package com.zkja.clientserver.thread;


import com.zkja.clientserver.domain.TcpReq;
import com.zkja.clientserver.thread.socket.SocketManager;
import com.zkja.clientserver.thread.stream.InputRunnable;
import com.zkja.clientserver.thread.stream.IteratorRunnable;
import com.zkja.clientserver.thread.stream.SendQueueRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @authon zzr
 */
public class NioSocketRunnable implements Runnable {

    /**
     * 存储socket 以imei 为键值
     * 已经将imei 存到了 SelectionKey特殊对象里了
     */

    private Logger logger = LoggerFactory.getLogger(NioSocketRunnable.class);

    private Selector selector;
    private volatile QueueManager queueManager;
    private volatile SocketManager socketManager;
    private volatile ThreadPoolTaskExecutor taskExecutor;
    private ByteBuffer sendBuffer = ByteBuffer.allocate(2048);
    private ByteBuffer recBuffer = ByteBuffer.allocate(2048);

    public NioSocketRunnable(QueueManager queueManager, SocketManager socketManager, ThreadPoolTaskExecutor taskExecutor) throws IOException {
        this.queueManager = queueManager;
        this.socketManager = socketManager;
        this.selector = Selector.open();
        this.taskExecutor = taskExecutor;
        logger.info("初始化NioSocketRunnable");
    }


    @Override
    public void run() {
        try {
            SendQueueRunnable sendQueueRunnable = new SendQueueRunnable(queueManager, socketManager);
            taskExecutor.execute(sendQueueRunnable);
            while (true) {
                try {
                    if (selector.select(10) > 0) {
                        Set selectionKeys = selector.selectedKeys();
                        Iterator iterator = selectionKeys.iterator();
                        /*IteratorRunnable iteratorRunnable = new IteratorRunnable(iterator, socketManager, queueManager);
                        iteratorRunnable.run();*/

                        SelectionKey selectionKey = null;
                        String imei = null;
                        while (iterator.hasNext()) {
                            selectionKey = (SelectionKey) iterator.next();
                            SocketChannel socketChannel = null;
                            try {
                                if (selectionKey.isValid() && selectionKey.isReadable()) {
                                    socketChannel = (SocketChannel) selectionKey.channel();
                                    logger.debug("收到一条");
                                    //返回false表示没取到数据
                                    Boolean statusRead = socketManager.sendSmc(recBuffer, socketChannel, queueManager, selectionKey);
                                    /*InputRunnable inputRunnable = new InputRunnable(queueManager,socketChannel,socketManager,selectionKey);
                                    taskExecutor.execute(inputRunnable);*/
                                    /**
                                     * * 这个时候有可能是因为 监听的连接断了 然后 没有返回数据 而且 readOps 还保留着1  所以会一直进来
                                     *                       * 所以把selectionKey 取消了
                                     */
                                   /*if(selectionKey==null||(selectionKey.attachment()==null&&!statusRead)){
                                        logger.info("Iterator cannal selectionkey");
                                        selectionKey.cancel();
                                        continue;
                                    }*/

                                    if (socketManager.getMap().get(selectionKey.attachment()) == null || socketManager.getMap().get(selectionKey.attachment()) != selectionKey) {
                                        socketManager.getMap().put((String) selectionKey.attachment(), selectionKey);
                                    }
                                }

                                if (selectionKey.isValid() && selectionKey.isWritable() && selectionKey.attachment() != null) {
                                    socketChannel = (SocketChannel) selectionKey.channel();
                                    if (selectionKey == null) {
                                        continue;
                                    }
                                    imei = (String) selectionKey.attachment();

                                    List<TcpReq> list = ((SocketQueueManager) queueManager).getImeiList(imei);
                                    if (list != null) {
                                        logger.debug("开始向imei:" + imei + "发送消息" + list.size());
                                        socketManager.sendSmu(sendBuffer, socketChannel, list);
                                        selectionKey.interestOps(selectionKey.interestOps() | SelectionKey.OP_READ);
                                    }


                                }
                            } catch (Exception e) {
                                logger.info("selectionKey已被channel");
                                logger.error("异常断开", e);
                                if (imei == null) {
                                    if (selectionKey.attachment() != null) {
                                        socketManager.getMap().remove(selectionKey.attachment());
                                        logger.error("成功删除 Imei " + selectionKey.attachment());
                                    }
                                } else {
                                    if (socketManager.getMap().get(imei) != null) {
                                        socketManager.getMap().remove(imei);
                                        logger.error("成功删除 Imei " + imei);
                                    }
                                }
                                iterator.remove();
                                selectionKey.cancel();
                                try {
                                    socketChannel.close();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                e.printStackTrace();
                            }
                            iterator.remove();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } finally {
            try {
                logger.info("selector关闭");
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void channelRegister(SocketChannel socketChannel) throws IOException {
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

}
