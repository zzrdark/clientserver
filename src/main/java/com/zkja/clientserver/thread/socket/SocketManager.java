package com.zkja.clientserver.thread.socket;

import com.zkja.clientserver.domain.TcpReq;
import com.zkja.clientserver.send.SocketMessage;
import com.zkja.clientserver.thread.QueueManager;
import com.zkja.clientserver.thread.stream.InputRunnable;
import com.zkja.clientserver.thread.stream.SendQueueRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 对socket进行管理
 *
 * @authon zzr
 */
@Component
public class SocketManager {
    Map<String,SelectionKey> map = new ConcurrentHashMap<String,SelectionKey>();

    Logger logger = LoggerFactory.getLogger(SendQueueRunnable.class);

    public Map<String, SelectionKey> getMap() {

        return map;
    }

    public void setMap(Map<String, SelectionKey> map) {
        this.map = map;
    }

    public void addSmuQueue(TcpReq tcpReq, QueueManager queueManager){
        SelectionKey selectionKey = null;
        if(tcpReq.getImei()!=null){
            selectionKey = (SelectionKey) map.get(tcpReq.getImei());
        }

        try {
            if(selectionKey!=null&&selectionKey.attachment()!=null){
                selectionKey.interestOps(selectionKey.interestOps()|SelectionKey.OP_WRITE);
            }
            queueManager.addSmuQueue(tcpReq);
        } catch (Exception e) {
            logger.info("SelectionKey  Imei码 "+selectionKey.attachment()+" 被Cancel ");
            try {
                selectionKey.channel().close();
                selectionKey.cancel();
                map.remove(tcpReq.getImei());
                e.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    /**
     * 这里是bio调用的  已废弃
     * @param socket
     * @param queueManager
     * @param inputRunnable
     */
    public void getStrData(Socket socket, QueueManager queueManager, InputRunnable inputRunnable){
        InputStream is = null;
        try {
            is = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            StringBuffer sb = new StringBuffer();
            boolean eof = false;
            while (true){    
                    if (eof){
//                        SocketMessage.sendsmc(queueManager,sb.toString(),inputRunnable);
                        //清空
                        sb.setLength(0);
                        eof = false;
                    }
                    while((len=is.read(bytes))!=-1){
                        String str = new String(bytes,0,len);
                        sb.append(str);
                        if(sb.toString().indexOf("]")!=-1){
                            eof = true;
                            break;
                        }
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 这里是bio调用的  已废弃
     * @param socket
     * @param list
     */
    public void putStrData(Socket socket, List<TcpReq> list){

        SocketMessage.sendsmu(socket,list);

    }

    /**
     *返回false表示没取到数据
     * @param byteBuffer
     * @param socketChannel
     * @param queueManager
     * @param selectionKey
     * @throws IOException
     */
    public boolean sendSmc(ByteBuffer byteBuffer, SocketChannel socketChannel, QueueManager queueManager, SelectionKey selectionKey) throws IOException {
       return SocketMessage.ReadAndaddQueue(byteBuffer, socketChannel, queueManager, selectionKey);

    }

    /**
     *
     * @param byteBuffer
     * @param socketChannel
     * @param list
     * @throws IOException
     */
    public void sendSmu(ByteBuffer byteBuffer, SocketChannel socketChannel,List list) throws IOException {
        SocketMessage.WriteSmu(byteBuffer,socketChannel, list);
    }

    public void sendSmuObject(ByteBuffer byteBuffer, SocketChannel socketChannel,TcpReq tcpReq) throws IOException {
        SocketMessage.WriteSmuObject(byteBuffer,socketChannel, tcpReq);
    }





}
