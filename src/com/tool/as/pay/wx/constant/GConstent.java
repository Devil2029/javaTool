package com.tool.as.pay.wx.constant;

public class GConstent {
	// marco define
	public static final String ZxmlRoot = "ZMSG";
	public static final String ZxmlHead = "ZHEAD";
	public static final String ZxmlBody = "ZBODY";
	public static final String ZxmlSpec = "cabinet_column";
	public static final String StationSerialNumber = "StationSerialNumber";
	 public static final String DATA="?method=report&content=";
	public static final String Program_App_Execp="9999";
	public static final String AuthId = "AuthId";
	public static final String AuthName = "AuthName";
	public static final String bcode = "BCode";
	public static final String tcode = "TCode";
	public static final String SUCCESS_CODE = "0000";
	public static final String SUCCESS_MSG = "SUCCESS";
	public static final int SUCCESS_TOTNUM = 1;
	public static final int SUCCESS_CURNUM = 1;

	public static final String DATE_TIME_CN = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_CN = "yyyy-MM-dd";
	public static final String DATE_TIME_US = "MM/dd/yyyy HH:mm:ss";
	public static final String DATE_US = "MM/dd/yyyy";
	
	// marco define
	public static final String ZxmlZsign = "ZSIGN";
	public static final String ZxmlHead_TCODE = "tcode";
	public static final String ZxmlZsign_algo = "algo";
	public static final String ZxmlZsign_time = "time";
	public static final String ZxmlZsign_sign = "sign";
	public static final String ZxmlZsign_msgid = "msgid";
	public static final String ZxmlZsign_salt = "salt";
	public static final String BCODE = "03";//
	public static final String IFLAG="1";
	public static final String IEND="1";
	public static final String ISTART="1";
	public static final String PLAT_VERSION="V1.0";
	
	
	

	//用户同意授权，获取code地址
	public static final String CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxecf2a65dc8209b35";
	
	public static final String IMAGE_URL="oss-cn-hangzhou.aliyuncs.com";//seradmin-public.oss-cn-hangzhou.aliyuncs.com

	//访问webservice接口
	public static final String WEBSERVICE = "https://autoshop.zhilai.com/autoshop_server/cxf/soap/";
	//public static final String WEBSERVICE = "http://127.0.0.1/autoshop_server/cxf/soap/";

	private GConstent() {
	}
}
