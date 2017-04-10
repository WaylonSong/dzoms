<%@page import="com.dz.common.other.ObjectAccess"%>
<%@page import="com.dz.module.vehicle.Vehicle"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<meta name="renderer" content="webkit">
	<title>同步记录查询</title>
	
	<link rel="stylesheet" href="/DZOMS/res/css/pintuer.css">
	<link rel="stylesheet" href="/DZOMS/res/css/admin.css">
	<link rel="stylesheet" href="/DZOMS/res/css/jquery.datetimepicker.css" />
	
	<script src="/DZOMS/res/js/jquery.js"></script>
	<script src="/DZOMS/res/js/pintuer.js"></script>
	<script src="/DZOMS/res/js/respond.js"></script>
	<script src="/DZOMS/res/js/admin.js"></script>
	<script type="text/javascript" src="/DZOMS/res/js/jquery.datetimepicker.js" ></script>
	
	<script>
$(document).ready(function(){
	$("#search_form").submit();
});

var beforeSubmit = function(){
			condition = " order by synTime desc ";
			
			$('[name="condition"]').val(condition);
			return true;
	};
	
function _synVehicle(){
   $("#synVehicle").submit();
}
        
function _synHistory(){
   $("#synHistory").submit();
}
	</script>
  </head>
 <body> 
	<div class="adminmin-bread" style="width: 100%;">
		<ul class="bread text-main" style="font-size: larger;"> 
        <li>车辆管理</li>
        <li>查询</li>
        <li>查询电子违章同步信息</li>
    </ul>
</div>
 
<form method="post" class="form-inline" id="search_form" action="/DZOMS/common/selectToList" target="result_form" onsubmit="beforeSubmit()">
		<input type="hidden" name="url" value="/vehicle/electric/syn_search_result.jsp" />
		<input type="hidden" name="className" value="com.dz.module.vehicle.electric.Synrecord"/>
		<input type="hidden" name="condition" />
</form>

<form method="post" id="synVehicle" action="/vehicle/synVehicle.action"></form>
<form method="post" id="synHistory" action="/vehicle/synHistory.action"></form>

<div>
	<div class="button-toolbar">
	  <div class="button-group">
	    <button onclick="_synVehicle()" type="button" class="button icon-pencil text-green" style="line-height: 6px;">
			   同步车辆信息</button>
			<button onclick="_synHistory()" type="button" class="button icon-pencil text-green" style="line-height: 6px;">
			   同步违章信息</button>                                                 
    </div>
  </div>
  
    <iframe name="result_form" width="100%" height="800px" id="result_form" scrolling="no">

    </iframe>
</div>

</body>
 <script src="/DZOMS/res/js/apps.js"></script>
</html>
