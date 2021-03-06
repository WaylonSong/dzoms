package com.dz.module.driver.meeting;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.struts2.ServletActionContext;
import org.javatuples.Triplet;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.xml.sax.SAXException;

import com.dz.common.global.BaseAction;
import com.dz.common.global.Page;
import com.dz.common.other.FileAccessUtil;
import com.dz.common.other.ObjectAccess;
import com.dz.common.other.PageUtil;
import com.dz.common.other.TimeComm;
import com.dz.module.driver.Driver;
import com.dz.module.user.User;

@Controller
@Scope("prototype")
public class MeetingAction extends BaseAction {
	@Autowired
	private MeetingService meetingService;
	@Autowired
	private FileAccessUtil fileAccessUtil;
	
	public void setMeetingService(MeetingService meetingService) {
		this.meetingService = meetingService;
	}
	
	public void setFileAccessUtil(FileAccessUtil fileAccessUtil) {
		this.fileAccessUtil = fileAccessUtil;
	}
	
	private Meeting meeting;

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}
	
	private File[] fileUploads;
	private String[] fileUploadsFileName;
	private String[] fileUploadsContentType;
	
	private List<Date> time;
	private List<String> dept;
	private List<String> type;
	
	private List<String> driverlist;
	
	public File[] getFileUploads() {
		return fileUploads;
	}
	public String[] getFileUploadsFileName() {
		return fileUploadsFileName;
	}
	public String[] getFileUploadsContentType() {
		return fileUploadsContentType;
	}
	public void setFileUploads(File[] fileUploads) {
		this.fileUploads = fileUploads;
	}
	public void setFileUploadsFileName(String[] fileUploadsFileName) {
		this.fileUploadsFileName = fileUploadsFileName;
	}
	public void setFileUploadsContentType(String[] fileUploadsContentType) {
		this.fileUploadsContentType = fileUploadsContentType;
	}
	
	private Date beginDate,endDate;
	
	public Date getBeginDate() {
		return beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String addMeeting(){
		try {
			
			User user = (User) session.getAttribute("user");
			meeting.setRegistrant(user.getUid());
			meeting.setDriverList(driverlist.size());
			
			meeting.setAlreadyChecked(false);
			meeting.setUncheckedNum(driverlist.size());
			meeting.setCheckedNum(0);
					
			//Date maxDate = Collections.max(Arrays.asList(time));
			Date minDate = Collections.min(time);
			
			meeting.setMeetingTime(minDate);
			
			for(int i=0;i<time.size();i++){
				switch(dept.get(i)+type.get(i)){
				case "一部例会":
					meeting.setMeetingTimeL1(time.get(i));
					break;
				case "二部例会":
					meeting.setMeetingTimeL2(time.get(i));
					break;
				case "三部例会":
					meeting.setMeetingTimeL3(time.get(i));
					break;
				case "一部补会":
					meeting.setMeetingTimeB1(time.get(i));
					break;
				case "二部补会":
					meeting.setMeetingTimeB2(time.get(i));
					break;
				case "三部补会":
					meeting.setMeetingTimeB3(time.get(i));
					break;
				}
			}

			meetingService.addMeeting(meeting);
			
			for(String idNum:driverlist){
				String m_dept = ((Driver)ObjectAccess.getObject("com.dz.module.driver.Driver", idNum)).getDept();
				int index = dept.indexOf(m_dept);
				if(index>=0){
					meetingService.addMeetingCheck(new MeetingCheck(meeting.getId(),idNum, time.get(index), null, null, null));
				}
			}
			
			String basePath = System.getProperty("com.dz.root") +"data/driver/meeting/";
			if(fileUploads!=null&&fileUploads.length>0){
				fileAccessUtil.store(basePath,""+ meeting.getId(), fileUploads, fileUploadsFileName);
			}
		} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
		}
		return SUCCESS;
	}
	
	public String searchMeeting(){
		int currentPage = 0;
		if (request.getParameter("currentPage") != null
				&& !(request.getParameter("currentPage")).isEmpty()) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));

			System.out.println(request.getParameter("currentPage"));
		} else {
			currentPage = 1;
		}
		Page page = PageUtil.createPage(15,
				meetingService.selectAllMeetingCount(beginDate,endDate), currentPage);
		List<Meeting> list = meetingService.selectAllMeeting(page,beginDate,endDate);
		
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		return SUCCESS;
	}
	
	
	private List<MeetingCheck> checkList;
	private List<String> fileList;
	
	private Integer totalPage,nowPage;
	
	public String selectMeetingById(){
		Meeting m  = (Meeting) ObjectAccess.getObject("com.dz.module.driver.meeting.Meeting", meeting.getId());
		this.setMeeting(m);
		
		Triplet<String, String, Object> dept_need = Triplet.with("idNum", "in (select idNum from Driver where dept is not null ) and 1=", (Object)1);
		
		checkList = meetingService.selectMeetingCheck(meeting.getId(),dept_need);
		
		if(nowPage==null||nowPage==0) nowPage=1;
		
		totalPage = (checkList.size()+19)/20;
		
		if(totalPage==0) totalPage=1;
		
		nowPage = nowPage % totalPage;
		
		if(nowPage==null||nowPage==0) nowPage=1;
		
		System.out.println(nowPage);
		System.out.println(totalPage);
		
		checkList = checkList.subList(Math.max((nowPage-1)*20,0), Math.min(nowPage*20,checkList.size()));
		
		System.out.println(checkList);
		
		String basePath = System.getProperty("com.dz.root") +"data/driver/meeting/";

		File[] fs = fileAccessUtil.list(basePath+ meeting.getId());
		fileList = new ArrayList<String>();
		
		if(fs!=null)
		for(File f:fs){
			fileList.add(f.getName());
		}
		
		request.setAttribute("meeting", meeting);
		request.setAttribute("checkList", checkList);
		
		return SUCCESS;
	}
	
	private String idNum;
	private String method,checkClass;
	private Integer meetingId;
	private Date checkTime;
	
	private String errorMsg;
	
	public String manmalCheck(){
		String result = m_manmalCheck();
		session.setAttribute("errorMsg", errorMsg);
		selectMeetingById();
		return SUCCESS;
	}
	
	/**
	 * 通过。NET客户端签到
	 * @throws IOException
	 */
	public void checkByNet() throws IOException{
		User user = ObjectAccess.query(User.class, "uname='"+uname+"'").get(0);
		session.setAttribute("user", user);
		String result = m_manmalCheck();
		JSONObject json = new JSONObject();
		if(result.equals(SUCCESS)){
			json.put("result", true);
		}else{
			json.put("result", false);
			json.put("msg", errorMsg);
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	private String uname;
		
	@SuppressWarnings("deprecation")
	private String m_manmalCheck(){
		MeetingCheck meetingCheck = meetingService.selectMeetingCheck(meetingId, idNum);
		
		if(meetingCheck==null){
			errorMsg = "该驾驶员不在该例会中。";
			return ERROR;
		}
		
		meetingCheck.setCheckTime(checkTime);
		meetingCheck.setCheckMethod(method);
		meetingCheck.setCheckClass(checkClass);

		
		Date checkBegin = TimeComm.convertDate(meetingCheck.getNeedCheckTime()),checkEndDate = TimeComm.convertDate(meetingCheck.getNeedCheckTime());
		checkBegin.setHours(12);
		checkBegin.setMinutes(0);
		checkEndDate.setHours(17);
		checkEndDate.setMinutes(30);

		
		Driver d = (Driver) ObjectAccess.getObject("com.dz.module.driver.Driver",idNum);
		Meeting m = (Meeting) ObjectAccess.getObject("com.dz.module.driver.meeting.Meeting",meetingId);
		
		Date checkMostBegin;
		long mostBegin=Long.MAX_VALUE;
		
//		System.out.println("MeetingAction.m_manmalCheck(),"+m.getMeetingTimeL1()+","+m.getMeetingTimeL2()+","+m.getMeetingTimeL3());
		
		if(m.getMeetingTimeL1()!=null){
			mostBegin = Math.min(mostBegin, m.getMeetingTimeL1().getTime());
		}
		
		if(m.getMeetingTimeL2()!=null){
			mostBegin = Math.min(mostBegin, m.getMeetingTimeL2().getTime());
		}
		
		if(m.getMeetingTimeL3()!=null){
			mostBegin = Math.min(mostBegin, m.getMeetingTimeL3().getTime());
		}
		
		checkMostBegin = new Date(mostBegin);
		checkMostBegin.setHours(12);
		checkMostBegin.setMinutes(0);
		
		String dept = d.getDept();
		Date buhuiDate = null;
		switch(dept){
		case "一部":
			buhuiDate = m.getMeetingTimeB1();
			break;
		case "二部":
			buhuiDate = m.getMeetingTimeB2();
			break;
		case "三部":
			buhuiDate = m.getMeetingTimeB3();
			break;
		}
		
		if (buhuiDate==null) {
			Calendar bh = Calendar.getInstance();
			bh.setTime(checkBegin);
			bh.add(Calendar.MONTH, 1);
			buhuiDate=bh.getTime();
		}
		
		if(org.apache.commons.lang3.StringUtils.contains(checkClass, "补会")||
				org.apache.commons.lang3.StringUtils.contains(checkClass, "收卡")||
				org.apache.commons.lang3.StringUtils.contains(checkClass, "特殊情况")){
			//该种情况下不需要验证时间
			
		}else if(!meetingCheck.isBuhui()){
			Date checkEnd = new Date(checkTime.getTime());
			checkEnd.setHours(17);
			checkEnd.setMinutes(30);
			
			if(checkMostBegin.after(checkTime)||checkTime.after(buhuiDate)||checkTime.after(checkEnd)){	
//				System.out.println(checkMostBegin);
//				System.out.println(checkBegin);
//				System.out.println(checkTime);
//				System.out.println(buhuiDate);
//				System.out.println(checkEnd);
//				System.out.println(checkMostBegin.after(checkTime));
//				System.out.println(checkBegin.after(checkTime));
//				System.out.println(checkTime.after(buhuiDate));
//				System.out.println(checkTime.after(checkEnd));
				errorMsg = "该驾驶员不在签到时限内。";
				return ERROR;
			}else{
				if(checkBegin.after(checkTime)){
					checkClass="未按规定日期参加例会";
				}

				if(checkTime.after(checkEndDate)&&checkClass.equals("正常")){
					checkClass="未按规定日期参加例会";
					
				}else if(checkClass.equals("正常")){
					Date time_span = new Date(checkTime.getTime());
					time_span.setHours(13);
					time_span.setMinutes(5);
					
					Date time_span_end = new Date(checkTime.getTime());
					time_span_end.setHours(14);
					time_span_end.setMinutes(0);
					
					if(checkTime.after(time_span) && time_span.before(time_span_end)){
						checkClass="迟到";
					}
					
					time_span.setHours(14);
					time_span.setMinutes(35);
					time_span_end.setHours(15);
					time_span_end.setMinutes(30);
					
					if(checkTime.after(time_span) && time_span.before(time_span_end)){
						checkClass="迟到";
					}
					
					time_span.setHours(16);
					time_span.setMinutes(5);
					time_span_end.setHours(17);
					time_span_end.setMinutes(30);
					
					if(checkTime.after(time_span) && time_span.before(time_span_end)){
						checkClass="迟到";
					}
				}
				
			}
		}else{
			buhuiDate.setHours(23);
			buhuiDate.setMinutes(59);
			buhuiDate.setMinutes(59);
			
			if(buhuiDate.after(checkTime)){
				errorMsg = "该驾驶员不在签到时限内。";
				return ERROR;
			}
			
			checkClass="补会";
		}
		
		meetingCheck.setIsChecked(true);
		
		meetingCheck.setCheckClass(checkClass);
		meetingCheck.setManmalCheckTime(new Date());
		
		User user = (User) session.getAttribute("user");
		
		meetingCheck.setManmalCheckPerson(user.getUid());
		
		/*Date time_span = new Date(checkTime.getTime());
		time_span.setHours(13);
		time_span.setMinutes(5);
		
		Date time_span_end = new Date(checkTime.getTime());
		time_span_end.setHours(14);
		time_span_end.setMinutes(0);
		
		if(checkTime.after(time_span) && time_span.before(time_span_end)){
			meetingCheck.setNeedCheckTime(new Timestamp(time_span.getTime()));
		}
		
		time_span.setHours(14);
		time_span.setMinutes(35);
		time_span_end.setHours(15);
		time_span_end.setMinutes(30);
		
		if(checkTime.after(time_span) && time_span.before(time_span_end)){
			meetingCheck.setNeedCheckTime(new Timestamp(time_span.getTime()));
		}
		
		time_span.setHours(16);
		time_span.setMinutes(5);
		time_span_end.setHours(17);
		time_span_end.setMinutes(30);
		
		if(checkTime.after(time_span) && time_span.before(time_span_end)){
			meetingCheck.setNeedCheckTime(new Timestamp(time_span.getTime()));
		}*/
		
		meetingService.updateMeetingCheck(meetingCheck);
		return SUCCESS;
	}
	
	public void clearCheck() throws IOException{
		MeetingCheck check = meetingService.selectMeetingCheck(meetingId, idNum);
		if(check!=null){
			check.setIsChecked(false);
			check.setCheckTime(null);
			check.setCheckMethod(null);
			check.setManmalCheckPerson(null);
			check.setManmalCheckTime(null);
			check.setCheckClass(null);
			ObjectAccess.saveOrUpdate(check);
		}
		JSONObject json = new JSONObject();
		json.put("result", true);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	private File fileCheck;
	private String fileCheckFileName;
	private String fileCheckContentType;
	
	private List<MeetingCheckTemp> importedList;
	
	@SuppressWarnings("unchecked")
	public String checkByFile() throws ParseException{
		InputStream inputXML = new BufferedInputStream(this.getClass().getResourceAsStream("MeetingCheckTemp.xml"));
	    XLSReader mainReader;
		try {
			mainReader = ReaderBuilder.buildFromXML( inputXML );
			InputStream inputXLS = new BufferedInputStream(new FileInputStream(fileCheck));
		    MeetingCheckTemp checkItem = new MeetingCheckTemp();
		    List<MeetingCheckTemp> checkItems = new ArrayList<MeetingCheckTemp>();
		    Map<String, Object> beans = new HashMap<String, Object>();
		    beans.put("checkItem", checkItem);
		    beans.put("checkItems", checkItems);
		    XLSReadStatus readStatus = mainReader.read( inputXLS, beans);
		    
		    if(readStatus.isStatusOK()){
		    	importedList = (List<MeetingCheckTemp>) beans.get("checkItems");
		    }

		} catch (IOException | SAXException | InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorMap=null;
			session.setAttribute("errorMsg", "文件解析失败，文档处于受保护模式，请使用Office将其转为兼容模式后，重新上传。");
			return SUCCESS;
		}
		
		meetingId = meeting.getId();
		method = "指纹机";
		checkClass = "正常";
		
		
		/*Date checkTime;String idNum;*/
		errorMap = new TreeMap<String,String>();
		for(MeetingCheckTemp mct:importedList){
			checkTime =dateFormat.parse(mct.getCheckTime()) ;
			idNum = mct.getIdNum();
			String result = this.m_manmalCheck();
			if(result.equals(ERROR)){
				errorMap.put(idNum, errorMsg);
			}
		}
		session.setAttribute("errorMap", errorMap);
		selectMeetingById();
		return SUCCESS;
	}
	
	private Map<String,String> errorMap;
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public List<Date> getTime() {
		return time;
	}

	public void setTime(List<Date> time) {
		this.time = time;
	}

	public List<String> getDept() {
		return dept;
	}

	public void setDept(List<String> dept) {
		this.dept = dept;
	}

	public List<String> getType() {
		return type;
	}

	public void setType(List<String> type) {
		this.type = type;
	}

	public List<String> getDriverlist() {
		return driverlist;
	}

	public void setDriverlist(List<String> driverlist) {
		this.driverlist = driverlist;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Integer getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public File getFileCheck() {
		return fileCheck;
	}

	public void setFileCheck(File fileCheck) {
		this.fileCheck = fileCheck;
	}

	public String getFileCheckFileName() {
		return fileCheckFileName;
	}

	public void setFileCheckFileName(String fileCheckFileName) {
		this.fileCheckFileName = fileCheckFileName;
	}

	public String getFileCheckContentType() {
		return fileCheckContentType;
	}

	public void setFileCheckContentType(String fileCheckContentType) {
		this.fileCheckContentType = fileCheckContentType;
	}

	public List<MeetingCheckTemp> getImportedList() {
		return importedList;
	}

	public void setImportedList(List<MeetingCheckTemp> importedList) {
		this.importedList = importedList;
	}

	public MeetingService getMeetingService() {
		return meetingService;
	}

	public List<MeetingCheck> getCheckList() {
		return checkList;
	}

	public void setCheckSet(List<MeetingCheck> checkList) {
		this.checkList = checkList;
	}

	public List<String> getFileList() {
		return fileList;
	}

	public void setFileList(List<String> fileList) {
		this.fileList = fileList;
	}

	public String getCheckClass() {
		return checkClass;
	}

	public void setCheckClass(String checkClass) {
		this.checkClass = checkClass;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Map<String, String> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(Map<String, String> errorMap) {
		this.errorMap = errorMap;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getNowPage() {
		return nowPage;
	}

	public void setNowPage(Integer nowPage) {
		this.nowPage = nowPage;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	
}
