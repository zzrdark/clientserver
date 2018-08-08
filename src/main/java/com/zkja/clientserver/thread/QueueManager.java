package com.zkja.clientserver.thread;

import com.zkja.clientserver.domain.TcpReq;
import com.zkja.clientserver.domain.TcpRes;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @authon zzr
 */

public class QueueManager {
    LinkedBlockingQueue<TcpReq> sendSmuQueue = new LinkedBlockingQueue<TcpReq>();
    LinkedBlockingQueue<TcpRes> sendSmcQueue = new LinkedBlockingQueue<TcpRes>();
    public static AtomicInteger count = new AtomicInteger();
    public static AtomicInteger countasd = new AtomicInteger();
    Logger logger = Logger.getLogger(QueueManager.class);
    public boolean addSmuQueue(TcpReq tcpReq){
        return sendSmuQueue.offer(tcpReq);
    }
    public boolean addSmcQueue(TcpRes tcpRes){
        logger.debug("sendSmcQueue: "+ tcpRes);
        return sendSmcQueue.offer(tcpRes);
    }

    public TcpReq pollSmuQueue(){
        TcpReq tcpReq= sendSmuQueue.poll();
        return tcpReq;

    }
    public TcpRes pollSmcQueue(){
    	
        TcpRes tcpRes = sendSmcQueue.poll();
        if (tcpRes==null){
            return null;
        }
        
        if(System.currentTimeMillis()-tcpRes.getTime()>10000){
        	logger.info("超时设备:"+tcpRes.getImei());
            return pollSmcQueue();
        }
        count.incrementAndGet();
        logger.info("smc队列大小："+getSmcSize()+"消费了："+count);
        return tcpRes;
    }

    public int getSmcSize(){
    	return sendSmcQueue.size();
    }

}
