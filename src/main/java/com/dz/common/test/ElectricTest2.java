package com.dz.common.test;
/**
 * 
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dz.common.global.BaseAction;

@Controller(value="electricTest")
@Scope("prototype")
public class ElectricTest2 extends BaseAction {
	/**
	 * {"resultcode":"200","reason":"查询成功##","result":{"province":"HLJ","city":"HLJ_HAERBIN","hphm":"黑A53773","hpzl":"02","lists":[]},"error_code":0}
		{"resultcode":"200","reason":"成功返回","result":{"surplus":"9"},"error_code":0}

	 */
	public void getCityConfigure() throws IOException{
		//ss
		ServletActionContext.getResponse().setContentType("application/json");
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
	
		out.print(getCityConfig());
		
		out.flush();
		out.close();
	}
	
	public void getTimeLeave() throws IOException{
		ServletActionContext.getResponse().setContentType("application/json");
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
	
		out.print(getTimeLeast());
		
		out.flush();
		out.close();
	}
	
	public void getInfo() throws IOException{
		
		
		ServletActionContext.getResponse().setContentType("application/json");
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		
//		String sb = getCarInfo(carNo, carframeNum, "HLJ_HAERBIN");
//		out.print(sb);
		
		/**
{
	"resultcode":"200",
	"reason":"查询成功",
	"result":{
		"province":"HB",
		"city":"HB_HD",
		"hphm":"冀DHL327",
		"hpzl":"02",
		"lists":[
			{
			"date":"2013-12-29 11:57:29",
			"area":"316省道53KM+200M",
			"act":"16362 : 驾驶中型以上载客载货汽车、校车、危险物品运输车辆以外的其他机动车在高速公路以外的道路上行驶超过规定时速20%以上未达50%的",
			"code":"",
			"fen":"6",
			"money":"100",
			"handled":"0"
			}
		]
	}
}
		 * 
		 */
		
		String failResult = "{\"resultcode\":\"200\",\"reason\":\"查询成功##\",\"result\":{\"province\":\"HLJ\",\"city\":\"HLJ_HAERBIN\",\"hphm\":\"黑A53773\",\"hpzl\":\"02\",\"lists\":[]},\"error_code\":0}";
		out.print(failResult);
		
		out.flush();
		out.close();
	}
	
	private String carNo,carframeNum,engineNo,cityId,carType;

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getCarframeNum() {
		return carframeNum;
	}

	public void setCarframeNum(String carframeNum) {
		this.carframeNum = carframeNum;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

 public static String URL ="http://v.juhe.cn/wz/";
	 
 public static String appKey="2b243f84276ceb37757248eb80587bef";
 
	/**  
	 * title:测试方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			
			System.out.println(getCarInfo("黑A53773", "LFV2A51G9D3005239", "HLJ_HAERBIN"));
			
			//检测是否支持 查询 该城市
			//System.out.println(getCityConfig());
			
			//查询剩余次数
			//System.out.println(getTimeLeast());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * title:获取违章信息
	 * 
	 * @param carInfo
	 * @return
	 */
	public static String getCarInfo(String carNo, String carFrameNum,String city) {

		String line = null;
		try {
			URL postUrl = new URL(URL + "query?");
			String content =  String.format("key=%s&city=%s&hphm=%s&hpzl=02&classno=%s", appKey,city,URLEncoder.encode(carNo,"UTF-8"),carFrameNum);			
			line = post(postUrl, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return line;
		
		/**
		 * String url ="http://v.juhe.cn/wz/query";//请求接口地址
        Map<String, Object> params = new HashMap<String, Object>();//请求参数
            params.put("dtype","");//返回数据格式：json或xml或jsonp,默认json
            params.put("callback","");//返回格式选择jsonp时，必须传递
            params.put("key",APPKEY);//你申请的key
            params.put("city","");//城市代码 *
            params.put("hphm","");//号牌号码 完整7位 ,需要utf8 urlencode*
            params.put("hpzl","");//号牌类型，默认02
            params.put("engineno","");//发动机号 (根据城市接口中的参数填写)
            params.put("classno","");//车架号 (根据城市接口中的参数填写)
 
        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		 */
	}

	/**
	 * title:获取省份城市对应ID配置
	 * 
	 * @return
	 * @throws IOException
	 */
	public static String getCityConfig() throws IOException {
		String line = null;
		try {
			URL postUrl = new URL(URL + "citys?");
			String content = String.format("province=HLJ&dtype=json&key=%s", appKey);
			line = post(postUrl, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return line;
		
		/***
		 * String result =null;
        String url ="http://v.juhe.cn/wz/citys";//请求接口地址
        Map<String, Object> params = new HashMap<String, Object>();//请求参数
            params.put("province","");//默认全部，省份简写，如：ZJ、JS
            params.put("dtype","");//返回数据格式：json或xml或jsonp,默认json
            params.put("format","");//格式选择1或2，默认1
            params.put("callback","");//返回格式选择jsonp时，必须传递
            params.put("key",APPKEY);//你申请的key
 
        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		 */

	}
	
	public static String getTimeLeast(){
		/** String result =null;
        String url ="http://v.juhe.cn/wz/status";//请求接口地址
        Map<String, Object> params = new HashMap<String, Object>();//请求参数
            params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
            params.put("dtype","");//返回数据的格式,xml或json，默认json
 
        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
		String line = null;
		try {
			URL postUrl = new URL(URL + "status?");
			String content = String.format("key=%s", appKey);
			line = post(postUrl, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return line;
	}
	
	
	

	/**
	 * title:post请求
	 * 
	 * @param postUrl
	 * @param content
	 * @return
	 */
	private static String post(URL postUrl, String content) {
		String line = null;
		try {
			HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.connect();
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(content);
			out.flush();
			out.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));

			while ((line = reader.readLine()) != null) {
				return line;
			}
			reader.close();
			connection.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return line;
	}
}

