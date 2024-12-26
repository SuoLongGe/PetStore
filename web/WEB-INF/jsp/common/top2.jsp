
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html >
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html >

<head>
    <link rel="StyleSheet" href="css/user.css" type="text/css"
          media="screen" />
    <link rel="StyleSheet" href="css/order.css">
    <title>Genius's PetStore</title>
</head>
<body>
<script>
    // JavaScript 控制步骤切换
    function showStep(stepIndex) {
        const steps = document.querySelectorAll('.step-content');
        const indicators = document.querySelectorAll('.step-indicator .step');
        const orderButton = document.querySelector('.order-details button');

        // 切换步骤内容显示状态
        steps.forEach((step, index) => {
            if (index === stepIndex) {
                step.classList.add('active');
            } else {
                step.classList.remove('active');
            }
        });

        // 切换步骤指示器的样式
        indicators.forEach((indicator, index) => {
            if (index === stepIndex) {
                indicator.classList.add('active');
            } else {
                indicator.classList.remove('active');
            }
        });

        // 根据当前步骤设置订单按钮状态
        if (stepIndex === 2) {
            orderButton.classList.remove('disabled');
            orderButton.classList.add('enabled');
            orderButton.disabled = false;
        } else {
            orderButton.classList.remove('enabled');
            orderButton.classList.add('disabled');
            orderButton.disabled = true;
        }
    }

    // 仅在按钮启用时执行操作
    function changeFormAction() {
        const orderButton = document.querySelector('.order-details button');
        const form = document.querySelector('form');  // 确保获取到表单

        // 检查按钮是否启用
        if (!orderButton.disabled) {
            // 执行提交操作
            form.submit();  // 提交表单
        }
    }
    document.getElementById('ComformOrderButton').addEventListener('click', function() {
        document.getElementById('orderForm').submit();
    });
</script>

<div id="Header">

    <div id="Logo">
        <div id="LogoContent">
            <a href="mainForm"><img src="images/logo-topbar.gif" /></a>
        </div>
    </div>



</div>

<div id="Content">