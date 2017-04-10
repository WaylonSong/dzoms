package com.dz.common.ocr;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.io.FileUtils;

public class EYzmDownload {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpClient httpClient = new HttpClient();
				GetMethod httpGet = new GetMethod("http://hl.122.gov.cn/captcha1");
				for (int i = 0; i < 100; i++)
				try {
					httpGet.setQueryString(URIUtil.encodeQuery("nocache="+new Date().getTime()));
					System.out.println("获取验证码......");
				    int result = httpClient.executeMethod(httpGet);
				    System.out.println("Response status code: " + result);
				        
				    InputStream input = httpGet.getResponseBodyAsStream();
				    File yzmFile = new File("E:\\result\\Test\\yzm"+i
				    		+ ".jpg");
				    yzmFile.createNewFile();
				    FileUtils.copyInputStreamToFile(input, yzmFile);
				        
				    httpGet.releaseConnection();
				    
				    Thread.sleep(300);
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

}
