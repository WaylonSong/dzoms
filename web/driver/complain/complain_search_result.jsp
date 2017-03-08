<%@page import="com.dz.common.other.ObjectAccess"%>
<%@page import="com.dz.module.driver.Driver"%>
<%@page import="com.dz.common.global.Page"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Page pg = (Page)request.getAttribute("page");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<meta name="renderer" content="webkit">
	<title>查询结果</title>
	
	<link rel="stylesheet" href="/DZOMS/res/css/pintuer.css">
	<link rel="stylesheet" href="/DZOMS/res/css/admin.css">
	<script src="/DZOMS/res/js/jquery.js"></script>
	<script src="/DZOMS/res/js/pintuer.js"></script>
	<script src="/DZOMS/res/js/respond.js"></script>
	<script src="/DZOMS/res/js/admin.js"></script>
	<script type="text/javascript" src="/DZOMS/res/layer-v2.1/layer/layer.js" ></script>
	<script type="text/javascript" src="/DZOMS/res/js/window.js" ></script>
<script>
	$(document).ready(function(){
		$("#show_div").find("input").change(resetClass);
	});
	
	function resetClass(){
		$(".selected_able").hide();
		var selects = [];
		$("input[name='sbx']:checked").each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数
      selects.push($(this).val());//将选中的值添加到数组chk_value中    
    });
	//	alert(selects);
		
		for(var i = 0;i<selects.length;i++){
			$("."+selects[i]).show();
		}
	}
	
	function _detail(){
		var selected_val = $("input[name='cbx']:checked").val();
		if(selected_val==undefined){
			alert('您没有选择任何一条数据');
			return;
		}
		
		var url = "/DZOMS/driver/complain/complainPreshow?complain.id="+selected_val;
		window.open(url,"投诉明细",'width=800,height=600,resizable=yes,scrollbars=yes');
	}
	
	function _update(){
		var selected_val = $("input[name='cbx']:checked").val();
		if(selected_val==undefined){
			alert('您没有选择任何一条数据');
			return;
		}
		
		var state = $("input[name='cbx']:checked").parents("tr").attr("state");
		
		if (state!=0) {
			alert('该数据已经过确认，不可修改。');
			return;
		}
		
		var url = "/DZOMS/driver/complain/complainPreupdate?complain.id="+selected_val;
		
		window.parent.location = url;
		//window.open(url,"投诉修改",'width=800,height=600,resizable=yes,scrollbars=yes');
	}
	
	function _attach(){
		var selected_val = $("input[name='cbx']:checked").val();
		if(selected_val==undefined){
			alert('您没有选择任何一条数据');
			return;
		}
		
		var state = $("input[name='cbx']:checked").parents("tr").attr("state");
		if (state!=4) {
			alert('该项投诉未完结，不可补充登记。');
			return;
		}
		
		var url = "/DZOMS/driver/complain/complainPreattach?complain.id="+selected_val;
		
		window.parent.location = url;
	}
	
	function _toExcel(){
		var path = $("[name='vehicleSele']").attr("action");
		var target = $("[name='vehicleSele']").attr("target");
		
		var url = "/DZOMS/driver/driverToExcel";
		$("[name='vehicleSele']").attr("action",url).attr("target","_blank").submit();
	}
	
	function _toPrint(){
		var selected_val = $("input[name='cbx']:checked").val();
		if(selected_val==undefined)
		alert('您没有选择任何一条数据');
		var url = "/DZOMS/driver/driverPreprint?driver.idNum="+selected_val;
		window.open(url,"驾驶员打印",'width=800,height=600,resizable=yes,scrollbars=yes');
	}
	
		function toBeforePage(){
		var currentPage = parseInt($("input[name='currentPage']").val());
		if(currentPage==1){
			alert("没有上一页了。");
			return ;
		}
		$("input[name='currentPage']").val($("input[name='currentPage']").val()-1);
		document.vehicleSele.submit();
	}
	
	function toNextPage(){
		var currentPage = parseInt($("input[name='currentPage']").val());
		if(currentPage==<%=pg.getTotalPage()%>){
			alert("没有下一页了。");
			return ;
		}
		$("input[name='currentPage']").val(parseInt($("input[name='currentPage']").val())+1);
		document.vehicleSele.submit();
	}
	
	function toPage(pg){
		
		$("input[name='currentPage']").val(pg);
		document.vehicleSele.submit();
	}
	
	$(document).ready(function(){
		var currentPage = parseInt($("input[name='currentPage']").val());
		$("#page-input").val(currentPage + "/<%=pg.getTotalPage()%>");
		
		$("#page-input").change(function(){
			
			var pg_num = parseInt($("#page-input").val());
			toPage(pg_num);
		});
		
		$("#page-input").focus(function(){
			$(this).val("");
		});
	});
</script>
  </head>
 <body>
<div>
   <div class="panel  margin-small" >
          	<div class="panel-head">
          	   <div class="line">
          	        	<div class="xm2">查询结果</div>
          	        	<div class="xm4 xm6-move">
          	        		       	<div class="button-toolbar">
	                                   <div class="button-group">
	                                     	<button onclick="_detail()" type="button" class="button icon-search text-blue" style="line-height: 6px;">
	                               		查看</button>
	                                    	<button onclick="_update()" type="button" class="button icon-pencil text-green" style="line-height: 6px;">
			                                                            修改</button>
			                                  <button onclick="_attach()" type="button" class="button icon-pencil text-green" style="line-height: 6px;">
			                                                            补充登记</button>
		                                    <!--<button onclick="_toExcel()" type="button" class="button icon-file-excel-o text-blue" style="line-height: 6px;">
			                                                            导出</button>
			                                  <button  onclick="_toPrint()" type="button" class="button icon-print text-green" style="line-height: 6px;">
			                                                            打印</button>-->
                                     </div>
                                </div>
          	        	</div>
          	        </div>
            </div>
  	
     
        <div class="panel-body">
        <s:if test="%{#request.list!=null}">
            <table class="table table-bordered table-hover table-striped">

                <tr>
                    <th>选择</th>
	<th class="complainTime  selected_able">投诉日期                    </th>
            <th class="complainClass selected_able">投诉类别                    </th>
            <th class="complainType    selected_able">投诉类型		              </th>
            <th class="licenseNum       selected_able">车牌号                      </th>
            <th class="complainObject       selected_able">违规项目                    </th>
            <th class="registrant        selected_able">登记人                      </th>
            <th class="registTime    selected_able">登记日期                    </th>
            <th class="state        selected_able">当前状态                    </th>
			
						<th class="isTrue     selected_able">是否属实                    </th>
            <th class="confirmPerson selected_able">确认人                      </th>
            <th class="confirmTime     selected_able">确认时间		              </th>
            <th class="dealPerson       selected_able">落实人                      </th>
            <th class="dealTime       selected_able">落实时间                    </th>
            <th class="dealReault        selected_able">处理情况                    </th>
            <th class="visitBackPerson    selected_able">回访人                      </th>
            <th class="visitBackTime        selected_able">回访时间                    </th>
						<th class="visitBackResult        selected_able">回访结果                    </th>
            <th class="finishPerson    selected_able">完结处理人                  </th>
            <th class="finishTime        selected_able">完结时间                    </th>
                </tr>
                

                    <s:iterator value="%{#request.list}" var="v">
                        <tr state="<s:property value="%{#v.state}"/>">
                            <td><input type="radio" name="cbx" value="<s:property value="%{#v.id}"/>" ></td>
<td class="complainTime selected_able"><s:property value="%{#v.complainTime}"/></td>
<td class="complainClass selected_able"><s:property value="%{#v.complainClass}"/></td>
<td class="complainType selected_able"><s:property value="%{#v.complainType}"/></td>
<td class="licenseNum selected_able"><s:property value="%{@com.dz.common.other.ObjectAccess@getObject('com.dz.module.vehicle.Vehicle',#v.vehicleId).licenseNum}"/></td>
<td class="complainObject selected_able"><s:property value="%{@com.dz.common.tablelist.TableListService@getFathersValueString(#v.complainObject)}"/></td>
<td class="registrant selected_able"><s:property value="%{@com.dz.common.other.ObjectAccess@getObject('com.dz.module.user.User',#v.registrant).uname}"/></td>
<td class="registTime selected_able"><s:property value="%{#v.registTime}"/></td>
<td class="state selected_able">
	<s:if test="%{#v.state==0}">
		待确认
	</s:if>
	<s:elseif test="%{#v.state==1}">
		待落实
	</s:elseif>
	<s:elseif test="%{#v.state==2}">
		待回访
	</s:elseif>
	<s:elseif test="%{#v.state==3}">
		待完结
	</s:elseif>
	<s:elseif test="%{#v.state==4}">
		已完结
	</s:elseif>
	<s:else>
		不属实
	</s:else>
</td>

<td class="isTrue selected_able"><s:property value="%{#v.state>0?'是':'否'}"/></td>
<td class="confirmPerson selected_able"><s:property value="%{@com.dz.common.other.ObjectAccess@getObject('com.dz.module.user.User',#v.confirmPerson).uname}"/></td>
<td class="confirmTime selected_able"><s:property value="%{#v.confirmTime}"/></td>
<td class="dealPerson selected_able"><s:property value="%{@com.dz.common.other.ObjectAccess@getObject('com.dz.module.user.User',#v.dealPerson).uname}"/></td>
<td class="dealTime selected_able"><s:property value="%{#v.dealTime}"/></td>
<td class="dealReault selected_able"><s:property value="%{@com.dz.common.other.ObjectAccess@getObject('com.dz.module.driver.Driver',#v.dealReault).name}"/></td>
<td class="visitBackPerson selected_able"><s:property value="%{@com.dz.common.other.ObjectAccess@getObject('com.dz.module.user.User',#v.visitBackPerson).uname}"/></td>
<td class="visitBackTime selected_able"><s:property value="%{#v.visitBackTime}"/></td>
<td class="visitBackResult selected_able"><s:property value="%{#v.visitBackResult}"/></td>
<td class="finishPerson selected_able"><s:property value="%{@com.dz.common.other.ObjectAccess@getObject('com.dz.module.user.User',#v.finishPerson).uname}"/></td>
<td class="finishTime selected_able"><s:property value="%{#v.finishTime}"/></td>
                        </tr>
                    </s:iterator>
				
            </table>
            </s:if>
            <s:if test="%{#request.list!=null}">
           <div class="line padding">
            	<div class="xm5-move">
            		<div>
            			<ul class="pagination">
                    <li> <a href="javascript:toBeforePage()">上一页</a> </li>
                  </ul>
                   <ul class="pagination pagination-group">
                    <li style="border: 0px;">
                    	<form>
                    		<div class="form-group">
                    			<div class="field">
                    			<input class="input input-auto" size="4" value="1/<%=pg.getTotalPage()%>" id="page-input" >
                    		</div>
                    			</div>
                    	</form>
                    	</li>
                   </ul>
                  <ul class="pagination">
                    <li><a href="javascript:toNextPage()">下一页</a></li>
                  </ul>
                  
            		</div>
            	</div>
            </div>
            </s:if>
            <s:else>
                无查询结果
            </s:else>
        </div>

    </div>
   <div class="line">
   	<div class="panel  margin-small" >
          	<div class="panel-head">
          	显示项
          	</div>
        <div class="panel-body">
            <div class="button-group checkbox" id="show_div">
              <label class="button active"><input type="checkbox" name="sbx" value="complainTime" checked="checked"><span class="icon icon-check text-green"></span>投诉日期</label>
		<label class="button active"><input type="checkbox" name="sbx" value="complainClass" checked="checked"><span class="icon icon-check text-green"></span>投诉类别</label>
		<label class="button active"><input type="checkbox" name="sbx" value="complainType" checked="checked"><span class="icon icon-check text-green"></span>投诉类型</label>
		<label class="button active"><input type="checkbox" name="sbx" value="licenseNum" checked="checked"><span class="icon icon-check text-green"></span>车牌号</label>
		<label class="button active"><input type="checkbox" name="sbx" value="complainObject" checked="checked"><span class="icon icon-check text-green"></span>违规项目</label>
		<label class="button active"><input type="checkbox" name="sbx" value="registrant" checked="checked"><span class="icon icon-check text-green"></span>登记人</label>
		<label class="button active"><input type="checkbox" name="sbx" value="registTime" checked="checked"><span class="icon icon-check text-green"></span>登记日期</label>
		<label class="button active"><input type="checkbox" name="sbx" value="state" checked="checked"><span class="icon icon-check text-green"></span>当前状态</label>
		<label class="button active"><input type="checkbox" name="sbx" value="isTrue" checked="checked"><span class="icon icon-check text-green"></span>是否属实</label>
		<label class="button active"><input type="checkbox" name="sbx" value="confirmPerson" checked="checked"><span class="icon icon-check text-green"></span>确认人</label>
		<label class="button active"><input type="checkbox" name="sbx" value="confirmTime" checked="checked"><span class="icon icon-check text-green"></span>确认时间</label>
		<label class="button active"><input type="checkbox" name="sbx" value="dealPerson" checked="checked"><span class="icon icon-check text-green"></span>落实人</label>
		<label class="button active"><input type="checkbox" name="sbx" value="dealTime" checked="checked"><span class="icon icon-check text-green"></span>落实时间</label>
		<label class="button active"><input type="checkbox" name="sbx" value="dealReault" checked="checked"><span class="icon icon-check text-green"></span>处理情况</label>
		<label class="button active"><input type="checkbox" name="sbx" value="visitBackPerson" checked="checked"><span class="icon icon-check text-green"></span>回访人</label>
		<label class="button active"><input type="checkbox" name="sbx" value="visitBackTime" checked="checked"><span class="icon icon-check text-green"></span>回访时间</label>
		<label class="button active"><input type="checkbox" name="sbx" value="visitBackResult" checked="checked"><span class="icon icon-check text-green"></span>回访结果</label>
		<label class="button active"><input type="checkbox" name="sbx" value="finishPerson" checked="checked"><span class="icon icon-check text-green"></span>完结处理人</label>
		<label class="button active"><input type="checkbox" name="sbx" value="finishTime" checked="checked"><span class="icon icon-check text-green"></span>完结时间</label>
            </div>

        </div>

    </div>

</div>

<form action="/DZOMS/driver/complain/searchComplain" method="post" name="vehicleSele">
    <s:hidden name="beginDate" />
    <s:hidden name="endDate" />
    <s:hidden name="states" />
    <s:hidden name="currentPage" value="%{#request.page.currentPage}" id="currentPage"></s:hidden>
</form>


</div>
</body>
 <script src="/DZOMS/res/js/apps.js"></script>
    <script>
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
