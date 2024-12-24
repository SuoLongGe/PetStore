

</div>

<div id="Footer">

    <div id="PoweredBy">&nbsp<a href="http://www.csu.edu.cn">www.csu.edu.cn</a>
    </div>

    <div id="Banner">
      <c:if test="${sessionScope.loginAccount != null }">
        <c:if test="${sessionScope.loginAccount.bannerOption}">
            ${sessionScope.loginAccount.bannerName}
        </c:if>
      </c:if>
    </div>

</div>
<script>
    function loginSuccess() {
        alert("Login successful, welcome to the PetStore!!");
    }
    function OrderSuccess() {
        alert("Order successfully created!!!!");
    }
</script>

<script src="js/productAuto.js"></script>
<script src="js/order.js"></script>

</body>
</html>
