<%@ page import="java.util.Date" pageEncoding="utf-8" %>
<html>
<head>
    <title>天津医科大学总医院医疗设备检修预警系统</title>

    <link rel="stylesheet" href="layui/css/layui.css"/>
    <link rel="stylesheet" href="css/vcode.css">
    <script type="text/javascript" src="jquery/jquery3.4.1.js"></script>
    <script type="text/javascript" src="layui/layui.all.js"></script>
    <script type="text/javascript" src="js/vcode.js"></script>
</head>
<body>
<center style="margin-top: 100px;">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
        <legend><label><h2>天津医科大学总医院医疗设备检修预警系统</h2></label></legend>
    </fieldset>
    <div align="center" style="width: 500px">
        <div class="layui-tab">
            <ul class="layui-tab-title">
                <li class="layui-this">用户登录</li>
                <li>用户注册</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <div class="layui-card">
                        <div class="layui-card-body">
                            <form id="loginform">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">用户名：</label>
                                    <div class="layui-input-block">
                                        <input type="text" id="uname" name="uname" required="required"
                                               autocomplete="off" placeholder="请输入用户名" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">密码：</label>
                                    <div class="layui-input-block">
                                        <input type="password" id="upwd" name="upwd" required="required"
                                               placeholder="请输入密码" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">验证码：</label>
                                    <div class="layui-input-inline">
                                        <input type="text" id="vcode" name="vcode" required="required"
                                               placeholder="请输入验证码" autocomplete="off" class="layui-input">
                                    </div>
                                    <div id="checkCode" onclick="refreshVcode()"></div>
                                </div>
                                <div class="layui-form-item" align="center">&nbsp;&nbsp;&nbsp;
                                    <label><a href="retrievePassword.html">忘记密码？</a></label><br/>
                                    <a class="layui-btn" href="javascript:login()">登录</a>
                                    <a class="layui-btn layui-btn-normal" href="javascript:reset()">重置</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="layui-tab-item">
                    <div class="layui-card">
                        <div class="layui-card-body">
                            <form id="signupform">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">用户名<span style="color: red">*</span>：</label>
                                    <div class="layui-input-block">
                                        <input type="text" id="signupname" name="signupname" required="required"
                                               placeholder="请输入用户名" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">密码<span style="color: red">*</span>：</label>
                                    <div class="layui-input-block">
                                        <input type="password" id="signuppwd" name="signuppwd" required="required"
                                               placeholder="请输入您要设置的密码，不少于六位" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">重复密码<span style="color: red">*</span>：</label>
                                    <div class="layui-input-block">
                                        <input type="password" id="signuppwd2" name="signuppwd2" required="required"
                                               placeholder="请重复您设置的密码" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">权限<span style="color: red">*</span>：</label>
                                    <div class="layui-input-inline">
                                        <select id="role" name="role" required="required">
                                            <option value="">请选择您对应岗位的权限</option>
                                            <option value="2">操作员</option>
                                            <option value="3">维护人员</option>
                                            <option value="4">内科</option>
                                            <option value="5">心肺科</option>
                                            <option value="6">耳鼻喉科</option>
                                            <option value="7">妇科</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">验证码：</label>
                                    <div class="layui-input-inline">
                                        <input type="text" id="vcode1" name="vcode1" required="required"
                                               placeholder="请输入验证码" autocomplete="off" class="layui-input">
                                    </div>
                                    <div id="checkCode1" onclick="refreshVcode()"></div>
                                </div>
                                <div class="layui-form-item" align="center">
                                    <a class="layui-btn" href="javascript:signup()">注册</a>
                                    <a class="layui-btn layui-btn-normal" href="javascript:reset()">重置</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</center>
<div align="center" class="layui-footer" style=" position: fixed;bottom:0;width: 100%">
    <span>&copy;2019-2020 天津融创软通 Copy Right</span>
</div>
</body>
<script type="text/javascript">
    $(function () {
        createCode(4);
        // alert("test");
    });

    function refreshVcode() {
        createCode(4);
    }

    function login() {
        var uname = $("#uname").val();
        if (uname.length == 0) {
            layer.tips("用户名不能为空!", "#uname", {tips: [2, '#FF0000'], time: 3000});
            return;
        }
        var upwd = $("#upwd").val();
        if (upwd.length == 0) {
            layer.tips("密码不能为空!", "#upwd", {tips: [2, '#FF0000'], time: 3000});
            return;
        }
        if (upwd.length < 6) {
            layer.tips("密码长度至少6位!", "#upwd", {tips: [2, '#FF0000'], time: 3000});
            return;
        }
        var code = document.getElementById("checkCode").innerHTML;
        var vcode = $("#vcode").val();
        if (vcode.length == 0) {
            layer.tips("验证码不能为空!", "#vcode", {tips: [1, '#FF0000'], time: 3000});
            return;
        }
        if (vcode.toUpperCase() == code.toUpperCase()) {
            $.ajax({
                url: "user/login.do",
                method: "post",
                cache: false,
                data: $("#loginform").serialize(),//表单序列化
                success: function (result) {
                    if (JSON.parse(result) == "success")
                        window.location.href = "admin.html";
                    else {
                        layer.msg("用户名或密码错误！", {icon: 2});
                    }
                },
                error: function (e) {
                    layer.msg("请求服务器失败！", {icon: 2});
                }
            });
        } else {
            layer.tips("验证码输入有误!", "#vcode", {tips: [1, '#FF0000'], time: 3000});
            return;
        }
    }
    //input键盘捕捉enter
    $("#uname,#upwd,#vcode").on("keydown", function (e) {
        if (e.keyCode == 13)
            login();
    });
    $("#signupname,#email,#signuppwd,#signuppwd2,#vcode1").on("keydown", function (e) {
        if (e.keyCode == 13)
            signup();
    });

    function signup() {
        var signupname = $("#signupname").val();
        if (signupname.length == 0) {
            layer.tips("注册用户名不能为空!", "#signupname", {tips: [2, '#FF0000'], time: 3000});
            return;
        }
        var signuppwd = $("#signuppwd").val();
        if (signuppwd.length == 0) {
            layer.tips("注册密码不能为空!", "#signuppwd", {tips: [2, '#FF0000'], time: 3000});
            return;
        }
        if (signuppwd.length < 6) {
            layer.tips("注册密码长度至少6位!", "#signuppwd", {tips: [2, '#FF0000'], time: 3000});
            return;
        }
        var signuppwd2 = $("#signuppwd2").val();
        if (signuppwd2.length == 0) {
            layer.tips("重复密码不能为空!", "#signuppwd2", {tips: [2, '#FF0000'], time: 3000});
            return;
        }
        if (signuppwd != signuppwd2) {
            layer.tips("密码输入不一致!", "#signuppwd2", {tips: [2, '#FF0000'], time: 3000});
            return;
        }
        var code1 = document.getElementById("checkCode1").innerHTML;
        var vcode1 = $("#vcode1").val();
        if (vcode1.length == 0) {
            layer.tips("验证码不能为空!", "#vcode1", {tips: [1, '#FF0000'], time: 3000});
            return;
        }
        if (vcode1.toUpperCase() == code1.toUpperCase()) {
            $.ajax({
                url: "user/signup.do",
                method: "get",
                cache: false,
                data: $("#signupform").serialize(),//表单序列化
                success: function (result) {
                    if (JSON.parse(result) == "success") {
                        layer.msg("注册成功！", {icon: 1});
                        window.location.href = "index.jsp";
                    } else {
                        layer.msg("注册失败！", {icon: 2});
                    }
                },
                error: function (e) {
                    layer.msg("请求服务器失败！", {icon: 2});
                }
            });
        } else {
            layer.tips("验证码输入有误!", "#vcode1", {tips: [1, '#FF0000'], time: 3000});
            return;
        }

    }

    function reset() {
        $("#loginform,#signupform").find('input[type=text],input[type=password],input[type=email]').each(function () {
            $(this).val('');
        });
    }
</script>
</html>