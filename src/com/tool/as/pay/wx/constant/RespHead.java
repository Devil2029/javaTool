/**  
* @Title: RespHead.java  
* @Package com.zhilai.as.json.head  
* @Description: TODO(用一句话描述该文件做什么)  
* @author zengsc  
* @date 2018年5月14日  
*/ 
package com.tool.as.pay.wx.constant;

import net.sf.json.JSONObject;

/**
* @ClassName: RespHead
* @Description: TODO(这里用一句话描述这个类的作用)
*
*/
public  abstract class  RespHead {
	private String charSet = "utf-8";
	protected String retcode = "0000";
	protected String retmsg = "";
	protected int totnum = 1;
	protected int curnum = 1;
	private int language;

	public int getLanguage() {
		return language;
	}

	public void setLanguage(int language) {
		this.language = language;
	}

	public String getCharSet() {
		return charSet;
	}

	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}

	public String getRetcode() {
		return retcode;
	}

	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}

	public String getRetmsg() {
		return retmsg;
	}

	public void setRetmsg(String retmsg) {
		this.retmsg = retmsg;
	}

	public int getTotnum() {
		return totnum;
	}

	public void setTotnum(int totnum) {
		this.totnum = totnum;
	}

	public int getCurnum() {
		return curnum;
	}

	public void setCurnum(int curnum) {
		this.curnum = curnum;
	}
	abstract public String CreateJson(Object... parm);
	protected JSONObject getJSONObject(String retcode, String retmsg, int totnum, int curnum) {
		JSONObject rootObject = new JSONObject();

		JSONObject JSONObject = new JSONObject();

		JSONObject JSONHeaderObject = new JSONObject();
		JSONHeaderObject.put("RetCode", retcode);
		JSONHeaderObject.put("RetMsg", retmsg);
		JSONHeaderObject.put("TotNum", totnum);
		JSONHeaderObject.put("CurNum", curnum);

		JSONObject.put(GConstent.ZxmlHead, JSONHeaderObject);
		
		rootObject.put(GConstent.ZxmlRoot, JSONObject);

		return rootObject;
	}
}
