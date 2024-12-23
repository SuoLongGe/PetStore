
var elUsername = document.getElementById('username'),
    elPassword = document.getElementById('password'),
    elCaptcha  = document.getElementById('captcha'),
    elCheck = document.getElementById('check'),
    elMsg1      = document.getElementById('feedback1'),
    elMsg2      = document.getElementById('feedback2'),
    elMsg4      = document.getElementById('feedback4'),
    elMsg5      = document.getElementById('feedback5'),
    elMsg6      = document.getElementById('feedback6'),
    elMsg3      = document.getElementById('feedback3');

var xhr='';

function sendRequest(username,password){
    xhr = new XMLHttpRequest();
    xhr.onreadystatechange = process;
    xhr.open('GET','signonCheck?username=' + username + '&password=' + password);
    xhr.send(null);
}

function sendCaptcha(captcha){
    xhr = new XMLHttpRequest();
    xhr.onreadystatechange = process1;
    xhr.open('GET', 'getCaptcha?captcha=' + captcha);
    xhr.send(null);
}

function  process1(){
    if(xhr.readyState==4){
        if(xhr.status==200){
            var responseInfo = xhr.responseText;
            console.log(responseInfo);
            if(responseInfo == 'yes'){
                elMsg6.textContent='匹配！';
                elMsg3.textContent='';
            }
            else {
                elMsg3.textContent='验证码不匹配!';
                elMsg6.textContent='';
            }
        }
    }
}

function  process(){
    if(xhr.readyState==4){
        if(xhr.status==200){
            var inputCaptcha = elCaptcha.value.trim();
            var responseInfo = xhr.responseText;
            console.log(responseInfo);
            if(responseInfo=='None'){
                elMsg4.textContent='用户名与密码不匹配！';
                elMsg5.textContent='';
            }else{
                elMsg5.textContent='success';
                elMsg4.textContent='';
            }
        }
    }
}

function checkUsername(username){
    if(username == null ||username == '' || username.length == 0){
        elMsg1.textContent='用户名不能为空！';
        return;
    }
    else{
        elMsg1.textContent='';
        return;
    }
}

function checkPassword(password){
    if(password == null || password == '' || password.length == 0){
        elMsg2.textContent='密码不能为空！';
        return;
    }
    else{
        elMsg2.textContent='';
        return;
    }
}

function checkCaptcha(captcha){
    if(captcha == null || captcha == '' || captcha.length == 0){
        elMsg3.textContent='验证码不能为空！';
        return;
    }
    sendCaptcha(captcha);
}

elUsername.addEventListener('input',function (){
    var username = elUsername.value.trim();
    checkUsername(username);
})

elCaptcha.addEventListener('input',function (){
    var captcha = elCaptcha.value.trim();
    var password = elPassword.value.trim();
    checkPassword(password);
    checkCaptcha(captcha);
})

elPassword.addEventListener('change',function (){
    var username = elUsername.value.trim();
    var password = elPassword.value.trim();
    sendRequest(username,password);
})