package com.laptrinhjavaweb.service.implement;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICategoryDao;
import com.laptrinhjavaweb.dao.INewDao;
import com.laptrinhjavaweb.model.Category;
import com.laptrinhjavaweb.model.News;
import com.laptrinhjavaweb.service.INewService;

import paging.Pageble;

public class NewService implements INewService {

	@Inject
	private INewDao newDAO;
	@Inject
	private ICategoryDao categoryDAO;
	
	@Override
	public List<News> findByCategoryId(Long categoryId) {
		// TODO Auto-generated method stub
		return newDAO.findByCategoryId(categoryId);
	}

	@Override
	public News save(News n) {
		Long newId=newDAO.save(n);
        n.setCreateDate(new Timestamp(System.currentTimeMillis()));
        
		return newDAO.findOne(newId);
	}

	@Override
	public News update(News n) {
		News oldn=newDAO.findOne(n.getId());
		n.setCreateDate(oldn.getCreateDate());
		n.setCreateBy(oldn.getCreateBy());
		n.setModifieDate(new Timestamp(System.currentTimeMillis()));
		
		newDAO.update(n);
		return newDAO.findOne(n.getId());
	}

	@Override
	public void delete(long[] ids) {
		for(long id : ids) {
			newDAO.delete(id);
		}		
	}

	@Override
	public List<News> findAll() {
		return newDAO.findAll();
	}

	@Override
	public List<News> findAll(Pageble pageble) {
		return newDAO.findAll(pageble);
	}

	@Override
	public News findOne(long id) {
		News n= newDAO.findOne(id);
		Category c=categoryDAO.findOne(n.getCategoryId());
		n.setCategoryCode(c.getCode());
		return n;
	}
	

}
