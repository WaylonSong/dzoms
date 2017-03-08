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
	<title>审批状态查询</title>
	
	<link rel="stylesheet" href="/DZOMS/res/css/pintuer.css">
	<link rel="stylesheet" href="/DZOMS/res/css/admin.css">
	<link rel="stylesheet" href="/DZOMS/res/css/jquery.datetimepicker.css" />
	
	<script src="/DZOMS/res/js/jquery.js"></script>
	<script src="/DZOMS/res/js/pintuer.js"></script>
	<script src="/DZOMS/res/js/respond.js"></script>
	<script src="/DZOMS/res/js/admin.js"></script>
	<script type="text/javascript" src="/DZOMS/res/js/jquery.datetimepicker.js" ></script>
	
	<link rel="stylesheet" href="/DZOMS/res/css/jquery.bigautocomplete.css" />
	<script type="text/javascript" src="/DZOMS/res/js/jquery.bigautocomplete.js" ></script>
	
	<script>
	function refreshSearch(){
		$("[name='vehicleSele']").submit();
	}
	
		$(document).ready(function(){
			$("[name='vehicleSele']").find("select").change(function(){
				$("[name='vehicleSele']").submit();
			});
						
			$("[name='vehicleSele']").submit();
			
			$("[name='vehicleSele']").find("input").change(function(){
				if($(this).val().trim().length==0)
						$("[name='vehicleSele']").submit();
			});
			
			$("#driver_name").bigAutocomplete({
				url:"/DZOMS/select/driverById",
				callback:refreshSearch
			});
			
			$("#carframe_num").bigAutocomplete({
				url:"/DZOMS/select/vehicleById",
				callback:refreshSearch
			});
			
			$("#license_num").bigAutocomplete({
				url:"/DZOMS/select/vehicleByLicenseNum",
				callback:refreshSearch
			});
			
		});
		
	var beforeSubmit = function(){
			var dept = $('#dept').val();
			
			var dataAlready = $('#dataAlready').val();
			
			var condition = " ";
			var contractCondition = "from Contract where 1=1 ";
			
			if(dept.trim().length>0){
				contractCondition += "and branchFirm='"+dept.trim()+"' ";
			}
			
			if (dataAlready=='true') {
				condition += "and state=8  ";
			} else if(dataAlready=='false'){
				condition += "and state!=8 ";
			}
			
			if ($("#driver_name").val().trim().length>0) {
				contractCondition += "and idNum='"+$("#driver_name").val().trim()+"' ";
			}
			
			if ($("#carframe_num").val().trim().length>0) {
				contractCondition += "and carframeNum='"+$("#carframe_num").val().trim()+"' ";
			}
			
			if ($("#license_num").val().trim().length>0) {
				contractCondition += "and carNum='"+$("#license_num").val().trim()+"' ";
			}
			
			condition+="and contractId in (select id "+contractCondition+") ";
			
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
                <li>审批状态查询</li>
    </ul>
</div>
 
<form id="search_form" action="/DZOMS/common/selectToList" target="result_form" onsubmit="beforeSubmit()" method="post"
      class="definewidth m20" style="width: 100%;" name="vehicleSele">
    <input type="hidden" name="url" value="/vehicle/vehicle_approval_search_result.jsp" />
		<input type="hidden" name="className" value="com.dz.module.vehicle.VehicleApproval"/>
		<input type="hidden" name="condition" />
		<input type="hidden" name="pageSize" value="5" />
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
                        <!--<tr>
                            <td>车辆购入日期</td>
                            <td><input type="text" id="vehicle.in_date" name="vehicle.inDate" class="input datepick" /></td>
                        </tr>
                        <tr>
                            <td>车辆制造日期</td>
                            <td><input type="text" id="vehicle.pd" name="vehicle.pd"  class="input datepick"/></td>

                        </tr>
                          <tr>
                            <td>承租人身份证号</td>
                            <td><input type="text" id="vehicle.driver_id" name="vehicle.driverId" class="input" /></td>
                        </tr>-->
                        <tr>
                            <td style="border-top: 0px;">承租人身份证号</td>
                            <td style="border-top: 0px;"><input type="text" id="driver_name" name="driverName" class="input"/></td>

                       
                            <td style="border-top: 0px;">归属部门</td>
                            <td style="border-top: 0px;"><select name="vehicle.dept" class="input" id="dept">
                            	<option></option>
                            	<option>一部</option>
                            	<option>二部</option>
                            	<option>三部</option>
                            </select></td>
                        
                            <td style="border-top: 0px;">车架号</td>
                            <td style="border-top: 0px;"><input type="text" id="carframe_num" name="vehicle.carframeNum" class="input" /></td>
                        
                            <td style="border-top: 0px;">车牌号</td>
                            <td style="border-top: 0px;"><input type="text" id="license_num" name="vehicle.licenseNum" class="input" /></td>

														<td style="border-top: 0px;">是否已完成</td>
                            <td style="border-top: 0px;">
                            <select id="dataAlready" class="input">
                            	<option value="all"></option>
                            	<option value="true">是</option>
                            	<option value="false">否</option>
                            </select>
                            </td>
                        </tr>
                        <!--<tr>
                            <td>车辆型号</td>
                            <td><input type="text" id="vehicle.car_mode" name="vehicle.carMode" class="input"/></td>
                        </tr>
                        <tr>
                            <td class="tableleft">合格证编号</td>
                            <td><input type="text" id="vehicle.certify_num" name="vehicle.certifyNum" class="input"/></td>
                        </tr>
                        <tr>
                            <td class="tableleft">车牌号</td>
                            <td><input type="text" id="vehicle.license_num" name="vehicle.licenseNum" class="input" /></td>
                        </tr>-->
                    </table>
                </blockquote>
            </div>
        </div>
      </div>
    </div>
  </div>


</form>
<div>
    <iframe name="result_form" width="100%" height="800px" id="result_form" scrolling="no">

    </iframe>

</div>

<script type="text/javascript" src="/DZOMS/res/js/DateTimeHelper.js" ></script>
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
