package com.dz.common.other;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;


public class FileUploadUtil extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws
    ServletException, IOException{
        String seq = getRandomString(30);
		
		if(request.getServletContext().getAttribute("TempFileMap")==null){
			request.getServletContext().setAttribute("TempFileMap", new ConcurrentHashMap<String,String>());
		}
		
		//数组  seq : filename
		Map<String,String> map = (Map<String,String>)request.getServletContext().getAttribute("TempFileMap");
		
		while(map.containsKey(seq)) seq = getRandomString(30);
		
		int index = 0;
		try{
			DiskFileItemFactory factory = new DiskFileItemFactory(4096, new File(System.getProperty("com.dz.root")+"tmp"));
			ServletFileUpload upload = new ServletFileUpload(factory); 
			upload.setFileSizeMax(-1);
			
			List<FileItem> items = new ArrayList<FileItem>(); 
		    try { 
		        //上传文件并解析出所有的表单字段包括普通字段和文件字段 
		    	items = upload.parseRequest(request); 
		    } catch (FileUploadException e1) { 
		    	System.out.println("文件上传发生错误" + e1.getMessage()); 
		    }
		    
		    Iterator<FileItem> i = items.iterator();
		    
		    while(i.hasNext()){
		    	FileItem fileItem = (FileItem) i.next();
		    	if(fileItem.isFormField()){ 
		    		String fieldname = fileItem.getFieldName();
		    		if(fieldname.equalsIgnoreCase("index")){
		    			index = Integer.parseInt(fileItem.getString());
		    		}
				    //文本数据。。。。。。
			    }else{
			    	String fileName = fileItem.getName();
			    	fileItem.write(new File(System.getProperty("com.dz.root")+"tmp",seq));
			    	
					//FileUtils.copyFile(file, new File(System.getProperty("com.dz.root")+"tmp",seq));
					map.put(seq, fileName);
			    }
		    }
		    
		}catch(Exception e){
			e.printStackTrace();
			seq = "ERROR";
		}
        
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html>"
				+ "<html lang=\"zh-cn\">"
				+ "	   <head>"
				+ "       <script>"
				+ "		   window.onload=function(){"
				+ "           parent.fileUploadResult("+index+");"
				+ "        };"
				+ "       </script>"
				+ "    </head>"
				+ "    <body>"
				+ "      <input id='result' value='"+seq+"'>"
				+ "    </body>"
				+ "</html>");
		out.flush();
		out.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}
	
	public static InputStream getFileStream(String seq) throws IOException{
		return FileUtils.openInputStream(new File(System.getProperty("com.dz.root")+"tmp",seq));
	}
	
	public static boolean store(String seq,File targetFile) {
		try {
			FileUtils.copyInputStreamToFile(getFileStream(seq), targetFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private static String getRandomString(int length){
	     String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	     Random random=new Random();
	     StringBuffer sb=new StringBuffer();
	     for(int i=0;i<length;i++){
	       int number=random.nextInt(62);
	       sb.append(str.charAt(number));
	     }
	     return sb.toString();
	 }
}
