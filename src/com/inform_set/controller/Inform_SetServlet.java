package com.inform_set.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
		
		if("getOneIsForDisplay".equals(action)) { // 來自 select_is.jsp 的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String is_no = req.getParameter("is_no").trim();
				String is_noReg = "I{1}S{1}[\\\\d]{4}";
				Pattern pat = Pattern.compile(is_noReg, Pattern.CASE_INSENSITIVE);
				Matcher matcher = pat.matcher(is_no.trim());
				if(is_no==null||(is_no.length()==0)) {
					errorMsgs.add("請輸入通知設定編號");
				}
				if(!matcher.find()) {
					errorMsgs.add("通知設定編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				Inform_SetService isSvc = new Inform_SetService();
				Inform_SetVO isVO = isSvc.getOneIs(is_no);
				if (isVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("isVO", isVO); // 資料庫取出的isVO物件,存入req
				String url = "/back-end/inform_set/listOne_is.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 listOne_is.jsp
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("getIsForDisplayByEmp".equals(action)) { // 來自 select_is.jsp 的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String emp_no = req.getParameter("emp_no").trim();
				String emp_noReg = "E{1}M{1}P{1}[\\\\d]{4}";
				Pattern pat = Pattern.compile(emp_noReg, Pattern.CASE_INSENSITIVE);
				Matcher matcher = pat.matcher(emp_no.trim());
				if(emp_no==null||(emp_no.length()==0)) {
					errorMsgs.add("請輸入員工編號");
				}
				if(!matcher.find()) {
					errorMsgs.add("員工編號格式不正確");
				}
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(emp_no);
				if (empVO == null) {
					errorMsgs.add("查無員工資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				Inform_SetService isSvc = new Inform_SetService();
				List<Inform_SetVO> isVOs = isSvc.getIsByEmp(emp_no);
				if (isVOs == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("isVOs", isVOs); // 資料庫取出的 isVOs ,存入req
				String url = "/back-end/inform_set/listByEmp_is.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 listByEmp_is.jsp
				successView.forward(req, res);
				
			} catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("getIsForDisplayByCont".equals(action)) { // 來自 select_is.jsp 的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String is_cont = req.getParameter("is_cont").trim();
				if (is_cont == null || is_cont.length() == 0) {
					errorMsgs.add("請確實輸入欲查詢之通知內容關鍵字");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				Inform_SetService isSvc = new Inform_SetService();
				List<Inform_SetVO> isVOs = isSvc.getIsByCont(is_cont);
				if (isVOs == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("isVOs", isVOs); // 資料庫取出的 isVOs ,存入req
				String url = "/back-end/inform_set/listByCont_is.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 listByCont_is.jsp
				successView.forward(req, res);
				
			} catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("getIsForDisplayByDate".equals(action)) { // 來自 select_is.jsp 的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				java.sql.Date is_date_startDate = null;
				try {
					is_date_startDate = java.sql.Date.valueOf(req.getParameter("is_date_startDate").trim());
				} catch (IllegalArgumentException e) {
					is_date_startDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入起始日期!");
				}
				
				java.sql.Date is_date_stopDate = null;
				try {
					is_date_stopDate = java.sql.Date.valueOf(req.getParameter("is_date_stopDate").trim());
				} catch (IllegalArgumentException e) {
					is_date_stopDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入結束日期!");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.參數轉型、開始查詢資料****************************************/
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String startDate = sdf.format(is_date_startDate);
				String stopDate = sdf.format(is_date_stopDate);
				
				Inform_SetService isSvc = new Inform_SetService();
				List<Inform_SetVO> isVOs = isSvc.getIsByDate(startDate, stopDate);
				if (isVOs == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("isVOs", isVOs); // 資料庫取出的 isVOs ,存入req
				String url = "/back-end/inform_set/listByDate_is.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 listByDate_is.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("getIsForDisplayByManyConditions".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				
				
				/***************************2.參數轉型、開始查詢資料****************************************/
				
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("isVOs", isVOs); // 資料庫取出的 isVOs ,存入req
//				String url = "/back-end/inform_set/listByManyConditions_is.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 listByManyConditions_is.jsp
//				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOneIsForUpdate".equals(action)) {
			// 來自 listAll_is.jsp、listOne_is.jsp、listByEmp_is.jsp、listByCont_is.jsp、listByDate_is.jsp 的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String is_no = req.getParameter("is_no").trim();
				
				/***************************2.開始查詢資料，進行可能的錯誤處理****************************************/
				Inform_SetService isSvc = new Inform_SetService();
				Inform_SetVO isVO = isSvc.getOneIs(is_no);
				
				java.util.Date nowTime = new java.util.Date();
				if(isVO.getIs_date().before(nowTime)) {
					errorMsgs.add("此通知時效已過期，無法予以修改");
				}
				if (!errorMsgs.isEmpty()) {
					HttpSession session = req.getSession();
					try {                                                        
						String location = (String) session.getAttribute("location");
						if (location != null) {
							// 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
							session.removeAttribute("location");
							res.sendRedirect(location);            
							return;
						}
					}catch (Exception ignored) { }
					// 無來源網頁 : 重導至select_is.jsp
					res.sendRedirect(req.getContextPath()+"/back-end/inform_set/select_is.jsp");
					return;
				}
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("isVO", isVO);
				String url = "/back-end/inform_set/update_is.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_is.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				HttpSession session = req.getSession();
				try {                                                        
					String location = (String) session.getAttribute("location");
					if (location != null) {
						// 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
						session.removeAttribute("location");
						res.sendRedirect(location);            
						return;
					}
				}catch (Exception ignored) { }
				// 無來源網頁 : 重導至select_is.jsp
				res.sendRedirect(req.getContextPath()+"/back-end/inform_set/select_is.jsp");
			}
		}
		
		if ("updateIs".equals(action)) { // 來自update_is.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String is_no = req.getParameter("is_no").trim();
				
				String emp_no = req.getParameter("emp_no").trim();
				String emp_noReg = "E{1}M{1}P{1}[\\\\d]{4}";
				Pattern pat = Pattern.compile(emp_noReg, Pattern.CASE_INSENSITIVE);
				Matcher matcher = pat.matcher(emp_no.trim());
				if(emp_no==null||(emp_no.length()==0)) {
					errorMsgs.add("請輸入員工編號");
				}
				if(!matcher.find()) {
					errorMsgs.add("員工編號格式不正確");
				}

				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(emp_no);
				if (empVO == null) {
					errorMsgs.add("查無員工資料");
				}
				
				String is_cont = req.getParameter("is_cont").trim();
				if (is_cont == null || is_cont.length() == 0) {
					errorMsgs.add("通知設定內容請勿空白");
				}
				if (is_cont.length() < 5) {
					errorMsgs.add("通知設定內容長度須至少 5 個字元以上");
	            }
				
				java.sql.Date is_date = null;
				try {
					is_date = java.sql.Date.valueOf(req.getParameter("is_date").trim());
				} catch (IllegalArgumentException e) {
					is_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Inform_SetVO isVO = new Inform_SetVO();
				isVO.setIs_no(is_no);
				isVO.setEmp_no(emp_no);
				isVO.setIs_cont(is_cont);
				isVO.setIs_date(is_date);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("isVO", isVO); // 含有輸入格式錯誤的isVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/update_is.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				Inform_SetService isSvc = new Inform_SetService();
				isVO = isSvc.updateIs(is_no, emp_no, is_cont, is_date);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("isVO", isVO); // 資料庫update成功後,正確的的isVO物件,存入req
				String url = "/back-end/inform_set/listOne_is.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOne_is.jsp
				successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/update_is.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("insertIs".equals(action)) { // 來自add_is.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String emp_no = req.getParameter("emp_no").trim();
				String emp_noReg = "E{1}M{1}P{1}[\\\\d]{4}";
				Pattern pat = Pattern.compile(emp_noReg, Pattern.CASE_INSENSITIVE);
				Matcher matcher = pat.matcher(emp_no.trim());
				if(emp_no==null||(emp_no.length()==0)) {
					errorMsgs.add("請輸入員工編號");
				}
				if(!matcher.find()) {
					errorMsgs.add("員工編號格式不正確");
				}
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(emp_no);
				if (empVO == null) {
					errorMsgs.add("查無員工資料");
				}
				
				String is_cont = req.getParameter("is_cont").trim();
				if (is_cont == null || is_cont.length() == 0) {
					errorMsgs.add("通知設定內容請勿空白");
				}
				if (is_cont.length() < 5) {
					errorMsgs.add("通知設定內容長度須至少 5 個字元以上");
	            }
				
				java.sql.Date is_date = null;
				try {
					is_date = java.sql.Date.valueOf(req.getParameter("is_date").trim());
				} catch (IllegalArgumentException e) {
					is_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Inform_SetVO isVO = new Inform_SetVO();
				isVO.setEmp_no(emp_no);
				isVO.setIs_cont(is_cont);
				isVO.setIs_date(is_date);
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/add_is.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Inform_SetService isSvc = new Inform_SetService();
				isVO = isSvc.addIs(emp_no, is_cont, is_date);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/inform_set/listAll_is.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAll_is.jsp
				successView.forward(req, res);	
				
			}catch(Exception e) {
				errorMsgs.add("新增資料失敗：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/add_is.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("deleteIs".equals(action)) { 
			// 來自 listAll_is.jsp、listOne_is.jsp、listByEmp_is.jsp、listByCont_is.jsp、listByDate_is.jsp 的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數***************************************/
				String is_no = req.getParameter("is_no").trim();
				
				/***************************2.確認是否過期***************************************/
				Inform_SetService isSvc = new Inform_SetService();
				Inform_SetVO isVO = isSvc.getOneIs(is_no);
				java.util.Date nowTime = new java.util.Date();
				if(isVO.getIs_date().before(nowTime)) {
					errorMsgs.add("此通知時效已過期，無法予以刪除");
				}
				if (!errorMsgs.isEmpty()) {
					HttpSession session = req.getSession();
					try {                                                        
						String location = (String) session.getAttribute("location");
						if (location != null) {
							// 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
							session.removeAttribute("location");
							res.sendRedirect(location);            
							return;
						}
					}catch (Exception ignored) { }
					// 無來源網頁 : 重導至select_is.jsp
					res.sendRedirect(req.getContextPath()+"/back-end/inform_set/select_is.jsp");
					return;
				}
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				isSvc.deleteIs(is_no);
				HttpSession session = req.getSession();
				try {                                                        
					String location = (String) session.getAttribute("location");
					if (location != null) {
						// 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
						session.removeAttribute("location");
						res.sendRedirect(location);            
						return;
					}
				}catch (Exception ignored) { }
				// 無來源網頁 : 重導至select_is.jsp
				res.sendRedirect(req.getContextPath()+"/back-end/inform_set/select_is.jsp");

			/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				HttpSession session = req.getSession();
				try {                                                        
					String location = (String) session.getAttribute("location");
					if (location != null) {
						// 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
						session.removeAttribute("location");
						res.sendRedirect(location);            
						return;
					}
				}catch (Exception ignored) { }
				// 無來源網頁 : 重導至select_is.jsp
				res.sendRedirect(req.getContextPath()+"/back-end/inform_set/select_is.jsp");
			}
			
		}
		
	}

}
