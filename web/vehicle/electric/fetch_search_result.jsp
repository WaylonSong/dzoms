<%@page import="com.dz.common.other.ObjectAccess"%>
<%@page import="com.dz.module.vehicle.Vehicle"%>
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
	<title>电子违章查询结果</title>
	
	<link rel="stylesheet" href="/DZOMS/res/css/pintuer.css">
	<link rel="stylesheet" href="/DZOMS/res/css/admin.css">
	<script src="/DZOMS/res/js/jquery.js"></script>
	<script src="/DZOMS/res/js/pintuer.js"></script>
	<script src="/DZOMS/res/js/respond.js"></script>
	<script src="/DZOMS/res/js/admin.js"></script>
    <script>
        $(document).ready(function(){
            $("#show_div").find("input").change(resetClass);
            resetClass();
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
	
	function _toExcel(){
		var url="/DZOMS/vehicle/exportToExcel?condition="+encodeURI($("input[name='condition']").val());
		window.open(url,"电子违章数据导出",'width=800,height=600,resizable=yes,scrollbars=yes');
	}
</script>
  </head>
 <body>
<div class="line">
   <div class="panel  margin-small" >
          	<div class="panel-head">
          	    <div class="line">
          	        	<div class="xm2">查询结果</div>
          	        	<div class="xm4 xm6-move">
          	        		       	<div class="button-toolbar">
	                                   <div class="button-group">
	                                    	<button onclick="_toExcel()" type="button" class="button icon-pencil text-green" style="line-height: 6px;">
			                                                            导出为Excel</button>
                                     </div>
                                </div>
          	        	</div>
          	        </div>	
          </div>
        <div class="panel-body">
            
          
                <table class="table table-striped table-bordered table-hover">

                    <tr>
<!--                        <th>选择</th>
-->                     <th class="carframeNum selected_able">车架号</th>
                        <th class="licenseNum selected_able">车牌号</th>
                        <th class="area selected_able">违章地点</th>
                        <th class="act selected_able">违章事项</th>
                        <th class="fen selected_able">分值</th>
                        <th class="money selected_able">罚款</th>
                        <th class="date selected_able">日期</th>
                        
                    </tr>
                    <s:if test="%{#request.list!=null}">
        
        <s:iterator value="%{#request.list}" var="v">
                   
<tr>
<!-- <td><input type="checkbox" name="cbx" value="<s:property value="%{#v.carframeNum}"/>" ></td>
--> <td class="carframeNum selected_able"><s:property value="%{#v.carframeNum }"/></td>
 <td class="licenseNum selected_able"><s:property value="%{#v.licenseNum}"/></td>
 <td class="area selected_able"><s:property value="%{#v.area }"/></td>
<td class="act selected_able"><s:property value="%{#v.act }"/></td>
<td class="fen selected_able"><s:property value="%{#v.fen }"/></td>
<td class="money selected_able"><s:property value="%{#v.money }"/></td>
<td class="date selected_able"><s:property value="%{#v.date }"/></td>
 </tr>
                    </s:iterator>
                    </s:if>
                </table>
            

            
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
    <s:if test="%{#request.list!=null}">
    	<div class="panel-foot border-red-light bg-red-light">
            合计：<%=pg.getTotalCount() %>条记录。
        </div>
    </s:if>
    <s:else>
    无查询结果
    </s:else>
        
       </div>
        
    </div>
</div>
 <div class="line">
   	<div class="panel  margin-small" >
          	<div class="panel-head">
          		显示项
          	</div>
        <div class="panel-body">
            <div class="button-group checkbox bg-blue-light" id="show_div">
                <label class="button active">
                    <input type="checkbox" name="sbx" value="carframeNum" checked="checked"><span class="icon icon-check text-green"></span>车架号
                </label>
                <label class="button active">
                    <input type="checkbox" name="sbx" value="licenseNum" checked="checked"><span class="icon icon-check text-green"></span>车牌号
                </label>
                <label class="button active"><input type="checkbox" name="sbx" value="area" checked="checked"><span class="icon icon-check text-green"></span>违章地点</label>
<label class="button active"><input type="checkbox" name="sbx" value="act" checked="checked"><span class="icon icon-check text-green"></span>违章事项</label>
<label class="button active"><input type="checkbox" name="sbx" value="fen" checked="checked"><span class="icon icon-check text-green"></span>分值</label>
<label class="button active"><input type="checkbox" name="sbx" value="money" checked="checked"><span class="icon icon-check text-green"></span>罚款</label>
<label class="button active"><input type="checkbox" name="sbx" value="date" checked="checked"><span class="icon icon-check text-green"></span>日期</label>
            </div>

        </div>

    </div>
   	
   </div>

<form action="/DZOMS/common/selectToList" method="post" name="vehicleSele">
    <s:hidden name="condition" />
    <input type="hidden" name="className" value="com.dz.module.vehicle.electric.ElectricHistory"/>
    <input type="hidden" name="url" value="/vehicle/electric/fetch_search_result.jsp" />
    <s:hidden name="currentPage" value="%{#request.page.currentPage}" id="currentPage"></s:hidden>
</form>
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
