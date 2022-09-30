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
import com.dong.web.entity.NoticeView;

public class NoticeService {
	
	public int removeNoticeAll(int[] ids) {
		return 0;
	}
	
	public int pubNoticeAll(int[] ids) {
		return 0;
	}
	
	public int insertNotice(Notice notice) {
		int result = 0;
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"
					+ "localhost:3306/ex", "root","djib1213ppoo");
			String sql = "INSERT INTO `ex`.`NOTICE` (`title`, `content`, `pub`, `file`) VALUES ('"+notice.getTitle()+"', '"+notice.getContent()+"', '"+notice.getPub()+"', '"+notice.getFile()+"');";
			
			Statement stmt = con.createStatement();
			result = stmt.executeUpdate(sql); 
			
			stmt.close();
			con.close(); 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteNoticeAll(String[] deleteIds) {
		int result = 0;
		String query = "";
		
		for(int i = 0; i < deleteIds.length; i++) {
			query += deleteIds[i];
			if(i != deleteIds.length - 1) query += ",";
		}
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"
					+ "localhost:3306/ex", "root","djib1213ppoo");
			String sql = "DELETE FROM NOTICE WHERE ID IN (" + query + ");";
			
			Statement stmt = con.createStatement(); 
			result = stmt.executeUpdate(sql); 
			
			stmt.close();
			con.close(); 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteNotice(int id) {
		return 0;
	}
	
	public int updateNotice(Notice notice) {
		return 0;
	}
	
	public List<Notice> getNoticeNewestList(){
		return null;
	}
	
	
	public List<NoticeView> getNoticeList(){
		return getNoticeList(1, "title", "");
	}
	
	public List<NoticeView> getNoticeList(int page){
		return getNoticeList(page, "title", "");
	}
	
	public List<NoticeView> getNoticeList(int page, String field, String query){
		List<NoticeView> list = new ArrayList();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"
					+ "localhost:3306/ex", "root","djib1213ppoo");
			String sql = "SELECT Q.* FROM (SELECT @ROWNUM := @ROWNUM + 1 AS ROWNUM, "
					+ "N.* FROM (SELECT @ROWNUM := 0) R, (SELECT * FROM NOTICE_VIEW WHERE " + field + " like '" + query + "%') N) Q "
					+ "WHERE ROWNUM BETWEEN ? AND ?;";
			
			PreparedStatement stmt = con.prepareStatement(sql); 
			
			stmt.setInt(1, 5 * page - 4);
			stmt.setInt(2, 5 * page);
			
			ResultSet rs = stmt.executeQuery(); 
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String file = rs.getString("file");
				int pub = rs.getInt("pub");
				int cnt = rs.getInt("cnt");
			
				NoticeView notice = new NoticeView(id, title, content, file, pub, cnt);
			
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
			String sql = "SELECT COUNT(*) as cnt FROM (SELECT * FROM NOTICE WHERE " + field + " like '" + query + "%') N;";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);  
			rs.next();
			
			count = rs.getInt("cnt");
			
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
			int pub = rs.getInt("pub");
			
			notice = new Notice(id, title, content, file, pub);
		
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
			int pub = rs.getInt("pub");
			
			notice = new Notice(id, title, content, file, pub);
		
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
			int pub = rs.getInt("pub");
			
			notice = new Notice(id, title, content, file, pub);
		
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
