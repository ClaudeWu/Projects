package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertLoan
 */
@WebServlet("/InsertLoan")
public class InsertLoan extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertLoan() {
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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loan","root", "wxwdd8008");
			System.out.println("connection established!");
			// Initialize the database 
			//Connection con = DatabaseConnection.initializeDatabase();
			// Create a SQL query to insert data into demo table
			PreparedStatement st = con.prepareStatement("insert into application values(?, ?, ?, ?, ?, ?)");
			// insert user data to table
			st.setString(1, request.getParameter("name"));
			st.setString(2, request.getParameter("age"));
			st.setString(3, request.getParameter("address"));
			st.setString(4, request.getParameter("p_number"));
			st.setInt(5, Integer.valueOf(request.getParameter("l_amount")));
			st.setInt(6, Integer.valueOf(request.getParameter("p_year")));
			// Execute the insert command using executeUpdate() 
            // to make changes in database
			st.executeUpdate();
			// Close all the connections 
            st.close(); 
            con.close();
            // Get a writer pointer  
            // to display the successful result 
            PrintWriter out = response.getWriter(); 
            out.println("<html><body><b>Successfully Inserted" + "</b></body></html>");
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
