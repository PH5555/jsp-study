package com.dong.web.entity;

public class Notice {
	private int id;
	private String title;
	private String content;
	private String file;
	private int pub;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public int getPub() {
		return pub;
	}

	public void setPub(int pub) {
		this.pub = pub;
	}
	
	public Notice() {
		
	}

	public Notice(int id, String title, String content, String file, int pub) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.file = file;
		this.pub = pub;
	}
	
}
