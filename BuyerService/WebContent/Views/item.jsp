<%@page import="model.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<%
	
	if (request.getParameter("itemCode") != null) 
		 { 
		 Item itemObj = new Item(); 
		 
		 String stsMsg = itemObj.insertItem(request.getParameter("itemCode"), 
		 request.getParameter("itemName"), 
		 request.getParameter("itemPrice"), 
		 request.getParameter("itemDesc")); 
		 session.setAttribute("statusMsg", stsMsg); 
	 } 
%>

<%
 Item itemObj = new Item(); 
 out.print(itemObj.readItems()); 
 
 

%>

<%
if (request.getParameter("itemID") != null){ 
	 Item item= new Item(); 
	 String stsMsg = item.deleteItem(request.getParameter("itemID")); 
	 session.setAttribute("statusMsg", stsMsg); 
	}
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
<link rel = "stylesheet" href = "bootstrap.min.css" >   
</head>
<body>
		<br><br><br>
		
		<div class = "container">
			<div class = "row">
				<div class ="col"  >
						<form method = "post" action = "item.jsp">
							Item code : <input name = "itemCode" type = "text"> <br>
							Item name : <input name = "itemName" type = "text"> <br>
							Item price: <input name = "itemPrice" type = "text"><br>
							Item description: <input name = "itemDesc" type = "text"><br>
							
							<input name = "btnSubmit" type="submit" value = "save" " class="btn btn-primary">
							
							<div class="alert alert-success">
 								<% out.print(session.getAttribute("statusMsg"));%>
							</div>
					</form>
				</div>	
		    </div>
		</div>
</body>
</html>