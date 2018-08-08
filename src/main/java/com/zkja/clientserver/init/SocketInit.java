package com.zkja.clientserver.init;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.zkja.clientserver.common.GsonUtil;
import com.zkja.clientserver.common.HttpGetPostUtil;
import com.zkja.clientserver.common.SmuConstant;
import com.zkja.clientserver.domain.TcpReq;
import com.zkja.clientserver.domain.TcpRes;
import com.zkja.clientserver.property.ServerSocketProperties;
import com.zkja.clientserver.thread.NioServerSocketRunnable;
import com.zkja.clientserver.thread.QueueManager;
import com.zkja.clientserver.thread.socket.SocketManager;

/**
@author linsq
@date 2018年7月20日上午10:08:54
*/
@Component
@EnableConfigurationProperties(ServerSocketProperties.class)
public class SocketInit implements ApplicationRunner{
	
	private static Logger logger = LoggerFactory.getLogger(SocketInit.class);

	
	@Autowired
    private ServerSocketProperties serverSocketProperties;

	@Autowired
    private QueueManager queueManager;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private  SocketManager socketManager;
    
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		 new Thread(new NioServerSocketRunnable(serverSocketProperties, queueManager, taskExecutor, socketManager)).start();
		 new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					//接收到设备tcp数据
					 TcpRes tcpRes = null;
					 while (true){
						 if((tcpRes=queueManager.pollSmcQueue())!= null){
//							 String url = "http://localhost:8080/smcServer/servlet/a1";
							 String url = serverSocketProperties.getSmcUrl()+"?sourceIp="+tcpRes.getSourceIp()+"&port="+tcpRes.getPort();

							 if(SmuConstant.ACTION_LOGIN.equals(tcpRes.getBwlx())){
								 //解析出数据发送到smc
								 Map<String, String> paramsMap = new HashMap<String, String>();
								 paramsMap.put("bwlx", SmuConstant.ACTION_LOGIN);
								 paramsMap.put("bwlsh", tcpRes.getBwlsh());
								 paramsMap.put("imei", tcpRes.getImei());
								 paramsMap.put("pid", tcpRes.getPid());
								 paramsMap.put("sjh", tcpRes.getSjh());
								 paramsMap.put("yjlx",tcpRes.getYjlx());
								 String reqJson = GsonUtil.toJson(paramsMap);
								 logger.info("requestLogin==="+reqJson);
								 String respJson = HttpGetPostUtil.doPostStr(url, paramsMap);
								 logger.info("responeLogin==="+respJson);
								 Map<String, String> map = GsonUtil.jsonToMap(respJson);
								 TcpReq tcpReqSmu = new TcpReq();
								 tcpReqSmu.setBwlsh(map.get("bwlsh"));
								 tcpReqSmu.setBwlx(map.get("bwlx"));
								 tcpReqSmu.setDsscsj(map.get("dsscsj"));
								 tcpReqSmu.setImei(map.get("imei"));
								 tcpReqSmu.setPid(map.get("pid"));
								 tcpReqSmu.setQdlx(map.get("qdlx"));
								 tcpReqSmu.setSfjj(map.get("sfjj"));
								 tcpReqSmu.setSjh(map.get("sjh"));
								 tcpReqSmu.setSjtb(map.get("sjtb"));
								 socketManager.addSmuQueue(tcpReqSmu,queueManager);
							 }else if(SmuConstant.ACTION_LOGOUT.equals(tcpRes.getBwlx())){
								 //解析出数据发送到smc
								 Map<String, String> paramsMap = new HashMap<String, String>();
								 paramsMap.put("bwlx", SmuConstant.ACTION_LOGOUT);
								 paramsMap.put("bwlsh", tcpRes.getBwlsh());
								 paramsMap.put("imei", tcpRes.getImei());
								 paramsMap.put("pid", tcpRes.getPid());
								 paramsMap.put("sjh", tcpRes.getSjh());
								 String reqJson = GsonUtil.toJson(paramsMap);
								 logger.info("requestLogout==="+reqJson);
								 String respJson = HttpGetPostUtil.doPostStr(url, paramsMap);
								 logger.info("responeLogout==="+respJson);
								 Map<String, String> map = GsonUtil.jsonToMap(respJson);
								 System.out.println(map);
								 TcpReq tcpReqSmu = new TcpReq();
								 tcpReqSmu.setBwlsh(map.get("bwlsh"));
								 tcpReqSmu.setBwlx(map.get("bwlx"));
								 tcpReqSmu.setImei(map.get("imei"));
								 tcpReqSmu.setPid(map.get("pid"));
								 tcpReqSmu.setSjh(map.get("sjh"));
								 socketManager.addSmuQueue(tcpReqSmu,queueManager);
							 }else if(SmuConstant.ACTION_HEARTBEAT.equals(tcpRes.getBwlx())){
								 //解析出数据发送到smc
								 Map<String, String> paramsMap = new HashMap<String, String>();
								 paramsMap.put("bwlx", SmuConstant.ACTION_HEARTBEAT);
								 paramsMap.put("bwlsh", tcpRes.getBwlsh());
								 paramsMap.put("imei", tcpRes.getImei());
								 paramsMap.put("pid", tcpRes.getPid());
								 paramsMap.put("sjh", tcpRes.getSjh());
								 String reqJson = GsonUtil.toJson(paramsMap);
								 logger.info("requestHeartbeat==="+reqJson);
								 String respJson = HttpGetPostUtil.doPostStr(url, paramsMap);
								 logger.info("responeHeartbeat==="+respJson);
								 Map<String, String> map = GsonUtil.jsonToMap(respJson);
								 TcpReq tcpReqSmu = new TcpReq();
								 tcpReqSmu.setBwlsh(map.get("bwlsh"));
								 tcpReqSmu.setBwlx(map.get("bwlx"));
								 tcpReqSmu.setImei(map.get("imei"));
								 tcpReqSmu.setPid(map.get("pid"));
								 tcpReqSmu.setSjh(map.get("sjh"));
								 socketManager.addSmuQueue(tcpReqSmu,queueManager);
							 }else if(SmuConstant.ACTION_SMU_SETUP.equals(tcpRes.getBwlx())){
								 //解析出数据发送到smc
								 Map<String, String> paramsMap = new HashMap<String, String>();
								 paramsMap.put("bwlx", SmuConstant.ACTION_SMU_SETUP);
								 paramsMap.put("bwlsh", tcpRes.getBwlsh());
								 paramsMap.put("imei", tcpRes.getImei());
								 paramsMap.put("pid", tcpRes.getPid());
								 paramsMap.put("sjh", tcpRes.getSjh());
								 String reqJson = GsonUtil.toJson(paramsMap);
								 logger.info("requestSmusetup==="+reqJson);
								 String respJson = HttpGetPostUtil.doPostStr(url, paramsMap);
								 logger.info("responeSmusetup==="+respJson);
								 Map<String, String> map = GsonUtil.jsonToMap(respJson);
								 System.out.println(map);
								 TcpReq tcpReqSmu = new TcpReq();
								 tcpReqSmu.setBwlsh(map.get("bwlsh"));
								 tcpReqSmu.setBwlx(map.get("bwlx"));
								 tcpReqSmu.setImei(map.get("imei"));
								 tcpReqSmu.setPid(map.get("pid"));
								 tcpReqSmu.setSjh(map.get("sjh"));
								 socketManager.addSmuQueue(tcpReqSmu,queueManager);
							 }else if(SmuConstant.ACTION_REPORT.equals(tcpRes.getBwlx())){
								 //解析出数据发送到smc
								 Map<String, String> paramsMap = new HashMap<String, String>();
								 paramsMap.put("bwlx", SmuConstant.ACTION_REPORT);
						    	 paramsMap.put("bwlsh", tcpRes.getBwlsh());
						    	 paramsMap.put("imei", tcpRes.getImei());
						    	 paramsMap.put("pid", tcpRes.getPid());
						    	 paramsMap.put("sjh", tcpRes.getSjh());
						    	 paramsMap.put("qdlx", tcpRes.getQdlx());
						    	 paramsMap.put("cbwlx", "GPRMC");
//						    	 String time = new SimpleDateFormat("yyyymmddHHMMSS").format(new Date());
						    	 paramsMap.put("gpstime", tcpRes.getGpstime());
						    	 paramsMap.put("av", tcpRes.getAv());
						    	 paramsMap.put("la", tcpRes.getLa());
						    	 paramsMap.put("wdbs", tcpRes.getWdbs());
						    	 paramsMap.put("lo", tcpRes.getLo());
						    	 paramsMap.put("jdbs", tcpRes.getJdbs());
						    	 paramsMap.put("sd", tcpRes.getSd());
						    	 paramsMap.put("fx", tcpRes.getFx());
						    	 paramsMap.put("locid", tcpRes.getLocid());
						    	 paramsMap.put("ccid", tcpRes.getCcid());
						    	 paramsMap.put("sjdl", tcpRes.getSjdl());
						    	 paramsMap.put("sbdl", tcpRes.getSbdl());
						    	 paramsMap.put("dssclx", tcpRes.getDssclx());
						    	 paramsMap.put("sjjg", tcpRes.getSjjg());
						    	 paramsMap.put("jqlx", tcpRes.getJqlx());
						    	 paramsMap.put("sbaz", tcpRes.getSbaz());
						         paramsMap.put("sfjj", tcpRes.getSfjj());
								 String reqJson = GsonUtil.toJson(paramsMap);
								 logger.info("requestReport==="+reqJson);
								 String respJson = HttpGetPostUtil.doPostStr(url, paramsMap);
								 logger.info("responeReport==="+respJson);
								 Map<String, String> map = GsonUtil.jsonToMap(respJson);
								 System.out.println(map);
								 TcpReq tcpReqSmu = new TcpReq();
								 tcpReqSmu.setBwlsh(map.get("bwlsh"));
								 tcpReqSmu.setBwlx(map.get("bwlx"));
								 tcpReqSmu.setImei(map.get("imei"));
								 tcpReqSmu.setPid(map.get("pid"));
								 tcpReqSmu.setSjh(map.get("sjh"));
								 socketManager.addSmuQueue(tcpReqSmu,queueManager);
							 }else if(SmuConstant.ACTION_REPORT_NEW.equals(tcpRes.getBwlx())){
								 //解析出数据发送到smc
								 Map<String, String> paramsMap = new HashMap<String, String>();
								 paramsMap.put("bwlx", SmuConstant.ACTION_REPORT_NEW);
						    	 paramsMap.put("bwlsh", tcpRes.getBwlsh());
						    	 paramsMap.put("imei", tcpRes.getImei());
						         paramsMap.put("pid", tcpRes.getPid());
						    	 paramsMap.put("sjh", tcpRes.getSjh());
						    	 paramsMap.put("qdlx", tcpRes.getQdlx());
						    	 paramsMap.put("cbwlx", "GPRMC");
//						    	 String time = new SimpleDateFormat("yyyymmddHHMMSS").format(new Date());
						    	 paramsMap.put("gpstime", tcpRes.getGpstime());
						    	 paramsMap.put("av", tcpRes.getAv());
						    	 paramsMap.put("la", tcpRes.getLa());
						    	 paramsMap.put("wdbs", tcpRes.getWdbs());
						    	 paramsMap.put("lo", tcpRes.getLo());
						    	 paramsMap.put("jdbs", tcpRes.getJdbs());
						    	 paramsMap.put("sd", tcpRes.getSd());
						    	 paramsMap.put("fx", tcpRes.getFx());
						    	 paramsMap.put("locid", tcpRes.getLocid());
						    	 paramsMap.put("ccid", tcpRes.getCcid());
						    	 paramsMap.put("sjdl", tcpRes.getSjdl());
						    	 paramsMap.put("sbdl", tcpRes.getSbdl());
						    	 paramsMap.put("dssclx", tcpRes.getDssclx());
						    	 paramsMap.put("sjjg", tcpRes.getSjjg());
						    	 paramsMap.put("jqlx", tcpRes.getJqlx());
						    	 paramsMap.put("sbaz", tcpRes.getSbaz());
						    	 paramsMap.put("sfjj", tcpRes.getSfjj());
						    	 paramsMap.put("iccid", tcpRes.getIccid());
						    	 paramsMap.put("jrfs", tcpRes.getJrfs());
						    	 paramsMap.put("cbwsm", tcpRes.getCbwsm());
						    	 paramsMap.put("yl", tcpRes.getYl());
						    	 paramsMap.put("mobilenet", tcpRes.getMobilenet());
						    	 paramsMap.put("wifi", tcpRes.getWifi());
						    	 paramsMap.put("bt", tcpRes.getBt());
						    	 paramsMap.put("terminalip", tcpRes.getTerminalip());
						    	 paramsMap.put("reserved", tcpRes.getReserved());
						    	 paramsMap.put("sourceip", tcpRes.getSourceIp());
						    	 paramsMap.put("reserved_wd", tcpRes.getReserved_wd());
						    	 paramsMap.put("reserved_xy", tcpRes.getReserved_xy());
						    	 paramsMap.put("reserved_xl", tcpRes.getReserved_xl());
						    	 paramsMap.put("reserved_jb", tcpRes.getReserved_jb());
						    	 paramsMap.put("reserved_ryzt", tcpRes.getReserved_ryzt());
						    	 paramsMap.put("reserved_xdgl", tcpRes.getReserved_xdgl());
								 String reqJson = GsonUtil.toJson(paramsMap);
								 logger.info("requestMbsLocate==="+reqJson);
								 String respJson = HttpGetPostUtil.doPostStr(url, paramsMap);
								 logger.info("responeMbsLocate==="+respJson);
								 Map<String, String> map = GsonUtil.jsonToMap(respJson);
								 TcpReq tcpReqSmu = new TcpReq();
								 tcpReqSmu.setBwlsh(map.get("bwlsh"));
								 tcpReqSmu.setBwlx(map.get("bwlx"));
								 tcpReqSmu.setImei(map.get("imei"));
								 tcpReqSmu.setPid(map.get("pid"));
								 tcpReqSmu.setSjh(map.get("sjh"));
								 socketManager.addSmuQueue(tcpReqSmu,queueManager);
							 }else if(SmuConstant.ACTION_REPORT_RUN.equals(tcpRes.getBwlx())){
								 //解析出数据发送到smc
								 Map<String, String> paramsMap = new HashMap<String, String>();
								 paramsMap.put("bwlx", SmuConstant.ACTION_REPORT_RUN);
						    	 paramsMap.put("bwlsh", tcpRes.getBwlsh());
						    	 paramsMap.put("imei", tcpRes.getImei());
						         paramsMap.put("pid", tcpRes.getPid());
						    	 paramsMap.put("sjh", tcpRes.getSjh());
						    	 paramsMap.put("sbwd", tcpRes.getSbwd());
						    	 paramsMap.put("ryxy",tcpRes.getRyxy());
//						    	 String time = new SimpleDateFormat("yyyymmddHHMMSS").format(new Date());
						    	 paramsMap.put("ryxl", tcpRes.getRyxl());
						    	 paramsMap.put("xzbs", tcpRes.getXzbs());
						    	 paramsMap.put("gl", tcpRes.getGl());
						    	 paramsMap.put("jlcs", tcpRes.getJlcs());
						    	 paramsMap.put("mostyxf", tcpRes.getMostyxf());
						    	 paramsMap.put("jbz", tcpRes.getJbz());
						    	 paramsMap.put("mopoyxf", tcpRes.getMopoyxf());
						    	 paramsMap.put("yddw", tcpRes.getYddw());
						    	 paramsMap.put("dycjlsj", tcpRes.getDycjlsj());
						    	 paramsMap.put("dycjlrq", tcpRes.getDycjlrq());
								 String reqJson = GsonUtil.toJson(paramsMap);
								 logger.info("requestSportReport==="+reqJson);
								 String respJson = HttpGetPostUtil.doPostStr(url, paramsMap);
								 logger.info("responeSportReport==="+respJson);
								 Map<String, String> map = GsonUtil.jsonToMap(respJson);
								 System.out.println(map);
								 TcpReq tcpReqSmu = new TcpReq();
								 tcpReqSmu.setBwlsh(map.get("bwlsh"));
								 tcpReqSmu.setBwlx(map.get("bwlx"));
								 tcpReqSmu.setImei(map.get("imei"));
								 tcpReqSmu.setPid(map.get("pid"));
								 tcpReqSmu.setSjh(map.get("sjh"));
								 socketManager.addSmuQueue(tcpReqSmu,queueManager);
							 }else if(SmuConstant.ACTION_WN.equals(tcpRes.getBwlx())){
								 Map<String, String> paramsMap = new HashMap<String, String>();
								 paramsMap.put("bwlx", SmuConstant.ACTION_WN);
								 paramsMap.put("bwlsh", tcpRes.getBwlsh());
								 paramsMap.put("imei", tcpRes.getImei());
								 paramsMap.put("pid", tcpRes.getPid());
								 paramsMap.put("sjh", tcpRes.getSjh());
								 paramsMap.put("nr",tcpRes.getNr());
								 String reqJson = GsonUtil.toJson(paramsMap);
								 logger.info("requestWnReport==="+reqJson);
								 String respJson = HttpGetPostUtil.doPostStr(url, paramsMap);
								 logger.info("responeWnReport==="+respJson);
								 Map<String, String> map = GsonUtil.jsonToMap(respJson);
								 TcpReq tcpReqSmu = new TcpReq();
								 tcpReqSmu.setBwlsh(map.get("bwlsh"));
								 tcpReqSmu.setBwlx(map.get("bwlx"));
								 tcpReqSmu.setImei(map.get("imei"));
								 tcpReqSmu.setPid(map.get("pid"));
								 tcpReqSmu.setSjh(map.get("sjh"));
								 socketManager.addSmuQueue(tcpReqSmu,queueManager);
							 }else{
								 queueManager.addSmcQueue(tcpRes);
							 }
						 }
					 }
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				
			}
		}).start();
	}

}


