<%@taglib uri="/struts-tags" prefix="s" %>
<%@ page language="java" import="java.util.*,com.dz.module.vehicle.*,com.dz.module.user.User" pageEncoding="UTF-8" %>
<%@page import="org.springframework.web.context.support.*" %>
<%@page import="org.springframework.context.*" %>
<%@page import="org.apache.commons.collections.*" %>
<% String path=request.getContextPath(); String basePath=request.getScheme()+ "://"+request.getServerName()+ ":"+request.getServerPort()+path+ "/"; %>

<!DOCTYPE html>
<html lang="zh-cn">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0, maximum-scale=1.2, user-scalable=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="renderer" content="webkit" />
<title> 例会详情 </title> 
<link rel="stylesheet" href="/DZOMS/res/css/pintuer.css">
<link rel="stylesheet" href="/DZOMS/res/css/admin.css">
<link rel="stylesheet" type="text/css" href="/DZOMS/res/css/jquery.datetimepicker.css" />
<script src="/DZOMS/res/js/jquery.js"> </script> 
<script src="/DZOMS/res/js/pintuer.js"> </script> 
<script src="/DZOMS/res/js/respond.js"> </script> 
<script type="text/javascript" src="/DZOMS/res/js/itemtool.js"></script> 
<script src="/DZOMS/res/js/admin.js"> </script> 
<script>

var $tableHead=$('<tr><th style="width:5%;">选择</th>' +
	'<th style="width:15%;">车牌号</th>' +
	'<th style="width:10%;">驾驶员</th>' +
	'<th style="width:25%;">身份证号</th>' +
	'<th style="width:5%;">性别</th>' +
	'<th style="width:10%;">属性</th>' +
	'<th style="width:15%;">车队名称</th>' +
	'<th style="width:15%;">分公司归属</th>' +
	'<tr>');

$(document).ready(function() {
	$("#search_but").click(function() {
		$.post("/DZOMS/driver/searchDriverToHtml", {
			"driver.dept": $("#department").val(),
			"driver.team": $("#driverTeam").val(),
			"driver.isInCar": "true"
		}, function(html) {
			$("#table1").html("").append($tableHead).append(html);
		});
	});

});
</script>

<style>
	.label {
		width: 80px;
		text-align:right;
	}
	.form-group {
		width: 300px;
		margin-top: 5px;
	}
	.changecolor {
		background-color: #0099CC;
	}
							
</style>
</head>

<body>
	
<div class="margin-big-bottom">
	<div class="adminmin-bread" style="width: 100%;">
		<ul class="bread text-main" style="font-size: larger;"> 
                <li>驾驶员</li>
                <li>例会</li>
                <li>例会详情</li>
        </ul>
        </div>
</div>
	<div class="line">
		<form method="post" action="addMeeting" name="addMeeting" class="form-inline form-tips" style="width: 100%;">
		<div class="panel  margin-small" >
          	<div class="panel-head">
          		例会详情
          		
          	</div>
          	<div class="panel-body">
				<div class="form-group">
					<div class="label">
						<label>例会名称 </label> 
					</div> 
					<div class="field">
						<s:textfield cssClass="input input-auto" size="20" name="meeting.meetingName" />
					</div> 
				</div> 
				<div class="form-group">
					<div class="label">
						<label>录入人 </label> 
					</div> 
					<div class="field">
						<s:textfield cssClass="input" type="text" readonly="readonly" value="%{@com.dz.common.other.ObjectAccess@getObject('com.dz.module.user.User',meeting.registrant).uname}"></s:textfield>
					</div> 
				</div> 
				<div class="form-group">
					<div class="label">
						<label>录入时间 </label> 
					</div> 
					<div class="field">
						<s:textfield cssClass="input" readonly="readonly" name="meeting.registTime" />
					</div> 
				</div>
		<br>
        
			<div style="margin-top: 5px;">
				<div style="width: 600px; ">
					<div class="float-left" style="width: 80px; text-align: right;">
						<strong> 例会内容 </strong> 
					</div> 
					<div class="field">
						<s:textarea cssStyle="width: 500px;" rows="5" class="input" placeholder="多行文本" name="meeting.meetingContext">
						</s:textarea> 
					</div> 
				</div>
			</div>
		<br>
			<div>
				<div style="height: 120px;">
					 <div class="float-left" style="width: 80px; text-align: right;">
				         <strong> 文件 </strong> 
			         </div> 
			    <div>
			 	<s:select list="fileList" size="5" cssStyle="width: 400px;" cssClass="float-left"></s:select>
			     </div> 
				</div>
			   
	        </div>

		<br>
	</div> 
	</div>
	</form>
</div>
<div class="line">
		<div class="panel  margin-small" >
          	<div class="panel-head">
          		例会时间
          		
          	</div>
          	<div class="panel-body">
				<table class="table table-bordered table-hover" id="meeting-time-detail">
					<tr>
						<th> 分公司归属 </th> <th> 类别 </th> <th> 起始时间 </th> <th> 终止时间 </th> <th> 可以签到时间 </th> <th> 可以签到时间终止 </th> <th> 迟到时间 </th> <th> 迟到时间终止 </th> 
					</tr>
					<s:if test="%{meeting.meetingTimeL1!=null}">
					<tr class="bg-yellow-light">
						<td>一部</td>
						<td>例会</td>
						<td><s:date name="meeting.meetingTimeL1" format="yyyy/MM/dd"/> 13:00:00</td>
						<td><s:date name="meeting.meetingTimeL1" format="yyyy/MM/dd"/> 14:00:00</td>
						<td><s:date name="meeting.meetingTimeL1" format="yyyy/MM/dd"/> 12:00:01</td>
						<td><s:date name="meeting.meetingTimeL1" format="yyyy/MM/dd"/> 14:00:00</td>
						<td><s:date name="meeting.meetingTimeL1" format="yyyy/MM/dd"/> 13:05:00</td>
						<td><s:date name="meeting.meetingTimeL1" format="yyyy/MM/dd"/> 14:00:00</td>
					</tr>
					<tr class="bg-yellow-light">
								<td>一部</td>
								<td>例会</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 14:30:00</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 15:30:00</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 14:00:01</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 15:30:00</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 14:35:00</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 15:30:00</td>
							</tr>
							<tr class="bg-yellow-light">
								<td>一部</td>
								<td>例会</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 16:00:00</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 17:00:00</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 15:30:01</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 17:30:00</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 16:05:01</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 17:30:00</td>
							</tr>
						</s:if>
						
					<s:if test="%{meeting.meetingTimeL1!=null}">
					<tr class="bg-blue-light">
						<td>二部</td>
						<td>例会</td>
						<td><s:date name="meeting.meetingTimeL1" format="yyyy/MM/dd"/> 13:00:00</td>
						<td><s:date name="meeting.meetingTimeL1" format="yyyy/MM/dd"/> 14:00:00</td>
						<td><s:date name="meeting.meetingTimeL1" format="yyyy/MM/dd"/> 12:00:01</td>
						<td><s:date name="meeting.meetingTimeL1" format="yyyy/MM/dd"/> 14:00:00</td>
						<td><s:date name="meeting.meetingTimeL1" format="yyyy/MM/dd"/> 13:05:00</td>
						<td><s:date name="meeting.meetingTimeL1" format="yyyy/MM/dd"/> 14:00:00</td>
					</tr>
					<tr class="bg-blue-light">
								<td>二部</td>
								<td>例会</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 14:30:00</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 15:30:00</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 14:00:01</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 15:30:00</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 14:35:00</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 15:30:00</td>
							</tr>
							<tr class="bg-blue-light">
								<td>二部</td>
								<td>例会</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 16:00:00</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 17:00:00</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 15:30:01</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 17:30:00</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 16:05:01</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 17:30:00</td>
							</tr>
						</s:if>
						
				<s:if test="%{meeting.meetingTimeL1!=null}">
					<tr class="bg-green-light">
						<td>三部</td>
						<td>例会</td>
						<td><s:date name="meeting.meetingTimeL1" format="yyyy/MM/dd"/> 13:00:00</td>
						<td><s:date name="meeting.meetingTimeL1" format="yyyy/MM/dd"/> 14:00:00</td>
						<td><s:date name="meeting.meetingTimeL1" format="yyyy/MM/dd"/> 12:00:01</td>
						<td><s:date name="meeting.meetingTimeL1" format="yyyy/MM/dd"/> 14:00:00</td>
						<td><s:date name="meeting.meetingTimeL1" format="yyyy/MM/dd"/> 13:05:00</td>
						<td><s:date name="meeting.meetingTimeL1" format="yyyy/MM/dd"/> 14:00:00</td>
					</tr>
					<tr class="bg-green-light">
								<td>三部</td>
								<td>例会</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 14:30:00</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 15:30:00</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 14:00:01</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 15:30:00</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 14:35:00</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 15:30:00</td>
							</tr>
							<tr class="bg-green-light"> 
								<td>三部</td>
								<td>例会</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 16:00:00</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 17:00:00</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 15:30:01</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 17:30:00</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 16:05:01</td>
								<td><s:date name="meeting.meetingTimeL1"
										format="yyyy/MM/dd" /> 17:30:00</td>
							</tr>
						</s:if>
						<s:if test="%{meeting.meetingTimeB1!=null}">
	<tr>
		<td>一部</td>
		<td>补会</td>
		<td><s:date name="meeting.meetingTimeB1" format="yyyy/MM/dd"/> 00:00:00</td>
		<td><s:date name="meeting.meetingTimeB1" format="yyyy/MM/dd"/> 00:00:00</td>
		<td><s:date name="meeting.meetingTimeB1" format="yyyy/MM/dd"/> 00:00:00</td>
		<td><s:date name="meeting.meetingTimeB1" format="yyyy/MM/dd"/> 00:00:00</td>
		<td><s:date name="meeting.meetingTimeB1" format="yyyy/MM/dd"/> 00:00:00</td>
		<td><s:date name="meeting.meetingTimeB1" format="yyyy/MM/dd"/> 00:00:00</td>
	</tr>
</s:if>
<s:if test="%{meeting.meetingTimeB2!=null}">
	<tr>
		<td>二部</td>
		<td>补会</td>
		<td><s:date name="meeting.meetingTimeB2" format="yyyy/MM/dd"/> 00:00:00</td>
		<td><s:date name="meeting.meetingTimeB2" format="yyyy/MM/dd"/> 00:00:00</td>
		<td><s:date name="meeting.meetingTimeB2" format="yyyy/MM/dd"/> 00:00:00</td>
		<td><s:date name="meeting.meetingTimeB2" format="yyyy/MM/dd"/> 00:00:00</td>
		<td><s:date name="meeting.meetingTimeB2" format="yyyy/MM/dd"/> 00:00:00</td>
		<td><s:date name="meeting.meetingTimeB2" format="yyyy/MM/dd"/> 00:00:00</td>
	</tr>
</s:if>
<s:if test="%{meeting.meetingTimeB3!=null}">
	<tr>
		<td>三部</td>
		<td>补会</td>
		<td><s:date name="meeting.meetingTimeB3" format="yyyy/MM/dd"/> 00:00:00</td>
		<td><s:date name="meeting.meetingTimeB3" format="yyyy/MM/dd"/> 00:00:00</td>
		<td><s:date name="meeting.meetingTimeB3" format="yyyy/MM/dd"/> 00:00:00</td>
		<td><s:date name="meeting.meetingTimeB3" format="yyyy/MM/dd"/> 00:00:00</td>
		<td><s:date name="meeting.meetingTimeB3" format="yyyy/MM/dd"/> 00:00:00</td>
		<td><s:date name="meeting.meetingTimeB3" format="yyyy/MM/dd"/> 00:00:00</td>
	</tr>
</s:if>
				</table>
			</div> 
	</div>
</div>
<form action="/DZOMS/driver/meeting/precheckMeeting" method="post" id="defform" target="defframe">
	<s:hidden name="meeting.id"></s:hidden>
</form>
<div class="line">
	<iframe src="" width="100%" height="600" name="defframe" id="defframe"></iframe>
	
</div>
		
	 
</body> 
<script src="/DZOMS/res/js/jquery.datetimepicker.js"> </script>
<script type="text/javascript" src="/DZOMS/res/js/DateTimeHelper.js"> </script>

    <script>
    $(document).ready(function() {
      
		$("#defform").submit();
    });
   
    </script>
</html>