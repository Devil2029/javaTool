package com.tool.as.pay.wx.service;




import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import com.tool.as.pay.wx.common.config.Configure;
import com.tool.as.pay.wx.common.util.HttpsRequest;
import com.tool.as.pay.wx.response.ScanCodeResData;

/**
 * User: lp
 * 服务的基�?
 */
public class BaseService{

    //API的地�?
    private String apiURL;

    //发请求的HTTPS请求�?
    private HttpsRequest serviceRequest;

    public BaseService(String api) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        apiURL = api;
        Class c = Class.forName(Configure.HttpsRequestClassName);
        serviceRequest = (HttpsRequest) c.newInstance();
    }

    protected ScanCodeResData sendPost(Object xmlObj) throws UnrecoverableKeyException, IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        return serviceRequest.savePost(apiURL,xmlObj);
    }
    /**
     * 自定义自己的HTTP请求器用
     * @param request 实现了IserviceRequest接口的HttpsRequest
     */
    public void setServiceRequest(HttpsRequest request){
        serviceRequest = request;
    }
}
