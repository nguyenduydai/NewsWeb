package com.laptrinhjavaweb.dao;
import java.util.List;

import com.laptrinhjavaweb.model.Category;
public interface ICategoryDao extends GenericDao<Category> {
	List<Category>	findAll();
	Category findOne(long id);
}
