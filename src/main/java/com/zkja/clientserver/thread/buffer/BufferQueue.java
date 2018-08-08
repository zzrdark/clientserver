package com.zkja.clientserver.thread.buffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zzr
 */
public class BufferQueue {
    private static Logger logger = LoggerFactory.getLogger(BufferQueue.class);
    private static Queue<Object> queue = new LinkedList<Object>();

    private static int INITSIZE = 2;

    private Lock mutex;

    private Condition condition;

    private BufferQueue(){
        mutex = new ReentrantLock();
        condition = mutex.newCondition();
    }

    public static BufferQueue getIntance(){
        return QueueBuffer.instance;
    }

    static class QueueBuffer{
        private static BufferQueue instance = new BufferQueue();
    }

    public void setInitSize(int size){
        INITSIZE = size;
    }

    public void produce(String msg){
        mutex.lock();
        try {
            while(queue.size() >= INITSIZE ){
                logger.info("wait to consume");
                condition.await();
            }
            queue.offer(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            condition.signalAll();
            mutex.unlock();
        }

    }

    public Object consume(){
        mutex.lock();
        try {
            while (queue.size() == 0) {
                logger.info("queue wait to produce");
                condition.await();
            }

            return queue.poll();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            condition.signalAll();
            mutex.unlock();
        }
    }

    public int getQueueSize(){
        return queue.size();
    }
}
