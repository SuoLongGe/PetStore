<%@ include file="../common/top.jsp"%>

<div id="Catalog">

    <form action="viewOrder" method="post" style="display:inline;">
    <table>
        <tr>
            <th colspan=2>Payment Details</th>
        </tr>
        <tr>
            <td>Card Type:</td>
            <td>
<%--                <stripes:select name="order.cardType">--%>
<%--                <stripes:options-collection--%>
<%--                        collection="${actionBean.creditCardTypes}" />--%>
<%--            </stripes:select>--%>
    <select id="favouriteCategoryId" name="newaccount.favouriteCategoryId" style="padding: 8px 30px; font-size: 20px">
        <option value="FISH" ></option>
        <option value="DOGS" ></option>
        <option value="REPTILES" ></option>
        <option value="CATS" >Cats</option>
        <option value="BIRDS" >Birds</option>
    </select>
            </td>
        </tr>
        <tr>
            <td>Card Number:</td>
            <td><stripes:text name="order.creditCard" /> * Use a fake
                number!</td>
        </tr>
        <tr>
            <td>Expiry Date (MM/YYYY):</td>
            <td><stripes:text name="order.expiryDate" /></td>
        </tr>
        <tr>
            <th colspan=2>Billing Address</th>
        </tr>

        <tr>
            <td>First name:</td>
            <td><stripes:text name="order.billToFirstName" /></td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td><stripes:text name="order.billToLastName" /></td>
        </tr>
        <tr>
            <td>Address 1:</td>
            <td><stripes:text size="40" name="order.billAddress1" /></td>
        </tr>
        <tr>
            <td>Address 2:</td>
            <td><stripes:text size="40" name="order.billAddress2" /></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><stripes:text name="order.billCity" /></td>
        </tr>
        <tr>
            <td>State:</td>
            <td><stripes:text size="4" name="order.billState" /></td>
        </tr>
        <tr>
            <td>Zip:</td>
            <td><stripes:text size="10" name="order.billZip" /></td>
        </tr>
        <tr>
            <td>Country:</td>
            <td><stripes:text size="15" name="order.billCountry" /></td>
        </tr>

        <tr>
            <td colspan=2><stripes:checkbox name="shippingAddressRequired" />
                Ship to different address...</td>
        </tr>

    </table>

<%--    <stripes:submit name="newOrder" value="Continue" />--%>

<%--</stripes:form></div>--%>

        <input type="submit" value="Confirm">
    </form>

<%@ include file="../common/bottom.jsp"%>
