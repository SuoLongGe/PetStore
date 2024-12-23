
var inform1 = document.getElementById("inform1");
var inform2 = document.getElementById("inform2");
var inform3 = document.getElementById("inform3");
var inform4 = document.getElementById("inform4");
var inform5 = document.getElementById("inform5");

var xhr='';

function showInform1(categoryId) {
    console.log(categoryId);
    sendRequest1(categoryId);
}
function sendRequest1(categoryId) {
    xhr = new XMLHttpRequest();
    xhr.onreadystatechange = processResponse1;
    xhr.open('GET','showMsg?categoryId=' + categoryId);
    xhr.send(null);
}
function processResponse1() {
    if (xhr.readyState === 4) {
        if (xhr.status === 200) {
            var resp = xhr.responseText;
            //显示悬浮层

            inform1.innerText = resp;
            inform1.style.display = "block";
        }
    }
}
function hiddenInform1(event){
    var informDiv = document.getElementById('inform1');
    var x=event.clientX;
    var y=event.clientY;
    var divx1 = informDiv.offsetLeft;
    var divy1 = informDiv.offsetTop;
    var divx2 = informDiv.offsetLeft + informDiv.offsetWidth;
    var divy2 = informDiv.offsetTop + informDiv.offsetHeight;
    if( x < divx1 || x > divx2 || y < divy1 || y > divy2){
        document.getElementById('inform1').style.display='none';
    }
}



function showInform2(categoryId) {
    console.log(categoryId);
    sendRequest2(categoryId);
}
function sendRequest2(categoryId) {
    xhr = new XMLHttpRequest();
    xhr.onreadystatechange = processResponse2;
    xhr.open('GET','showMsg?categoryId=' + categoryId);
    xhr.send(null);
}
function processResponse2() {
    if (xhr.readyState === 4) {
        if (xhr.status === 200) {
            var resp = xhr.responseText;
            //显示悬浮层

            inform2.innerText = resp;
            inform2.style.display = "block";
        }
    }
}
function hiddenInform2(event){
    var informDiv = document.getElementById('inform2');
    var x=event.clientX;
    var y=event.clientY;
    var divx1 = informDiv.offsetLeft;
    var divy1 = informDiv.offsetTop;
    var divx2 = informDiv.offsetLeft + informDiv.offsetWidth;
    var divy2 = informDiv.offsetTop + informDiv.offsetHeight;
    if( x < divx1 || x > divx2 || y < divy1 || y > divy2){
        document.getElementById('inform2').style.display='none';
    }
}



function showInform3(categoryId) {
    console.log(categoryId);
    sendRequest3(categoryId);
}
function sendRequest3(categoryId) {
    xhr = new XMLHttpRequest();
    xhr.onreadystatechange = processResponse3;
    xhr.open('GET','showMsg?categoryId=' + categoryId);
    xhr.send(null);
}
function processResponse3() {
    if (xhr.readyState === 4) {
        if (xhr.status === 200) {
            var resp = xhr.responseText;
            //显示悬浮层

            inform3.innerText = resp;
            inform3.style.display = "block";
        }
    }
}
function hiddenInform3(event){
    var informDiv = document.getElementById('inform3');
    var x=event.clientX;
    var y=event.clientY;
    var divx1 = informDiv.offsetLeft;
    var divy1 = informDiv.offsetTop;
    var divx2 = informDiv.offsetLeft + informDiv.offsetWidth;
    var divy2 = informDiv.offsetTop + informDiv.offsetHeight;
    if( x < divx1 || x > divx2 || y < divy1 || y > divy2){
        document.getElementById('inform3').style.display='none';
    }
}



function showInform4(categoryId) {
    console.log(categoryId);
    sendRequest4(categoryId);
}
function sendRequest4(categoryId) {
    xhr = new XMLHttpRequest();
    xhr.onreadystatechange = processResponse4;
    xhr.open('GET','showMsg?categoryId=' + categoryId);
    xhr.send(null);
}
function processResponse4() {
    if (xhr.readyState === 4) {
        if (xhr.status === 200) {
            var resp = xhr.responseText;
            //显示悬浮层

            inform4.innerText = resp;
            inform4.style.display = "block";
        }
    }
}
function hiddenInform4(event){
    var informDiv = document.getElementById('inform4');
    var x=event.clientX;
    var y=event.clientY;
    var divx1 = informDiv.offsetLeft;
    var divy1 = informDiv.offsetTop;
    var divx2 = informDiv.offsetLeft + informDiv.offsetWidth;
    var divy2 = informDiv.offsetTop + informDiv.offsetHeight;
    if( x < divx1 || x > divx2 || y < divy1 || y > divy2){
        document.getElementById('inform4').style.display='none';
    }
}



function showInform5(categoryId) {
    console.log(categoryId);
    sendRequest5(categoryId);
}
function sendRequest5(categoryId) {
    xhr = new XMLHttpRequest();
    xhr.onreadystatechange = processResponse5;
    xhr.open('GET','showMsg?categoryId=' + categoryId);
    xhr.send(null);
}
function processResponse5() {
    if (xhr.readyState === 4) {
        if (xhr.status === 200) {
            var resp = xhr.responseText;
            //显示悬浮层

            inform5.innerText = resp;
            inform5.style.display = "block";
        }
    }
}
function hiddenInform5(event){
    var informDiv = document.getElementById('inform5');
    var x=event.clientX;
    var y=event.clientY;
    var divx1 = informDiv.offsetLeft;
    var divy1 = informDiv.offsetTop;
    var divx2 = informDiv.offsetLeft + informDiv.offsetWidth;
    var divy2 = informDiv.offsetTop + informDiv.offsetHeight;
    if( x < divx1 || x > divx2 || y < divy1 || y > divy2){
        document.getElementById('inform5').style.display='none';
    }
}
