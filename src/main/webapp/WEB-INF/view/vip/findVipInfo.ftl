<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="Generator" content="EditPlus®">
    <meta name="Author" content="">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <title>会员信息-查询</title>
    <link rel="stylesheet" href="/resources/layui/css/layui.css">
</head>

<body>
<form id="pageSubmit" class="layui-form">
    <input type="hidden" id="currentPage" name="currentPage" >
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">会员ID</label>
            <div class="layui-input-inline">
                <input name="id" class="layui-input" placeholder="请输入" type="text" value="${(map.id)!''}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">会员编号</label>
            <div class="layui-input-inline">
                <input name="vipNo" class="layui-input" placeholder="请输入" type="text" value="${(map.vipNo)!''}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">会员账号</label>
            <div class="layui-input-inline">
                <input name="vipAccount" class="layui-input" placeholder="请输入" type="text" value="${(map.vipAccount)!''}">
            </div>
        </div>
        <div class="layui-inline">
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
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">身份证号</label>
            <div class="layui-input-inline">
                <input name="idCard" class="layui-input" placeholder="请输入" type="text" value="${(map.idCard)!''}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline">
                <input name="realName" class="layui-input" placeholder="请输入" type="text" value="${(map.realName)!''}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">注册日期</label>
            <div class="layui-input-inline">
                <input class="layui-input" placeholder="请选择" name="startRegDate" id="LAY_demorange_s" value="${(map.startRegDate)!''}">
            </div>
            <div class="layui-input-inline">
                <input class="layui-input" placeholder="请选择" name="lastRegDate" id="LAY_demorange_e" value="${(map.lastRegDate)!''}">
            </div>
        </div>
        <div class="layui-inline">
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
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-inline">
                <input name="email" class="layui-input" placeholder="请输入" type="text" value="${(map.email)!''}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">电话一</label>
            <div class="layui-input-inline">
                <input name="firstPhoneNo" class="layui-input" placeholder="请输入" type="text" value="${(map.firstPhoneNo)!''}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">电话二</label>
            <div class="layui-input-inline">
                <input name="secondPhoneNo" class="layui-input" placeholder="请输入" type="text" value="${(map.secondPhoneNo)!''}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">会员地址</label>
            <div class="layui-input-inline">
                <select name="provinceId" lay-filter="select" id="provinceId">
                    <option value="0">请选择省份</option>
                    <#list provinceList as item>
                    <option value="${item.provinceId}">${item.province}</option>
                    </#list>
                </select>
            </div>
            <div class="layui-input-inline" id="cityDiv">
                <select name="cityId" id="cityId">
                    <option value="0">请选择城市</option>
                    <#list cityList as item>
                    <option value="${item.cityId}">${item.city}</option>
                    </#list>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <button class="layui-btn layui-btn-primary layui-btn-big" lay-submit lay-filter="paging">确定</button>
        </div>
    </div>
</form>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>所有会员</legend>
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
            <th>会员ID</th>
            <th>会员编号</th>
            <th>会员账号</th>
            <th>会员类型</th>
            <th>身份证号</th>
            <th>真实姓名</th>
            <th>生日</th>
            <th>注册日期</th>
            <th>会员等级</th>
            <th>邮箱</th>
            <th>电话</th>
            <th>电话</th>
            <th>地址</th>
            <th>操作</th>
            <th>地址修改</th>
        </tr>
        </thead>
        <tbody>
        <#list list as item>
        <tr>
            <td>${item.id}</td>
            <td>${item.vipNo}</td>
            <td>${item.vipAccount}</td>
            <td>
                <#if item.vipType == '100'>网上会员
                <#elseif item.vipType == '200'>手机会员
                <#elseif item.vipType == '300'>线下会员
                </#if>
            </td>
            <td>${item.idCard! ''}</td>
            <td>${item.realName! ''}</td>
            <td>${item.vipBirthday! ''}</td>
            <td>${item.regDate}</td>
            <td>
                <#if item.vipLevel == 1>一星会员
                <#elseif item.vipLevel == 2>二星会员
                <#elseif item.vipLevel == 3>三星会员
                <#elseif item.vipLevel == 4>四星会员
                <#elseif item.vipLevel == 5>五星会员
                </#if>
            </td>
            <td>${item.email}</td>
            <td>
                <#if item.firstPhoneNo == ''>--
                <#else>${item.firstPhoneNo}
                </#if>
            </td>
            <td>
                <#if item.secondPhoneNo == ''>--
                <#else>${item.secondPhoneNo}
                </#if>
            </td>
            <td>${item.province}${item.city}${item.addrInfo}</td>
            <th><a class="amend" vipId="${item.id}" vipNo="${item.vipNo}" vipAccount="${item.vipAccount}" vipType="${item.vipType}"
                   vipLevel="${item.vipLevel}" email="${item.email}" firstPhoneNo="${item.firstPhoneNo}" secondPhoneNo="${item.secondPhoneNo}"
                   regDate="${item.regDate}" idCard="${item.idCard}" realName="${item.realName}">详情修改</a></th>
            <th><a class="site" cityId="${item.cityId}" provinceId="${item.provinceId}"
                   vipNo="${item.vipNo}" addId="${item.addId}">地址修改</a></th>
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


    layui.define([ 'element', 'form', 'layer', 'laypage'], function(exports) {
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

        //监听提交
        form.on('submit(paging)', function(){

            return true;
            from.render();
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


        /*
        *
        *详情修改
        *
        * */


        $(".amend").on("click",function(){
            var vipId=$(this).attr("vipId");
            var vipNo=$(this).attr("vipNo");
            var vipAccount=$(this).attr("vipAccount");
            var vipLevel=$(this).attr("vipLevel");
            var vipType=$(this).attr("vipType");
            var email=$(this).attr("email");
            var firstPhoneNo=$(this).attr("firstPhoneNo");
            var regDate=$(this).attr("regDate");
            var secondPhoneNo=$(this).attr("secondPhoneNo");
            var idCard=$(this).attr("idCard");
            var realName=$(this).attr("realName");
            layer.open({
                title: '会员信息修改'
                ,area: ['500px', '500px']
                ,type: 2 //content内容为一个连接
                ,content: '/vip/showVipInfo.do?vipId='+vipId+"&&vipNo="+vipNo+"&&vipAccount="+vipAccount+"&&vipLevel="+vipLevel
                +"&&vipType="+vipType+"&&email="+email+"&&firstPhoneNo="+firstPhoneNo+"&&secondPhoneNo="+secondPhoneNo
                +"&&regDate="+regDate+"&&idCard="+idCard+"&&realName="+realName

            });
        });

        $(".site").on("click",function(){
            var cityId=$(this).attr("cityId");
            var provinceId=$(this).attr("provinceId");
            var vipNo=$(this).attr("vipNo");
            var addId=$(this).attr("addId");
            layer.open({
                title: '会员信息修改'
                ,area: ['700px', '600px']
                ,type: 2 //content内容为一个连接
                ,content: '/vip/showSite.do?=cityId='+cityId+"&&provinceId="+provinceId
                +"&&vipNo="+vipNo+"&&addId="+addId


            });
        });


        layui.use('laydate', function(){
            var laydate = layui.laydate;

            var start = {
                 min: '1937-05-16 23:59:59'
                ,max: '2099-06-16 23:59:59'
                ,istoday: false
                ,choose: function(datas){
                    end.min = datas; //开始日选好后，重置结束日的最小日期
                    end.start = datas //将结束日的初始值设定为开始日
                }
            };

            var end = {
                 min: '1937-05-16 23:59:59'
                ,max: '2099-06-16 23:59:59'
                ,istoday: false
                ,choose: function(datas){
                    start.max = datas; //结束日选好后，重置开始日的最大日期
                }
            };

            document.getElementById('LAY_demorange_s').onclick = function(){
                start.elem = this;
                laydate(start);
            }
            document.getElementById('LAY_demorange_e').onclick = function(){
                end.elem = this
                laydate(end);
            }

        });


        var vipType="${(map.vipType)!''}"
        var vipLevel="${(map.vipLevel)!''}"


        $("#provinceId").find("option[value = \"${(map.provinceId)!''}\"]").attr("selected","selected");
        $("#cityId").find("option[value = \"${(map.cityId)!''}\"]").attr("selected","selected");


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


        form.render();
    });
</script>
</body>
</html>