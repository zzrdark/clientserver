package com.zkja.clientserver.domain;

/**
 * @authon zzr
 */
public class TcpRes {

    public TcpRes(){
        time = System.currentTimeMillis();
    }

    private final Long time;

    /**
     * 信息类型，如 80
     */
    private String bwlx;
    /**
     * 报文流水号
     */
    private String bwlsh;
    /**
     * Imei码，15位
     */
    private String imei;
    /**
     * Pid码，14位
     */
    private String pid;
    /**
     * 手机号码，11位
     */
    private String sjh;
    /**
     * 启动类型1：普通启动；2：过滤启动
     */
    private String qdlx;
    /**
     * 定时上传的时间
     */
    private String dsscsj;
    /**
     * 是否监禁过
     */
    private String sfjj;
    /**
     * 时间同步
     */
    private String sjtb;
    /**
     * 硬件类型
     */
    private String yjlx;

    /**
     * 次报文类型，一般固定为GPRMC
     */
    private String cbwlx;
    
    /**
     * GPS时间
     */
    private String gpstime;
    /**
     * 定位标识 A：定位；V：不定位
     */
    private String av;
    /**
     * 纬度,解析后为WGS84坐标
     */
    private String la = "0";
    /**
     * 纬度标识 “N”：表示北纬；“S”：表示南纬
     */
    private String wdbs = "N";
    /**
     * 经度,解析后为WGS84坐标。如：112.123456
     */
    private String lo = "0";
    /**
     * 经度标识。“E”：表示东经；“W”：表示西经
     */
    private String jdbs = "E";
    /**
     * 速度
     */
    private String sd = "0";
    /**
     * 方向，取值范围 0－360
     */
    private String fx = "270";
    /**
     * 基站locid值
     */
    private String locid;
    /**
     * 基站ccid
     */
    private String ccid;
    /**
     * 手机电量
     */
    private String sjdl;
    /**
     * 手表电量
     */
    private String sbdl;
    /**
     * 手表温度
     */
    private String sbwd;
    
    
    /**
     * 定时上传类型 0：不启动；1：普通启动，处理所有信息；2：过滤报警信息启动
     */
    private String dssclx;
    /**
     * 上传时间间隔
     */
    private String sjjg;
    /**
     * 报警内容。无报警的时候，该内容为空
     */
    private String jqlx;
    /**
     * 设备是否安装，0：未安装；1：安装
     */
    private String sbaz;
    /**
     * SIM卡的CCID号
     */
    private String iccid;
    /**
     * 接入方式。移动：0；WIFI：1
     */
    private String jrfs;
    /**
     * 上报有效的次报文数目
     */
    private String cbwsm;
    /**
     * 预留
     */
    private String yl;
    /**
     * 移动网络
     */
    private String mobilenet;
    /**
     * WIFI数据
     */
    private String wifi;
    /**
     * 蓝牙数据
     */
    private String bt;
    /**
     * 设备自身IP
     */
    private String terminalip;
    /**
     * 预留空间
     */
    private String reserved;
    /**
     * 网关IP，socketServer解析所得.如：220.170.123.222
     */
    private String sourceIp;
    /**
     * 端口
     */
    private String port;
    /**
     * 温度。单位：℃
     */
    private String reserved_wd;
    /**
     * 血压
     */
    private String reserved_xy;
    /**
     * 心率
     */
    private String reserved_xl;
    /**
     * 计步
     */
    private String reserved_jb;
    /**
     * 人员状态
     */
    private String reserved_ryzt;
    /**
     * 吸毒概率
     */
    private String reserved_xdgl;
    
    /**
     *人员血压
     */
    private String ryxy;
    /**
     *人员心率
     */
    private String ryxl;
    /**
     *行走步数
     */
    private String xzbs;
    /**
     *概率
     */
    private String gl;
    /**
     *记录次数
     */
    private String jlcs;
    /**
     *MOST有效符(A数据有效，V数据无效)
     */
    private String mostyxf;
    /**
     *记步值
     */
    private String jbz;
    /**
     *MOPO有效符(A数据有效，V数据无效)
     */
    private String mopoyxf;
    /**
     *运动定位
     */
    private String yddw;
    /**
     *第一次记录时间(时分秒)
     */
    private String dycjlsj;
    /**
     *第一次记录日期（日月年）
     */
    private String dycjlrq;
    /**
     *监禁结果 0：失败；1成功
     */
    private String jjjg;
    /**
     *ip地址
     */
    private String ip;
    /**
     *端口
     */
    private String dk;
    /**
     *心跳
     */
    private String xtsj;
    /**
     *GPS类型
     */
    private String gpslx;
    /**
     *SMU关机
     */
    private String smugj;
    /**
     *马达抖动控制
     */
    private String mdddkz;
    /**
     *有效符
     */
    private String yxf;
    /**
     *预留 
     */
    private String ylkj;
    /**
     * 运动参数定时上报时间
     */
    private String nr;
    /**
     * 初始血压值校准值
     */
    private String csxy;
    /**
     * 血压报警线值
     */
    private String xybj;
    /**
     * 初始脉搏校准值
     */
    private String csmb;
    /**
     * 脉搏报警线值
     */
    private String mbbj;
    /**
     * 体重
     */
    private String tz;
    /**
     * 身高
     */
    private String sg;
    /**
     * 蓝牙固定标签名称
     */
    private String lybqmc;
    /**
     * 蓝牙固定标签ID
     */
    private String lybqid;

    public Long getTime() {
        return time;
    }

    public String getLybqmc() {
		return lybqmc;
	}

	public void setLybqmc(String lybqmc) {
		this.lybqmc = lybqmc;
	}

	public String getLybqid() {
		return lybqid;
	}

	public void setLybqid(String lybqid) {
		this.lybqid = lybqid;
	}

	public String getCsxy() {
		return csxy;
	}

	public void setCsxy(String csxy) {
		this.csxy = csxy;
	}

	public String getXybj() {
		return xybj;
	}

	public void setXybj(String xybj) {
		this.xybj = xybj;
	}

	public String getCsmb() {
		return csmb;
	}

	public void setCsmb(String csmb) {
		this.csmb = csmb;
	}

	public String getMbbj() {
		return mbbj;
	}

	public void setMbbj(String mbbj) {
		this.mbbj = mbbj;
	}

	public String getTz() {
		return tz;
	}

	public void setTz(String tz) {
		this.tz = tz;
	}

	public String getSg() {
		return sg;
	}

	public void setSg(String sg) {
		this.sg = sg;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getXtsj() {
		return xtsj;
	}

	public void setXtsj(String xtsj) {
		this.xtsj = xtsj;
	}

	public String getGpslx() {
		return gpslx;
	}

	public void setGpslx(String gpslx) {
		this.gpslx = gpslx;
	}

	public String getSmugj() {
		return smugj;
	}

	public void setSmugj(String smugj) {
		this.smugj = smugj;
	}

	public String getMdddkz() {
		return mdddkz;
	}

	public void setMdddkz(String mdddkz) {
		this.mdddkz = mdddkz;
	}

	public String getYxf() {
		return yxf;
	}

	public void setYxf(String yxf) {
		this.yxf = yxf;
	}

	public String getYlkj() {
		return ylkj;
	}

	public void setYlkj(String ylkj) {
		this.ylkj = ylkj;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDk() {
		return dk;
	}

	public void setDk(String dk) {
		this.dk = dk;
	}

	public String getJjjg() {
		return jjjg;
	}

	public void setJjjg(String jjjg) {
		this.jjjg = jjjg;
	}

	public String getBwlx() {
        return bwlx;
    }

    public void setBwlx(String bwlx) {
        this.bwlx = bwlx;
    }

    public String getBwlsh() {
        return bwlsh;
    }

    public void setBwlsh(String bwlsh) {
        this.bwlsh = bwlsh;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getSjh() {
        return sjh;
    }

    public void setSjh(String sjh) {
        this.sjh = sjh;
    }

	public String getQdlx() {
		return qdlx;
	}

	public void setQdlx(String qdlx) {
		this.qdlx = qdlx;
	}

	public String getDsscsj() {
		return dsscsj;
	}

	public void setDsscsj(String dsscsj) {
		this.dsscsj = dsscsj;
	}

	public String getSfjj() {
		return sfjj;
	}

	public void setSfjj(String sfjj) {
		this.sfjj = sfjj;
	}

	public String getSjtb() {
		return sjtb;
	}

	public void setSjtb(String sjtb) {
		this.sjtb = sjtb;
	}

	public String getYjlx() {
		return yjlx;
	}

	public void setYjlx(String yjlx) {
		this.yjlx = yjlx;
	}

	
	public String getCbwlx() {
		return cbwlx;
	}

	public void setCbwlx(String cbwlx) {
		this.cbwlx = cbwlx;
	}

	public String getGpstime() {
		return gpstime;
	}

	public void setGpstime(String gpstime) {
		this.gpstime = gpstime;
	}

	public String getAv() {
		return av;
	}

	public void setAv(String av) {
		this.av = av;
	}

	public String getLa() {
		return la;
	}

	public void setLa(String la) {
		this.la = la;
	}

	public String getWdbs() {
		return wdbs;
	}

	public void setWdbs(String wdbs) {
		this.wdbs = wdbs;
	}

	public String getLo() {
		return lo;
	}

	public void setLo(String lo) {
		this.lo = lo;
	}

	public String getJdbs() {
		return jdbs;
	}

	public void setJdbs(String jdbs) {
		this.jdbs = jdbs;
	}

	public String getSd() {
		return sd;
	}

	public void setSd(String sd) {
		this.sd = sd;
	}

	public String getFx() {
		return fx;
	}

	public void setFx(String fx) {
		this.fx = fx;
	}

	public String getLocid() {
		return locid;
	}

	public void setLocid(String locid) {
		this.locid = locid;
	}

	public String getCcid() {
		return ccid;
	}

	public void setCcid(String ccid) {
		this.ccid = ccid;
	}

	public String getSjdl() {
		return sjdl;
	}

	public void setSjdl(String sjdl) {
		this.sjdl = sjdl;
	}

	public String getSbdl() {
		return sbdl;
	}

	public void setSbdl(String sbdl) {
		this.sbdl = sbdl;
	}

	public String getDssclx() {
		return dssclx;
	}

	public void setDssclx(String dssclx) {
		this.dssclx = dssclx;
	}

	public String getSjjg() {
		return sjjg;
	}

	public void setSjjg(String sjjg) {
		this.sjjg = sjjg;
	}

	public String getJqlx() {
		return jqlx;
	}

	public void setJqlx(String jqlx) {
		this.jqlx = jqlx;
	}

	public String getSbaz() {
		return sbaz;
	}

	public void setSbaz(String sbaz) {
		this.sbaz = sbaz;
	}

	
	
	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getJrfs() {
		return jrfs;
	}

	public void setJrfs(String jrfs) {
		this.jrfs = jrfs;
	}

	public String getCbwsm() {
		return cbwsm;
	}

	public void setCbwsm(String cbwsm) {
		this.cbwsm = cbwsm;
	}

	public String getYl() {
		return yl;
	}

	public void setYl(String yl) {
		this.yl = yl;
	}

	public String getMobilenet() {
		return mobilenet;
	}

	public void setMobilenet(String mobilenet) {
		this.mobilenet = mobilenet;
	}

	public String getWifi() {
		return wifi;
	}

	public void setWifi(String wifi) {
		this.wifi = wifi;
	}

	public String getBt() {
		return bt;
	}

	public void setBt(String bt) {
		this.bt = bt;
	}

	public String getTerminalip() {
		return terminalip;
	}

	public void setTerminalip(String terminalip) {
		this.terminalip = terminalip;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getReserved_wd() {
		return reserved_wd;
	}

	public void setReserved_wd(String reserved_wd) {
		this.reserved_wd = reserved_wd;
	}

	public String getReserved_xy() {
		return reserved_xy;
	}

	public void setReserved_xy(String reserved_xy) {
		this.reserved_xy = reserved_xy;
	}

	public String getReserved_xl() {
		return reserved_xl;
	}

	public void setReserved_xl(String reserved_xl) {
		this.reserved_xl = reserved_xl;
	}

	public String getReserved_jb() {
		return reserved_jb;
	}

	public void setReserved_jb(String reserved_jb) {
		this.reserved_jb = reserved_jb;
	}

	public String getReserved_ryzt() {
		return reserved_ryzt;
	}

	public void setReserved_ryzt(String reserved_ryzt) {
		this.reserved_ryzt = reserved_ryzt;
	}

	public String getReserved_xdgl() {
		return reserved_xdgl;
	}

	public void setReserved_xdgl(String reserved_xdgl) {
		this.reserved_xdgl = reserved_xdgl;
	}

	
	public String getRyxy() {
		return ryxy;
	}

	public void setRyxy(String ryxy) {
		this.ryxy = ryxy;
	}

	public String getRyxl() {
		return ryxl;
	}

	public void setRyxl(String ryxl) {
		this.ryxl = ryxl;
	}

	public String getXzbs() {
		return xzbs;
	}

	public void setXzbs(String xzbs) {
		this.xzbs = xzbs;
	}

	public String getGl() {
		return gl;
	}

	public void setGl(String gl) {
		this.gl = gl;
	}

	public String getJlcs() {
		return jlcs;
	}

	public void setJlcs(String jlcs) {
		this.jlcs = jlcs;
	}

	public String getMostyxf() {
		return mostyxf;
	}

	public void setMostyxf(String mostyxf) {
		this.mostyxf = mostyxf;
	}

	public String getJbz() {
		return jbz;
	}

	public void setJbz(String jbz) {
		this.jbz = jbz;
	}

	public String getMopoyxf() {
		return mopoyxf;
	}

	public void setMopoyxf(String mopoyxf) {
		this.mopoyxf = mopoyxf;
	}

	public String getYddw() {
		return yddw;
	}

	public void setYddw(String yddw) {
		this.yddw = yddw;
	}

	public String getDycjlsj() {
		return dycjlsj;
	}

	public void setDycjlsj(String dycjlsj) {
		this.dycjlsj = dycjlsj;
	}

	public String getDycjlrq() {
		return dycjlrq;
	}

	public void setDycjlrq(String dycjlrq) {
		this.dycjlrq = dycjlrq;
	}

	
	public String getSbwd() {
		return sbwd;
	}

	public void setSbwd(String sbwd) {
		this.sbwd = sbwd;
	}

	@Override
	public String toString() {
		return "TcpRes [bwlx=" + bwlx + ", bwlsh=" + bwlsh + ", imei=" + imei + ", pid=" + pid + ", sjh=" + sjh
				+ ", qdlx=" + qdlx + ", dsscsj=" + dsscsj + ", sfjj=" + sfjj + ", sjtb=" + sjtb + ", yjlx=" + yjlx
				+ "]";
	}

    

}
