package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.mapper.RowMapper;

public interface GenericDao<T> {
	<T> List<T> query(String sql,RowMapper<T> rowMappper,Object... parameters);
	void update(String sql,Object... parameters);
	Long insert(String sql,Object... parameters);
}
