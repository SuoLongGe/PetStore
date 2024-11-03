<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h3>Account Information</h3>
<table>
  <tr>
    <td>First name:</td>
    <td><input type="text"  class="user-form-input2" name="firstName" id="firstName" placeholder="请输入姓" autocomplete="off"></td>
  </tr>
  <tr>
    <td>Last name:</td>
    <td><input type="text"  class="user-form-input2" name="lastName" id="lastName" placeholder="请输入名" autocomplete="off"></td>
  </tr>
  <tr>
    <td>Email:</td>
    <td><input type="text"  class="user-form-input2" name="email" id="email" placeholder="请输入邮箱" autocomplete="off"></td>
  </tr>
  <tr>
    <td>Phone:</td>
    <td><input type="text"  class="user-form-input2" name="phone" id="phone" placeholder="请输入电话号码" autocomplete="off"></td>
  </tr>
  <tr>
    <td>Address 1:</td>
    <td><input type="text"  class="user-form-input2" name="address1" id="address1" placeholder="请输入联系地址1" autocomplete="off"></td>
  </tr>
  <tr>
    <td>Address 2:</td>
    <td><input type="text"  class="user-form-input2" name="address2" id="address2" placeholder="请输入联系地址2(选填)" autocomplete="off"></td>
  </tr>
  <tr>
    <td>City:</td>
    <td><input type="text"  class="user-form-input2" name="city" id="city" placeholder="请输入所在城市" autocomplete="off"></td>
  </tr>
  <tr>
    <td>State:</td>
    <td><input type="text"  class="user-form-input2" name="state" id="state" placeholder="请输入状态" autocomplete="off"></td>
  </tr>
  <tr>
    <td>Zip:</td>
    <td><input type="text"  class="user-form-input2" name="zip" id="zip" placeholder="请输入邮编" autocomplete="off"></td>
  </tr>
  <tr>
    <td>Country:</td>
    <td><input type="text"  class="user-form-input2" name="country" id="country" placeholder="请输入所在国家" autocomplete="off"></td>
  </tr>
</table>

<h3>Profile Information</h3>

<table>
  <tr>
    <td>Language Preference:</td>
    <td>
      <select id="language" name="account.languagePreference" style="padding: 8px 30px; font-size: 20px">
        <option value="English" >English</option>
        <option value="Chinese" >Spanish</option>
        <option value="French" >French</option>
        <option value="German" >German</option>
      </select>
    </td>
  </tr>
  <tr>
    <td>Favourite Category:</td>
    <td>
      <select id="favouriteCategoryId" name="account.favouriteCategoryId" style="padding: 8px 30px; font-size: 20px">
        <option value="FISH" >Fish</option>
        <option value="DOGS" >Dogs</option>
        <option value="REPTILES" >Reptiles</option>
        <option value="CATS" >Cats</option>
        <option value="BIRDS" >Birds</option>
      </select>
    </td>
  </tr>
  <tr>
    <td>Enable MyList</td>
    <td>
      <input type="checkbox" style="width: 30px;height: 30px;" name="account.listOption" value="EnableMyList"></td>
  </tr>
  <tr>
    <td>Enable MyBanner</td>
    <td>
      <input type="checkbox" style="width: 30px;height: 30px;" name="account.bannerOption" value="EnableMyBanner">
    </td>
  </tr>

</table>
