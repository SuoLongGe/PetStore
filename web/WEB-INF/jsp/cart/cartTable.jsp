<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table>
    <thead>
    <tr>
        <th>Item ID</th>
        <th>Product ID</th>
        <th>Description</th>
        <th>In Stock?</th>
        <th>Quantity</th>
        <th>List Price</th>
        <th>Total Cost</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${empty cart.cartItemList}">
        <tr>
            <td colspan="7">Your cart is empty.</td>
        </tr>
    </c:if>
    <c:forEach var="cartItem" items="${cart.cartItemList}">
        <tr>
            <td><a href="itemForm?itemId=${cartItem.item.itemId}">${cartItem.item.itemId}</a></td>
            <td>${cartItem.item.productId}</td>
            <td>${cartItem.item.name}</td>
            <td>${cartItem.inStock}</td>
            <td>
                <input type="number" name="quantity_${cartItem.item.itemId}" value="${cartItem.quantity}" min="1">
            </td>
            <td>${cartItem.item.listPrice}</td>
            <td>${cartItem.total}</td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="7"><b>Sub Total:</b> ${subTotal}</td>
    </tr>
    </tbody>
</table>
