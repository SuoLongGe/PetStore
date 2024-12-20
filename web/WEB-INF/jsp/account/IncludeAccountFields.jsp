<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h3 style="font-size: 25px">Account Information</h3>

<div class="user-form-item1">
  <p style="color: #344978;font-size: 20px">First name</p>
  <input type="text" class="user-form-input1" name="firstname" id="firstname"  autocomplete="off">
  <div  id="feedback4" style="color: red" > </div>
</div>

<div class="user-form-item1">
  <p style="color: #344978;font-size: 20px">Last name</p>
  <input type="text" class="user-form-input1" name="lastname" id="lastname"  autocomplete="off">
  <div  id="feedback5" style="color: red" > </div>
</div>

<div class="user-form-item1">
  <p style="color: #344978;font-size: 20px; margin-right: 43px;">Email</p>
  <input type="text" class="user-form-input1" name="email" id="email"  autocomplete="off">
  <div  id="feedback6" style="color: red" > </div>
</div>

<div class="user-form-item1">
  <p style="color: #344978;font-size: 20px; margin-right: 35px;">Phone     </p>
  <input type="text" class="user-form-input1" name="phone" id="phone"  autocomplete="off">
  <div  id="feedback7" style="color: red" > </div>
</div>

<div class="user-form-item1">
  <p style="color: #344978;font-size: 20px">Address 1 </p>
  <input type="text" class="user-form-input1" name="address1" id="address1"  autocomplete="off">
  <div  id="feedback8" style="color: red" > </div>
</div>

<div class="user-form-item1">
  <p style="color: #344978;font-size: 20px">Address 2</p>
  <input type="text" class="user-form-input1" name="address2" id="address2"  autocomplete="off">

</div>

<div class="user-form-item1">
  <p style="color: #344978;font-size: 20px; margin-right: 51px;">City     </p>
  <input type="text" class="user-form-input1" name="city" id="city"  autocomplete="off">
  <div  id="feedback9" style="color: red" > </div>
</div>

<div class="user-form-item1">
  <p style="color: #344978;font-size: 20px; margin-right: 40px;">State    </p>
  <input type="text" class="user-form-input1" name="state" id="state"  autocomplete="off">
  <div  id="feedback10" style="color: red" > </div>
</div>

<div class="user-form-item1">
  <p style="color: #344978;font-size: 20px; margin-right: 55px;">Zip     </p>
  <input type="text" class="user-form-input1" name="zip" id="zip"  autocomplete="off">
  <div  id="feedback11" style="color: red" > </div>
</div>

<div class="user-form-item1">
  <p style="color: #344978;font-size: 20px; margin-right: 15px;">Country </p>
  <input type="text" class="user-form-input1" name="country" id="country"  autocomplete="off">
  <div  id="feedback12" style="color: red" > </div>
</div>

<h3 style="font-size: 25px">Profile Information</h3>

<div class="user-form-item1">
  <p style="color: #344978;font-size: 20px; margin-right: 3px;">Language Preference</p>
  <select id="language" name="account.languagePreference" style="border-radius:8px; padding: 8px 30px; font-size: 20px">
    <option value="English" >English</option>
    <option value="Chinese" >Spanish</option>
    <option value="French" >French</option>
    <option value="German" >German</option>
  </select>
</div>

<div class="user-form-item1">
  <p style="color: #344978;font-size: 20px; margin-right: 13px;">Favourite Category</p>
  <select id="favouriteCategoryId" name="account.favouriteCategoryId" style="border-radius:8px;padding: 8px 30px; font-size: 20px">
    <option value="FISH" >Fish</option>
    <option value="DOGS" >Dogs</option>
    <option value="REPTILES" >Reptiles</option>
    <option value="CATS" >Cats</option>
    <option value="BIRDS" >Birds</option>
  </select>
</div>

<div class="user-form-item1">
  <input type="checkbox" class="custom-checkbox" style="border-radius:8px; width: 30px;height: 30px;" name="account.listOption" value="EnableMyList"></td>
  <p style="color: black;font-size: 20px; margin-right: 40px;"> &nbsp; I'll receive "MyList" to see my want </p>
</div>

<div class="user-form-item1">
  <input type="checkbox" class="custom-checkbox" style="border-radius:8px; width: 30px;height: 30px;" name="account.bannerOption" value="EnableMyBanner">
  <p style="color: black;font-size: 20px; margin-right: 30px;"> &nbsp; I'll receive "MyBanner" to see my favourite category</p>
</div>

<script src="js/check-userinfo.js"> </script>

