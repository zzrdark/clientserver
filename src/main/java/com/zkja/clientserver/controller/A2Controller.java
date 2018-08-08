package com.zkja.clientserver.controller;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zkja.clientserver.thread.socket.SocketManager;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkja.clientserver.common.GsonUtil;
import com.zkja.clientserver.common.SmuConstant;
import com.zkja.clientserver.domain.TcpReq;
import com.zkja.clientserver.domain.TcpRes;
import com.zkja.clientserver.thread.QueueManager;

/**
 * @author zzr
 */
@RestController
public class A2Controller {

	@Autowired
    private QueueManager queueManager;

	@Autowired
	private SocketManager socketManager;


	private static Logger logger = LoggerFactory.getLogger(A2Controller.class);
    /**
     * smu监禁/解禁
     * @throws Exception 
     */
    @RequestMapping("imprisonUrl")
    public void imprisonUrl(HttpServletRequest request, HttpServletResponse response) throws Exception{
        	Long startTime = System.currentTimeMillis();
    		TcpReq tcpReq = beforeRequest(request);
			socketManager.addSmuQueue(tcpReq, queueManager);
    		TcpRes tcpRes = null;
    		while(true){
	    		if((tcpRes=queueManager.pollSmcQueue())!= null){
	    			if(SmuConstant.ACTION_IMPRISON_DISABLE.equals(tcpReq.getBwlx())){
	    				if(SmuConstant.ACTION_SMU_IMPRISON_ANSWER.equals(tcpRes.getBwlx())){
	    					reponseSmc(tcpRes,response);
	    					break;
	    				}else{
	    					queueManager.addSmcQueue(tcpRes);
	    				}
	            	}else if(SmuConstant.ACTION_IMPRISON_ENABLED.equals(tcpReq.getBwlx())){
	            		if(SmuConstant.ACTION_SMU_RELIEVE_ANSWER.equals(tcpRes.getBwlx())){
	            			reponseSmc(tcpRes,response);
	            			break;
	            		}else{
	            			queueManager.addSmcQueue(tcpRes);
	            		}
	            	}
				 
	    		}
				if (timeout(startTime)){
					return;
				}
    		}
    }
    /**
     * 启动定时报告StartReport
     */
    @RequestMapping("startReportUrl")
    public void startReportUrl(HttpServletRequest request, HttpServletResponse response){
		Long startTime = System.currentTimeMillis();
    	try {
    		TcpReq tcpReq = beforeRequest(request);
			socketManager.addSmuQueue(tcpReq, queueManager);
    		TcpRes tcpRes = null;
    		while(true){
	    		if((tcpRes=queueManager.pollSmcQueue())!= null){
					 if(SmuConstant.ACTION_SMU_STARTREPORT_ANSWER.equals(tcpRes.getBwlx())){
						 reponseSmc(tcpRes,response);
						 break;
					 }else{
						 queueManager.addSmcQueue(tcpRes);
					 }
				 
	    		}
				if (timeout(startTime)){
					return;
				}
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    /**
     * 停止定时报告StopReport
     */
    @RequestMapping("stopReportUrl")
    public void stopReportUrl(HttpServletRequest request, HttpServletResponse response){
		Long startTime = System.currentTimeMillis();
    	try {
    		TcpReq tcpReq = beforeRequest(request);
			socketManager.addSmuQueue(tcpReq, queueManager);
    		TcpRes tcpRes = null;
    		while(true){
	    		if((tcpRes=queueManager.pollSmcQueue())!= null){
	    			if(SmuConstant.ACTION_SMU_STOPREPORT_ANSWER.equals(tcpRes.getBwlx())){
	    				reponseSmc(tcpRes,response);
	    				break;
	    			}else{
	    				queueManager.addSmcQueue(tcpRes);
	    			}
	    			
	    		}
				if (timeout(startTime)){
					return;
				}
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    /**
     * 请求SMU上传当前状态SmuInfo
     */
    @RequestMapping("smuInfoUrl")
    public void smuInfoUrl(HttpServletRequest request, HttpServletResponse response){
		Long startTime = System.currentTimeMillis();
    	try {
    		TcpReq tcpReq = beforeRequest(request);
			socketManager.addSmuQueue(tcpReq, queueManager);
    		TcpRes tcpRes = null;
    		while(true){
	    		if((tcpRes=queueManager.pollSmcQueue())!= null){
	    			if(SmuConstant.ACTION_SMU_SMUINFO_ANSWER.equals(tcpRes.getBwlx())){
	    				reponseSmc(tcpRes,response);
	    				break;
	    			}else{
	    				queueManager.addSmcQueue(tcpRes);
	    			}
	    			
	    		}
				if (timeout(startTime)){
					return;
				}
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    /**
     * 设置协议SysConf
     */
    @RequestMapping("sysConfUrl")
    public void sysConfUrl(HttpServletRequest request, HttpServletResponse response){
		Long startTime = System.currentTimeMillis();
    	try {
    		TcpReq tcpReq = beforeRequest(request);
			socketManager.addSmuQueue(tcpReq, queueManager);
    		TcpRes tcpRes = null;
    		while(true){
	    		if((tcpRes=queueManager.pollSmcQueue())!= null){
	    			if(SmuConstant.ACTION_SMU_SMUINFO_ANSWER.equals(tcpRes.getBwlx())){
	    				reponseSmc(tcpRes,response);
	    				break;
	    			}else{
	    				queueManager.addSmcQueue(tcpRes);
	    			}
	    			
	    		}
				if (timeout(startTime)){
					return;
				}
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    /**
     * 围栏协议　Fence
     */
    @RequestMapping("sysFenceUrl")
    public void fenceUrl(HttpServletRequest request, HttpServletResponse response){
		Long startTime = System.currentTimeMillis();
    	try {
    		TcpReq tcpReq = beforeRequest(request);
			socketManager.addSmuQueue(tcpReq, queueManager);
    		TcpRes tcpRes = null;
    		while(true){
	    		if((tcpRes=queueManager.pollSmcQueue())!= null){
	    			if(SmuConstant.ACTION_SMU_FENCE_ANSWER.equals(tcpRes.getBwlx())){
	    				reponseSmc(tcpRes,response);
	    				break;
	    			}else{
	    				queueManager.addSmcQueue(tcpRes);
	    			}
	    			
	    		}
				if (timeout(startTime)){
					return;
				}
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    /**
     * 设置灯协议
     * @throws Exception 
     */
    @RequestMapping("lightUrl")
    public void lightUrl(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Long startTime = System.currentTimeMillis();
    	TcpReq tcpReq = beforeRequest(request);
		socketManager.addSmuQueue(tcpReq, queueManager);
		TcpRes tcpRes = null;
		while(true){
			if((tcpRes=queueManager.pollSmcQueue())!= null){
				if(SmuConstant.ACTION_SMU_LIGHT_ANSWER.equals(tcpRes.getBwlx())){
					reponseSmc(tcpRes,response);
					break;
				}else{
					queueManager.addSmcQueue(tcpRes);
				}
				
			}
			if (timeout(startTime)){
				return;
			}
		}
    }
    /**
     * 通用协议 wn
     * @throws Exception 
     */
    @RequestMapping("wnUrl")
    public void wnUrl(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Long startTime = System.currentTimeMillis();
    	TcpReq tcpReq = beforeRequest(request);
		socketManager.addSmuQueue(tcpReq, queueManager);
    	TcpRes tcpRes = null;
    	while(true){
	    	if((tcpRes=queueManager.pollSmcQueue())!= null){
	    		if(SmuConstant.ACTION_SMU_WN_ANSWER.equals(tcpRes.getBwlx())){
	    			reponseSmc(tcpRes,response);
	    			break;
	    		}else{
	    			queueManager.addSmcQueue(tcpRes);
	    		}
	    		
	    	}
			if (timeout(startTime)){
				return;
			}
    	}
    }
    /**
     * SMC中文下发F6--TCP报文
     * @throws Exception 
     */
    @RequestMapping("pushUrl")
    public void issuedUrl(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Long startTime = System.currentTimeMillis();
    	TcpReq tcpReq = beforeRequest(request);
		socketManager.addSmuQueue(tcpReq, queueManager);
    	TcpRes tcpRes = null;
    	while(true){
	    	if((tcpRes=queueManager.pollSmcQueue())!= null){
	    		if(SmuConstant.ACTION_SMU_F6_ANSWER.equals(tcpRes.getBwlx())){
	    			reponseSmc(tcpRes,response);
	    			break;
	    		}else{
	    			queueManager.addSmcQueue(tcpRes);
	    		}
	    		
	    	}
			if (timeout(startTime)){
				return;
			}
    	}
    }
    /**
     * SMC设置SMU工作参数值 F7－TCP报文
     * @throws Exception 
     */
    @RequestMapping("gpsUrl")
    public void setupSmuUrl(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Long startTime = System.currentTimeMillis();
    	TcpReq tcpReq = beforeRequest(request);
    	logger.info(tcpReq.toString());
		socketManager.addSmuQueue(tcpReq, queueManager);
    	TcpRes tcpRes = null;
    	while(true){
    		if((tcpRes=queueManager.pollSmcQueue())!= null){
    			if(SmuConstant.ACTION_SMU_F7_ANSWER.equals(tcpRes.getBwlx())){
    				reponseSmc(tcpRes,response);
    				break;
    			}else{
    				queueManager.addSmcQueue(tcpRes);
    			}
    			
    		}
			if (timeout(startTime)){
				return;
			}
    	}
    }
    /**
     *SMU运动参数定时报告时间设置 F8－TCP报文
     * @throws Exception 
     */
    @RequestMapping("runSetTimeUrl")
    public void setupSportUrl(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Long startTime = System.currentTimeMillis();
    	TcpReq tcpReq = beforeRequest(request);
		socketManager.addSmuQueue(tcpReq, queueManager);
    	TcpRes tcpRes = null;
    	while(true){
	    	if((tcpRes=queueManager.pollSmcQueue())!= null){
	    		if(SmuConstant.ACTION_SMU_F8_ANSWER.equals(tcpRes.getBwlx())){
	    			reponseSmc(tcpRes,response);
	    			break;
	    		}else{
	    			queueManager.addSmcQueue(tcpRes);
	    		}
	    		
	    	}
			if (timeout(startTime)){
				return;
			}
    	}
    }
    /**
     *SMU当前运动参数请求报告G0-TCP报文
     * @throws Exception 
     */
    @RequestMapping("runInfoUrl")
    public void nowSportUrl(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Long startTime = System.currentTimeMillis();
    	TcpReq tcpReq = beforeRequest(request);
		socketManager.addSmuQueue(tcpReq, queueManager);
    	TcpRes tcpRes = null;
    	while(true){
	    	if((tcpRes=queueManager.pollSmcQueue())!= null){
	    		if(SmuConstant.ACTION_SMU_G0_ANSWER.equals(tcpRes.getBwlx())){
	    			reponseSmc(tcpRes,response);
	    			break;
	    		}else{
	    			queueManager.addSmcQueue(tcpRes);
	    		}
	    		
	    	}
			if (timeout(startTime)){
				return;
			}
    	}
    }
    
    /**
     *实时报告请求 F10/FB-TCP报文
     * @throws Exception 
     */
    @RequestMapping("smuInfo_newUrl")
    public void realtimeReportUrl(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Long startTime = System.currentTimeMillis();
    	TcpReq tcpReq = beforeRequest(request);
		socketManager.addSmuQueue(tcpReq, queueManager);
    	TcpRes tcpRes = null;
    	while(true){
	    	if((tcpRes=queueManager.pollSmcQueue())!= null){
	    		if(SmuConstant.ACTION_REPORT_REALTIME_ANSWER.equals(tcpRes.getBwlx())||SmuConstant.ACTION_REPORT_AB_ANSWER.equals(tcpRes.getBwlx()) ){
	    			reponseSmc(tcpRes,response);
	    			break;
	    		}else{
	    			queueManager.addSmcQueue(tcpRes);
	    		}
	    		
	    	}
			if (timeout(startTime)){
				return;
			}
    	}
    }
    
    /**
     *	健康参数初始化校准协议FC-TCP报文
     * @throws Exception 
     */
    @RequestMapping("healthParamsUrl")
    public void healthParamsUrl(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Long startTime = System.currentTimeMillis();
    	TcpReq tcpReq = beforeRequest(request);
		socketManager.addSmuQueue(tcpReq, queueManager);
    	TcpRes tcpRes = null;
    	while(true){
	    	if((tcpRes=queueManager.pollSmcQueue())!= null){
	    		if(SmuConstant.ACTION_HEALTHPARAMS.equals(tcpRes.getBwlx())){
	    			reponseSmc(tcpRes,response);
	    			break;
	    		}else{
	    			queueManager.addSmcQueue(tcpRes);
	    		}
	    		
	    	}
			if (timeout(startTime)){
				return;
			}
    	}
    }
    /**
     *	蓝牙固定标签配置协议FD-TCP报文
     * @throws Exception 
     */
    @RequestMapping("bluetoothSetupUrl")
    public void bluetoothSetupUrl(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Long startTime = System.currentTimeMillis();
    	TcpReq tcpReq = beforeRequest(request);
		socketManager.addSmuQueue(tcpReq, queueManager);
    	TcpRes tcpRes = null;
    	while(true){
	    	if((tcpRes=queueManager.pollSmcQueue())!= null){
	    		if(SmuConstant.ACTION_BLUETOOTHSETUP_ANSWER.equals(tcpRes.getBwlx())){
	    			reponseSmc(tcpRes,response);
	    			break;
	    		}else{
	    			queueManager.addSmcQueue(tcpRes);
	    		}
	    		
	    	}
			if (timeout(startTime)){
	    		return;
			}
    	}
    }
    
    protected void output(HttpServletResponse response, String jsonResult) {
		try {
			response.setContentType("application/json; charset=UTF-8");

			response.setContentLength(jsonResult.getBytes("UTF-8").length);
			logger.info("response json: {}" + jsonResult);
			//response.getOutputStream().write(jsonResult.getBytes());
			// printStream.println(result);
			PrintWriter printWriter=response.getWriter();
			printWriter.write(jsonResult);
            printWriter.flush();
			//response.getOutputStream().
		} catch (Exception e) {
			logger.error("Error output json data to the client!!!orginal json={}" + jsonResult, e);
		}
	}

    public void reponseSmc(TcpRes tcpRes,HttpServletResponse response){
		String respJson = GsonUtil.getJson(tcpRes,tcpRes.getBwlsh());
		logger.info("respJson===" + respJson);
		output(response, respJson);
    }
    
    public TcpReq beforeRequest(HttpServletRequest request) throws Exception{
        String jsonData = Streams.asString(request.getInputStream(), "gbk");
        GsonUtil.getEntityFromRequest(jsonData, TcpReq.class);

        logger.info("request==="+jsonData);
        TcpReq tcpReq = (TcpReq) GsonUtil.getEntityFromRequest(
                jsonData, TcpReq.class);
        String remotePort = request.getRemotePort()+"";
        String ssPort = (String) request.getParameter("port");
        String sourceIp = (String) request.getParameter("sourceIp");
        logger.info("url Parameter----ssPort="+ssPort+",sourceIp="+sourceIp);
        return tcpReq;
    }

    public boolean timeout(Long startTime){
    	Long time = System.currentTimeMillis() - startTime;
    	if(time>10000){
			return true;
		}
		return false;

	}
}
