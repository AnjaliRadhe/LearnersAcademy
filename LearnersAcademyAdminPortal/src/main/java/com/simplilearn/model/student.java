package com.simplilearn.model;


public class student {

	 int sid;
     String sfname;
	 String slname;
	
	classes classes;

	public student() {
		super();
	}


	public student(int sid, String sfname, String slname, classes classes) {
		super();
		this.sid = sid;
		this.sfname = sfname;
		this.slname = slname;
		this.classes=classes;
	}

	public student(String sfname, String slname, classes classes) {
		super();
		this.sfname = sfname;
		this.slname = slname;
		this.classes=classes;
	}
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSfname() {
		return sfname;
	}

	public void setSfname(String sfname) {
		this.sfname = sfname;
	}

	public String getSlname() {
		return slname;
	}

	public void setSlname(String slname) {
		this.slname = slname;
	}

	public classes getClasses() {
		return classes;
	}

	public void setClasses(classes classes) {
		this.classes = classes;
	}

//	public int getcid() {
//		return classes.cid;
//	}
//	
//	public void setcid(int cid) {
//		this.classes.cid= cid;
//	}
//	
//	public String getcname() {
//		return classes.cname;
//	}
//
//	public void setcname(String cname) {
//		this.classes.cname=cname;
//	}
//
//	public String getcshortcode() {
//		return classes.cshortcode;
//	}
//
//	public void setcshortcode(String cshortcode) {
//		this.classes.cshortcode = cshortcode;
//	}
//	
//	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Classid [id=" + sid + ", classname=" + sfname + ", class shortcod =" + slname + "]";
	}
}
