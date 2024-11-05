
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更改用户信息</title>
    <link rel="stylesheet" type="text/css" href="css/user.css">
</head>
<body>
<div class="w">
    <h1>用户信息</h1>
    <div class="user-content">

        <form action="editAccount" class="user-form" method="post">

            <c:if test="${requestScope.registerMsg != null}">
                <div class="user-form-error">
                    <p style="font-size: 20px;"> ${requestScope.editMsg}</p>
                </div>
            </c:if>

            <table>
                <tr>
                    <td>User ID:</td>
                    <td>${sessionScope.loginAccount.username}</td>
                </tr>
                <tr>
                    <td>New password:</td>
                    <td><input type="text"  class="user-form-input2" name="newpassword" id="newpassword" placeholder="请输入新密码" autocomplete="off"></td>
                </tr>
                <tr>
                    <td>Repeat password:</td>
                    <td><input type="text"  class="user-form-input2" name="newrepeatpassword" id="newrepeatpassword" placeholder="请再次输入新密码" autocomplete="off"></td>
                </tr>
            </table>


            <h3>Account Information</h3>
            <table>
                <tr>
                    <td>First name:</td>
                    <td><input type="text"  class="user-form-input2" name="newfirstName" id="newfirstName" value="${sessionScope.loginAccount.firstName}" autocomplete="off"></td>
                </tr>
                <tr>
                    <td>Last name:</td>
                    <td><input type="text"  class="user-form-input2" name="newlastName" id="newlastName" value="${sessionScope.loginAccount.lastName}" autocomplete="off"></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input type="text"  class="user-form-input2" name="newemail" id="newemail" value="${sessionScope.loginAccount.email}" autocomplete="off"></td>
                </tr>
                <tr>
                    <td>Phone:</td>
                    <td><input type="text"  class="user-form-input2" name="newphone" id="newphone" value="${sessionScope.loginAccount.phone}" autocomplete="off"></td>
                </tr>
                <tr>
                    <td>Address 1:</td>
                    <td><input type="text"  class="user-form-input2" name="newaddress1" id="newaddress1" value="${sessionScope.loginAccount.address1}" autocomplete="off"></td>
                </tr>
                <tr>
                    <td>Address 2:</td>
                    <td><input type="text"  class="user-form-input2" name="newaddress2" id="newaddress2" value="${sessionScope.loginAccount.address2}" autocomplete="off"></td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td><input type="text"  class="user-form-input2" name="newcity" id="newcity" value="${sessionScope.loginAccount.city}" autocomplete="off"></td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td><input type="text"  class="user-form-input2" name="newstate" id="newstate" value="${sessionScope.loginAccount.state}" autocomplete="off"></td>
                </tr>
                <tr>
                    <td>Zip:</td>
                    <td><input type="text"  class="user-form-input2" name="newzip" id="newzip" value="${sessionScope.loginAccount.zip}" autocomplete="off"></td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td><input type="text"  class="user-form-input2" name="newcountry" id="newcountry" value="${sessionScope.loginAccount.country}" autocomplete="off"></td>
                </tr>
            </table>

            <h3>Profile Information</h3>

            <table>
                <tr>
                    <td>Language Preference:</td>
                    <td>
                        <select id="language" name="newaccount.languagePreference" style="padding: 8px 30px; font-size: 20px">
                            <option value="English" >English</option>
                            <option value="Chinese" >Spanish</option>
                            <option value="French" >French</option>
                            <option value="German" >German</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Favourite Category:</td>
                    <td>
                        <select id="favouriteCategoryId" name="newaccount.favouriteCategoryId" style="padding: 8px 30px; font-size: 20px">
                            <option value="FISH" >Fish</option>
                            <option value="DOGS" >Dogs</option>
                            <option value="REPTILES" >Reptiles</option>
                            <option value="CATS" >Cats</option>
                            <option value="BIRDS" >Birds</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Enable MyList</td>
                    <td>
                        <input type="checkbox" style="width: 30px;height: 30px;" name="newaccount.listOption" value="EnableMyList" checked>
                    </td>
                </tr>
                <tr>
                    <td>Enable MyBanner</td>
                    <td>
                        <input type="checkbox" style="width: 30px;height: 30px;" name="newaccount.bannerOption" value="EnableMyBanner" checked>
                    </td>
                </tr>
            </table>

            <div class="user-form-item">
                <input type="submit" value="保存用户信息" class="user-form-submit">
            </div>

            <div class="user-form-link">
                <a href="signonForm" class="link">返回登录>></a>
            </div>
        </form>
    </div>
</div>
</body>
</html>