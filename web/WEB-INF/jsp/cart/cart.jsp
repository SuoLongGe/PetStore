<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page session="true" %>
<%@ include file="../common/top.jsp" %>

<!-- 页面标题 -->
<title>Shopping Cart</title>

<!-- 购物车的样式 -->
<head>
    <link rel="stylesheet" href="css/cart.css" type="text/css">
</head>

<div id="BackLink">
    <a href="mainForm">Return to Main Menu</a>
</div>

<div id="Catalog">
    <!-- Flex 布局容器，包含购物车和右侧 MyList -->
    <div id="CartContainer">
        <!-- 左侧购物车部分 -->
        <div id="Cart">
            <h2>Shopping Cart</h2>

            <!-- 错误信息展示 -->
            <c:if test="${not empty errorMessage}">
                <div class="error-message">
                        ${errorMessage}
                </div>
            </c:if>

            <!-- 购物车表单 -->
            <form action="<%= request.getContextPath() %>/updateCart" method="post">
                <table id="cartTable">
                    <thead>
                    <tr>
                        <th><b>Item ID</b></th>
                        <th><b>Product ID</b></th>
                        <th><b>Description</b></th>
                        <th><b>In Stock?</b></th>
                        <th><b>Quantity</b></th>
                        <th><b>List Price</b></th>
                        <th><b>Total Cost</b></th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- 购物车为空时显示 -->
                    <c:if test="${empty cart.cartItemList}">
                        <tr>
                            <td colspan="7"><b>Your cart is empty.</b></td>
                        </tr>
                    </c:if>

                    <!-- 购物车项目展示 -->
                    <c:forEach var="cartItem" items="${cart.cartItemList}">
                        <tr>
                            <td>
                                <a href="itemForm?itemId=${cartItem.item.itemId}">${cartItem.item.itemId}</a>
                            </td>
                            <td>${cartItem.item.productId}</td>
                            <td>
                                    ${cartItem.item.attribute1} ${cartItem.item.attribute2}
                                    ${cartItem.item.attribute3} ${cartItem.item.attribute4}
                                    ${cartItem.item.attribute5} ${cartItem.item.product.name}
                            </td>
                            <td>${cartItem.inStock}</td>
                            <td>
                                <input type="number" name="quantity_${cartItem.item.itemId}" value="${cartItem.quantity}" min="1" class="quantity-input" data-item-id="${cartItem.item.itemId}">
                            </td>
                            <td>
                                <fmt:formatNumber value="${cartItem.item.listPrice}" pattern="$#,##0.00" />
                            </td>
                            <td>
                                <fmt:formatNumber value="${cartItem.total}" pattern="$#,##0.00" />
                            </td>
                            <td>
                                <a href="removeCartItem?workingItemId=${cartItem.item.itemId}" class="Button">Remove</a>
                            </td>
                        </tr>
                    </c:forEach>

                    <!-- 小计展示 -->
                    <tr>
                        <td colspan="7">
                            <b>Sub Total:</b> <fmt:formatNumber value="${sessionScope.cart.subTotal}" pattern="$#,##0.00" />
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    </tbody>
                </table>

<%--                <input type="submit" value="Update Cart" class="Button">--%>
            </form>

            <form action="newOrderForm" method="post" style="display:inline;">
                <input type="submit" value="New Order" class="Button">
            </form>
        </div>


        <div id="MyList">
            <c:if test="${sessionScope.loginAccount != null}">
                <c:if test="${!empty sessionScope.loginAccount.listOption}">
                    <%@ include file="includeMyList.jsp" %>
                </c:if>
            </c:if>
        </div>
    </div>

    <div id="Separator">&nbsp;</div>
</div>


<%@ include file="../common/bottom.jsp" %>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function() {

        $('.quantity-input').on('change', function() {
            var itemId = $(this).data('item-id');
            var quantity = $(this).val();

            if (quantity == 0) {

                alert("数量不能为0");
                $(this).val(1);
                return;
            }


            $.ajax({
                url: '<%= request.getContextPath() %>/updateCart',
                method: 'POST',
                data: {
                    itemId: itemId,
                    quantity: quantity
                },
                success: function(response) {

                    $('#cartTable').html(response.cartHtml);
                    $('#subtotal').text(response.subTotal);
                },
                error: function(xhr, status, error) {
                }
            });
        });
    });
</script>

