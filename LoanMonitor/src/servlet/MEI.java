package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class MEI
 */
@WebServlet("/MEI")
public class MEI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	private Statement stat;
	private ResultSet result;
	private PrintWriter out;
	int month_Inst, n_month;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MEI() {
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
			String name = request.getParameter("app_name");
			int n = Integer.parseInt(request.getParameter("month"));
			out = response.getWriter();
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loan","root","wxwdd8008");
			stat = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			//String sql = "insert into loandetail(monthlyinstallment, monthfine) values('" + month_Inst + "', '"+ n_month + "')";
			//con.setAutoCommit(false);
			//stat.addBatch(sql);
			result = stat.executeQuery("select amount, paymentyear from application where name = " +  "'" + name + "'");
			//result.last();
			//System.out.println("rows before batch execution= "+ result.getRow());
			//stat.executeBatch();
			//con.commit();
			//System.out.println("Batch executed");
			
			while(result.next()) 
			{
				int x = result.getInt(1);
				int y = result.getInt(2);
				int monthInst = (x/(12*y))+(7/100)*x;
				int nMonth = (12/100)*monthInst*n;
				month_Inst = monthInst;
				n_month = nMonth;
				
				out.println("<br>" + "Monthly installment: " + monthInst + "<br>" + "Fine forvmonths: " + nMonth);
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
