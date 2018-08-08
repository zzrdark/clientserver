package com.zkja.clientserver.thread;

import com.zkja.clientserver.domain.TcpReq;
import com.zkja.clientserver.domain.TcpRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zzr
 */
@Component
public class SocketQueueManager extends QueueManager {

    Logger logger = LoggerFactory.getLogger(SocketQueueManager.class);

    private Map<String,List<TcpReq>> map = new ConcurrentHashMap<String,List<TcpReq>>();


    private void addCache(){
        for(String str:map.keySet()){
            if (map.get(str).size()==0){
                map.remove(str);
            }
        }
        if(map.size()<100){
            for(int i =0;i<50;i++){
                if(!pullImeiMap()){
                    break ;
                }
            }
        }

    }

    public synchronized Map getImeiMap(){
        addCache();
        return map;
    }

    public List<TcpReq> getImeiList(String imei){
        List<TcpReq> list = map.remove(imei);
        return list;
    }

    /**
     * 返回false  pollSmuQueue 为 空
     * @return
     */
    private boolean pullImeiMap(){
        List<TcpReq> list = null;
        TcpReq tcpReq = super.pollSmuQueue();

        if(tcpReq == null){
            return false;
        }

        if(tcpReq.getImei()==null){
            logger.info("获取到Imei为空的报文，报文类型为"+tcpReq.getBwlx());
            return true;
        }else {
            if ((list = map.get(tcpReq.getImei()))!= null) {
                list.add(tcpReq);
                return true;
            }else {
                List<TcpReq> listNew = new LinkedList<TcpReq>();
                listNew = Collections.synchronizedList(listNew);
                listNew.add(tcpReq);
                map.put(tcpReq.getImei(),listNew);
                return true;
            }
        }

    }

}
