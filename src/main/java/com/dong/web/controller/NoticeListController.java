package com.dong.web.controller;

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

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet{
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
		
		System.out.print(noticeService.getNoticeCount(field, query));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp");
		dispatcher.forward(request, response);
		 
	}
}
