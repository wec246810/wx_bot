<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <c:import url="header.jsp"/>
    <c:import url="left.jsp"/>
</head>
<head>
    <title>我的账户</title>
    <script>

        $(document).ready(function () {
            var username = "${sessionScope.get("username")}";
            var password = "${sessionScope.get("password")}";
            $.ajax({
                url: "http://localhost:8080/MyTime",    //请求的url地址
                dataType: "json",   //返回格式为json
                data: {"username": username, "password": password},    //参数值
                type: "POST",   //请求方式
                beforeSend: function () {
                },
                success: function (req) {
                    //请求成功时处理
                    $("#result").html(req.error);
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
    </script>
</head>

<body>
<section class="rt_wrap content mCustomScrollbar">
    <div class="rt_content">
        <section>
            <%--<h2><strong style="color:grey;">页面标题及表格/分页（根据具体情况列入重点，切勿放置可扩展内容不定的数据）</strong></h2>--%>
            授权日期：111
            <div class="page_title" style="padding-top: 20px">
                <p id="result"></p>
            </div>
        </section>
        <section>
            <%--<h2><strong style="color:grey;">页面标题及表格/分页（根据具体情况列入重点，切勿放置可扩展内容不定的数据）</strong></h2>--%>
            余额：
            <div class="page_title" style="padding-top: 20px">
                <p id="money"></p>
            </div>
        </section>
    </div>
</section>
</body>
</html>