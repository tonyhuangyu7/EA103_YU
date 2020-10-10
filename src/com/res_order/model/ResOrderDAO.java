package com.res_order.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ResOrderDAO implements ResOrderDAO_interface {
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/EA103G7");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}

	private static final String INSERT_STMT = "INSERT INTO RES_ORDER (RES_NO ,MEAL_ORDER_NO ,MEM_NO ,EMP_NO, RES_TIME, RES_DATE, PEOPLE, TIME_PERI_NO, INFO_STS, SEAT_STS) VALUES ('RESO'||LPAD(SEQ_RES_NO.NEXTVAL,4,0), ?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT RES_NO ,MEAL_ORDER_NO ,MEM_NO ,EMP_NO ,RES_TIME ,RES_DATE ,PEOPLE ,TIME_PERI_NO ,INFO_STS, SEAT_STS FROM RES_ORDER ORDER BY RES_NO";
	private static final String GET_ONE_STMT = "SELECT MEAL_ORDER_NO ,MEM_NO ,EMP_NO ,RES_TIME ,RES_DATE ,PEOPLE ,TIME_PERI_NO ,INFO_STS, SEAT_STS FROM RES_ORDER WHERE RES_NO = ?";
	private static final String UPDATE = "UPDATE RES_ORDER SET MEAL_ORDER_NO=? ,MEM_NO=? ,EMP_NO=? ,RES_DATE=? ,PEOPLE=? ,TIME_PERI_NO=? ,INFO_STS=? ,SEAT_STS=? WHERE RES_NO = ?";

	@Override
	public void insert(ResOrderVO resOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, resOrderVO.getMeal_order_no());
			pstmt.setString(2, resOrderVO.getMem_no());
			pstmt.setString(3, resOrderVO.getEmp_no());
			pstmt.setDate(4, resOrderVO.getRes_date());
			pstmt.setInt(5, new Integer(resOrderVO.getPeople()));
			pstmt.setString(6, resOrderVO.getTime_peri_no());
			pstmt.setInt(7, new Integer(resOrderVO.getInfo_sts()));
			pstmt.setInt(8, new Integer(resOrderVO.getSeat_sts()));

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(ResOrderVO resOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, resOrderVO.getMeal_order_no());
			pstmt.setString(2, resOrderVO.getMem_no());
			pstmt.setString(3, resOrderVO.getEmp_no());
			pstmt.setDate(4, resOrderVO.getRes_date());
			pstmt.setInt(5, new Integer(resOrderVO.getPeople()));
			pstmt.setString(6, resOrderVO.getTime_peri_no());
			pstmt.setInt(7, new Integer(resOrderVO.getInfo_sts()));
			pstmt.setInt(8, new Integer(resOrderVO.getSeat_sts()));
			pstmt.setString(9, resOrderVO.getRes_no());

			pstmt.executeUpdate();
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public ResOrderVO findByPrimaryKey(String res_no) {
		ResOrderVO resOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, res_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				resOrderVO = new ResOrderVO();

				resOrderVO.setRes_no(res_no);
				resOrderVO.setMeal_order_no(rs.getString("MEAL_ORDER_NO"));
				resOrderVO.setMem_no(rs.getString("MEM_NO"));
				resOrderVO.setEmp_no(rs.getString("EMP_NO"));
				resOrderVO.setRes_time(rs.getTimestamp("RES_TIME"));
				resOrderVO.setRes_date(rs.getDate("RES_DATE"));
				resOrderVO.setPeople(rs.getInt("PEOPLE"));
				resOrderVO.setTime_peri_no(rs.getString("TIME_PERI_NO"));
				resOrderVO.setInfo_sts(new Integer(rs.getInt("INFO_STS")));
				resOrderVO.setSeat_sts(new Integer(rs.getInt("SEAT_STS")));
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return resOrderVO;
	}

	@Override
	public List<ResOrderVO> getAll() {
		List<ResOrderVO> list = new ArrayList<ResOrderVO>();
		ResOrderVO resOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {
//				if (new Integer(rs.getInt("SEAT_ISDEL")) == 0) {
				resOrderVO = new ResOrderVO();

				resOrderVO.setRes_no(rs.getString("RES_NO"));
				resOrderVO.setMeal_order_no(rs.getString("MEAL_ORDER_NO"));
				resOrderVO.setMem_no(rs.getString("MEM_NO"));
				resOrderVO.setEmp_no(rs.getString("EMP_NO"));
				resOrderVO.setRes_time(rs.getTimestamp("RES_TIME"));
				resOrderVO.setRes_date(rs.getDate("RES_DATE"));
				resOrderVO.setPeople(rs.getInt("PEOPLE"));
				resOrderVO.setTime_peri_no(rs.getString("TIME_PERI_NO"));
				resOrderVO.setInfo_sts(new Integer(rs.getInt("INFO_STS")));
				resOrderVO.setSeat_sts(new Integer(rs.getInt("SEAT_STS")));

				list.add(resOrderVO); // Store the row in the list
//				}
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// bytesArrayToByteObject error
		} catch (NullPointerException npe) {
			throw new RuntimeException("A bytesArrayToByteObject error occured. " + npe.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}
