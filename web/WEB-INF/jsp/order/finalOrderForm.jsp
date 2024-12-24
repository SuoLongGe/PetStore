<%@ include file="../common/top2.jsp"%>

<style>
    #BackLink {
        margin: 20px;
        font-size: 16px;
        color: #4CAF50;
    }
    #BackLink a {
        text-decoration: none;
        color: #4CAF50;
        font-weight: bold;
    }
    #BackLink a:hover {
        text-decoration: underline;
    }
    body {
        font-family: 'Arial', sans-serif;
        background-color: #f4f7fc;
        margin: 0;
        padding: 0;
    }
    #Catalog {
        width: 80%;
        margin: 50px auto;
        background-color: #ffffff;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        padding: 20px;
    }

    h3 {
        font-size: 20px;
        color: #333;
        margin-bottom: 15px;
    }

    table {
        width: 100%;
        margin-bottom: 20px;
        border-collapse: collapse;
    }

    th {
        background-color: #4CAF50;
        color: white;
        text-align: left;
        padding: 10px;
        border-radius: 5px;
    }

    td {
        padding: 10px;
        border-bottom: 1px solid #ddd;
    }

    td b {
        color: #333;
    }

    .message {
        margin: 20px;
        padding: 10px;
        background-color: #f0f8ff;
        border: 1px solid #4CAF50;
        border-radius: 5px;
        color: #4CAF50;
        font-weight: bold;
        text-align: center;
    }

    .note {
        font-size: 12px;
        color: #777;
    }

    input[type="submit"] {
        background-color: #4CAF50;
        color: white;
        border: none;
        padding: 15px 20px;
        font-size: 16px;
        border-radius: 5px;
        cursor: pointer;
        width: 100%;
        transition: background-color 0.3s ease;
        margin-top: 20px;
    }

    input[type="submit"]:hover {
        background-color: #45a049;
    }
</style>

<div class="message">
    <li>Thank you, your order has been submitted.</li>
</div>

<div id="BackLink">
    <a href="mainForm">Return to Main Menu</a>
</div>

<div id="Catalog">
    <table>
        <tr>
            <th align="center" colspan="2">
                Order #${sessionScope.order.orderId}
                <fmt:formatDate value="${sessionScope.order.orderDate}" pattern="yyyy/MM/dd hh:mm:ss" />
            </th>
        </tr>

        <!-- Payment Details Section -->
        <tr>
            <th colspan="2">Payment Details</th>
        </tr>
        <tr>
            <td>Card Type:</td>
            <td><c:out value="${sessionScope.order.cardType}" /></td>
        </tr>
        <tr>
            <td>Card Number:</td>
            <td><c:out value="${sessionScope.order.creditCard}" /> * Fake number!</td>
        </tr>
        <tr>
            <td>Expiry Date (MM/YYYY):</td>
            <td><c:out value="${sessionScope.order.expiryDate}" /></td>
        </tr>

        <!-- Billing Address Section -->
        <tr>
            <th colspan="2">Billing Address</th>
        </tr>
        <tr>
            <td>First name:</td>
            <td><c:out value="${sessionScope.order.billToFirstName}" /></td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td><c:out value="${sessionScope.order.billToLastName}" /></td>
        </tr>
        <tr>
            <td>Address 1:</td>
            <td><c:out value="${sessionScope.order.billAddress1}" /></td>
        </tr>
        <tr>
            <td>Address 2:</td>
            <td><c:out value="${sessionScope.order.billAddress2}" /></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><c:out value="${sessionScope.order.billCity}" /></td>
        </tr>
        <tr>
            <td>State:</td>
            <td><c:out value="${sessionScope.order.billState}" /></td>
        </tr>
        <tr>
            <td>Zip:</td>
            <td><c:out value="${sessionScope.order.billZip}" /></td>
        </tr>
        <tr>
            <td>Country:</td>
            <td><c:out value="${sessionScope.order.billCountry}" /></td>
        </tr>

        <!-- Shipping Address Section -->
        <tr>
            <th colspan="2">Shipping Address</th>
        </tr>
        <tr>
            <td>First name:</td>
            <td><c:out value="${sessionScope.order.shipToFirstName}" /></td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td><c:out value="${sessionScope.order.shipToLastName}" /></td>
        </tr>
        <tr>
            <td>Address 1:</td>
            <td><c:out value="${sessionScope.order.shipAddress1}" /></td>
        </tr>
        <tr>
            <td>Address 2:</td>
            <td><c:out value="${sessionScope.order.shipAddress2}" /></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><c:out value="${sessionScope.order.shipCity}" /></td>
        </tr>
        <tr>
            <td>State:</td>
            <td><c:out value="${sessionScope.order.shipState}" /></td>
        </tr>
        <tr>
            <td>Zip:</td>
            <td><c:out value="${sessionScope.order.shipZip}" /></td>
        </tr>
        <tr>
            <td>Country:</td>
            <td><c:out value="${sessionScope.order.shipCountry}" /></td>
        </tr>

        <!-- Courier and Order Status Section -->
        <tr>
            <td>Courier:</td>
            <td><c:out value="${sessionScope.order.courier}" /></td>
        </tr>
        <tr>
            <td colspan="2">Status: <c:out value="${sessionScope.order.status}" /></td>
        </tr>

        <!-- Line Items Table -->
        <tr>
            <td colspan="2">
                <table>
                    <tr>
                        <th>Item ID</th>
                        <th>Description</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total Cost</th>
                    </tr>
                    <c:forEach var="lineItem" items="${sessionScope.order.lineItems}">
                        <tr>
                            <td>
                                <a href="itemForm?itemId=${lineItem.itemId}">
                                        ${lineItem.itemId}
                                </a>
                            </td>
                            <td>
                                <c:if test="${lineItem.item != null}">
                                    ${lineItem.item.attribute1}
                                    ${lineItem.item.attribute2}
                                    ${lineItem.item.attribute3}
                                    ${lineItem.item.attribute4}
                                    ${lineItem.item.attribute5}
                                    ${lineItem.item.product.name}
                                </c:if>
                                <c:if test="${lineItem.item == null}">
                                    <i>{description unavailable}</i>
                                </c:if>
                            </td>
                            <td>
                                    ${lineItem.quantity}
                            </td>
                            <td>
                                <fmt:formatNumber value="${lineItem.unitPrice}" pattern="$#,##0.00" />
                            </td>
                            <td>
                                <fmt:formatNumber value="${lineItem.total}" pattern="$#,##0.00" />
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <th colspan="5">
                            Total:
                            <fmt:formatNumber value="${sessionScope.order.totalPrice}" pattern="$#,##0.00" />
                        </th>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>

<%@ include file="../common/bottom.jsp"%>
