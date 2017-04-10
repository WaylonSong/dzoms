package com.dz.common.ocr;

import java.io.File;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.dz.common.other.ObjectAccess;
import com.dz.module.vehicle.Vehicle;

public class OcrAnaylse {
	
	private static final String OCR_BASE = "E:\\result";
	private static final String YZM_PATH = "E:\\result\\raw\\yzm";
	private static List<Vehicle> vl = null;
	private static int count = 0 ,errcount = 0;

	public static void main(String[] args) {
		if(args.length>0){
			init(args[0]);
		}else{
			init();
		}

		System.out.println("count="+vl.size());
		
		while(check()){
			if(count+errcount>=60000){
				System.out.println("本日查询次数已用完，请在明日继续！");
				break;
			}
			fecth();
			try {
				Thread.sleep(500l);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("count="+count);
		System.out.println("errcount="+errcount);
	}
	
	private static void init(String string) {
		// TODO Auto-generated method stub
		vl = ObjectAccess.query(Vehicle.class, String.format(" carframeNum not in (select carframeNum from ElectricFromHtml where fetchTime >'%s')",string));
	}

	private static void init(){
		//vl = new ArrayList<Vehicle>();
		Calendar cl = Calendar.getInstance();
		cl.add(Calendar.DATE, -1);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		vl = ObjectAccess.query(Vehicle.class, String.format(" carframeNum not in (select carframeNum from ElectricFromHtml where fetchTime >'%s')",df.format(cl.getTime())));
		//vl.add(new Vehicle("LFPH3ACB0F1D33866","黑ATS915"));
		//vl.add(new Vehicle("LFV2A11G0A3145507","黑ATE330"));
		//vl.add(new Vehicle("LFV2A11G0A3145524","黑ATD821"));
		//vl.add(new Vehicle("LFV2A11G0A3145541","黑ATE362"));
		//vl.add(new Vehicle("LFV2A11G0A3146379","黑ATD721"));
		
	}
	
	private static boolean check(){
		if(count<vl.size())
			return true;
		return false;
	}

	private static void fecth() {
		Vehicle vehicle = vl.get(count);
		
		HttpClient httpClient = new HttpClient();
		//httpClient.getParams().setSoTimeout(30000);
		GetMethod httpGet = new GetMethod("http://www.hljjj.gov.cn:8081/Home/Wfcx");
		GetMethod httpGetYZM;
		try {
			//System.out.println("请求网站......");
			int result = httpClient.executeMethod(httpGet);
	        //System.out.println("Response status code: " + result);

	        httpGet.releaseConnection();
	        
	        httpGetYZM = new GetMethod("http://www.hljjj.gov.cn:8081/Home/Yzm");
	        httpGetYZM.setQueryString(URIUtil.encodeQuery("time="+new Date().getTime()));
	        
	        //System.out.println("获取验证码......");
	        result = httpClient.executeMethod(httpGetYZM);
	        //System.out.println("Response status code: " + result);
	        
	        InputStream input = httpGetYZM.getResponseBodyAsStream();
//	        File yzmFile = new File(YZM_PATH+errcount+".jpg");
	        File yzmFile = new File(YZM_PATH+".jpg");
	        yzmFile.createNewFile();
	        FileUtils.copyInputStreamToFile(input, yzmFile);
	        
	        httpGetYZM.releaseConnection();
	        
	       // System.out.println("请输入验证码:");
	       // Scanner sc = new Scanner(System.in);
	       // String yzmStr = sc.next().trim();
	        
	        File ocrBase = new File(OCR_BASE);
	        
	        ImageExe.exec(yzmFile, ocrBase);
	        
	        String yzmStr = null;
	        
	        try{
	        	yzmStr = OcrKing.ocr(ocrBase+"/"+yzmFile.getName()+".tif");
	        }catch(OcrTimesOutOfStackException e){
	        	errcount+=60000;
	        	return;
	        }
	        
	        //System.out.println(yzmStr);
	        
	        PostMethod postMethod = new PostMethod("http://www.hljjj.gov.cn:8081/Home/getWfcx");
	        postMethod.addParameter("hpzl", "02");
	        postMethod.addParameter("dy", new String("黑".getBytes(),"ISO-8859-1"));
	        
//	        System.out.println("OcrAnaylse.fecth(),line 117,yzm="+yzmStr);
	        
	        
	        postMethod.addParameter("yzm", yzmStr);
	        postMethod.addParameter("xzqh", "A"); //车牌 区号 A
	        postMethod.addParameter("hphm", StringUtils.right(vehicle.getLicenseNum(), 5));// 车牌号 不含地区号
	        postMethod.addParameter("clsbdh", vehicle.getCarframeNum()); //车架号
	        postMethod.addParameter("jkbj", "%");
	        postMethod.addParameter("ts", ""+new Date().getTime());
	        
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
	        
	        //System.out.println("查询信息中......");
	        result = httpClient.executeMethod(postMethod);
	       // System.out.println("Response status code: " + result);
	        
	        if(result==200){
	        	InputStream input2 = postMethod.getResponseBodyAsStream();
		        
	        	StringBuffer sb = new StringBuffer();
		        int l;
		        byte[] tmp = new byte[65536];

		        while ((l = input2.read(tmp)) > 0) {
		        	sb.append(new String(tmp,0,l,"utf-8"));
		        }
		        String ss = sb.toString();
		        
//		        System.out.println(count+ss);
		        
		        String[] sarr = ss.split(",");
		        if(!StringUtils.contains(sarr[0], "0")){
		        	if(StringUtils.contains(ss, "号牌号码或车辆识别代号填写错误")){
				        System.out.println(count+" "+vehicle.getCarframeNum()+" "+vehicle.getLicenseNum()+" "+ss);
				        count++;
				    }
		        	errcount++;
		        	postMethod.releaseConnection();
		        	return;
		        }
		        
		        //String itemStr = StringUtils.remove(sarr[1], '"');
		        int item = StringUtils.countMatches(sarr[2], "tr") / 2 - 1;
		        
		        ElectricFromHtml elec = new ElectricFromHtml();
		        elec.setCarframeNum(vehicle.getCarframeNum());
		        elec.setFetchItems(item);
		        elec.setFetchTime(new Date());
		        
		        if(item==0){
		        	ObjectAccess.saveOrUpdate(elec);
		        	System.out.println("该车无违章记录！");
		        }else{
		        	 elec.setFetchResult(ss.trim());
		        	 ObjectAccess.saveOrUpdate(elec);
		        }
		        
		       count++ ;
		       System.out.println(count);
	        }

	        postMethod.releaseConnection();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
