<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.front_inform.model.*"%>

<%
	Front_InformService front_informSvc = new Front_InformService();
	List<Front_InformVO> list = front_informSvc.getAllInform();
	pageContext.setAttribute("list", list);
%>

<html>
<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>員工查看通知</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/style.css">


<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}

table {
	width: 920px;
	background-color: white;
	border-radius: 10px;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
}
</style>

</head>
<body bgcolor='white'>
	<div class="main_content">
		<aside class="aside">
			<div class="firstLine">
				<div class="r1" style="font-size: 20px; font-weight: 900;">
					EMP0009</div>
				<div class="msg">
					<img src="<%=request.getContextPath()%>/back-end/images/help.png"
						alt="">
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
					<div>員工登入</div>
					<div>
						<a
							href="<%=request.getContextPath()%>/back-end/front_inform/empCheckInform.jsp"
							style="color: white; text-decoration: none;">查看通知</a>
					</div>
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
			<div class="content">
				<table id="table-1">
					<tr>
						<td>
							<h3>查看所有通知</h3> <a
							href="<%=request.getContextPath()%>/back-end/back-index.jsp"
							style="font-weight: 200px;; color: black; text-decoration: none;">返回首頁</a>
						</td>
					</tr>
				</table>

				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>

				<jsp:useBean id="fiSvc" scope="page"
					class="com.front_inform.model.Front_InformService" />
				<table>
					<tr>
						<th>通知編號</th>
						<th>會員編號</th>
						<th>訂位編號</th>
						<th>通知內容</th>
						<th style="width: 100px; text-align: center;">通知日期</th>
						<th>通知狀態</th>
						<th>已讀狀態</th>
					</tr>
					<%@ include file="page1.file"%>
					<c:forEach var="front_informVO" items="${list}"
						begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

						<tr>
							<td style="width: 80px; text-align: center;">${front_informVO.info_no}</td>
							<td style="width: 80px; text-align: center;">${front_informVO.mem_no}</td>
							<td style="width: 80px; text-align: center;">${front_informVO.res_no}</td>
							<td>${front_informVO.info_cont}</td>
							<td style="width: 100px; text-align: center;"><fmt:formatDate
									value="${front_informVO.info_date}" pattern="yyyy-MM-dd" /></td>

							<td style="width: 80px; text-align: center;">${front_informVO.info_sts}</td>

							<td style="width: 80px; text-align: center;">${front_informVO.read_sts}</td>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="page2.file"%>
			</div>
		</main>
	</div>
	<script src="<%=request.getContextPath()%>/back-end/js/jquery.min.js"></script>
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