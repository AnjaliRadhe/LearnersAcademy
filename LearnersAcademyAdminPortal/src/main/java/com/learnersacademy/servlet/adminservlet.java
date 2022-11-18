package com.learnersacademy.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.learnersacademy.Utill.JDBCUtill;
import com.simplilearn.model.classes;
import com.simplilearn.model.student;
import com.simplilearn.utill.HibernateUtillXml;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/")
public class adminservlet extends HttpServlet {
	public static final String LOGIN = "SELECT * FROM LOGIN_DETAILS WHERE EMAIL= ?";
	public static final String ADDSUBJECTSTODB = "INSERT INTO SUBJECT_DETAILS VALUES(?,?,?);";
	public static final String ADDCLASSESTODB = "INSERT INTO CLASS_DETAILS VALUES(?,?,?);";
	public static final String ADDTEACHERSTODB = "INSERT INTO TEACHER_DETAILS VALUES(?,?,?);";
	public static final String ASSIGNSCTODB = "INSERT INTO CLASSES_SUBJECTS VALUES(?,?);";
	public static final String ASSIGNSTSODB = "INSERT INTO TEACHERS_SUBJECTS VALUES(?,?);";
	public static final String SEARCH = "SELECT * FROM CLASS_DETAILS WHERE  CNAME = ?";
	public static final String SEARCHCLASS = "SELECT * FROM CLASS_DETAILS WHERE  CNAME= ?";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public adminservlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("************START-doPost()************");

		String path = request.getServletPath();
		System.out.println("PATH-" + path); // insert

		switch (path) {
		
		case "/home":
			request.setAttribute("pageload" ,"N"); 
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
			rd.forward(request, response);
			break;

		case "/login":
			request.setAttribute("alertMsg", "Welcome Admin");
			checklogin(request, response);
			break;

		case "/logout": {
			rd = request.getRequestDispatcher("/WEB-INF/logout.jsp");
			rd.forward(request, response);
			break;
		}

		case "/addsubject": {
			request.setAttribute("pageload" ,"N"); 
			rd = request.getRequestDispatcher("/WEB-INF/addsubject.jsp");
			rd.forward(request, response);
			break;
		}

		case "/addsubjectdb":
			addsubjectdb(request, response);
			break;
			
		case "/assignTS": {
			request.setAttribute("pageload" ,"N"); 
			rd = request.getRequestDispatcher("/WEB-INF/assignTS.jsp");
			rd.forward(request, response);
			break;
		}	
		
		case "/assignTStoDB":
			assignTStoDB(request, response);
			break;
		
		case "/addclass":
			request.setAttribute("pageload" ,"N"); 
			rd = request.getRequestDispatcher("/WEB-INF/addclasses.jsp");
			rd.forward(request, response);
			break;
			
		case "/addclassesdb":
			addclassesdb(request, response);
			break;
			
		case "/addteachers":
			request.setAttribute("pageload" ,"N"); 
			rd = request.getRequestDispatcher("/WEB-INF/addteachers.jsp");
			rd.forward(request, response);
			break;
			
		case "/addteachersdb":
			addteachersdb(request, response);
			break;
			
		case "/assignSC":
			request.setAttribute("pageload" ,"N"); 
			rd = request.getRequestDispatcher("/WEB-INF/assignSubjecttoclasses.jsp");
			rd.forward(request, response);
			break;
			
		case "/assignSCtoDB":
			assignSCtoDB(request, response);
			break;
			
		case "/listsubjeccts":
			request.setAttribute("pageload" ,"N"); 
			rd = request.getRequestDispatcher("/WEB-INF/listsubjects.jsp");
			rd.forward(request, response);
			break;
			
		case "/listclasses":
			request.setAttribute("pageload" ,"N"); 
			rd = request.getRequestDispatcher("/WEB-INF/listclasses.jsp");
			rd.forward(request, response);
			break;
			
		case "/listteachers":
			request.setAttribute("pageload" ,"N"); 
			rd = request.getRequestDispatcher("/WEB-INF/listteachers.jsp");
			rd.forward(request, response);
			break;
			
		case "/addstudent":
			request.setAttribute("pageload" ,"N"); 
			rd = request.getRequestDispatcher("/WEB-INF/addstudent.jsp");
			rd.forward(request, response);
			break;
			
		case "/addstudentdb":
			addstudentdb(request, response);
			break;
			
		case "/genreport":
			request.setAttribute("pageload" ,"N"); 
			rd = request.getRequestDispatcher("/WEB-INF/genreport.jsp");
			rd.forward(request, response);
			break;
			
		case "/genreportdb":
			genreportdb(request, response);
			break;
			
		default:
			break;
		}
	}

	private void checklogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("*********** START-checklogin)");

		String emailId = (String) request.getParameter("emailId");
		String password = (String) request.getParameter("password");
		System.out.println("Email from user " + emailId);
		System.out.println("Password from user " + password);

		try {
			Connection con = JDBCUtill.getMySqlConnection();
			PreparedStatement pstmt = con.prepareStatement(LOGIN);
			pstmt.setString(1, emailId);
			ResultSet rs = pstmt.executeQuery();
			int found=0;

			while (rs.next()) {

				String emaildb = rs.getString("EMAIL");
				String passworddb = rs.getString("PASSWORD");

				System.out.println("Email " + emaildb);
				System.out.println("Password " + passworddb);

				if ((emailId.equals(emaildb)) && (password.equals(passworddb))) {
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
					rd.forward(request, response);
					found++;
					break;
				}
					
			}
			if(found==0)
			{
			request.setAttribute("pageload","Y");
			request.setAttribute("alertMsg", "login Failed,enter correct details");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/loginadmin.jsp");
			rd.forward(request, response);
			}
			JDBCUtill.cleanup(pstmt, con);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error Occured while Searching  the data in to STUDENT_PROJECT Table");
		}

		
		System.out.println("*********** END-checklogin");
	}

	private void addsubjectdb(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("*********** START-addsubjectdb)");

		String rollno = (String) request.getParameter("SubjectID");
		String name = (String) request.getParameter("name");
		String shortcode = (String) request.getParameter("shortcode");

		int found = 0;

		try {
			Connection con = JDBCUtill.getMySqlConnection();
			PreparedStatement pstmt = con.prepareStatement(ADDSUBJECTSTODB);
			pstmt.setString(1, rollno);
			pstmt.setString(2, name);
			pstmt.setString(3, shortcode);
			found = pstmt.executeUpdate();

			if (found == 0) {
				request.setAttribute("pageload","Y");
				request.setAttribute("alertMsg", "Insert Failed");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addsubject.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("pageload","Y");
				request.setAttribute("alertMsg", "Insert Success");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addsubject.jsp");
				rd.forward(request, response);
			}
			JDBCUtill.cleanup(pstmt, con);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error Occured while inserting data into SUBJECT_DETAILS Table");
			request.setAttribute("pageload","Y");
			request.setAttribute("alertMsg", "Insert Failed,Duplicate key");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addsubject.jsp");
			rd.forward(request, response);
		}

		System.out.println("*********** END-addsubjectdb");
	}
	private void addclassesdb(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("*********** START-addclassesdb)");

		String cid = (String) request.getParameter("ClassID");
		String cname = (String) request.getParameter("Classname");
		String cshortcode = (String) request.getParameter("Class_shortcode");

		int found = 0;

		try {
			Connection con = JDBCUtill.getMySqlConnection();
			PreparedStatement pstmt = con.prepareStatement(ADDCLASSESTODB);
			pstmt.setString(1, cid);
			pstmt.setString(2, cname);
			pstmt.setString(3, cshortcode);
			found = pstmt.executeUpdate();

			if (found == 0) {
				request.setAttribute("pageload","Y");
				request.setAttribute("alertMsg", "Insert Failed");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addclasses.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("pageload","Y");
				request.setAttribute("alertMsg", "Insert Success");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addclasses.jsp");
				rd.forward(request, response);
			}
			JDBCUtill.cleanup(pstmt, con);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error Occured while inserting data into CLASS_DETAILS Table");
			request.setAttribute("pageload","Y");
			request.setAttribute("alertMsg", "Insert Failed,Duplicate Key");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addclasses.jsp");
			rd.forward(request, response);
		}

		System.out.println("*********** END-addclassesdb");
	}

	private void addteachersdb(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("*********** START-addteachersd)");

		String tid = (String) request.getParameter("TeacherID");
		String tfname = (String) request.getParameter("Tfname");
		String tsname = (String) request.getParameter("Tsname");

		int found = 0;

		try {
			Connection con = JDBCUtill.getMySqlConnection();
			PreparedStatement pstmt = con.prepareStatement(ADDTEACHERSTODB);
			pstmt.setString(1, tid);
			pstmt.setString(2, tfname);
			pstmt.setString(3, tsname);
			found = pstmt.executeUpdate();

			if (found == 0) {
				request.setAttribute("pageload","Y");
				request.setAttribute("alertMsg", "Insert Failed");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addteachers.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("pageload","Y");
				request.setAttribute("alertMsg", "Insert Success");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addteachers.jsp");
				rd.forward(request, response);
			}
			JDBCUtill.cleanup(pstmt, con);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error Occured while inserting data into CLASS_DETAILS Table");
			request.setAttribute("pageload","Y");
			request.setAttribute("alertMsg", "Insert Failed,Duplicaate Key");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addteachers.jsp");
			rd.forward(request, response);
		}

		System.out.println("*********** END-addteachersd");
	}
	private void assignSCtoDB(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("*********** START-addteachersd)");

		String sname = (String) request.getParameter("sname");
		String cname = (String) request.getParameter("cname");

		int found = 0;

		try {
			Connection con = JDBCUtill.getMySqlConnection();
			PreparedStatement pstmt = con.prepareStatement(ASSIGNSCTODB);
			pstmt.setString(1, cname);
			pstmt.setString(2, sname);
			found = pstmt.executeUpdate();

			if (found == 0) {
				request.setAttribute("pageload","Y");
				request.setAttribute("alertMsg", "Insert Failed");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/assignSubjecttoclasses.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("pageload","Y");
				request.setAttribute("alertMsg", "Insert Success");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/assignSubjecttoclasses.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error Occured while inserting data into CLASS_DETAILS Table");
			request.setAttribute("pageload","Y");
			request.setAttribute("alertMsg", "Insert Failed,Duplicate Key");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/assignSubjecttoclasses.jsp");
			rd.forward(request, response);
		}

		System.out.println("*********** END-addteachersd");
	}
	
	private void addstudentdb(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("*********** addstudent)");

		
		String SFName = (String) request.getParameter("SFName");
		String SLName = (String) request.getParameter("SLName");
		String cname = (String) request.getParameter("cname");
		

		int cid = 0;
		String cnme = null;
		String csc = null;
	
		System.out.println("*********** SFName="+SFName);
		System.out.println("*********** SLName="+SLName);
		System.out.println("*********** cname="+cname);
		

		try {
			Connection con = JDBCUtill.getMySqlConnection();
			PreparedStatement pstmt = con.prepareStatement(SEARCH);
			pstmt.setString(1, cname);
			ResultSet rs = pstmt.executeQuery();
	
			while (rs.next()) {

				  cid = rs.getInt("CID");
				  cnme = rs.getString("CNAME");
				  csc = rs.getString("CSHORTCODE");
			}
			
			System.out.println("*********** CID="+cid);
			System.out.println("*********** CNAME="+cnme);
			System.out.println("*********** CODE="+csc);
			JDBCUtill.cleanup(pstmt, con);
			 
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("pageload","Y");
			request.setAttribute("alertMsg", "Insert Failed,Duplicate Key");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addstudent.jsp");
			rd.forward(request, response);
		}
		
		
		Transaction tx =null;
		try {
			
			SessionFactory sf=HibernateUtillXml.getSessionFactory();
			Session session = sf.openSession();
			tx = session.beginTransaction();
			
			System.out.println("*********** hibernateinside");
	 
			classes classes = new classes(cid,cnme,csc);
			student student = new student(SFName,SLName,classes);
	
			session.save(student);
 
			tx.commit();
			session.close();
			request.setAttribute("pageload","Y");
			request.setAttribute("alertMsg", "Insert Success");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addstudent.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			if(tx!=null) {
				tx.rollback();
				request.setAttribute("pageload","Y");
				request.setAttribute("alertMsg", "Insert Failed,Duplicate Key");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addstudent.jsp");
				rd.forward(request, response);
			}
		}
		
		System.out.println("*********** addstudent");
	}
	private void genreportdb(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("*********** genreportdb)");

		
		String cname = (String) request.getParameter("cname");
	
		int sid = 0;
		String slname = null;
		String csc = null;
		 
		int cid=0;

		try {
			Connection con = JDBCUtill.getMySqlConnection();
			PreparedStatement pstmt = con.prepareStatement(SEARCHCLASS);
			pstmt.setString(1,cname);
			ResultSet rs = pstmt.executeQuery();
	
			while (rs.next()) {

				  cid = rs.getInt("CID");
				  cname = rs.getString("CNAME");
				  csc = rs.getString("CSHORTCODE");
			}
			
			System.out.println(cid);
			System.out.println(cname);
			System.out.println(csc);
			
			
			request.setAttribute("pageload","Y");
			request.setAttribute("CID", cid);
			request.setAttribute("COURSEFNAME", cname);
			request.setAttribute("alertMsg", "Insert Success");
			
			System.out.println("*********** 4)");
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/genreportdisplay.jsp");
			rd.forward(request, response);
			 
			JDBCUtill.cleanup(pstmt, con);
		} catch (Exception e) {
			System.out.println("*********** 5)");
			e.printStackTrace();
			request.setAttribute("pageload","Y");
			request.setAttribute("alertMsg", "Insert Failed,Duplicate Key");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/genreportdisplay.jsp");
			rd.forward(request, response);
		}
		
		System.out.println("*********** genreportdb");
	}
	private void assignTStoDB(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("*********** START- assignTStoDB)");

		String sname = (String) request.getParameter("sname");
		String tfname = (String) request.getParameter("tfname");

		int found = 0;

		try {
			Connection con = JDBCUtill.getMySqlConnection();
			PreparedStatement pstmt = con.prepareStatement(ASSIGNSTSODB);
			pstmt.setString(1, sname);
			pstmt.setString(2, tfname);
			found = pstmt.executeUpdate();

			if (found == 0) {
				request.setAttribute("pageload","Y");
				request.setAttribute("alertMsg", "Insert Failed");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/assignTS.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("pageload","Y");
				request.setAttribute("alertMsg", "Insert Success");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/assignTS.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error Occured while inserting data into CLASS_DETAILS Table");
			request.setAttribute("pageload","Y");
			request.setAttribute("alertMsg", "Insert Failed,Duplicate Key");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/assignTS.jsp");
			rd.forward(request, response);
		}

		System.out.println("*********** END- assignTStoDB");
	}
	
}

