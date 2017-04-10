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
	<title>电子违章查询</title>
	
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
	
	$("#search_form").find("input,select").change(function(){
		$("#search_form").submit();
	});
			
	$("#search_form").submit();
});

var beforeSubmit = function(){
			var dept = $('#dept').val();
			var beginDate = $('#beginDate').val();
			var endDate = $('#endDate').val();
			
			var condition = " ";
			
			if(dept.trim().length>0){
				condition += "and carframeNum in (select carframeNum from Vehicle where dept='"+dept.trim()+"') ";
			}
						
			if(beginDate.trim().length>0){
				condition += "and date >= '"+beginDate.trim()+"' ";
			}
			if(endDate.trim().length>0){
				condition += "and date <= '"+endDate.trim()+"' ";
			}
			
			$('[name="condition"]').val(condition);
			return true;
	};
	</script>
  </head>
 <body> 
	<div class="adminmin-bread" style="width: 100%;">
		<ul class="bread text-main" style="font-size: larger;"> 
                <li>车辆管理</li>
                <li>查询</li>
                <li>查询电子违章信息</li>
    </ul>
</div>
 
<form method="post" class="form-inline" id="search_form" action="/DZOMS/common/selectToList" target="result_form" onsubmit="beforeSubmit()">
		<input type="hidden" name="url" value="/vehicle/electric/search_byVehicle_result.jsp" />
		<input type="hidden" name="className" value="com.dz.module.vehicle.electric.ElectricHistory"/>
		<input type="hidden" name="condition" />
   <div class="line">
   		<div class="panel  margin-small" >
          	<div class="panel-head">
          		查询条件
          	</div>
        <div class="panel-body">
        	<div class="line">
        		<div class="xm12 padding">
                <blockquote class="panel-body">
                    <table class="table" style="border: 0px;">
                        <tr>
                            <td style="border-top: 0px;">归属部门</td>
                            <td style="border-top: 0px;">
                            <select id="dept" class="input">
                            	<option></option>
                            	<option>一部</option>
                            	<option>二部</option>
                            	<option>三部</option>
                            </select>
                            </td>
                            <td style="border-top: 0px;">日期范围</td>
                            <td class="time-order"><input class="datepick" id="beginDate" /></td>
                            <td class="time-order">到</td>
                            <td class="time-order"><input class="datepick" id="endDate" /></td>
                        </tr>
                    </table>
                </blockquote>
            </div>
        </div>
      </div>
    </div>
  </div>

<script>
$('.datepick').datetimepicker(
{
      lang:"ch",           //语言选择中文
      format:"Y-m-d H:i:s"
});
</script>
</form>
<div>
    <iframe name="result_form" width="100%" height="800px" id="result_form" scrolling="no">

    </iframe>

</div>

</body>
 <script src="/DZOMS/res/js/apps.js"></script>
    <script>
    	function iFrameHeight() {
	try{
var ifm= document.getElementById("result_form");   
var subWeb = document.frames ? document.frames["result_form"].document : ifm.contentDocument;   
if(ifm != null && subWeb != null) {
   ifm.height = subWeb.body.scrollHeight+200;
}   }catch(e){}
}    

$(document).ready(function(){
	window.setInterval('iFrameHeight();',1000);
});
    $(document).ready(function() {
    	try{
    		 App.init();
    	}catch(e){
    		//TODO handle the exception
    	}
    	
       
        // $(".xdsoft_datetimepicker.xdsoft_noselect").show();
        // $("#ri-li").append($(".xdsoft_datetimepicker.xdsoft_noselect"));

    });
    </script>
</html>
