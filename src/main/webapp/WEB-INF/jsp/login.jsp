<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>后台登录</title>
    <meta name="author" content="DeathGhost"/>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
    <style>
        body {
            height: 100%;
            background: #16a085;
            overflow: hidden;
        }

        canvas {
            z-index: -1;
            position: absolute;
        }
    </style>
    <script src="/js/jquery.js"></script>
    <script src="/js/verificationNumbers.js"></script>
    <script src="/js/Particleground.js"></script>
    <script>
        $(document).ready(function () {
            //粒子背景特效
            $('body').particleground({
                dotColor: '#5cbdaa',
                lineColor: '#5cbdaa'
            });

            $("#but").click(function () {
                var username = document.getElementById("username").value;
                var password = document.getElementById("password").value;
                $.ajax({
                    url: "http://localhost:8080/check",    //请求的url地址
                    dataType: "json",   //返回格式为json
                    data: {"username": username, "password": password},    //参数值
                    type: "POST",   //请求方式
                    beforeSend: function () {
                    },
                    success: function (req) {
                        //请求成功时处理
                        if (req.error == "0") {
                            self.location = "/index";
                        } else {
                            $("#result").html(req.error);
                        }
                    },
                    complete: function () {
                        //请求完成的处理
                        //alert("com");
                    },
                    error: function () {
                        //请求出错处理
                        alert("err");
                    }
                });
            });
        });
    </script>
</head>
<body>
<dl class="admin_login">
    <dt>
        <strong>微信二维码群发系统</strong>
        <em>wxqf System</em>
    </dt>
    <dd class="user_icon">
        <input type="text" id="username" name="username" placeholder="账号" class="login_txtbx"/>
    </dd>
    <dd class="pwd_icon">
        <input type="password" id="password" name="password" placeholder="密码" class="login_txtbx"/>
    </dd>
    <dd>
        <input type="button" id="but" value="立即登陆" class="submit_btn"/>
    </dd>
    <dd>
        <p id="result"></p>
        <p>© Y.S.K 版权所有</p>
        <p></p>
    </dd>
</dl>
</body>
</html>