package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.dbConnection;


public class Item {
	
	public Item (){
		
	}
	
	public String insertItem(String code, String name, String price, String desc)
	{
			dbConnection con = new dbConnection();
			Connection connection = con.connect();
			
			PreparedStatement ps = null;
			String output = "";
			
			try {
				
			String query = " insert into item  (`itemID`,`itemCode`,`itemName`,`itemPrice`,`itemDesc`)"
					 + " values (?, ?, ?, ?, ?)"; 
		
			ps = connection.prepareStatement(query);
			
			// binding values
			 ps.setInt(1, 0); 
			 ps.setString(2, code); 
			 ps.setString(3, name); 
			 ps.setDouble(4, Double.parseDouble(price)); 
			 ps.setString(5, desc); 
			 
			//execute the statement
			 ps.execute(); 
			 connection.close(); 
			 
			 System.out.println("inserted successfully");
			
			} catch (Exception e) {
				
				output = "Error while inserting"; 
				 System.err.println(e.getMessage()); 
				
			}
				return output;
		
	}
	
	
	public String readItems()
	{ 
		dbConnection con = new dbConnection();
		Connection connection = con.connect();
		
		
		String output = "";
		
		try{ 
		 
			 if (con == null) 
			 { 
				 return "Error while connecting to the database for reading."; 
			 } 
			 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Item Code</th>" 
			 +"<th>Item Name</th><th>Item Price</th>"
			 + "<th>Item Description</th>" 
			 + "<th>Update</th><th>Remove</th></tr>"; 
			 
			 String query = "select * from item"; 
			 
			 Statement stmt = connection.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
			 String itemID = Integer.toString(rs.getInt("itemID")); 
			 String itemCode = rs.getString("itemCode"); 
			 String itemName = rs.getString("itemName"); 
			 String itemPrice = Double.toString(rs.getDouble("itemPrice")); 
			 String itemDesc = rs.getString("itemDesc"); 
			 
			 // Add a row into the html table
			 output += "<tr><td>" + itemCode + "</td>"; 
			 output += "<td>" + itemName + "</td>"; 
			 output += "<td>" + itemPrice + "</td>";  
			 output += "<td>" + itemDesc + "</td>";
			 
			 // buttons
			 output += "<td> <form method='post' action='item.jsp'> <input name='btnUpdate' " 
			 + " type='button' value='Update' class='btn btn-secondary'></td>"
			 + "<td><form method='post' action='item.jsp'>"
			 + "<input name='btnRemove' " 
			 + " type='submit' value='Remove' class='btn btn-danger' >"
			 + "<input name='itemID' type='hidden' " 
			 + " value='" + itemID + "'>" + "</form></td></tr>"; 
			 } 
			 connection.close(); 
			 
			 // Complete the html table
			 output += "</table>"; 
			 } 
		catch (Exception e) { 
			 output = "Error while reading the items."; 
			 System.err.println(e.getMessage()); 
		 } 
		return output; 
	
	}
	
	public String deleteItem(String itemID){ 
		dbConnection con = new dbConnection();
		Connection connection = con.connect();
		
		PreparedStatement ps = null;
		
		
		String output = "";
		try
		 { 
			
			 if (con == null)  { 
				 return "Error while connecting to the database for deleting."; 
			 } 
			 
			 // create a prepared statement
			 String query = "delete from item where itemID=?"; 
			 ps = connection.prepareStatement(query); 
			 
			 // binding values
			 ps.setInt(1, Integer.parseInt(itemID)); 
			 
			 // execute the statement
			 ps.execute(); 
			 connection.close(); 
			 
			 output = "Deleted successfully"; 
		
		 }catch (Exception e) 
		 { 
			 output = "Error while deleting the item."; 
			 System.err.println(e.getMessage()); 
		 } 
		return output;
	}
}
