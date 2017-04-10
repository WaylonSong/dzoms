<%@ page import="com.dz.module.driver.UrlAttachBean" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: doggy
  Date: 16-5-19
  Time: 下午2:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/DZOMS/res/css/pintuer.css">
    <link rel="stylesheet" href="/DZOMS/res/css/admin.css">
    <script src="/DZOMS/res/js/jquery.js"></script>
    <script src="/DZOMS/res/js/pintuer.js"></script>
</head>
<body>
<div class="margin-big-bottom">
    <div class="adminmin-bread" style="width: 100%;">
        <ul class="bread text-main" style="font-size: larger;">
            <li>驾驶员</li>
            <li>聘用</li>
            <li>聘用审核</li>

        </ul>
    </div>
</div>
<div class="panel">
     <div class="panel-head">
         聘用审核
     </div>
    <div class="panel-body">
        <table class="table">
            <tr>
                <th>姓名</th>
                <th>身份证</th>
                <th>操作</th>
            </tr>
            <%List<UrlAttachBean> urlAttachBeen = (List)request.getAttribute("urlAttachBeen");%>
            <%for(UrlAttachBean uab:urlAttachBeen)
            	if(!uab.getUrl().contains("driverPreupdate")){%>
             <tr>
                <td><%=uab.getName()%></td>
                <td><%=uab.getIdNum()%></td>
                <td><a href="<%=uab.getUrl()%>" class="button bg-blue">审核</a></td>
            </tr>
            <%}%>
        </table>
    </div>
</div>

</body>
</html>
