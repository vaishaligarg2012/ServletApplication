package com.niit.servlet.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vaishali Garg
 *
 */
public class ConnectionStart {
	Connection conn =null;
	public ConnectionStart() {
		try {

			Class.forName("org.h2.Driver");
			conn=  DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//CRUD
	public int deleteTable(String query) {
		System.out.println(query);
		int r=0;
		try {
			Statement stmt=conn.createStatement(); 
			r=stmt.executeUpdate(query); 
			System.out.println("resultset "+r);
		
			conn.close(); 

		}catch(Exception e){
			e.printStackTrace();
		}

        return r;
	}
	public Object[] selectTable(String query) {
		List<String> obj=new ArrayList<>();
		ResultSet rs=null;
		ResultSetMetaData rsmd=null;
		System.out.println("Please get all data from table");
		try {
			Statement stmt=conn.createStatement();  
			rs=stmt.executeQuery(query); 
			//System.out.println("resultset "+rs.getRow()+" ");
			rsmd=rs.getMetaData(); 
			System.out.println(rsmd);
			//System.out.println("Result Set meta "+rsmd.getColumnCount()+" "+rsmd.getColumnName(1));



		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Object[]{rs,rsmd};
	}

	public int insertIntoTable(String query) {
		int r=0;
		try {
			System.out.println("Insert here");
			Statement stmt=conn.createStatement(); 
			r=stmt.executeUpdate(query); 
			System.out.println("resultset "+r);
			conn.close();  
		}catch(Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	public int updateTable(String query){
		int s=0;
		try {
			Statement stmt=conn.createStatement(); 
			s=stmt.executeUpdate(query); 
			System.out.println("resultset "+s);
			conn.close();  
		}catch(Exception e) {
			e.printStackTrace();
		}
         return s;
	}


}
