package com.dong.web.controller.admin.notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dong.web.entity.Notice;
import com.dong.web.service.NoticeService;

@WebServlet("/admin/notice/list")
public class ListController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] deleteIds = request.getParameterValues("del");
		String[] openIds = request.getParameterValues("open");
	    String cmd = request.getParameter("cmd");
	    
	    if(cmd.equals("일괄공개")) {
	    	
	    }
	    else if(cmd.equals("일괄삭제")) {
	    	NoticeService service = new NoticeService();
	    	service.deleteNoticeAll(deleteIds);
	    }
		
		response.sendRedirect("list");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String field = "title";
		String query = "";
		int page = 1;
		
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		
		if(field_ != null && !field_.equals("")) {
			field = field_;
		}
		if(query_ != null && !query_.equals("")) {
			query = query_;
		}
		if(page_ != null) {
			page= Integer.parseInt(page_);
		}
		
		NoticeService noticeService = new NoticeService();
		request.setAttribute("list", noticeService.getNoticeList(page, field, query));
		request.setAttribute("count", noticeService.getNoticeCount(field, query));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/list.jsp");
		dispatcher.forward(request, response);
		 
	}
}
