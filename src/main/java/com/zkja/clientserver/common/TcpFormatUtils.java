package com.zkja.clientserver.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zkja.clientserver.controller.A2Controller;
import com.zkja.clientserver.domain.TcpReq;
import com.zkja.clientserver.domain.TcpRes;

import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @authon zzr
 */
public class TcpFormatUtils {

	private static Logger logger = LoggerFactory.getLogger(TcpFormatUtils.class);

    /**
     * 解析smu发来的报文
     * @param data
     * @return
     */
    public static TcpRes getRes(String data){
    	logger.info("smu返回的数据："+data);
    	System.out.println("smu返回的数据长度："+data.length());
        if(data==null){
            return null;
        }
        TcpRes t = new TcpRes();
        String bwlx = data.substring(1, 3);//报文类型
        try {
        	if(SmuConstant.ACTION_SMU_A1_ANSWER.equals(bwlx)){
        		String length = data.substring(12, 14);
        		boolean flag = false;
        		String lengthLong = data.substring(13, 15);
        		boolean flagLong = false;
        		int index = data.indexOf("(");
        		if(index!=-1){
        			if(length.equals(Integer.valueOf(index-14).toString())){
        				flag = true;
        			}
        			if(lengthLong.equals(Integer.valueOf(index-15).toString())){
        				flagLong = true;
        			}
        		}else{
        			if(length.equals(Integer.valueOf(data.length()-1-14).toString())){
        				flag = true;
        			}
        			if(lengthLong.equals(Integer.valueOf(data.length()-1-15).toString())){
        				flagLong = true;
        			}
        		}
        		if(flag){
        			bwlx = data.substring(1, 3);
        		}
        		if(flagLong){
        			data = data.substring(1,data.length());
        			bwlx = data.substring(0, 3);
        		}
        	}
        	String bwlsh = data.substring(3,12);//流水号,9位
        	String imei = data.substring(14, 29);//Imei码，15位
        	String pid = data.substring(29, 43);//pid码，14位
        	String sjh = data.substring(43, 54);//手机号码，11位
        	t.setBwlsh(bwlsh);
        	t.setImei(imei);
        	t.setBwlx(bwlx);
        	t.setPid(pid);
        	t.setSjh(sjh);
        	//登录
        	if (SmuConstant.ACTION_LOGIN.equals(bwlx)) {
        		t.setYjlx(data.substring(54, 56));//硬件类型
        	}else if(SmuConstant.ACTION_REPORT.equals(bwlx)){
        		int firstLeft = data.indexOf("(");
        		int firstRight = data.indexOf(")");
        		String s1 = data.substring(firstLeft+1,firstRight);
        		String [] arr1 =  s1.split(",");
        		t.setCbwlx(arr1[0]);
        		t.setAv(arr1[2]);
        		t.setLa(arr1[3]);
        		t.setWdbs(arr1[4]);
        		t.setLo(arr1[5]);
        		t.setJdbs(arr1[6]);
        		t.setSd(arr1[7]);
        		t.setFx(arr1[8]);
        		StringBuffer sb = new StringBuffer();
        		String year =  new SimpleDateFormat("yyyy").format(new Date());
        		sb.append(year.substring(0, 2));
        		String date = arr1[9];
        		sb.append(date.substring(4, date.length()));
        		sb.append(date.substring(2, 4));
        		sb.append(date.substring(0, 2));
        		t.setGpstime(sb.toString()+arr1[1].split("\\.")[0]);
        		int secondLeft = data.indexOf("(",firstRight);
        		int secondRight = data.indexOf(")",secondLeft);
        		String s2 = data.substring(secondLeft+1,secondRight);
        		t.setSjdl(s2.substring(6, 8));
        		t.setSbdl(s2.substring(8, 10));
        		t.setDssclx(s2.substring(10, 11));
        		t.setSjjg(s2.substring(11, 15));
        		t.setJqlx(s2.substring(6,21));
        		t.setSbaz(s2.substring(19,20));
        		t.setSfjj(s2.substring(20,21));
        		int lastLeft = data.lastIndexOf("(");
        		int lastRight = data.lastIndexOf(")");
        		String s3 = data.substring(lastLeft+1,lastRight);
        		String [] arr2 = s3.split(",");
        		t.setLocid(arr2[1]);
        		t.setCcid(arr2[2]);
        	}else if(SmuConstant.ACTION_REPORT_NEW.equals(bwlx)){
        		t.setQdlx("2");
        		t.setIccid(data.substring(54, 74));
        		t.setJrfs(data.substring(74, 75));
        		t.setCbwsm(data.substring(75, 76));
        		t.setYl(data.substring(76, 78));
        		int firstLeft = data.indexOf("(");
        		int firstRight = data.indexOf(")");
        		String s1 = data.substring(firstLeft+1,firstRight);
        		String []arr1 = s1.split(",");
        		t.setCbwlx(arr1[0]);
        		t.setAv(arr1[1]);
        		if(SmuConstant.ACTION_VALID.equals(arr1[1])){
        			String date = arr1[10];
        			StringBuffer sb = new StringBuffer();
        			String year =  new SimpleDateFormat("yyyy").format(new Date());
        			sb.append(year.substring(0, 2));
        			sb.append(date.substring(4, date.length()));
        			sb.append(date.substring(2, 4));
        			sb.append(date.substring(0, 2));
        			t.setGpstime(sb.toString()+arr1[2].split("\\.")[0]);
        			t.setLa(arr1[4]);
        			t.setWdbs(arr1[5]);
        			t.setJdbs(arr1[7]);
        			t.setLo(arr1[6]);
        			t.setSd(arr1[8]);
        			t.setFx(arr1[9]);
        		}else{
        			String date = arr1[3];
        			StringBuffer sb = new StringBuffer();
        			String year =  new SimpleDateFormat("yyyy").format(new Date());
        			sb.append(year.substring(0, 2));
        			sb.append(date.substring(4, date.length()));
        			sb.append(date.substring(2, 4));
        			sb.append(date.substring(0, 2));
        			t.setGpstime(sb.toString()+arr1[2].split("\\.")[0]);
        		}
        		int secondLeft = data.indexOf("(",firstRight);
        		int secondRight = data.indexOf(")",secondLeft);
        		String s2 = data.substring(secondLeft+1,secondRight);
        		t.setSjdl(s2.substring(8, 10));
        		t.setSbdl(s2.substring(10, 12));
        		t.setDssclx(s2.substring(12, 13));
        		t.setSjjg(s2.substring(13, 17));
        		t.setJqlx(s2.substring(8, 23));
        		t.setSbaz(s2.substring(21, 22));
        		t.setSfjj(s2.substring(22, 23));
        		
        		int thirdLeft = data.indexOf("(",secondRight);
        		int thirdRight = data.indexOf(")",thirdLeft);
        		String s3 = data.substring(thirdLeft+1,thirdRight);
        		String []arr3 = s3.split(",");
        		t.setMobilenet(data.substring(thirdLeft,thirdRight+1));
        		if(SmuConstant.ACTION_VALID.equals(arr3[1])){
        			String loc = arr3[8];
        			t.setLocid(loc.substring(0, 4));
        			t.setCcid(loc.substring(4, 12));
        		}
        		
        		int fourLeft = data.indexOf("(",thirdRight);
        		int fourRight = data.indexOf(")",fourLeft);
        		String s4 = data.substring(fourLeft,fourRight+1);
        		t.setWifi(s4);
        		
        		int fiveLeft = data.indexOf("(",fourRight);
        		int fiveRight = data.indexOf(")",fiveLeft);
        		String s5 = data.substring(fiveLeft,fiveRight+1);
        		t.setBt(s5);
        		
        		int sixLeft = data.indexOf("(",fiveRight);
        		int sixRight = data.indexOf(")",sixLeft);
        		String s6 = data.substring(sixLeft,sixRight+1);
        		t.setTerminalip(s6);
        		
        		int lastLeft = data.lastIndexOf("(");
        		int lastRight = data.lastIndexOf(")");
        		String s7 = data.substring(lastLeft,lastRight+1);
        		t.setReserved(s7);
        		
        		//手机电量那些，reserved_wd那些没有
        	}else if(SmuConstant.ACTION_REPORT_RUN.equals(bwlx)){
        		t.setSbwd(data.substring(54,57));
        		t.setRyxy(data.substring(57, 63));
        		t.setRyxl(data.substring(63, 66));
        		t.setXzbs(data.substring(66, 71));
        		t.setGl(data.substring(71, 73));
        		t.setJlcs(data.substring(74, 76));
        		int firstLeft = data.indexOf("(");
        		int firstRight = data.indexOf(")");
        		String s1 = data.substring(firstLeft+1,firstRight);
        		String [] arr1 =  s1.split(",");
        		t.setMostyxf(arr1[1]);
        		t.setJbz(s1.substring(7,42));
        		int lastLeft = data.lastIndexOf("(");
        		int lastRight = data.lastIndexOf(")");
        		String s2 = data.substring(lastLeft+1,lastRight);
        		String [] arr2 = s2.split(",");
        		t.setMopoyxf(arr2[1]);
        		t.setYddw(s2.substring(7,84));
        		t.setDycjlsj(data.substring(lastRight+2, lastRight+8));
        		t.setDycjlrq(data.substring(lastRight+9, lastRight+15));
        	}else if(SmuConstant.ACTION_SMU_IMPRISON_ANSWER.equals(bwlx) || SmuConstant.ACTION_SMU_RELIEVE_ANSWER.equals(bwlx)){
        		t.setJjjg(data.substring(54, 55));
        	}else if(SmuConstant.ACTION_SMU_STARTREPORT_ANSWER.equals(bwlx)){
        		t.setQdlx(data.substring(54, 55));
        		t.setDsscsj(data.substring(55, 59));
        	}else if(SmuConstant.ACTION_SMU_SMUINFO_ANSWER.equals(bwlx)){
        		int firstLeft = data.indexOf("(");
        		int firstRight = data.indexOf(")");
        		String s1 = data.substring(firstLeft+1,firstRight);
        		String [] arr1 =  s1.split(",");
        		t.setCbwlx(arr1[0]);
        		t.setAv(arr1[2]);
        		t.setLa(arr1[3]);
        		t.setWdbs(arr1[4]);
        		t.setLo(arr1[5]);
        		t.setJdbs(arr1[6]);
        		t.setSd(arr1[7]);
        		t.setFx(arr1[8]);
        		StringBuffer sb = new StringBuffer();
        		String year =  new SimpleDateFormat("yyyy").format(new Date());
        		sb.append(year.substring(0, 2));
        		String date = arr1[9];
        		sb.append(date.substring(4, date.length()));
        		sb.append(date.substring(2, 4));
        		sb.append(date.substring(0, 2));
        		t.setGpstime(sb.toString()+arr1[1].split("\\.")[0]);
        		int secondLeft = data.indexOf("(",firstRight);
        		int secondRight = data.indexOf(")",secondLeft);
        		String s2 = data.substring(secondLeft+1,secondRight);
        		t.setSjdl(s2.substring(6, 8));
        		t.setSbdl(s2.substring(8, 10));
        		t.setDssclx(s2.substring(10, 11));
        		t.setSjjg(s2.substring(11, 15));
        		t.setJqlx(s2.substring(6,21));
        		t.setSbaz(s2.substring(19,20));
        		t.setSfjj(s2.substring(20,21));
        		int lastLeft = data.lastIndexOf("(");
        		int lastRight = data.lastIndexOf(")");
        		String s3 = data.substring(lastLeft+1,lastRight);
        		String [] arr2 = s3.split(",");
        		t.setLocid(arr2[1]);
        		t.setCcid(arr2[2]);
        	}else if(SmuConstant.ACTION_SMU_SYSCONF_ANSWER.equals(bwlx)){
        		int firstLeft = data.indexOf("(");
        		int firstRight = data.indexOf(")");
        		String s1 = data.substring(firstLeft+1,firstRight);
        		String [] arr1 =  s1.split(",");
        		t.setIp(arr1[0]);
        		t.setDk(arr1[1]);
        	}else if(SmuConstant.ACTION_SMU_F7_ANSWER.equals(bwlx)){
        		int firstLeft = data.indexOf("(");
        		int firstRight = data.indexOf(")");
        		String s1 = data.substring(firstLeft+1,firstRight);
        		String [] arr1 =  s1.split(",");
        		t.setXtsj(arr1[0]);
        		t.setGpslx(arr1[1]);
        		t.setSmugj(arr1[2]);
        		t.setMdddkz(arr1[3]);
        		t.setYxf(arr1[4]);
        	}else if(SmuConstant.ACTION_SMU_F8_ANSWER.equals(bwlx)){
        		t.setNr(data.substring(54,58));
        	}else if(SmuConstant.ACTION_SMU_G0_ANSWER.equals(bwlx) || SmuConstant.ACTION_SMU_AA_ANSWER.equals(bwlx)){
        		t.setSbwd(data.substring(54,57));
        		t.setRyxy(data.substring(57, 63));
        		t.setRyxl(data.substring(63, 66));
        		t.setXzbs(data.substring(66, 71));
        		t.setGl(data.substring(71, 73));
        		t.setJlcs(data.substring(73, 75));
        		t.setJbz(data.substring(75,80));
        		t.setYddw(data.substring(80,88));
        	}else if(SmuConstant.ACTION_REPORT_REALTIME_ANSWER.equals(bwlx) || SmuConstant.ACTION_REPORT_AB_ANSWER.equals(bwlx)){
        		t.setIccid(data.substring(54, 74));
        		t.setJrfs(data.substring(74, 75));
        		t.setCbwsm(data.substring(75, 76));
        		t.setYl(data.substring(76, 78));
        		int firstLeft = data.indexOf("(");
        		int firstRight = data.indexOf(")");
        		String s1 = data.substring(firstLeft+1,firstRight);
        		String []arr1 = s1.split(",");
        		t.setCbwlx(arr1[0]);
        		t.setAv(arr1[1]);
        		StringBuffer sb = new StringBuffer();
        		String year =  new SimpleDateFormat("yyyy").format(new Date());
        		sb.append(year.substring(0, 2));
        		String date = arr1[10];
        		sb.append(date.substring(4, date.length()));
        		sb.append(date.substring(2, 4));
        		sb.append(date.substring(0, 2));
        		t.setGpstime(sb.toString()+arr1[2].split("\\.")[0]);
        		t.setLa(arr1[4]);
        		t.setWdbs(arr1[5]);
        		t.setJdbs(arr1[7]);
        		t.setLo(arr1[6]);
        		t.setSd(arr1[8]);
        		t.setFx(arr1[9]);
        		int secondLeft = data.indexOf("(",firstRight);
        		int secondRight = data.indexOf(")",secondLeft);
        		String s2 = data.substring(secondLeft+1,secondRight);
        		t.setSjdl(s2.substring(8, 10));
        		t.setSbdl(s2.substring(10, 12));
        		t.setDssclx(s2.substring(12, 13));
        		t.setSjjg(s2.substring(13, 17));
        		t.setJqlx(s2.substring(8, 23));
        		t.setSbaz(s2.substring(21, 22));
        		t.setSfjj(s2.substring(22, 23));
        		
        		int thirdLeft = data.indexOf("(",secondRight);
        		int thirdRight = data.indexOf(")",thirdLeft);
        		t.setMobilenet(data.substring(thirdLeft,thirdRight+1));//移动网路获取的值要哪些
        		
        		int fourLeft = data.indexOf("(",thirdRight);
        		int fourRight = data.indexOf(")",fourLeft);
        		String s4 = data.substring(fourLeft,fourRight+1);
        		t.setWifi(s4);
        		
        		int fiveLeft = data.indexOf("(",fourRight);
        		int fiveRight = data.indexOf(")",fiveLeft);
        		String s5 = data.substring(fiveLeft,fiveRight+1);
        		t.setBt(s5);
        		
        		int sixLeft = data.indexOf("(",fiveRight);
        		int sixRight = data.indexOf(")",sixLeft);
        		String s6 = data.substring(sixLeft,sixRight+1);
        		t.setTerminalip(s6);
        		
        		int lastLeft = data.lastIndexOf("(");
        		int lastRight = data.lastIndexOf(")");
        		String s7 = data.substring(lastLeft,lastRight+1);
        		t.setReserved(s7);
        		//reserved_wd那些没有
        	}else if(SmuConstant.ACTION_HEALTHPARAMS_ANSWER.equals(bwlx)){
        		t.setCsxy(data.substring(54,60));
        		t.setXybj(data.substring(60,66));
        		t.setCsmb(data.substring(66,69));
        		t.setMbbj(data.substring(69, 72));
        		t.setTz(data.substring(72, 74));
        		t.setSg(data.substring(74, 77));
        		t.setYlkj(data.substring(77,83));
        	}else if(SmuConstant.ACTION_BLUETOOTHSETUP_ANSWER.equals(bwlx)){
        		String[] arr = data.split(",");
        		t.setLybqmc(arr[1]);
        		t.setLybqid(arr[2]);
        		t.setYlkj(arr[3]);
        	}else if(SmuConstant.ACTION_WN.equals(bwlx)){
        		int firstLeft = data.indexOf("(");
        		int firstRight = data.indexOf(")");
        		String s1 = data.substring(firstLeft,firstRight+1);
        		t.setNr(s1);
        	}
        	return t;
		} catch (Exception e) {
			logger.error("解析出错：",e);
			return null;
		}
    }

    /**
     * 拼装报文
     * @param tcpReq
     * @return String
     */
    public static String getReq(TcpReq tcpReq){

		/**
		 * 下面是Smu-->ClientServer应答
		 */


    	
		//登录
		if(SmuConstant.ACTION_LOGIN_RES.equals(tcpReq.getBwlx())){
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());
			String str = new String(tcpReq.getImei()+tcpReq.getPid()+tcpReq.getSjh()+tcpReq.getQdlx()+tcpReq.getDsscsj()+tcpReq.getSfjj()+tcpReq.getSjtb());
			sb.append(str.length());
			sb.append(str);
			sb.append("]");
			return sb.toString();
    	}

		//退登
		if(SmuConstant.ACTION_LOGOUT_RES.equals(tcpReq.getBwlx())){
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());

			String str = new String(tcpReq.getImei()+tcpReq.getPid()+tcpReq.getSjh());
			sb.append(str.length());
			sb.append(str);
			sb.append("]");

			return sb.toString();
		}

		//链路
		if(SmuConstant.ACTION_HEARTBEAT_RES.equals(tcpReq.getBwlx())){
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());


			String str = new String(tcpReq.getImei()+tcpReq.getPid()+tcpReq.getSjh());
			sb.append(str.length());
			sb.append(str);
			sb.append("]");
			return sb.toString();
		}

		//安装
		if(SmuConstant.ACTION_SMU_SETUP_RES.equals(tcpReq.getBwlx())){
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());
			String str = new String(tcpReq.getImei()+tcpReq.getPid()+tcpReq.getSjh());
			sb.append(str.length());
			sb.append(str);
			sb.append("]");
			return sb.toString();
		}

		//定时报告
		if (SmuConstant.ACTION_SMU_F2_ANSWER.equals(tcpReq.getBwlx())) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());
			String str = new String(tcpReq.getImei()+tcpReq.getPid()+tcpReq.getSjh());
			sb.append(str.length());
			sb.append(str);
			sb.append("]");
			return sb.toString();
		}

		//版本汇报
		if (SmuConstant.ACTION_SMU_WN_ANSWER.equals(tcpReq.getBwlx())) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());
			String str = new String(tcpReq.getImei()+tcpReq.getPid()+tcpReq.getSjh());
			sb.append(str.length());
			sb.append(str);
			sb.append("]");
			return sb.toString();
		}

		//多基站协议
		if (SmuConstant.ACTION_SMU_F5_ANSWER.equals(tcpReq.getBwlx())) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());
			String str = new String(tcpReq.getImei()+tcpReq.getPid()+tcpReq.getSjh());
			sb.append(str.length());
			sb.append(str);
			sb.append("]");
			return sb.toString();
		}

		//请求SMU上报当前状态命令
		if (SmuConstant.ACTION_SMU_F9_ANSWER.equals(tcpReq.getBwlx())) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());
			String str = new String(tcpReq.getImei()+tcpReq.getPid()+tcpReq.getSjh());
			sb.append(str.length());
			sb.append(str);
			sb.append("]");
			return sb.toString();
		}


    	/**
		 * 下面是ClientServer -->Smu 请求
    	 */

        //监禁
        if (SmuConstant.ACTION_IMPRISON_DISABLE.equals(tcpReq.getBwlx())) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());
			String str = new String(tcpReq.getImei()+tcpReq.getPid()+tcpReq.getSjh());
			sb.append(str.length());
			sb.append(str);
			sb.append("]");
            return sb.toString();
        }

        //解禁
		if (SmuConstant.ACTION_IMPRISON_ENABLED.equals(tcpReq.getBwlx())) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());
			String str = new String(tcpReq.getImei()+tcpReq.getPid()+tcpReq.getSjh());
			sb.append(str.length());
			sb.append(str);
			sb.append("]");
			return sb.toString();
		}

		//启动定时报告
		if (SmuConstant.ACTION_STARTREPORT.equals(tcpReq.getBwlx())) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());
			String str = new String(tcpReq.getImei()+tcpReq.getPid()+tcpReq.getSjh()+tcpReq.getQdlx()+tcpReq.getScsj());
			sb.append(str.length());
			sb.append(str);
			sb.append("]");
			return sb.toString();
		}

		//停止定时报告
		if (SmuConstant.ACTION_STOPREPORT.equals(tcpReq.getBwlx())) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());
			String str = new String(tcpReq.getImei()+tcpReq.getPid()+tcpReq.getSjh());
			sb.append(str.length());
			sb.append(str);
			sb.append("]");
			return sb.toString();
		}

		//请求SMU上报当前状态命令
		if (SmuConstant.ACTION_SMUINFO.equals(tcpReq.getBwlx())) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());
			String str = new String(tcpReq.getImei()+tcpReq.getPid()+tcpReq.getSjh());
			sb.append(str.length());
			sb.append(str);
			sb.append("]");
			return sb.toString();
		}

		//设置TCP通信参数
		if (SmuConstant.ACTION_SYSCONF.equals(tcpReq.getBwlx())) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());
			String str = new String(tcpReq.getImei()+tcpReq.getPid()+tcpReq.getSjh());
			sb.append(str.length());
			sb.append(str);
			sb.append("("+tcpReq.getIp()+","+tcpReq.getPort()+")");
			sb.append("]");
			return sb.toString();
		}

		//设置电子围栏
		if (SmuConstant.ACTION_FENCECONF.equals(tcpReq.getBwlx())) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());
			String str = new String(tcpReq.getImei()+"0000000000000000000000000");
			sb.append(str.length());
			sb.append(str);
			sb.append("("+tcpReq.getWlkg()+","+
					tcpReq.getBj()+","+tcpReq.getLo()+","+tcpReq.getJdbs()+","+tcpReq.getLa()+","+tcpReq.getWdbs()+")");
			sb.append("]");
			return sb.toString();
		}

		//设置灯
		if (SmuConstant.ACTION_LIGHTCONF.equals(tcpReq.getBwlx())) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());
			String str = new String(tcpReq.getImei()+"0000000000000000000000000"+tcpReq.getDkg()+tcpReq.getB1()+tcpReq.getB2());
			sb.append(str.length());
			sb.append(str);
			sb.append("]");
			return sb.toString();
		}

		//final String keyPre13 = "RU";
		//固件远程升级   未使用
		/*if (keyPre13.equals(tcpReq.getBwlx())) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());
			String str = new String(tcpReq.getImei()+tcpReq.getPid()+tcpReq.getSjh());
			sb.append(str.length());
			sb.append(str);
			sb.append("(");
			sb.append(tcpReq.getIp()+","+tcpReq.getPort()+",");
			sb.append(tcpReq.);
			sb.append(")");
			sb.append("]");
			return sb.toString();
		}*/


		//FTP远程升级
		if (SmuConstant.ACTION_WN.equals(tcpReq.getBwlx())) {

			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());
			String str = new String(tcpReq.getImei()+tcpReq.getPid()+tcpReq.getSjh());
			sb.append(str.length());
			sb.append(str);
			sb.append(tcpReq.getNr());
			sb.append("]");
			logger.info(sb.toString());
			return sb.toString();
		}

		// 中文协议
		if (SmuConstant.ACTION_SMU_F6.equals(tcpReq.getBwlx())) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());
			String str = new String(tcpReq.getImei()+tcpReq.getPid()+tcpReq.getSjh()+tcpReq.getNr());
			sb.append(str.length());
			sb.append(str);
			sb.append("]");
			return sb.toString();
		}

		// 参数设置
		if (SmuConstant.ACTION_SMU_F7.equals(tcpReq.getBwlx())) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());
			String str = new String(tcpReq.getImei()+tcpReq.getPid()+tcpReq.getSjh());
			sb.append(str.length());
			sb.append(str);
			sb.append("("+tcpReq.getXtsj()+
					","+tcpReq.getGpslx()+","+tcpReq.getSmugj()+","+tcpReq.getMdddkz()+","+tcpReq.getYxf()+tcpReq.getYlkj()+")");
			sb.append("]");
			return sb.toString();
		}

		// 开启运动参数定时
		if (SmuConstant.ACTION_SMU_F8.equals(tcpReq.getBwlx())) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());
			while(tcpReq.getNr().trim().length()<4){
				tcpReq.setNr("0"+tcpReq.getNr().trim());
			}
			String str = new String(tcpReq.getImei()+tcpReq.getPid()+tcpReq.getSjh()+tcpReq.getNr());
			sb.append(str.length());
			sb.append(str);
			sb.append("]");
			return sb.toString();
		}



		// 多基站实时
		if (SmuConstant.ACTION_REPORT_FB.equals(tcpReq.getBwlx())||SmuConstant.ACTION_REPORT_REALTIME.equals(tcpReq.getBwlx())) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());
			StringBuffer str = new StringBuffer();
			str.append(new String(tcpReq.getImei()+tcpReq.getPid()+tcpReq.getSjh()));
			sb.append(str.length());
			sb.append(str);
			sb.append("]");
			return sb.toString();
		}


		final String tcpRro5 = "B0";
		final String tcpRro6 = "AA";
		//运动参数实时请求
		if (tcpRro5.equals(tcpReq.getBwlx())||tcpRro6.equals(tcpReq.getBwlx())) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());
			StringBuffer str = new StringBuffer();
			str.append(new String(tcpReq.getImei()+tcpReq.getPid()+tcpReq.getSjh()));
			str.append(tcpReq.getCsxy()+tcpReq.getXybj()+tcpReq.getCsmb()+tcpReq.getMbbj()+tcpReq.getTz()+tcpReq.getSg());
			sb.append(str.length());
			sb.append(str);
			sb.append("]");
			return sb.toString();
		}

		final String tcpRro1 = "FC";
		//健康参数初始化
		if (tcpRro1.equals(tcpReq.getBwlx())) {
			StringBuffer sb = new StringBuffer();

			while(tcpReq.getCsxy().length()<6){
				tcpReq.setCsxy("0"+tcpReq.getCsxy());
			}

			while(tcpReq.getXybj().length()<6){
				tcpReq.setXybj("0"+tcpReq.getXybj());
			}

			while(tcpReq.getCsmb().length()<3){
				tcpReq.setCsmb("0"+tcpReq.getCsmb());
			}
			while(tcpReq.getMbbj().length()<3){
				tcpReq.setMbbj("0"+tcpReq.getMbbj());
			}
			while(tcpReq.getTz().length()<3){
				tcpReq.setTz("0"+tcpReq.getTz());
			}
			while(tcpReq.getSg().length()<3){
				tcpReq.setSg("0"+tcpReq.getSg());
			}
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());
			StringBuffer str = new StringBuffer();
			str.append(new String(tcpReq.getImei()+tcpReq.getPid()+tcpReq.getSjh()));
			str.append(tcpReq.getCsxy()+tcpReq.getXybj()+tcpReq.getCsmb()+tcpReq.getMbbj()+tcpReq.getTz()+tcpReq.getSg());
			sb.append(str.length());
			sb.append(str);
			sb.append("]");
			return sb.toString();
		}

		final String tcpRro2 = "FD";
		//蓝牙
		if (tcpRro2.equals(tcpReq.getBwlx())) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());
			StringBuffer str = new StringBuffer();
			str.append(new String(tcpReq.getImei()+tcpReq.getPid()+tcpReq.getSjh()));
			str.append(","+tcpReq.getLybqmc()+","+tcpReq.getLybqid()+","+tcpReq.getYlkj());
			sb.append(str.length());
			sb.append(str.toString());
			sb.append("]");
			return sb.toString();
		}

		final String tcpRro3 = "G0";

		final String tcpRro4 = "FA";
		//运动参数实时请求
		if (tcpRro4.equals(tcpReq.getBwlx())||tcpRro3.equals(tcpReq.getBwlx())) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(tcpReq.getBwlx());
			sb.append(tcpReq.getBwlsh());
			String str = new String(tcpReq.getImei()+tcpReq.getPid()+tcpReq.getSjh());
			sb.append(str.length());
			sb.append(str);
			sb.append("]");
			return sb.toString();
		}
        return null;
    }
}
