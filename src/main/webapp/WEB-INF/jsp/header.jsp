<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<header>
    <h1><img src="/images/admin_logo.png"/></h1>
    <ul class="rt_nav">
        <li><a href="/touse" target="_blank" class="website_icon">站点首页</a></li>
        <li><a href="/mycount" class="admin_icon"><%=session.getAttribute("username")%></a></li>
        <li><a href="/mycount" class="set_icon">授权日期</a></li>
        <li><a href="/logout" class="quit_icon">安全退出</a></li>
    </ul>
</header>
<body>
</body>
</html>
