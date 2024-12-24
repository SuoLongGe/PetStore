<%@ include file="../common/top2.jsp"%>





<style>
    body {
        font-family: 'Arial', sans-serif;
        background-color: #f4f7fc;
        margin: 0;
        padding: 0;
    }
    form {
        background-color: transparent;
        border: none; /* 移除表单的边框 */
    }

    #Catalog {
        width: 80%;
        margin: 50px auto;
        background-color: #ffffff;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        padding: 20px;
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
    input[type="text"], select {
        width: 100%;
        padding: 10px;
        margin: 5px 0;
        border: 1px solid #ddd;
        border-radius: 5px;
        font-size: 14px;
    }
    input[type="text"]:focus, select:focus {
        border-color: #4CAF50;
        outline: none;
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
    }
    input[type="submit"]:hover {
        background-color: #45a049;
    }
    .form-section {
        margin-bottom: 30px;
    }
    .form-section h3 {
        font-size: 18px;
        margin-bottom: 10px;
        color: #333;
    }
    .note {
        font-size: 12px;
        color: #777;
    }
    #Payment-Detail{
    }
    #Billing-Address{
          display: none;
    }
    #Shipping-Address{
        display: none;
    }



    .container {
        width: 700px;
        background: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }
    .step-indicator {
        display: flex;
        align-items: center;
        margin-bottom: 20px;
        font-size: 14px;
        color: #aaa;
        font-family: Arial, sans-serif;
    }
    .step {
        padding: 0 5px;
        font-size:20px;
        cursor: pointer;
        transition: all 0.3s ease; /* 添加过渡效果 */
    }
    .step:hover {
        color: #4CAF50; /* 鼠标悬停时改变字体颜色 */
        background-color: rgba(76, 175, 80, 0.1); /* 背景颜色变化 */
        border-radius: 5px; /* 增加圆角 */
    }
    /*.step.active {*/
    /*    color: #000;*/
    /*    font-weight: bold;*/
    /*}*/
    /* 活动步骤的样式 */
    .step.active {
        font-weight: bold;
        color: #4CAF50; /* 活动步骤的字体颜色 */
    }

    /* 点击步骤后的特效 */
    .step:active {
        background-color: #4CAF50; /* 点击时背景色变化 */
        color: white; /* 字体颜色变为白色 */
        border-radius: 5px; /* 添加圆角 */
    }

    /* 给步骤间的箭头添加一些间距 */
    .step-indicator span {
        padding: 0 10px;
        font-size: 18px;
    }

    .step-content {
        display: none;
    }
    .step-content.active {
        display: block;
    }
    .form-section {
        margin-bottom: 20px;
    }
    button {
        margin-top: 20px;
        width: 100%;
        padding: 10px;
        background-color: #4CAF50;
        color: white;
        border: none;
        border-radius: 4px;
        font-size: 16px;
        cursor: pointer;
    }
    button:hover {
        background-color: #45a049;
    }
    input, select {
        width: 100%;
        padding: 8px;
        margin: 8px 0;
        border: 1px solid #ccc;
        border-radius: 4px;
    }
    label {
        display: block;
        margin-top: 10px;
    }
    .user-form-error {
        color: red;
        margin-bottom: 10px;
    }


</style>

<div id="Catalog">
    <form action="newOrder" method="post" style="display:inline;">
    <div class="order-container">
        <!-- 左侧的步骤表单部分 -->
        <div class="order-steps">

        <c:if test="${requestScope.newOrderMsg != null}">
            <div class="user-form-error">
                <p style="font-size: 20px; color: red;" > ${requestScope.newOrderMsg}</p>
            </div>
        </c:if>



        <!-- Payment Details Section -->
<%--        <div class="form-section" id="Payment-Detail">--%>
<%--            <h3>Payment Details</h3>--%>
<%--            <table>--%>
<%--                <tr>--%>
<%--                    <td>Card Type:</td>--%>
<%--                    <td>--%>
<%--                        <select id="order.cardType" name="order.cardType">--%>
<%--                            <option value="Visa">Visa</option>--%>
<%--                            <option value="MasterCard">MasterCard</option>--%>
<%--                            <option value="American Express">American Express</option>--%>
<%--                        </select>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td>UserId:</td>--%>
<%--                    <td><input type="text" name="order.UserId" id="order.UserId" value="${sessionScope.loginAccount.username}" autocomplete="off"></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td>Card Number:</td>--%>
<%--                    <td><input type="text" name="Card_Number" id="Card_Number" autocomplete="off">--%>
<%--                        <span class="note">* Use a fake number!</span></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td>Expiry Date (MM/YYYY):</td>-%>
<%--                    <td><input type="text" name="Ex-piry_Date" id="Expiry_Date" autocomplete="off"></td>--%>
<%--                </tr>--%>
<%--            </table>--%>
<%--        </div>--%>


        <!-- Billing Address Section -->
        <div class="form-section" id="Billing-Address">
            <h3>Billing Address</h3>
            <table>
                <tr>
                    <td>First Name:</td>
                    <td><input type="text" name="order.billToFirstName" id="order.billToFirstName" value="${sessionScope.loginAccount.firstName}" autocomplete="off"></td>
                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td><input type="text" name="order.billToLastName" id="order.billToLastName" value="${sessionScope.loginAccount.lastName}" autocomplete="off"></td>
                </tr>
                <tr>
                    <td>Address 1:</td>
                    <td><input type="text" name="order.billAddress1" id="order.billAddress1" value="${sessionScope.loginAccount.address1}" autocomplete="off"></td>
                </tr>
                <tr>
                    <td>Address 2:</td>
                    <td><input type="text" name="order.billAddress2" id="order.billAddress2" value="${sessionScope.loginAccount.address2}" autocomplete="off"></td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td><input type="text" name="order.billCity" id="order.billCity" value="${sessionScope.loginAccount.city}" autocomplete="off"></td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td><input type="text" name="order.billState" id="order.billState" value="${sessionScope.loginAccount.state}" autocomplete="off"></td>
                </tr>
                <tr>
                    <td>Zip:</td>
                    <td><input type="text" name="order.billZip" id="order.billZip" value="${sessionScope.loginAccount.zip}" autocomplete="off"></td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td><input type="text" name="order.billCountry" id="order.billCountry" value="${sessionScope.loginAccount.country}" autocomplete="off"></td>
                </tr>
            </table>
        </div>

        <!-- Shipping Address Section -->
<%--        <div class="form-section" id="Shipping-Address">--%>
<%--            <h3>Shipping Address</h3>--%>
<%--            <table>--%>
<%--                <tr>--%>
<%--                    <td>First Name:</td>--%>
<%--                    <td><input type="text" name="order.shipToFirstName" id="order.shipToFirstName" value="${sessionScope.loginAccount.firstName}" autocomplete="off"></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td>Last Name:</td>--%>
<%--                    <td><input type="text" name="order.shipToLastName" id="order.shipToLastName" value="${sessionScope.loginAccount.lastName}" autocomplete="off"></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td>Address 1:</td>--%>
<%--                    <td><input type="text" name="order.shipAddress1" id="order.shipAddress1" value="${sessionScope.loginAccount.address1}" autocomplete="off"></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td>Address 2:</td>--%>
<%--                    <td><input type="text" name="order.shipAddress2" id="order.shipAddress2" value="${sessionScope.loginAccount.address2}" autocomplete="off"></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td>City:</td>--%>
<%--                    <td><input type="text" name="order.shipCity" id="order.shipCity" value="${sessionScope.loginAccount.city}" autocomplete="off"></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td>State:</td>--%>
<%--                    <td><input type="text" name="order.shipState" id="order.shipState" value="${sessionScope.loginAccount.state}" autocomplete="off"></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td>Zip:</td>--%>
<%--                    <td><input type="text" name="order.shipZip" id="order.shipZip" value="${sessionScope.loginAccount.zip}" autocomplete="off"></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td>Country:</td>--%>
<%--                    <td><input type="text" name="order.shipCountry" id="order.shipCountry" value="${sessionScope.loginAccount.country}" autocomplete="off"></td>--%>
<%--                </tr>--%>
<%--            </table>--%>
<%--        </div>--%>

<%--        <div class="container">--%>
<%--            <!-- 步骤指示器 -->--%>
<%--            <div class="step-indicator">--%>
<%--                <span class="step active">选择地址</span>--%>
<%--                <span> &gt; </span>--%>
<%--                <span class="step">运输方式</span>--%>
<%--                <span> &gt; </span>--%>
<%--                <span class="step">付款方式</span>--%>
<%--            </div>--%>

<%--            <!-- 选择地址 -->--%>
<%--            <div class="step-content active" id="step1">--%>
<%--                <h2>选择地址</h2>--%>
<%--                <form>--%>
<%--                    <div class="form-section">--%>
<%--                        <label for="order.billToFirstName">First Name:</label>--%>
<%--                        <input type="text" name="order.billToFirstName" id="order.billToFirstName" value="${sessionScope.loginAccount.firstName}">--%>
<%--                        <label for="order.billToLastName">Last Name:</label>--%>
<%--                        <input type="text" name="order.billToLastName" id="order.billToLastName" value="${sessionScope.loginAccount.lastName}">--%>
<%--                        <label for="order.billAddress1">Address 1:</label>--%>
<%--                        <input type="text" name="order.billAddress1" id="order.billAddress1" value="${sessionScope.loginAccount.address1}">--%>
<%--                        <label for="order.billAddress2">Address 2:</label>--%>
<%--                        <input type="text" name="order.billAddress2" id="order.billAddress2" value="${sessionScope.loginAccount.address2}">--%>
<%--                        <label for="order.billCity">City:</label>--%>
<%--                        <input type="text" name="order.billCity" id="order.billCity" value="${sessionScope.loginAccount.city}">--%>
<%--                        <label for="order.billState">State:</label>--%>
<%--                        <input type="text" name="order.billState" id="order.billState" value="${sessionScope.loginAccount.state}">--%>
<%--                        <label for="order.billZip">Zip:</label>--%>
<%--                        <input type="text" name="order.billZip" id="order.billZip" value="${sessionScope.loginAccount.zip}">--%>
<%--                    </div>--%>
<%--                    <button type="button" onclick="validateForm(1)">确认并继续</button>--%>
<%--                </form>--%>
<%--            </div>--%>

<%--            <!-- 选择运输方式 -->--%>
<%--            <div class="step-content" id="step2">--%>
<%--                <h2>选择运输方式</h2>--%>
<%--                <form>--%>
<%--                    <div class="form-section">--%>
<%--                        <label>--%>
<%--                            <input type="radio" name="shipping" value="free" checked>--%>
<%--                            免费 常规运输 (预计送达：2023-02-01)--%>
<%--                        </label>--%>
<%--                        <label>--%>
<%--                            <input type="radio" name="shipping" value="priority">--%>
<%--                            $8.50 优先发货 (预计送达：2023-05-28)--%>
<%--                        </label>--%>
<%--                    </div>--%>
<%--                    <button type="button" onclick="validateForm(2)">确认并继续</button>--%>
<%--                </form>--%>
<%--            </div>--%>

<%--            <!-- 选择付款方式 -->--%>
<%--            <div class="step-content" id="step3">--%>
<%--                <h2>选择付款方式</h2>--%>
<%--                <form>--%>
<%--                    <div class="form-section">--%>
<%--                        <label for="order.cardType">Card Type:</label>--%>
<%--                        <select id="order.cardType" name="order.cardType">--%>
<%--                            <option value="Visa">Visa</option>--%>
<%--                            <option value="MasterCard">MasterCard</option>--%>
<%--                            <option value="American Express">American Express</option>--%>
<%--                        </select>--%>
<%--                        <label for="Card_Number">Card Number:</label>--%>
<%--                        <input type="text" name="Card_Number" id="Card_Number">--%>
<%--                        <label for="Expiry_Date">Expiry Date (MM/YYYY):</label>--%>
<%--                        <input type="text" name="Expiry_Date" id="Expiry_Date">--%>
<%--                    </div>--%>
<%--                    <button type="button" onclick="validateForm(3)">订单并支付</button>--%>
<%--                </form>--%>
<%--            </div>--%>
<%--        </div>--%>
            <!-- 步骤指示器 -->
            <div class="step-indicator">
                <span class="step active"  onclick="showStep(0)">Billing Address</span>
                <span style="padding: 0 10px;">&gt;</span>
                <span class="step"  onclick="showStep(1)">Shipping Address</span>
                <span style="padding: 0 10px;">&gt;</span>
                <span class="step" onclick="showStep(2)">Payment Details</span>
            </div>
            <!-- 选择地址 -->
            <div class="step-content active" id="step1">
                <h3>Billing Address</h3>
                <form>
                    <label for="order.billToFirstName">First Name:</label>
                    <input type="text" name="order.billToFirstName" id="order.billToFirstName" value="${sessionScope.loginAccount.firstName}">
                    <label for="order.billToLastName">Last Name:</label>
                    <input type="text" name="order.billToLastName" id="order.billToLastName" value="${sessionScope.loginAccount.lastName}">
                    <label for="order.billAddress1">Address 1:</label>
                    <input type="text" name="order.billAddress1" id="order.billAddress1" value="${sessionScope.loginAccount.address1}">
                    <label for="order.billAddress2">Address 2:</label>
                    <input type="text" name="order.billAddress2" id="order.billAddress2" value="${sessionScope.loginAccount.address2}">
                    <label for="order.billCity">City:</label>
                    <input type="text" name="order.billCity" id="order.billCity" value="${sessionScope.loginAccount.city}">
                    <label for="order.billState">State:</label>
                    <input type="text" name="order.billState" id="order.billState" value="${sessionScope.loginAccount.state}">
                    <label for="order.billZip">Zip:</label>
                    <input type="text" name="order.billZip" id="order.billZip" value="${sessionScope.loginAccount.zip}">
                </form>
            </div>

            <!-- 选择运输方式 -->
            <div class="step-content" id="step2">
                <h3>Shipping Address</h3>

                <div>
                    <label for="order.shipToFirstName">shipToFirstName:</label>
                    <input type="text" name="order.shipToFirstName" id="order.shipToFirstName" value="${sessionScope.loginAccount.firstName}" autocomplete="off">
                </div>
                <div>
                    <label for="order.shipToLastName">shipToLastName:</label>
                    <input type="text" name="order.shipToLastName" id="order.shipToLastName" value="${sessionScope.loginAccount.lastName}" autocomplete="off">
                </div>

                <div>
                    <label for="order.shipAddress1">Address 1:</label>
                    <input type="text" name="order.shipAddress1" id="order.shipAddress1" value="${sessionScope.loginAccount.address1}" autocomplete="off">
                </div>
                <div>
                    <label for="order.shipAddress2">Address 2:</label>
                    <input type="text" name="order.shipAddress2" id="order.shipAddress2" value="${sessionScope.loginAccount.address2}" autocomplete="off">
                </div>

                <div>
                    <label for="order.shipCity">City:</label>
                    <input type="text" name="order.shipCity" id="order.shipCity" value="${sessionScope.loginAccount.city}" autocomplete="off">
                </div>
                <div>
                    <label for="order.shipState">State:</label>
                    <input type="text" name="order.shipState" id="order.shipState" value="${sessionScope.loginAccount.state}" autocomplete="off">
                </div>


                <div>
                    <label for="order.shipZip">Zip:</label>
                    <input type="text" name="order.shipZip" id="order.shipZip" value="${sessionScope.loginAccount.zip}" autocomplete="off">
                </div>
                <div>
                    <label for="order.shipCountry">Country:</label>
                        <input type="text" name="order.shipCountry" id="order.shipCountry" value="${sessionScope.loginAccount.country}" autocomplete="off">
                </div>
            </div>

            <!-- 选择付款方式 -->
            <div class="step-content" id="step3">
                <h3>Payment Details</h3>
                <div>
                    <label for="order.cardType">Card Type:</label>
                    <select id="order.cardType" name="order.cardType">
                        <option value="Visa">Visa</option>
                        <option value="MasterCard">MasterCard</option>
                        <option value="American Express">American Express</option>
                    </select>
                </div>
                <div>
                    <label for="order.UserId">UserId:</label>
                    <input type="text" name="order.UserId" id="order.UserId" value="${sessionScope.loginAccount.username}" autocomplete="off">
                </div>


                <div>
                    <label for="Card_Number">Card Number:</label>
                    <input type="text" name="Card_Number" id="Card_Number" autocomplete="off">
                </div>
                <div>
                    <label for="Expiry_Date">Expiry Date (MM/YYYY):</label>
                    <input type="text" name="Expiry_Date" id="Expiry_Date">
                </div>
            </div>
        </div>

        <div class="order-details">
            <h2>Order details</h2>

            <div class="item">
                <span>name</span>
                <span class="value">${lineItem.item.product.name}</span>
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
            <input type="text" placeholder="Coupon redemption code">
<%--            <button type="submit" onclick="changeFormAction()">Order and pay</button>--%>
            <button type="submit" id="orderButton" class="disabled" onclick="changeFormAction()">Order and pay</button>
        </div>
  <%--        <input type="submit" value="Continue">--%>
        </div>
    </form>

</div>






<%@ include file="../common/bottom.jsp"%>
