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
            <div class="layui-form-item">
                <label class="layui-form-label">会员ID</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" name="id" value="${(map.vipId)!''}" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">会员编号</label>
                <div class="layui-input-inline">
                    <input type="text" name="vipNo" class="layui-input" value="${(map.vipNo)!''}" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">会员账号</label>
                <div class="layui-input-block" id="div_input">
                    <input type="text" name="vipAccount" value="${(map.vipAccount)!''}" class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">注册日期</label>
                <div class="layui-input-inline">
                    <input name="regDate" class="layui-input" type="text" value="${(map.regDate)!''}" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">身份证号</label>
                <div class="layui-input-inline">
                    <input name="idCard" class="layui-input" placeholder="请输入" type="text" value="${(map.idCard)!''}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">真实姓名</label>
                <div class="layui-input-inline">
                    <input name="realName" class="layui-input" placeholder="请输入" type="text" value="${(map.realName)!''}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-inline">
                    <input name="email" class="layui-input" placeholder="请输入" type="text" value="${(map.email)!''}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">电话一</label>
                <div class="layui-input-inline" id="firstPhoneNoDiv">
                    <input name="firstPhoneNo" class="layui-input" placeholder="请输入" type="text" value="${(map.firstPhoneNo)!''}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">电话二</label>
                <div class="layui-input-inline" id="secondPhoneNoDiv">
                    <input name="secondPhoneNo" class="layui-input" placeholder="请输入" type="text" value="${(map.secondPhoneNo)!''}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">会员类型</label>
                <div class="layui-input-inline">
                    <select name="vipType" lay-filter="selectmenu" id="vipType">
                        <option value="000">请选择</option>
                        <option value="100">网上会员</option>
                        <option value="200">手机会员</option>
                        <option value="300">线下会员</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">会员等级</label>
                <div class="layui-input-inline">
                    <select name="vipLevel" lay-filter="selectmenu" id="vipLevel">
                        <option value="0">请选择</option>
                        <option value="1">一星会员</option>
                        <option value="2">二星会员</option>
                        <option value="3">三星会员</option>
                        <option value="4">四星会员</option>
                        <option value="5">五星会员</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
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

        /*
        * 回显
        * */
        var vipType="${(map.vipType)!''}"
        var vipLevel="${(map.vipLevel)!''}"


        if(vipType == '100') {
            $("#vipType").find("option[value = '100']").attr("selected","selected");
        } else if(vipType == '200') {
            $("#vipType").find("option[value = '200']").attr("selected","selected");
        }else if (vipType == '300'){
            $("#vipType").find("option[value = '300']").attr("selected","selected");
        }else if (vipType == '000'){
            $("#vipType").find("option[value = '000']").attr("selected","selected");
        }


        if(vipLevel == 1) {
            $("#vipLevel").find("option[value = '1']").attr("selected","selected");
        }else if (vipLevel == 2) {
            $("#vipLevel").find("option[value = '2']").attr("selected","selected");
        }else if (vipLevel == 3) {
            $("#vipLevel").find("option[value = '3']").attr("selected","selected");
        }else if (vipLevel == 4) {
            $("#vipLevel").find("option[value = '4']").attr("selected","selected");
        }else if (vipLevel == 5) {
            $("#vipLevel").find("option[value = '5']").attr("selected","selected");
        }else if (vipLevel == 0) {
            $("#vipLevel").find("option[value = '0']").attr("selected","selected");
        }




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
                url: "/vip/updateVipInfo.do",  //后台程序地址
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

        form.render();

    });
</script>
</body>
</html>