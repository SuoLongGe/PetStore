
<c:if test="${!empty sessionScope.myList}">
    <div id="MyListContainer">
        <h3>Pet Favorites</h3>
        <p>Shop for more of your favorite pets here:</p>
        <ul>
            <c:forEach var="product" items="${sessionScope.myList}">
                <li>
                    <a href="productForm?productId=${product.productId}">
                            ${product.name}
                    </a>
                    (${product.productId})
                </li>
            </c:forEach>
        </ul>
    </div>

</c:if>
