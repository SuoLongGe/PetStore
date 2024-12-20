<%@ include file="../common/top.jsp"%>
<head>
    <link rel="StyleSheet" href="css/category.css" type="text/css" media="screen" />
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

    <script>
        $(document).ready(function() {
            // QuickLinks 逻辑
            $('#QuickLinks a').on('click', function(event) {
                console.log('Link clicked!');
                event.preventDefault();
                console.log('event.preventDefault() was called.');

                var categoryId = $(this).attr('href').split('=')[1];
                var $activeLink = $(this);

                $.ajax({
                    url: 'categoryForm',
                    type: 'GET',
                    data: { categoryId: categoryId },
                    beforeSend: function(xhr) {
                        xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
                    },
                    success: function(response) {
                        $('#productTableContainer').html(response);
                        $('#QuickLinks a').removeClass('active');
                        $activeLink.addClass('active');
                    },
                    error: function() {
                        alert('Error loading data.');
                    }
                });
            });

            // 轮播图逻辑
            let currentIndex = 0;
            const totalCards = $('.carousel-card').length;

            function updateCarousel() {
                $('#carousel').css('transform', 'translateX(' + (-currentIndex * 100) + '%)');
            }

            function goToNextSlide() {
                currentIndex = (currentIndex + 1) % totalCards;
                updateCarousel();
            }

            function goToPrevSlide() {
                currentIndex = (currentIndex - 1 + totalCards) % totalCards;
                updateCarousel();
            }

            // 点击右侧按钮
            $('#next-btn').on('click', function() {
                goToNextSlide();
            });

            // 点击左侧按钮
            $('#prev-btn').on('click', function() {
                goToPrevSlide();
            });

            // 监听轮播动画完成
            $('#carousel').on('transitionend', function() {
                if (currentIndex === totalCards) {
                    currentIndex = 0;
                    $('#carousel').css('transition', 'none');  // 禁用过渡
                    updateCarousel();
                    setTimeout(function() {
                        $('#carousel').css('transition', 'transform 0.5s ease-in-out');  // 恢复过渡
                    }, 50);
                }
            });

            // 初始化显示第一个卡片
            updateCarousel();
        });
    </script>
</head>

<div id="BackLink">
    <a href="mainForm">Return to Main Menu</a>
</div>

<div id="Catalog">
    <!-- 卡片轮播 -->
    <div id="carousel-container">
        <div id="carousel">
            <div class="carousel-card">
                <i class="fas fa-paw"></i> Best Deals for Pets
            </div>
            <div class="carousel-card">
                <i class="fas fa-tags"></i> 30% Off on All Accessories
            </div>
            <div class="carousel-card">
                <i class="fas fa-dog"></i> Adopt a Dog Today
            </div>
            <div class="carousel-card">
                <i class="fas fa-cat"></i> Special Offers for Cats
            </div>
            <div class="carousel-card">
                <i class="fas fa-dove"></i> Exotic Birds Available
            </div>
        </div>
        <!-- 左右导航按钮 -->
        <button id="prev-btn" aria-label="Previous">
            <i class="fas fa-chevron-left"></i>
        </button>
        <button id="next-btn" aria-label="Next">
            <i class="fas fa-chevron-right"></i>
        </button>
    </div>

    <!-- 分类标题 -->
    <h2>Our Category</h2>

    <!-- 快速链接 -->
    <c:set var="currentCategoryName" value="${sessionScope.category.name}" />
    <div id="QuickLinks">
        <a href="categoryForm?categoryId=FISH" class="${currentCategoryName == 'Fish' ? 'active' : ''}">Fish</a>
        <a href="categoryForm?categoryId=DOGS" class="${currentCategoryName == 'Dogs' ? 'active' : ''}">Dogs</a>
        <a href="categoryForm?categoryId=REPTILES" class="${currentCategoryName == 'Reptiles' ? 'active' : ''}">Reptiles</a>
        <a href="categoryForm?categoryId=CATS" class="${currentCategoryName == 'Cats' ? 'active' : ''}">Cats</a>
        <a href="categoryForm?categoryId=BIRDS" class="${currentCategoryName == 'Birds' ? 'active' : ''}">Birds</a>
    </div>

    <!-- 产品表格 -->
    <div id="productTableContainer">
        <table>
            <tr>
                <th>Product ID</th>
                <th>Name</th>
            </tr>
            <c:forEach var="product" items="${sessionScope.productList}">
                <tr>
                    <td>
                        <a href="productForm?productId=${product.productId}">${product.productId}</a>
                    </td>
                    <td>${product.name}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<%@ include file="../common/bottom.jsp"%>
