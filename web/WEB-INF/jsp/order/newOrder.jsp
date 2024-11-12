<%@ include file="../common/top2.jsp"%>


<style>
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
</style>

<div id="Catalog">
    <form action="newOrder" method="post" style="display:inline;">
        <!-- Payment Details Section -->
        <div class="form-section">
            <h3>Payment Details</h3>
            <table>
                <tr>
                    <td>Card Type:</td>
                    <td>
                        <select id="order.cardType" name="order.cardType">
                            <option value="Visa">Visa</option>
                            <option value="MasterCard">MasterCard</option>
                            <option value="American Express">American Express</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>UserId:</td>
                    <td><input type="text" name="order.UserId" id="order.UserId" value="${sessionScope.loginAccount.username}" autocomplete="off"></td>
                </tr>
                <tr>
                    <td>Card Number:</td>
                    <td><input type="text" name="Card_Number" id="Card_Number" autocomplete="off">
                        <span class="note">* Use a fake number!</span></td>
                </tr>
                <tr>
                    <td>Expiry Date (MM/YYYY):</td>
                    <td><input type="text" name="Expiry_Date" id="Expiry_Date" autocomplete="off"></td>
                </tr>
            </table>
        </div>


        <!-- Billing Address Section -->
        <div class="form-section">
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
        <div class="form-section">
            <h3>Shipping Address</h3>
            <table>
                <tr>
                    <td>First Name:</td>
                    <td><input type="text" name="order.shipToFirstName" id="order.shipToFirstName" value="${sessionScope.loginAccount.firstName}" autocomplete="off"></td>
                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td><input type="text" name="order.shipToLastName" id="order.shipToLastName" value="${sessionScope.loginAccount.lastName}" autocomplete="off"></td>
                </tr>
                <tr>
                    <td>Address 1:</td>
                    <td><input type="text" name="order.shipAddress1" id="order.shipAddress1" value="${sessionScope.loginAccount.address1}" autocomplete="off"></td>
                </tr>
                <tr>
                    <td>Address 2:</td>
                    <td><input type="text" name="order.shipAddress2" id="order.shipAddress2" value="${sessionScope.loginAccount.address2}" autocomplete="off"></td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td><input type="text" name="order.shipCity" id="order.shipCity" value="${sessionScope.loginAccount.city}" autocomplete="off"></td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td><input type="text" name="order.shipState" id="order.shipState" value="${sessionScope.loginAccount.state}" autocomplete="off"></td>
                </tr>
                <tr>
                    <td>Zip:</td>
                    <td><input type="text" name="order.shipZip" id="order.shipZip" value="${sessionScope.loginAccount.zip}" autocomplete="off"></td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td><input type="text" name="order.shipCountry" id="order.shipCountry" value="${sessionScope.loginAccount.country}" autocomplete="off"></td>
                </tr>
            </table>
        </div>

        <input type="submit" value="Continue">
    </form>
</div>

<%@ include file="../common/bottom.jsp"%>
