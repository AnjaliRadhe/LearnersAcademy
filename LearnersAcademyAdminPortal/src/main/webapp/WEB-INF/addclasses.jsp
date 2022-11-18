<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Add Subjecct</title>
<style type="text/css">
<%@
include file="css/style.css" 
%>
</style>
</head>
<body>
	<!-- partial:index.partial.html -->
	<div id="login-form-wrap">
		<h2>Add Classes</h2>
		
		 <% String message = (String)request.getAttribute("alertMsg");%>
		 <% String pageload = (String)request.getAttribute("pageload");%>
		 <% if(pageload=="Y"){%>
         <script type="text/javascript">
         var msg = "<%=message%>";
         alert(msg);
         </script>
          <%}%>
         
		<form id="addsubject" method="post"
			action="<%=request.getContextPath()%>/addclassesdb">
			<p>
				<input type="text" id="ClassID" name="ClassID"
					placeholder="ClassID" required=""><i
					class="validation"><span></span><span></span></i>
			</p>
			<p>
				<input type="text" id="Classname" name="Classname"
					placeholder="Classname" required=""><i
					class="validation"><span></span><span></span></i>
			</p>
			<p>
				<input type="text" id="Class_shortcode" name="Class_shortcode"
					placeholder="Class_shortcode" required=""><i class="validation"><span></span><span></span></i>
			</p>
			<p>
				<input type="submit" id="submit" value="Add to DB">
			</p>
		</form>
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