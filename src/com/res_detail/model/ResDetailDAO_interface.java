package com.res_detail.model;

import java.util.List;

public interface ResDetailDAO_interface {
	public void insert(ResDetailVO resDetailVO);

	public void update(ResDetailVO resDetailVO);
	
	public ResDetailVO findByPrimaryKey(String res_de_no);

	public List<ResDetailVO> getAll();
}
