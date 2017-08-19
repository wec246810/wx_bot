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
    <%--<script>--%>
    //        // 高效的字符串拼接函数，替代使用+号的低效率拼接，使用时直接复制即可
    //        function StringBuffer() {
    //            this.__strings__ = new Array();
    //        }
    //        StringBuffer.prototype.append = function (str) {
    //            this.__strings__.push(str);
    //            return this;    //方便链式操作
    //        }
    //        StringBuffer.prototype.toString = function () {
    //            return this.__strings__.join("");
    //        }
    //        $(document).ready(function () {
    //            var pageNum =1 ;
    //            var pageSize =10;
    //            $.ajax({
    //                url: "http://localhost:8080/getSendRecord",    //请求的url地址
    //                dataType: "json",   //返回格式为json
    //                data: { "pageNum": pageNum,"pageSize":pageSize},    //参数值
    //                type: "POST",   //请求方式
    //                success: function (req) {
    //                    //请求成功时处理
    //                    var list=new StringBuffer();//自定义的拼接字符串函数，高效。
    //                    $.each(req,function(i,actObj){//i表示循环的下标，actObj表示循环的对象，可自定义名字
    //                        list.append("<tr>");
    ////                        list.append("<td>"+actObj.id+"</td>");
    //                        list.append("<td style=\"width:265px;\"><div class=\"cut_title ellipsis\">"+actObj.content+"</td>");
    //                        list.append("<td>"+actObj.createTime+"</td>");
    //                        list.append("<td>"+actObj.userNum+"</td>");
    ////                        list.append("<td>"+actObj.createtime+"</td>");
    ////                        list.append("<td>"+actObj.title+"</td>");
    ////                        list.append("<td>"+actObj.content+"</td>");
    ////                        list.append("<td>"+actObj.applyNum+"</td>");
    //                        list.append("<td>");
        //                        list.append("<a href=\"#\">表内链接</a>");
        //                        list.append("<a href=\"#\" class=\"inner_btn\">表内按钮</a>");
        //                        list.append("</td>");
    //                        list.append("</tr>");
    //                    });
    //                    var head="    <table class=\"table\">\n" +
    //                        "                    <tr>\n" +
        //                        "                        <th>发送内容</th>\n" +
        //                        "                        <th>发送时间</th>\n" +
        //                        "                        <th>扫描人数</th>\n" +
        //                        "                    </tr>";
    //                    $("#act_table").html(head+list.toString());
    //                },
    //                error: function () {
    //                    //请求出错处理
    //                    alert("err");
    //                }
    //            });
    //        });
    <%--</script>--%>
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
                <div class="page_title"style="padding-top: 20px">
                    <h2 class="fl">发送记录</h2>
                    <%--<a class="fr top_rt_btn">右侧按钮</a>--%>
                </div>
                <table class="table" id="act_table">
                    <%--<tr>--%>
                    <%--<th>发送内容</th>--%>
                    <%--<th>发送时间</th>--%>
                    <%--<th>扫描人数</th>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                    <%--<td style="width:265px;"><div class="cut_title ellipsis">${sr.id}</div></td>--%>
                    <%--<td>内容二</td>--%>
                    <%--<td>内容三</td>--%>
                    <%--<td>--%>
                    <%--<a href="#">表内链接</a>--%>
                    <%--<a href="#" class="inner_btn">表内按钮</a>--%>
                    <%--</td>--%>
                    <%--</tr>--%>
                </table>
                <aside class="paging">
                    <c:choose>
                        <c:when test="${page>1}">
                            <a href="list.do?page=${page-1}">上一页</a>
                        </c:when>
                        <c:otherwise>
                            上一页
                        </c:otherwise>
                    </c:choose>
                    第${page}页
                    <c:choose>
                        <c:when test="${page<totalPage}">
                            <a href="list.do?page=${page+1}">下一页</a>
                        </c:when>
                        <c:otherwise>
                            下一页
                        </c:otherwise>
                    </c:choose>
                    共${totalPage}页
                </aside>
            </section>
        </div>
    </div>
</section>
</body>
</html>