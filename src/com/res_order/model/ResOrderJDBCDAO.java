package com.res_order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResOrderJDBCDAO implements ResOrderDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA103G7";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO RES_ORDER (RES_NO ,MEAL_ORDER_NO ,MEM_NO ,EMP_NO, RES_TIME, RES_DATE, PEOPLE, TIME_PERI_NO, INFO_STS, SEAT_STS) VALUES ('RESO'||LPAD(SEQ_RES_NO.NEXTVAL,4,0), ?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT RES_NO ,MEAL_ORDER_NO ,MEM_NO ,EMP_NO ,RES_TIME ,RES_DATE ,PEOPLE ,TIME_PERI_NO ,INFO_STS, SEAT_STS FROM RES_ORDER ORDER BY RES_NO";
	private static final String GET_ONE_STMT = "SELECT MEAL_ORDER_NO ,MEM_NO ,EMP_NO ,RES_TIME ,RES_DATE ,PEOPLE ,TIME_PERI_NO ,INFO_STS, SEAT_STS FROM RES_ORDER WHERE RES_NO = ?";
	private static final String UPDATE = "UPDATE RES_ORDER SET MEAL_ORDER_NO=? ,MEM_NO=? ,EMP_NO=? ,RES_DATE=? ,PEOPLE=? ,TIME_PERI_NO=? ,INFO_STS=? ,SEAT_STS=? WHERE RES_NO = ?";

	@Override
	public void insert(ResOrderVO resOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			System.out.println("Insert success");

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			System.out.println("Update success");

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			System.out.println("FindByPrimaryKey success");

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
			System.out.println("Get All success");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

//	public static void main(String[] args) {
//
//		ResOrderJDBCDAO dao = new ResOrderJDBCDAO();
//		// insert
//		ResOrderVO resOrderVO = new ResOrderVO();
//		resOrderVO.setMeal_order_no(null);
//		resOrderVO.setMem_no("MEM0007");
//		resOrderVO.setEmp_no("EMP0001");
//		resOrderVO.setRes_date(java.sql.Date.valueOf("2020-10-05"));
//		resOrderVO.setPeople(new Integer(6));
//		resOrderVO.setTime_peri_no("TP0001");
//		resOrderVO.setInfo_sts(new Integer(0));
//		resOrderVO.setSeat_sts(new Integer(0));
//		dao.insert(resOrderVO);
//
//		// update
//		ResOrderVO resOrderVO1 = new ResOrderVO();
//		resOrderVO1.setRes_no("RESO0009");
//		resOrderVO1.setMeal_order_no("");
//		resOrderVO1.setMem_no("MEM0007");
//		resOrderVO1.setEmp_no("EMP0001");
//		resOrderVO1.setRes_date(java.sql.Date.valueOf("2020-10-05"));
//		resOrderVO1.setPeople(new Integer(6));
//		resOrderVO1.setTime_peri_no("TP0001");
//		resOrderVO1.setInfo_sts(new Integer(3));
//		resOrderVO1.setSeat_sts(new Integer(1));
//		dao.update(resOrderVO1);
//
//		// get one seat obj
//		ResOrderVO resOrderVO2 = dao.findByPrimaryKey("RESO0009");
//		System.out.print(resOrderVO2.getRes_no() + ",\t");
//		System.out.print(resOrderVO2.getMeal_order_no() + ",\t");
//		System.out.print(resOrderVO2.getMem_no() + ",\t");
//		System.out.print(resOrderVO2.getEmp_no() + ",\t");
//		System.out.print(resOrderVO2.getRes_time() + ",\t");
//		System.out.print(resOrderVO2.getRes_date() + ",\t");
//		System.out.print(resOrderVO2.getPeople() + ",\t");
//		System.out.print(resOrderVO2.getTime_peri_no() + ",\t");
//		System.out.print(resOrderVO2.getInfo_sts() + ",\t");
//		System.out.print(resOrderVO2.getSeat_sts() + ",\t");
//
//		// select all
//		List<ResOrderVO> list = dao.getAll();
//		for (ResOrderVO resOrderVO3 : list) {
//			System.out.print(resOrderVO3.getRes_no() + ",\t");
//			System.out.print(resOrderVO3.getMeal_order_no() + ",\t");
//			System.out.print(resOrderVO3.getMem_no() + ",\t");
//			System.out.print(resOrderVO3.getEmp_no() + ",\t");
//			System.out.print(resOrderVO3.getRes_time() + ",\t");
//			System.out.print(resOrderVO3.getRes_date() + ",\t");
//			System.out.print(resOrderVO3.getPeople() + ",\t");
//			System.out.print(resOrderVO3.getTime_peri_no() + ",\t");
//			System.out.print(resOrderVO3.getInfo_sts() + ",\t");
//			System.out.print(resOrderVO3.getSeat_sts() + ",\t");
//			System.out.println();
//		}
//	}
}
