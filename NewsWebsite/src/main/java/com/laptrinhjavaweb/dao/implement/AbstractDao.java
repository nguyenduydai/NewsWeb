package com.laptrinhjavaweb.dao.implement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.dao.GenericDao;
import com.laptrinhjavaweb.mapper.RowMapper;
import com.laptrinhjavaweb.model.News;

public class AbstractDao<T> implements GenericDao<T>{
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/hoclaptrinhjavaweb";
			String user="root";
			String password="2003dai2003";
			return DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public <T> List<T> query(String sql, RowMapper<T> rowMappper, Object... parameters) {
		List<T> results=new ArrayList<>();
		Connection con=getConnection();	
		PreparedStatement pre=null;
		ResultSet result=null;	
		try {
			con=getConnection();
			pre=con.prepareStatement(sql);
			setParameters(pre,parameters);
			result=pre.executeQuery();
			while(result.next()) {
				results.add(rowMappper.mapRow(result));
			}
			return results;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally {
			try {
				if(con!=null)	con.close();
				if(pre!=null)	pre.close();
				if(result!=null)result.close();
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
		}	
	}

	private void setParameters(PreparedStatement pre, Object... parameters) {
		for(int i=0;i<parameters.length;i++)
		{
			try {
				Object parameter=parameters[i];
				int index=i+1;
				if(parameter instanceof Long) {
					pre.setLong(index, (Long)parameter);
				}
				else if(parameter instanceof String) {
					pre.setString(index, (String)parameter);
				}
				else if(parameter instanceof Integer) {
					pre.setInt(index, (Integer)parameter);
				}
				else if(parameter instanceof Timestamp) {
					pre.setTimestamp(index, (Timestamp)parameter);
				}else {
					pre.setNull(index, java.sql.Types.NULL);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	@Override
	public void update(String sql, Object... parameters) {
		Connection con=null;
		PreparedStatement pre=null;
		try {
			con=getConnection();
			con.setAutoCommit(false);
			pre=con.prepareStatement(sql);
			setParameters(pre,parameters);
			pre.executeUpdate();
			con.commit();
			} catch (SQLException e) {
				if(con!=null)
				{
					try {
						con.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				e.printStackTrace();
			}finally {
				try {
					if(con!=null)	con.close();
					if(pre!=null)	pre.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		
	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Connection con=null;
		PreparedStatement pre=null;
		ResultSet rs=null;
		Long id = null;
		try {
			con=getConnection();
			con.setAutoCommit(false);
			pre=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			setParameters(pre,parameters);
			pre.executeUpdate();
			rs=pre.getGeneratedKeys();
			if(rs.next())	id=rs.getLong(1);
			con.commit();
			} catch (SQLException e) {
				if(con!=null)
				{
					try {
						con.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			e.printStackTrace();
			return null;
			}finally {
				try {
					if(con!=null)	con.close();
					if(pre!=null)	pre.close();
					if(rs!=null)	rs.close();
				}catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		return id;
	}
}

