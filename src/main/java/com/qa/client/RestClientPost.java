package com.qa.client;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClientPost{
	
	public CloseableHttpResponse httpPost(String url, String payloadPostReqString, HashMap<String,String> headerMap) throws Exception {
		CloseableHttpClient closeHttpReqObj=HttpClients.createDefault();
		HttpPost httpPostObj=new HttpPost(url);
		
		//attaching payload obj to post obj
		httpPostObj.setEntity(new StringEntity(payloadPostReqString));
		
		//attaching header req to post obj
		for(Map.Entry<String,String> entry:headerMap.entrySet()) {
			httpPostObj.addHeader(entry.getKey(),entry.getValue());
		}
		
		CloseableHttpResponse closeHttpRespObj=closeHttpReqObj.execute(httpPostObj);
		return closeHttpRespObj;
	}
}