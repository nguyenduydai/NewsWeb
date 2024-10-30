package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.Category;

public class CategoryMapper implements RowMapper<Category> {

	@Override
	public Category mapRow(ResultSet rs) {
		Category cat=new Category();
		try {
			cat.setId(rs.getLong("id"));
			cat.setName(rs.getString("name"));
			cat.setCode(rs.getString("code"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        return cat;
	}

}
