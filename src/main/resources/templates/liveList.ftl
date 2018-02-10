<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, height=device-height, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
    <#include "${ctx}/public/common.ftl"/>
</head>
<body>
<table class="layui-table">
    <tr>
        <td>PublishTime</td>
        <td>StreamName</td>
        <td>PublishUrl</td>
        <td>DomainName</td>
        <td>AppName</td>
        <td>PublishDomain</td>
        <td>操作</td>
    </tr>
    <#list result.OnlineInfo.LiveStreamOnlineInfo as streamInfo>
    <tr>
        <td>${streamInfo.PublishTime}</td>
        <td>${streamInfo.StreamName}</td>
        <td>${streamInfo.PublishUrl}</td>
        <td>${streamInfo.DomainName}</td>
        <td>${streamInfo.AppName}</td>
        <td>${streamInfo.PublishDomain}</td>
        <td><a href="${ctx}/show/live" class="layui-btn layui-btn-sm">查看直播</a></td>
    </tr>
    </#list>
</table>
</body>
</html>