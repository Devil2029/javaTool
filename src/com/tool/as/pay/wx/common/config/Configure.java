package com.tool.as.pay.wx.common.config;





/**
 * 这里放置各种配置数据
 */
public class Configure {

	public static final String TRADE_TYPE_JSAPI ="JSAPI";//公众号支�? 类型
	public static final String TRADE_TYPE_NATIVE ="NATIVE";//Native支付 二维�?
	public static final String TRADE_TYPE_H5 ="MWEB";//H5支付
	public static final String TRADE_TYPE_APP ="APP";//app支付

	//受理模式下给子商户分配的子商户号
	public static String subMchID = "";

	//是否使用异步线程的方式来上报API测�?�，默认为异步模�?
	private static boolean useThreadToDoReport = true;

	//机器IP
	private static String ip = "";
	//以下是几个API的路径：
	//1）支付API
	public static String PAY_API = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	//2账单查询 API
	public static String ORDER_API="https://api.mch.weixin.qq.com/pay/orderquery";

	public static boolean isUseThreadToDoReport() {
		return useThreadToDoReport;
	}

	public static void setUseThreadToDoReport(boolean useThreadToDoReport) {
		Configure.useThreadToDoReport = useThreadToDoReport;
	}

	public static String HttpsRequestClassName = "com.tool.as.pay.common.util.HttpsRequest";

	public static void setSubMchID(String subMchID) {
		Configure.subMchID = subMchID;
	}

	public static void setIp(String ip) {
		Configure.ip = ip;
	}

	public static String getSubMchid(){
		return subMchID;
	}

	public static String getIP(){
		return ip;
	}

	public static void setHttpsRequestClassName(String name){
		HttpsRequestClassName = name;
	}

}
