<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <script src="/js/jquery.js"></script>
</head>
<head>
    <title>二维码页面</title>
    <meta http-equiv="refresh" content="3">
    <script>
        $(document).ready(function () {
            var type =${type};
            $.ajax({
                url: "http://localhost:8080/sendmessage",    //请求的url地址
//                dataType: "json",   //返回格式为json
                data: { "type": type},    //参数值
                type: "POST",   //请求方式
                success: function () {
                    //请求成功时处理
            alert("sendmessage");
                },
                error: function () {
                    //请求出错处理
                    alert("err");
                }
            });
    });
    </script>
</head>
<body>
<img src="${qrcode}">
</body>
</html>
