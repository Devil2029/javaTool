package com.tool.as.pay.wx.request;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.tool.as.pay.wx.common.config.Signature;

/**
 * 微信支付 请求支付参数封装 统一入口
 * @author lp
 *
 */
@XStreamAlias("xml")
public class ScanCodeReqData {

    //每个字段具体的意思请查看API文档
    private String appid = "";
    private String mch_id = "";
    private String nonce_str = "";
    private String sign = "";
    private String body = "";//商品描述
    private String out_trade_no = "";
    private int total_fee = 0;
	private String openid;
	private String mKey;
	public String getmKey() {
		return mKey;
	}

	public void setmKey(String mKey) {
		this.mKey = mKey;
	}
	private String attach;

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getScene_info() {
		return scene_info;
	}

	public void setScene_info(String scene_info) {
		this.scene_info = scene_info;
	}
	private String spbill_create_ip = "";
    private String notify_url = "";
    private String trade_type = "";
    private String scene_info="";

	/**
     * @param authCode 这个是扫码终端设备从用户手机上扫取到的支付授权号，这个号是跟用户用来支付的银行卡绑定的，有效期是1分钟
     * @param body 要支付的商品的描述信息，用户会在支付成功页面里看到这个信�?
     * @param attach 支付订单里面可以填的附加数据，API会将提交的这个附加数据原样返�?
     * @param outTradeNo 商户系统内部的订单号,32个字符内可包含字�?, 确保在商户系统唯�?
     * @param totalFee 订单总金额，单位为�?�分”，只能整数
     * @param deviceInfo 商户自己定义的扫码支付终端设备号，方便追溯这笔交易发生在哪台终端设备�?
     * @param spBillCreateIP 订单生成的机器IP
     * @param timeStart 订单生成时间�? 格式为yyyyMMddHHmmss，如2009�?12 �?25 �?9 �?10 �?10 秒表示为20091225091010。时区为GMT+8 beijing。该时间取自商户服务�?
     * @param timeExpire 订单失效时间，格式同�?
     * @param goodsTag 商品标记，微信平台配置的商品标记，用于优惠券或�?�满减使�?
     */
    public ScanCodeReqData(String body,String outTradeNum,int totalFee,String openid,String tradeType,String notify_url,String sceneInfo,String appId,String mchId,String mKey){

        //微信分配的公众号ID（开通公众号之后可以获取到）
        setAppid(appId);
     
        //微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到�?
        setMch_id(mchId);
        setAttach(outTradeNum);
        setScene_info(sceneInfo);
        //要支付的商品的描述信息，用户会在支付成功页面里看到这个信�?
        try {
   
            setNotify_url(notify_url);
			new String(org.apache.commons.codec.binary.Base64.encodeBase64("中文".getBytes()), "UTF-8");
		    String encode=URLDecoder.decode("中文", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			setBody(body);
        //支付订单里面可以填的附加数据，API会将提交的这个附加数据原样返回，有助于商户自己可以注明该笔消费的具体内容，方便后续的运营和记�?
        //setDetail(detail);
        //商户系统内部的订单号,32个字符内可包含字�?, 确保在商户系统唯�?
        setOut_trade_no(getRandomString(32));       
        setOpenid(openid);
        //订单总金额，单位为�?�分”，只能整数
        setTotal_fee(totalFee);
        //订单生成的机器IP
        setSpbill_create_ip("113.57.246.11");
        //预支付url
        //setNotify_url(WeixinApp.notify_url);
        //setTrade_type(WeixinApp.trade_type_scan_code);
        setTrade_type(tradeType);
        //随机字符串，不长�?32 �?
        setNonce_str(getRandomString(32));
        //根据API给的签名规则进行签名
        String sign = Signature.getSign(toMap(),mKey);
        setSign(sign);//把签名数据设置到Sign这个属�?�中
    }
  
    public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	/**
	 * 生成随即�?
	 * @param length
	 * @return
	 */
	public  String getRandomString(int length) { //length表示生成字符串的长度  
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 }
    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }
    public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if(obj!=null){
                    map.put(field.getName(), obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}
