<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/resources/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field" >
    <legend>会员信息修改</legend>
    <div class="layui-field-box" >
        <form class="layui-form">
           <#list list as item>
            <div class="layui-form-item">
                <label class="layui-form-label">会员地址</label>
                <div class="layui-input-inline">
                    <input name="site" class="layui-input" type="text" value="${item.province}${item.city}${(item.addrInfo)!''}">
                </div>
                <div class="layui-input-inline">
                    <input class="updateShow"  class="layui-input" type="button" value="修改" <#--value="${item.province}${item.city}${(item.addrInfo)!''}"-->>
                </div>
            </div>

            </#list>
                <div class="Div"  style="display: none">
                    <div class="layui-form-item">
                        <label class="layui-form-label">会员地址</label>
                        <div class="layui-input-inline">
                            <select name="provinceId" lay-filter="select" id="provinceId">
                                <option value="0">请选择省份</option>
                            <#list provinceList as prce>
                                <option value="${prce.provinceId}">${prce.province}</option>
                            </#list>
                            </select>
                        </div>
                        <div class="layui-input-inline">
                            <select name="cityId" id="cityId">
                                <option value="0">请选择城市</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">详细地址</label>
                        <div class="layui-input-inline">
                            <input type="text" name="addrInfo" class="layui-input">
                        </div>
                    </div>
                </div>
                <input name="addId" value="${(map.addId)!''}" type="hidden">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <button class="layui-btn" lay-submit lay-filter="registerForm">立即提交</button>
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
    layui.use(['form','jquery','layer'], function(){
        var $ = layui.jquery;
        var form = layui.form();
        var layer = layui.layer;

        $(".updateShow").on("click",function () {
            layer.msg("dsad")
            $(".Div").css("display","block");
        })
        //监听提交
        form.on('submit(registerForm)', function(params){
            //表单数据
            /* var username = $("#username").val();
             var password = $("#password").val();
             var gender = $("input[name='gender']:checked").val();
             var organization = $("#organization").val();*/
            //等同于上面注释掉的部分
            var data = $("form").serializeArray();

            $.ajax({
                type: "POST",
                url: "/vip/updateSite.do",  //后台程序地址
                data: data,  //需要post的数据
                success: function(data){           //后台程序返回的标签，比如我喜欢使用1和0 表示成功或者失败
                    console.log(data);
                    if (data.result == 'success'){   //如果成功了, 则关闭当前layer
                        layer.msg('修改成功',{
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
                    }
                }
            });
            return false;//return false 表示不通过页面刷新方式提交表单
        });


        form.on('select(select)', function(params){
            var Data = $("#provinceId").serializeArray();
            var selectData = $('#provinceId').val();

            if (selectData != 0) {
                $.ajax({
                    type: "POST",
                    url: "/vip/findCity.do",  //后台程序地址
                    data: Data,  //需要post的数据
                    success: function (data) {
                        //后台程序返回的标签，比如我喜欢使用1和0 表示成功或者失
                        $('#cityId').text("");
                        var op="<option value='0'>请选择城市</option>"
                        $('#cityId').append(op);
                        for (var i = 0; i < data.length; i++) {
                            var opt = "<option value = '" + data[i].cityId+ "'>" + data[i].city + "</option>"
                            $("#cityId").append(opt);
                        }
                        form.render();
                    }
                });
            }
            return false;//return false 表示不通过页面刷新方式提交表单
        });

        form.render();

    });
</script>
</body>
</html>