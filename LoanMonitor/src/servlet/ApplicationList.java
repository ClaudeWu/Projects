package servlet;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ApplicationList
 */
@WebServlet("/ApplicationList")
public class ApplicationList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	private Statement stat;
	private ResultSet result;
	private PrintWriter out;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try 
		{
			out = response.getWriter();
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loan","root","wxwdd8008");
			stat = con.createStatement();
			result = stat.executeQuery("select * from application");
			while(result.next()) 
			{
				String x = result.getString(1);
				String s = result.getString(2);
				String y = result.getString(3);
				String e = result.getString(4);
				int f = result.getInt(5);
				int g = result.getInt(6);
				out.println( "<br>" + "Name: " + x 
						+ "<br>"+ "Age: "+ s 
						+ "<br>" + "Address: " + y 
						+ "<br>" + "Phone number: " + e 
						+ "<br>" + "Loan Amount: " + f 
						+ "<br>" + "Years of payment: " + g + "<br>");
			}
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println("unable to load driver");
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
