<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@taglib
	uri="/struts-tags" prefix="s"%><%@ page language="java"
	import="java.util.*, com.dz.module.vehicle.VehicleApproval,com.dz.module.user.User"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>添加投诉信息</title>
<link rel="stylesheet" href="/DZOMS/res/css/pintuer.css" />
<link rel="stylesheet" href="/DZOMS/res/css/admin.css" />
<link rel="stylesheet" href="/DZOMS/res/css/jquery.datetimepicker.css" />
<script src="/DZOMS/res/js/jquery.js"></script>
<script src="/DZOMS/res/js/pintuer.js"></script>
<script src="/DZOMS/res/js/respond.js"></script>
<script src="/DZOMS/res/js/admin.js"></script>

<script type="text/javascript" src="/DZOMS/res/js/JsonList.js" ></script>
<script type="text/javascript" src="/DZOMS/res/js/TableList.js" ></script>
<script src="/DZOMS/res/js/jquery.datetimepicker.js"></script>
<script>
	$(document).ready(function(){
		var jsonStr = $("#complainFromIn").val();
		var json = $.parseJSON(jsonStr);
		for(var key in json){
			$('input[name="'+key+'"]').val(json[key]);
		}
	});
	
	$(document).ready(function(){
		var complainObject = $("#complainObject").val();
		var json = $.parseJSON(complainObject);
		var str = json["complainObject2"]+":"+json["complain.complainObject"];
		$("#complainObject_show").val(str);
	});
</script>
</head>
<body>
    <div class="adminmin-bread">
        <ul class="bread">
            <li><a href="" class="icon-home"> 开始</a></li>
            <li>投诉修改</li>
        </ul>
    </div>
    
    <form name="confirmComplain" action="/DZOMS/driver/complain/updateComplain" method="post">
    	<s:hidden name="complain.id"/>
    	<s:hidden name="complain.vehicleId"/>
    	<s:hidden name="complain.grade"/>
    	<s:hidden name="complain.state"/>
    	<s:hidden name="complain.registrant"/>
        <div class="container">
            <table class="table table-hover">
                <tr>
                    <td class="tableleft">投诉时间</td>
                    <td><s:textfield cssClass="datetimepicker input" type="text" name="complain.complainTime" /></td>
                    <td class="tableleft">投诉类别</td>
                    <td>
                        <s:textfield cssClass="input" name="complain.complainClass"></s:textfield>
                    </td>
                    <td class="tableleft ">投诉类型</td>
                    <td>
                    	<s:textfield cssClass="input" name="complain.complainType"></s:textfield>
                    </td>
                </tr>
                <tr>
                    <td class="tableleft">投诉项目</td>
                    <td colspan="4">
                    	<s:hidden name="complain.complainObject" id="complainObject"></s:hidden>
                        <s:textfield cssClass="input" id="complainObject_show"></s:textfield>
                    </td>
                </tr>
                <tr>
                    <td class="tableleft">信息来源</td>
                    <td style="white-space: nowrap">
                        <s:textfield cssClass="input" name="complain.complainFromOut"></s:textfield>
                    </td>
                    <td class="tableleft">姓名</td>
                    <td><s:textfield cssClass="input" name="complainFromIn3">
                    </s:textfield></td>
                    <td class="tableleft">电话</td>
                    <td><input class="input" name="complainFromIn2"/></td>
                    <td class="tableleft">手机</td>
                    <td><input class="input" name="complain.complainFromIn"/></td>
                </tr>
                <tr>
                	<s:set name="t_vehicle" value="%{@com.dz.common.other.ObjectAccess@getObject('com.dz.module.vehicle.Vehicle',complain.vehicleId)}"></s:set>
                    <td class="tableleft">车牌号</td>
                    <td>
                        <s:textfield cssClass="input" value="%{#t_vehicle.licenseNum}">
                        </s:textfield>
                    </td>
                    <s:set name="t_driver" value="%{@com.dz.common.other.ObjectAccess@getObject('com.dz.module.driver.Driver',#t_vehicle.driverId)}"></s:set>
                    <td class="tableleft">承租人</td>
                    <td><s:textfield  class="input" name="idNum" value="%{#t_driver.idNum}"/></td>
                    <td class="tableleft">电话</td>
                    <td><s:textfield  class="input" name="telephone" value="%{#t_driver.phoneNum1}"/></td>
                    <td class="tableleft">分公司归属</td>
                    <td><s:textfield  class="input" name="company" value="%{#t_vehicle.dept}"/></td>
                </tr>
                <tr>
                    <td class="tableleft">投诉人姓名</td>
                    <td><s:textfield cssClass="input" name="complain.complainPersonName"/></td>
                    <td class="tableleft">投诉任性别</td>
                    <td>
                        <s:textfield cssClass="input" value="%{complain.complainPersonSex?'男':'女'}">
                        </s:textfield>
                    </td>
                    <td class="tableleft">投诉人电话</td>
                    <td><s:textfield cssClass="input" name="complain.complainPersonPhone"/></td>
                    <td class="tableleft">发票号</td>
                    <td><s:textfield cssClass="input" name="complain.ticketNumber"/></td>
                </tr>
                
                <tr>
                    <td>投诉内容</td>
                    <td colspan="7">
                        <s:textarea rows="5" cssClass="input" placeholder="多行文本框" name="complain.complainContext"></s:textarea>
                    </td>
                </tr>
                <tr>
                    <td>投诉登记人</td>
                    <td><s:textfield cssClass="input" value="%{@com.dz.common.other.ObjectAccess@getObject('com.dz.module.user.User',complain.registrant).uname}"/></td>
                    <td colspan="3"></td>
                    <td>投诉登记时间</td>
                    <td><s:textfield cssClass="input" name="complain.registTime"/></td>
                </tr>
                <tr>
                    <td colspan="6"> <div class="form-button"><button class="button bg-green" type="submit">录入</button></div></td>
                    <td> <div class="form-button"><button class="button">退出</button></div></td>
                </tr>
            </table>
        </div>
    </form>
     <script src="/DZOMS/res/js/DateTimeHelper.js"></script>
</body>
</html>
