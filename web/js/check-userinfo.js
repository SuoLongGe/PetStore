
var elUsername =document.getElementById('username'),
    elPassword =document.getElementById('password'),
    elRepeatPassword =document.getElementById('repeatpassword'),
    elFirstname =document.getElementById('firstname'),
    elLastname =document.getElementById('lastname'),
    elEmail =document.getElementById('email'),
    elPhone =document.getElementById('phone'),
    elAddr1 =document.getElementById('address1'),
    elCity =document.getElementById('city'),
    elState =document.getElementById('state'),
    elZip =document.getElementById('zip'),
    elCountry =document.getElementById('country'),
    elMsg1      = document.getElementById('feedback1'),
    elMsg2      = document.getElementById('feedback2'),
    elMsg3      = document.getElementById('feedback3'),
    elMsg4      = document.getElementById('feedback4'),
    elMsg5      = document.getElementById('feedback5'),
    elMsg6      = document.getElementById('feedback6'),
    elMsg7      = document.getElementById('feedback7'),
    elMsg8      = document.getElementById('feedback8'),
    elMsg9      = document.getElementById('feedback9'),
    elMsg10      = document.getElementById('feedback10'),
    elMsg11      = document.getElementById('feedback11'),
    elMsg12      = document.getElementById('feedback12');

var xhr='';

function checkUsername(username){
    if(username == null ||username == '' || username.length == 0){
        elMsg1.textContent='用户名不能为空！';
        return;
    }
    sendRequest(username);
}

function checkPassword(password){
    if(password == null || password == '' || password.length == 0){
        elMsg2.textContent='密码不能为空！';
        return;
    }
    else if(password<=3){
        elMsg2.textContent='密码不能少于四位数！';
        return;
    }
    else{
        elMsg2.textContent='';
        return;
    }
}

function checkRepeatPassword(password,repeatpassword){
    if(repeatpassword != password){
        elMsg3.textContent='重复密码与原密码不一致！';
        return;
    }
    else{
        elMsg3.textContent='';
        return;
    }
}

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

function sendRequest(username){
    xhr = new XMLHttpRequest();
    xhr.onreadystatechange = process;
    xhr.open('GET','userinfoExist?username='+username);
    xhr.send(null);
}

function  process(){
    if(xhr.readyState==4){
        if(xhr.status==200){
            var responseInfo = xhr.responseText;
            console.log(responseInfo);
            if(responseInfo=='Exist'){
                elMsg1.textContent='用户名已存在！';
            }else{
                elMsg1.textContent='';
            }
        }
    }
}
elUsername.addEventListener('blur' , function (){
    var username = elUsername.value.trim();
    checkUsername(username);
})

elPassword.addEventListener('blur',function () {
    var password = elPassword.value.trim();
    checkPassword(password);
})
elRepeatPassword.addEventListener('blur',function (){
    var password = elPassword.value.trim();
    var repeatpassword = elRepeatPassword.value.trim();
    checkRepeatPassword(password,repeatpassword);
})


elFirstname.addEventListener('blur',function (){
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
elLastname.addEventListener('blur',function (){
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
elEmail.addEventListener('blur',function (){
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
elPhone.addEventListener('blur',function (){
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
elAddr1.addEventListener('blur',function (){
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
elCity.addEventListener('blur',function (){
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
elState.addEventListener('blur',function (){
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
elZip.addEventListener('blur',function (){
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
elCountry.addEventListener('blur',function (){
    var country = elCountry.value.trim();
    checkcountry(country);
})