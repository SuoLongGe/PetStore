<%@include file="../common/top.jsp"%>

<div id="BackLink">
  <a href="mainForm">Return to Main Menu</a>
</div>

<div id="Content">

  <h2>My Orders</h2>

  <table>
    <tr>
      <th>Order ID</th>
      <th>Date</th>
      <th>Total Price</th>
    </tr>

    <c:forEach var="order" items="${sessionScope.orderLists}">
      <tr>
        <td><a href="toViewOrder?orderId=${order.orderId}">${order.orderId}</a></td>
        <td>${order.orderDate}</td>
        <td>${order.totalPrice}</td>
      </tr>
    </c:forEach>


  </table>

</div>


<%@ include file="../common/bottom.jsp"%>
