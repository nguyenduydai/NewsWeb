package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.News;

import paging.Pageble;

public interface INewService {
	List<News> findByCategoryId(Long categoryId);
	List<News> findAll();
	List<News> findAll(Pageble pageble);
	News save(News n);
	News update(News n);
	void delete(long[] ids);
	News findOne(long id);
}
