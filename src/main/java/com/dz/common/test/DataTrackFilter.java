package com.dz.common.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dz.common.other.Timer;

public class DataTrackFilter implements Filter{
	
	private static ApplicationContext ctx;

	public static ApplicationContext getCtx() {
		return ctx;
	}

	public void doFilter(ServletRequest trequest, ServletResponse tresponse,
			FilterChain nextFilter) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) trequest;
		HttpServletResponse response = (HttpServletResponse) tresponse;
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String url = request.getServletPath();
		
		if(!StringUtils.startsWithAny(url, new String[]{"/res","/login","/user","/uploadFileTo","/isExist","/download","/charge/receipt","/driver/meeting/check","/driver/driverShowApplyCheckPrint"})
				&&!url.trim().equals("/")){
			if(request.getSession().getAttribute("user")==null){
				response.setStatus(401);
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("您尚未登陆，请登录后访问！");
				out.flush();
				out.close();
				return;
			}
		}
		
		System.out.println("Track begin......");
		System.out.println(url);
		Map<String,String[]> maps = request.getParameterMap();
		for(Map.Entry<String,String[]> entry:maps.entrySet()){
			System.out.println(entry.getKey()+"="+decodeStringArray(entry.getValue()));
		}
		System.out.println("Track end......\n");
		
		ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext()); 
		
		nextFilter.doFilter(request, response);
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		String webRootPath =filterConfig.getServletContext().getRealPath("/");  
        System.setProperty("com.dz.root" , webRootPath);
        String path =System.getProperty("com.dz.root");  
        System.out.println("com.dz.root:"+path);  
        
        new Timer().startTcpServer();
        
	}
	private String decodeStringArray(String[] ss){
		if(ss.length == 0)
			return "";
		if(ss.length == 1)
			return ss[0];
		String s = "";
		for(int i = 0;i < ss.length-1;++i){
			s += ss[i];
			s += " & ";
		}
		s+=ss[ss.length-1];
		return s;
	}
}
