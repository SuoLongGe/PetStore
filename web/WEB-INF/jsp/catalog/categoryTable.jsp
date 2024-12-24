<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="productTableContainer">
  <table>
    <tr>
      <th>Product ID</th>
      <th>Name</th>
    </tr>
    <c:choose>
      <c:when test="${not empty sessionScope.productList}">
        <c:forEach var="product" items="${sessionScope.productList}">
          <tr>
            <td><a href="productForm?productId=${product.productId}">${product.productId}</a></td>
            <td>${product.name}</td>
          </tr>
        </c:forEach>
      </c:when>
      <c:otherwise>
        <tr><td colspan="2">No products found.</td></tr>
      </c:otherwise>
    </c:choose>
  </table>
</div>
