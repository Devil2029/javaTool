package com.tool.as.pay.wx.service;



import org.springframework.stereotype.Service;

import com.tool.as.pay.wx.common.config.Configure;
import com.tool.as.pay.wx.request.ScanCodeReqData;
import com.tool.as.pay.wx.response.ScanCodeResData;


@Service
public class WxPayService  extends BaseService {

    public WxPayService() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super(Configure.PAY_API);
    }

    /**
     * 请求支付服务
     * @param scanCodeReqData 这个数据对象里面包含了API要求提交的各种数据字�?
     * @return API返回的数�?
     * @throws Exception
     */
    public ScanCodeResData request(ScanCodeReqData scanCodeReqData) throws Exception {
        //--------------------------------------------------------------------
        //发�?�HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
    	ScanCodeResData responseString = sendPost(scanCodeReqData);

        return responseString;
    }
}
