<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<%
  EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工權限修改</title>

<style>

</style>

</head>
<body>

	<h4><a href="select_page.jsp">回首頁</a></h4>
	<h3>員工權限修改</h3>

	<h3>權限修改:</h3>
	
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="emp.do" name="form1">
	<table>
		<tr>
			<td>員工編號:</td>
			<td><%=empVO.getEmp_no()%></td>
		</tr>
		<tr>
			<td>員工姓名:</td>
			<td><%=empVO.getEmp_name()%></td>
		</tr>
	</table>
		
	
		<jsp:useBean id="fun_authSvc" scope="page" class="com.fun_auth.model.Fun_authService" />
		
			權限:<font color=red><b>*</b></font><br>
			
			<c:forEach var="fun_authVO" items="${fun_authSvc.all}">
				<label><input name="fun_no[]" type="checkbox" value="${fun_authVO.fun_no}">${fun_authVO.fun_name}</label>
			</c:forEach>
			
	<br>
	<input type="hidden" name="action" value="update_a">
	<input type="hidden" name="emp_no" value="<%=empVO.getEmp_no()%>">
	<input type="hidden" name="emp_name" value="<%=empVO.getEmp_name()%>">
	<input type="hidden" name="emp_sts" value="<%=empVO.getEmp_sts()%>">
	<input type="submit" value="送出修改"></FORM>

</body>
</html>