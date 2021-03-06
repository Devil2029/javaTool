package com.tool.as.pay.wx.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * API会返回XML格式的数据，这个类用来装这些数据
 */
@XStreamAlias("xml")
public class ScanCodeResData {
    //协议�?
    private String return_code = "";
    private String return_msg = "";

    //协议返回的具体数据（以下字段在return_code 为SUCCESS 的时候有返回�?
    private String appid = "";
    private String mch_id = "";
    private String nonce_str = "";
    private String sign = "";
    private String result_code = "";
    private String err_code = "";
    private String err_code_des = "";
    private String out_trade_no="";
    public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	//业务返回的具体数据（以下字段在return_code 和result_code 都为SUCCESS 的时候有返回�?
    private String trade_type = "";
    private String prepay_id = "";
    private String code_url = "";
    private String mweb_url="";
   public String getMweb_url() {
		return mweb_url;
	}

	public void setMweb_url(String mweb_url) {
		this.mweb_url = mweb_url;
	}

private String openid="";
   
   private String timeStamp;

    public String getTimeStamp() {
	return timeStamp;
}

public void setTimeStamp(String timeStamp) {
	this.timeStamp = timeStamp;
}

	public String getPrepay_id() {
	return prepay_id;
}

public void setPrepay_id(String prepay_id) {
	this.prepay_id = prepay_id;
}

public String getCode_url() {
	return code_url;
}

public void setCode_url(String code_url) {
	this.code_url = code_url;
}

	public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
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

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

}
