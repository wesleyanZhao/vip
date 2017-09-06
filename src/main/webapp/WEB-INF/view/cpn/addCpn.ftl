<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/resources/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field" >
    <legend>添加代金券</legend>
    <div class="layui-field-box" >
        <form class="layui-form">
        <#--查询代金券类别  -->
            <div class="layui-form-item">
                <label class="layui-form-label">代金券类别列表</label>
                <div class="layui-input-block" style="width: 190px">
                    <select id="cpnTypeNo" name="cpnTypeNo"  >
                        <option value=""><请选择></option>
                    <#list result as item >
                        <option value="${item.cpnTypeNo}">${item.cpnName}</option>
                    </#list>
                    </select>
                </div>
            </div>
        <#--查询代金券的商品类别  -->
            <div class="layui-form-item">
                <label class="layui-form-label">代金券商品类别列表</label>
                <div class="layui-input-block" style="width: 190px">
                    <select id="tpCd" name="tpCd"  >
                        <option value=""><请选择></option>
                    <#list resultTpCd as itemTpCd >
                        <option value="${itemTpCd.tpCd}">${itemTpCd.tpNm}</option>
                    </#list>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">代金券金额</label>
                <div class="layui-input-inline">
                    <input type="text" id="cpnSum" name="cpnSum" required lay-verify="required" placeholder="例如：5" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">代金券限额</label>
                <div class="layui-input-inline">
                    <input type="text" id="cpnLimit" name="cpnLimit" required lay-verify="required" placeholder="例如：50" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">代金券内容</label>
                <div class="layui-input-inline">
                    <input type="text" id="cpnContent" name="cpnContent" required lay-verify="required" placeholder="例如：满50可用" autocomplete="off" class="layui-input">
                </div>
            </div>
             <#--上架时间-->
            <div class="layui-form-item">
                <label class="layui-form-label">激活时间</label>
                <div class="layui-inline">
                    <input name="activeDate"  class="layui-input" placeholder="激活时间" onclick="layui.laydate({elem: this})">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">过期时间</label>
                <div class="layui-inline">
                    <input name="expireDate"  class="layui-input" placeholder="过期时间" onclick="layui.laydate({elem: this})">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">是否使用</label>
                <div class="layui-input-block">
                    <input  type="radio" name="isUsed" value="y" title="可使用" checked >
                    <input  type="radio" name="isUsed" value="n" title="不可使用" >
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="addCpnForm">确定</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>
</fieldset>
<script type="text/javascript" src="/resources/layui/layui.js"></script>
<script>
    layui.use(['form','jquery','layer','laydate'], function(){
        var $ = layui.jquery;
        var form = layui.form();
        var layer = layui.layer;
        //监听提交
        form.on('submit(addCpnForm)', function(params){
            var data = $("form").serializeArray();
            $.ajax({
                type: "POST",
                url: "/cpn/addCpnForm.do",  //后台程序地址
                data: data,  //需要post的数据
                success: function(data){           //后台程序返回的标签，比如我喜欢使用1和0 表示成功或者失败
                    if (data.result == 'success'){   //如果成功了, 则关闭当前layer
                        layer.msg('添加成功',{
                            icon: 1,
                            time: 1000 //1秒关闭（如果不配置，默认是3秒）
                        });
                    }

                }
            });
            return false;//return false 表示不通过页面刷新方式提交表单
        });
        form.render();
    });
</script>
</body>
</html>