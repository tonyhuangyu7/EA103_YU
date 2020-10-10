<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.front_inform.model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>後台首頁</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/style.css">


</head>
<body>

	<div class="main_content">
		<aside class="aside">
			<div class="firstLine">
				<div class="r1" style="font-size: 20px; font-weight: 900;">
					EMP0009</div>
				<div class="msg">
					<img src="<%=request.getContextPath()%>/back-end/images/help.png" alt="">
				</div>
				<button id="bigOne" style="line-height: 2;">
					<svg width="1em" height="1em" viewBox="0 0 16 16"
						class="bi bi-list" fill="currentColor"
						xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd"
							d="M2.5 11.5A.5.5 0 0 1 3 11h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 7h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 3h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z" />
                    </svg>
				</button>
				<button id="smallOne" style="line-height: 2;">
					<svg width="1em" height="1em" viewBox="0 0 16 16"
						class="bi bi-list" fill="currentColor"
						xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd"
							d="M2.5 11.5A.5.5 0 0 1 3 11h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 7h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 3h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z" />
                    </svg>
				</button>
			</div>
			<div class="bottom">
				<div class="me">
					<div class="func">員工登入</div>
					<div class="func">
						<a
							href="<%=request.getContextPath()%>/back-end/front_inform/empCheckInform.jsp"
							style="color: white; text-decoration: none;">查看通知</a>
					</div>
				</div>
				<div class="manager">
					<div class="func">員工管理</div>
					<div class="func">會員管理</div>
					<div class="func">通知管理</div>
					<div class="func">廣告管理</div>
					<div class="func">用餐營業時段管理</div>
					<div class="func">桌位管理</div>
					<div class="func">菜單管理</div>
					<div class="func">餐點組成管理</div>
					<div class="func">紅利商品管理</div>
				</div>
				<div class="me">
					<div class="func">最新消息管理</div>
					<div class="func">現場點餐</div>
					<div class="func">現場劃位</div>
					<div class="func">候位管理</div>
					<div class="func">訂單結帳</div>
					<div class="func">訂單派工</div>
					<div class="func">出餐管理</div>
					<div class="func">訂單管理</div>
					<div class="func">訂餐管理</div>
					<div class="func">訂餐訂單處理</div>
					<div class="func">訂位管理</div>
					<div class="func">評價管理</div>
					<div class="func">食材管理</div>
					<div class="func">食材消耗統計</div>
				</div>
			</div>
		</aside>
		<main class="main">
			<div class="rheader">
				<h1>吃 Pot 吧！員工專區</h1>
			</div>
			<div class="content"></div>
		</main>
	</div>

	<script src="<%=request.getContextPath()%>/back-end/js/jquery.min.js"></script>
	<script>
	var bottom = $("div.bottom");
	var content = $("div.content");
	var cWidth = $("div.content").width();
	$(function(){
		$("#bigOne").on('click', function(){
			bottom.stop().animate({height:'toggle'},1000);
			content.stop().animate({width:((cWidth=='940px')?'1200px':'940px'),});
		});
		$("#smallOne").on('click', function(){
	 		bottom.stop().animate({height: 'toggle'},"slow");
		});
	});
	</script>
	<script src="<%=request.getContextPath()%>/back-end/js/jquery-migrate-3.0.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/js/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/js/jquery.easing.1.3.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/js/jquery.waypoints.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/js/jquery.stellar.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/js/owl.carousel.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/js/jquery.magnific-popup.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/js/aos.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/js/jquery.animateNumber.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/js/bootstrap-datepicker.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/js/jquery.timepicker.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/js/scrollax.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/js/main.js"></script>
</body>
</html>