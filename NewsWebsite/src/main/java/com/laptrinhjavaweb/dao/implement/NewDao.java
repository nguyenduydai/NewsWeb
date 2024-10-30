package com.laptrinhjavaweb.dao.implement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.dao.INewDao;
import com.laptrinhjavaweb.mapper.CategoryMapper;
import com.laptrinhjavaweb.mapper.NewMapper;
import com.laptrinhjavaweb.model.News;

import paging.Pageble;

public class NewDao extends AbstractDao<News> implements INewDao{

	
	@Override
	public List<News> findAll() {
		
		String sql="SELECT * FROM news";
		return query(sql,new NewMapper());
	}

	@Override
	public List<News> findByCategoryId(Long categoryId) {
		String sql="SELECT * FROM news WHERE categoryid = ?";
		return query(sql,new NewMapper(),categoryId);
	}

	@Override
	public Long save(News n) {
		StringBuilder sql = new StringBuilder("INSERT INTO news (title, content,");
		sql.append(" thumbnail, shortdescription, categoryid, createddate, createdby)");
		sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?)");
		return insert(sql.toString(), n.getTitle(), n.getContent(), n.getThumbnail(), n.getShortDescription(),
				n.getCategoryId(),n.getCreateDate(), n.getCreateBy());	
	}

	@Override
	public News findOne(Long id) {
		String sql="SELECT * FROM news WHERE id = ?";
		List<News> ns= query(sql,new NewMapper(),id);
		return ns.isEmpty() ? null : ns.get(0);
	}

	@Override
	public void update(News n) {
		StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, thumbnail = ?,");
		sql.append(" shortdescription = ?, content = ?, categoryid = ?,");
		sql.append(" createdate = ?, createby = ?, modifiedate = ?, modifieby = ? WHERE id = ?");
		update(sql.toString(), n.getTitle(), n.getThumbnail(), n.getShortDescription(),
				n.getContent(), n.getCategoryId(), n.getCreateDate(), 
				n.getCreateBy(), n.getModifieDate(), 
				n.getModifieBy(), n.getId());
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM news WHERE id = ?";
		update(sql, id);
		
	}

	@Override
	public List<News> findAll(Pageble pageble) {
		StringBuilder sql= new StringBuilder("SELECT * FROM news");
		if(pageble.getSortName()!=null && pageble.getSortBy()!=null)
			sql.append(" ORDER BY "+pageble.getSortName()+" "+ pageble.getSortBy());
		if(pageble.getOffset()!=null && pageble.getLimit()!=null)
		{
			sql.append(" LIMIT ?, ?");
		    return query(sql.toString(),new NewMapper(),pageble.getOffset(),pageble.getLimit());
		}else {
			return query(sql.toString(),new NewMapper());
		}
		    
	}

}
