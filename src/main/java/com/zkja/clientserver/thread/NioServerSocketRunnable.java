package com.zkja.clientserver.thread;

import com.zkja.clientserver.property.ServerSocketProperties;
import com.zkja.clientserver.thread.socket.SocketManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zzr
 */
public class NioServerSocketRunnable implements Runnable{


    private ServerSocketProperties serverSocketProperties;

    private Logger logger = LoggerFactory.getLogger(NioServerSocketRunnable.class);

    private volatile QueueManager queueManager;

    private volatile ThreadPoolTaskExecutor taskExecutor;

    private volatile SocketManager socketManager;

    private static Integer integer = new Integer(0);




    public NioServerSocketRunnable(ServerSocketProperties serverSocketProperties, QueueManager queueManager,
                                   ThreadPoolTaskExecutor taskExecutor, SocketManager socketManager) {
		this.serverSocketProperties = serverSocketProperties;
		this.queueManager = queueManager;
		this.taskExecutor = taskExecutor;
		this.socketManager = socketManager;
	}



	@Override
    public void run() {
        ServerSocketChannel serverSocketChannel = null;
        Selector selector = null;
        try {
            selector = Selector.open();
            Map<String,SelectionKey> synMap = new ConcurrentHashMap<String,SelectionKey>(1000);
            socketManager.setMap(synMap);
            serverSocketChannel = serverSocketListen(selector);
            NioSocketRunnable nioSocketRunnable = new NioSocketRunnable(queueManager, socketManager, taskExecutor);
            taskExecutor.execute(nioSocketRunnable);

            serverSocketSelection(selector, nioSocketRunnable);
        } catch (IOException e) {
            logger.error("NioServerSocket出错。");
            e.printStackTrace();
        } finally {
            try {
                logger.info("NioServerSocketRunnable：selector关闭");
                logger.info("NioServerSocketRunnable：serverSocketChannel关闭");
                selector.close();
                serverSocketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ServerSocketChannel serverSocketListen(Selector selector) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(Integer.valueOf(serverSocketProperties.getPort())));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
        logger.info("serverSocketChannel  监听了"+serverSocketProperties.getPort()+"端口");
        logger.info("serverSocketChannel  注册了 selector  监听接受");
        return serverSocketChannel;
    }

    public void serverSocketSelection(Selector selector, NioSocketRunnable nioSocketRunnable) throws IOException {
        while (true){
            int num = 0;
            try {
                if (selector.select(10)>0){
                    Set selectionKeys = selector.selectedKeys();
                    Iterator iterator = selectionKeys.iterator();
                    while (iterator.hasNext()){
                    	
                        SelectionKey key = (SelectionKey) iterator.next();
                        iterator.remove();
                        if(key.isValid()&&key.isAcceptable()){
                        	integer++;
                            SocketChannel socketChannel = ((ServerSocketChannel)key.channel()).accept();
                            nioSocketRunnable.channelRegister(socketChannel);
                            logger.info("连接上一台smu:"+integer);
                        }
                    }
                }
            } catch (IOException e) {
                logger.info("Serversocket在accept过程出现错误");
                e.printStackTrace();
            }
        }
    }


}
