
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html >
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html >

<head>
    <meta charset="UTF-8">
    <link rel="StyleSheet" href="css/petstore.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="css/top.css">
    <title>Genius's PetStore</title>
    <script src="https://kit.fontawesome.com/1c3b84e567.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="nav-top">
    <div class="w">
        <div class="user-info">

            <c:if test="${sessionScope.loginAccount == null}">
                <span class="user not-login">
                <a href="signonForm" class="link">Login </a>
                <a href="registerForm" class="link"> Register</a>
                </span>
            </c:if>


            <c:if test="${sessionScope.loginAccount != null}">
            <span class="user not-login">
                <span class="link-text">
                    欢迎您，<span class="username">${loginAccount.username}</span>
                </span>
                <a href="signOut" class="link">Sign out</a>
                </span>
            </c:if>
        </div>

        <ul class="nav-top-list">

            <c:if test="${sessionScope.loginAccount != null}">
            <li class="nav-top-item">
                <a href="cartForm" class="link">
                    <i class="fa fa-shopping-cart"></i>
                    Shopping Cart
                </a>
            </li>
            <li class="nav-top-item">
                <a href="myOrder" class="link">MyOrder</a>
            </li>
            <li class="nav-top-item">
                <a href="editAccountForm" class="link">个人中心</a>
            </li>
            </c:if>

            <li class="nav-top-item">
                <a href="help.html" class="link">关于MyStore</a>
            </li>
        </ul>

    </div>
</div>

<div class="nav-search">
    <div class="w">
        <a href="mainForm" class="link1 logo">
            <img src="images/top-bar.gif" />
            Mystore</a>
        <div class="search-content">
            <form action="searchProductForm" method="post">
            <input type="text" name="keyword" size="18" class="search-input" />
            <input type="submit" name="searchProduct" value="Search" class="btn search-btn">
            </form>
            <div class="quick-head"><a href="categoryForm?categoryId=FISH">Fish</a></div>
            <div class="quick"><a href="categoryForm?categoryId=DOGS">Dog</a></div>
            <div class="quick"><a href="categoryForm?categoryId=REPTILES">Reptiles</a></div>
            <div class="quick"><a href="categoryForm?categoryId=CATS">Cat</a></div>
            <div class="quick"><a href="categoryForm?categoryId=BIRDS">Birds</a></div>
        </div>
    </div>
</div>
<div id="Content">