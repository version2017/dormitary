package com.merit.utils.weixin.util;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class KddUtil {
	public static Log _logger = LogFactory.getLog(KddUtil.class);
	public static String kdd_url="http://211.156.193.140:8000/cotrackapi/api/track/mail/";
	public static String doGet( String queryString,String charset) {
		String response = null;
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(kdd_url+queryString);
		method.setRequestHeader(new Header("authenticate", "4ab3d9a87bf179f83f722339f96118ba"));
		method.setRequestHeader(new Header("version", "ems_track_cn_1.0"));
		try {
//			if (StringUtils.isNotBlank(queryString))
//				method.setQueryString(URIUtil.encodeQuery(queryString));
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),charset));  
				StringBuffer stringBuffer = new StringBuffer();  
				String str = "";  
				while((str = reader.readLine())!=null){  
				    stringBuffer.append(str);  
				}  
				response = stringBuffer.toString();
				reader.close();
			}
		} catch (URIException e) {
			_logger.error("执行HTTP Get请求时，编码查询字符串“" + queryString + "”发生异常！", e);
		} catch (IOException e) {
			_logger.error("执行HTTP Get请求" + kdd_url + "时，发生异常！", e);
		} finally {
			method.releaseConnection();
		}
		
		return response;
	}
}
