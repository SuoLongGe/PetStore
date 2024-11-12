
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html >
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html >

<head>
    <meta charset="UTF-8">
    <link rel="StyleSheet" href="css/petstore.css" type="text/css"
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
            <div class="top_cart">
            <a href="cartForm"><img align="middle" name="img_cart" src="images/cart.gif" /></a>
            </div>
            <img  src="images/separator.gif" />


            <c:if test="${sessionScope.loginAccount == null}">
            <div class="top_icon">
            <a href="signonForm">Sign in</a>
            </div>
            <img  src="images/separator.gif" />
            </c:if>

            <c:if test="${sessionScope.loginAccount != null}">
            <div class="top_icon">
            <a href="signOut">Sign out</a>
            </div>
            <img  src="images/separator.gif" />
            <div class="top_icon">
            <a href="editAccountForm">My Account</a>
            </div>
            <img  src="images/separator.gif" />
            </c:if>
            <div class="top_icon">
                <a href="help.html">?</a>
            </div>
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