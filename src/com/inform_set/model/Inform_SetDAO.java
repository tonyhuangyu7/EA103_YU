package com.inform_set.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Inform_SetDAO implements Inform_SetDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/EA103G7");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_IS_STMT = "INSERT INTO INFORM_SET (IS_NO,EMP_NO,IS_CONT,IS_DATE) VALUES ('IS'||LPAD(to_char(SEQ_IS_NO.nextval), 4, '0'), ?, ?, ?)"; 
	private static final String UPDATE_IS_STMT = "UPDATE INFORM_SET SET EMP_NO=?, IS_CONT=?, IS_DATE=? WHERE IS_NO=?";
	private static final String DELETE_IS_STMT = "DELETE FROM INFORM_SET WHERE IS_NO=?"; 
	private static final String GET_BY_ISNO = "SELECT IS_NO, IS_CONT, IS_DATE, EMP_NO FROM INFORM_SET WHERE IS_NO=?"; 
	private static final String GET_BY_EMPNO = "SELECT IS_NO, IS_CONT, IS_DATE, EMP_NO FROM INFORM_SET WHERE EMP_NO=? ORDER BY IS_NO DESC"; 
	private static final String GET_BY_VAGUE = "SELECT IS_NO, IS_CONT, IS_DATE, EMP_NO FROM INFORM_SET WHERE IS_CONT LIKE ? ORDER BY IS_NO DESC"; 
	private static final String GET_BY_DATE = "SELECT IS_NO, IS_CONT, IS_DATE, EMP_NO FROM INFORM_SET WHERE IS_DATE BETWEEN to_date(?,'yyyy-mm-dd') AND to_date(?,'yyyy-mm-dd') ORDER BY IS_DATE DESC";
	private static final String GET_ALL = "SELECT IS_NO, IS_CONT, IS_DATE, EMP_NO FROM INFORM_SET ORDER BY IS_NO DESC"; 
	
	@Override
	public void insert(Inform_SetVO inform_setVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_IS_STMT);
			pstmt.setString(1, inform_setVO.getEmp_no());
			pstmt.setString(2, inform_setVO.getIs_cont()); 
			pstmt.setDate(3, inform_setVO.getIs_date());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
	public void update(Inform_SetVO inform_setVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_IS_STMT);
			pstmt.setString(1, inform_setVO.getEmp_no());
			pstmt.setString(2, inform_setVO.getIs_cont());
			pstmt.setDate(3, inform_setVO.getIs_date());
			pstmt.setString(4, inform_setVO.getIs_no());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
	public void delete(String is_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_IS_STMT);
			pstmt.setString(1, is_no);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
	public Inform_SetVO findByIsNo(String is_no) {
		Inform_SetVO inform_setVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_ISNO);
			pstmt.setString(1, is_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				inform_setVO = new Inform_SetVO();
				inform_setVO.setIs_no(rs.getString("IS_NO"));
				inform_setVO.setIs_cont(rs.getString("IS_CONT"));
				inform_setVO.setIs_date(rs.getDate("IS_DATE"));
				inform_setVO.setEmp_no(rs.getString("EMP_NO"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
		return inform_setVO;
	}

	@Override
	public List<Inform_SetVO> findByEmpNo(String emp_no) {
		List<Inform_SetVO> list = new ArrayList<Inform_SetVO>();
		Inform_SetVO inform_setVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_EMPNO);
			pstmt.setString(1, emp_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				inform_setVO = new Inform_SetVO();
				inform_setVO.setIs_no(rs.getString("IS_NO"));
				inform_setVO.setIs_cont(rs.getString("IS_CONT"));
				inform_setVO.setIs_date(rs.getDate("IS_DATE"));
				inform_setVO.setEmp_no(rs.getString("EMP_NO"));
				list.add(inform_setVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
	

	@Override
	public List<Inform_SetVO> findByIsCont(String is_cont) {
		List<Inform_SetVO> list = new ArrayList<Inform_SetVO>();
		Inform_SetVO inform_setVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_VAGUE);
			pstmt.setString(1, '%'+is_cont+'%');
			rs = pstmt.executeQuery();
			while (rs.next()) {
				inform_setVO = new Inform_SetVO();
				inform_setVO.setIs_no(rs.getString("IS_NO"));
				inform_setVO.setIs_cont(rs.getString("IS_CONT"));
				inform_setVO.setIs_date(rs.getDate("IS_DATE"));
				inform_setVO.setEmp_no(rs.getString("EMP_NO"));
				list.add(inform_setVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
	
	@Override
	public List<Inform_SetVO> findByDate(String startDate, String stopDate) {
		List<Inform_SetVO> list = new ArrayList<Inform_SetVO>();
		Inform_SetVO inform_setVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_DATE);
			pstmt.setString(1, startDate);
			pstmt.setString(2, stopDate);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				inform_setVO = new Inform_SetVO();
				inform_setVO.setIs_no(rs.getString("IS_NO"));
				inform_setVO.setIs_cont(rs.getString("IS_CONT"));
				inform_setVO.setIs_date(rs.getDate("IS_DATE"));
				inform_setVO.setEmp_no(rs.getString("EMP_NO"));
				list.add(inform_setVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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

	@Override
	public List<Inform_SetVO> getAll() {
		List<Inform_SetVO> list = new ArrayList<Inform_SetVO>();
		Inform_SetVO inform_setVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				inform_setVO = new Inform_SetVO();
				inform_setVO.setIs_no(rs.getString("IS_NO"));
				inform_setVO.setIs_cont(rs.getString("IS_CONT"));
				inform_setVO.setIs_date(rs.getDate("IS_DATE"));
				inform_setVO.setEmp_no(rs.getString("EMP_NO"));
				list.add(inform_setVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
