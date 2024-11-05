<%@ include file="../common/top.jsp"%>

<div id="BackLink">
  <a href="mainForm">Return to Main Menu</a>
</div>

<div id="Catalog">

  <table>
    <tr>
      <th>&nbsp;</th>
      <th>Product ID</th>
      <th>Name</th>
    </tr>
    <c:forEach var="product" items="${sessionScope.productList}">
      <tr>
        <td>
        <a href="productForm?productId=${product.productId}">${product.productId}</a>
        </td>
        <td>
<%--          <b> <stripes:link--%>
<%--                beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"--%>
<%--                event="viewProduct">--%>
<%--          <stripes:param name="productId" value="${product.productId}" />--%>
<%--          <font color="BLACK"> ${product.productId} </font>--%>
<%--        </stripes:link> </b>--%>
          <a href="productForm?productId=${product.productId}">${product.productId}</a>
        </td>
        <td>${product.name}</td>
      </tr>
    </c:forEach>
    <tr>
      <td></td>
    </tr>

  </table>

</div>

<%@ include file="../common/bottom.jsp"%>
