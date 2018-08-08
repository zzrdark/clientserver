package com.zkja.clientserver.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.zkja.clientserver.domain.RespMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http+get/post消息发送工具类
 * @author Administrator
 *
 */
public class HttpGetPostUtil {
	
	private static Logger logger =LoggerFactory.getLogger(HttpGetPostUtil.class);

	/**
	 * http+get消息发送
	 * @param url 消息发送地址
	 * @param paramsMap 请求参数  <键,值> 的形式
	 * @return key取值为code和message
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 * @throws ParseException 
	 */
	public static Map<String, String> doGet(String url, Map<String, String> paramsMap) throws Exception {	
		return doGet(url, paramsMap, "");
	}
	
	/**
	 * http+get消息发送
	 * @param url 消息发送地址
	 * @param paramsMap 请求参数  <键,值> 的形式
	 * @return key取值为code和message
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 * @throws ParseException 
	 */
	public static Map<String, String> doGet(String url, Map<String, String> paramsMap, String charsetName) throws Exception {
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if(null != paramsMap && paramsMap.size() > 0) {
			StringBuffer sb = new StringBuffer();
			for(Map.Entry<String, String> entry : paramsMap.entrySet()) {
				String value = entry.getValue() == null ?  "" : entry.getValue();
				params.add(new BasicNameValuePair(entry.getKey(), value));
				sb.append(entry.getKey()).append(" = ").append(entry.getValue()).append(", ");
			}
			logger.info("doGet : url=" + url + ". params [" + sb.toString() + "] ");
		}

		Map<String, String> resultMsg = new HashMap<String, String>();
		
//		try {
			String strParam = EntityUtils.toString(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpGet httpget = new HttpGet(url);
			url = httpget.getURI().toString() + "?" + strParam;
			logger.info("http get url="+url);
			httpget.setURI(new URI(url));
			HttpParams httpParameters = new BasicHttpParams();
			// 设置请求超时10秒
			HttpConnectionParams.setConnectionTimeout(httpParameters, 10 * 1000);
			// 设置等待数据超时10秒
			HttpConnectionParams.setSoTimeout(httpParameters, 10 * 1000);
			HttpClient httpClient = new DefaultHttpClient(httpParameters);
			// 发送请求
			HttpResponse httpresponse = httpClient.execute(httpget);
			HttpEntity entity = httpresponse.getEntity();
			resultMsg.put("code", String.valueOf(httpresponse.getStatusLine().getStatusCode()));
			if (charsetName != null && charsetName.trim().length() > 0) {
				resultMsg.put("message", EntityUtils.toString(entity, charsetName));
			} else {
				resultMsg.put("message", EntityUtils.toString(entity));
			}
			
			httpClient.getConnectionManager().shutdown();

			logger.info("resultMsg=" + resultMsg);
//		} catch (IOException e) {
//			logger.error("doPost error! ", e);
//		} catch (Exception e) {
//			logger.error("doPost error! ", e);
//		}
		return resultMsg;
	}
	
	public static Map<String, String> doGet(String url, Map<String, String> headerMap, Map<String, String> paramsMap) throws Exception {
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if(null != paramsMap && paramsMap.size() > 0) {
			StringBuffer sb = new StringBuffer();
			for(Map.Entry<String, String> entry : paramsMap.entrySet()) {
				params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				sb.append(entry.getKey()).append(" = ").append(entry.getValue()).append(", ");
			}
			logger.info("doGet : url=" + url + ". params [" + sb.toString() + "] ");
		}

		Map<String, String> resultMsg = new HashMap<String, String>();
		
//		try {
			String strParam = EntityUtils.toString(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpGet httpget = new HttpGet(url);
			for(Map.Entry<String, String> entry : headerMap.entrySet()) {
				httpget.setHeader(entry.getKey(), entry.getValue());
			}
			httpget.setURI(new URI(httpget.getURI().toString() + "?" + strParam));
			HttpParams httpParameters = new BasicHttpParams();
			// 设置请求超时10秒
			HttpConnectionParams.setConnectionTimeout(httpParameters, 10 * 1000);
			// 设置等待数据超时10秒
			HttpConnectionParams.setSoTimeout(httpParameters, 10 * 1000);
			HttpClient httpClient = new DefaultHttpClient(httpParameters);
			// 发送请求
			HttpResponse httpresponse = httpClient.execute(httpget);
			HttpEntity entity = httpresponse.getEntity();
			resultMsg.put("code", String.valueOf(httpresponse.getStatusLine().getStatusCode()));
			resultMsg.put("message", EntityUtils.toString(entity));
			
			httpClient.getConnectionManager().shutdown();

			logger.info("resultMsg=" + resultMsg);
//		} catch (IOException e) {
//			logger.error("doPost error! ", e);
//		} catch (Exception e) {
//			logger.error("doPost error! ", e);
//		}
		return resultMsg;
	}
	
	/**
	 * http+post消息发送
	 * @param url 消息发送地址
	 * @param paramsMap 请求参数  <键,值> 的形式
	 * @return key取值为code和message
	 * @throws IOException 
	 */
	public static Map<String, String> doPost(String url, Map<String, String> paramsMap) throws IOException {

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if(null != paramsMap && paramsMap.size() > 0) {
			StringBuffer sb = new StringBuffer();
			for(Map.Entry<String, String> entry : paramsMap.entrySet()) {
				params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				sb.append(entry.getKey()).append(" = ").append(entry.getValue()).append(", ");
			}
			logger.info("doPost : url=" + url + ". params [" + sb.toString() + "] ");
		}

		Map<String, String> resultMsg = new HashMap<String, String>();

		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpParams httpParameters = new BasicHttpParams();
			// 设置请求超时10秒
			HttpConnectionParams.setConnectionTimeout(httpParameters, 10 * 1000);
			// 设置等待数据超时10秒
			HttpConnectionParams.setSoTimeout(httpParameters, 10 * 1000); 
			HttpClient httpClient = new DefaultHttpClient(httpParameters);
			// 发送请求
			HttpResponse httpresponse = httpClient.execute(httpPost);
			HttpEntity entity = httpresponse.getEntity();
			resultMsg.put("code", String.valueOf(httpresponse.getStatusLine().getStatusCode()));
			resultMsg.put("message", EntityUtils.toString(entity));
			
			httpClient.getConnectionManager().shutdown();

			logger.info("resultMsg=" + resultMsg);
		} catch (UnsupportedEncodingException e) {
			logger.error("doPost error!", e);
			throw e;
		} catch (ClientProtocolException e) {
			logger.error("doPost error!", e);
			throw e;
		} catch (IOException e) {
			logger.error("doPost error!", e);
			throw e;
		}
		return resultMsg;
	}
	
	/**
	 * http+post消息发送
	 * @param url 消息发送地址
	 * @param paramsMap 请求参数  <键,值> 的形式
	 * @return key取值为code和message
	 * @throws IOException 
	 */
	public static String doPostStr(String url, Map<String, String> map) throws IOException {

				List<NameValuePair> valuePairs = new LinkedList<NameValuePair>();
				for(Map.Entry<String, String> entry : map.entrySet()){
					valuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				
				// 配置超时时间
				RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(5000)
						.setSocketTimeout(5000).setRedirectsEnabled(true).build();
		 
				HttpPost httpPost = new HttpPost(url);
				// 设置超时时间
				httpPost.setConfig(requestConfig);
				CloseableHttpClient httpclient = HttpClients.createDefault();
				String strResult = "";
				int StatusCode=404;
				try {
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(valuePairs, "UTF-8");
					 String reqJson = GsonUtil.toJson(map);
					StringEntity s = new StringEntity(reqJson.toString());
					logger.info("params=" + entity);
					// 设置post求情参数
					httpPost.setEntity(s);
					HttpResponse httpResponse = httpclient.execute(httpPost);
		 
					if (httpResponse != null) {
						StatusCode=httpResponse.getStatusLine().getStatusCode();
						if (httpResponse.getStatusLine().getStatusCode() == 200) {
							strResult = EntityUtils.toString(httpResponse.getEntity());
							logger.info("post/"+StatusCode+":"+strResult);
							return strResult;
						}  else {
							strResult = "Error Response: " + httpResponse.getStatusLine().toString();
							logger.info("post/"+StatusCode+":"+strResult);
							strResult=null;
						}
					} else {
		 
					}
		 
				} catch (Exception e) {
					logger.error(e.getMessage());
				} finally {
		 
				}
				logger.info("resultMsg=" + strResult);
				return strResult;

	}
	
	/**
	 * http+post消息发送
	 * @param url 消息发送地址
	 * @param paramsMap 请求参数  <键,值> 的形式
	 * @return key取值为code和message
	 * @throws IOException 
	 */
	public static String doPostStr(String url, Map<String, String> headerMap,String json) throws IOException {

		StringEntity params = new StringEntity(json, "utf-8");
        
		logger.info("params=" + params);
		String resultMsg = "";
		try {
			
			HttpPost httpPost = new HttpPost(url);
			for(Map.Entry<String, String> entry : headerMap.entrySet()) {
				httpPost.setHeader(entry.getKey(), entry.getValue());
			}
			httpPost.setEntity(params);
			HttpParams httpParameters = new BasicHttpParams();
			// 设置请求超时10秒
			HttpConnectionParams.setConnectionTimeout(httpParameters, 10 * 1000);
			// 设置等待数据超时10秒
			HttpConnectionParams.setSoTimeout(httpParameters, 10 * 1000); 
			HttpClient httpClient = new DefaultHttpClient(httpParameters);
			// 发送请求
			HttpResponse httpresponse = httpClient.execute(httpPost);
			HttpEntity entity = httpresponse.getEntity();
			//System.out.println("response status: " + httpresponse.getStatusLine());  
//			System.out.println("----------:"+EntityUtils.toString(entity));
			resultMsg = EntityUtils.toString(entity);
			
			httpClient.getConnectionManager().shutdown();

			logger.info("resultMsg=" + resultMsg);
		} catch (UnsupportedEncodingException e) {
			logger.error("doPost error!", e);
			throw e;
		} catch (ClientProtocolException e) {
			logger.error("doPost error!", e);
			throw e;
		} catch (IOException e) {
			logger.error("doPost error!", e);
			throw e;
		}
		return resultMsg;
	}
	
	/*public static void main(String[] args) {
		Map<String, String> paramsMap = new HashMap<String, String>();
//		paramsMap.put("opcode", SmuConstant.OPCODE_SP_DEV_STATUS);
		paramsMap.put("imei", "86560902813671311111111111111111111");
		paramsMap.put("status", "1");
		paramsMap.put("createTime", "2016-07-01 15:49:30");
		paramsMap.put("sn", "8000191711111111111111111");
		paramsMap.put("phone", "64818846836");
		try {
			String reqJson = GsonUtil.toJson(paramsMap);
			String respone = doPostStr("http://qh.sqjz.net/loc/watch/response.php", reqJson);
			System.out.println(respone);
			logger.info("respJson===" +respone);
			
			RespMessage respMessage = (RespMessage) GsonUtil.getEntityFromRequest(
					respone, RespMessage.class);
			
			logger.info(respMessage.getRet() + "|" + respMessage.getRetInfo());
			Thread.sleep(1000);
			//0成功
			if ("0".equals(respMessage.getRet())){
				System.out.println("success");
			}else{
				System.out.println("falure");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
