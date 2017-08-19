<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="header.jsp"/>
    <c:import url="left.jsp"/>
</head>
<head>
    <title>充值页面</title>
</head>
<body>
<section class="rt_wrap content mCustomScrollbar">
    <div class="rt_content">
        <section>
            <form action="/paypage" method="post">
                <input type="text" class="textbox" name="price" placeholder="请输入充值金额..."/>
                <input type="submit" class="link_btn" value="充值"/>
            </form>
        </section>
    </div>
</section>

</body>
</html>
