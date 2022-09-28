package com.dong.web.controller.admin.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.dong.web.entity.Notice;
import com.dong.web.service.NoticeService;

@MultipartConfig(
	fileSizeThreshold=1024*1024,
	maxFileSize=1024*1024*50,
	maxRequestSize=1024*1024*50*5
)
@WebServlet("/admin/notice/reg")
public class RegController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String open_ = request.getParameter("open");
		
		Part part = request.getPart("file");
		String fileName = part.getSubmittedFileName();
		InputStream stream = part.getInputStream();
		
		String realPath = request.getServletContext().getRealPath("/uplad");
		String filePath = realPath + File.separator + fileName;
		
		FileOutputStream fos = new FileOutputStream(filePath);
		
		byte[] buf = new byte[1024];
		int size = 0;
		while((size = stream.read(buf)) != -1) {
			fos.write(buf, 0, size);
		}
		
		stream.close();
		fos.close();
		
		int open = 0;
		
		if(open_ != null) open = 1;
	
		Notice notice = new Notice();
		
		notice.setTitle(title);
		notice.setContent(content);
		notice.setPub(open);
		NoticeService service = new NoticeService();
		service.insertNotice(notice);
		
		response.sendRedirect("list");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp");
		dispatcher.forward(request, response);
		 
	}
}
