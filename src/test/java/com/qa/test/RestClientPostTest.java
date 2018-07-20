package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.Base;
import com.qa.client.RestClientPost;
import com.qa.data.payloadPost;

public class RestClientPostTest extends Base{

String url,serviceUrl;
Base baseObj;
RestClientPost restClientPostObj;
payloadPost payloadPostReqObj;
CloseableHttpResponse closeHttpRespObj;

@BeforeMethod
public void setUp() {
		baseObj=new Base();
		restClientPostObj=new RestClientPost();
		url=prop.getProperty("url");
		serviceUrl=prop.getProperty("serviceUrl");
		url=url+serviceUrl;
}

@Test
public void post() throws Exception, JsonMappingException, IOException {
	//create Header for post req
	HashMap<String, String> headerReqMap=new HashMap<String,String>();
	headerReqMap.put("Content-type", "application/json");
	
	//*********Creating  payloadReqString *************class to json obj conversion
	ObjectMapper mapper=new ObjectMapper();
	payloadPostReqObj=new payloadPost("morpheus","leader");
	
	//at runtime this method will convert payLoadObj to Json obj and store in file
	mapper.writeValue(new File("C:\\Users\\D E L L\\eclipse-workspace\\ApiTest\\src\\main\\java\\com\\qa\\data\\payloadPost.json"), payloadPostReqObj);
	
	//json obj to string
	String payloadPostReqString=mapper.writeValueAsString(payloadPostReqObj);
	System.out.println("payloadPostReqString: "+payloadPostReqString);
	//*********Creating  payloadReqString ENDS*************
	
	//sending post req to http client: including headerReq, json payload 
	closeHttpRespObj=restClientPostObj.httpPost(url, payloadPostReqString, headerReqMap);
	
	//********Validate Response ************************response Status Code
	int statusCode=closeHttpRespObj.getStatusLine().getStatusCode();
	System.out.println("statusCode: "+statusCode);
	
	//response string entity
	String httpRespString=EntityUtils.toString(closeHttpRespObj.getEntity(),"UTF-8");
	System.out.println("httpRespString: "+httpRespString);
	
	//String to JSON Obj
	JSONObject httpRespJSON=new JSONObject(httpRespString);
	System.out.println("httpRespJSON: "+httpRespJSON);
	
	//validate weather name,job got created or not?//json to java //actual java object created from response
	payloadPost payloadPostRespObj=mapper.readValue(httpRespString, payloadPost.class);
	System.out.println("responseObj: "+payloadPostRespObj);
	
	//********Validate Response ENDS************************
	System.out.println("comparing exp and actual payload req and resp: "+payloadPostReqObj.getName().equals(payloadPostRespObj.getName()));
}

@AfterMethod
public void tearDown() {
	
}
}
