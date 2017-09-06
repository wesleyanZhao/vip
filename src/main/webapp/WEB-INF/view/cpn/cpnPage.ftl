<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="Generator" content="EditPlus®">
    <meta name="Author" content="">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <title>代金券管理系统 惠买ivalue后台管理</title>
    <link rel="stylesheet" href="/resources/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>筛选条件</legend>
</fieldset>
<form id="pageSubmit" class="layui-form" method="post">
    <input type="hidden" id="currentPage" name="currentPage" >
    <div class="layui-form-item">
        <div class="layui-form-item">
            <label class="layui-form-label">代金券类别名称</label>
            <div class="layui-input-block" style="width: 190px">
                <select id="cpnTpyeNo" name="cpnTpyeNo"  >
                    <option value="999"><请选择></option>
                <#list resultType as itemType >
                    <option value="${itemType.cpnTypeNo! ''}">${itemType.cpnName! ''}</option>
                </#list>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">代金券金额</label>
        <div class="layui-input-inline">
            <input type="text"  value="${params.cpnSum!''}" name="cpnSum"   placeholder="例如：5" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">代金券限额</label>
        <div class="layui-input-inline">
            <input type="text"  value="${params.cpnLimit!''}" name="cpnLimit"   placeholder="例如：50" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">代金券内容</label>
        <div class="layui-input-inline">
            <input type="text" id="cpnContent"  value="${params.cpnContent!''}" name="cpnContent"   placeholder="例如：满50可用" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-pane" style="margin-top: 15px;">
        <div class="layui-form-item">
            <label class="layui-form-label">日期范围</label>
            <div class="layui-input-inline">
                <input class="layui-input" name="activeDate" value="${params.activeDate!''}" id="LAY_demorange_s" placeholder="激活时间" onclick="layui.laydate({elem: this})">
            </div>
            <div class="layui-input-inline">
                <input class="layui-input" name="expireDate"  value="${params.expireDate!''}" id="LAY_demorange_e" placeholder="过期时间" onclick="layui.laydate({elem: this})">
            </div>
        </div>
    </div>
    <div  class="layui-inline">
        <label class="layui-form-label">是否可用</label>
        <div class="layui-input-inline">
            <select id="isUsed" name="isUsed" >
                <option value="k">请选择</option>
                <option value="y">是</option>
                <option value="n">否</option>
            </select>
        </div>
    </div>
    <div class="layui-inline">
        <button class="layui-btn" lay-submit lay-filter="">搜索</button>
        <button class="layui-btn layui-btn-primary" type="reset">重置</button>
    </div>
</form>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>代金券列表</legend>
</fieldset>
<div class="layui-form">
    <table class="layui-table">
        <colgroup>
            <col width="100">
            <col width="100">
            <col width="100">
            <col width="100">
            <col width="100">
            <col width="100">
            <col width="100">
            <col width="100">
            <col width="100">
            <col width="100">
        </colgroup>
        <thead>
        <tr>
            <th>ID</th>
            <th>代金券编号</th>
            <th>代金券类别名称</th>
            <th>商品类别编号</th>
            <th>代金券金额</th>
            <th>代金券限额</th>
            <th>代金券内容</th>
            <th>激活时间</th>
            <th>过期时间</th>
            <th>是否可用</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
            <#list list as item>
            <tr>
                <td>${item.id! ''}</td>
                <td>${item.cpnNo! ''}</td>
                <td>${item.cpnName! ''}</td>
                <td>${item.tpCd! ''}</td>
                <td>${item.cpnSum! ''}</td>
                <td>${item.cpnLimit! ''}</td>
                <td>${item.cpnContent! ''}</td>
                <td>${item.activeDate! ''}</td>
                <td>${item.expireDate! ''}</td>
                <td>
                    <#if item.isUsed=='y'>可用
                    <#else>不可用
                    </#if>
                </td>
                <td>
                    <a href="javascript:;" class="layui-btn layui-btn-radius"  var="${item.id!''}">修改</a><#--id为代金券信息id-->
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
    layui.define([ 'element', 'form', 'layer', 'laypage' ,'laydate'], function(exports) {
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
            cont : 'form_page', // 页面上的id
            pages : ptotalpages,//总页数
            curr : pindex,//当前页。
            skip : true,
            jump : function(obj, first) {
                $("#currentPage").val(obj.curr);//设置当前页
                //$("#size").val(psize);
                //防止无限刷新,
                //只有监听到的页面index 和当前页不一样是才出发分页查询
                if (obj.curr != pindex ) {
                    $("#pageSubmit").submit();
                }
            }
        });
        //带回页面的select参数进行动态赋值
        var isUsed = "${params.isUsed!''}";
        var cpnTpyeNo = "${params.cpnTpyeNo!''}";
        // 菜单级别动态赋值
        if(cpnTpyeNo == '100') {
            $("#cpnTpyeNo").find("option[value = '100']").attr("selected","selected");
        } else if(cpnTpyeNo == '200') {
            $("#cpnTpyeNo").find("option[value = '200']").attr("selected","selected");
        } else if(cpnTpyeNo == '300') {
            $("#cpnTpyeNo").find("option[value = '300']").attr("selected","selected");
        }
        if(isUsed == 'y') {
            $("#isUsed").find("option[value = 'y']").attr("selected","selected");
        } else if(isUsed == 'n') {
            $("#isUsed").find("option[value = 'n']").attr("selected","selected");
        }
        // 重新渲染页面
        form.render();
        //  弹出窗  修改代金券信息
        $(".addPraDetail").on("click", function(){
            var id = $(this).attr("var");
            layer.open({
                title: '商品明细 - 惠买ivalue管理系统'
                ,area: ['550px', '500px']
                ,type: 2//content内容为一个连接 type: 1   content 下写什么  弹出内容就是什么   就是文字  没有效果
                ,content: '/cpn/updateCpnInfo.do?id='+id
            });
        });
    });
</script>
</body>
</html>