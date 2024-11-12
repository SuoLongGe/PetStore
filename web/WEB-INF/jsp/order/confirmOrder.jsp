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
  #Catalog {
    width: 80%;
    margin: 50px auto;
    background-color: #ffffff;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    padding: 20px;
  }
  body {
    font-family: 'Arial', sans-serif;
    background-color: #f4f7fc;
    margin: 0;
    padding: 0;
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
  .form-section {
    margin-bottom: 30px;
  }
  .note {
    font-size: 12px;
    color: #777;
  }
</style>

<div id="BackLink">
  <a href="mainForm">Return to Main Menu</a>
</div>

<div id="Catalog">
  <p>Please confirm the information below and then press continue...</p>


  <form action="confirmOrder" method="post" style="display:inline;">
    <table>
      <tr>
        <th align="center" colspan="2">
          <font size="4">
            <b>Order</b>
          </font>
          <br />
          <font size="3">
            <b>
              <fmt:formatDate value="${sessionScope.order.orderDate}" pattern="yyyy/MM/dd hh:mm:ss" />
            </b>
          </font>
        </th>
      </tr>


      <!-- Billing Address Section -->
      <tr>
        <th colspan="2">Billing Address</th>
      </tr>
      <tr>
        <td>First name:</td>
        <td><b><c:out value="${sessionScope.order.billToFirstName}" /></b></td>
      </tr>
      <tr>
        <td>Last name:</td>
        <td><b><c:out value="${sessionScope.order.billToLastName}" /></b></td>
      </tr>
      <tr>
        <td>Address 1:</td>
        <td><b><c:out value="${sessionScope.order.billAddress1}" /></b></td>
      </tr>
      <tr>
        <td>Address 2:</td>
        <td><b><c:out value="${sessionScope.order.billAddress2}" /></b></td>
      </tr>
      <tr>
        <td>City:</td>
        <td><b><c:out value="${sessionScope.order.billCity}" /></b></td>
      </tr>
      <tr>
        <td>State:</td>
        <td><b><c:out value="${sessionScope.order.billState}" /></b></td>
      </tr>
      <tr>
        <td>Zip:</td>
        <td><b><c:out value="${sessionScope.order.billZip}" /></b></td>
      </tr>
      <tr>
        <td>Country:</td>
        <td><b><c:out value="${sessionScope.order.billCountry}" /></b></td>
      </tr>


      <!-- Shipping Address Section -->
      <tr>
        <th colspan="2">Shipping Address</th>
      </tr>
      <tr>
        <td>First name:</td>
        <td><b><c:out value="${sessionScope.order.shipToFirstName}" /></b></td>
      </tr>
      <tr>
        <td>Last name:</td>
        <td><b><c:out value="${sessionScope.order.shipToLastName}" /></b></td>
      </tr>
      <tr>
        <td>Address 1:</td>
        <td><b><c:out value="${sessionScope.order.shipAddress1}" /></b></td>
      </tr>
      <tr>
        <td>Address 2:</td>
        <td><b><c:out value="${sessionScope.order.shipAddress2}" /></b></td>
      </tr>
      <tr>
        <td>City:</td>
        <td><b><c:out value="${sessionScope.order.shipCity}" /></b></td>
      </tr>
      <tr>
        <td>State:</td>
        <td><b><c:out value="${sessionScope.order.shipState}" /></b></td>
      </tr>
      <tr>
        <td>Zip:</td>
        <td><b><c:out value="${sessionScope.order.shipZip}" /></b></td>
      </tr>
      <tr>
        <td>Country:</td>
        <td><b><c:out value="${sessionScope.order.shipCountry}" /></b></td>
      </tr>


      <tr>
        <td colspan="2">
          <input type="submit" name="confirmed" value="Confirm Order">
        </td>
      </tr>
    </table>
  </form>
</div>

<%@ include file="../common/bottom.jsp"%>
