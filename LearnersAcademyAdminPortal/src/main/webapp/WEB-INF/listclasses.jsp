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
		<h1>Classes List</h1>

		<table align="center" cellpadding="5" cellspacing="5" border="1">
			<tr>

			</tr>
			<tr bgcolor="#D3D3D3">
				<td><b>Class ID</b></td>
				<td><b>Class Name</b></td>
				<td><b>Class Shortcode</b></td>

			</tr>
			<%
			try {
				connection = DriverManager.getConnection(connectionUrl + database, userid, password);
				statement = connection.createStatement();
				String sql = "select * from CLASS_DETAILS";
				resultSet = statement.executeQuery(sql);
				while (resultSet.next()) {
			%>
			<tr>

				<td><%=resultSet.getString("CID")%></td>
				<td><%=resultSet.getString("CNAME")%></td>
				<td><%=resultSet.getString("CSHORTCODE")%></td>

			</tr>

			<%
			}
			connection.close();
			} catch (Exception e) {
			e.printStackTrace();
			}
			%>

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