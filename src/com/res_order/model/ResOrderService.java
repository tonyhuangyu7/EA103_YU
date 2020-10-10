package com.res_order.model;

import java.util.List;

public class ResOrderService {
	private ResOrderDAO_interface dao;

	public ResOrderService() {
//		dao = new ResOrderJDBCDAO();
		dao = new ResOrderDAO();
	}

	public ResOrderVO addResOrder(String meal_order_no, String mem_no, String emp_no, java.sql.Timestamp res_time,
			java.sql.Date res_date, Integer people, String time_peri_no, Integer info_sts, Integer seat_sts) {

		ResOrderVO resOrderVO = new ResOrderVO();

		resOrderVO.setMeal_order_no(meal_order_no);
		resOrderVO.setMem_no(mem_no);
		resOrderVO.setEmp_no(emp_no);
		resOrderVO.setRes_time(res_time);
		resOrderVO.setRes_date(res_date);
		resOrderVO.setPeople(people);
		resOrderVO.setTime_peri_no(time_peri_no);
		resOrderVO.setInfo_sts(info_sts);
		resOrderVO.setSeat_sts(seat_sts);

		dao.insert(resOrderVO);

		return resOrderVO;
	}

	public ResOrderVO updateResOrder(String res_no, String meal_order_no, String mem_no, String emp_no,
			java.sql.Timestamp res_time, java.sql.Date res_date, Integer people, String time_peri_no, Integer info_sts,
			Integer seat_sts) {

		ResOrderVO resOrderVO = new ResOrderVO();
		
		resOrderVO.setRes_no(res_no);
		resOrderVO.setMeal_order_no(meal_order_no);
		resOrderVO.setMem_no(mem_no);
		resOrderVO.setEmp_no(emp_no);
		resOrderVO.setRes_time(res_time);
		resOrderVO.setRes_date(res_date);
		resOrderVO.setPeople(people);
		resOrderVO.setTime_peri_no(time_peri_no);
		resOrderVO.setInfo_sts(info_sts);
		resOrderVO.setSeat_sts(seat_sts);

		dao.update(resOrderVO);

		return resOrderVO;

	}

	public ResOrderVO getOneResOrder(String res_no) {
		return dao.findByPrimaryKey(res_no);
	}

	public List<ResOrderVO> getAll() {
		return dao.getAll();
	}
}
