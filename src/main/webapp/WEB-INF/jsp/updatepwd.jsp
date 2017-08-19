<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <c:import url="header.jsp"/>
    <c:import url="left.jsp"/>
</head>
<head>
    <title>更新密码</title>
    <script>
        $(document).ready(function () {
            $("#but").click(function () {
                var newpassword=document.getElementById("newpassword").value;
                var newpassword2=document.getElementById("newpassword2").value;
                var oldpassword = document.getElementById("oldpassword").value;
                if( newpassword==newpassword2&&newpassword!=""&&newpassword2!=""&&oldpassword!=""){
                $.ajax({
                    url: "http://localhost:8080/updatepassword",    //请求的url地址
                    dataType: "json",   //返回格式为json
                    data: {"oldpassword": oldpassword, "newpassword": newpassword},    //参数值
                    type: "POST",   //请求方式
                    success: function (req) {
                        //请求成功时处理
                        if (req.error.indexOf("成功") > 0) {
                            alert("密码修改成功，请重新登录");
                            self.location="/login";
                        } else {
                          alert("修改密码失败！");
                        }
                    },
                    error: function () {
                        //请求出错处理
                        alert("err");
                    }
                });}else if(newpassword!=newpassword2){
                    alert(" 两次输入密码不一致！");
                }else{
                    alert("请认真填写上面的空白部分");
                }
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
        <em>原密码 :</em>&nbsp;&nbsp;&nbsp;<input type="text" id="oldpassword" class="textbox textbox_225" placeholder="原始密码"/><br><br><br>
        <em>新密码 :</em>&nbsp;&nbsp;&nbsp;<input type="password" id="newpassword" class="textbox textbox_225" placeholder="新密码"/><br><br><br>
        <em>新密码 :</em>&nbsp;&nbsp;&nbsp;<input type="password" id="newpassword2" class="textbox textbox_225" placeholder="确认新密码"/><br><br><br>
        <input type="button" id="but" value="确认" class="group_btn"/>
    </section>
</div>
    </div></section>
</body>
</html>
