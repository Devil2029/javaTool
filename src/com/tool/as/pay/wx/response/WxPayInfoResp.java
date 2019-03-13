package com.tool.as.pay.wx.response;

import net.sf.json.JSONObject;

import com.tool.as.pay.wx.constant.GConstent;
import com.tool.as.pay.wx.constant.RespHead;

/**
 * 微信预支付请�? 返回数据信息
 * @author think
 *
 */
public class WxPayInfoResp extends RespHead {


	public String CreateJson(Object... parm) {
		ScanCodeResData scanPayResData = (ScanCodeResData) parm[4];
		String timeStamp=(String)parm[5];
		String appId=(String)parm[6];
		try {
			JSONObject rootObject = super.getJSONObject((String) parm[0], (String) parm[1], (Integer) parm[2], (Integer) parm[3]);
			JSONObject JSONBody = new JSONObject();
			JSONBody.put("appId",appId);
			JSONBody.put("timeStamp",timeStamp);
			JSONBody.put("nonceStr",scanPayResData.getNonce_str());
			JSONBody.put("package","prepay_id="+scanPayResData.getPrepay_id());
			JSONBody.put("signType","MD5");
			JSONBody.put("paySign",scanPayResData.getSign());
			JSONObject JSONObject = (JSONObject) rootObject.get(GConstent.ZxmlRoot);
			JSONObject.put(GConstent.ZxmlBody, JSONBody);
			return rootObject.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}
	public String CreateH5Json(Object... parm) {
		ScanCodeResData scanPayResData = (ScanCodeResData) parm[4];
		try {
			JSONObject rootObject = super.getJSONObject((String) parm[0], (String) parm[1], (Integer) parm[2], (Integer) parm[3]);
			JSONObject JSONBody = new JSONObject();
			JSONBody.put("scanPayResData",scanPayResData);
			JSONObject JSONObject = (JSONObject) rootObject.get(GConstent.ZxmlRoot);
			JSONObject.put(GConstent.ZxmlBody, JSONBody);
			return rootObject.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

}
