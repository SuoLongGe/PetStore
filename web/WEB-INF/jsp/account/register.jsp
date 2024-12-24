
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册界面</title>
    <link rel="stylesheet" type="text/css" href="css/user.css">
</head>
<body>
<div class="w">
    <a href="mainForm" class="link1 logo">
        <img src="images/top-bar.gif" />
        Mystore</a>
    <br/>
    <h1 style="font-size:36px;font-weight: normal;text-align: left ">Welcome to PetStore</h1>
    <p style="font-size: 20px">Already have an account? <a href="signonForm" class="link">Login</a></p>
    <div class="user-content">

        <form action="register" class="user-form" method="post" onsubmit="return registerSuccess()">

                        <c:if test="${requestScope.registerMsg != null}">
                        <div class="user-form-error">
                            <p style="font-size: 20px; color: red;" > ${requestScope.registerMsg}</p>
                        </div>
                        </c:if>

            <div class="user-form-item">
                <p style="font-family:'Palatino Linotype'; color: #449c6c ;font-size: 15px; margin-bottom: auto">Username</p>
                <input type="text" class="user-form-input" name="username" id="username"  autocomplete="off">
                <div  id="feedback1" style="color: red" > </div>
            </div>




            <div class="user-form-item">
                <p style="font-family:'Palatino Linotype'; color: #449c6c;font-size: 15px; margin-bottom: auto">Password</p>
                <input type="password" class="user-form-input" name="password" id="password"  autocomplete="off">
                <div id="feedback2" style="color: red" > </div>
            </div>


            <div class="user-form-item">
                <p style="font-family:'Palatino Linotype'; color: #449c6c;font-size: 15px; margin-bottom: auto">RepeatPassword</p>
                <input type="password" class="user-form-input" name="repeatpassword" id="repeatpassword"  autocomplete="off">
                <div id="feedback3" style="color: red" > </div>
            </div>



            <%@ include file="IncludeAccountFields.jsp"%>
            <div>&nbsp;</div>
            <div>&nbsp;</div>
            <p style="font-size: 18px">· By creating an account, you agree to the Terms of use and Privacy Poicy</p>
            <p style="font-size: 20px">· Already have an account? <a href="signonForm" class="link">Login</a></p>
            <div class="user-form-item">
                <input type="submit" value="注册" class="user-form-submit">
            </div>

            <div id="MainImage">
                <div id="MainImageContent">
                    <img  src="images/black.gif" align="middle"
                          usemap="#estoremap" width="680" style="position: absolute;left: 1000px; top: -250px;"/></div>
                    <img  src="images/black.gif" align="middle"
                      usemap="#estoremap" width="680" style="position: absolute;left: 1000px; top: 500px;"/></div>
            </div>

        </form>
    </div>
</div>
<script src="js/check-userinfo.js"> </script>
<script>
    function registerSuccess() {
        alert("Account registration successful! Please complete the login.");
    }
</script>
</body>
</html>
