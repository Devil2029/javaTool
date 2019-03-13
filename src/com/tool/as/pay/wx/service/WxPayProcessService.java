package com.tool.as.pay.wx.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tool.as.pay.wx.common.config.Signature;
import com.tool.as.pay.wx.common.util.SymmetricEncoder;
import com.tool.as.pay.wx.constant.GConstent;
import com.tool.as.pay.wx.request.DataInfoReq;
import com.tool.as.pay.wx.request.ScanCodeReqData;
import com.tool.as.pay.wx.response.ScanCodeResData;
import com.tool.as.pay.wx.response.WxPayInfoResp;
public class WxPayProcessService{
	private final static Log log = LogFactory.getLog(WxPayProcessService.class);

	private static WxPayService wxPayService;
	
	private static WxPayInfoResp wxPayInfoResp;
	
	private static DataInfoReq dataInfoReq;
	/**
	 * 支付请求 请求报文格式
	 * @param apply_xml
	 * @param tradeType
	 * @return
	 */
	public static String tradeProcess(String apply_xml) {
		Map ret = dataInfoReq.parseXml(apply_xml);
		try{
		ScanCodeResData scanPayResData = null;
		ScanCodeReqData scCodeData = (ScanCodeReqData) ret.get("payInfo");//参数封装
		scanPayResData=wxPayService.request(scCodeData);//发�?�请�?
		if("JSAPI".equals(ret.get("tradeType"))){//JSAPI 支付
			  //根据API给的签名规则进行签名
			String timeStamp=dateToStamp();
			//预支付返回的结果 返回给支付界面唤�? 手机微信支付客户�?
			scanPayResData.setTimeStamp(timeStamp);
			scanPayResData.setNonce_str(getRandomString(32));
			Map<String, Object> map= new HashMap<String, Object>();
			map.put("appId", ret.get("appId"));
			map.put("timeStamp", timeStamp);
			map.put("nonceStr", scanPayResData.getNonce_str());
			map.put("prepay_id", scanPayResData.getPrepay_id());
			map.put("signType", "MD5");
			String sign = Signature.getSign(map,ret.get("mKey").toString());//生成签名
			scanPayResData.setSign(sign);
			return wxPayInfoResp.CreateJson(GConstent.SUCCESS_CODE, GConstent.SUCCESS_MSG, 1, 1,scanPayResData,timeStamp,ret.get("appId"));
		}
		if("MWEB".equals(ret.get("tradeType"))){//H5支付
			return wxPayInfoResp.CreateH5Json(GConstent.SUCCESS_CODE, GConstent.SUCCESS_MSG, 1, 1,scanPayResData);
		}
		}catch (IllegalAccessException e) {
			log.error("安全权限异常", e);
		} catch (InstantiationException e) {
			log.error("实例化异�?", e);
		} catch (ClassNotFoundException e) {
			log.error("无法找到指定的类异常", e);
		} catch (Exception e) {
			log.error("查询失败", e);
		}
		return ret.get("code").toString();
	}

	
	
	/**
	 * 支付请求 参数请求
	 * @return
	 * @param body 描述
       @param outTradeNum 商户订单号
       @param int totalFee 金额
	 * @param String openid 公众平台唯一识别号
	 * @param String tradeType 交易类型
	 * @param String notify_url  回调地址
	 * @param String sceneInfo
	 */
	public static ScanCodeResData tradeProcessData(String body,String outTradeNum,int totalFee,
			 String openid,String tradeType,String notify_url,String sceneInfo,String appId,String mchId,String mKey) {
		ScanCodeResData scanPayResData = null;
		try{
		String new_appId=SymmetricEncoder.AESDncode("AES", appId);//解密 
	    String new_mchId=SymmetricEncoder.AESDncode("AES", mchId);//商户号
	    String new_mKey=SymmetricEncoder.AESDncode("AES", mKey);//商户秘钥
		ScanCodeReqData scCodeData = new ScanCodeReqData(body,outTradeNum,totalFee,
				 openid,tradeType,notify_url,sceneInfo,new_appId,new_mchId,new_mKey);//参数封装
		scanPayResData=wxPayService.request(scCodeData);//发起请求
		if("JSAPI".equals(tradeType)){//JSAPI 支付
			  //根据API给的签名规则进行签名
			String timeStamp=dateToStamp();
			//预支付返回的结果 返回给支付界面唤�? 手机微信支付客户�?
			scanPayResData.setTimeStamp(timeStamp);
			scanPayResData.setNonce_str(getRandomString(32));
			Map<String, Object> map= new HashMap<String, Object>();
			map.put("appId", new_appId);
			map.put("timeStamp", timeStamp);
			map.put("nonceStr", scanPayResData.getNonce_str());
			map.put("prepay_id", scanPayResData.getPrepay_id());
			map.put("signType", "MD5");
			String sign = Signature.getSign(map,new_mKey);//生成签名
			scanPayResData.setSign(sign);
			return scanPayResData;
		}
		if("MWEB".equals(tradeType)){//H5支付
			return scanPayResData;
		}
		}catch (IllegalAccessException e) {
			log.error("安全权限异常", e);
		} catch (InstantiationException e) {
			log.error("实例化异�?", e);
		} catch (ClassNotFoundException e) {
			log.error("无法找到指定的类异常", e);
		} catch (Exception e) {
			log.error("查询失败", e);
		}
		return scanPayResData;
	}
	
	public WxPayInfoResp getWxPayInfoResp() {
		return wxPayInfoResp;
	}

	public void setWxPayInfoResp(WxPayInfoResp wxPayInfoResp) {
		this.wxPayInfoResp = wxPayInfoResp;
	}

	public DataInfoReq getDataInfoReq() {
		return dataInfoReq;
	}

	public void setDataInfoReq(DataInfoReq dataInfoReq) {
		this.dataInfoReq = dataInfoReq;
	}

	public WxPayService getWxPayService() {
		return wxPayService;
	}

	public void setWxPayService(WxPayService wxPayService) {
		this.wxPayService = wxPayService;
	}
	 /* 
     * 获取当前时间的时间搓
     */    
    public static String dateToStamp(){
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    	String str = sdf.format(date);
		return str.substring(0,10);
    }
	/**
	 * 生成随即�?
	 * @param length
	 * @return
	 */
	public static  String getRandomString(int length) { //length表示生成字符串的长度  
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 }
	
	/**
	 * 支付回调 处理
	 * @param request
	 * @param response
	 */
	public void contractNotify(HttpServletRequest request, HttpServletResponse response) {
			StringBuilder sb = new StringBuilder();
			BufferedReader reader;
			try {
				reader = request.getReader();
				char[] buff = new char[1024];
				int len;
				while ((len = reader.read(buff)) != -1) {
					sb.append(buff, 0, len);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			String wxResponseText = sb.toString();
			if("0".equals("0")) {
				StringBuilder XML = new StringBuilder();
				XML.append("<xml>");
				XML.append("<return_code>SUCCESS</return_code>");
				XML.append("<return_msg></return_msg>");
				XML.append("</xml>");
				String xml = XML.toString();
				try {
					response.getWriter().write(xml);
				} catch (IOException e) {
					log.error(e);
				}
			}
	}
}
