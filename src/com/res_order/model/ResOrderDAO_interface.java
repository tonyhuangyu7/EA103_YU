package com.res_order.model;

import java.util.List;

public interface ResOrderDAO_interface {
	public void insert(ResOrderVO resOrderVO);

	public void update(ResOrderVO resOrderVO);

	public ResOrderVO findByPrimaryKey(String res_no);

	public List<ResOrderVO> getAll();
}
