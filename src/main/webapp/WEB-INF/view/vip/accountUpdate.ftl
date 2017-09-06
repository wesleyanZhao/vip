<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/resources/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field" >
    <legend>修改账户信息</legend>
    <div class="layui-field-box" >
        <form class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label">现有积分</label>
                <div class="layui-input-inline">
                    <input type="text" name="" value="${(result.accPoint)!''}" disabled autocomplete="off" class="layui-input">
                    <input type="hidden" name="vipNo" value="${(result.vipNo)!''}"/>
                    <#--<input type="hidden" name="cpnNo" value="${(cpnNo)!''}"/>-->
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">修改积分</label>
                <div class="layui-input-inline">
                    <input type="text" name="accPoint"  placeholder="请输入积分" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">现有余额</label>
                <div class="layui-input-inline">
                    <input type="text" name="" value="${(result.accSum)!''}" disabled placeholder="请输库存数量" autocomplete="off" class="layui-input">

                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">修改余额</label>
                <div class="layui-input-inline">
                    <input type="text" name="accSum" placeholder="请输入余额" autocomplete="off" class="layui-input">

                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">银行卡号</label>
                <div class="layui-input-inline">
                    <input type="text" name="" value="${(result.bankCardNo)!''}" disabled placeholder="请输银行卡卡号" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">修改银行卡号</label>
                <div class="layui-input-inline">
                    <input type="text" name="bankCardNo"  placeholder="请输银行卡卡号" autocomplete="off" class="layui-input">
                </div>
            </div>


            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="updateBalance">确定</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>
</fieldset>
<script type="text/javascript" src="/resources/layui/layui.js"></script>
<script>
    //Demo
    // 待学生自主完成
    layui.use(['form','jquery','layer','laydate'], function(){
        var $ = layui.jquery;
        var form = layui.form();
        var layer = layui.layer;

        //监听提交
        form.on('submit(updateBalance)', function(params){
            //表单数据
            /* var username = $("#username").val();
             var password = $("#password").val();
             var gender = $("input[name='gender']:checked").val();
             var organization = $("#organization").val();*/

            //等同于上面注释掉的部分
            var data = $("form").serializeArray();

            $.ajax({
                type: "POST",
                url: "/mac/updateBalance.do",  //后台程序地址
                data: data,  //需要post的数据
                success: function(data){           //后台程序返回的标签，比如我喜欢使用1和0 表示成功或者失败
                    if (data.result == 'success'){   //如果成功了, 则关闭当前layer
                        layer.msg('成功',{
                            icon: 1,
                            time: 1000 //1秒关闭（如果不配置，默认是3秒）
                        },function(){//
                            //do something
                            //注册成功后，自动关闭当前注册页面
                            //先得到当前iframe层的索引
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                            //parent.layer.closeAll("iframe");
                        });
                    }else{
                        layer.msg('失败');
                    }
                }
            });
            return false;//return false 表示不通过页面刷新方式提交表单
        });
    });
</script>
</body>
</html>
