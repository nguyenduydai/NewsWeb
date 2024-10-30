package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.News;

import paging.Pageble;

public interface INewDao extends GenericDao<News> {
	News findOne (Long id);
	List<News> findAll();
	List<News> findAll(Pageble pageble);
	List<News> findByCategoryId(Long categoryId);
	Long save(News n);
	void update(News n);
	void delete(long id);
}
