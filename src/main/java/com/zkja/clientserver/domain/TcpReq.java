package com.zkja.clientserver.domain;

/**
 * @authon zzr
 */
public class TcpReq {

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
     * 硬件类型
     */
    private String yjlx;
    /**
     * 启动类型1：普通启动；2：过滤启动
     */
    private String qdlx;
    /**
     * 定时上传的时间
     */
    private String dsscsj;

	/**
	 * 定时上传的时间
	 */
	private String scsj;
    /**
     * 是否监禁过
     */
    private String sfjj;
    /**
     * 时间同步
     */
    private String sjtb;

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
	 * 围栏打开状态  0：取消围栏，1：开启围栏
	 */
	private String wlkg;
	/**
	 * 半径
	 */
	private String bj;
    /**
     * 纬度,解析后为WGS84坐标
     */
    private String la;
    /**
     * 纬度标识 “N”：表示北纬；“S”：表示南纬
     */
    private String wdbs;
    /**
     * 经度,解析后为WGS84坐标。如：112.123456
     */
    private String lo;
    /**
     * 经度标识。“E”：表示东经；“W”：表示西经
     */
    private String jdbs;
    /**
     * 速度
     */
    private String sd;
    /**
     * 方向，取值范围 0－360
     */
    private String fx;
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
	 * Ip
	 */
	private String ip;

	/**
	 * 端口
	 */
	private String port;

	/**
	 *灯开关，0:关闭蓝光灯；1:开启蓝光灯
	 */
	private String dkg;
	/**
	 * 设置灯  保留字段1
	 */
	private String b1;
	/**
	 * 设置灯  保留字段2
	 */
	private String b2;
	/**
	 * 报文内容
	 */
	private String nr;
	/**
	 * 心跳时间
	 */
	private String xtsj;
	/**
	 * GPS类型
	 */
	private String gpslx;
	/**
	 * SMU关机
	 */
	private String smugj;
	/**
	 * 马达抖动控制
	 */
	private String mdddkz;
	/**
	 * 有效符
	 */
	private String yxf;
	/**
	 * 预留空间
	 */
	private String ylkj;

	/**
	 * 手表温度
	 */
	private String sbwd;
	/**
	 * 人员血压
	 */
	private String ryxy;
	/**
	 * 人员心率
	 */
	private String ryxl;
	/**
	 * 行走步数
	 */
	private String xzbs;

	/**
	 * 概率
	 */
	private String gl;
	/**
	 * 记录次数
	 */
	private String jlcs;
	/**
	 * MOST有效符(A数据有效，V数据无效)
	 */
	private String mostyxf;
	/**
	 * 记步值
	 */
	private String jbz;
	/**
	 * MOPO有效符(A数据有效，V数据无效)
	 */
	private String mopoyxf;
	/**
	 * 运动定位
	 */
	private String yddw;
	/**
	 * 第一次记录时间(时分秒)
	 */
	private String dycjlsj;
	/**
	 * 第一次记录日期（日月年）
	 */
	private String dycjlrq;
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
	/**
	 * 记步值
	 */
	private String qbz;

	public String getQbz() {
		return qbz;
	}

	public void setQbz(String qbz) {
		this.qbz = qbz;
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


	public String getScsj() {
		return scsj;
	}

	public void setScsj(String scsj) {
		this.scsj = scsj;
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

	public String getSbwd() {
		return sbwd;
	}

	public void setSbwd(String sbwd) {
		this.sbwd = sbwd;
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

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getDkg() {
		return dkg;
	}

	public void setDkg(String dkg) {
		this.dkg = dkg;
	}

	public String getB1() {
		return b1;
	}

	public void setB1(String b1) {
		this.b1 = b1;
	}

	public String getB2() {
		return b2;
	}

	public void setB2(String b2) {
		this.b2 = b2;
	}

	public String getWlkg() {
		return wlkg;
	}

	public void setWlkg(String wlkg) {
		this.wlkg = wlkg;
	}

	public String getBj() {
		return bj;
	}

	public void setBj(String bj) {
		this.bj = bj;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
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

	public String getYjlx() {
		return yjlx;
	}

	public void setYjlx(String yjlx) {
		this.yjlx = yjlx;
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

	@Override
	public String toString() {
		return "TcpReq{" +
				"bwlx='" + bwlx + '\'' +
				", bwlsh='" + bwlsh + '\'' +
				", imei='" + imei + '\'' +
				", pid='" + pid + '\'' +
				", sjh='" + sjh + '\'' +
				", yjlx='" + yjlx + '\'' +
				", qdlx='" + qdlx + '\'' +
				", dsscsj='" + dsscsj + '\'' +
				", sfjj='" + sfjj + '\'' +
				", sjtb='" + sjtb + '\'' +
				", cbwlx='" + cbwlx + '\'' +
				", gpstime='" + gpstime + '\'' +
				", av='" + av + '\'' +
				", wlkg='" + wlkg + '\'' +
				", bj='" + bj + '\'' +
				", la='" + la + '\'' +
				", wdbs='" + wdbs + '\'' +
				", lo='" + lo + '\'' +
				", jdbs='" + jdbs + '\'' +
				", sd='" + sd + '\'' +
				", fx='" + fx + '\'' +
				", locid='" + locid + '\'' +
				", ccid='" + ccid + '\'' +
				", sjdl='" + sjdl + '\'' +
				", sbdl='" + sbdl + '\'' +
				", dssclx='" + dssclx + '\'' +
				", sjjg='" + sjjg + '\'' +
				", jqlx='" + jqlx + '\'' +
				", sbaz='" + sbaz + '\'' +
				", ip='" + ip + '\'' +
				", port='" + port + '\'' +
				", dkg='" + dkg + '\'' +
				", b1='" + b1 + '\'' +
				", b2='" + b2 + '\'' +
				", nr='" + nr + '\'' +
				", xtsj='" + xtsj + '\'' +
				", gpslx='" + gpslx + '\'' +
				", smugj='" + smugj + '\'' +
				", mdddkz='" + mdddkz + '\'' +
				", yxf='" + yxf + '\'' +
				", ylkj='" + ylkj + '\'' +
				", sbwd='" + sbwd + '\'' +
				", ryxy='" + ryxy + '\'' +
				", ryxl='" + ryxl + '\'' +
				", xzbs='" + xzbs + '\'' +
				", gl='" + gl + '\'' +
				", jlcs='" + jlcs + '\'' +
				", mostyxf='" + mostyxf + '\'' +
				", jbz='" + jbz + '\'' +
				", mopoyxf='" + mopoyxf + '\'' +
				", yddw='" + yddw + '\'' +
				", dycjlsj='" + dycjlsj + '\'' +
				", dycjlrq='" + dycjlrq + '\'' +
				", csxy='" + csxy + '\'' +
				", xybj='" + xybj + '\'' +
				", csmb='" + csmb + '\'' +
				", mbbj='" + mbbj + '\'' +
				", tz='" + tz + '\'' +
				", sg='" + sg + '\'' +
				", lybqmc='" + lybqmc + '\'' +
				", lybqid='" + lybqid + '\'' +
				", qbz='" + qbz + '\'' +
				'}';
	}
}
