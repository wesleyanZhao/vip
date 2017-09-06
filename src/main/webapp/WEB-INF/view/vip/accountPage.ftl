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
<form class="layui-form" id="pageSubmit">
    <input type="hidden" id="currentPage" name="currentPage">

    <div class="layui-form-item">
        <label class="layui-form-label">会员编号</label>
        <div class="layui-input-inline">
            <input type="text" id="ordNo" name="vipNo" value="${(params.vipNo)!''}" placeholder="会员编号" autocomplete="off"
                   class="layui-input">
        </div>
        <label class="layui-form-label">账户积分</label>
        <div class="layui-input-inline">
            <input type="text" id="accPoint" name="startAccPoint" value="${(params.startAccPoint)!''}" placeholder="账户积分" autocomplete="off"
                   class="layui-input">
        </div>
        <div class="layui-input-inline">
            <input type="text" id="accPoint" name="lastAccPoint" value="${(params.lastAccPoint)!''}" placeholder="账户积分" autocomplete="off"
                   class="layui-input">
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">账户余额</label>
            <div class="layui-input-inline">
                <input type="text" id="accSum" name="startAccSum" value="${(params.startAccSum)!''}" placeholder="账户余额"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-input-inline">
                <input type="text" id="accSum" name="lastAccSum" value="${(params.lastAccSum)!''}" placeholder="账户余额"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">银行卡号</label>
            <div class="layui-input-inline">
                <input type="text" id="bankCardNo" name="bankCardNo" value="${(params.bankCardNo)!''}" placeholder="银行卡号"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <label class="layui-form-label"></label>
        <div class="layui-input-inline">
            <button class="layui-btn" lay-submit lay-filter="">点击查询</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>

</form>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>所有用户</legend>
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
            <th>ID</th>
            <th>会员编号</th>
            <th>账户积分</th>
            <th>账户余额</th>
            <th>银行名称</th>
            <th>银行卡类型</th>
            <th>银行卡号</th>
            <th>管理操作</th>
            <th>积分查询</th>
            <th>代金券查询</th>

        </tr>
        </thead>
        <tbody>
        <#list list as item>
        <tr>
            <td>${item.id!''}</td>
            <td>${item.vipNo!''}</td>
            <td>${item.accPoint!''}</td>
            <td>${item.accSum!''}</td>
            <td>${item.bankName! ''}</td>
            <td>
                <#if (item.bankName! '')!=''>
                    <#if item.bankCardType=='100'>借记卡
                    <#elseif item.bankCardType=='200'>信用卡
                    <#elseif item.bankCardType=='300'>储蓄卡
                    </#if>
                <#elseif (item.bankName! '')==''>无
                </#if>
            </td>
            <td>${item.bankCardNo! ''}</td>
            <td>
                <a class="updateAccount" vipNo="${item.vipNo!''}" accPoint="${item.accPoint!''}" accSum="${item.accSum!''}" bankCardNo="${item.bankCardNo!''}">修改账户</a>
            </td>
            <td>
                <a class="findIntegral" vipNo="${item.vipNo!''}"   >积分查询</a>
            </td>

            <td>
                <a class="findCoupon" vipNo="${item.vipNo!''}"    >代金券查询</a>
            </td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>
<div class="layui-form">
    <span id="form_page"></span>&nbsp;共${page.total}条数据
</div>

<script type="text/javascript" src="/resources/layui/layui.js"></script>
<script type="text/javascript">

    layui.define(['element', 'form', 'layer', 'laypage','laydate','jquery'], function (exports) {
        var element = layui.element();
        var form = layui.form();
        var layer = layui.layer;
        var laypage = layui.laypage;
        var $ = layui.jquery;

        var pindex = "${page.pageNum}";// 当前页
        var ptotalpages = "${page.pages}";// 总页数
        var pcount = "${page.total}";// 数据总数

        // 分页
        laypage({
            cont: 'form_page', // 页面上的id
            pages: ptotalpages,//总页数
            curr: pindex,//当前页。
            skip: true,
            jump: function (obj, first) {
                $("#currentPage").val(obj.curr);//设置当前页
                //$("#size").val(psize);
                //防止无限刷新,
                //只有监听到的页面index 和当前页不一样是才出发分页查询
                if (obj.curr != pindex) {
                    $("#pageSubmit").submit();
                }
            }
        });





        //修改操作弹出层
        $(".updateAccount").on("click", function () {
            var vipNo = $(this).attr("vipNo");
            var accPoint =$(this).attr("accPoint");
            var bankCardNo = $(this).attr("bankCardNo");
            var accSum = $(this).attr("accSum");
            layer.open({
                title: '修改操作 - 惠买ivalue管理系统'
                , area: ['500px', '400px']
                , type: 2 //content内容为一个连接
                , content: '/mac/modifyUpdate.do?vipNo=' + vipNo+"&&accPoint="+accPoint+"&&accSum="+accSum+"&&bankCardNo="+bankCardNo
            });
        });

        //积分信息查询
        $(".findIntegral").on("click", function () {
            var vipNo = $(this).attr("vipNo");
            layer.open({
                title: '修改操作 - 惠买ivalue管理系统'
                , area: ['500px', '400px']
                , type: 2 //content内容为一个连接
                , content: '/mac/integralFind.do?vipNo=' + vipNo
            });
        });
        //代金券信息查询
        $(".findCoupon").on("click", function () {
            var vipNo = $(this).attr("vipNo");
            layer.open({
                title: '修改操作 - 惠买ivalue管理系统'
                , area: ['500px', '400px']
                , type: 2 //content内容为一个连接
                , content: '/mac/findCoupon.do?vipNo=' + vipNo
            });
        });
    });
</script>
</body>
</html>