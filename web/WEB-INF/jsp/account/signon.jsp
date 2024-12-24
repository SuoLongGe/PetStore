
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册界面</title>
    <link rel="stylesheet" type="text/css" href="css/user.css">
</head>
<body>
  <div class="w1">
      <a href="mainForm" class="link1 logo">
          <img src="images/top-bar.gif" />
          Mystore
      </a>
      <br/>
    <h1 style="font-size:36px;font-weight: normal;text-align: left ">Welcome to PetStore</h1>
    <p style="font-size: 20px">Need a user name and password?  <a href="registerForm" class="link">Register Now!</a></p>
    <div class="user-content">


    <form action="signOn" class="user-form" method="post" onsubmit="return loginSuccess()">
        <p style="font-size: 20px; font-weight: bold">·Please enter your username and password.</p>
        <c:if test="${requestScope.signOnMsg !=null }">
            <p><font color="red">${requestScope.signOnMsg}</font></p>
        </c:if>

        <div class="user-form-item">
            <p style="font-family:'Palatino Linotype'; color: #449c6c ;font-size: 15px; margin-bottom: auto">Username</p>
            <input type="text" class="user-form-input" name="username" id="username"  autocomplete="off">
        </div>
        <div  id="feedback1" style="color: red" > </div>

        <div class="user-form-item">
            <p style="font-family:'Palatino Linotype'; color: #449c6c ;font-size: 15px; margin-bottom: auto">Password</p>
            <input type="password" class="user-form-input" name="password" id="password"  autocomplete="off">
        </div>
        <div  id="feedback2" style="color: red" > </div>
        <div  id="feedback4" style="color: red" > </div>
        <div  id="feedback5" style="color: green" > </div>

        <div class="user-form-item">
            <p style="font-family:'Palatino Linotype'; color: #449c6c ;font-size: 15px; margin-bottom: auto">Captcha(验证码)</p>
            <input style="width: 408px" type="text" class="user-form-input" name="captcha" id="captcha"  autocomplete="off">
            <img src="signonForm?action=captcha" alt="CAPTCHA" id="captchaImage" style="vertical-align: middle;">
        </div>
        <div  id="feedback3" style="color: red" > </div><div  id="feedback6" style="color: green" > </div>
        <br/>


        <br/>
        <br/>
        <br/>

        <input type="submit" value="Login" style="margin-left:20px;width: 200px; padding: 10px 25px; font-size: 18px; background-color: #215496; color: white; border: none; border-radius: 5px; cursor: pointer;">

        <div id="MainImage">
            <div id="MainImageContent">
                <img  src="images/black.gif" align="middle"
                      usemap="#estoremap" width="680" style="position: absolute;left: 1000px; top: -250px;"/></div>
        </div>

        <script>
            // 允许用户刷新验证码
            document.getElementById('captchaImage').onclick = function() {
                this.src = 'SignOnFormServlet?action=captcha&rand=' + Math.random();
            };
        </script>
    </form>


   </div>
  </div>


<script src="js/signon.js"> </script>
<script>
    function registerSuccess() {
        alert("Account registration successful! Please complete the login.");
    }
</script>
</body>
</html>

