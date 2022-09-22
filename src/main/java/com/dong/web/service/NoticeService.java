package com.dong.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dong.web.entity.Notice;

public class NoticeService {
	public List<Notice> getNoticeList(){
		return getNoticeList(1, "title", "");
	}
	
	public List<Notice> getNoticeList(int page){
		return getNoticeList(page, "title", "");
	}
	
	public List<Notice> getNoticeList(int page, String field, String query){
		List<Notice> list = new ArrayList();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"
					+ "localhost:3306/ex", "root","djib1213ppoo");
			String sql = "SELECT Q.* FROM (SELECT @ROWNUM := @ROWNUM + 1 AS ROWNUM, "
					+ "N.* FROM (SELECT @ROWNUM := 0) R, (SELECT * FROM NOTICE WHERE ? like '" + query + "%') N) Q "
					+ "WHERE ROWNUM BETWEEN ? AND ?;";
		
			PreparedStatement stmt = con.prepareStatement(sql); 
			stmt.setString(1, field);
			stmt.setInt(2, 5 * page - 4);
			stmt.setInt(3, 5 * page);
			
			ResultSet rs = stmt.executeQuery(); 
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String file = rs.getString("file");
			
				Notice notice = new Notice(id, title, content, file);
			
				list.add(notice);
			}
			
			rs.close();
			stmt.close();
			con.close(); 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public int getNoticeCount() {
		return getNoticeCount("title", "");
	}
	
	public int getNoticeCount(String field, String query) {
		int count = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"
					+ "localhost:3306/ex", "root","djib1213ppoo");
			String sql = "SELECT COUNT(*) as cnt FROM (SELECT * FROM NOTICE WHERE ? like '" + query + "%') N;";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, field);
			ResultSet rs = stmt.executeQuery();  
			rs.next();
			
			count = rs.getInt("count");
			
		    rs.close();
			stmt.close();
			con.close(); 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	
	public Notice getNotice(int id) {
		Notice notice = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"
					+ "localhost:3306/ex", "root","djib1213ppoo");
			String sql = "SELECT * FROM NOTICE WHERE ID = ?;";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();  
			rs.next();
			
			String title = rs.getString("title");
			String content = rs.getString("content");
			String file = rs.getString("file");
			
			notice = new Notice(id, title, content, file);
		
		    rs.close();
			stmt.close();
			con.close(); 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notice;
	}
	
	public Notice getPrevNotice(int id) {
		Notice notice = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"
					+ "localhost:3306/ex", "root","djib1213ppoo");
			String sql = "SELECT Q.* FROM(SELECT @ROWNUM := @ROWNUM + 1 AS ROWNUM, N.* FROM NOTICE N, (SELECT @ROWNUM := 0)R WHERE ID < ? ORDER BY ID DESC)Q WHERE ROWNUM = 1;";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();  
			rs.next();
			
			String title = rs.getString("title");
			String content = rs.getString("content");
			String file = rs.getString("file");
			
			notice = new Notice(id, title, content, file);
		
		    rs.close();
			stmt.close();
			con.close(); 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notice;
	}
	
	public Notice getNextNotice(int id) {
		Notice notice = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"
					+ "localhost:3306/ex", "root","djib1213ppoo");
			String sql = "SELECT Q.* FROM (SELECT @ROWNUM := @ROWNUM + 1 AS ROWNUM, N.* FROM NOTICE N, (SELECT @ROWNUM := 0)R WHERE ID > ?)Q WHERE ROWNUM = 1;";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();  
			rs.next();
			
			String title = rs.getString("title");
			String content = rs.getString("content");
			String file = rs.getString("file");
			
			notice = new Notice(id, title, content, file);
		
		    rs.close();
			stmt.close();
			con.close(); 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notice;
	}
}
