package com.tool.as.pay.wx.request;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tool.as.pay.wx.common.util.JSONUtils;
import com.tool.as.pay.wx.common.util.StringUtils;
import com.tool.as.pay.wx.common.util.SymmetricEncoder;
import com.tool.as.pay.wx.constant.GConstent;
import com.tool.as.pay.wx.constant.PayContant;
import com.tool.as.pay.wx.constant.ReqHead;

public class DataInfoReq extends ReqHead {
	private final static Log log = LogFactory.getLog(DataInfoReq.class);

	@SuppressWarnings("unchecked")
	public Map parseXml(String report_json) {
		Map r = new HashMap();
		String openid = "";
		String outTradeNum = "";// 订单编号
		String notify_url = "";
		String sceneInfo = "";
		String tradeType = "";
		String body = "";
		int total_fee = 0;
		try {
			// json解析协议
			JSONObject JSONBody = JSONUtils.getBody(report_json);

			String isCode = JSONBody.getString("isCode");// 用来区分是否�?�? openID
			if (JSONBody.containsKey("TotalFee")) {// 必填参数验证
				String price = JSONBody.getString("TotalFee");// 订单金额
				if (StringUtils.isNotEmpty(price)) {// 判断是否传入空�??
					total_fee = Integer.parseInt(price);// 转成整数 支付单位以�?�分�?
				} else {
					r.put("code", PayContant.Xml_PRICE_Error);
				}
			} else {
				r.put("code", PayContant.Xml_PRICE_NULL);
			}
			if (JSONBody.containsKey("isCode")) {// 必填参数验证
				if ("1".equals(isCode)) {// �?要openId 可能是小程序入口
					if (JSONBody.containsKey("OpenId")) {// 必填参数验证
						openid = JSONBody.getString("OpenId");// 关注公众�? 公众号配置的
																// 唯一识别�?
					} else {
						r.put("code", PayContant.Xml_CODE_OPENID);
					}
				}
			} else {
				r.put("code", PayContant.Xml_CODE_IS_OPEN);
			}
			if (JSONBody.containsKey("OutTradeNo")) {// 必填参数验证 订单编号
				outTradeNum = JSONBody.getString("OutTradeNo");
			} else {
				r.put("code", PayContant.Xml_ORDER_ID);
			}
			if (StringUtils.isNotEmpty(JSONBody.getString("NOTIFY_URL"))) {// 回调通知地址
				notify_url = JSONBody.getString("NOTIFY_URL");
			}
			if (JSONBody.containsKey("SceneInfo")) {
				sceneInfo = JSONBody.getString("SceneInfo");
			}
			if (JSONBody.containsKey("TradeType")) {
				tradeType = JSONBody.getString("TradeType");
			}
			if (JSONBody.containsKey("body")) {
				body = JSONBody.getString("body");
			}
			String appId = SymmetricEncoder.AESDncode("AES",
					JSONBody.getString("appId"));
			String mchId = SymmetricEncoder.AESDncode("AES",
					JSONBody.getString("mchId"));
			String mKey = SymmetricEncoder.AESDncode("AES",
					JSONBody.getString("mKey"));// 商户秘钥

			ScanCodeReqData payInfo = new ScanCodeReqData(body, outTradeNum,
					total_fee, openid, tradeType, notify_url, sceneInfo, appId,
					mchId, mKey);
			r.put("code", 0);
			r.put("payInfo", payInfo);
			r.put("tradeType", tradeType);
			r.put("mKey", mKey);
			r.put("appId", appId);
		} catch (Exception e) {
			e.printStackTrace();
			r.put("code", GConstent.Program_App_Execp);
			return r;
		}
		return r;
	}

}
