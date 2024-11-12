
<%@ include file="../common/top.jsp"%>


<div id="Welcome">
    <div id="WelcomeContent">
<%--    显示登陆用户的firstname--%>
    </div>
</div>

<div id="Main">
    <div id="Sidebar">
        <div id="SidebarContent">

            <div class="category-card">
            <a href="categoryForm?categoryId=FISH"><img src="images/fish_icon.gif" /></a><br />
            </div>
            Saltwater, Freshwater <br />

            <div class="category-card">
            <a href="categoryForm?categoryId=DOGS"><img src="images/dogs_icon.gif" /></a><br />
            </div>
            Various Breeds <br />

            <div class="category-card">
            <a href="categoryForm?categoryId=CATS"><img src="images/cats_icon.gif" /></a><br />
            </div>
            Various Breeds, Exotic Varieties <br />

            <div class="category-card">
            <a href="categoryForm?categoryId=REPTILES"><img src="images/reptiles_icon.gif" /></a><br />
            </div>
            Lizards, Turtles, Snakes <br />

            <div class="category-card">
            <a href="categoryForm?categoryId=BIRDS"><img src="images/birds_icon.gif" /></a><br />
            </div>
            Exotic Varieties
        </div>
    </div>

    <div id="MainImage">
        <div id="MainImageContent">
            <map name="estoremap">
                <area alt="Birds" coords="72,2,280,250"
                      href="categoryForm?categoryId=BIRDS" shape="RECT" />
                <area alt="Fish" coords="2,180,72,250"
                      href="categoryForm?categoryId=FISH" shape="RECT" />
                <area alt="Dogs" coords="60,250,130,320"
                      href="categoryForm?categoryId=DOGS" shape="RECT" />
                <area alt="Reptiles" coords="140,270,210,340"
                      href="categoryForm?categoryId=REPTILES" shape="RECT" />
                <area alt="Cats" coords="225,240,295,310"
                      href="categoryForm?categoryId=CATS" shape="RECT" />
                <area alt="Birds" coords="280,180,350,250"
                      href="categoryForm?categoryId=BIRDS" shape="RECT" />
            </map>
            <img height="355" src="images/splash.gif" align="middle"
                 usemap="#estoremap" width="350" /></div>
    </div>

    <div id="Separator">&nbsp;</div>
</div>
<%@ include file="../common/bottom.jsp"%>

