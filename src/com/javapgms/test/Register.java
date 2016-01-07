/*QUE 3 :write a program to get the data in the HTML form and save the data into the database
 *QUE 4: Validate the input data before saving and show error on invalid data
 */
package com.javapgms.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class RegisterServlet
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//int id=request.getIntHeader("userid");
		String n=request.getParameter("name");
		String p=request.getParameter("pass");
		String e=request.getParameter("email");
		String c=request.getParameter("country");
		//String DRIVER = "org.hsqldb.jdbcDriver";
				
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
			PreparedStatement ps=con.prepareStatement("insert into SHWETHA.registeruser values(?,?,?,?)");
			ps.setString(1,n);
		ps.setString(2,p);
		ps.setString(3,e);
		ps.setString(4,c);
		
		int i=ps.executeUpdate();
		if(i>0)
		out.print("You are successfully registered...");
		
			
		}catch (Exception e2) {System.out.println(e2);}
		
		out.close();
	}

}
    
