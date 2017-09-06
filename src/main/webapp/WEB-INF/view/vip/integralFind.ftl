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
    <legend>积分查询</legend>
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
            <th>积分记录</th>
            <th>积分状态</th>
            <th>日期</th>
            <th>积分单号</th>
        </tr>
        </thead>
        <tbody>
            <#list lm as item>
            <tr>
                <td>${item.vipNo}</td>
                <td>
                <#if item.pointState=='u'>-${item.pointNum}
                <#else>+${item.pointNum}
                </#if>
                <td>${item.pointState}</td>
                <td>${item.rcdDate}</td>
                <td>${item.ordNo}</td>
            </tr>
            </#list>
        </tbody>
    </table>
</div>
<#--<div class="layui-form">-->
    <#--<span id="form_page"></span>&nbsp;共${page.total}条数据-->
<#--</div>-->
<script type="text/javascript" src="/resources/layui/layui.js"></script>
<script type="text/javascript">

    layui.define([ 'element', 'form', 'layer', 'laypage'], function(exports) {
        var element = layui.element();
        var form = layui.form();
        var layer = layui.layer;
        var laypage = layui.laypage;
        var $ = layui.jquery;

    });
</script>
</body>
</html>