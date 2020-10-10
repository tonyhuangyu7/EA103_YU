package com.inform_set.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.inform_set.model.Inform_SetService;
import com.inform_set.model.Inform_SetVO;

public class Inform_SetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insertIS".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String emp_no = req.getParameter("emp_no");
				if(emp_no==null||(emp_no.trim().length()==0)) {
					errorMsgs.add("請輸入員工編號");
				}
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(emp_no);
				if (empVO == null) {
					errorMsgs.add("查無員工資料");
				}
				
				String is_cont = req.getParameter("is_cont");
				if(is_cont==null||(is_cont.trim().length()==0)) {
					errorMsgs.add("通知設定內容請勿空白");
				}
				
				java.sql.Date is_date = null;
				try {
					is_date = java.sql.Date.valueOf(req.getParameter("is_date").trim());
				}catch(IllegalArgumentException e) {
					errorMsgs.add("請輸入通知日期"); // 使用 webSocket ，在該日期推播給會員
				}
				
				Inform_SetVO isVO = new Inform_SetVO();
				isVO.setEmp_no(emp_no);
				isVO.setIs_cont(is_cont);
				isVO.setIs_date(is_date);
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/errorPage.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Inform_SetService isSvc = new Inform_SetService();
				isVO = isSvc.addIs(emp_no, is_cont, is_date);
				
				String url = "/inform_set/listAllIs.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllIs.jsp
				successView.forward(req, res);	
				
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/errorPage.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("getOneIsForUpdate".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String is_no = req.getParameter("is_no");
				if(is_no==null||(is_no.trim().length()==0)) {
					errorMsgs.add("請輸入通知設定編號");
				}
				
				Inform_SetService isSvc = new Inform_SetService();
				Inform_SetVO isVO = isSvc.getOneIs(is_no);
				if (isVO == null) {
					errorMsgs.add("查無通知編號資料");
				}
				
				req.setAttribute("isVO", isVO); // 資料庫取出的isVO物件,存入req
				String url = "/inform_set/update_is_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_is_input.jsp
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/errorPage.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("search".equals(action)) {
			
		}
		
		if("deleteIs".equals(action)) { // 來自 listAllIs.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String is_no = req.getParameter("is_no");
				Inform_SetService isSvc = new Inform_SetService();
				Inform_SetVO isVO = isSvc.getOneIs(is_no);
				
				java.util.Date nowTime = new java.util.Date();
				if(isVO.getIs_date().before(nowTime)) {
					errorMsgs.add("此通知時效已過期，無法予以刪除");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/errorPage.jsp");
					failureView.forward(req, res);
					return;
				}
				
				isSvc.deleteIs(is_no);
				String url = "/inform_set/listAllIs.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/errorPage.jsp");
				failureView.forward(req, res);
			}
			
		}
		
	}

}
