
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册界面</title>
    <link rel="stylesheet" type="text/css" href="css/user.css">
</head>
<body>
<div class="w">
    <h1>用户注册</h1>
    <div class="user-content">

        <form action="register" class="user-form" method="post" onsubmit="return registerSuccess()">

                        <c:if test="${requestScope.registerMsg != null}">
                        <div class="user-form-error">
                            <p style="font-size: 20px; color: red;" > ${requestScope.registerMsg}</p>
                        </div>
                        </c:if>

            <div class="user-form-item">
                <label for="username" class="user-form-label">
                    <i class="fa-solid fa-user"></i>
                </label>
                <input type="text" class="user-form-input" name="username" id="username" placeholder="请输入用户名" autocomplete="off">
            </div>

            <div class="user-form-item">
                <label for="password" class="user-form-label">
                    <i class="fa-solid fa-lock"></i>
                </label>
                <input type="password" class="user-form-input" name="password" id="password" placeholder="请输入密码" autocomplete="off">
            </div>

            <div class="user-form-item">
                <label for="password" class="user-form-label">
                    <i class="fa-solid fa-lock"></i>
                </label>
                <input type="password" class="user-form-input" name="repeatpassword" id="repeatpassword" placeholder="请再次输入密码" autocomplete="off">
            </div>

<%--            <div class="user-form-item">--%>
<%--                <label for="password" class="user-form-label">--%>
<%--                    <i class="fa-solid fa-lock"></i>--%>
<%--                </label>--%>
<%--                <input type="text" class="user-form-input" name="email" id="email" placeholder="请输入邮箱" autocomplete="off">--%>
<%--            </div>--%>



            <%@ include file="IncludeAccountFields.jsp"%>

            <div class="user-form-item">
                <input type="submit" value="注册" class="user-form-submit">
            </div>
            <div class="user-form-link">
                <a href="signonForm" class="link">返回登录>></a>
            </div>
        </form>
    </div>
</div>
<script>
    function registerSuccess() {
        alert("Account registration successful! Please complete the login.");
    }
</script>
</body>
</html>
