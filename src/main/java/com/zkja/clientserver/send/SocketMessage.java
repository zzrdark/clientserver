package com.zkja.clientserver.send;

import com.zkja.clientserver.common.TcpFormatUtils;
import com.zkja.clientserver.domain.TcpReq;
import com.zkja.clientserver.domain.TcpRes;
import com.zkja.clientserver.thread.QueueManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;

/**
 * @authon zzr
 */
public class SocketMessage {
    private static Logger logger = LoggerFactory.getLogger(SocketMessage.class);

    /**
     * 这里是bio调用的  已废弃
     *
     * @param queueManager
     * @param data
     * @param
     * @return
     */
    /*public static boolean sendsmc(QueueManager queueManager, String data, InputRunnable inputRunnable) {
        TcpRes tcpRes = TcpFormatUtils.getRes(data);
        logger.info("Thread:" + Thread.currentThread().getName() + " 接收到Socket  Tcp" + tcpRes);
        inputRunnable.setImei(tcpRes.getImei());
        return queueManager.addSmcQueue(tcpRes);
    }*/
    public static boolean sendsmc(QueueManager queueManager, String data, SelectionKey selectionKey, SocketChannel socketChannel) {
        TcpRes tcpRes = null;
        try {
            tcpRes = TcpFormatUtils.getRes(data);
            tcpRes.setSourceIp(socketChannel.socket().getInetAddress().getHostAddress());
            tcpRes.setPort(String.valueOf(socketChannel.socket().getPort()));
            if (tcpRes.getImei() != null || !tcpRes.getImei().trim().isEmpty()) {
                if (selectionKey.attachment() == null || ((String) selectionKey.attachment()).equals(tcpRes.getImei())) {
                    selectionKey.attach(tcpRes.getImei());
                }
            }
            logger.info("Thread:" + Thread.currentThread().getName() + " 接收到SocketChannel  Tcp" + tcpRes);
            /*return SendSmcUtil.sendSmcList(tcpRes,queueManager);*/
            return queueManager.addSmcQueue(tcpRes);
        } catch (Exception e) {
            logger.error(data);
            logger.error("解析出错：", e);
            return false;
        }
    }

    /**
     * 这里是bio调用的  已废弃
     *
     * @param socket
     * @param list
     * @return
     */
    public static boolean sendsmu(Socket socket, List<TcpReq> list) {
        OutputStream os = null;
        try {
            os = socket.getOutputStream();
            for (TcpReq tcpReq : list) {
                String strData = TcpFormatUtils.getReq(tcpReq);
                logger.debug("Thread:" + Thread.currentThread().getName() + " 发送到Socket Tcp" + tcpReq);
                os.write(strData.getBytes());
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public static boolean ReadAndaddQueue(ByteBuffer byteBuffer, SocketChannel socketChannel, QueueManager queueManager, SelectionKey selectionKey) throws IOException {
        int len = 0;
        StringBuffer sb = new StringBuffer();
        boolean eof = false;
        String[] strs = null;
        Integer sum = 0;

        byteBuffer.flip();
        byteBuffer.clear();
        try{

            while ((len = socketChannel.read(byteBuffer)) > 0) {
                byteBuffer.flip();
                sum += len;
                String str = new String(byteBuffer.array(), 0, len);
                sb.append(str);
                int index = 0;
                if ((index = sb.toString().indexOf("]")) != -1) {
                    String strr = sb.substring(0, index + 1);
                    sb.delete(0, index + 1);
                    if (strr.contains("]")) {
                        sendsmc(queueManager, strr, selectionKey, socketChannel);
                    }
                    //eof = true;
                }
            }
            byteBuffer.clear();
        }catch (IOException e){
            logger.error("socketChannel.read",byteBuffer.toString());
            logger.error("socketChannel.read",new String(byteBuffer.array()));
            throw new IOException(e);
        }
            /*if (eof) {
                for (String string : strs) {
                    sendsmc(queueManager, string, selectionKey ,socketChannel);
                }
                //清空  如果同一条数据只发一半过来会出错
                sb.setLength(0);
                eof = false;
            }*/
            //byteBuffer.compact();
            byteBuffer.clear();



        if (len == -1) {
            socketChannel.close();
        }

        if (sum == 0) {
            return false;
        }
        return true;
    }

   /* public static boolean ReadAndaddQueue(ByteBuffer byteBuffer, SocketChannel socketChannel, QueueManager queueManager,SelectionKey selectionKey) throws IOException {
        int len = 0;
        StringBuffer sb = new StringBuffer();
        boolean eof = false;
        String[] strs = null;
        Integer sum = 0;
        byteBuffer.flip();
        byteBuffer.clear();
        while ((len = socketChannel.read(byteBuffer)) > 0) {
            byteBuffer.flip();
            sum +=len;
            String str = new String(byteBuffer.array(), 0, len);
            sb.append(str);
            //byteBuffer.compact();
            byteBuffer.clear();
        }
        if(sum == 0){
            return false;
        }
        strs = sb.toString().split("]");
        for (String string : strs) {
            sendsmc(queueManager, string+"]", selectionKey ,socketChannel);
        }
        //清空  如果同一条数据只发一半过来会出错
        sb.setLength(0);
        return true;
    }*/

    public static boolean WriteSmu(ByteBuffer byteBuffer, SocketChannel socketChannel, List<TcpReq> list) throws IOException {
        for (TcpReq tcpReq : list) {
            String data = TcpFormatUtils.getReq(tcpReq);
            byteBuffer.put(data.getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            logger.info("write:" + data);
            byteBuffer.clear();
        }

        list.clear();
        return true;
    }

    public static boolean WriteSmuObject(ByteBuffer byteBuffer, SocketChannel socketChannel, TcpReq tcpReq) throws IOException {
        String data = TcpFormatUtils.getReq(tcpReq);
        byteBuffer.put(data.getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        logger.info("write:" + data);
        byteBuffer.clear();

        return true;
    }


}
