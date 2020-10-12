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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>員工查看通知</title>

<!-- Bootstrap CSS CDN -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
	integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4"
	crossorigin="anonymous">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!-- Our Custom CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/css/style2.css">
<!-- Scrollbar Custom CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
<!-- Font Awesome JS -->
<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"
	integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ"
	crossorigin="anonymous"></script>
<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"
	integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY"
	crossorigin="anonymous"></script>


<style>
#table-1, #table-1 td {
	background: #7386D5;
    color: #fff;
	border: 0;
	width: 100%;
	height: 70;
	border-radius: 5px;
	text-align: center;
	box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.1);
}
</style>

</head>
<body>

	<div class="wrapper">

		<!-- Sidebar  -->
		<nav id="sidebar">
			<div class="sidebar-header" style="cursor: default;">
				<h3><span>EMP0009</span><br>詹詠祺，您好！</h3>
				<!-- 員工編號 ${empVO.emp_no}  員工姓名 ${empVO.emp_name} -->
			</div>

			<ul class="list-unstyled components">
				<li><a href="#">現場點餐</a></li>
				<li><a href="#">現場劃位</a></li>
				<li><a href="#">訂單結帳</a></li>
				<li><a href="#">候位管理</a></li>
				<li class="active"><a href="#pageSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">主管員工專區</a>
					<ul class="collapse list-unstyled" id="pageSubmenu">
						<li><a href="#">員工管理</a></li>
						<li><a href="#">會員管理</a></li>
						<li><a href="#">廣告管理</a></li>
						<li><a href="#">最新消息管理</a></li>
						<li><a href="<%=request.getContextPath()%>/back-end/inform_set/inform_set_New.jsp">通知管理</a></li>
						<li><a href="#">評價管理</a></li>
						<li><a href="#">用餐時段管理</a></li>
						<li><a href="#">桌位管理</a></li>
						<li><a href="#">菜單管理</a></li>
						<li><a href="#">食材管理</a></li>
						<li><a href="#">餐點組成管理</a></li>
						<li><a href="#">食材消耗統計</a></li>
						<li><a href="#">紅利商品管理</a></li>
					</ul>
				</li>
				<li><a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">一般員工專區</a>
					<ul class="collapse list-unstyled" id="homeSubmenu">
						<li><a href="<%=request.getContextPath()%>/back-end/front_inform/empCheckInform_New.jsp">查看通知</a></li>
						<li><a href="#">訂單派工</a></li>
						<li><a href="#">出餐管理</a></li>
						<li><a href="#">訂餐訂單處理</a></li>
						<li><a href="#">訂餐管理</a></li>
						<li><a href="#">訂單管理</a></li>
						<li><a href="#">訂位管理</a></li>
					</ul>
				</li>
			</ul>

			<ul class="list-unstyled CTAs">
				<li><a href="#" id="logIn">Log in</a></li>
				<li><a href="#" id="logOut">Log out</a></li>
			</ul>
		</nav>

		<!-- Page Content  -->
		<div id="content">

			<nav class="navbar navbar-expand-lg navbar-light bg-light">
				<div class="container-fluid">

					<button type="button" id="sidebarCollapse" class="btn btn-info">
						<i class="fas fa-align-justify"></i>
					</button>
					<div id="titleBig" style="margin: 0 auto; font-size: 30px; font-weight: 800;"><a href="<%=request.getContextPath()%>/back-end/back-index_New.jsp">吃 Pot 吧！員工專區</a></div>
					<div id="rwdShow">
						<button type="button" id="topbarCollapse" class="btn btn-info"
							data-toggle="collapse" data-target="#navbarSupportedContent"
							aria-controls="navbarSupportedContent" aria-expanded="false"
							aria-label="Toggle navigation">
							<i class="fas fa-align-justify"></i>
						</button>
						<div id="titleSmall" style="padding-left: 10px; font-size: 30px; font-weight: 800;"><a href="<%=request.getContextPath()%>/back-end/back-index_New.jsp">吃 Pot 吧！員工專區</a></div>
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<ul class="nav navbar-nav ml-auto">
								<!-- 員工編號 ${empVO.emp_no}  員工姓名 ${empVO.emp_name} -->
								<li class="nav-item active"><a class="nav-link" href="#"
									id="empId" style="cursor: default;"><span>EMP0009
											詹詠祺</span>，您好！</a></li>
								<li class="nav-item active"><a class="nav-link" href="#">現場點餐</a></li>
								<li class="nav-item active"><a class="nav-link" href="#">現場劃位</a></li>
								<li class="nav-item active"><a class="nav-link" href="#">訂單結帳</a></li>
								<li class="nav-item active"><a class="nav-link" href="#">候位管理</a></li>
								<li class="nav-item active"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/back-index_m.jsp">主管員工專區</a></li>
								<li class="nav-item active"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/back-index_e.jsp">一般員工專區</a></li>
								<li class="nav-item active"><div id="topLogIn" style="display: inline-block; width: 90px; text-align: center; border-radius: 5px; background: #6d7fcc; color: #fff; cursor: pointer;">Log in</div>
									<div id="topLogOut" style="display: inline-block; width: 90px; text-align: center; margin-left: 10px; border-radius: 5px; background: #6d7fcc; color: #fff; cursor: pointer;">Log out</div></li>
							</ul>
						</div>
					</div>
				</div>
			</nav>

			<h5 style="font-weight: 900; display: inline-block;">一般員工專區</h5><span> - 查看通知</span>
			<a href="<%=request.getContextPath()%>/back-end/back-index_New.jsp" style="display: inline-block; font-size: 8px; font-weight: 900; color: #6d7fcc; text-decoration: none; margin-left: 20px;">返回首頁</a>
			<p>
				<table id="table-1">
					<tr>
						<td>
							<h3 style="margin-bottom:0;">查看所有通知</h3>
						</td>
					</tr>
				</table>
				<br>
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>

				<jsp:useBean id="fiSvc" scope="page" class="com.front_inform.model.Front_InformService" />
				<table class="table table-hover" style="width: 100%; font-size: 70%;">
					<thead style="text-align: center;">
						<tr>
							<th style="width: 10%;">通知編號</th>
							<th style="width: 10%;">會員編號</th>
							<th style="width: 10%;">訂位編號</th>
							<th style="width: 20%;">通知內容</th>
							<th style="width: 20%;">通知日期</th>
							<th style="width: 15%;">通知狀態</th>
							<th style="width: 15%;">已讀狀態</th>
						</tr>
					</thead>
					<%@ include file="page1.file"%>
					<tbody>
					<c:forEach var="front_informVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr>
							<td style="text-align: center;">${front_informVO.info_no}</td>
							<td style="text-align: center;">${front_informVO.mem_no}</td>
							<td style="text-align: center;">${front_informVO.res_no}</td>
							<td style="text-align: center;">
								<c:choose>
									<c:when test="${front_informVO.info_cont eq '提醒您，因您多次訂位且多次點按確認當天用餐按鈕，但皆未至本餐廳用餐，您的訂位功能將於 3 天後恢復'}">暫停訂位功能通知</c:when>
									<c:when test="${front_informVO.info_cont eq '提醒您，因您多次訂餐付款但皆未至本餐廳取餐，您的訂餐功能將於 3 天後恢復'}">暫停訂餐功能通知</c:when>
									<c:when test="${front_informVO.info_cont eq '提醒您，因您檢舉多則評價，但評價內容多數未達不當言論之標準，您的檢舉功能將於 7 天後恢復'}">暫停檢舉功能通知</c:when>
									<c:when test="${front_informVO.info_cont eq '提醒您，因您有多則評價被檢舉成功，您的評價功能將於 14 天後恢復'}">暫停評價功能通知</c:when>
									<c:when test="${front_informVO.info_cont eq '提醒您，您將於 1 分鐘後被停權'}">會員停權通知</c:when>
									<c:when test="${front_informVO.info_cont eq '訂位成功，點選查看訂位明細'}">訂位成功通知</c:when>
									<c:when test="${front_informVO.info_cont eq '訂餐成功！您尚未付款，點選前往結帳'}">訂餐成功尚未結帳</c:when>
									<c:when test="${front_informVO.info_cont eq '您已成功付款，點選查看訂單明細'}">訂單付款成功通知</c:when>
									<c:when test="${front_informVO.info_cont eq '您的餐點已完成，請至本餐廳取餐'}">取餐通知</c:when>
									<c:otherwise>當日用餐通知</c:otherwise>
								</c:choose>
							</td>
							<td style="text-align: center;"><fmt:formatDate value="${front_informVO.info_date}" pattern="yyyy-MM-dd" /></td>
							<td style="text-align: center;">
								<c:choose>
									<c:when test="${front_informVO.info_sts == 0}">一般通知</c:when>
									<c:when test="${front_informVO.info_sts == 1}">確認用餐</c:when>
									<c:when test="${front_informVO.info_sts == 2}">尚未回覆</c:when>
									<c:when test="${front_informVO.info_sts == 3}">取消訂位</c:when>
								</c:choose>
							</td>
							<td style="text-align: center;">
								<c:choose>
									<c:when test="${front_informVO.read_sts == 0}">未讀</c:when>
									<c:when test="${front_informVO.read_sts == 1}">已讀</c:when>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<%@ include file="page2.file"%>
			</p>
		</div>
	</div>

	<!-- jQuery CDN - Slim version (=without AJAX) -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<!-- Popper.JS -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
		integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
		crossorigin="anonymous"></script>
	<!-- Bootstrap JS -->
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
		integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
		crossorigin="anonymous"></script>
	<!-- jQuery Custom Scroller CDN -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#sidebar").mCustomScrollbar({
				theme : "minimal"
			});

			$('#sidebarCollapse').on('click', function() {
				$('#sidebar, #content').toggleClass('active');
				$('.collapse.in').toggleClass('in');
				$('a[aria-expanded=true]').attr('aria-expanded', 'false');
			});
		});
	</script>

</body>
</html>