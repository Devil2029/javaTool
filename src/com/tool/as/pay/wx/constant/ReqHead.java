/**  
* @Title: ReqHead.java  
* @Package com.zhilai.as.json.head  
* @Description: TODO(用一句话描述该文件做什么)  
* @author zengsc  
* @date 2018年5月14日  
*/ 
package com.tool.as.pay.wx.constant;

import com.alibaba.fastjson.JSONObject;

/**
* @ClassName: ReqHead
* @Description: TODO(这里用一句话描述这个类的作用)
*/
public abstract class ReqHead {
	private String bcode = GConstent.BCODE;
	private String tcode;
	private String istart = GConstent.ISTART;
	private String iend = GConstent.IEND;
	private String iflag = GConstent.IFLAG;
	
	public JSONObject getJSONReqHead(){
		JSONObject rootObject = new JSONObject();
		JSONObject jsonHead = new JSONObject();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("BCode", this.bcode);
		jsonObject.put("TCode", this.tcode);
		jsonObject.put("IStart", this.istart);
		jsonObject.put("IEnd", this.iend);
		jsonObject.put("IFlag", this.iflag);
		jsonHead.put(GConstent.ZxmlHead, jsonObject);
		rootObject.put(GConstent.ZxmlRoot, jsonHead);
		return rootObject;
	}
	
	public JSONObject getJSONReqBody(){
		JSONObject zbodyObject = new JSONObject();
		return zbodyObject;
	}
		
	public String getBcode() {
		return bcode;
	}

	public void setBcode(String bcode) {
		this.bcode = bcode;
	}

	public String getTcode() {
		return tcode;
	}

	public void setTcode(String tcode) {
		this.tcode = tcode;
	}

	public String getIstart() {
		return istart;
	}

	public void setIstart(String istart) {
		this.istart = istart;
	}

	public String getIend() {
		return iend;
	}

	public void setIend(String iend) {
		this.iend = iend;
	}

	public String getIflag() {
		return iflag;
	}

	public void setIflag(String iflag) {
		this.iflag = iflag;
	}
}
