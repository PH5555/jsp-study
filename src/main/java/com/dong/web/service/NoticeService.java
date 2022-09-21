package com.dong.web.service;

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
		return null;
	}
	
	public int getNoticeCount() {
		return getNoticeCount("title", "");
	}
	
	public int getNoticeCount(String field, String query) {
		return 0;
	}
	
	public Notice getNotice(int id) {
		return null;
	}
	
	public Notice getPrevNotice(int id) {
		return null;
	}
	
	public Notice getNextNotice(int id) {
		return null;
	}
}
