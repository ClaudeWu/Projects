package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AfterLogin
 */
@WebServlet("/AfterLogin")
public class AfterLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	private Statement stat;
	private PrintWriter out;
	private ResultSet result;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfterLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loan","root","wxwdd8008");
			stat = con.createStatement();
			String name = request.getParameter("username");
			String password = request.getParameter("password");
			String op = request.getParameter("r1");
			result = stat.executeQuery("select username, password from clients");
			out = response.getWriter();
			while(result.next()) 
			{
				String u = result.getString(1);
				String p = result.getString(2);
				if(u.equals(name) && p.equals(password) && op.equals("admin")) {
					RequestDispatcher dis=request.getRequestDispatcher("AdminEnd.html");
					dis.forward(request, response);  
				}
				else if(u.equals(name) && p.equals(password) && op.equals("client")) {
					RequestDispatcher dis=request.getRequestDispatcher("ClientEnd.html");
					dis.forward(request, response);
				}	
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

}
