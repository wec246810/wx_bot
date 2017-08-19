<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <c:import url="header.jsp"/>
    <c:import url="left.jsp"/>
</head>
<head>
    <title>发送记录页面</title>
    <script>
        <%--高效的字符串拼接函数，替代使用+号的低效率拼接，使用时直接复制即可--%>

        function StringBuffer() {
            this.__strings__ = new Array();
        }

        StringBuffer.prototype.append = function (str) {
            this.__strings__.push(str);
            return this;    //方便链式操作
        }
        StringBuffer.prototype.toString = function () {
            return this.__strings__.join("");
        }
        $(document).ready(function () {
            var pageNum = "${sessionScope.get("pageNum")}";
            var pageSize = "${sessionScope.get("pageSize")}";
            $.ajax({
                url: "http://localhost:8080/getSendRecord",    //请求的url地址
                dataType: "json",   //返回格式为json
                data: {"pageNum": pageNum, "pageSize": pageSize},    //参数值
                type: "POST",   //请求方式
                success: function (req) {
                    //请求成功时处理
                    var list = new StringBuffer();//自定义的拼接字符串函数，高效。
                    $.each(req, function (i, sendRecord) {//i表示循环的下标，sendRecord表示循环的对象，可自定义名字
                        //sendRecord.createTime
                        var createtime = new Date(sendRecord.createTime).toLocaleString('chinese', {hour12: false});
                        list.append("<tr>");
                        list.append("<td>" + sendRecord.id + "</td>");
                        list.append("<td style=\"width:265px;\"><div class=\"cut_title ellipsis\">" + sendRecord.content + "</td>");
                        list.append("<td>" + createtime + "</td>");
                        list.append("<td>" + sendRecord.userNum + "</td>");
                        list.append("<td>");
                        list.append("<a href=\"#\">表内链接</a>");
                        list.append("<a href=\"#\" class=\"inner_btn\">表内按钮</a>");
                        list.append("</td>");
                        list.append("</tr>");
                    });
                    var head = "    <table class=\"table\" id=\"act_table\">\n" +
                        "                    <tr>\n" +
                        "                        <th>发送ID</th>\n" +
                        "                        <th>发送内容</th>\n" +
                        "                        <th>发送时间</th>\n" +
                        "                        <th>扫描人数</th>\n" +
                        "                        <th>操作</th>\n" +
                        "                    </tr>";
                    $("#act_table").html(head + list.toString());
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
        <div>
            <section style="padding-top: 20px">
                <input type="text" class="textbox textbox_295" placeholder="输入搜索内容"/>
                <input type="button" value="搜索" class="group_btn"/>
            </section>
            <section>
                <div class="page_title" style="padding-top: 20px">
                    <h2 class="fl">发送记录</h2>
                    <%--<a class="fr top_rt_btn">右侧按钮</a>--%>
                </div>
                <table class="table" id="act_table">

                </table>
                <aside class="paging">
                    <c:choose>
                        <c:when test="${pageNum>1}">
                            <a href="/sendRecord/${pageNum-1}/${pageSize}">上一页</a>
                        </c:when>
                        <c:otherwise>
                            上一页
                        </c:otherwise>
                    </c:choose>
                    第${pageNum}页
                    <c:choose>
                        <c:when test="${pageNum<sessionScope.totalPage}">
                            <a href="/sendRecord/${pageNum+1}/${pageSize}">下一页</a>
                        </c:when>
                        <c:otherwise>
                            下一页
                        </c:otherwise>
                    </c:choose>
                    共${sessionScope.totalPage}页
                </aside>
            </section>
        </div>
    </div>
</section>
</body>
</html>