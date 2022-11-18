package com.simplilearn.model;


public class classes {

	 int cid;
	 String cname;
	 String cshortcode;

	public classes() {
		super();
	}

	public classes(int cid, String cname, String cshortcode) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.cshortcode = cshortcode;
		 
	}

	public classes(String cname, String cshortcode) {
		super();
		this.cname = cname;
		this.cshortcode = cshortcode;
		 
	}



	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCshortcode() {
		return cshortcode;
	}

	public void setCshortcode(String cshortcode) {
		this.cshortcode = cshortcode;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Classid [id=" + cid + ", classname=" + cname + ", class shortcod =" + cshortcode + "]";
	}

	 
}
