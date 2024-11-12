
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html >
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html >

<head>
    <link rel="StyleSheet" href="css/user.css" type="text/css"
          media="screen" />
    <title>Genius's PetStore</title>
</head>
<body>

<div id="Header">

    <div id="Logo">
        <div id="LogoContent">
            <a href="mainForm"><img src="images/logo-topbar.gif" /></a>
        </div>
    </div>

    <div id="Menu">
        <div id="MenuContent">
            <a href="cartForm"><img align="middle" name="img_cart" src="images/cart.gif" /></a>
            <img  src="images/separator.gif" />

            <c:if test="${sessionScope.loginAccount == null}">
                <a href="signonForm">Sign in</a>
                <img  src="images/separator.gif" />
            </c:if>

            <c:if test="${sessionScope.loginAccount != null}">
                <a href="signOut">Sign out</a>
                <img  src="images/separator.gif" />
                <a href="editAccountForm">My Account</a>
                <img  src="images/separator.gif" />
            </c:if>
            <a href="help.html">?</a>
        </div>
    </div>

    <div id="Search">
        <div id="SearchContent">
            <form action="searchProductForm" method="post">
                <input type="text" name="keyword" size="18" class="input-text">
                <input type="submit" name="searchProduct" value="Search" class="submit-btn">
            </form>
        </div>
    </div>

    <div id="QuickLinks">

        <div class="topcategory-card" >
            <a href="categoryForm?categoryId=FISH"><img src="images/fish_icon.gif" /></a>
        </div>
        <img  src="images/separator.gif" />
        <div class="topcategory-card" >
            <a href="categoryForm?categoryId=DOGS"><img src="images/dogs_icon.gif" /></a>
        </div>
        <img  src="images/separator.gif" />
        <div class="topcategory-card" >
            <a href="categoryForm?categoryId=REPTILES"><img src="images/reptiles_icon.gif" /></a>
        </div>
        <img  src="images/separator.gif" />
        <div class="topcategory-card" >
            <a href="categoryForm?categoryId=CATS"><img src="images/cats_icon.gif" /></a>
        </div>
        <img  src="images/separator.gif" />
        <div class="topcategory-card" >
            <a href="categoryForm?categoryId=BIRDS"><img src="images/birds_icon.gif" /></a>
        </div>
        <img  src="images/separator.gif" />
    </div>

</div>

<div id="Content">