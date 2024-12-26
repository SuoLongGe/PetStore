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
    <div class="order-details">
      <h2>Order details</h2>

      <div class="item">
        <span>UserId</span>
        <span class="value">${sessionScope.loginAccount.username}</span>
      </div>
      <div class="item">
        <span>price</span>
        <span class="value">${sessionScope.order.totalPrice}</span>
      </div>
      <div class="item">
        <span>Postage</span>
        <span class="value free">free</span>
      </div>
      <div class="item">
        <span>Mailing method</span>
        <span class="value free">SF Express</span>
      </div>

      <hr>
      <div class="item">
        <span>total</span>
        <span class="value">${sessionScope.order.totalPrice}</span>
      </div>
      <div class="item">
        <span>Delivery time</span>
        <span class="value">2024-12-31</span>
      </div>
      <button type="submit" id="ComformOrderButton" >Comfirm</button>

    </div>



  </form>
</div>

<%@ include file="../common/bottom.jsp"%>
