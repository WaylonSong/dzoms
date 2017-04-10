package com.dz.common.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HostParams;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.io.FileUtils;

public class ElectricTest3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpClient httpClient = new HttpClient();
		
		//httpClient.getParams().setParameter("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0");
		GetMethod httpGet = new GetMethod("http://www.hljjj.gov.cn:8081/Home/Wfcx");
		GetMethod httpGetYZM;
		try {
			System.out.println("请求网站......");
			int result = httpClient.executeMethod(httpGet);
			 // Display status code
	        System.out.println("Response status code: " + result);
	        // Get all the cookies
//	        Cookie[] cookies = httpClient.getState().getCookies();
	        // Display the cookies
//	        System.out.println("Present cookies: ");
//	        for (int i = 0; i < cookies.length; i++) {
//	            System.out.println(" - " + cookies[i].toExternalForm());
//	        }	        
	        httpGet.releaseConnection();
	        
	        httpGetYZM = new GetMethod("http://www.hljjj.gov.cn:8081/Home/Yzm");
	        httpGetYZM.setQueryString(URIUtil.encodeQuery("time="+new Date().getTime()));
	        
	        System.out.println("获取验证码......");
	        result = httpClient.executeMethod(httpGetYZM);
	        System.out.println("Response status code: " + result);
	        
	        InputStream input = httpGetYZM.getResponseBodyAsStream();
	        File yzmFile = new File("C:\\Users\\Xiaoyao\\Desktop\\项目\\电子违章\\yzm.jpg");
	        yzmFile.createNewFile();
	        FileUtils.copyInputStreamToFile(input, yzmFile);
	        
	        httpGetYZM.releaseConnection();
	        
	        Cookie[] cookies = httpClient.getState().getCookies();
	        System.out.println("Present cookies: ");
	        for (int i = 0; i < cookies.length; i++) {
	            System.out.println(" - " + cookies[i].toExternalForm());
	        }	 
	        
	        System.out.println("请输入验证码:");
	        Scanner sc = new Scanner(System.in);
	        String yzmStr = sc.next().trim();
	        
	        PostMethod postMethod = new PostMethod("http://www.hljjj.gov.cn:8081/Home/getWfcx");
	        postMethod.addParameter("hpzl", "02");
	        postMethod.addParameter("dy", new String("黑".getBytes(),"ISO-8859-1"));
	        postMethod.addParameter("yzm", yzmStr);
	        postMethod.addParameter("xzqh", "A"); //车牌 区号 A
	        postMethod.addParameter("hphm", "TS915");// 车牌号 不含地区号
	        postMethod.addParameter("clsbdh", "LFPH3ACB0F1D33866"); //车架号
	        postMethod.addParameter("jkbj", "0");
	        postMethod.addParameter("ts", ""+new Date().getTime());
	        
//	        HostConfiguration hostConfiguration = httpClient.getHostConfiguration();
//			hostConfiguration.setHost("www.hljjj.gov.cn:8081");
//			HostParams hps = new HostParams();
//			hps.setParameter("Accept", "application/json, text/javascript, */*");
//			hps.setParameter("Accept-Encoding", "gzip, deflate");
//			hps.setParameter("Content-Type", "application/x-www-form-urlencoded");
//			hps.setParameter("X-Requested-With", "XMLHttpRequest");
//			hps.setIntParameter("DNT", 1);
//			hps.setParameter("Referer", "http://www.hljjj.gov.cn:8081/Home/Wfcx");
//			hps.setParameter("Connection", "keep-alive");
//			hostConfiguration.setParams(hps);
//			postMethod.setHostConfiguration(hostConfiguration);
	        
	        
	        
	        List <Header> headers = new ArrayList <Header>(); 
	        headers.add(new Header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0")); 
	        headers.add(new Header("Accept", "application/json, text/javascript, */*"));
	        headers.add(new Header("Accept-Encoding", "gzip, deflate"));
	        headers.add(new Header("Content-Type", "application/x-www-form-urlencoded"));
	        headers.add(new Header("X-Requested-With", "XMLHttpRequest"));
	        headers.add(new Header("DNT", "1"));
	        headers.add(new Header("Referer", "http://www.hljjj.gov.cn:8081/Home/Wfcx"));
	        headers.add(new Header("Connection", "keep-alive"));
	        //headers.add(new Header("Content-Length","91"));
	        httpClient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
	        
//	        postMethod.setQueryString(URIUtil.encodeQuery("hpzl=02&dy=&yzm=" + yzmStr + "&xzqh=" + "A" + "&hphm=" + "TS915" + "&clsbdh=" + "LFPH3ACB0F1D33866" + "&jkbj=0"+"&ts"+new Date().getTime()));
	        
//	        GetMethod getMethod = new GetMethod("http://www.hljjj.gov.cn:8081/Home/getWfcx");
//	        getMethod.setQueryString(URIUtil.encodeQuery("hpzl=02&dy=&yzm=" + yzmStr + "&xzqh=" + "A" + "&hphm=" + "TS915" + "&clsbdh=" + "LFPH3ACB0F1D33866" + "&jkbj=0"+"&ts"+new Date().getTime()));
	        
	        
	        System.out.println("查询信息中......");
	        result = httpClient.executeMethod(postMethod);
//	        result = httpClient.executeMethod(getMethod);
	        System.out.println("Response status code: " + result);
	        
	        cookies = httpClient.getState().getCookies();
	        System.out.println("Present cookies: ");
	        for (int i = 0; i < cookies.length; i++) {
	            System.out.println(" - " + cookies[i].toExternalForm());
	        }	 
	        
	        InputStream input2 = postMethod.getResponseBodyAsStream();
//	        InputStream input2 = getMethod.getResponseBodyAsStream();
	        
	        int l;
	        byte[] tmp = new byte[2048];
	        System.out.println();
	        while ((l = input2.read(tmp)) != -1) {
	        	System.out.print(new String(tmp));
	        }
	        System.out.println();
	        
	        postMethod.releaseConnection();
//	        getMethod.releaseConnection();
	        
//	        PostMethod postMethod2 = new PostMethod("http://www.hljjj.gov.cn:8081/Home/getWfcx");
//	        postMethod2.addParameter("hpzl", "02");
//	        postMethod2.addParameter("dy", "黑");
//	        postMethod2.addParameter("yzm", yzmStr);
//	        postMethod2.addParameter("xzqh", "A"); //车牌 区号 A
//	        postMethod2.addParameter("hphm", "50361");// 车牌号 不含地区号
//	        postMethod2.addParameter("clsbdh", "LFPH4ABA571B00637"); //车架号
//	        postMethod2.addParameter("jkbj", "0");
//	        postMethod2.addParameter("ts", ""+new Date().getTime());
//	        
//	        System.out.println("查询信息中......");
//	        result = httpClient.executeMethod(postMethod2);
//	        System.out.println("Response status code: " + result);
//	        
//	        InputStream input22 = postMethod2.getResponseBodyAsStream();
//	        
//	        int l2;
//	        byte[] tmp2 = new byte[2048];
//	        System.out.println();
//	        while ((l2 = input22.read(tmp2)) != -1) {
//	        	System.out.print(new String(tmp2));
//	        }
//	        System.out.println();
//	        
//	        postMethod2.releaseConnection();
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
