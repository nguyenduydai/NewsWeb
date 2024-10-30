package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.News;

public class NewMapper implements RowMapper<News> {

	@Override
	public News mapRow(ResultSet rs) {
		News n=new News();
		try {
			n.setId(rs.getLong("id"));
			n.setTitle(rs.getString("title"));
			n.setThumbnail(rs.getString("thumbnail"));
			n.setShortDescription(rs.getString("shortdescription"));
			n.setContent(rs.getString("content"));
			n.setCategoryId(rs.getLong("categoryid"));
			n.setCreateDate(rs.getTimestamp("createdate"));
			n.setCreateBy(rs.getString("createby"));
			if (rs.getTimestamp("modifiedate") != null) {
				n.setModifieDate(rs.getTimestamp("modifiedate"));
			}
			if (rs.getString("modifieby") != null) {
				n.setModifieBy(rs.getString("modifieby"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return n;
	}

}
