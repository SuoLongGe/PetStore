
var elPassword = document.getElementById('newpassword'),
    elRepeatpassword = document.getElementById('newrepeatpassword'),
    elFirstname =document.getElementById('newfirstName'),
    elLastname =document.getElementById('newlastName'),
    elEmail =document.getElementById('newemail'),
    elPhone =document.getElementById('newphone'),
    elAddr1 =document.getElementById('newaddress1'),
    elCity =document.getElementById('newcity'),
    elState =document.getElementById('newstate'),
    elZip =document.getElementById('newzip'),
    elCountry =document.getElementById('newcountry'),
    elMsg1 = document.getElementById('feedback1'),
    elMsg2 = document.getElementById('feedback2'),
    elMsg4      = document.getElementById('feedback4'),
    elMsg5      = document.getElementById('feedback5'),
    elMsg6      = document.getElementById('feedback6'),
    elMsg7      = document.getElementById('feedback7'),
    elMsg8      = document.getElementById('feedback8'),
    elMsg9      = document.getElementById('feedback9'),
    elMsg10      = document.getElementById('feedback10'),
    elMsg11      = document.getElementById('feedback11'),
    elMsg12      = document.getElementById('feedback12');

window.onload = function (){
    elMsg1.textContent='请输入新密码！';
    elMsg2.textContent='请重复新密码！';
}
function checknewpassword(newpassword){
    if(newpassword == null ||newpassword == '' || newpassword.length == 0){
        elMsg1.textContent='请输入新密码！';
        return;
    }
    else{
        elMsg1.textContent='';
    }
}

function checknewrepeatpassword(newpassword,newrepeatpassword){
    if(newrepeatpassword == null ||newrepeatpassword == '' || newrepeatpassword.length == 0){
        elMsg2.textContent='请重复新密码！';
        return;
    }
    else if(newpassword != newrepeatpassword){
        elMsg2.textContent='两次新密码不一致！';
        return;
    }
    else{
        elMsg2.textContent='';
    }
}

elPassword.addEventListener('input',function (){
    var password = elPassword.value.trim();
    checknewpassword(password);
})

elRepeatpassword.addEventListener('input',function (){
    var password = elPassword.value.trim();
    var repeatpassword = elRepeatpassword.value.trim();
    checknewrepeatpassword(password,repeatpassword);
})

function checkfirstname(firstName){
    if(firstName == null || firstName == '' || firstName.length == 0){
        elMsg4.textContent='名不能为空！';
        return;
    }
    else{
        elMsg4.textContent='';
        return;
    }
}
elFirstname.addEventListener('input',function (){
    var firstName = elFirstname.value.trim();
    checkfirstname(firstName);
})

function checklastname(lastname){
    if(lastname == null || lastname == '' || lastname.length == 0){
        elMsg5.textContent='姓不能为空！';
        return;
    }
    else{
        elMsg5.textContent='';
        return;
    }
}
elLastname.addEventListener('input',function (){
    var lastname = elLastname.value.trim();
    checklastname(lastname);
})

function checkemail(email){
    if(email == null || email == '' || email.length == 0){
        elMsg6.textContent='邮箱不能为空！';
        return;
    }
    else{
        elMsg6.textContent='';
        return;
    }
}
elEmail.addEventListener('input',function (){
    var email = elEmail.value.trim();
    checkemail(email);
})

function checkphone(phone){
    if(phone == null || phone == '' || phone.length == 0){
        elMsg7.textContent='电话不能为空！';
        return;
    }
    else{
        elMsg7.textContent='';
        return;
    }
}
elPhone.addEventListener('input',function (){
    var phone = elPhone.value.trim();
    checkphone(phone);
})

function checkaddr1(addr1){
    if(addr1 == null || addr1 == '' || addr1.length == 0){
        elMsg8.textContent='联系地址1不能为空！';
        return;
    }
    else{
        elMsg8.textContent='';
        return;
    }
}
elAddr1.addEventListener('input',function (){
    var addr1 = elAddr1.value.trim();
    checkaddr1(addr1);
})

function checkcity(city){
    if(city == null || city == '' || city.length == 0){
        elMsg9.textContent='居住城市不能为空！';
        return;
    }
    else{
        elMsg9.textContent='';
        return;
    }
}
elCity.addEventListener('input',function (){
    var city = elCity.value.trim();
    checkcity(city);
})

function checkstate(state){
    if(state == null || state == '' || state.length == 0){
        elMsg10.textContent='状态不能为空！';
        return;
    }
    else{
        elMsg10.textContent='';
        return;
    }
}
elState.addEventListener('input',function (){
    var state = elState.value.trim();
    checkstate(state);
})

function checkzip(zip){
    if(zip == null || zip == '' || zip.length == 0){
        elMsg11.textContent='邮箱不能为空！';
        return;
    }
    else{
        elMsg11.textContent='';
        return;
    }
}
elZip.addEventListener('input',function (){
    var zip = elZip.value.trim();
    checkzip(zip);
})

function checkcountry(country){
    if(country == null || country == '' || country.length == 0){
        elMsg12.textContent='居住国家不能为空！';
        return;
    }
    else{
        elMsg12.textContent='';
        return;
    }
}
elCountry.addEventListener('input',function (){
    var country = elCountry.value.trim();
    checkcountry(country);
})