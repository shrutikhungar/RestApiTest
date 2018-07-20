package com.qa.test;

import java.io.IOException;
import java.util.HashMap;
import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.base.Base;
import com.qa.client.RestClientGet;

public class RestClientGetTest extends Base{
	
	RestClientGet restClientObj;
	Base baseObj;
	String url,serviceUrl;
	CloseableHttpResponse closeHttpResp;
	String closeHttpRespString;
	
	@BeforeMethod
	public void setup() {
		baseObj=new Base();
		restClientObj=new RestClientGet();
		url=prop.getProperty("url");
		serviceUrl=prop.getProperty("serviceUrl");
		url=url+serviceUrl;
	}
	@Test
	public void getWithoutHeaderTest() throws IOException, Exception, JSONException {
		closeHttpResp=restClientObj.getWithoutHeaders(url);
		System.out.println("*********************TEST 1*********************TEST 1***********************************TEST 1***************************");
		parseHttpResponseTogetDetails(closeHttpResp);
		System.out.println("*********************TEST 1*********************TEST 1***********************************TEST 1***************************");
		//Getting status code
	}
	
	/*@AfterMethod
	public void tearDown() {
		System.out.println("******************************************closing connection**************************************************************");
		restClientObj.httpReqObClose();
		System.out.println("******************************************closing connection**************************************************************");
	}*/
	
	@Test
	public void getWithHeaderTest() throws Exception, IOException, JSONException {
		restClientObj=new RestClientGet();
		
		HashMap<String,String> reqHeaderMap=new HashMap<String,String>();
		reqHeaderMap.put("Content-Type", "application/json");
		reqHeaderMap.put("username", "test@amazon.com");
		reqHeaderMap.put("password", "test213");
		reqHeaderMap.put("Auth Token", "12345");
		
		closeHttpResp=restClientObj.getWithHeaders(url,reqHeaderMap);
		System.out.println("*********************TEST 2*********************TEST 2***********************************TEST 2***************************");
		parseHttpResponseTogetDetails(closeHttpResp);
		closeHttpResp.getAllHeaders();
		System.out.println("*********************TEST 2*********************TEST 2***********************************TEST 2***************************");
		
	}
	
	public void parseHttpResponseTogetDetails(CloseableHttpResponse closeHttpResp) throws ParseException, IOException, JSONException{
		int statusCode=closeHttpResp.getStatusLine().getStatusCode();
		System.out.println("*****************************************************************************************************************");
		System.out.println("Status Code:"+statusCode);
		System.out.println("*****************************************************************************************************************");
		System.out.println("**************************************************************************************************************");
		
		//getting all the headers
		HashMap<String,String> respHeaderHM=new HashMap<String,String>();
		Header[] respHeaderArr=closeHttpResp.getAllHeaders();
		for(Header header:respHeaderArr) {
			respHeaderHM.put(header.getName(),header.getValue());
		}
		System.out.println("*******************************************PRINTING HEADER HASHMAP*******************************************");
		System.out.println("respHeaderHM: "+respHeaderHM);
		System.out.println("*******************************************PRINTING HEADER HASHMAP*******************************************");
		
		//getting Json response String

		closeHttpRespString=EntityUtils.toString(closeHttpResp.getEntity(),"UTF-8");
		System.out.println("******************************************PRINTING RESPONSE STRING******************************************");
		System.out.println("closeHttpRespString: "+closeHttpRespString);
		System.out.println("******************************************PRINTING RESPONSE STRING******************************************");
		
		//Json String into Json Object
		JSONObject respJSON=new JSONObject(closeHttpRespString);
		System.out.println("**************************************************************************************************************");
		System.out.println("Response JSON: "+respJSON);
		System.out.println("**************************************************************************************************************");
	}
}