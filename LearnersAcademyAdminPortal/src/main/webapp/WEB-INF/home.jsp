<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">
<%@
include file="css/home.css" 
%>
</style>
 </head>
  <body>
    <header>
      <!-- Navbar -->
      <nav id="navbar">
        <div class="container">
          <h1><a href="#">Learner's Academy</a></h1>
          <ul>
            <li><a href="#"><form id="logout" method="post" action="<%=request.getContextPath()%>/logout">
			<input type="submit" id="logout" value="Logout" class="frm">			
			</form>
			</a></li>
            
          </ul>
        </div>
      </nav>
      <!-- Showcase Area -->
      <div id="showcase">
        <div class="container">
          <div class="showcase-content">
            <h2>Welcome Administrator</h2>
            <p class="lead">
             Select a task from below
            </p>
          </div>
        </div>
      </div>
    </header>

   
    <!-- Our Services -->
    <div id="services">
      <div class="container">
        <h3 class="bg-dark-line"><span>Tasks</span></h3>
        
      
		 <% String message = (String)request.getAttribute("alertMsg");%>
		 <% String pageload = (String)request.getAttribute("pageload");%>
		 <% if(pageload=="Y"){%>
         <script type="text/javascript">
         var msg = "<%=message%>";
         alert(msg);
         </script>
          <%}%>
        
         <div class="box bg-primary">
         <form id="logout" method="post" action="<%=request.getContextPath()%>/addsubject" >
			<input type="submit" id="addstudent" value="Add Subjects" class="frm1">			
			</form>
        </div>
        
        <div class="box light-gray">
         <form id="logout" method="post" action="<%=request.getContextPath()%>/addclass" >
			<input type="submit" id="addstudent" value="Add Classes" class="frm1">			
			</form>
        </div>
        
        <div class="box bg-primary">
           <form id="logout" method="post" action="<%=request.getContextPath()%>/addteachers" >
			<input type="submit" id="addstudent" value="Add Teachers" class="frm1">			
			</form>
        </div>
        
        <div class="box light-gray">
          <form id="logout" method="post" action="<%=request.getContextPath()%>/addstudent" >
			<input type="submit" id="addstudent" value="Add Student" class="frm1">			
			</form>
        </div>
        
        <div class="box bg-primary">
           <form id="logout" method="post" action="<%=request.getContextPath()%>/assignSC" >
			<input type="submit" id="addstudent" value="Assign Subject to Classes" class="frm1">			
			</form>
        </div>
        
        <div class="box light-gray">
           <form id="logout" method="post" action="<%=request.getContextPath()%>/assignTS" >
			<input type="submit" id="addstudent" value="Assign Teachers to Subjects" class="frm1">			
			</form>
        </div>
        
        <div class="box bg-primary">
           <form id="logout" method="post" action="<%=request.getContextPath()%>/listsubjeccts" >
			<input type="submit" id="addstudent" value="List Subjects" class="frm1">			
			</form>
        </div>
        
        <div class="box box light-gray">
           <form id="logout" method="post" action="<%=request.getContextPath()%>/listclasses" >
			<input type="submit" id="addstudent" value="List Classes" class="frm1">			
			</form>
        </div>
        
       <div class="box box bg-primary">
           <form id="logout" method="post" action="<%=request.getContextPath()%>/listteachers" >
			<input type="submit" id="addstudent" value="List Teachers" class="frm1">			
			</form>
        </div>
        
       <div class="box light-gray">
          <form id="logout" method="post" action="<%=request.getContextPath()%>/genreport" >
			<input type="submit" id="addstudent" value="Generate Student Report" class="frm1">			
			</form>
        </div>
        
      
        
      </div>
    </div>

    <div class="clr"></div>


    <footer id="main-footer">
      <div class="container">
        <p>
          Learner's Academy Portal Design for Simplilearn &copy 2022
        </p>
      </div>
    </footer>
  </body>

</body>
</html>