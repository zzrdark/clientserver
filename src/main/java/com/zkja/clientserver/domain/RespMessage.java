package com.zkja.clientserver.domain;

/**
 * @authon zzr
 */
public class RespMessage {
    private String ret;
    private String retInfo;
    private Object result;
    public String getRet() {
        return ret;
    }
    public void setRet(String ret) {
        this.ret = ret;
    }
    public String getRetInfo() {
        return retInfo;
    }
    public void setRetInfo(String retInfo) {
        this.retInfo = retInfo;
    }
    public Object getResult() {
        return result;
    }
    public void setResult(Object result) {
        this.result = result;
    }
    public RespMessage(){
    	
    }

    public RespMessage(String ret, String retInfo, Object result) {
        this.ret = ret;
        this.retInfo = retInfo;
        this.result = result;
    }
}
