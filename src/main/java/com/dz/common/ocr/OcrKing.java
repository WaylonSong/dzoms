package com.dz.common.ocr;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringBufferInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class OcrKing {
	final static String apiKeys[]={
		"27f1a47f860b19d55fx3OtrTTIGKyQApk1GxBQ3ml54K9vhYr1GxS26DSEB6vg6ecm5haKqK8ZrQ",
		"005e5859fe4351c3deJntwBz396GLoFuXwinlfVlLFkb3qjBvo1Is8wzTeK6r0W7TArhFktuPXCg",
		"5d9f4b66f7d8978e78ZnRQM34iDIEBSwIFyTOtgUbuISiFDLU3xIffT2keqRfpCOGQ42jDAovrOFH7UvPEEv5W"};
	
	public static void main(String[] args) throws Exception {
		// the url below can not be changed !!
		//url=&service=OcrKingForCaptcha&language=eng&charset=7&apiKey=你的key
		String filePath="E:\\result\\Test\\yzm0.jpg";	
		String ret = ocr(filePath);
		System.out.println(ret);
	}
	
	private static int count=0;

	public static String ocr(String filePath) throws OcrTimesOutOfStackException {
		String apiUrl = "http://lab.ocrking.com/ok.html";
		String apiKey = apiKeys[count++%3];

		Map<String, String> dataMap = new HashMap<String, String>();

		// you need to modify parameters according to OcrKing Api Document 
		dataMap.put("service", "OcrKingForCaptcha");
		dataMap.put("language", "eng");
		dataMap.put("charset", "7");
		// 如果不传递原始url到type或乱传一个地址到type 结果很可能就是错的
		// 如果想禁止后台做任何预处理  type可以设为 http://www.nopreprocess.com
		// 如果确实不确定验证码图片的原url  type可以设为 http://www.unknown.com  此时后台只进行常用预处理
		dataMap.put("type", "http://file3.ocrking.com");
		// you need to modify parameters according to OcrKing Api Document 

		dataMap.put("apiKey", apiKey);
		Map<String, String> fileMap = new HashMap<String, String>();
		fileMap.put("ocrfile", filePath);
		String ret = postMultipart(apiUrl, dataMap, fileMap);
		
		System.out.println("OcrKing.ocr(),line 56,result:"+ret);
		
		String result = null;
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
	    DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(new StringBufferInputStream(ret));
			
			NodeList nl = document.getElementsByTagName("Result");
			if(nl.getLength()>0){
				result = nl.item(0).getTextContent();
			}
			NodeList nl2 = document.getElementsByTagName("Status");
			if(nl2.getLength()>0){
				String status = nl2.item(0).getTextContent();
				if(StringUtils.equals(status, "false")){
					throw new OcrTimesOutOfStackException(result);
				}
			}
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return result;
	}

			/**
			 * post data with file uploading
			 * @param urlStr  the address to upload
			 * @param dataMap post data
			 * @param fileMap file to upload
			 * @return xml result
			 */
			public static String postMultipart(String urlStr, Map<String, String> dataMap, Map<String, String> fileMap) {
				String res = "";
				HttpURLConnection conn = null;
				String boundary = "----------------------------OcrKing_Client_Aven_s_Lab";
				try {
					URL url = new URL(urlStr);
					conn = (HttpURLConnection) url.openConnection();
					conn.setConnectTimeout(5000);
					conn.setReadTimeout(30000);
					conn.setDoOutput(true);
					conn.setDoInput(true);
					conn.setUseCaches(false);
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Accept-Charset", "UTF-8");
					conn.setRequestProperty("Connection", "Keep-Alive");
					conn.setRequestProperty("Referer", "http://lab.ocrking.com/?javaclient0.1)");
					conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1; zh-CN; rv:1.9.1.3) Gecko/20100101 Firefox/8.0");
					conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

					OutputStream out = new DataOutputStream(conn.getOutputStream());
					// data   
					if (dataMap != null) {
						StringBuffer strBuf = new StringBuffer();
						Iterator<Map.Entry<String, String>> iter = dataMap.entrySet().iterator();
						while (iter.hasNext()) {
							Map.Entry<String, String> entry = iter.next();
							String inputName = (String) entry.getKey();
							String inputValue = (String) entry.getValue();
							if (inputValue == null) {
								continue;
							}
							strBuf.append("\r\n").append("--").append(boundary).append("\r\n");
							strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
							strBuf.append(inputValue);
						}
						out.write(strBuf.toString().getBytes());
					}

					// file  
					if (fileMap != null) {
						Iterator<Map.Entry<String, String>> iter = fileMap.entrySet().iterator();
						while (iter.hasNext()) {
							Map.Entry<String, String> entry = iter.next();
							String inputName = (String) entry.getKey();
							String inputValue = (String) entry.getValue();
							if (inputValue == null) {
								continue;
							}
							File file = new File(inputValue);
							String filename = file.getName();

							StringBuffer strBuf = new StringBuffer();
							strBuf.append("\r\n").append("--").append(boundary).append("\r\n");
							strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename + "\"\r\n");
							strBuf.append("Content-Type:application/octet-stream\r\n\r\n");

							out.write(strBuf.toString().getBytes());

							DataInputStream in = new DataInputStream(new FileInputStream(file));
							int bytes = 0;
							byte[] bufferOut = new byte[1024];
							while ((bytes = in.read(bufferOut)) != -1) {
								out.write(bufferOut, 0, bytes);
							}
							in.close();
						}
					}

					byte[] endData = ("\r\n--" + boundary + "--\r\n").getBytes();
					out.write(endData);
					out.flush();
					out.close();

					// handle the response 
					StringBuffer strBuf = new StringBuffer();
					BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String line = null;
					while ((line = reader.readLine()) != null) {
						strBuf.append(line).append("\n");
					}
					res = strBuf.toString();
					reader.close();
					reader = null;
				} catch (Exception e) {
					System.out.println("error " + urlStr);
					//e.printStackTrace();
				} finally {
					if (conn != null) {
						conn.disconnect();
						conn = null;
					}
				}
				return res;
			}

}
