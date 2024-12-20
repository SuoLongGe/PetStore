<%@ include file="../common/top.jsp"%>
<head>
    <link rel="stylesheet" href="css/item.css" type="text/css">
</head>

<div id="BackLink">
    <a href="productForm?productId=${sessionScope.product.productId}">
        Return to ${sessionScope.product.productId}
    </a>
</div>

<div id="Catalog">

    <!-- 左侧内容 -->
    <div class="catalog-left">

        <!-- 产品标题 -->
        <h1 class="product-title">${sessionScope.product.name}</h1>

        <!-- 产品价格 -->
        <div class="product-price">
            <fmt:formatNumber value="${sessionScope.item.listPrice}" pattern="$#,##0.00" />
        </div>

        <!-- 产品评分 -->
        <div class="product-rating">
            <span class="rating-stars">&#9733;&#9733;&#9733;&#9733;&#9734;</span>
            4.6 / 5.0 (556 reviews)
        </div>

        <!-- 库存状态 -->
        <div class="product-stock">
            <c:if test="${sessionScope.item.quantity <= 0}">
                <span class="out-of-stock">Back ordered.</span>
            </c:if>
            <c:if test="${sessionScope.item.quantity > 0}">
                ${sessionScope.item.quantity} in stock.
            </c:if>
        </div>

        <!-- 购买数量和按钮 -->
        <form action="addItemToCart" method="get">
            <!-- 商品ID -->
            <input type="hidden" name="workingItemId" value="${sessionScope.item.itemId}">

            <!-- 数量输入框 -->
            <label for="quantityInput" class="quantity-label">Qty:</label>
            <input type="number" id="quantityInput" name="quantity" value="1" min="1" class="quantity-input">

            <!-- 购买按钮 -->
            <button type="submit" class="add-to-cart-button">Add to Cart</button>
        </form>

        <!-- 额外说明 -->
        <div class="product-shipping-info">
            3-5 days free shipping. No assembly tools required. 30-day return policy.
        </div>

    </div>

    <!-- 右侧描述内容 -->
    <div class="catalog-right">
        <p>${sessionScope.product.description}</p>
    </div>

</div>

<%@ include file="../common/bottom.jsp"%>
