package com.laptrinhjavaweb.dao.implement;

import java.util.List;

import com.laptrinhjavaweb.dao.ICategoryDao;
import com.laptrinhjavaweb.mapper.CategoryMapper;
import com.laptrinhjavaweb.mapper.NewMapper;
import com.laptrinhjavaweb.model.Category;
import com.laptrinhjavaweb.model.News;

public class CategoryDao extends AbstractDao<Category> implements ICategoryDao{

	@Override
	public List<Category> findAll() {
		String sql="SELECT * FROM category";
		return query(sql,new CategoryMapper());
	}

	@Override
	public Category findOne(long id) {
		String sql="SELECT * FROM category WHERE id = ?";
		List<Category> ns= query(sql,new CategoryMapper(),id);
		return ns.isEmpty() ? null : ns.get(0);
	}
	
	
}
