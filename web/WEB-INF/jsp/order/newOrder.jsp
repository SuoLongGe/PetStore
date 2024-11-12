<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <form action="newOrder" method="post" style="display:inline;">
        <c:if test="${requestScope.newOrderMsg != null}">
            <div class="user-form-error">
                <p style="font-size: 20px; color: red;"> ${requestScope.newOrderMsg}</p>
            </div>
        </c:if>

    <table>
        <tr>
            <th colspan=2>Payment Details</th>
        </tr>
        <tr>
            <td>Card Type:</td>
            <td>

    <select id="order.cardType" name="order.cardType" style="padding: 2px 15px; font-size: 15px">
        <option value="Visa" >Visa</option>
        <option value="MasterCard" >MasterCard</option>
        <option value="American Express" >American Express</option>
    </select>
            </td>
        </tr>

        <tr>
            <td>UserId:</td>
            <td><input type="text"  name="order.UserId" id="order.UserId" value="${sessionScope.loginAccount.username}" autocomplete="off"></td>
        </tr>
        <tr>
            <td>Card Number:</td>
            <td><input type="text"  name="Card_Number" id="Card_Number"  autocomplete="off">
                * Use a fake number!</td>
        </tr>
        <tr>
            <td>Expiry Date (MM/YYYY):</td>
            <td><input type="text"  name="Expiry_Date" id="Expiry_Date"  autocomplete="off"></td>
        </tr>
        <tr>
            <th colspan=2>Billing Address</th>
        </tr>

        <tr>
            <td>First name:</td>
            <td><input type="text"  name="order.billToFirstName" id="order.billToFirstName" value="${sessionScope.loginAccount.firstName}" autocomplete="off"></td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td><input type="text"  name="order.billToLastName" id="order.billToLastName" value="${sessionScope.loginAccount.lastName}" autocomplete="off"></td>
        </tr>
        <tr>
            <td>Address 1:</td>
            <td><input type="text"  name="order.billAddress1" id="order.billAddress1" value="${sessionScope.loginAccount.address1}" autocomplete="off"></td>
        </tr>
        <tr>
            <td>Address 2:</td>
            <td><input type="text"  name="order.billAddress2" id="order.billAddress2" value="${sessionScope.loginAccount.address2}" autocomplete="off"></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><input type="text"  name="order.billCity" id="order.billCity" value="${sessionScope.loginAccount.city}" autocomplete="off"></td>
        </tr>
        <tr>
            <td>State:</td>
            <td><input type="text"  name="order.billState" id="order.billState" value="${sessionScope.loginAccount.state}" autocomplete="off"></td>
        </tr>
        <tr>
            <td>Zip:</td>
            <td><input type="text"  name="order.billZip" id="order.billZip" value="${sessionScope.loginAccount.zip}" autocomplete="off"></td>
        </tr>
        <tr>
            <td>Country:</td>
            <td><input type="text"  name="order.billCountry" id="order.billCountry" value="${sessionScope.loginAccount.country}" autocomplete="off"></td>
        </tr>

    </table>

        <table>
            <tr>
                <th colspan=2 >Shipping Address</th>
            </tr>

            <tr>
                <td>First name:</td>
                <td><input type="text"  name="order.shipToFirstName" id="order.shipToFirstName" value="${sessionScope.loginAccount.firstName}" autocomplete="off"></td>
            </tr>
            <tr>
                <td>Last name:</td>
                <td><input type="text"  name="order.shipToLastName" id="order.shipToLastName" value="${sessionScope.loginAccount.lastName}" autocomplete="off"></td>
            </tr>
            <tr>
                <td>Address 1:</td>
                <td><input type="text"  name="order.shipAddress1" id="order.shipAddress1" value="${sessionScope.loginAccount.address1}" autocomplete="off"></td>
            </tr>
            <tr>
                <td>Address 2:</td>
                <td><input type="text"  name="order.shipAddress2" id="order.shipAddress2" value="${sessionScope.loginAccount.address2}" autocomplete="off"></td>
            </tr>
            <tr>
                <td>City:</td>
                <td><input type="text"  name="order.shipCity" id="order.shipCity" value="${sessionScope.loginAccount.city}" autocomplete="off"></td>
            </tr>
            <tr>
                <td>State:</td>
                <td><input type="text"  name="order.shipState" id="order.shipState" value="${sessionScope.loginAccount.state}" autocomplete="off"></td>
            </tr>
            <tr>
                <td>Zip:</td>
                <td><input type="text"  name="order.shipZip" id="order.shipZip" value="${sessionScope.loginAccount.zip}" autocomplete="off"></td>
            </tr>
            <tr>
                <td>Country:</td>
                <td><input type="text"  name="order.shipCountry" id="order.shipCountry" value="${sessionScope.loginAccount.country}" autocomplete="off"></td>
            </tr>
        </table>

        <input type="submit" value="Continue">
    </form>

<%@ include file="../common/bottom.jsp"%>
