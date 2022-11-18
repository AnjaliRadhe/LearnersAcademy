<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
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
Statement statement = null;
ResultSet resultSet = null;
%>
<!DOCTYPE html>
<html>
<style type="text/css">
<%@
include file="css/style.css" 
%>
</style>
<body>
	<div id="login-form-wrap">
		<h1>Add Student Details</h1>


 		<% String message = (String)request.getAttribute("alertMsg");%>
		 <% String pageload = (String)request.getAttribute("pageload");%>
		 <% if(pageload=="Y"){%>
         <script type="text/javascript">
         var msg = "<%=message%>";
         alert(msg);
         </script>
          <%}%>
          
		<FORM id="addstudent" method="post"
			action="<%=request.getContextPath()%>/addstudentdb">
			
			<p>
				<input type="text" id="SFName" name="SFName"
					placeholder="First Name" required=""><i
					class="validation"><span></span><span></span></i>
			</p>
			<p>
				<input type="text" id="SLName" name="SLName"
					placeholder="Last Name" required=""><i
					class="validation"><span></span><span></span></i>
			</p>
			<%
			try {
				connection = DriverManager.getConnection(connectionUrl + database, userid, password);
				statement = connection.createStatement();
				String sql = "select * from CLASS_DETAILS";
				resultSet = statement.executeQuery(sql);
			%>
			`
			<p>
				<label for="cars">Choose a class:</label> <select class="dropdown" name="cname">
					<%
					while (resultSet.next()) {
						String cname = resultSet.getString("CNAME");
					%>
					<option value="<%=cname%>"><%=cname%></option>
					<%
					}
					%>
				</select>
			</p>
			<%
			connection.close();
			} catch (Exception e) {
			e.printStackTrace();
			}
			%>
			<p>
				<input type="submit" id="submit" value="Add to DB">
			</p>
		</FORM>
		<div id="create-account-wrap">
			 <form id="addsubject" method="post"
			action="<%=request.getContextPath()%>/home">
			<p>
				<input type="submit" id="submit" value="Go back Home!">
			</p>
			</form>
		</div>
	</div>
</body>
</html>