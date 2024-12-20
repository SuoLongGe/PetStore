
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更改用户信息</title>
    <link rel="stylesheet" type="text/css" href="css/user.css">
</head>
<body>
<div class="w">
    <a href="mainForm" class="link1 logo">
        <img src="images/top-bar.gif" />
        Mystore</a>
    <h1 style="text-align: left;margin-top: 50px">Edit Your Informations</h1>
    <div class="user-content">

        <c:if test="${requestScope.registerMsg != null}">
            <div class="user-form-error">
                <p style="font-size: 20px;"> ${requestScope.editMsg}</p>
            </div>
        </c:if>
        <form action="editAccount" class="user-form" method="post">



            <div class="user-form-item">
                <p style="font-family:'Palatino Linotype'; color: #449c6c ;font-size: 25px; margin-bottom: auto">User ID:&nbsp;${sessionScope.loginAccount.username}</p>
<%--                <p style="font-family:'Palatino Linotype'; color: #449c6c ;font-size: 25px; margin-bottom: auto">&nbsp;${sessionScope.loginAccount.username}</p>--%>
            </div>

            <div class="user-form-item">
                <p style="font-family:'Palatino Linotype'; color: #449c6c ;font-size: 15px; margin-bottom: auto">New password:</p>
                <input type="password" class="user-form-input" name="newpassword" id="newpassword" placeholder="请输入新密码" autocomplete="off">
                <div  id="feedback1" style="color: red" > </div>
            </div>

            <div class="user-form-item">
                <p style="font-family:'Palatino Linotype'; color: #449c6c ;font-size: 15px; margin-bottom: auto">Repeat password:</p>
                <input type="password" class="user-form-input" name="newrepeatpassword" id="newrepeatpassword" placeholder="请再次输入新密码" autocomplete="off">
                <div  id="feedback2" style="color: red" > </div>
            </div>

            <h3 style="font-size: 25px">Account Information</h3>
            <div class="user-form-item1">
                <p style="color: #344978;font-size: 20px">First name</p>
                <input type="text" class="user-form-input1" name="newfirstName" id="newfirstName" value="${sessionScope.loginAccount.firstName}" autocomplete="off">
                <div  id="feedback4" style="color: red" > </div>
            </div>

            <div class="user-form-item1">
                <p style="color: #344978;font-size: 20px">Last name</p>
                <input type="text" class="user-form-input1" name="newlastName" id="newlastName" value="${sessionScope.loginAccount.lastName}" autocomplete="off">
                <div  id="feedback5" style="color: red" > </div>
            </div>

            <div class="user-form-item1">
                <p style="color: #344978;font-size: 20px; margin-right: 43px;">Email</p>
                <input type="text" class="user-form-input1" name="newemail" id="newemail" value="${sessionScope.loginAccount.email}" autocomplete="off">
                <div  id="feedback6" style="color: red" > </div>
            </div>

            <div class="user-form-item1">
                <p style="color: #344978;font-size: 20px; margin-right: 35px;">Phone     </p>
                <input type="text" class="user-form-input1" name="newphone" id="newphone" value="${sessionScope.loginAccount.phone}" autocomplete="off">
                <div  id="feedback7" style="color: red" > </div>
            </div>

            <div class="user-form-item1">
                <p style="color: #344978;font-size: 20px">Address 1 </p>
                <input type="text" class="user-form-input1" name="newaddress1" id="newaddress1" value="${sessionScope.loginAccount.address1}" autocomplete="off">
                <div  id="feedback8" style="color: red" > </div>
            </div>

            <div class="user-form-item1">
                <p style="color: #344978;font-size: 20px">Address 2</p>
                <input type="text" class="user-form-input1" name="newaddress2" id="newaddress2" value="${sessionScope.loginAccount.address2}" autocomplete="off">

            </div>

            <div class="user-form-item1">
                <p style="color: #344978;font-size: 20px; margin-right: 51px;">City     </p>
                <input type="text" class="user-form-input1" name="newcity" id="newcity" value="${sessionScope.loginAccount.city}" autocomplete="off">
                <div  id="feedback9" style="color: red" > </div>
            </div>

            <div class="user-form-item1">
                <p style="color: #344978;font-size: 20px; margin-right: 40px;">State    </p>
                <input type="text" class="user-form-input1" name="newstate" id="newstate" value="${sessionScope.loginAccount.state}" autocomplete="off">
                <div  id="feedback10" style="color: red" > </div>
            </div>

            <div class="user-form-item1">
                <p style="color: #344978;font-size: 20px; margin-right: 55px;">Zip     </p>
                <input type="text" class="user-form-input1" name="newzip" id="newzip" value="${sessionScope.loginAccount.zip}" autocomplete="off">
                <div  id="feedback11" style="color: red" > </div>
            </div>

            <div class="user-form-item1">
                <p style="color: #344978;font-size: 20px; margin-right: 15px;">Country </p>
                <input type="text" class="user-form-input1" name="newcountry" id="newcountry" value="${sessionScope.loginAccount.country}" autocomplete="off">
                <div  id="feedback12" style="color: red" > </div>
            </div>

            <h3 style="font-size: 25px">Profile Information</h3>

            <div class="user-form-item1">
                <p style="color: #344978;font-size: 20px; margin-right: 3px;">Language Preference</p>
                <select id="language" name="newaccount.languagePreference" style="border-radius:8px; padding: 8px 30px; font-size: 20px">
                    <option value="English" >English</option>
                    <option value="Chinese" >Spanish</option>
                    <option value="French" >French</option>
                    <option value="German" >German</option>
                </select>
            </div>

            <div class="user-form-item1">
                <p style="color: #344978;font-size: 20px; margin-right: 13px;">Favourite Category</p>
                <select id="favouriteCategoryId" name="newaccount.favouriteCategoryId" style="border-radius:8px;padding: 8px 30px; font-size: 20px">
                    <option value="FISH" >Fish</option>
                    <option value="DOGS" >Dogs</option>
                    <option value="REPTILES" >Reptiles</option>
                    <option value="CATS" >Cats</option>
                    <option value="BIRDS" >Birds</option>
                </select>
            </div>


            <div class="user-form-item1">
                <input type="checkbox" class="custom-checkbox" style="border-radius:8px; width: 30px;height: 30px;" name="newaccount.listOption" value="EnableMyList" checked></td>
                <p style="color: black;font-size: 20px; margin-right: 40px;"> &nbsp; I'll receive "MyList" to see my want </p>
            </div>

            <div class="user-form-item1">
                <input type="checkbox" class="custom-checkbox" style="border-radius:8px; width: 30px;height: 30px;" name="newaccount.bannerOption" value="EnableMyBanner" checked>
                <p style="color: black;font-size: 20px; margin-right: 30px;"> &nbsp; I'll receive "MyBanner" to see my favourite category</p>
            </div>


            <div class="user-form-item">
                <input type="submit" value="Save" class="user-form-submit">
            </div>

                <div id="MainImage">
                    <div id="MainImageContent">
                        <img  src="images/black.gif" align="middle"
                              usemap="#estoremap" width="680" style="position: absolute;left: 1000px; top: -250px;"/></div>
                    <img  src="images/black.gif" align="middle"
                          usemap="#estoremap" width="680" style="position: absolute;left: 1000px; top: 500px;"/></div>
            </div>

<%--            <div class="user-form-link">--%>
<%--                <a href="signonForm" class="link">返回登录>></a>--%>
<%--            </div>--%>
        </form>
    </div>
<script src="js/editinfo.js"> </script>
</body>
</html>