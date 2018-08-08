package com.zkja.clientserver.domain;

/**
 * @authon zzr
 */

public class A2Res {
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
     * 启动类型 1：普通启动；2：过滤启动
     */
    private String qdlx;
    /**
     * 定时上传时间
     */
    private String scsj;

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

	public String getScsj() {
		return scsj;
	}

	public void setScsj(String scsj) {
		this.scsj = scsj;
	}

    
}

