<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.emp_auth.model.*"%>
<%@ page import="com.fun_auth.model.*"%>
<%@ page import="java.util.*"%>

<%
  EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<% 
  List<Emp_authVO> emp_authVO = (List<Emp_authVO>) request.getAttribute("emp_authVO"); //Emp_authServlet.java(Concroller), 存入req的emp_authVO物件
%>

<%
  List<Fun_authVO> fun_authVO = (List<Fun_authVO>) request.getAttribute("fun_authVO");
%>

<html>
<head>
<title>員工資料</title>

<style>

</style>

</head>
<body>

	<h4><a href="select_page.jsp">回首頁</a></h4>
	<h3>員工資料</h3>

<table>
	<tr>
		<th>員工編號</th>
		<th>員工姓名</th>
		<th>在職狀態</th>
		<th>權限內容</th>
	</tr>
	<tr>
		<td>${empVO.emp_no}</td>
		<td>${empVO.emp_name}</td>
		<td>${(empVO.emp_sts==1) ? "在職" : "離職"}</td>

		<td>
			<c:forEach var="fun_authVO" items="${fun_authVO}">
				${fun_authVO.fun_name}<br>
			</c:forEach>
		</td>
	</tr>
	
</table>

</body>
</html>