<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib
	uri="/struts-tags" prefix="s"%>
<%@page import="org.springframework.web.context.support.*"%>
<%@page import="org.springframework.context.*" %>
<%@page import="org.apache.commons.collections.*" %>
<%@ page language="java"
	import="java.util.*,com.dz.module.user.User,com.dz.module.vehicle.*,com.dz.module.driver.*,com.dz.common.other.*,com.dz.common.global.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
		<title>证照办理</title>
		<link rel="stylesheet" href="/DZOMS/res/css/pintuer.css">
<link rel="stylesheet" href="/DZOMS/res/css/admin.css">
<script src="/DZOMS/res/js/jquery.js"></script>
<script src="/DZOMS/res/js/pintuer.js"></script>
<script src="/DZOMS/res/js/respond.js"></script>
<script src="/DZOMS/res/js/admin.js"></script>
<script src="/DZOMS/res/js/itemtool.js"></script>
<link rel="stylesheet" href="/DZOMS/res/css/jquery.datetimepicker.css" />
<script src="/DZOMS/res/layer-v2.1/layer/layer.js"></script>
<script src="/DZOMS/res/js/window.js"></script>
<script src="/DZOMS/res/js/jquery.datetimepicker.js"></script>
<script>
	  function refresh(){
        	document.driverBusinessApply.action = "/DZOMS/driver/driverInCar/businessReciveCancelSelect";
        	document.driverBusinessApply.submit();
        }
        
        function refresh2(){
        	if ($('[name="driver.name"]').val().trim().length==0) {
        		return false;
        	}
        	document.driverBusinessApply.action = "/DZOMS/driver/driverInCar/businessReciveCancelSelect2";
        	document.driverBusinessApply.submit();
        }
</script>

	   <style>
	   	.form-group{
	   		width: 400px;
	   	}
        .label{
            width: 120px;
            text-align: right;
        }
    </style>
<body>
<div class="margin-big-bottom">
	<div class="adminmin-bread" style="width: 100%;">
		<ul class="bread text-main" style="font-size: larger;"> 
                <li>驾驶员</li>
                <li>证照办理</li>
                <li>注销</li>
        </ul>
        </div>
</div>
<div class="container">
    <form action="businessReciveCancel" name="driverBusinessApply" method="post"
    	style="width: 100%;" class="form-inline form-tips">

        <div class="panel">
            <div class="panel-head">
                <strong>证照信息</strong>
            </div>
            <div class="panel-body">
            <div class="form-group">
                <div class="label padding">
                    <label>
                        车牌号
                    </label>
                </div>
                <s:hidden name="driver.carframeNum" value="%{vehicle.carframeNum}"></s:hidden>
                <s:hidden name="vehicle.carframeNum" value="%{vehicle.carframeNum}"></s:hidden>
                <div class="field" >
                <s:if test="%{vehicle.licenseNum!=null}">
                	<s:textfield name="vehicle.licenseNum" cssClass="input"/>
                </s:if>
                <s:else>
                <%! List<String> vmstr; %>
				<%  
					ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());    
  					VehicleService vms = ctx.getBean(VehicleService.class);
  					
  					List<Vehicle> vml = vms.selectAll();
  					
					vmstr = (List<String>)CollectionUtils.collect(vml, new Transformer(){
  						@Override
  						public Object transform(Object obj) {
               				Vehicle vm = (Vehicle) obj;
                			return vm.getLicenseNum();
           				}
  					});
  					
  					vmstr.add(0, "");
  					
  					request.setAttribute("vmstr",vmstr);
  				%>
                	<s:select list="#request.vmstr" cssClass="input" name="vehicle.licenseNum" onchange="refresh()"></s:select>
                </s:else>
                </div>
            </div>
            <div class="form-group">
                <div class="label padding">
                    <label>
                        承租人
                    </label>
                </div>
                <div class="field" >
                   <s:set name="driverId" value="%{@com.dz.common.other.ObjectAccess@getObject('com.dz.module.vehicle.Vehicle',vehicle.carframeNum).driverId}"></s:set>
                    <s:textfield value="%{@com.dz.common.other.ObjectAccess@getObject('com.dz.module.driver.Driver',#driverId).name}" cssClass="input"/>
                </div>
            </div>
            <br>
            <div class="form-group">
                <div class="label padding">
                    <label>
                        驾驶员姓名
                    </label>
                </div>
                <div class="field" >
                	<s:if test="%{vehicle.licenseNum!=null}">
                		<s:if test="%{driver.name==null||driver.name==''}">
                		
                		<s:set name="firstDriver" value="%{@com.dz.common.other.ObjectAccess@getObject('com.dz.module.driver.Driver',vehicle.firstDriver)}"></s:set>
                			
                		<s:if test="%{#firstDriver==null||!#firstDriver.isInCar||#firstDriver.businessApplyCancelTime==null}">
                			<s:set name="firstDriverName" value="%{''}"></s:set>
                		</s:if>
                		<s:else>
                			<s:set name="firstDriverName" value="%{#firstDriver.name==null?'':#firstDriver.name}"></s:set>
                		</s:else>
                		
                		
                		<s:set name="secondDriver" value="%{@com.dz.common.other.ObjectAccess@getObject('com.dz.module.driver.Driver',vehicle.secondDriver)}"></s:set>
                			
                		<s:if test="%{#secondDriver==null||!#secondDriver.isInCar||#secondDriver.businessApplyCancelTime==null}">
                			<s:set name="secondDriverName" value="%{''}"></s:set>
                		</s:if>
                		<s:else>
                			<s:set name="secondDriverName" value="%{#secondDriver.name==null?'':#secondDriver.name}"></s:set>
                		</s:else>
                		
                		<s:select cssClass="input" list="{'',#firstDriverName,#secondDriverName}" name="driver.name" onchange="refresh2()"></s:select>
                		</s:if>
                		<s:else>
                			<s:textfield cssClass="input"  name="driver.name" />
                		</s:else>
                	</s:if>
                	<s:else>
                		<s:textfield cssClass="input"  name="driver.name" />
                	</s:else>
                </div>
            </div>
            
           
                <div class="form-group">
                    <div class="label padding">
                        <label>
                            身份证号
                        </label>
                    </div>
                    <div class="field">
                        <s:textfield cssClass="input"  name="driver.idNum" />
                    </div>
                </div>
                <br/>

                
                
                <div class="form-group">
                    <div class="label padding" >
                        <label>
                            申请注销时间
                        </label>
                    </div>
                    <div class="field">
                        <s:textfield cssClass="input"  name="driver.businessApplyCancelTime" readonly="readonly"></s:textfield>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label padding" >
                        <label>
                            发证时间
                        </label>
                    </div>
                    <div class="field">
                        <s:textfield cssClass="input"  name="driver.applyTime" readonly="readonly"></s:textfield>
                    </div>
                </div>
                <br/>
                <div class="form-group">
                    <div class="label padding" >
                        <label>
                            驾驶员属性
                        </label>
                    </div>
                    <div class="field">
                        <s:select cssClass="input"  name="applyAttribute"
                                  list="@com.dz.common.other.JsonListReader@getList('driver/driver.json','driver.attribute')"></s:select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label padding" >
                        <label>
                            作息时间
                        </label>
                    </div>
                    <div class="field">
                        <s:select cssClass="input"  name="driver.restTime"
                                  list="@com.dz.common.other.JsonListReader@getList('driver/driver.json','driver.restTime')"></s:select>
                    </div>
                </div>
                <br>
                <div class="form-group">
                    <div class="label padding" >
                        <label>
                            录入人
                        </label>
                    </div>
                    <div class="field">
                        <input class="input" type="text" readonly="readonly" value="<%=((User)session.getAttribute("user")).getUname()%>">
                        <input type="hidden" name="driver.businessApplyCancelRegistrant" value="<%=((User)session.getAttribute("user")).getUid()%>"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label padding" >
                        <label>
                            登记时间
                        </label>
                    </div>
                    <div class="field">
                        <input  class="datepick input" readonly="readonly" name="driver.businessReciveCancelRegistTime" value="<%=(new  java.text.SimpleDateFormat("yyyy/MM/dd")).format(new java.util.Date()) %>"/>
                    </div>
                </div>
            </div>
              <div class="form-group">
                <div class="label padding" >
                </div>
                <div class="field">  
                </div>
            </div>
            <div class="form-group">
                <div class="label padding" >
                </div>
                <div class="field">
                    <input type="button" class="button bg-green submitbutton margin-big-left" value="提交">
                </div>
            </div>
        </div>
    </form>
    <script>
    add_but_bind('.submitbutton',function(){
    	driverBusinessApply.submit();
    });
    </script>
</div>
</body>
</html>
