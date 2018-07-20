package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClientGet{
	CloseableHttpClient closeHttpReqObj;
	HttpGet httpGetReqObj;
	CloseableHttpResponse closeHttpResp;
	
	public CloseableHttpResponse getWithoutHeaders(String url) {
		closeHttpReqObj=HttpClients.createDefault();
		httpGetReqObj=new HttpGet(url);
		try {
			closeHttpResp=closeHttpReqObj.execute(httpGetReqObj);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return closeHttpResp;
	}
	
	public void httpReqObClose() {
		try {
			closeHttpReqObj.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public CloseableHttpResponse getWithHeaders(String url, HashMap<String,String> reqHeaderMap) {
		closeHttpReqObj=HttpClients.createDefault();
		httpGetReqObj=new HttpGet(url);
		
		for(Map.Entry<String, String> entry:reqHeaderMap.entrySet()) {
			httpGetReqObj.addHeader(entry.getKey(), entry.getValue());
		}
		
		try {
			closeHttpResp=closeHttpReqObj.execute(httpGetReqObj);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return closeHttpResp;
	}
}