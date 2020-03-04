package mypackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login
 */
@WebServlet("/appelli")
public class appelli  extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public appelli() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Abbiamo stabilito una connessione ad db
		Connection conn = Connessione.getCon();
				
		try {
					
			// Ottieni dati su appelli
			Statement smt = conn.createStatement();
			ResultSet rs = smt.executeQuery("select idAppello, Data, idMateria, Materia\r\n" + 
					"from appello inner join corso\r\n" + 
					"on appello.idMateria = corso.idcorso;");
			
			// Risposta contenente pagina dove dirigersi
			RequestDispatcher rd = request.getRequestDispatcher("appelli.jsp");
			request.setAttribute("tabella_appelli", rs);
			rd.forward(request,response);
					
		} catch (Exception e) {
					
			System.out.println("Errore");
					
		}
	}
}
