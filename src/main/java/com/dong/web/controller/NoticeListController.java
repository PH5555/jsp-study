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
		NoticeService noticeService = new NoticeService();
		request.setAttribute("list", noticeService.getNoticeList());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp");
		dispatcher.forward(request, response);
		 
	}
}
