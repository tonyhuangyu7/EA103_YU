<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>

<%
    EmpService empSvc = new EmpService();
    List<EmpVO> list = empSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有員工資料</title>

<style>

</style>

</head>
<body>
	<h4><a href="select_page.jsp">回首頁</a></h4>
	<h3>所有員工資料</h3>
	
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<table>
		<tr>
			<th>員工編號</th>
			<th>員工姓名</th>
			<th>在職狀態</th>
			<th>狀態修改</th>
			<th>權限新增</th>
			<th>權限刪除</th>
		</tr>
		<%@ include file="page1.file" %> 
		<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			
			<tr>
				<td>${empVO.emp_no}</td>
				<td>${empVO.emp_name}</td>
				<td>${(empVO.emp_sts==1) ? "在職" : "離職"}</td>
				
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do" style="margin-bottom: 0px;">
				     <input type="submit" value="狀態修改">
				     <input type="hidden" name="emp_no" value="${empVO.emp_no}">
				     <input type="hidden" name="action"	value="Update_sts">
				  </FORM>
				</td>
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do" style="margin-bottom: 0px;">
				  	 <input type="submit" value="權限新增">
				  	 <input type="hidden" name="emp_no" value="${empVO.emp_no}">
				  	 <input type="hidden" name="action" value="Update_auth">
				  </FORM>
				</td>
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do" style="margin-bottom: 0px;">
				  	 <input type="submit" value="權限刪除">
				  	 <input type="hidden" name="emp_no" value="${empVO.emp_no}">
				  	 <input type="hidden" name="emp_name" value="${empVO.emp_name}">
				  	 <input type="hidden" name="emp_sts" value="${empVO.emp_sts}">
				  	 <input type="hidden" name="action" value="delete_emp_auth">
				  </FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file" %>
	
</body>
</html>