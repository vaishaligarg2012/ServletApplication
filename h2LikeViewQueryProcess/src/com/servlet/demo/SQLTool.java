package com.servlet.demo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.RowSetMetaData;

/**
 * @author Vaishali Garg
 *
 */
@SuppressWarnings("serial")
public class SQLTool extends HttpServlet {
	PrintWriter pw;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		pw=response.getWriter();

		String title=	"Tool";

		pw.println(
				"<html>\n" +
						"<head><title>" + title + "</title></head>\n" +
						"<body bgcolor = \"#f0f0f0\">\n" +
				"<h1 align = \"center\">SQL Tool</h1>\n");
		pw.println("<div><form method='post'><input type = textarea name= query style='width:100%;height:50%;font-size:larger'placeholder='Write Query here...' />");
		pw.println("<div align='right'><button type=submit>Execute</button></div>");
		pw.println("<div style='width:100%; height:35%; color:blue;background:white'>Output Table:");

	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request,response);
		String s1=request.getParameter("query");
		System.out.println(s1+" ");
		String content=s1.toLowerCase();
		ConnectionStart obj=new ConnectionStart();
		if(content.indexOf("delete") != -1){
			int s=	obj.deleteTable(content);
			if(s!=0) {
				pw.println("rows Deleted!!");
			}else {
				pw.println("Something went Wrong");
			}
		}else
			if(content.indexOf("select") != -1){
				Object[] arr=obj.selectTable(content);
				ResultSetMetaData d =null;
				ResultSet rs=null;
				try {
					d =   (ResultSetMetaData)arr[1];
					rs=(ResultSet)arr[0];
					System.out.println("Result Set meta "+d.getColumnCount()+" "+d.getColumnName(1));
					//conn.close();
					pw.println("<table border=1 cellpadding=1 cellspacing=1 width=70%>");

					for(int y=1;y<=d.getColumnCount();y++) {
						pw.print("<th>" + d.getColumnName(y) + "</th>");
					}
					while(rs.next()) {
						pw.print("<tr>");
						pw.print("</tr>");
						for (int j = 1; j <= d.getColumnCount();j++) {
							pw.print("<td>" +rs.getObject(j)  + "</td>");
						}
					}
					pw.println("</table>");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(content.indexOf("insert") != -1){
				int x=obj.insertIntoTable(content);
				if(x==1) {
					pw.println("1 row Inserted!!");
				}else {
					pw.println("Something went Wrong");
				}
			}else if(content.indexOf("update") != -1){
				int d=obj.updateTable(content);
				if(d==1) {
					pw.println("1 row Updated!!");
				}else {
					pw.println("Something went Wrong");
				}

			}
		pw.println("</div></form></div>");
		pw.println("</body>");
		pw.println("</head>");
		pw.println("</html>");
	}
}
