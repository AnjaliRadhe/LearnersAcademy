<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%
String id = request.getParameter("userid");
String driver = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/";
String database = "Adminportal";
String userid = "root";
String password = "root12345";
try {
	Class.forName(driver);
} catch (ClassNotFoundException e) {
	e.printStackTrace();
}
Connection connection = null;
PreparedStatement statement = null;
ResultSet resultSet = null;
%>
<!DOCTYPE html>
<html>

<style type="text/css">
<style type="text/css">
<%@
include file="css/style.css" 
%>
</style>

<body>
	<div id="login-form-wrap">
		<h1>Class Report</h1>

		<%
		String message = (String) request.getAttribute("alertMsg");
		%>
		<%
		int cid = (int) request.getAttribute("CID");
		%>
		<%
		String cNAME = (String) request.getAttribute("COURSEFNAME");
		%>
		<%String name[]=new String[10];%>
		<%int count=0;%>

		<div class="divTable blueTable">

			<div class="divTableBody">

				<div class="divTableRow">
					<div class="divTableCell">Course ID</div>
					<div class="divTableCell"><%=cid%></div>
				</div>

				<div class="divTableRow">
					<div class="divTableCell">Course Name</div>
					<div class="divTableCell"><%=cNAME%></div>
				</div>


			</div>

		</div>
		<table align="center" cellpadding="5" cellspacing="5" border="1">
			<tr style="font-size: 16px;">
				<b>Student Details</b>
			</tr>
			<tr bgcolor="#D3D3D3">
				<td><b>Student ID</b></td>
				<td><b>Student First Name</b></td>
				<td><b>Student Last Name</b></td>

			</tr>

			<%
			try {

				connection = DriverManager.getConnection(connectionUrl + database, userid, password);
				PreparedStatement pstmt = connection.prepareStatement("select * from STUDENT_details WHERE CID = ?");
				pstmt.setInt(1, cid);
				ResultSet rs = pstmt.executeQuery();
			%>

			<p>

				<%
				while (rs.next()) {
				%>
			
			<tr>
				<td><%=rs.getString("SID")%></td>
				<td><%=rs.getString("SFNAME")%></td>
				<td><%=rs.getString("SLNAME")%></td>
			</tr>
			</p>
			<%
			}
			connection.close();
			} catch (Exception e) {
			e.printStackTrace();
			}
			%>


			<table align="center" cellpadding="5" cellspacing="5" border="1">
				<tr style="font-size: 16px;">
					<b>Subject Details</b>
				</tr>
				<tr bgcolor="#D3D3D3">
					<td><b>Course Name</b></td>
					<td><b>Subject Name</b></td>

				</tr>

				<%
				try {

					connection = DriverManager.getConnection(connectionUrl + database, userid, password);
					PreparedStatement pstmt = connection.prepareStatement("select * from CLASSES_SUBJECTS WHERE CLASS_NAME = ?");
					pstmt.setString(1, cNAME);
					ResultSet rs = pstmt.executeQuery();
				%>

				<p>

					<%
					while (rs.next()) {

						name[count]=rs.getString("SUBJECT_NAME");
						System.out.println("*********** name)"+ name[count]);
						count++;
					%>
				
				<tr>
					<td><%=rs.getString("CLASS_NAME")%></td>
					<td><%=rs.getString("SUBJECT_NAME")%></td>

				</tr>
				</p>
				<%
				}
				connection.close();
				} catch (Exception e) {
				e.printStackTrace();
				}
				%>



				<table align="center" cellpadding="5" cellspacing="5" border="1">
				<tr style="font-size: 16px;">
					<b>Teacher Details</b>
				</tr>
				<tr bgcolor="#D3D3D3">
					<td><b>Subject Name</b></td>
					<td><b>Teacher Name</b></td>

				</tr>


				<% 
				try {
					for(int i=0;i<count;i++)
					{
					System.out.println("********teacherloop)"+ name[i]);
					connection = DriverManager.getConnection(connectionUrl + database, userid, password);
					PreparedStatement pstmt1 = connection.prepareStatement("select * from TEACHERS_SUBJECTS WHERE SUBJECT_NAME = ?");
					pstmt1.setString(1, name[i]);
					ResultSet rs1 = pstmt1.executeQuery();
					
				%>
			    <p>
					<%
					while (rs1.next()) {
						System.out.println("*********** while)");
					%>
				
				<tr>
				<%System.out.println("*********** table)");%>
					<td><%=rs1.getString("TEACHER_NAME")%></td>
					<td><%=rs1.getString("SUBJECT_NAME")%></td>

				</tr>
				  </p>
				  
				<%
				}
					}
				connection.close();
				} catch (Exception e) {
				e.printStackTrace();
				}
				%>

</table>

				</div>
				<div id="create-account-wrap">
			 <form id="addsubject" method="post"
			action="<%=request.getContextPath()%>/home">
			<p>
				<input type="submit" id="submit" value="Go back Home!">
			</p>
			</form>
		</div>
</body>
</html>