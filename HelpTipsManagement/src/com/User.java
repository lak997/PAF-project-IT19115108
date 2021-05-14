package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
	
	
		
		//A common method to connect to the DB
	//A common method to connect to the DB
	private Connection connect(){
		Connection con = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3311/tech", "root", "");
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return con;
	}
			
		
		
		//Insert Project Details
		public String insertTips(String tipId, String relatedArea, String tipDetail, String date){
			String output = "";
			try{
				Connection con = connect();
					if (con == null){
						return "Error while connecting to the database for inserting."; 
				}
				
					
					// create a prepared statement
					String query = "INSERT INTO `tipstable`(`tipId`, `relatedArea`, `tipDetail`, `date`) VALUES (?,?,?,?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					
					 // binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2, relatedArea);
					 preparedStmt.setString(3, tipDetail);
					 preparedStmt.setString(4, date);
					
					 
					 
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 
					 String newTips = readTips(); 
					 output = "{\"status\":\"success\", \"data\": \"" + newTips + "\"}";
					 
					 }catch (Exception e)
					 {
						 
						 output = "{\"status\":\"error\", \"data\":\"Error while inserting the tips.\"}"; 
						 System.err.println(e.getMessage());
					 }
			 return output;
		 }
		
		
		
		
		public String readTips(){
			String output = "";
			try{
				Connection con = connect();
					if (con == null){
						return "Error while connecting to the database for reading."; 
			}
					
				// Prepare the html table to be displayed
				output = 
						"<table border='1' >"+ 
						"<tr >" +
							 "<th >Related Area</th>" +
							 "<th >Tip Detail</th>" +
							 "<th>Date</th>" +		
							 "<th>Update</th>" +
							 "<th>Remove</th>" +
						
						 "</tr>";
	
				String query = "select * from `tipstable`";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				 
				 
				 // iterate through the rows in the result set
				 while (rs.next()){
					 
					 
					 String tipId =  Integer.toString(rs.getInt("tipId"));
					 String relatedArea = rs.getString("relatedArea");
					 String tipDetail = rs.getString("tipDetail");
					 String date = rs.getString("date");
				
					 
	
					 
					 // Add into the html table
					 
					 //output += "<tr><td>" + orderId + "</td>";
					 output += "<td>" + relatedArea + "</td>";
					 output += "<td>" + tipDetail + "</td>";
					 output += "<td>" + date + "</td>";
					 
				
		
					 
					 
					 // buttons
					
					 output += "<td><input name='btnUpdate' type='button' value='Update' "
								+ "class='btnUpdate btn btn-secondary' data-userid='" + tipId + "'></td>"
								+ "<td><input name='btnRemove' type='button' value='Remove' "
								+ "class='btnRemove btn btn-danger' data-userid='" + tipId + "'></td></tr>"; 
				 }
			 con.close();
			 
			 // Complete the html table
			 output += "</table>";
			 
			 
			 }catch (Exception e){
				 
				 output = "Error while reading the Tips.";
				 System.err.println(e.getMessage());
			 }
			 return output;
			 
		}
		
		
		
		public String updateTips(String tipId, String relatedArea, String tipDetail, String date){ 
			String output = ""; 
			try{
				Connection con = connect();
				if (con == null){
					return "Error while connecting to the database for updating."; 
				} 
				
				 // create a prepared statement
				String query = "UPDATE `tipstable` SET `relatedArea`=?,`tipDetail`=?,`date`=? WHERE `tipId`=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				  
				 preparedStmt.setString(1, relatedArea);
				 preparedStmt.setString(2, tipDetail);
				 preparedStmt.setString(3, date);
				 preparedStmt.setString(4, tipId);
				 
				// preparedStmt.setString(4, sector);
				
				 
 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 
				 String newTips = readTips(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newTips + "\"}";
				 
		
				 } catch (Exception e) {
					 
					 output = "{\"status\":\"error\", \"data\": \"Error while updating the Tips.\"}";
					 System.err.println(e.getMessage()); 
				 } 
				 return output; 
		 }
		
		

		public String deleteTips(String tipId) { 
			String output = ""; 
			try{ 
				Connection con = connect();
				if (con == null) { 
					return "Error while connecting to the database for deleting."; 
				} 
					// create a prepared statement
				    String query ="DELETE FROM `tipstable` WHERE tipId=?";
					PreparedStatement preparedStmt = con.prepareStatement(query); 
					
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(tipId)); 
					
					// execute the statement
					preparedStmt.execute(); 
					con.close(); 
					
					String newTips = readTips(); 
					output = "{\"status\":\"success\", \"data\": \"" + newTips + "\"}"; 
					
			} catch (Exception e) { 
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the Tips.\"}"; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		}
		
}