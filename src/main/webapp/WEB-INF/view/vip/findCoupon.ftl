<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="Generator" content="EditPlus®">
    <meta name="Author" content="">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <title>商品管理系统 惠买ivalue后台管理</title>
    <link rel="stylesheet" href="/resources/layui/css/layui.css">
</head>

<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>代金券查询</legend>
</fieldset>

<div class="layui-form">
    <table class="layui-table">
        <colgroup>
            <col width="50">
            <col width="200">
            <col width="100">
            <col width="100">
            <col width="100">
            <col>
        </colgroup>
        <thead>
        <tr>
            <th>会员编号</th>
            <th>代金券类型</th>
            <th>代金券金额</th>
            <th>代金券内容</th>
            <th>日期</th>
        </tr>
        </thead>
        <tbody>
            <#list list as item>
            <tr>
                <td>${item.vipNo}</td>
                <td>
                <#if item.cpnTypeNo=='100'>网站
                <#elseif item.cpnTypeNo=='200'>手机
                <#elseif item.cpnTypeNo=='300'>线下
                </#if>
                <td>${item.cpnSum}</td>
                <td>${item.cpnContent}</td>
                <td>${item.instDate}</td>
            </tr>
            </#list>
        </tbody>
    </table>
</div>
<script type="text/javascript" src="/resources/layui/layui.js"></script>
<script type="text/javascript">
</script>
</body>
</html>