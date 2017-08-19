<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" class="com.ysk.entity.User"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:out value="&lt要显示的数据对象（未使用转义字符）&gt" escapeXml="true" default="默认值"></c:out><br/>
<c:out value="&lt要显示的数据对象（使用转义字符）&gt" escapeXml="false" default="默认值"></c:out><br/>
<body>
<c:set value="张三" var="name1" scope="session"></c:set>
<c:set value="李四" var="name2" scope="session"></c:set>
<c:set value="赵五" target="${user}" property="username"></c:set>
<c:set target="${user}" property="password">19</c:set>
<li>从session中得到的值：${sessionScope.name1}</li>
<li>从session中得到的值：${sessionScope.name2}</li>
<li>从Bean中获取对象person的name值：<c:out value="${user.userName}"></c:out></li>
<li>从Bean中获取对象person的密码值：<c:out value="${user.password}"></c:out></li>
<li>${sessionScope.get("username")}</li>
<c:set value="杨尚昆" var="ysk" scope="application"/>
<c:out value="${applicationScope.ysk}"/>

<c:set value="赵五" target="${user}" property="userName"></c:set>
<c:set target="${user}" property="password">19</c:set>
<c:if test="${user.userName == '赵武'}" var="name1"></c:if>
<c:out value="name1的值：${name1}"></c:out><br/>
<c:if test="${user.userName == '赵五'}" var="name2"></c:if>
<c:out value="name2的值：${name2}"></c:out>


<c:set var="score">85</c:set>
<c:choose>
    <c:when test="${score>=90}">
        你的成绩为优秀！
    </c:when>
    <c:when test="${score>=70&&score<90}">
        您的成绩为良好!
    </c:when>
    <c:when test="${score>60&&score<70}">
        您的成绩为及格
    </c:when>
    <c:otherwise>
        对不起，您没有通过考试！
    </c:otherwise>
</c:choose>
<%
    List a = new ArrayList();
    a.add("贝贝");
    a.add("晶晶");
    a.add("欢欢");
    a.add("莹莹");
    a.add("妮妮");
    request.setAttribute("a", a);
%>
<B><c:out value="不指定begin和end的迭代："/></B><br>
<c:forEach var="fuwa" items="${a}">
    &nbsp;<c:out value="${fuwa}"/><br>
</c:forEach>
<B><c:out value="指定begin和end的迭代："/></B><br>
<c:forEach var="fuwa" items="${a}" begin="1" end="3" step="2">
    &nbsp;<c:out value="${fuwa}"/><br>
</c:forEach>
<B><c:out value="输出整个迭代的信息："/></B><br>
<c:forEach var="fuwa" items="${a}" begin="3" end="4" step="1" varStatus="s">
    &nbsp;<c:out value="${fuwa}"/>的四种属性：<br>
    &nbsp;&nbsp;所在位置，即索引：<c:out value="${s.index}"/><br>
    &nbsp;&nbsp;总共已迭代的次数：<c:out value="${s.count}"/><br>
    &nbsp;&nbsp;是否为第一个位置：<c:out value="${s.first}"/><br>
    &nbsp;&nbsp;是否为最后一个位置：<c:out value="${s.last}"/><br>
</c:forEach>
<c:forTokens items="北、京、欢、迎、您" delims="、" var="c1">
    <c:out value="${c1}"></c:out>
</c:forTokens>
<br>
<c:forTokens items="123-4567-8854" delims="-" var="t">
    <c:out value="${t}"></c:out>
</c:forTokens>
<br>
<c:forTokens items="1*2*3*4*5*6*7" delims="*" begin="1" end="3"
             var="n" varStatus="s">
    &nbsp;<c:out value="${n}"/>的四种属性：<br>
    &nbsp;&nbsp;所在位置，即索引：<c:out value="${s.index}"/>
    <br>
    &nbsp;&nbsp;总共已迭代的次数：<c:out value="${s.count}"/>
    <br>
    &nbsp;&nbsp;是否为第一个位置：<c:out value="${s.first}"/>
    <br>
    &nbsp;&nbsp;是否为最后一个位置：<c:out value="${s.last}"/>
    <br>
</c:forTokens>
<c:catch var="error1">
    <c:import url="http://www.baidu.com" charEncoding="utf-8"/>
</c:catch>
<c:out value="${error1}"></c:out>
<hr>
<h4>
    <c:out value="相对路径引用的实例，引用本应用中的文件"/>
</h4>
<c:catch>
    <c:import url="/MyTime" charEncoding="utf-8"/>
</c:catch>
<hr>
<h4>
    <c:out value="使用字符串输出、相对路径引用的实例，并保存在session范围内"/>
</h4>
<c:catch var="error3">
    <c:import var="myurl" url="/mycount" scope="session" charEncoding="utf-8"></c:import>
    <c:out value="${myurl}"></c:out>
    <c:out value="${myurl}"/>
</c:catch>
<c:out value="${error3}"></c:out>

</body>
</body>
</html>
