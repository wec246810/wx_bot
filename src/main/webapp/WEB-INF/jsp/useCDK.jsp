<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <c:import url="header.jsp"/>
    <c:import url="left.jsp"/>
</head>
<head>
    <title>充值页面</title>
    <script>
        $(document).ready(function () {
            $("#but").click(function () {
                var username = "${sessionScope.get("username")}";
                var cdkValue = document.getElementById("cdkValue").value;
                $.ajax({
                    url: "http://localhost:8080/UCDK",    //请求的url地址
                    dataType: "json",   //返回格式为json
                    data: {"username": username, "cdkValue": cdkValue},    //参数值
                    type: "POST",   //请求方式
                    beforeSend: function () {
                    },
                    success: function (req) {
                        if(req.error==username){
                            alert("充值成功！");
                        }else{
                            alert(req.error);
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
<section class="rt_wrap content mCustomScrollbar">
    <div class="rt_content">
        <div>
            <section style="text-align: center;padding-top: 30px">
                <h2><strong style="color:grey;"></strong></h2>
                <em>CDK :</em>&nbsp;&nbsp;&nbsp;<input type="text" class="textbox textbox_225" id="cdkValue" placeholder="请输入CDK"/><br><br><br>
                <input type="button" value="确认充值" id="but" class="group_btn"/>
            </section>
        </div>
    </div>
</section>
</body>
</html>
