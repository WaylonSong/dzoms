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
<title>查看投诉信息</title>
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
	
	function lookfile(){
		//alert($("#filename").val());
        if( $("#filename").val()!=undefined){
            window.open($("#filename").val(),"图片预览",'resizable=no,scrollbars=no');
        }
    }
</script>
</head>
<body>
    <div class="adminmin-bread">
        <ul class="bread">
            <li><a href="" class="icon-home"> 开始</a></li>
            <li>投诉详情</li>
        </ul>
    </div>
    
    <form name="confirmComplain" action="/DZOMS/driver/complain/confirmComplain" method="post">
    	<s:hidden name="complain.id"/>
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
                        <s:textfield cssClass="input" id="complainObject_show" value="%{@com.dz.common.tablelist.TableListService@getFathersValueString(complain.complainObject)}"></s:textfield>
                    </td>
                </tr>
                <tr>
                    <td class="tableleft">信息来源</td>
                    <td style="white-space: nowrap">
                        <s:textfield cssClass="input" name="complain.complainFromOut"></s:textfield>
                    </td>
                    <s:hidden id="complainFromIn" name="complain.complainFromIn"></s:hidden>
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
                	<td>相关照片</td>
                	<td colspan="7">
                       <div  style="width: 100%">
           							 <div class="float-left" style="width: 80px; text-align: right;">
           							 	<strong>添加文件:</strong>
           							 </div>
            							<div class="float-left">
                							<select  id="filename" size="5" style="width: 400px;border: 1px solid rgb(221, 221, 221); border-image: none;"  class="float-left">
                								<%@page import="com.opensymphony.xwork2.util.*,com.dz.module.driver.complain.*,java.io.*,com.dz.common.other.*" %>
                								<%
                									ValueStack vs = (ValueStack) request.getAttribute("struts.valueStack");
                									Complain complain = (Complain) vs.findValue("complain");
                									String filepath = System.getProperty("com.dz.root")+"/data/driver/complain/"+complain.getId();
                									
                									File[] files = FileAccessUtil.list(filepath);
                									if(files!=null)
                									for(File file : files){
                								%>
                								<option value="/DZOMS/data/driver/complain/<%=complain.getId()%>/<%=file.getName()%>"><%=file.getName() %></option>
                								<%} %>
                							</select>
                						</div>
                	
            					</div>
            					  <div class="margin-small">
                                   <div class="margin-small">
<!--                                         <a id="add" class="button input-file" href="javascript:addfile();">添加</a>
-->                                    
                                         <a id="look" class="button input-file" href="javascript:lookfile();">查看</a>
                                    
<!--                                         <a  class="button input-file" href="javascript:void(0);" onclick="delefile()">删除</a>
-->                                     </div>
                              </div>
                    </td>
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
                
                <s:if test="%{complain.state!=0}">
                	<tr>
                    <td>确认结果</td>
                    <td colspan="7">
                        <s:textfield rows="5" cssClass="input" value="属实"></s:textfield>
                    </td>
                	</tr>
                	<tr>
                    	<td>确认人</td>
                    	<td>
                    		<s:textfield cssClass="input" value="%{@com.dz.common.other.ObjectAccess@getObject('com.dz.module.user.User',complain.confirmPerson).uname}"/>
                    	</td>
                    	<td colspan="3"></td>
                    	<td>确认时间</td>
                    	<td><s:textfield cssClass="input" name="complain.confirmTime" readonly="readonly" /></td>
                	</tr>
                </s:if>
               
               <s:if test="%{complain.state>1}">
                <tr>
                    <td>落实结果</td>
                    <td colspan="7">
                        <s:textfield cssClass="input" value="%{@com.dz.common.other.ObjectAccess@getObject('com.dz.module.driver.Driver',complain.dealReault).name}"></s:textfield>
                    </td>
                </tr>
                <tr>
                	<td class="tableleft">分值</td>
                    <td><s:textfield cssClass="input" name="complain.grade"/></td>
                </tr>
                <tr>
                    <td>落实负责人</td>
                    <td>
                    	<s:textfield cssClass="input" value="%{@com.dz.common.other.ObjectAccess@getObject('com.dz.module.user.User',complain.dealPerson).uname}"/>
                    </td>

                    <td colspan="3"></td>
                    <td>落实时间</td>
                    <td><s:textfield  cssClass="datepick input" name="complain.dealTime" /></td>
                </tr>
				</s:if>
				<s:if test="%{complain.state>2}">
                <tr>
                    <td>回访记录</td>
                    <td colspan="7">
                        <s:textarea rows="5" cssClass="input" placeholder="多行文本框" name="complain.visitBackResult"></s:textarea>
                    </td>
                    
                </tr>
                <tr>
                    <td>回访负责人</td>
                    <td>
                    	<s:textfield cssClass="input" value="%{@com.dz.common.other.ObjectAccess@getObject('com.dz.module.user.User',complain.visitBackPerson).uname}"/>
                    </td>

                    <td colspan="3"></td>
                    <td>回访时间</td>
                    <td>
                    	<s:textfield  cssClass="datepick input" name="complain.visitBackTime" />
                    </td>
                </tr>
                </s:if>
				<s:if test="%{complain.state>3}">
					<tr>
                    <td>完结负责人</td>
                    <td>
                    	<s:textfield  cssClass="input" value="%{@com.dz.common.other.ObjectAccess@getObject('com.dz.module.user.User',complain.finishPerson).uname}" />
                    </td>

                    <td colspan="3"></td>
                    <td>完结时间</td>
                    <td><s:textfield  cssClass="input" name="complain.finishTime" /></td>
                </tr>
				</s:if>
				<s:if test="%{complain.attachDetail!=null}">
                <tr>
                	<td>补充登记</td>
                	<td><s:textfield  cssClass="input" name="complain.attachDetail" /></td>
                </tr>
               </s:if>
            </table>
        
        
        </div>
    </form>
     <script src="/DZOMS/res/js/DateTimeHelper.js"></script>
</body>
</html>
