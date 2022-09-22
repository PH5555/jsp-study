package com.dong.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dong.web.entity.Notice;
import com.dong.web.service.NoticeService;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		NoticeService noticeService = new NoticeService();
		request.setAttribute("notice", noticeService.getNotice(id));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp");
		dispatcher.forward(request, response);
		
	}
}
