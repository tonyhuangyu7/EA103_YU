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
<title>inform_set.jsp</title>

<style>
* {
	box-sizing: border-box;
}

body {
	margin: 0;
	padding: 10px;
	font-family: "Poppins", Arial, sans-serif;
	line-height: 2;
}

div.main_content {
	width: 1200px;
	margin: 0 auto;
	font-size: 0;
}

aside.aside {
	width: 250px;
	display: inline-block;
	vertical-align: top;
	font-size: 1rem;
	margin-right: 10px;
	border: 1px solid #999;
}

main.main {
	width: calc(100% - 250px - 10px);
	display: inline-block;
	vertical-align: top;
	font-size: 1rem;
}

div.rheader {
	border: 1px solid black;
	border-radius: 10px;
	font-size: 20px;
	text-align: center;
}

h1 {
	margin: 5px auto;
}

div.content {
	height: 920px;
}

div.r1 {
	display: inline-block;
	position: relative;
	top: -20px;
	margin: 10px auto;
	margin-left: 10px;
}

div.msg {
	display: inline-block;
	margin-top: 10px;
	margin-left: 10px
}

button#bigOne {
	display: inline-block;
	position: relative;
	top: -20px;
	border-radius: 10px;
	margin-left: 10px;
	line-height: inherit;
}

button#smallOne {
	display: none;
}

div.bottom {
	padding: 10px;
}

div.me div, div.manager div {
	margin-top: 5px;
	border: 1px solid red;
	border-radius: 5px;
	background-color: red;
	margin-bottom: 10px;
	padding: 5px;
	color: white;
	text-align: center;
	font-weight: 1000;
}

@media screen and (max-width: 1200px) {
	aside.aside {
		width: 100%;
		margin-bottom: 10px;
	}
	aside.aside, main.main, div.rheader {
		width: 100%;
		display: block;
	}
	div.main_content {
		width: 100%;
		display: block;
		margin: auto;
	}
	div.r1, div.msg {
		margin: 10px auto;
		margin-left: 50px;
	}
	button#bigOne {
		display: none;
	}
	button#smallOne {
		display: inline-block;
		position: absolute;
		top: 45px;
		right: 40px;
		border-radius: 10px;
		line-height: inherit;
	}
}
</style>

</head>
<body>

	<div class="main_content">
		<aside class="aside">
			<div class="firstLine">
				<div class="r1" style="font-size: 20px; font-weight: 900;">
					EMP0009</div>
				<div class="msg">
					<img src="<%=request.getContextPath()%>/images/help.png" alt="">
				</div>
				<button id="bigOne">
					<svg width="1em" height="1em" viewBox="0 0 16 16"
						class="bi bi-list" fill="currentColor"
						xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd"
							d="M2.5 11.5A.5.5 0 0 1 3 11h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 7h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 3h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z" />
                    </svg>
				</button>
				<button id="smallOne">
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
					<div>員工登入</div>
					<div>查看通知</div>
				</div>
				<div class="manager">
					<div>員工管理</div>
					<div>會員管理</div>
					<div>通知管理</div>
					<div>廣告管理</div>
					<div>用餐營業時段管理</div>
					<div>桌位管理</div>
					<div>菜單管理</div>
					<div>餐點組成管理</div>
					<div>紅利商品管理</div>
				</div>
				<div class="me">
					<div>最新消息管理</div>
					<div>現場點餐</div>
					<div>現場劃位</div>
					<div>候位管理</div>
					<div>訂單結帳</div>
					<div>訂單派工</div>
					<div>出餐管理</div>
					<div>訂單管理</div>
					<div>訂餐管理</div>
					<div>訂餐訂單處理</div>
					<div>訂位管理</div>
					<div>評價管理</div>
					<div>食材管理</div>
					<div>食材消耗統計</div>
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

	<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-migrate-3.0.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.easing.1.3.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.waypoints.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.stellar.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/owl.carousel.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.magnific-popup.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/aos.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.animateNumber.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap-datepicker.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.timepicker.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/scrollax.min.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
	<script src="<%=request.getContextPath()%>/js/google-map.js"></script>
	<script src="<%=request.getContextPath()%>/js/main.js"></script>
</body>
</html>