<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib
	uri="/struts-tags" prefix="s"%>
<%@page import="org.springframework.web.context.support.*"%>
<%@page import="org.springframework.context.*" %>
<%@page import="org.apache.commons.collections.*" %>
<%@ page language="java"
	import="java.util.*,com.dz.module.user.User,com.dz.module.vehicle.*,com.dz.module.driver.*,com.dz.common.other.*,com.dz.common.global.*,com.opensymphony.xwork2.util.*"
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
<script src="/DZOMS/res/js/jquery.datetimepicker.js"></script>
<script src="/DZOMS/res/layer-v2.1/layer/layer.js"></script>
<script src="/DZOMS/res/js/window.js"></script>
<script type="text/javascript" src="/DZOMS/res/js/JsonList.js" ></script>
<script type="text/javascript" src="/DZOMS/res/js/fileUpload.js" ></script>
<script>
	  function refresh(){
        	document.driverBusinessApply.action = "/DZOMS/driver/driverInCar/businessReciveSelect";
        	document.driverBusinessApply.submit();
        }
        
        function refresh2(){
        	if ($('[name="driver.name"]').val().trim().length==0) {
        		return false;
        	}
        	document.driverBusinessApply.action = "/DZOMS/driver/driverInCar/businessReciveSelect2";
        	document.driverBusinessApply.submit();
        }
</script>
	   <style>
	   	.form-group{
	   		width: 400px;
	   	}
        .label{
            width: 140px;
            text-align: right;
        }
    </style>
<body>
<div class="margin-big-bottom">
	<div class="adminmin-bread" style="width: 100%;">
		<ul class="bread text-main" style="font-size: larger;"> 
                <li>驾驶员</li>
                <li>证照办理</li>
                <li>办领</li>
        </ul>
        </div>
</div>
<div class="container">
    <form action="businessRecive" name="driverBusinessApply" method="post"
    	style="width: 100%;" class="form-inline form-tips" >

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
                <s:hidden name="vehicle.carframeNum" value="%{vehicle.carframeNum}"></s:hidden>
                <s:hidden name="driver.carframeNum" value="%{vehicle.carframeNum}"></s:hidden>
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
                    <s:textfield value="%{@com.dz.common.other.ObjectAccess@getObject('com.dz.module.driver.Driver',#driverId).name}" cssClass="input"  readonly="true"/>
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
                		<%--
                		<s:set name="firstDriver" value="%{@com.dz.common.other.ObjectAccess@getObject('com.dz.module.driver.Driver',vehicle.firstDriver)}"></s:set>
                			
                		<s:if test="%{#firstDriver==null||#firstDriver.isInCar}">
                			<s:set name="firstDriverName" value="%{''}"></s:set>
                		</s:if>
                		<s:else>
                			<s:set name="firstDriverName" value="%{#firstDriver.name==null?'':#firstDriver.name}"></s:set>
                		</s:else>
                		
                		
                		<s:set name="secondDriver" value="%{@com.dz.common.other.ObjectAccess@getObject('com.dz.module.driver.Driver',vehicle.secondDriver)}"></s:set>
                			
                		<s:if test="%{#secondDriver==null||#secondDriver.isInCar}">
                			<s:set name="secondDriverName" value="%{''}"></s:set>
                		</s:if>
                		<s:else>
                			<s:set name="secondDriverName" value="%{#secondDriver.name==null?'':#secondDriver.name}"></s:set>
                		</s:else>
                		--%>
<%
ValueStack vs = (ValueStack) request.getAttribute("struts.valueStack"); 
Vehicle vehicle = (Vehicle)vs.findValue("vehicle");
String hql = "carframeNum='"+vehicle.getCarframeNum()+"' and finished=false and operation='证照申请'";      		
List<Driverincar> list = ObjectAccess.query(Driverincar.class, hql);

List<String> names = (List<String>)CollectionUtils.collect(list, new Transformer(){
  						@Override
  						public Object transform(Object obj) {
               				Driverincar vm = (Driverincar) obj;
               				Driver d = ObjectAccess.getObject(Driver.class, vm.getIdNumber());
                			return d.getName();
           				}
  					});
  					
  					names.add(0, "");
  	
request.setAttribute("names",names);
%>
                		<s:select cssClass="input" list="%{#request.names}" name="driver.name" onchange="refresh2()"></s:select>
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
                        <s:textfield cssClass="input"  name="driver.idNum"  readonly="true"/>
                    </div>
                </div>
                <br/>

                <div class="form-group">
                    <div class="label padding" >
                        <label>
                            申请时间
                        </label>
                    </div>
                    <div class="field">
                        <s:textfield cssClass="input"  name="driver.businessApplyTime"  readonly="true"></s:textfield>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label padding" >
                        <label>
                            发证时间
                        </label>
                    </div>
                    <div class="field">
                        <s:textfield cssClass="input"  name="driver.businessReciveTime" readonly="readonly"></s:textfield>
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
                    	<s:textfield cssClass="input"  name="driver.businessApplyDriverClass" readonly="readonly"></s:textfield>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label padding" >
                        <label>
                            作息时间
                        </label>
                    </div>
                    <div class="field">
                    	<s:textfield cssClass="input"  name="driver.restTime" readonly="readonly"></s:textfield>
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
                        <input type="hidden" name="driver.businessReciveRegistrant" value="<%=((User)session.getAttribute("user")).getUid()%>"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label padding" >
                        <label>
                            登记时间
                        </label>
                    </div>
                    <div class="field">
                        <input  class="input" readonly="readonly" name="driver.businessReciveRegistTime" value="<%=(new  java.text.SimpleDateFormat("yyyy/MM/dd")).format(new java.util.Date()) %>"/>
                       
                    </div>
                </div>
            </div>
             <div class="form-group">
            	 <div class="label padding" >
                        <label>
                        </label>
                    </div>
            	<div class="field">
            		
            	</div>
             </div>
            <div class="form-group">
            	 <div class="label padding" >
                        <label>
                        </label>
                    </div>
            	<div class="field">
            		 <input type="button" class="button bg-green submitbutton margin-big-left" value="提交">
            	</div>
             </div>
        </div>
    </form>
    <script>
    
function beforeSubmit(){
	var rawFirstDriver='<s:property value="%{vehicle.firstDriver}"/>';
	var rawSecondDriver='<s:property value="%{vehicle.secondDriver}"/>';
	var rawThirdDriver='<s:property value="%{vehicle.thirdDriver}"/>';
	var rawTempDriver='<s:property value="%{vehicle.tempDriver}"/>';
	var newIdNum='<s:property value="%{driver.idNum}"/>';
	
	var rawIdNum='';
	var driverClass=$('[name="driver.businessApplyDriverClass"]').val();
	
	if(newIdNum.length==0){
		alert("您的数据录入不全!!!");
		return;
	}
	
	if(driverClass.length==0){
		alert("请选择驾次！");
		return;
	}
	
	switch (driverClass){
		case "主驾":
			rawIdNum=rawFirstDriver;
			break;
		case "副驾":
			rawIdNum=rawSecondDriver;
			break;
		case "三驾":
			rawIdNum=rawThirdDriver;
			break;
		default:
			break;
	}
	
	if(rawIdNum.length==0){
		driverReady++;
	}else{
		var condition2 = " from Driver where idNum ='" + rawIdNum +"'";
	$.post("/DZOMS/common/doit",{"condition":condition},function(result){
		if (result!=undefined&&result["affect"]!=undefined) {
			var driver = result["affect"];
			if(driver["businessApplyCancelState"]==1){
				
				alert("该车原驾驶员"+driver["name"]+"存在未办理的证照注销事务，请先进行办理！");
				driverReady=0;
			}else{
				driverReady++;
				checkDriverReady();
			}
		}
	});
	}
	
	var condition = " from Driver where idNum ='" + newIdNum +"'";
	$.post("/DZOMS/common/doit",{"condition":condition},function(result){
		if (result!=undefined&&result["affect"]!=undefined) {
			var driver = result["affect"];
			if(driver["businessApplyCancelState"]==1){
				alert("该驾驶员存在未办理的证照注销事务，请先进行办理！");
				driverReady=0;
			}else{
				driverReady++;
				checkDriverReady();
			}
			
		}
	});

}

function checkDriverReady(){
	if (driverReady==2) {
		document.driverBusinessApply.submit();
	}
}

button_bind(".submitbutton","确定提交?","beforeSubmit();");

$(function(){
	$showdialogs=function(e){
		var trigger=e.attr("data-toggle");
		var getid=e.attr("data-target");
		var data=e.attr("data-url");
		var mask=e.attr("data-mask");
		var width=e.attr("data-width");
		var detail="";
		var masklayout=$('<div class="dialog-mask"></div>');
		if(width==null){width="80%";}
		
		if (mask=="1"){
			$("body").append(masklayout);
		}
		detail='<div class="dialog-win" style="position:fixed;width:'+width+';z-index:11;">';
		if(getid!=null){detail=detail+$(getid).html();}
		if(data!=null){detail=detail+$.ajax({url:data,async:false}).responseText;}
		detail=detail+'</div>';
		
		var win=$(detail);
		win.find(".dialog").addClass("open");
		$("body").append(win);
		
		/**
		 * Show next to selector
		 */
		var e_top = e.offset().top-win.outerHeight();
		
		var x=parseInt($(window).width()-win.outerWidth())/2;
		//var y=parseInt($(window).height()-win.outerHeight())/2;
		var y = e_top;
		if (y<=10){y="10"}
		win.css({"left":x,"top":y});
		win.find(".dialog-close,.close").each(function(){
			$(this).click(function(){
				win.remove();
				$('.dialog-mask').remove();
			});
		});
		masklayout.click(function(){
			win.remove();
			$(this).remove();
		});
	};
});

    var driverReady=0;
    
</script>
<script type="text/javascript" src="/DZOMS/res/js/DateTimeHelper.js" ></script>
</div>
</body>
</html>