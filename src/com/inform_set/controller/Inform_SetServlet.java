package com.inform_set.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
		
if("getOneIsForDisplay".equals(action)) { // 來自 select_is.jsp 的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String is_no = req.getParameter("is_no").trim().toUpperCase();
				String is_noReg = "I{1}S{1}[\\d]{4}";
				Pattern pat = Pattern.compile(is_noReg);
				Matcher matcher = pat.matcher(is_no.trim());
				if(is_no==null||(is_no.length()==0)) {
					errorMsgs.add("請輸入通知設定編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
					failureView.forward(req, res);
					return;
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
				String emp_no = req.getParameter("emp_no").trim().toUpperCase();
				String emp_noReg = "E{1}M{1}P{1}[\\d]{4}";
				Pattern pat = Pattern.compile(emp_noReg);
				Matcher matcher = pat.matcher(emp_no.trim());
				if(emp_no==null||(emp_no.length()==0)) {
					errorMsgs.add("請輸入員工編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
					failureView.forward(req, res);
					return;
				}
				if(!matcher.find()) {
					errorMsgs.add("員工編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
					failureView.forward(req, res);
					return;
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
				
				String is_contReg="[`~!@#$%^&*()+=|{}':;'\",\\[\\].?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]"; 
				Pattern pat = Pattern.compile(is_contReg); 
				Matcher matcher = pat.matcher(is_cont);
				if(matcher.find()) {
					errorMsgs.add("請輸入非特殊符號之關鍵字");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				Inform_SetService isSvc = new Inform_SetService();
				List<Inform_SetVO> isVOs = new ArrayList<Inform_SetVO>();
				if(is_cont.contains(" ")) {
					String[] many = is_cont.split(" ");
					for(String aSearch : many) { // isSvc.getIsByCont(aSearch) 回傳 List<Inform_SetVO>
						for(Inform_SetVO isVO : isSvc.getIsByCont(aSearch) ) {
							isVOs.add(isVO);
						}
					}
					if(isVOs.isEmpty()) {
						errorMsgs.add("查無資料");
					}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
						failureView.forward(req, res);
						return;
					}
					
					Set<Inform_SetVO> temp= new HashSet<Inform_SetVO>();
					temp.addAll(isVOs);      
					isVOs = new ArrayList<Inform_SetVO>();
					isVOs.addAll(temp);
					
					Collections.sort(isVOs);
					
					if (isVOs.isEmpty()) {
						errorMsgs.add("查無資料");
					}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
						failureView.forward(req, res);
						return;
					}
					
				} else {
					isVOs = isSvc.getIsByCont(is_cont);
					if (isVOs.isEmpty()) {
						errorMsgs.add("查無資料");
					}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
						failureView.forward(req, res);
						return;
					}
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
				String startDate = req.getParameter("is_date_startDate").trim();
				if (startDate == null || startDate.length() == 0) {
					errorMsgs.add("請輸入起始日期");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
					failureView.forward(req, res);
					return;
				}
				java.sql.Date is_date_startDate = null;
				try {
					is_date_startDate = java.sql.Date.valueOf(req.getParameter("is_date_startDate").trim());
				} catch (IllegalArgumentException e) {
					is_date_startDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入起始日期");
				}
				
				String stopDate = req.getParameter("is_date_startDate").trim();
				if (stopDate == null || stopDate.length() == 0) {
					errorMsgs.add("請輸入結束日期");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
					failureView.forward(req, res);
					return;
				}
				java.sql.Date is_date_stopDate = null;
				try {
					is_date_stopDate = java.sql.Date.valueOf(req.getParameter("is_date_stopDate").trim());
				} catch (IllegalArgumentException e) {
					is_date_stopDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入結束日期");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.參數轉型、開始查詢資料****************************************/
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				startDate = sdf.format(is_date_startDate);
				stopDate = sdf.format(is_date_stopDate);
				
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
		
		if("getIsForDisplayByComplex".equals(action)) { // 來自 select_is.jsp 的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數，並依據參數查詢資料****************************************/
				// 先判斷是否輸入日期
				// 若未輸入起始日期則起始日自動調整為 1970-01-01，未輸入停止日期則停止日自動調整為使用者查詢當下之系統時間
				java.sql.Date is_date_startDate = null;
				try {
					is_date_startDate = java.sql.Date.valueOf(req.getParameter("is_date_startDate").trim());
				} catch (IllegalArgumentException e) {
					is_date_startDate = java.sql.Date.valueOf("1970-01-01");
				}
				java.sql.Date is_date_stopDate = null;
				try {
					is_date_stopDate = java.sql.Date.valueOf(req.getParameter("is_date_stopDate").trim());
				} catch (IllegalArgumentException e) {
					is_date_stopDate = new java.sql.Date(System.currentTimeMillis());
				}
				// 將 java.sql.Date 轉型為 String
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String startDate = sdf.format(is_date_startDate);
				String stopDate = sdf.format(is_date_stopDate);
				// 取得該期間的通知設定資料
				Inform_SetService isSvc1 = new Inform_SetService();
				List<Inform_SetVO> step1isVOs = isSvc1.getIsByDate(startDate, stopDate);
				if (step1isVOs == null) {
					errorMsgs.add("該期間查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
					failureView.forward(req, res);
					return;
				}
				// 到此程式尚未 return 代表已可確定該期間有資料
				
				// 取得員工編號參數
				String emp_no = req.getParameter("emp_no").trim();
				List<Inform_SetVO> step3isVOs = null; 
				// 預備一個 List 去放置符合員工編號及模糊查詢的結果，最後再與藉由日期所查詢到的 step1isVOs 做比較
				
				// 判斷是否真的有輸入員工編號
				// 有輸入員工編號
				if(emp_no!=null && (emp_no.length()!=0)) { 
					String emp_noReg = "E{1}M{1}P{1}[\\d]{4}";
					Pattern pat = Pattern.compile(emp_noReg, Pattern.CASE_INSENSITIVE);
					Matcher matcher = pat.matcher(emp_no.trim());
					if(!matcher.find()) {
						errorMsgs.add("員工編號格式不正確");
					}
					EmpService empSvc = new EmpService();
					EmpVO empVO = empSvc.getOneEmp(emp_no);
					if (empVO == null) {
						errorMsgs.add("查無員工");
					}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
						failureView.forward(req, res);
						return;
					}
					// 輸入員工編號後，若未發生上述錯誤狀況，則進入下方模糊查詢條件判斷流程
					
					// 取得模糊查詢的參數
					String is_cont = req.getParameter("is_cont").trim();
					// 有輸入模糊查詢
					if (is_cont != null && is_cont.length() != 0) {
						Inform_SetService isSvc2 = new Inform_SetService();
						List<Inform_SetVO> step2isVOs = isSvc2.getIsByCont(is_cont);
						if (step2isVOs == null) {
							errorMsgs.add("查無相關內容之資料");
						}
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
							failureView.forward(req, res);
							return;
						}
						// 輸入模糊查詢後，若上述錯誤狀況未發生，則進入下方條件判斷流程
						// 判斷模糊查詢出的 isVOs 中，同時符合輸入之員工編號的 isVOs
						for(Inform_SetVO step2isVO : step2isVOs) {
							// 利用由模糊查詢得出之 step2isVOs 搭配 for-each 判斷其員工編號，若符合員工編號則加入預先準備好的 step3isVOs 中
							if(emp_no.equals(step2isVO.getEmp_no())) {
								step3isVOs.add(step2isVO);
							}
						}
					
					// 未輸入模糊查詢
					} else { 
						// 直接 getIsByEmp 得 step2isVOs
						Inform_SetService isSvc2 = new Inform_SetService();
						List<Inform_SetVO> step2isVOs = isSvc2.getIsByEmp(emp_no);
						if (step2isVOs == null) {
							errorMsgs.add("該員工未書寫任何通知設定資料");
						}
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
							failureView.forward(req, res);
							return;
						}
						// 若 step2isVOs 含有資料，搭配 for-each 將資料加入預先準備好的 step3isVOs 中
						for(Inform_SetVO step2isVO : step2isVOs) {
							step3isVOs.add(step2isVO);
						}
					}
					/*************** 所得之 step3isVOs 為皆符合員工編號及模糊查詢的 isVOs ***************/
				
				} else { // 未輸入員工編號
					// 取得模糊查詢的參數
					String is_cont = req.getParameter("is_cont").trim();
					// 有輸入模糊查詢
					if (is_cont != null && is_cont.length() != 0) {
						Inform_SetService isSvc2 = new Inform_SetService();
						List<Inform_SetVO> step2isVOs = isSvc2.getIsByCont(is_cont);
						if (step2isVOs == null) {
							errorMsgs.add("查無相關內容之資料");
						}
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
							failureView.forward(req, res);
							return;
						}
						// 輸入模糊查詢後，若上述錯誤狀況未發生，則進入下方條件判斷流程
						// 將由模糊查詢所取得的 step2isVOs，搭配 for-each 放入預先準備好的 step3isVOs 中
						for(Inform_SetVO step2isVO : step2isVOs) {
							step3isVOs.add(step2isVO);
						}
					}
					// 未輸入模糊查詢 → 僅由日期判斷，不須再寫額外的條件判斷 ( step1isVOs )
					/*************** 所得之 step3isVOs 接為符合員工編號及模糊查詢的 isVOs ***************/
				}
				// 準備一個最後要放入頁面的 isVOs，判斷所得之 step3isVOs 是否符合一開始設立的日期條件 ( 藉由是否為相同 is_no 判斷 )
				List<Inform_SetVO> isVOs = null;
				for(Inform_SetVO step1isVO : step1isVOs) {
					for(Inform_SetVO step3isVO : step3isVOs) {
						if(step1isVO.getIs_no().equals(step3isVO.getIs_no())) {
							isVOs.add(step1isVO); // 最後查詢出來的 isVOs
						}
					}
				}
				if (isVOs == null) {
					errorMsgs.add("查無符合條件之資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("isVOs", isVOs); // 資料庫取出的 isVOs ,存入req
				String url = "/back-end/inform_set/listByComplex_is.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 listByComplex_is.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOneIsForUpdate".equals(action)) { 
			// 來自 listAll_is.jsp、listByCont_is.jsp、listByComplex_is.jsp、listByDate_is.jsp、listByEmp_is.jsp、listOne_is.jsp 的請求
			// Q：如何在發生錯誤時及成功轉交時，跳回來源頁面? 使用 form 表單提交的頁面，有辦法跳回去嗎?
			
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
					errorMsgs.add("通知編號 " + is_no + " 時效已過期，無法予以修改");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
					failureView.forward(req, res);
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
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("updateIs".equals(action)) { // 來自update_is.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String is_no = req.getParameter("is_no").trim();
				
				String emp_no = req.getParameter("emp_no").trim();
				String emp_noReg = "E{1}M{1}P{1}[\\d]{4}";
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
				String emp_noReg = "E{1}M{1}P{1}[\\d]{4}";
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
				req.setAttribute("isVO", isVO);
				String url = "/back-end/inform_set/listOne_is.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listOne_is.jsp
				successView.forward(req, res);	
				
			}catch(Exception e) {
				errorMsgs.add("新增資料失敗：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/add_is.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("deleteIs".equals(action)) { 
			// 來自 listAll_is.jsp、listByCont_is.jsp、listByComplex_is.jsp、listByEmp_is.jsp、listByDate_is.jsp、listOne_is.jsp 的請求
			// Q：如何在發生錯誤時及成功轉交時，跳回來源頁面? 使用 form 表單提交的頁面，有辦法跳回去嗎?
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數***************************************/
				String is_no = new String(req.getParameter("is_no").trim());
				
				/***************************2.確認是否過期***************************************/
				Inform_SetService isSvc = new Inform_SetService();
				Inform_SetVO isVO = isSvc.getOneIs(is_no);
				java.util.Date nowTime = new java.util.Date();
				if(isVO.getIs_date().before(nowTime)) {
					errorMsgs.add("通知編號 " + is_no + " 時效已過期，無法予以刪除");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				isSvc.deleteIs(is_no);
				String url = "/back-end/inform_set/listAll_is.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

			/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/inform_set/select_is.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
