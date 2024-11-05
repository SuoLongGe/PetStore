<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../common/top.jsp" %>

<div class="container">
    <div class="empty-cart-message">
        <h2>购物车为空</h2>
        <p>您的购物车中没有任何商品，请返回商店添加商品！</p>

        <!-- 显示错误信息 -->
        <c:out value="${sessionScope.errorMsg}" />

        <a href="mainForm" class="btn btn-primary btn-back">返回商店</a>
    </div>
</div>

<%@ include file="../common/bottom.jsp" %>
