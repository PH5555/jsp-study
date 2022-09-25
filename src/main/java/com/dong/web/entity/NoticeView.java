package com.dong.web.entity;

public class NoticeView extends Notice {
	
	private int commentCnt;
	

	public int getCommentCnt() {
		return commentCnt;
	}

	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}

	public NoticeView(int id, String title, String content, String file, int commentCnt) {
		super(id, title, content, file);
		this.commentCnt = commentCnt;
	}

}
