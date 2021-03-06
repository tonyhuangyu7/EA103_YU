<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.emp_auth.model.*"%>
<%@ page import="java.util.*"%>

<%
  EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<%
  List<Emp_authVO> emp_authVO = (List<Emp_authVO>) request.getAttribute("emp_authVO"); //Emp_authServlet.java (Concroller) 存入req的emp_authVO物件 (包括幫忙取出的emp_authVO, 也包括輸入資料錯誤時的emp_authVO物件)
%>

<%
  String emp_no = new String(request.getParameter("emp_no").trim());
  Emp_authService emp_authService = new Emp_authService();
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工狀態修改</title>

<style>
	#loc{
		position: absolute;
		top: 150px;
		left: 300px;
		z-index: 10;
	}
</style>

</head>
<body>
	 
	 <div id="loc">
	<h4><a href="select_page.jsp">回首頁</a></h4>
	<h3>員工狀態修改</h3>
	
	<h3>狀態修改:</h3>
	
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
		
		<tr>
			<td>狀態:<font color=red><b>*</b></font></td>
			<td><select size="1" name="emp_sts">
				<option value="${(empVO.emp_sts==1) ? 1 : 0}" >${(empVO.emp_sts==1) ? "在職" : "離職"}
				<option value="${(empVO.emp_sts==1) ? 0 : 1}" >${(empVO.emp_sts==1) ? "離職" : "在職"}
			</select></td>
		</tr>
	
	</table>
	<br>
	<input type="hidden" name="action" value="update_s">
	<input type="hidden" name="emp_no" value="<%=empVO.getEmp_no()%>">
	<input type="hidden" name="emp_name" value="<%=empVO.getEmp_name()%>">
	
	<input name="fun_no[]" type="hidden" value="<%=emp_authService.getOneEmp_auth(emp_no)%>">

	<input type="submit" value="送出修改">
	</FORM>
	</div>
	
	<jsp:include page="/back-end/siderbar/siderbar.jsp" />

</body>
</html>