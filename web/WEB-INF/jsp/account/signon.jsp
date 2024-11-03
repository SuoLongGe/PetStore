<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/top.jsp"%>



<div id="Catalog">

    <form action="signOn" method="post">
        <p>Please enter your username and password.</p>
        <c:if test="${requestScope.signOnMsg !=null }">
            <p><font color="red">${requestScope.signOnMsg}</font></p>
        </c:if>
        <p>Username : <input type="text" name="username" style="padding: 7px 25px; font-size: 20px; "> <br />
            <br/>
            Password : <input type="password" name="password" style="padding: 7px 25px; font-size: 20px;">
        </p>
        <input type="submit" value="Login" style="padding: 10px 25px; font-size: 18px; background-color: #136518; color: white; border: none; border-radius: 5px; cursor: pointer;">

    </form>

    <br/>
    Need a user name and password? &nbsp;&nbsp;

    <a href="registerForm"> Register Now!</a>

</div>

<%@ include file="../common/bottom.jsp"%>

