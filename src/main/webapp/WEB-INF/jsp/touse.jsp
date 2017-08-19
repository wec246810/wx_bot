<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <c:import url="header.jsp"/>
    <c:import url="left.jsp"/>
</head>
<head>
    <title>使用页面</title>
    <script>
        $(document).ready(function () {
            var url="/qrcode/";
            $("#but").click(function () {
                <%--<c:set value= var="aaa" scope="session"/>--%>
                if($("input[name='man']").is(':checked')){
                    url=url+"1";
                }if($("input[name='woman']").is(':checked')){
                    url=url+"2";
                }if($("input[name='group']").is(':checked')){
                    url=url+"3";
                }if($("input[name='man']").is(':checked')||$("input[name='woman']").is(':checked')||$("input[name='group']").is(':checked')&$.trim($("#content").val()).length > 0){
                    var content =$.trim($("#content").val());
                    <%--<c:set value="${content}" scope="session"/>--%>
                    $.ajax({
                        url: "http://localhost:8080/getcontent",    //请求的url地址
//                        dataType: "html",   //返回格式为json
                        data: {"content": content},    //参数值
                        type: "POST",   //请求方式
                        success: function (req) {
                            alert(content);
                            self.location=url;
                        },
                        error: function () {
                            //请求出错处理
                            alert("err");
                        }
                    });

                }else {
                    alert("请至少选择一个发送对象并输入发送内容");
                }
            });
        });
    </script>
</head>

<body>
<section class="rt_wrap content mCustomScrollbar">
    <div class="rt_content">
        <div>
            <input type="text" id="content" name="content" class="textbox textbox_295" placeholder="输入需要发送的内容..."/>
            <%--<c:set value="$"--%>
            <input type="button" id="but" value="生成二维码" class="link_btn"/>
        </div>
        <div>
            <input type="checkbox" name="man" value="man" /> 男
            <input type="checkbox" name="woman" value="woman" /> 女
            <input type="checkbox" name="group" value="group" /> 群聊
        </div>

    </div>
</section>
</body>
</html>
