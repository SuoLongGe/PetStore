<%@include file="../common/top.jsp"%>

<div id="BackLink">
  <a href="mainForm">Return to Main Menu</a>
</div>
<style>
  table {
    width: 90%;
    margin: 20px auto;
    border-collapse: collapse;
    border-radius: 8px;
    overflow: hidden;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    background-color: #ffffff;
  }

  th {
    background-color:#4CAF50;
    color: #ffffff;
    font-weight: bold;
    text-transform: uppercase;
    letter-spacing: 0.1em;
    padding: 15px;
    text-align: center;
  }

  td {
    padding: 12px;
    text-align: center;
    color: #333;
    font-size: 14px;
    border-bottom: 1px solid #e9ecef;
  }

  tr:nth-child(even) {
    background-color: #f2f6fc;
  }

  tr:hover {
    background-color: #e9f2ff;
    transition: background-color 0.3s;
  }

  a {
    color: #007bff;
    text-decoration: none;
    font-weight: 600;
  }

  a:hover {
    text-decoration: underline;
  }
</style>
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
