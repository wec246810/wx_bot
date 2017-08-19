<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <head>
        <meta charset="utf-8"/>
        <title>后台管理系统</title>
        <meta name="author" content="Y.S.K" />
        <link rel="stylesheet" type="text/css" href="/css/style.css" />
        <link rel="stylesheet" type="text/css" href="/css/jquery.mCustomScrollbar.css" />
        <!--[if lt IE 9]>
        <script src="/js/html5.js"></script>
        <![endif]-->
        <script src="/js/jquery.js"></script>
        <script src="/js/jquery.mCustomScrollbar.concat.min.js"></script>
        <script src="/js/Particleground.js"></script>

    </head>
</head>
<body>
<!--aside nav-->
<aside class="lt_aside_nav content mCustomScrollbar">
    <h2><a href="/touse">操作面板</a></h2>
    <ul>
        <!--
         <li>
          <dl>
           <dt>商品信息</dt>
               当前链接则添加class:active
           <dd><a href="#" class="active">个人信息</a></dd>
           <dd><a href="#">账户信息</a></dd>
           <dd><a href="#">发送记录</a></dd>
           <dd><a href="#">修改密码</a></dd>
          </dl>
         </li> -->
        <li>
            <dl>
                <dt>个人信息</dt>
                <dd><a href="/touse">开始使用</a></dd>
                <dd><a href="/mycount">账户信息</a></dd>
                <dd><a href="/sendRecord/1/8">发送记录</a></dd>
                <dd><a href="/useCDK">使用CDK</a></dd>
                <dd><a href="/topay">充值余额</a></dd>
                <dd><a href="/updatepwd">修改密码</a></dd>
            </dl>
        </li>
    </ul>
</aside>
</body>
</html>
