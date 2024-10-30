package com.laptrinhjavaweb.service.implement;

import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICategoryDao;
import com.laptrinhjavaweb.model.Category;
import com.laptrinhjavaweb.service.ICategoryService;

public class CategoryService implements ICategoryService{
    @Inject
	private ICategoryDao categoryDAO;
	@Override
	public List<Category> findAll() {
		return categoryDAO.findAll();
	}

}
